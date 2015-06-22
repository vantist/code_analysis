package ntut.csie.detect.executor;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.service.impl.FileManagerServiceImpl;
import ntut.csie.detect.util.LogUtil;

public class DecompileExecutor extends DecoratorExecutor {
	private String[] command;
	private String directory = "";
	private ProcessBuilder processBuilder;
	
	private final String logPrefix = "[DecompileExecutor]";
	
	@Autowired
	FileManagerService fileManagerService;
	
	public DecompileExecutor() {
	}
	
	public DecompileExecutor(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}
	
	public DecompileExecutor(String[] command, String directory) {
		this.command = command;
		this.directory = directory;
	}
	
	public void init() {
		if (fileManagerService == null) {
			fileManagerService = new FileManagerServiceImpl();
		}
		
		processBuilder = new ProcessBuilder(
        		"." + AnalysisConfiguration.jadPathPrefix + "jad",
        		"-d",
        		"." + AnalysisConfiguration.analysisSourcePathPrefix + command[0],
        		"-r",
        		"-o",
        		"-sjava",
        		"." + AnalysisConfiguration.decompressPathPrefix + command[0] + "/**/*.class"
        );
		processBuilder.directory(new File(directory));
	}

	@Override
	public void run() {
		LogUtil.log("反編譯任務 (" + command[0] +") 開始初始", logPrefix);
		init();
		
		try {
			LogUtil.log("反編譯任務 (" + command[0] +") 開始運行", logPrefix);
			
			Process p = processBuilder.start();
			
			fileManagerService.saveFile(
					directory + AnalysisConfiguration.jadErrorPathPrefix + command[0] + ".log",
					p.getErrorStream()
			);
			
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			LogUtil.log("反編譯任務 (" + command[0] +") 運行結束", logPrefix);
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