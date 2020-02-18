package shell;

public class HistoryIndex extends SpecialCommand {
	private History history = null;
	private Executioner executioner = null;
	
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
		String strIndex = params[0].substring(1, params[0].length());
		int index = Integer.parseInt(strIndex);
		System.out.println("debug: " + index);
		String command = this.history.getCommandAt(index);
		System.out.println("debug: " + command);
		if (command != null) {
			if (!command.startsWith(super.getCommand())) {
				executioner.execute(command);
			}
		}
	}

}
