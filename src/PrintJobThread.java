import java.io.IOException;

public class PrintJobThread extends Thread {

    private StringBuffer output;
    private Disk currentDisk;
    private FileInfo file;
    private StringBuffer name;
    private int len;
    private int dNumber;

    public PrintJobThread(String outFileName) {
        name = new StringBuffer(outFileName);

        file = MainClass.diskManager.dm.getFileInfoFromDisk(name);
        len = file.fileLength;
        dNumber = file.diskNumber;
        currentDisk = MainClass.diskManager.disks[dNumber];
    }

    @Override
    public void run() {
        output = new StringBuffer();

        int availablePrinter = UserThread.PrinterManager.request();
        String o = "PRINTER" + (availablePrinter + 1) + " requested.";
        System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + o);
        MainClass.g.updateTextBox(o, "PRINTER");

        MainClass.printers[availablePrinter].isActive = true;
        MainClass.g.createArrayPanels();

        Printer p = MainClass.printers[availablePrinter];

        for (int i = 0; i < len; i++) {
            int currentSector = file.startingSector + i;
            MainClass.diskManager.disks[dNumber].isActive = true;
            currentDisk.read(currentSector, output);

            String out1 = "Reading " + output.toString() + " from DISK" + (dNumber+1) + "from sector " + currentSector;
            System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + out1);
            MainClass.g.updateTextBox(out1, "DISK");
            try {
                Thread.sleep(200);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            MainClass.diskManager.disks[dNumber].isActive = false;
            String out2 = output.toString() + " to PRINTER" + (availablePrinter+1);
            System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + out2);
            MainClass.g.updateTextBox(out2, "PRINTER");
            try {
                p.print(output);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2750);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            output = new StringBuffer();
        }

        String o2 = "Printed to PRINTER" + (availablePrinter+1);
        System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + o2);
        MainClass.g.updateTextBox(o2, "PRINTER");

        MainClass.printers[availablePrinter].isActive = false;
        MainClass.g.createArrayPanels();

        UserThread.PrinterManager.release(availablePrinter);
        String o3 = "PRINTER" + (availablePrinter+1) + " avaialable.";
        System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + o3);
        MainClass.g.updateTextBox(o3, "PRINTER");
    }
}
