package shell.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shell.structures.History;
import shell.commands.SpecialCommand;

public class Executioner {
	private static final String SHELL_INPUT = "-> ";
	private static final String REGEX_COMMAND_SPACE = " ";
	private static final String SHELL_IO_OUTPUT = "I/O-> \n";

	private ProcessBuilder pBuilder = null;
	private History history = null;
	private Decoder decoder = null;
	
	public void startExecution() throws java.io.IOException {
		String commandLine;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		pBuilder = new ProcessBuilder();
		history = new History();
		decoder = new Decoder(this, history, pBuilder);

		do {
			//collect input
			System.out.print(SHELL_INPUT);
			commandLine = console.readLine().trim();

			//basic validation
			if (commandLine.isBlank())
				continue;

			execute(commandLine);
		} while (true);
	}

	public void execute(String instruction) {
		history.add(instruction);
		String[] params = instruction.split(REGEX_COMMAND_SPACE);
		
		SpecialCommand command = decoder.isSpecialCommand(params[0]);
		if (command != null) {
			command.execute(params);
			return;
		}
		
		List<String> commands = new ArrayList<>();
		Collections.addAll(commands, params);
		
		try {
			pBuilder.command(commands);
			Process process = pBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			StringBuilder sBuilder = new StringBuilder();
			String line;
			
			while ((line = reader.readLine()) != null) {
				sBuilder.append(line);
				sBuilder.append(System.getProperty("line.separator"));
			}
			String result = sBuilder.toString();
			System.out.println(SHELL_IO_OUTPUT + result);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
