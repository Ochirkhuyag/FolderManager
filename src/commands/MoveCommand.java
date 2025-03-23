package commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MoveCommand implements Command {

	private static MoveCommand command;
	
	private MoveCommand() {}

	public static Command getInstance() {
		if(command == null) {
			command = new MoveCommand();
		}
		return command;
	}
	
	public void execute(String[] args) {
		if(args.length != 3) {
			print("Usage: move <source folder> <desitination folder>");
		}
		moveFolder(args[1], args[2]);
	}
	
	private void moveFolder(String src, String dest) {
        File sourceFolder = new File(src);
        File destFolder = new File(dest);

        if (!sourceFolder.exists()) {
            print("Source folder '" + src + "' does not exist.");
            return;
        }

        if (destFolder.exists()) {
            print("Destination folder '" + dest + "' already exists.");
            return;
        }

        try {
            Files.move(sourceFolder.toPath(), destFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            print("Failed to move folder: " + e.getMessage());
        }
    }

}
