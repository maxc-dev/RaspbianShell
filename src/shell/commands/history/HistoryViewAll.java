package shell.commands.history;

import shell.commands.SpecialCommand;
import shell.structures.History;

public class HistoryViewAll extends SpecialCommand {
	private History history;
	
	public HistoryViewAll(History history) {
		super("!!");
		this.history = history;
	}
	
	@Override
	public void execute(String[] params) {
		System.out.println(history.see());
	}

}
