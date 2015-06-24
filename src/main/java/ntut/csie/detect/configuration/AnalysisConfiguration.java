package ntut.csie.detect.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ntut.csie.detect.report.FindBugsReportService;
import ntut.csie.detect.report.ReportService;
import ntut.csie.detect.service.AnalysisService;
import ntut.csie.detect.service.impl.AnalysisJarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/*
 * 啟用 spring 排程
 */
@Configuration
@EnableAsync
@EnableScheduling
public class AnalysisConfiguration implements SchedulingConfigurer {
	public static final String webinfPathPrefix = "/WEB-INF/";
	public static final String analysisPathPrefix = webinfPathPrefix + "analysis_files/";
	public static final String analysisSourcePathPrefix = webinfPathPrefix + "analysis_source/";
	public static final String findbugsReportPathPrefix = webinfPathPrefix + "findbugs_report/";
	public static final String pmdReportPathPrefix = webinfPathPrefix + "pmd_report/";
	public static final String findbugsErrorPathPrefix = webinfPathPrefix + "findbugs_error/";
	public static final String pmdErrorPathPrefix = webinfPathPrefix + "pmd_error/";
	public static final String jadErrorPathPrefix = webinfPathPrefix + "jad_error/";
	public static final String tarErrorPathPrefix = webinfPathPrefix + "tar_error/";
	public static final String findbugsPathPrefix = webinfPathPrefix + "findbugs/lib/";
	public static final String pmdPathPrefix = webinfPathPrefix + "pmd/bin/";
	public static final String decompressPathPrefix = webinfPathPrefix + "tmp/";
	public static final String jadPathPrefix = webinfPathPrefix + "jad/";
	
	public AnalysisConfiguration() {
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
	    taskRegistrar.setScheduler(taskExecutor());
	}
	
	@Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}