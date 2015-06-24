package ntut.csie.detect.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ntut.csie.detect.report.component.Report;
import ntut.csie.detect.service.FileManagerService;

@Service("AnalysisReportService")
public class AnalysisReportService implements ReportService {
	private Set<ReportService> reportServices;
	private List<Report> reports;
	
	@Autowired
	public AnalysisReportService(ServletContext context, FileManagerService fileManagerService) {
		reports = new ArrayList<Report>();
		reportServices = new HashSet<ReportService>();
		
		reportServices.add(new FindBugsReportService(context, fileManagerService));
		reportServices.add(new PMDReportService(context, fileManagerService));
	}

	@Override
	public boolean checkReport() {
		boolean checked = false;
		
		for (Iterator<ReportService> iterator = reportServices.iterator(); iterator.hasNext();) {
			ReportService reportService = (ReportService) iterator.next();
			checked = reportService.checkReport();
		}
		
		return checked;
	}
	
	@Scheduled(fixedDelay = 1000)
	private void scheduledCheck() {
		checkReport();
	}

	@Override
	public List<Report> getReports() {
		reports.clear();
		
		for (Iterator<ReportService> iterator = reportServices.iterator(); iterator.hasNext();) {
			ReportService reportService = (ReportService) iterator.next();
			reports.addAll(reportService.getReports());
		}
		
		return reports;
	}

	@Override
	public Report getReport(int index) {
		return reports.get(index);
	}
}
