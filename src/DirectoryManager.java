import java.util.*;

public class DirectoryManager {

    private final Hashtable<String, FileInfo> T = new Hashtable<String, FileInfo>();

    public void enter(StringBuffer key, FileInfo file) {
        String name = key.toString();
        T.put(name, file);
    }

    public FileInfo lookup(StringBuffer key) {
        String name = key.toString();
        return T.get(name);
    }

    public synchronized FileInfo getFileInfoFromDisk(StringBuffer name) {
        return lookup(name);
    }

    public synchronized void addFileToDisk(StringBuffer name, FileInfo info) {
        enter(name, info);
    }
}
