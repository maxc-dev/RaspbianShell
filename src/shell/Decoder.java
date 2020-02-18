package shell;

import java.util.ArrayList;

public class Decoder {
	public ArrayList<SpecialCommand> specialCommands = null;
	
	public Decoder(Executioner executioner, History history, ProcessBuilder processBuilder) {
		specialCommands = new ArrayList<>();
		specialCommands.add(new CurrentDirectory(processBuilder));
		specialCommands.add(new HistoryViewAll(history));
		specialCommands.add(new HistoryIndex(history, executioner));
	}
	
	public SpecialCommand isSpecialCommand(String instruction) {
		for (SpecialCommand specialCommand : specialCommands) {
			if (instruction.startsWith(specialCommand.getCommand())) {
				return specialCommand;
			}
		}
		return null;
	}
	
}
