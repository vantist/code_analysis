package ntut.csie.detect.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ntut.csie.detect.component.Report;
import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.service.ReportService;

@Service("FindbugsReportService")
public class FindbugsReportService implements ReportService {
	private String path = "";
	private List<Report> reports = new ArrayList<Report>();
	
	@Autowired
	FileManagerService fileManagerService;
	
	@Autowired
	public FindbugsReportService(ServletContext context) {
		path = context.getRealPath(AnalysisConfiguration.reportPathPrefix);
	}
	
	private void updateList() {
		List<File> files = fileManagerService.getFiles(path);
		reports.clear();
		
		for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			reports.add(new Report(file));
		}
	}
	
	@Scheduled(fixedDelay = 1000)
	private void scheduledCheck() {
		checkReport();
	}
	
	@Override
	public boolean checkReport() {
		boolean exist = fileManagerService.checkFiles(path);
		
		if (exist)
			updateList();
		else if (!exist && reports.size() > 0)
			reports.clear();
		
		return exist;
	}

	@Override
	public List<Report> getReports() {
		return reports;
	}

	@Override
	public Report getReport(int index) {
		return reports.get(index);
	}

	@Override
	public String getReportPath() {
		return path;
	}
}