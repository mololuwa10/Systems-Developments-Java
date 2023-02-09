package GUI;

import SMMCompetitor.CompetitorList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileChooser {
    private JFrame frame;

    public void createGUI() {
        frame = new JFrame("File Chooser Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel and add it to the JFrame
        JPanel panel = new JPanel();
        frame.add(panel);

        // Create a button to open the file chooser
        JButton chooseFileButton = new JButton("Choose File");
        chooseFileButton.addActionListener(new ChooseFileButtonListener());
        panel.add(chooseFileButton);

        // Set the size and location of the JFrame
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);

        // Show the JFrame
        frame.setVisible(true);
    }

    private class ChooseFileButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Create a file chooser
            JFileChooser fileChooser = new JFileChooser();

            // Show the file chooser and wait for the user's response
            int returnValue = fileChooser.showOpenDialog(frame);

            // If the user clicked the "Open" button, load the selected file
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                CompetitorList football = new CompetitorList();
                football.setSelectedFile(selectedFile.getAbsolutePath());

                football.readFile(selectedFile.getAbsolutePath());

                CompetitorTable competitorTable = new CompetitorTable(football);
//                competitorTable.createGUI(football.prepareData());
                frame.dispose();


            } else if (returnValue == JFileChooser.CANCEL_OPTION) {
                frame.dispose();
            }
        }
    }

}
