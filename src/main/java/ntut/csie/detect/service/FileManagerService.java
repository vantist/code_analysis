package ntut.csie.detect.service;

import java.io.IOException;
import java.io.InputStream;

public interface FileManagerService {
	void saveFile(String path, InputStream fileInputStream) throws IOException;
	String readFile(String fileName) throws IOException;
}