package shell.commands.history;

import shell.commands.SpecialCommand;
import shell.process.Executioner;
import shell.structures.History;

public class HistoryIndex extends SpecialCommand {
	private History history;
	private Executioner executioner;
	
	public HistoryIndex(History history, Executioner executioner) {
		super("!");
		this.history = history;
		this.executioner = executioner;
	}

	@Override
	public void execute(String[] params) {
		if (params[0].length() <= 1) {
			super.throwInvalidParams();
			return;
		}
		String strIndex = params[0].substring(1);
		int index = Integer.parseInt(strIndex);
		String command = this.history.getCommandAt(index);
		if (command != null) {
			if (!command.startsWith(super.getCommand())) {
				executioner.execute(command);
			}
		}
	}

}
