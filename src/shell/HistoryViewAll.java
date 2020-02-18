package shell;

public class HistoryViewAll extends SpecialCommand {
	private History history = null;
	
	public HistoryViewAll(History history) {
		super("!!");
		this.history = history;
	}
	
	@Override
	public void execute(String[] params) {
		System.out.println(history.see());
	}

}
