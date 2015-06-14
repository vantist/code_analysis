package ntut.csie.detect.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.util.LogUtil;

@Service("FileManagerService")
public class FileManagerServiceImpl implements FileManagerService {
	private final String logPrefix = "[FileManagerService]";	
	
	public void saveFile(String folder, String fileName, InputStream fileInputStream) throws IOException {
		saveFile(folder.concat(File.separator).concat(fileName), fileInputStream);
	}

	@Override
	public void saveFile(String path, InputStream fileInputStream) throws IOException {
		AsyncSaveFile asyncSaveFile = new AsyncSaveFile(path, fileInputStream);
		Thread thread = new Thread(asyncSaveFile);
		thread.start();
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
	public boolean checkFiles(String path) {
		File folder = new File(path);
		
		if (folder.isDirectory() && folder.list(new AnalysisFileFilter()).length > 0)
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
		AnalysisFileFilter analysisFileFilter = new AnalysisFileFilter();
		
		try	{	
			for (int i = 0; i < file.listFiles(analysisFileFilter).length; i++) {
				childFile = file.listFiles(analysisFileFilter)[i];
				
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

	@Override
	public boolean removeFile(String path) {
		File file = new File(path);
		return file.delete();
	}
}

/*
 * 實做附檔名篩選器
 */
class AnalysisFileFilter implements FilenameFilter {
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".jar")
			|| name.endsWith(".xml")
			|| name.endsWith(".war")
			|| name.endsWith(".zip")
			|| name.endsWith(".html");
	}
}

/*
 * 實做異步存檔
 */
class AsyncSaveFile implements Runnable {
	private String path;
	private InputStream fileInputStream;
	private final String logPrefix = "[FileManagerService/AsyncSaveFile]";
	
	public AsyncSaveFile(String path, InputStream fileInputStream) {
		this.path = path;
		this.fileInputStream = fileInputStream;
	}

	@Override
	public void run() {
		try {
			OutputStream out;
			
	        int read = 0;
	        final byte[] bytes = new byte[1024];
	        
	        read = fileInputStream.read(bytes);
	        out = new FileOutputStream(new File(path));

	        while (read != -1) {
	            out.write(bytes, 0, read);
	            read = fileInputStream.read(bytes);
	        }
	        
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			LogUtil.log(path + " 輸出成功", logPrefix);
		}
	}
}
