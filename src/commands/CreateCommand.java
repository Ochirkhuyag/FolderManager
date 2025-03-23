package commands;

import java.io.File;

public class CreateCommand implements Command {
	
	private static CreateCommand command;
	
	private CreateCommand() {}
	
	public static Command getInstance() {
		if(command == null) {
			command = new CreateCommand();
		}
		return command;
	}

	public void execute(String[] args) {
		if (args.length != 2) {
            print("Usage: create <folder>");
            return;
        }
        createFolder(args[1]);
	}
	
	private void createFolder(String folderName) {
        File folder = new File(folderName);
        if (folder.exists()) {
        	print("Folder '" + folderName + "' already exists.");
        } else if (!folder.mkdirs()) {
        	print("Failed to create folder '" + folderName + "'.");
        }
    }
}
