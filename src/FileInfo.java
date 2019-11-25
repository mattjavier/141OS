
public class FileInfo {
    public int diskNumber;
    public int startingSector;
    public int fileLength;

    public FileInfo(int num, int sect, int length) {
        diskNumber = num;
        startingSector = sect;
        fileLength = length;
    }
}
