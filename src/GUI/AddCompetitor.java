package GUI;

import SMMCompetitor.Competitor;
import SMMCompetitor.CompetitorList;
import SMMCompetitor.Name;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class AddCompetitor {
    public Competitor competitor;
    public JTextField nameField;
    public JTextField ageField;
    public JTextField levelField;
    public JTextField countryField;
    public JTextField score1Field;
    public JTextField score2Field;
    public JTextField score3Field;
    public JTextField score4Field;
    public JFrame newCompetitorFrame;
    public JTextField scoresField;
    public JTextField genderField;
    private JButton saveButton;
    private JButton cancelButton;
    public CompetitorList competitorlist;
    public DefaultTableModel tableModel;
//    private int row;

    public AddCompetitor(CompetitorList competitorList, DefaultTableModel tableModel) {
        this.competitorlist = competitorList;
        this.tableModel = tableModel;
//        this.competitor = competitor;

        newCompetitorFrame = new JFrame("New Competitor");
        newCompetitorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newCompetitorFrame.setLocationRelativeTo(null);
        newCompetitorFrame.setSize(500, 400);

        JPanel newCompetitorPanel = new JPanel();
        newCompetitorFrame.add(newCompetitorPanel);

        JLabel name = new JLabel("Enter Competitor's Full Name: ");
        nameField = new JTextField(40);
        newCompetitorPanel.add(name);
        newCompetitorPanel.add(nameField);

        JLabel age = new JLabel("Enter Competitor's Age: ");
        ageField = new JTextField(40);
        newCompetitorPanel.add(age);
        newCompetitorPanel.add(ageField);

        JLabel level = new JLabel("Enter Competitor's Level: ");
        levelField = new JTextField(40);
        newCompetitorPanel.add(level);
        newCompetitorPanel.add(levelField);

        JLabel country = new JLabel("Enter Competitor's Country: ");
        countryField = new JTextField(40);
        newCompetitorPanel.add(country);
        newCompetitorPanel.add(countryField);

        JLabel scores = new JLabel("Enter Competitor's Scores: ");
        score1Field = new JTextField(5);
        score2Field = new JTextField(5);
        score3Field = new JTextField(5);
        score4Field = new JTextField(5);
        newCompetitorPanel.add(scores);

        newCompetitorPanel.add(score1Field);
        newCompetitorPanel.add(score2Field);
        newCompetitorPanel.add(score3Field);
        newCompetitorPanel.add(score4Field);

        JLabel gender = new JLabel("Enter Competitor's Gender: ");
        genderField = new JTextField(40);
        newCompetitorPanel.add(gender);
        newCompetitorPanel.add(genderField);

        JButton saveButton = new JButton("Save");
        newCompetitorPanel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter tempFile = new FileWriter(competitorlist.getSelectedFile(), true);
                    PrintWriter pw = new PrintWriter(tempFile);

                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String level = levelField.getText();
                    String country = countryField.getText();
                    String gender = genderField.getText();
                    String[] scores = {score1Field.getText(), score2Field.getText(), score3Field.getText(), score4Field.getText()};

                    String scoresString = Arrays.toString(scores);
                    scoresString = scoresString.substring(1, scoresString.length() - 1);
                    Name nameObject = new Name(name);
                    Competitor newCompetitor = new Competitor(String.valueOf(competitorList.addToId()), nameObject, age, country, scores, gender, level);

                    pw.println(String.valueOf(newCompetitor.getId()) + "," + name + "," + level + "," + age + "," + gender + "," + country + "," + scoresString);
                    pw.close();
                    competitorList.add(newCompetitor);
                    tableModel.addRow(new Object[]{String.valueOf(newCompetitor.getId()), newCompetitor.getName().getFullName(), String.valueOf(newCompetitor.getAge()), newCompetitor.getCompLevel(), newCompetitor.getCountry(), Arrays.toString(newCompetitor.getScores()), newCompetitor.getGender(), String.valueOf(newCompetitor.getOverallScore())});
                    newCompetitorFrame.dispose();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        newCompetitorFrame.setVisible(true);
    }
}