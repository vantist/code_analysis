package ntut.csie.detect.service;

import org.springframework.web.multipart.MultipartFile;

import ntut.csie.detect.component.AnalysisFile;

public interface AnalysisService {
	Boolean check(AnalysisFile file);
	void analysis();
	String save(MultipartFile file);
}