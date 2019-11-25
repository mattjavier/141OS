import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserThread extends Thread {

    private int i;
    private File inputFile;
    private String inputFileName;
    private StringBuffer currentLine;
    private FileReader fileReader;
    private BufferedReader reader;
    private int availableDisk;
    public static ResourceManager PrinterManager = new ResourceManager(MainClass.printers.length);
    public boolean isActive;

    // used for testing
    public String name;

    public UserThread(int ind) {
        i = ind;
        inputFileName = "./inputs/USER"+i;
        inputFile = new File(inputFileName);
        currentLine = new StringBuffer();

        name = inputFileName;
    }

    @Override
    public void run() {
        StringBuffer currentFileName = null;
        int sector;
        Disk currentDisk;
        FileInfo currentFileInfo;

        try {
            fileReader = new FileReader(inputFile);
            reader = new BufferedReader(fileReader);
            String str;

            isActive = true;
            MainClass.g.createArrayPanels();

            while((str = reader.readLine()) != null) {
                currentLine = new StringBuffer();
                currentLine.append(str);

                if (currentLine.substring(0, 4).equals(".sav")) {
                    // save to disk
                    availableDisk = MainClass.diskManager.request();

                    MainClass.diskManager.disks[availableDisk].isActive = true;
                    MainClass.g.createArrayPanels();

                    currentFileName = new StringBuffer(currentLine.substring(6));
                    String o = currentFileName.toString() + " created on DISK" + (availableDisk+1);
                    System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + o);
                    MainClass.g.updateTextBox(o, "USER");

                } else if (currentLine.substring(0, 4).equals(".end")) {
                    // end of current file, save to disk, release disk
                    String o = currentFileName.toString() + " saved. DISK" + (availableDisk+1) + " released.";
                    System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + o);
                    MainClass.g.updateTextBox(o, "USER");
                    MainClass.diskManager.release(availableDisk);
                    MainClass.diskManager.disks[availableDisk].isActive = false;

                    MainClass.g.createArrayPanels();

                    currentFileName = new StringBuffer();

                } else if (!currentLine.substring(0, 1).equals(".")){

                    currentFileInfo = MainClass.diskManager.dm.getFileInfoFromDisk(currentFileName);
                    currentDisk = MainClass.diskManager.disks[availableDisk];

                    if (currentFileInfo != null) {
                        // FileInfo ALREADY exists
                        sector = MainClass.diskManager.nextFreeSector(availableDisk);
                        currentFileInfo.fileLength++;
                        currentDisk.write(sector, currentLine);

                        // write line to free sector
                        String o = "Writing " + currentLine.toString() + " to DISK" + (availableDisk+1) + " at sector " + sector;
                        System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + o);
                        MainClass.g.updateTextBox(o, "DISK");
                        Thread.sleep(200);

                    } else {
                        // FileInfo does not yet exist, CREATE new FileInfo object

                        sector = MainClass.diskManager.nextFreeSector(availableDisk);
                        currentFileInfo = new FileInfo(availableDisk, sector, 1);

                        MainClass.diskManager.dm.addFileToDisk(currentFileName, currentFileInfo);
                        currentDisk.write(sector, currentLine);

                        // write line to free sector
                        String o = "Writing " + currentLine.toString() + " to DISK" + (availableDisk+1) + " at sector " + sector;
                        System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + o);
                        MainClass.g.updateTextBox(o, "DISK");
                        Thread.sleep(200);
                    }

                } else if (currentLine.substring(0, 4).equals(".pri")) {
                    // print
                    String outFile = currentLine.substring(7);

                    PrintJobThread printJob = new PrintJobThread(outFile);
                    printJob.start();
                    String o = "UserThread " + i + " sending to printer.";
                    System.out.println("[" + (System.currentTimeMillis() - MainClass.startTime) + "]----" + o);
                    MainClass.g.updateTextBox(o, "USER");
                }
            }
            isActive = false;
            MainClass.g.createArrayPanels();

        } catch(InterruptedException ie) {
            ie.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
