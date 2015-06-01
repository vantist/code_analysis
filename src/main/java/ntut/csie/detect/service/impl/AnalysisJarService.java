package ntut.csie.detect.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ntut.csie.detect.component.AnalysisFile;
import ntut.csie.detect.service.AnalysisService;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.util.CommandExecutorUtil;

@Service("AnalysisJarService")
public class AnalysisJarService implements AnalysisService {
	private Stack<AnalysisFile> files = new Stack<AnalysisFile>();
	private final String analysisPathPrefix = "/WEB-INF/analysis/";
	private final String findbugsPathPrefix = "/WEB-INF/findbugs/";
	private String path;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	public AnalysisJarService(ServletContext context) {
		path = context.getRealPath(analysisPathPrefix);
	}
	
	@Override
	public Boolean check(AnalysisFile file) {
		return files.contains(file);
	}

	@Override
	@Scheduled(initialDelay = 1000, fixedDelay = 5000)
	public void analysis() {
		AnalysisFile file;
		
		if (files.empty())
			return;
		
		file = files.pop();
		
		new CommandExecutorUtil().run();
	}

	@Override
	public String save(MultipartFile file) {
		String newPath = path + File.separator + System.currentTimeMillis() + "_" + file.getOriginalFilename();
				
		try {
			fileManagerService.saveFile(newPath, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		files.push(new AnalysisFile(newPath));
		return newPath;
	}
}