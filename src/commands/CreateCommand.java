package commands;

import config.FeatureFlag;
import storage.Folder;

public class CreateCommand implements Command {

    private static CreateCommand command;
    private Folder rootFolder;

    private CreateCommand(Folder rootFolder) {
        this.rootFolder = rootFolder;
    }

    public static Command getInstance(Folder rootFolder) {
        if (command == null) {
            command = new CreateCommand(rootFolder);
        }
        return command;
    }

    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: create <folder>");
            return;
        }
        createInMemoryFolder(args[1]);
    }

    private void createInMemoryFolder(String folderPath) {
        String[] parts = folderPath.split("/");
        Folder current = rootFolder;
        for (String part : parts) {
            current = current.addOrGetSubfolder(part);
        }
        if(FeatureFlag.getPrintDebugMessage()) {
        	System.out.println("Created folder '" + folderPath + "' in memory.");
        }
    }
}
