package ntut.csie.detect.service;

import java.util.List;

import ntut.csie.detect.component.Report;

public interface ReportService {
	String getReportPath();	
	
	// 檢查是否有報表
	boolean checkReport();
	
	// 取得報表列表	
	List<Report> getReports();
	
	// 取得該篇報表
	Report getReport(int index);
}