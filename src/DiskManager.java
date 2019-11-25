
public class DiskManager extends ResourceManager {

    public static Disk[] disks;
    public DirectoryManager dm;

    public DiskManager(int numberOfDisks, int numberOfItems) {
        super(numberOfItems);
        dm = new DirectoryManager();
        disks = new Disk[numberOfDisks];
        for (int i = 0; i < disks.length; i++) {
            disks[i] = new Disk();
        }
    }

    public int nextFreeSector(int diskIndex) {
        for (int i = 0; i < Disk.NUM_SECTORS; i++) {
            StringBuffer test = new StringBuffer();
            disks[diskIndex].read(i, test);
            if (test.length() == 0) {
                return i;
            }
        }
        return -1;
    }
}
