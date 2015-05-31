package ntut.csie.detect.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import ntut.csie.detect.service.CommandExecutorService;

@Service("CommandExecutorService")
public class CommandExecutorServiceImpl implements CommandExecutorService {
	private String[] command;
	private String directory = "";
	private ProcessBuilder processBuilder;
	private Boolean running = false;
	
	public CommandExecutorServiceImpl() {
	}
	
	public CommandExecutorServiceImpl(String[] command) {
		this.command = command;
	}
	
	public CommandExecutorServiceImpl(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}
	
	public CommandExecutorServiceImpl(String[] command, String directory) {
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

	@Override
	public Boolean isRunning() {
		return running;
	}
}
