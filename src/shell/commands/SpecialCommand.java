package shell.commands;

import java.io.IOException;

public abstract class SpecialCommand {
	private static final String ERROR_PARAM = "Invalid parameters for command: [%s] Try <cmd>-help for help with a special command.";
	
	private String command;
	
	public SpecialCommand(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return command;
	}
	
	protected void throwInvalidParams() {
		try {
			throw new IOException(String.format(ERROR_PARAM, this.command));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public abstract void execute(String[] params);
}
