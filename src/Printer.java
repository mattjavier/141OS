import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Printer {

    public boolean isActive;
    private String fileName;
    private FileWriter fileWriter;
    private File file;
    private BufferedWriter writer;

    public Printer(int ind) {
        fileName = "./outputs/PRINTER"+ind;
        isActive = false;
        file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(StringBuffer content) throws IOException {
        try {
            fileWriter = new FileWriter(file, true);
            writer = new BufferedWriter(fileWriter);
            writer.write(content.toString() + "\n");
            writer.flush();
        } catch (IOException e) {
            System.out.println("Could not write to " + fileName);
            e.printStackTrace();
        }
    }
}
