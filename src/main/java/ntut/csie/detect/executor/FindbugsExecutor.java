package ntut.csie.detect.executor;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.service.impl.FileManagerServiceImpl;
import ntut.csie.detect.util.LogUtil;

public class FindbugsExecutor extends DecoratorExecutor {
	private String[] command;
	private String directory = "";
	private ProcessBuilder processBuilder;
	
	private final String logPrefix = "[FindbugsExecutor]";
	
	@Autowired
	FileManagerService fileManagerService;
	
	public FindbugsExecutor() {
	}
	
	public FindbugsExecutor(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}
	
	public FindbugsExecutor(String[] command, String directory) {
		this.command = command;
		this.directory = directory;
	}

	public void init() {
		if (fileManagerService == null) {
			fileManagerService = new FileManagerServiceImpl();
		}
		
		processBuilder = new ProcessBuilder(
				"java", 
        		"-jar",
        		"." + AnalysisConfiguration.findbugsPathPrefix + "findbugs.jar",
        		"-textui",
        		"-html",
        		"-bugCategories",
        		"security",
        		"." + AnalysisConfiguration.analysisPathPrefix + command[0]
        );
		processBuilder.directory(new File(directory));
	}

	@Override
	public void run() {
		LogUtil.log("掃描任務 (" + command[0] +") 開始初始", logPrefix);
		init();
		
		try {
			LogUtil.log("掃描任務 (" + command[0] +") 開始運行", logPrefix);
			
			Process p = processBuilder.start();
			
			fileManagerService.saveFile(
					directory + AnalysisConfiguration.findbugsErrorPathPrefix + command[0] + ".log",
					p.getErrorStream()
			);
			
			fileManagerService.saveFile(
					directory + AnalysisConfiguration.findbugsReportPathPrefix + command[0] + ".html",
					p.getInputStream()
			);
			
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LogUtil.log("掃描任務 (" + command[0] +") 運行結束", logPrefix);
		}
		
		super.run();
	}

	@Override
	public void setCommand(String command) {
		this.command = command.split(" ");
	}

	@Override
	public void setCommand(String[] command) {
		this.command = command;
	}

	@Override
	public void setDirectory(String directory) {
		this.directory = directory;
	}
}