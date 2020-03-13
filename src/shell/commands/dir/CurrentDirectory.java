package shell.commands.dir;

import java.io.File;

import shell.commands.SpecialCommand;


public class CurrentDirectory extends SpecialCommand {
	private static final String USER_DIRECTORY = "user.dir";
	private ProcessBuilder processBuilder;
	
	public CurrentDirectory(ProcessBuilder processBuilder) {
		super("cd");
		this.processBuilder = processBuilder;
	}

	@Override
	public void execute(String[] params) {
		if (params.length == 1) {
			processBuilder.directory(new File(System.getProperty(USER_DIRECTORY)));
		} else if (params.length == 2) {
			processBuilder.directory(new File(params[1]));
		} else {
			super.throwInvalidParams();
		}
	}

}
