package ntut.csie.detect.service;

import java.util.List;

import ntut.csie.detect.component.AnalysisFile;

public interface AnalysisService {
	List<AnalysisFile> getRunningJobs();
	List<AnalysisFile> getWaitingJobs();
	int checkRunningJobs();
	int checkWaitingJobs();
	void analysis();
	String save(AnalysisFile file);
}