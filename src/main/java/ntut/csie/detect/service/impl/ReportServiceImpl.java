package ntut.csie.detect.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntut.csie.detect.component.Report;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.service.ReportService;

@Service("ReportService")
public class ReportServiceImpl implements ReportService {
	private String path = "";
	private List<Report> reports = new ArrayList<Report>();
	
	@Autowired
	FileManagerService fileManagerService;
	
	private void updateList() {
		List<File> files = fileManagerService.getFiles(path);
		reports.clear();
		
		for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			reports.add(new Report(file));
		}
	}
	
	@Override
	public Boolean checkReport() {
		Boolean exist = fileManagerService.checkFiles(path);
		
		if (exist)
			updateList();
		
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
	public void setReportPath(String path) {
		this.path = path;
	}

	@Override
	public String getReportPath() {
		return path;
	}

}