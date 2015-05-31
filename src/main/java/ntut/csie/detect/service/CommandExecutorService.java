package ntut.csie.detect.service;

public interface CommandExecutorService extends Runnable {
	void setCommand(String command);
	void setCommand(String[] command);
	void setDirectory(String directory);
	Boolean isRunning();
}