package shell.process;

import java.util.ArrayList;

import shell.commands.dir.CurrentDirectory;
import shell.structures.History;
import shell.commands.history.HistoryIndex;
import shell.commands.history.HistoryViewAll;
import shell.commands.SpecialCommand;

public class Decoder {
	public ArrayList<SpecialCommand> specialCommands;
	
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
