package ntut.csie.detect.controller;

import java.io.IOException;

import javax.servlet.ServletContext;

import ntut.csie.detect.component.AnalysisFile;
import ntut.csie.detect.component.AnalysisState;
import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.service.ReportService;
import ntut.csie.detect.service.impl.AnalysisJarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AnalysisController {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private AnalysisJarService analysisJarService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	/*
	 * 分析Jar檔
	 */
	@RequestMapping(value = "/analysis", method = RequestMethod.POST)
	public String analysis(@RequestPart("file") MultipartFile file) {
		if (!file.isEmpty()) {
			AnalysisFile analysisFile = new AnalysisFile(file);
			analysisJarService.save(analysisFile);
		}
		return "redirect:/";
	}
	
	/*
	 * 取得分析資訊
	 */
	@RequestMapping(value = "/analysis/states", method = RequestMethod.GET)
	@ResponseBody
	public AnalysisState analysisRunningJobs() {
		return new AnalysisState(analysisJarService.getRunningJobs(), analysisJarService.getWaitingJobs(), reportService.getReports());
	}
	
	/*
	 * 取得分析資訊
	 */
	@RequestMapping(value = "/report/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String getReport(@PathVariable("id") int id) {
		
		
		
		try {
			return fileManagerService.readFile(reportService.getReport(id).getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
}