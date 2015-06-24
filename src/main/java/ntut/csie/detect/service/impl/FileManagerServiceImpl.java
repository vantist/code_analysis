package ntut.csie.detect.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;

import ntut.csie.detect.service.FileManagerService;
import ntut.csie.detect.util.LogUtil;

@Service("FileManagerService")
public class FileManagerServiceImpl implements FileManagerService {
//	private final String logPrefix = "[FileManagerService]";	
	
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
	            sb.append('\n');
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}

	@Override
	public boolean checkFiles(String path, FilenameFilter filenameFilter) {
		File folder = new File(path);
		
		if (folder.isDirectory() && folder.list(filenameFilter).length > 0) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<File> getFiles(String path, FilenameFilter filenameFilter) {
		File folder = new File(path);
		List<File> files = new ArrayList<File>();
		
		addFolderFiles(folder, files, filenameFilter);
		
		return files;
	}
	
	private void addFolderFiles(File file, List<File> fileList, FilenameFilter filenameFilter) {
		File childFile;
		
		try	{	
			for (int i = 0; i < file.listFiles(filenameFilter).length; i++) {
				childFile = file.listFiles(filenameFilter)[i];
				
				if (childFile.isHidden()) {
					continue;
				}
				
				if (childFile.isDirectory()) {
					addFolderFiles(childFile, fileList, filenameFilter);
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

	@Override
	public void unZip(String path, String filePath) {
		File directory = new File(path);
        
		// if the output directory doesn't exist, create it
		if(!directory.exists()) 
			directory.mkdirs();

		// buffer for read and write data to file
		byte[] buffer = new byte[2048];
        
		try {
			FileInputStream fInput = new FileInputStream(filePath);
			ZipInputStream zipInput = new ZipInputStream(fInput);
            
			ZipEntry entry = zipInput.getNextEntry();
            
			while (entry != null) {
				String entryName = entry.getName();
				File file = new File(path + File.separator + entryName);
                
				System.out.println("Unzip file " + entryName + " to " + file.getAbsolutePath());
                
				// create the directories of the zip directory
				if (entry.isDirectory()) {
					File newDir = new File(file.getAbsolutePath());
					if (!newDir.exists()) {
						boolean success = newDir.mkdirs();
						if (success == false) {
							System.out.println("Problem creating Folder");
						}
					}
                }
				else {
					FileOutputStream fOutput = new FileOutputStream(file);
					int count = 0;
					while ((count = zipInput.read(buffer)) > 0) {
						// write 'count' bytes to the file output stream
						fOutput.write(buffer, 0, count);
					}
					fOutput.close();
				}
				// close ZipEntry and take the next one
				zipInput.closeEntry();
				entry = zipInput.getNextEntry();
			}
            
			// close the last ZipEntry
			zipInput.closeEntry();
            
			zipInput.close();
			fInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
