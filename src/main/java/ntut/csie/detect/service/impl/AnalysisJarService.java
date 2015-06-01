package ntut.csie.detect.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ntut.csie.detect.component.AnalysisFile;
import ntut.csie.detect.service.AnalysisService;
import ntut.csie.detect.service.FileManagerService;

@Service("AnalysisJarService")
public class AnalysisJarService implements AnalysisService {
	private List<AnalysisFile> files = new ArrayList<AnalysisFile>();
	private final String pathPrefix = "/WEB-INF/analysis/";
	private String path;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	@Autowired
	public AnalysisJarService(ServletContext context) {
		path = context.getRealPath(pathPrefix);
	}
	
	@Override
	public Boolean check(AnalysisFile file) {
		files.add(new AnalysisFile());
		return null;
	}

	@Override
	@Scheduled(initialDelay = 1000, fixedDelay = 5000)
	public void analysis() {
		System.out.println(files.size());
		System.out.println(System.currentTimeMillis());
	}

	@Override
	public void save(MultipartFile file) {
		try {
			fileManagerService.saveFile(path.concat(File.separator).concat(file.getOriginalFilename()), file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
