package ntut.csie.detect.component;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnalysisFile {
	@JsonIgnore
	private MultipartFile file;
	private String fileName;
	@JsonIgnore
	private String path;
	
	public AnalysisFile(MultipartFile file) {
		this.file = file;
		this.fileName = file.getOriginalFilename();
		this.path = "";
	}
	
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
