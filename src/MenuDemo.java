import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MenuDemo {
    private boolean isFirstPress = true;
    private JTextField textField;


    public MenuDemo() {
        JFrame frame = new JFrame("Menu Demo");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem printDateTimeItem = new JMenuItem("Print Date and Time");
        printDateTimeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printDateTime();
            }
        });

        JMenuItem writeToFileItem = new JMenuItem("Write to log.txt");
        writeToFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeToFile();
            }
        });

        JMenuItem changeBackgroundColorItem = new JMenuItem("Change Background Color");
        changeBackgroundColorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    changeBackgroundColor(panel);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error changing background color: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(printDateTimeItem);
        menu.add(writeToFileItem);
        menu.add(changeBackgroundColorItem);
        menu.add(exitItem);

        menuBar.add(menu);
        panel.add(menuBar, BorderLayout.NORTH);

        textField = new JTextField();
        textField.setColumns(1);
        panel.add(textField, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);


    }

    private void printDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());
        textField.setText(dateTime);
    }

    private void writeToFile() {
        String content = textField.getText();
        try {
            FileWriter writer = new FileWriter("log.txt");
            writer.write(content);
            writer.close();
            JOptionPane.showMessageDialog(null, "Text written to log.txt successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void changeBackgroundColor(JPanel panel) throws Exception {
        if (isFirstPress) {
            panel.setBackground(Color.GREEN);
            isFirstPress = false;
        } else {
            Random random = new Random();
            float hue = random.nextFloat() * 0.3f + 0.3f; // Limiting to a range of green hues
            Color color = Color.getHSBColor(hue, 0.6f, 0.9f);
            panel.setBackground(color);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuDemo();
            }
        });
    }
}