package commands;

import config.FeatureFlag;
import storage.Folder;

public class DeleteCommand implements Command {
    
    private static DeleteCommand command;
    private Folder rootFolder;

    private DeleteCommand(Folder rootFolder) {
        this.rootFolder = rootFolder;
    }

    public static Command getInstance(Folder rootFolder) {
        if (command == null) {
            command = new DeleteCommand(rootFolder);
        }
        return command;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: delete <folder>");
            return;
        }
        deleteInMemoryFolder(args[1]);
    }

    private void deleteInMemoryFolder(String folderPath) {
        Folder parent = findParentFolder(folderPath);
        String folderName = getLastPart(folderPath);

        if (parent == null || !parent.hasSubfolder(folderName)) {
            System.out.println("Folder '" + folderPath + "' does not exist.");
            return;
        }

        parent.removeSubfolder(folderName);
        if(FeatureFlag.getPrintDebugMessage()) {
        	System.out.println("Deleted '" + folderPath + "' from memory.");
        }
    }

    private Folder findParentFolder(String fullPath) {
        String[] parts = fullPath.split("/");
        Folder current = rootFolder;
        for (int i = 0; i < parts.length - 1; i++) {
            current = current.getSubfolder(parts[i]);
            if (current == null) return null;
        }
        return current;
    }

    private String getLastPart(String path) {
        String[] parts = path.split("/");
        return parts[parts.length - 1];
    }
}
