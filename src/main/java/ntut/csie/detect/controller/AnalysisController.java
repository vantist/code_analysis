package ntut.csie.detect.controller;

import javax.servlet.ServletContext;

import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.service.ReportService;
import ntut.csie.detect.service.impl.AnalysisJarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	
	/*
	 * 分析Jar檔
	 */
	@RequestMapping(value="/analysis", method = RequestMethod.POST)
	public String analysis(@RequestPart("file") MultipartFile file) {
		System.out.println(analysisJarService.save(file));
		return "/index";
	}
}
