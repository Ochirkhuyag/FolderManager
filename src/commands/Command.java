package commands;

public interface Command {
	void execute(String[] args);
	default void print(String message) {
		System.out.println(message + "\n");
	}
}
