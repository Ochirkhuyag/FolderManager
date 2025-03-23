package main;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import commands.*;

public class FolderManager {
	private static final Map<String, Command> commandMap = new HashMap<>();

    public static void main(String[] args) {
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
        commandMap.put("create", CreateCommand.getInstance());
        commandMap.put("delete", DeleteCommand.getInstance());
        commandMap.put("move", MoveCommand.getInstance());
        commandMap.put("list", ListCommand.getInstance());
    }
}
