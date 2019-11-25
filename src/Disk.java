
public class Disk {

    public static final int NUM_SECTORS = 1024;
    public boolean isActive;
    public boolean isDone;
    private StringBuffer sectors[];

    public Disk() {
        sectors = new StringBuffer[NUM_SECTORS];
        isActive = false;
        for (int i = 0; i < sectors.length; i++) {
            sectors[i] = new StringBuffer();
        }
    }

    public void write(int sector, StringBuffer data) {
        sectors[sector].append(data);
    }

    public void read(int sector, StringBuffer data) {
        data.append(sectors[sector]);
    }
}
