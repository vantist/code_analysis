package ntut.csie.detect.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/*
 * 啟用 spring 排程
 */
@Configuration
@EnableScheduling
public class AnalysisConfiguration {
	public static final String analysisPathPrefix = "/WEB-INF/analysis/";
	public static final String reportPathPrefix = "/WEB-INF/report/";
	public static final String findbugsPathPrefix = "/WEB-INF/findbugs/lib/";
	public static final String webinfPathPrefix = "/WEB-INF/";

}