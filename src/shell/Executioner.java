package shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Executioner {
	private ProcessBuilder pBuilder = null;
	private History history = null;
	private Decoder decoder = null;
	
	public void startExecution() throws java.io.IOException {
		String commandLine;
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		pBuilder = new ProcessBuilder();
		history = new History();
		decoder = new Decoder(this, history, pBuilder);

		// we break out with <control><C>
		while (true) {
			// read what they entered
			System.out.print("jsh>");
			commandLine = console.readLine();

			// if they entered a return, just loop again
			if (commandLine.equals("")) 
				continue;

			execute(commandLine);
	 	}
	}

	public void execute(String instruction) {
		history.add(instruction);
		String[] params = instruction.split(" ");
		
		SpecialCommand command = decoder.isSpecialCommand(params[0]);
		if (command != null) {
			command.execute(params);
			return;
		}
		
		List<String> commands = new ArrayList<String>();
		for (String param : params) {
			commands.add(param);
		}
		
		try {
			pBuilder.command(commands);
			Process process = pBuilder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			StringBuffer sBuilder = new StringBuffer();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				sBuilder.append(line);
				sBuilder.append(System.getProperty("line.separator"));
			}
			String result = sBuilder.toString();
			System.out.println("I/O:\n" + result);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
