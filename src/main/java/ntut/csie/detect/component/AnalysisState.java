package ntut.csie.detect.component;

import java.util.List;

import ntut.csie.detect.report.component.Report;

import org.springframework.stereotype.Component;

@Component
public class AnalysisState {
	private List<Report> reports;
	private List<AnalysisFile> runningJobs;
	private List<AnalysisFile> waitingJobs;
	
	
	public AnalysisState() {
	}
	
	public AnalysisState(List<AnalysisFile> runningJobs, List<AnalysisFile> watingJobs, List<Report> reports) {
		this.runningJobs = runningJobs;
		this.waitingJobs = watingJobs;
		this.reports = reports;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public List<AnalysisFile> getRunningJobs() {
		return runningJobs;
	}

	public void setRunningJobs(List<AnalysisFile> runningJobs) {
		this.runningJobs = runningJobs;
	}

	public List<AnalysisFile> getWaitingJobs() {
		return waitingJobs;
	}

	public void setWaitingJobs(List<AnalysisFile> waitingJobs) {
		this.waitingJobs = waitingJobs;
	}
}
