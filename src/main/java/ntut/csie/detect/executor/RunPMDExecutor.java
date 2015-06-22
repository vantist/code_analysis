package ntut.csie.detect.executor;

public class RunPMDExecutor extends DecoratorExecutor {
	private String[] command;
	private String directory;
	private DecoratorExecutor pmdExecutor;
	private DecoratorExecutor decompileExecutor;
	private DecoratorExecutor decompressExecutor;
	
	public RunPMDExecutor() {
	}
	
	public RunPMDExecutor(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}

	public RunPMDExecutor(String[] command, String directory) {
		this.command = command;
		this.directory = directory;
	}

	@Override
	public void run() {
		init();
		
		decompileExecutor.setNext(pmdExecutor);
		decompressExecutor.setNext(decompileExecutor);
		
		Thread decompressThread = new Thread(decompressExecutor);
		
		decompressThread.start();
		
		try {
			decompressThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		super.run();
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

	@Override
	public void init() {
		pmdExecutor = new PMDExecutor(command, directory);
		decompileExecutor = new DecompileExecutor(command, directory);
		decompressExecutor = new DecompressExecutor(command, directory);
	}
}