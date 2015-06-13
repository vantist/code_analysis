package ntut.csie.detect.util.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.service.impl.FileManagerServiceImpl;
import ntut.csie.detect.util.CommandExecutorUtil;

public class FindbugsExecutor implements CommandExecutorUtil {
	private String[] command;
	private String directory = "";
	private ProcessBuilder processBuilder;
	
	@Autowired
	FileManagerService fileManagerService;
	
	public FindbugsExecutor() {
	}
	
	public FindbugsExecutor(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}
	
	public void init() {
		if (fileManagerService == null)
			fileManagerService = new FileManagerServiceImpl();
		
		processBuilder = new ProcessBuilder(
				"java", 
				"-Xmx2048m",
        		"-jar",
        		"." + AnalysisConfiguration.findbugsPathPrefix + "findbugs.jar",
        		"-textui",
        		"-html",
//        		"-bugCategories",
//        		"security",
        		"." + AnalysisConfiguration.analysisPathPrefix + command[0]
        );
		processBuilder.directory(new File(directory));
	}

	@Override
	public void run() {
		System.out.println("掃描任務開始初始");
		init();
		
		try {
			System.out.println("掃描任務開始運行");
			
			Process p = processBuilder.start();
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(p.getErrorStream(), writer, "UTF-8");
			System.out.println("錯誤訊息：\n" + writer.toString());
			
			fileManagerService.saveFile(
					directory + AnalysisConfiguration.reportPathPrefix + command[0] + ".html",
					p.getInputStream()
			);
		} catch (IOException e) {
			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
		} finally {
			System.out.println("掃描任務運行結束");

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
}