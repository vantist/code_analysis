package ntut.csie.detect.util;

import ntut.csie.detect.util.LogUtil;

public class LogUtil {
	private static final boolean enableLog = true;
	private static final String infoLogPrefix = "[info] ";
	private static final String errorLogPrefix = "[error] ";

	public static void log(String msg) {
		if (enableLog) {
			System.out.println(infoLogPrefix + msg);
		}
	}

	public static void log(String msg, String logPrefix) {
		if (enableLog) {
			System.out.println(logPrefix + infoLogPrefix + msg);
		}
	}

	public static void errorLog(String msg) {
		if (enableLog) {
			System.err.println(errorLogPrefix + msg);
		}
	}

	public static void errorLog(String msg, String logPrefix) {
		if (enableLog) {
			System.err.println(logPrefix + errorLogPrefix + msg);
		}
	}
}