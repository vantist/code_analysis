package ntut.csie.detect.service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileManagerService {
	void saveFile(String path, InputStream fileInputStream) throws IOException;
	
	String readFile(String fileName) throws IOException;
	
	// 確認資料夾下是否有檔案存在
	boolean checkFiles(String path, FilenameFilter filenameFilter);
	
	boolean removeFile(String path);
	
	void unZip(String path, String filePath);

	// 取得資料夾下所有檔案資訊	
	List<File> getFiles(String path, FilenameFilter filenameFilter);
}