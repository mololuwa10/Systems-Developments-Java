package GUI;

import SMMCompetitor.Competitor;
import SMMCompetitor.CompetitorList;
import SMMCompetitor.Name;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;

public class EditCompetitor extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField levelField;
    private JTextField countryField;
    private JTextField scoresField;
    private JTextField genderField;
    private JButton saveButton;
    private JButton cancelButton;
    public CompetitorList competitorlist;
    public Competitor competitor;
    public DefaultTableModel tableModel;
    public int row;

    public EditCompetitor(CompetitorList competitorList, int row, DefaultTableModel tableModel) throws IOException {
        this.competitorlist = competitorList;
        Competitor selectedCompetitor = competitorList.getAtIndex(row);
        this.competitor = selectedCompetitor;
        this.tableModel = tableModel;
        this.row = row;

        setTitle("Edit Competitor");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel);
        nameField = new JTextField(selectedCompetitor.getName().getFullName());
        panel.add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        panel.add(ageLabel);
        ageField = new JTextField(String.valueOf(selectedCompetitor.getAge()));
        panel.add(ageField);

        JLabel levelLabel = new JLabel("Level:");
        panel.add(levelLabel);
        levelField = new JTextField(selectedCompetitor.getCompLevel());
        panel.add(levelField);

        JLabel countryLabel = new JLabel("Country:");
        panel.add(countryLabel);
        countryField = new JTextField(selectedCompetitor.getCountry());
        panel.add(countryField);

        JLabel scoresLabel = new JLabel("Scores:");
        panel.add(scoresLabel);
        String scores = Arrays.toString(selectedCompetitor.getScores());
        scoresField = new JTextField(scores.substring(1, scores.length() - 1));
        panel.add(scoresField);

        JLabel genderLabel = new JLabel("Gender:");
        panel.add(genderLabel);
        genderField = new JTextField(selectedCompetitor.getGender());
        panel.add(genderField);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        panel.add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonListener());
        panel.add(cancelButton);

        add(panel);
    }

    private class SaveButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                    FileWriter tempFile = new FileWriter(competitorlist.getSelectedFile());
                    PrintWriter pw = new PrintWriter(tempFile);

                    for (int i = 0; i < competitorlist.getSize(); i++) {
                        Competitor c = competitorlist.getAtIndex(i);
                        if(c.getId() == competitor.getId()) {
                            // Updating the competitor's data
                            c.setName(new Name(nameField.getText()));
                            c.setAge(Integer.parseInt(ageField.getText()));
                            c.setCompLevel(levelField.getText());
                            c.setCountry(countryField.getText());
                            c.setScores(scoresField.getText().replaceAll("\\s", "").split(","));
                            c.setGender(genderField.getText());
                        }
                        String scores = Arrays.toString(c.getScores());
                        String scoreSet = scores.substring(1, scores.length() - 1).replaceAll("\\s", "");
                        pw.println(c.getId() + "," +c.getName().getFullName() + "," + c.getCompLevel() + "," + c.getAge()  + "," + c.getGender() + "," + c.getCountry() + "," + scoreSet);
                    }
                    pw.close();
                } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            tableModel.setValueAt(competitor.getName().getFullName(), row, tableModel.findColumn("Full Name"));
            tableModel.setValueAt(String.valueOf(competitor.getAge()), row, tableModel.findColumn("Age"));
            tableModel.setValueAt(competitor.getCompLevel(), row, tableModel.findColumn("Level"));
            tableModel.setValueAt(competitor.getCountry(), row, tableModel.findColumn("Country"));

            String scoresString = Arrays.toString(competitor.getScores());
            tableModel.setValueAt(scoresString.replaceAll("\\s", ""), row, tableModel.findColumn("Scores"));
            tableModel.setValueAt(competitor.getGender(), row, tableModel.findColumn("Gender"));
            tableModel.setValueAt(String.valueOf(competitor.getOverallScore()), row, tableModel.findColumn("Overall Score"));
            // Save the competitor's updated information to the database or file
            dispose();
        }
    }
    private class CancelButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Close the Edit Competitor frame without saving any changes
            dispose();
        }
    }
}
