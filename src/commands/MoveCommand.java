package commands;

import config.FeatureFlag;
import storage.Folder;

public class MoveCommand implements Command {

    private static MoveCommand command;
    private Folder rootFolder;

    private MoveCommand(Folder rootFolder) {
        this.rootFolder = rootFolder;
    }

    public static Command getInstance(Folder rootFolder) {
        if (command == null) {
            command = new MoveCommand(rootFolder);
        }
        return command;
    }

    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: move <source-folder> <destination-folder>");
            return;
        }
        moveFolder(args[1], args[2]);
    }

    private void moveFolder(String srcPath, String destPath) {
        Folder parentOfSrc = findParentFolder(srcPath);
        String srcName = getLastPart(srcPath);

        if (parentOfSrc == null || !parentOfSrc.hasSubfolder(srcName)) {
            System.out.println("Source folder '" + srcPath + "' does not exist.");
            return;
        }

        Folder srcFolder = parentOfSrc.getSubfolder(srcName);

        Folder destFolder = findOrCreateFolder(destPath);
        if (destFolder.hasSubfolder(srcName)) {
            System.out.println("Destination folder '" + destPath + "' already has a subfolder '" + srcName + "'.");
            return;
        }

        parentOfSrc.removeSubfolder(srcName);
        destFolder.addSubfolder(srcFolder);
        
        if(FeatureFlag.getPrintDebugMessage()) {
        	System.out.println("Moved '" + srcPath + "' to '" + destPath + "'.");
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

    private Folder findOrCreateFolder(String path) {
        String[] parts = path.split("/");
        Folder current = rootFolder;
        for (String part : parts) {
            current = current.addOrGetSubfolder(part);
        }
        return current;
    }
}
