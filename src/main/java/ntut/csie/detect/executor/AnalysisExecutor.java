package ntut.csie.detect.executor;

public class AnalysisExecutor extends DecoratorExecutor {
	private String[] command;
	private String directory;
	private DecoratorExecutor findbugsExecutor;
	private DecoratorExecutor runPMDExecutor;
	
	public AnalysisExecutor(String command, String directory) {
		this.command = command.split(" ");
		this.directory = directory;
	}

	@Override
	public void run() {
		init();
		
		runPMDExecutor.setNext(findbugsExecutor);
		setNext(runPMDExecutor);
		
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
		findbugsExecutor = new FindbugsExecutor(command, directory);
		runPMDExecutor = new RunPMDExecutor(command, directory);
	}
}
