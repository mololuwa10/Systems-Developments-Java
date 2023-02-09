////package Tests;
////
////import GUI.AddCompetitor;
////import SMMCompetitor.Competitor;
////import SMMCompetitor.CompetitorList;
////import org.junit.Test;
////import org.junit.Before;
////import org.junit.After;
////import static org.junit.Assert.*;
////import java.util.ArrayList;
////import javax.swing.JFrame;
////import javax.swing.JLabel;
////import javax.swing.JPanel;
////import javax.swing.JTextField;
////import javax.swing.JButton;
////import javax.swing.table.DefaultTableModel;
////
////public class AddCompetitorTest {
////    private AddCompetitor addCompetitor;
////    private CompetitorList competitorList;
////    private DefaultTableModel tableModel;
////
////    @Before
////    public void setup() {
////        tableModel = new DefaultTableModel();
////        addCompetitor = new AddCompetitor(competitorList, tableModel);
////    }
////
////    @Test
////    public void testIfGUICreated() {
////        JFrame newCompetitorFrame = addCompetitor.newCompetitorFrame;
////        assertNotNull(newCompetitorFrame);
////
////        JPanel newCompetitorPanel = (JPanel) newCompetitorFrame.getContentPane().getComponent(0);
////        assertNotNull(newCompetitorPanel);
////
////        JLabel name = (JLabel) newCompetitorPanel.getComponent(0);
////        assertEquals("Enter Competitor's Full Name: ", name.getText());
////
////        JTextField nameField = (JTextField) newCompetitorPanel.getComponent(1);
////        assertNotNull(nameField);
////
////        JLabel age = (JLabel) newCompetitorPanel.getComponent(2);
////        assertEquals("Enter Competitor's Age: ", age.getText());
////
////        JTextField ageField = (JTextField) newCompetitorPanel.getComponent(3);
////        assertNotNull(ageField);
////
////        // similarly, you can check the rest of the fields.
////        JLabel level = (JLabel) newCompetitorPanel.getComponent(4);
////        assertEquals("Enter Competitor's Level: ", level.getText());
////
////        JTextField levelField = (JTextField) newCompetitorPanel.getComponent(5);
////        assertNotNull(levelField);
////
////        JLabel country = (JLabel) newCompetitorPanel.getComponent(6);
////        assertEquals("Enter Competitor's Country: ", country.getText());
////
////        JTextField countryField = (JTextField) newCompetitorPanel.getComponent(7);
////        assertNotNull(countryField);
////
////        JLabel scores = (JLabel) newCompetitorPanel.getComponent(6);
////        assertEquals("Enter Competitor's Scores: ", scores.getText());
////        JTextField score1Field = new JTextField(5);
////        JTextField score2Field = new JTextField(5);
////        JTextField score3Field = new JTextField(5);
////        JTextField score4Field = new JTextField(5);
////        newCompetitorPanel.add(scores);
////
////        assertEquals(score1Field, newCompetitorPanel.getComponent(7));
////        assertEquals(score2Field, newCompetitorPanel.getComponent(8));
////        assertEquals(score3Field, newCompetitorPanel.getComponent(9));
////        assertEquals(score4Field, newCompetitorPanel.getComponent(10));
////    }
////
////    @After
////    public void teardown() {
////        addCompetitor = null;
////        competitorList = null;
////        tableModel = null;
////    }
////}
package Tests;

import GUI.AddCompetitor;
import SMMCompetitor.Competitor;
import SMMCompetitor.CompetitorList;
import SMMCompetitor.Name;
import org.junit.Test;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AddCompetitorTest {

    @Test
    public void testAddCompetitor() throws IOException {
        CompetitorList competitorList = new CompetitorList();

        competitorList.readFile("competitor_sample.csv");
        int sizeCompetitor = competitorList.getSize();
        String[] scores = {"5", "7", "7", "7"};
        Competitor johnDoe = new Competitor("100" , new Name("John", "Doe"), 25, "USA", scores, "Male", "Veteran");
        competitorList.add(johnDoe);

        int newSize = competitorList.getSize();
        assertEquals(newSize, sizeCompetitor + 1);
    }
}

