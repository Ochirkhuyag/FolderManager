package commands;

import java.io.File;

public class DeleteCommand implements Command {
	
	private static DeleteCommand command;
	
	private DeleteCommand() {}

	public static Command getInstance() {
		if(command == null) {
			command = new DeleteCommand();
		}
		return command;
	}

	public void execute(String[] args) {
		if (args.length != 2) {
            print("Usage: delete <folder>");
            return;
        }
        File folder = new File(args[1]);
        deleteFolder(folder);
    }

    private void deleteFolder(File folder) {
    	if (!folder.exists()) {
            print("Folder '" + folder.getPath() + "' does not exist.");
            return;
        }

        if (folder.isDirectory()) {
            File[] contents = folder.listFiles();
            if (contents != null) {
                for (File file : contents) {
                    deleteFolder(file);
                }
            }
        }

        if (!folder.delete()) {
            print("Failed to delete: " + folder.getPath());
        }
	}
}
