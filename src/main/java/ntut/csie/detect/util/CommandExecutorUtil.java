package ntut.csie.detect.util;

public interface CommandExecutorUtil extends Runnable {
	Boolean isRunning();
	void setCommand(String command);
	void setCommand(String[] command);
	void setDirectory(String directory);
	void init();
}
