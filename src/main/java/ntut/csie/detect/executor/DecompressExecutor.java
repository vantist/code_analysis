package ntut.csie.detect.executor;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.service.impl.FileManagerServiceImpl;
import ntut.csie.detect.util.LogUtil;

public class DecompressExecutor extends DecoratorExecutor {
	private String[] command;
	private String directory = "";
	private ProcessBuilder processBuilder;
	
	private final String logPrefix = "[DecompressExecutor]";
	
	@Autowired
	FileManagerService fileManagerService;
	
	public DecompressExecutor(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}
	
	public DecompressExecutor(String[] command, String directory) {
		this.command = command;
		this.directory = directory;
	}
	
	public void init() {
		if (fileManagerService == null) {
			fileManagerService = new FileManagerServiceImpl();
		}
		
		//	mkdir ./ant | tar -C ./ant -zxvf ant.jar	
		processBuilder = new ProcessBuilder(
        		"tar",
        		"-C",
        		"." + AnalysisConfiguration.decompressPathPrefix + command[0],
        		"-zxvf",
        		"." + AnalysisConfiguration.analysisPathPrefix + command[0]
        );
		
		File folder = new File(directory + AnalysisConfiguration.decompressPathPrefix + command[0]);
		folder.mkdirs();
		processBuilder.directory(new File(directory));
	}

	@Override
	public void run() {
		LogUtil.log("解壓縮任務 (" + command[0] +") 開始初始", logPrefix);
		init();

		try {
			LogUtil.log("解壓縮任務 (" + command[0] +") 開始運行", logPrefix);
			
			Process p = processBuilder.start();
			
			LogUtil.log("解壓縮任務指令 ：" + processBuilder.command().toString(), logPrefix);
			
			fileManagerService.saveFile(
					directory + AnalysisConfiguration.tarErrorPathPrefix + command[0] + ".log",
					p.getErrorStream()
			);
			
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LogUtil.log("解壓縮任務 (" + command[0] +") 運行結束", logPrefix);
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