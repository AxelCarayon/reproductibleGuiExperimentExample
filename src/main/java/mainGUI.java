import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Paths;

public class mainGUI {

    private JButton calculerLaSommeButton;
    private JPanel panel1;
    private JTextField inputText;
    private JButton calculerLaMoyenneButton;
    private JButton validerButton;
    private JList<Number> list1;
    private JLabel result;
    private JButton viderLaListeButton;

    private final DefaultListModel<Number> model;
    private final String SOURCE_DIRECTORY = System.getProperty("user.dir");
    private final String OUTPUT_FOLDER = Paths.get(SOURCE_DIRECTORY, "outputs").toString();
    private final String INPUT_FOLDER = Paths.get(SOURCE_DIRECTORY, "inputs").toString();

    public mainGUI() {
        model = new DefaultListModel<>();
        list1.setModel(model);
        validerButton.addActionListener(e -> {
            model.addElement(Integer.valueOf(inputText.getText()));
            inputText.setText("");
        });

        inputText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    validerButton.doClick();
                }
            }
        });
        calculerLaSommeButton.addActionListener(e -> {
            if (model.size() > 0) {
                int somme = 0;
                for (Object number : model.toArray()) {
                    somme += (int) number;
                }
                result.setText(somme + "");
                writeInput();
                writeOutput("somme");
            }
        });

        calculerLaMoyenneButton.addActionListener(e -> {
            if (model.size() > 0) {
                int somme = 0;
                for (Object number : model.toArray()) {
                    somme += (int) number;
                }
                result.setText(somme / model.size() + "");
                writeInput();
                writeOutput("moyenne");
            }
        });
        viderLaListeButton.addActionListener(e -> model.removeAllElements());
    }

    private void writeInput() {
        File inputFile = new File(Paths.get(INPUT_FOLDER, "inputs.txt").toString());
        try {
            inputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(inputFile);
            for (Object number : model.toArray()) {
                writer.write((int) number + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeOutput(String fileName) {
        File outputFile = new File(Paths.get(OUTPUT_FOLDER, fileName + ".txt").toString());
        try {
            outputFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(outputFile);
            writer.write(result.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new mainGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
