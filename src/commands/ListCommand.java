package commands;

import java.io.File;

public class ListCommand implements Command {

    private static ListCommand command;

    private ListCommand() {}

    public static Command getInstance() {
        if (command == null) {
            command = new ListCommand();
        }
        return command;
    }

    public void execute(String[] args) {
    	
        if (args.length != 1) {
        	System.out.println("Usage: list" + args.length);
        	return;
        }

        String path = ".";

        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Directory '" + path + "' does not exist.");
            return;
        }
        printFolder(dir, "");
    }

    private void printFolder(File folder, String preceedingSpace) {
    	
        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
            	System.out.println(preceedingSpace + file.getName());
            	printFolder(file, preceedingSpace + "\t");
            }
        }
    }
}
