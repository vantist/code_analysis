package ntut.csie.detect.report;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import ntut.csie.detect.configuration.AnalysisConfiguration;
import ntut.csie.detect.report.component.Report;
import ntut.csie.detect.service.FileManagerService;

public class FindBugsReportService implements ReportService {
	private String path = "";
	private List<Report> reports = new ArrayList<Report>();
	private FilenameFilter filenameFilter;
	private FileManagerService fileManagerService;
	
	public FindBugsReportService(ServletContext context, FileManagerService fileManagerService) {
		this.fileManagerService = fileManagerService;
		path = context.getRealPath(AnalysisConfiguration.findbugsReportPathPrefix);
		filenameFilter = new FindbugsFilenameFilter();
	}
	
	private void updateList() {
		List<File> files = fileManagerService.getFiles(path, filenameFilter);
		reports.clear();
		
		for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			reports.add(new Report(file));
		}
	}
	
	@Override
	public boolean checkReport() {
		boolean exist = fileManagerService.checkFiles(path, filenameFilter);
		
		if (exist) {
			updateList();
		} else if (!exist && reports.size() > 0) {
			reports.clear();
		}
		
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
}

/*
 * 實做附檔名篩選器
 */
class FindbugsFilenameFilter implements FilenameFilter {
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".xml")
			|| name.endsWith(".html");
	}
}