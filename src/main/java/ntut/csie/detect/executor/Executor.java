package ntut.csie.detect.executor;

public interface Executor extends Runnable {
	void setCommand(String command);
	void setCommand(String[] command);
	void setDirectory(String directory);
	void init();
}
