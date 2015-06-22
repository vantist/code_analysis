package ntut.csie.detect.executor;

import ntut.csie.detect.util.LogUtil;

public class DecoratorExecutor implements Executor {
	private Executor nextExecutor = null;
	private final String logPrefix = "[DecoratorExecutor]";

	@Override
	public void run() {
		next();
	}

	@Override
	public void setCommand(String command) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setCommand(String[] command) {
		// TODO Auto-generated method stub
	}

	@Override
	public void setDirectory(String directory) {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	public void next() {
		if (nextExecutor == null) {
			return;
		}
		
		Thread nextThread = new Thread(nextExecutor);
		nextThread.start();
		
		try {
			nextThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
			LogUtil.log(nextThread.getName() + " 等待出錯", logPrefix);
		}
	}
	
	public void setNext(Executor nextExecutor) {
		this.nextExecutor = nextExecutor;
	}
}
