package main;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import commands.*;
import config.FeatureFlag;
import storage.Folder;

public class FolderManager {
	private static final Map<String, Command> commandMap = new HashMap<>();
	private static final Folder root = new Folder("");

    public static void main(String[] args) {
    	System.out.println("Folder Manager v1.0 - Endpoint coding challenge by Ochi Samand");
    	
    	boolean debugMode = args.length > 0 && args[0].equalsIgnoreCase("-debug");

        setConfig(debugMode);
        registerCommands();
        
        System.out.println("Folder Manager v1.0 - Endpoint coding challenge by Ochi Samand");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Commands: create <folder>, delete <folder>, move <src> <dest>, exit\n");

        while (true) {
            System.out.print("");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }
            
            if(input.equals("")) {
            	continue;
            }

            String[] parts = input.split("\\s+");
            if (parts.length == 0) continue;

            Command cmd = commandMap.get(parts[0].toLowerCase());
            if (cmd != null) {
                cmd.execute(parts);
            } else {
                System.out.println("Unsupported command.");
            }
        }
        scanner.close();
    }

    private static void registerCommands() {
        commandMap.put("create", CreateCommand.getInstance(root));
        commandMap.put("delete", DeleteCommand.getInstance(root));
        commandMap.put("move", MoveCommand.getInstance(root));
        commandMap.put("list", ListCommand.getInstance(root));
    }
    
    private static void setConfig(boolean showDebugFlag) {
    	FeatureFlag.setPrintDebugMessage(showDebugFlag);
    }
}
