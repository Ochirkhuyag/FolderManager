package storage;

import java.util.*;

public class Folder {
    private Map<String, Folder> subfolders = new HashMap<>();
    private String name;

    public Folder(String name) {
        this.name = name;
    }

    public Folder addOrGetSubfolder(String folderName) {
        return subfolders.computeIfAbsent(folderName, Folder::new);
    }

    public Collection<Folder> getSubfolders() {
        return subfolders.values();
    }

    public String getName() {
        return name;
    }
    
    public Folder getSubfolder(String name) {
        return subfolders.get(name);
    }

    public void removeSubfolder(String name) {
        subfolders.remove(name);
    }

    public void addSubfolder(Folder folder) {
        subfolders.put(folder.getName(), folder);
    }

    public boolean hasSubfolder(String name) {
        return subfolders.containsKey(name);
    }
}
