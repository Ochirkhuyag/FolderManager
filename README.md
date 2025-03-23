# FolderManager

## Overview  
FolderManager is a simple Java command-line application that allows users to simulate creating, deleting, moving, and listing folders in memory using a clean Command Pattern structure.  
No folders are created on the actual filesystem — all operations are done in-memory.

## Features  
- `create <folder>` — Simulates creating a folder in memory  
- `delete <folder>` — Deletes a simulated folder from memory  
- `move <source> <destination>` — Moves a simulated folder to a new parent  
- `list` — Displays the entire in-memory folder tree structure  
- Optional `-debug` flag to print debug messages for actions taken  
- Uses Command Pattern for clean, testable, and extendable design


## Requirements  
- Java 8 or later

## How to compile and run

Compile:  
Navigate to your project root (where `src` is located) and run:
```
javac -d out -sourcepath src src/main/FolderManager.java
```

Run without debug:
```
java -cp out main.FolderManager
```

Run with debug mode enabled:
```
java -cp out main.FolderManager -debug
```
In debug mode, the program will print confirmation messages whenever folders are created, moved, or deleted.

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
 │   ├── config/
 │   │   ├── FeatureFlag.java
 │   ├── storage/
 │   │   ├── Folder.java
 │   ├── main/
 │   │   ├── FolderManager.java
 ├── README.md
```

## Example usage (without debug)
```
create myFolder
move myFolder newFolder
create newFolder/abc1
create newFolder/abc2
list
LIST

newFolder
  abc1
  abc2

delete newFolder
list
LIST
```

## Example usage with -debug
```
java -cp out main.FolderManager -debug

create food/fruits/apples
Created folder 'food/fruits/apples' in memory.

move food/fruits food/fruits-moved
Moved 'food/fruits' to 'food/fruits-moved'.

delete food/fruits-moved
Deleted 'food/fruits-moved' from memory.

list
LIST

food
```

---