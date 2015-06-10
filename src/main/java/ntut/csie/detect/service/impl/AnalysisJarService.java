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
import ntut.csie.detect.service.AnalysisService;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.util.impl.FindbugsExecutor;

@Service("AnalysisJarService")
public class AnalysisJarService implements AnalysisService {
	private Queue<AnalysisFile> files = new LinkedList<AnalysisFile>();
	private List<AnalysisFile> analysisFiles = new ArrayList<AnalysisFile>();
	private Set<Thread> findbugsExecutors = new HashSet<Thread>();
	private String path;
	
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
		FindbugsExecutor findbugsExecutor;
		Thread thread;
		
		System.out.println("開始排程掃描");
		
		while (!files.isEmpty() && checkRunningJobs() < 5) {
			file = files.poll();
			
			findbugsExecutor = new FindbugsExecutor(file.getFileName(), path);
			analysisFiles.add(file);
			thread = new Thread(findbugsExecutor, file.getFileName());
			findbugsExecutors.add(thread);
			thread.start();
		}
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
		
		for (Iterator<Thread> iterator = findbugsExecutors.iterator(); iterator.hasNext();) {
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
				System.out.println("從清單移除：" + fileName);
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