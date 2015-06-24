package ntut.csie.detect.executor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import ntut.csie.detect.util.LogUtil;

public class AnalysisExecutor implements Executor {
	private String[] command;
	private String directory;
	private Set<Executor> executors;
	private Set<Thread> executorThreads;
	
	private final String logPrefix = "[AnalysisExecutor]";
	
	public AnalysisExecutor(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}
	
	@Override
	public void init() {
		executors = new HashSet<Executor>();
		
		executors.add(new FindbugsExecutor(command, directory));
		executors.add(new RunPMDExecutor(command, directory));
		
		executorThreads = new HashSet<Thread>();
	}
	
	private void runExecutors() {
		for (Iterator<Executor> iterator = executors.iterator(); iterator.hasNext();) {
			Executor executor = (Executor) iterator.next();
			Thread thread = new Thread(executor);
			thread.start();
			executorThreads.add(thread);
		}
	}
	
	private void waitExecutors() throws InterruptedException {
		for (Iterator<Thread> iterator = executorThreads.iterator(); iterator.hasNext();) {
			Thread thread = (Thread) iterator.next();
			
			thread.join();
		}
	}

	@Override
	public void run() {
		init();
		
		LogUtil.log("掃描執行器開始", logPrefix);
		
		runExecutors();
		
		try {
			waitExecutors();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			LogUtil.log("掃描執行器結束", logPrefix);
		}
	}
	
	@Override
	public void setCommand(String command) {
		this.command = command.split(" ");
	}

	@Override
	public void setCommand(String[] command) {
		this.command = command;
	}

	@Override
	public void setDirectory(String directory) {
		this.directory = directory;
	}
}
