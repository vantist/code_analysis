package ntut.csie.detect.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ntut.csie.detect.component.Report;
import ntut.csie.detect.service.ReportService;

@Service("ReportService")
public class ReportServiceImpl implements ReportService {
	private List<Report> reports = new ArrayList<Report>();

	@Override
	public Boolean checkReport() {
		// TODO Auto-generated method stub
		return null;
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
