import java.io.IOException;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class gui {

    static JFrame frame;
    static JPanel panel;
    static JPanel userPanel;
    static JPanel printerPanel;
    static JPanel diskPanel;
    static JPanel arrayPanels;
    static JPanel userPanels;
    static JPanel printerPanels;
    static JPanel diskPanels;
    static JPanel MenuBar;
    static JPanel user1;
    static JPanel user2;
    static JPanel user3;
    static JPanel user4;
    static JPanel printer1;
    static JPanel printer2;
    static JPanel printer3;
    static JPanel disk1;
    static JPanel disk2;
    static JPanel disk3;
    static JPanel textPanel;
    static JTextField tf;
    static JPanel bottom;

    gui() {
        frame = new JFrame("141 OS");
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        arrayPanels = new JPanel();
        userPanels = new JPanel();
        printerPanels = new JPanel();
        diskPanels = new JPanel();
        MenuBar = new JPanel();
        textPanel = new JPanel();
        bottom = new JPanel();
        createGUI();
    }

    static void createGUI() {

        GridLayout panGL = new GridLayout(1, 2);
        panGL.setHgap(10);
        panel.setLayout(panGL);
        // Panel for "Start Simulation" button
        JButton startButton = new JButton("START");
        startButton.setActionCommand("START");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainClass.run();
        }});

        // EXIT button for mid execution exit or after program finishes
        JButton exitButton = new JButton("EXIT");
        exitButton.setActionCommand("EXIT");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
        }});

        JPanel buttons = new JPanel();
        GridLayout buttonGL = new GridLayout(1, 2);
        buttonGL.setHgap(5);
        buttons.setLayout(buttonGL);
        buttons.add(startButton);
        buttons.add(exitButton);
        buttons.setBackground(Color.black);
        panel.add(buttons);

        // Key
        JPanel key = new JPanel();
        GridLayout keyLayout = new GridLayout(1, 4);
        keyLayout.setHgap(5);
        key.setLayout(keyLayout);

        JLabel keyLabel = new JLabel("KEY");
        keyLabel.setForeground(Color.white);
        key.add(keyLabel);

        JPanel active = new JPanel();
        JPanel notActive = new JPanel();
        JLabel activeLabel = new JLabel("ACTIVE");
        JLabel notActiveLabel = new JLabel("NOT ACTIVE");
        active.add(activeLabel);
        notActive.add(notActiveLabel);
        active.setBackground(Color.red);
        notActive.setBackground(Color.green);
        key.add(active);
        key.add(notActive);

        key.add(new JLabel(""));
        key.setBackground(Color.black);

        panel.add(key);
        panel.setBackground(Color.black);

        // Text Panel
        tf = new JTextField(30);
        textPanel.add(tf);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.black);

        // Bottom Panel
        GridLayout bot = new GridLayout(2, 1);
        bot.setVgap(5);
        bottom.setLayout(bot);

        bottom.add(panel);
        bottom.add(textPanel);
        bottom.setBackground(Color.black);

        createArrayPanels();

        // Menu Bar
        GridLayout MenuGL = new GridLayout(1, 3);
        MenuGL.setHgap(10);
        JPanel userTitle = new JPanel();
        JLabel userLabel = new JLabel("USERS (1...4)");
        JPanel printerTitle = new JPanel();
        JLabel printerLabel = new JLabel("PRINTERS (1...3)");
        JPanel diskTitle = new JPanel();
        JLabel diskLabel = new JLabel("DISKS (1...2)");
        userLabel.setForeground(Color.white);
        printerLabel.setForeground(Color.white);
        diskLabel.setForeground(Color.white);
        userTitle.setBackground(Color.black);
        printerTitle.setBackground(Color.black);
        diskTitle.setBackground(Color.black);
        userTitle.add(userLabel);
        printerTitle.add(printerLabel);
        diskTitle.add(diskLabel);
        MenuBar.add(userTitle);
        MenuBar.add(new JPanel().add(new JLabel("          ")));
        MenuBar.add(new JPanel().add(new JLabel("          ")));
        MenuBar.add(new JPanel().add(new JLabel("          ")));
        MenuBar.add(printerTitle);
        MenuBar.add(new JPanel().add(new JLabel("          ")));
        MenuBar.add(new JPanel().add(new JLabel("          ")));
        MenuBar.add(new JPanel().add(new JLabel("          ")));
        MenuBar.add(diskTitle);
        MenuBar.setBackground(Color.black);
    }

    static synchronized void createArrayPanels() {
        // 3 Panels for Users, Printers, Disks
        GridLayout gL = new GridLayout(1, 3);
        gL.setHgap(10);
        arrayPanels.setLayout(gL);

        // Labels for Users, Printers, Disks
        GridLayout userGL = new GridLayout(4, 1);
        GridLayout printerGL = new GridLayout(3, 1);
        GridLayout diskGL = new GridLayout(2, 1);
        userGL.setVgap(10);
        printerGL.setVgap(10);
        diskGL.setVgap(10);
        userPanels.setLayout(userGL);
        printerPanels.setLayout(printerGL);
        diskPanels.setLayout(diskGL);

        arrayPanels.removeAll();

        // User Panels
        userPanels.removeAll();
        user1 = new JPanel();
        user2 = new JPanel();
        user3 = new JPanel();
        user4 = new JPanel();
        if (MainClass.users[0].isActive) {
            user1.setBackground(Color.red);
        } else {
            user1.setBackground(Color.green);
        }

        if (MainClass.users[1].isActive) {
            user2.setBackground(Color.red);
        } else {
            user2.setBackground(Color.green);
        }

        if (MainClass.users[2].isActive) {
            user3.setBackground(Color.red);
        } else {
            user3.setBackground(Color.green);
        }

        if (MainClass.users[3].isActive) {
            user4.setBackground(Color.red);
        } else {
            user4.setBackground(Color.green);
        }
        user1.removeAll();
        user1.revalidate();
        userPanels.add(user1);

        user2.revalidate();
        userPanels.add(user2);

        user3.revalidate();
        userPanels.add(user3);

        user4.revalidate();
        userPanels.add(user4);

        userPanels.revalidate();

        // Printer Panels
        printerPanels.removeAll();
        printer1 = new JPanel();
        printer2 = new JPanel();
        printer3 = new JPanel();
        if (MainClass.printers[0].isActive) {
            printer1.setBackground(Color.red);
        } else {
            printer1.setBackground(Color.green);
        }

        if (MainClass.printers[1].isActive) {
            printer2.setBackground(Color.red);
        } else {
            printer2.setBackground(Color.green);
        }

        if (MainClass.printers[2].isActive) {
            printer3.setBackground(Color.red);
        } else {
            printer3.setBackground(Color.green);
        }
        printer1.revalidate();
        printerPanels.add(printer1);

        printer2.revalidate();
        printerPanels.add(printer2);

        printer3.revalidate();
        printerPanels.add(printer3);

        printerPanels.revalidate();

        // Disk Panels
        diskPanels.removeAll();
        disk1 = new JPanel();
        disk2 = new JPanel();
        if (MainClass.diskManager.disks[0].isActive) {
            disk1.setBackground(Color.red);
        } else {
            disk1.setBackground(Color.green);
        }

        if (MainClass.diskManager.disks[1].isActive) {
            disk2.setBackground(Color.red);
        } else {
            disk2.setBackground(Color.green);
        }
        disk1.revalidate();
        diskPanels.add(disk1);

        disk2.revalidate();
        diskPanels.add(disk2);

        diskPanels.revalidate();

        // Add panels to arrayPanels
        arrayPanels.add(userPanels);
        arrayPanels.add(printerPanels);
        arrayPanels.add(diskPanels);
        arrayPanels.revalidate();
    }

    static synchronized void updateTextBox(String text, String type) {
        Font font = new Font("SansSerif", Font.PLAIN, 10);
        tf.setText(text+" ");
        tf.setHorizontalAlignment(JTextField.CENTER);
        tf.setFont(font);

        if (type.equals("USER")) {
            tf.setForeground(Color.blue);
        } else if (type.equals("PRINTER")) {
            tf.setForeground(Color.darkGray);
        } else {
            tf.setForeground(Color.red);
        }
        textPanel.revalidate();
    }

    static void showGUI() {
        // Display all Panels
        frame.getContentPane().add(BorderLayout.NORTH, MenuBar);
        frame.getContentPane().add(BorderLayout.CENTER, arrayPanels);
        frame.getContentPane().add(BorderLayout.SOUTH, bottom);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

public class MainClass {

    public static final int NUMBER_OF_USERS = 4;
    public static final int NUMBER_OF_DISKS = 2;
    public static final int NUMBER_OF_PRINTERS = 3;

    public static UserThread[] users = new UserThread[NUMBER_OF_USERS];
    public static Printer[] printers = new Printer[NUMBER_OF_PRINTERS];

    public static DiskManager diskManager = new DiskManager(NUMBER_OF_DISKS, NUMBER_OF_DISKS);

    public static gui g;
    public static long startTime;

    public static void run() {
        startTime = System.currentTimeMillis();
        for (int i = 0; i < users.length; i++) {
            users[i].start();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < users.length; i++) {
            users[i] = new UserThread(i+1);
        }

        for (int i = 0; i < printers.length; i++) {
            printers[i] = new Printer(i+1);
        }

        g = new gui();
        g.showGUI();
    }
}
