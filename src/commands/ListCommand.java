package commands;

import storage.Folder;

public class ListCommand implements Command {

    private static ListCommand command;
    private Folder rootFolder;

    private ListCommand(Folder rootFolder) {
        this.rootFolder = rootFolder;
    }

    public static Command getInstance(Folder rootFolder) {
        if (command == null) {
            command = new ListCommand(rootFolder);
        }
        return command;
    }

    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: list");
            return;
        }
        printFolders(rootFolder, "");
    }

    private void printFolders(Folder folder, String indent) {
        for (Folder sub : folder.getSubfolders()) {
            System.out.println(indent + sub.getName());
            printFolders(sub, indent + "  ");
        }
    }
}
