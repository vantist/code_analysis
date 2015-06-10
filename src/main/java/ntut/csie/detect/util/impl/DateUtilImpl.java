package ntut.csie.detect.util.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import ntut.csie.detect.util.DateUtil;

public class DateUtilImpl implements DateUtil {
	//設定日期格式
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private final SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmssSSS");

	@Override
	public String getDate() {
		return dateFormat.format(new Date());
	}

	@Override
	public String getTime() {
		return timeFormat.format(new Date());
	}

}
