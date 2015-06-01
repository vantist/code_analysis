package ntut.csie.detect.util;

import java.io.File;
import java.io.IOException;

public class CommandExecutorUtil implements Runnable {
	private String[] command;
	private String directory = "";
	private ProcessBuilder processBuilder;
	private Boolean running = false;
	
	public CommandExecutorUtil() {
	}
	
	public CommandExecutorUtil(String[] command) {
		this.command = command;
	}
	
	public CommandExecutorUtil(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}
	
	public CommandExecutorUtil(String[] command, String directory) {
		this.command = command;
		this.directory = directory;
	}
	
	private void initProcessBuilder() {
		processBuilder = new ProcessBuilder(command);
		processBuilder.directory(new File(directory));
	}

	@Override
	public void run() {
		initProcessBuilder();
		
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
