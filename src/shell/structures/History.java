package shell.structures;

import java.util.ArrayList;

public class History extends ArrayList<String> {
	private static final long serialVersionUID = 1L;

	public String see() {
		int index = size();
		if (index == 0) {
			return null;
		}
		StringBuilder history = new StringBuilder();
		for (String item : this) {
			history.append(index-1);
			history.append(" ");
			history.append(item);
			history.append("\n");
			index--;
		}
		return history.toString();
	}
	
	public String getCommandAt(int index) {
		int size = size()-1;
		if (index >= size || index < 0) {
			System.out.println("Invalid history index.");
			return null;
		}
		return get(size-index-1);
	}
}
