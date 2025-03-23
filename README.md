# FolderManager

## Overview  
FolderManager is a simple Java command-line application that allows users to **create, delete, move, and list folders** using a structured Command Pattern.

## Features  
- `create <folder>` — Creates a new folder.  
- `delete <folder>` — Deletes a folder and its contents.  
- `move <source> <destination>` — Moves a folder to a new location.  
- `list <folder>` — Displays all subfolders in a tree structure.  
- Uses **Command Pattern** for clean and modular design.

## How to compile and run

### Compile  
Navigate to the project root where the `src` folder is located and run:
```
javac -d out -sourcepath src src/main/FolderManager.java
```

### ️Run  
```
java -cp out main.FolderManager
```

## Project Structure  
```
FolderManager/
 ├── src/
 │   ├── commands/
 │   │   ├── Command.java
 │   │   ├── CreateCommand.java
 │   │   ├── DeleteCommand.java
 │   │   ├── ListCommand.java
 │   │   ├── MoveCommand.java
 │   ├── main/
 │   │   ├── FolderManager.java
 ├── README.md
```

## Example usage  
```
create myFolder
move myFolder newFolder
create newFolder/abc1
create newFolder/abc2
list
newFolder
  abc1
  abc2

delete newFolder
```

## Requirements  
- Java 8 or later  
- Run commands from the root directory where `src` is located