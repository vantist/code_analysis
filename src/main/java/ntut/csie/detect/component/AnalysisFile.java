package ntut.csie.detect.component;

public class AnalysisFile {
	private String fileName;
	private String path;
	
	public AnalysisFile(String path) {
		this.path = path;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
