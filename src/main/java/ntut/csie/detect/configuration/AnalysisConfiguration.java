package ntut.csie.detect.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ntut.csie.detect.service.AnalysisService;
import ntut.csie.detect.service.ReportService;
import ntut.csie.detect.service.impl.AnalysisJarService;
import ntut.csie.detect.service.impl.FindbugsReportService;

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
	public static final String analysisPathPrefix = "/WEB-INF/analysis/";
	public static final String reportPathPrefix = "/WEB-INF/report/";
	public static final String errorPathPrefix = "/WEB-INF/error/";
	public static final String findbugsPathPrefix = "/WEB-INF/findbugs/lib/";
	public static final String webinfPathPrefix = "/WEB-INF/";
	
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