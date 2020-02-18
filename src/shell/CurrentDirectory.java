package shell;

import java.io.File;


public class CurrentDirectory extends SpecialCommand {
	private ProcessBuilder processBuilder = null;
	
	public CurrentDirectory(ProcessBuilder processBuilder) {
		super("cd");
		this.processBuilder = processBuilder;
	}

	@Override
	public void execute(String[] params) {
		if (params.length == 1) {
			processBuilder.directory(new File(System.getProperty("user.dir")));
		} else if (params.length == 2) {
			processBuilder.directory(new File(params[1]));
		} else {
			super.throwInvalidParams();
		}
	}

}
