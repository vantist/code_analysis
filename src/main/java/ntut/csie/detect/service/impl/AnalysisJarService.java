package ntut.csie.detect.service.impl;

import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ntut.csie.detect.component.AnalysisFile;
import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.executor.AnalysisExecutor;
import ntut.csie.detect.service.AnalysisService;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.util.LogUtil;

@Service("AnalysisJarService")
public class AnalysisJarService implements AnalysisService {
	private Queue<AnalysisFile> files = new LinkedList<AnalysisFile>();
	private List<AnalysisFile> analysisFiles = new ArrayList<AnalysisFile>();
	private Set<Thread> analysisExecutors = new HashSet<Thread>();
	private String path;
	
	private final String logPrefix = "[AnalysisJarService]";
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	public AnalysisJarService(ServletContext context) {
		path = context.getRealPath("");
	}

	@Override
	@Scheduled(fixedDelay = 30000)
	public void analysis() {
		AnalysisFile file;
		AnalysisExecutor analysisExecutor;
		Thread thread;
		
		LogUtil.log("執行排程掃描，開始檢查動作", logPrefix);
		
		while (!files.isEmpty() && checkRunningJobs() < 5) {
			file = files.poll();
			
			analysisExecutor = new AnalysisExecutor(file.getFileName(), path);
			analysisFiles.add(file);
			thread = new Thread(analysisExecutor, file.getFileName());
			analysisExecutors.add(thread);
			thread.start();
		}
		
		LogUtil.log("排程動作結束", logPrefix);
	}

	@Override
	public String save(AnalysisFile analysisFile) {
		MultipartFile file = analysisFile.getFile();
		String newFilename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		String newPath = path + AnalysisConfiguration.analysisPathPrefix + newFilename;
				
		try {
			fileManagerService.saveFile(newPath, file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

		analysisFile.setFileName(newFilename);
		analysisFile.setPath(newPath);
		files.offer(analysisFile);
		return newPath;
	}

	@Override
	public int checkRunningJobs() {
		int count = 0;
		
		for (Iterator<Thread> iterator = analysisExecutors.iterator(); iterator.hasNext();) {
			Thread thread = (Thread) iterator.next();
			
			if (thread.getState().equals(State.TERMINATED)) {
				removeFromAnalysisList(thread.getName());
				iterator.remove();
			} else {
				count++;
			}
		}
		return count;
	}
	
	private void removeFromAnalysisList(String fileName) {
		for (Iterator<AnalysisFile> iterator = analysisFiles.iterator(); iterator.hasNext();) {
			AnalysisFile analysisFile = (AnalysisFile) iterator.next();
			
			if (analysisFile.getFileName().equals(fileName)) {
				LogUtil.log("從清單移除：" + fileName, logPrefix);
				iterator.remove();
			}
		}
	}

	@Override
	public int checkWaitingJobs() {
		return files.size();
	}

	@Override
	public List<AnalysisFile> getRunningJobs() {
		checkRunningJobs();
		return analysisFiles;
	}

	@Override
	public List<AnalysisFile> getWaitingJobs() {
		return new ArrayList<AnalysisFile>(files);
	}
}