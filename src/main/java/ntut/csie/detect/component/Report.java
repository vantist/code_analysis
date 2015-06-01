package ntut.csie.detect.component;

import java.io.File;

public class Report {
	private File file;
	
	public Report(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}