package ntut.csie.detect.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ntut.csie.detect.component.AnalysisFile;
import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.service.AnalysisService;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.util.CommandExecutorUtil;
import ntut.csie.detect.util.impl.FindbugsExecutor;

@Service("AnalysisJarService")
public class AnalysisJarService implements AnalysisService {
	private Stack<AnalysisFile> files = new Stack<AnalysisFile>();
	private Set<CommandExecutorUtil> findbugsExecutors = new HashSet<CommandExecutorUtil>();
	private String path;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	public AnalysisJarService(ServletContext context) {
		path = context.getRealPath(AnalysisConfiguration.analysisPathPrefix);
	}
	
	@Override
	public Boolean check(AnalysisFile file) {
		return files.contains(file);
	}
	
	// 檢查正在掃描的任務有幾個
	private int checkRunning() {
		int count = 0;
		
		for (Iterator<CommandExecutorUtil> iterator = findbugsExecutors.iterator(); iterator.hasNext();) {
			CommandExecutorUtil findbugsExecutor = (CommandExecutorUtil) iterator.next();
			
			if (findbugsExecutor.isRunning())
				count++;
			else
				iterator.remove();
		}
		
		return count;
	}

	@Override
	@Scheduled(initialDelay = 1000, fixedDelay = 10000)
	public void analysis() {
		AnalysisFile file;
		FindbugsExecutor findbugsExecutor;
		
		
		while (!files.empty() && checkRunning() <= 5) {
			file = files.pop();
			
			findbugsExecutor = new FindbugsExecutor(file.getFileName(), path + AnalysisConfiguration.webinfPathPrefix);
			findbugsExecutor.run();
			findbugsExecutors.add(findbugsExecutor);
		}
	}

	@Override
	public String save(MultipartFile file) {
		String newFilename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		String newPath = path + File.separator + newFilename;
				
		try {
			fileManagerService.saveFile(newPath, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		AnalysisFile analysisFile = new AnalysisFile(newPath);
		analysisFile.setFileName(newFilename);
		files.push(analysisFile);
		return newPath;
	}
}