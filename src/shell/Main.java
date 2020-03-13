package shell;

import shell.process.Executioner;

public class Main {
	public static void main(String[] args) throws java.io.IOException {
		Executioner executioner = new Executioner();
		executioner.startExecution();
	}
}
