package ntut.csie.detect.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Service;

import ntut.csie.detect.service.FileManagerService;

@Service("FileManagerService")
public class FileManagerServiceImpl implements FileManagerService {
	public void saveFile(String folder, String fileName, InputStream fileInputStream) throws IOException {
		saveFile(folder.concat(File.separator).concat(fileName), fileInputStream);
	}

	@Override
	public void saveFile(String path, InputStream fileInputStream) throws IOException {
		OutputStream out = new FileOutputStream(new File(path));
		
        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = fileInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        
        out.close();
	}

	@Override
	public String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}

	@Override
	public Boolean checkFiles(String path) {
		File folder = new File(path);
		
		if (folder.isDirectory() && folder.list().length > 0)
			return true;
		
		return false;
	}

	@Override
	public List<File> getFiles(String path) {
		File folder = new File(path);
		List<File> files = new ArrayList<File>();
		
		addFolderFiles(folder, files);
		
		return files;
	}
	
	private void addFolderFiles(File file, List<File> fileList) {
		File childFile;
		
		try	{	
			for (int i = 0; i < file.listFiles().length; i++) {
				childFile = file.listFiles()[i];
				
				if (childFile.isHidden())
					continue;
				
				if (childFile.isDirectory()) {
					addFolderFiles(childFile, fileList);
				} else {
					fileList.add(childFile);
				}
			}
		} catch (Exception e) {
			return;
		}
	}
}
