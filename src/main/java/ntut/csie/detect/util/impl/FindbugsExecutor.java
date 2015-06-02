package ntut.csie.detect.util.impl;

import java.io.File;
import java.io.IOException;

import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.util.CommandExecutorUtil;

public class FindbugsExecutor implements CommandExecutorUtil {
	private String[] command;
	private String directory = "";
	private ProcessBuilder processBuilder;
	private Boolean running = false;
	
	public FindbugsExecutor() {
	}
	
	public FindbugsExecutor(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}
	
	public void init() {
		processBuilder = new ProcessBuilder(
				"java", 
        		"-jar",
        		"./findbugs/lib/findbugs.jar",
        		"-textui",
        		"-xml",
        		"-bugCategories",
        		"security",
        		directory + AnalysisConfiguration.analysisPathPrefix + command[0],
        		">",
        		directory + AnalysisConfiguration.reportPathPrefix + command[0] + "_report.xml"
        );
		processBuilder.directory(new File(directory));
	}

	@Override
	public void run() {
		init();
		
		try {
			running = true;
			processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			running = false;
		}
	}

	public void setCommand(String command) {
		this.command = command.split(" ");
	}

	public void setCommand(String[] command) {
		this.command = command;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public Boolean isRunning() {
		return running;
	}
}
