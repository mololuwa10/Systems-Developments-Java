package Tests;

import SMMCompetitor.Competitor;
import SMMCompetitor.CompetitorList;
import SMMCompetitor.Name;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompetitorListTest {
    private Competitor competitor;
    CompetitorList competitorList = new CompetitorList();
    private Competitor competitor1;
    private Competitor competitor2;

    private Name name;
    private String[] score;
    private Name name2;


    @Before
    public void setup() {
        name = new Name("John", "Doe");
        name2 = new Name("Jane", "Doe");
        score = new String[]{"10", "10", "10", "10"};
        competitor1 = new Competitor("1", name, 25, "USA", score, "Male", "Veteran");
        competitor2 = new Competitor("2", name2, 24, "USA", score, "Female", "Beginner");
        competitorList.add(competitor1);
        competitorList.add(competitor2);
    }

    @Test
    public void testAdd() {
        Name name = new Name("TestFirstName", "TestMiddleName", "TestLastName");
        String[] score = new String[]{"10", "10", "10", "10"};
        competitor = new Competitor("200", name, 30, "TestCountry", score, "TestGender", "Veteran");
        CompetitorList competitorList = new CompetitorList();
        competitorList.add(competitor);
        assertTrue(competitorList.competitorList.contains(competitor));
    }

    @Test
    public void testGetSelectedFile() {
        competitorList.selectedFile = "test.txt";
        String expected = "test.txt";
        String result = competitorList.getSelectedFile();
        assertEquals(expected, result);
    }

    @Test
    public void testSetSelectedFile() {
        competitorList.setSelectedFile("test.txt");
        String expected = "test.txt";
        String result = competitorList.selectedFile;
        assertEquals(expected, result);
    }

    @Test
    public void testGetAllCompetitors() {
        Name name = new Name("John", "Doe");
        Name name2 = new Name("Jane", "Doe");
        String[] score = new String[]{"10", "10", "10", "10"};
        CompetitorList instance = new CompetitorList();
        Competitor competitor1 = new Competitor("1", name, 25, "USA", score, "Male", "Veteran");
        Competitor competitor2 = new Competitor("2", name2, 24, "USA", score, "Female", "Beginner");
        instance.competitorList.add(competitor1);
        instance.competitorList.add(competitor2);
        String expected = "CiD Competitor Name Age   Level       Country  Scores            Gender\n" +
                "1   John Doe        25    Expert      USA      [10, 10, 10, 10]   Male \n" +
                "2   Jane Doe        24    Beginner    USA      [10, 10, 10, 10]   Female \n";
        String result = instance.getAllCompetitors();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAverageScore() {
        double expected = 10.0;
        double result = competitorList.getAverageScore();
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testGetMaxScore() {
        Name name = new Name("John", "Doe");
        Name name2 = new Name("Jane", "Doe");
        String[] score = new String[]{"12", "10", "10", "10"};
        String[] score2 = new String[]{"12", "14", "10", "10"};
        CompetitorList instance = new CompetitorList();
        Competitor competitor1 = new Competitor("1", name, 25, "USA", score, "Male", "Veteran");
        Competitor competitor2 = new Competitor("2", name2, 24, "USA", score2, "Female", "Beginner");
        instance.competitorList.add(competitor1);
        instance.competitorList.add(competitor2);
        int expected = 46;
        int result = (int) instance.getMaxScore();
        assertEquals(expected, result);
    }

    @Test
    public void testGetMinScore() {
        Name name = new Name("John", "Doe");
        Name name2 = new Name("Jane", "Doe");
        String[] score = new String[]{"12", "10", "10", "10"};
        String[] score2 = new String[]{"12", "14", "10", "10"};
        CompetitorList instance = new CompetitorList();
        Competitor competitor1 = new Competitor("1", name, 25, "USA", score, "Male", "Veteran");
        Competitor competitor2 = new Competitor("2", name2, 24, "USA", score2, "Female", "Beginner");
        instance.competitorList.add(competitor1);
        instance.competitorList.add(competitor2);
        int expected = 42;
        int result = (int) instance.getMinScore();
        assertEquals(expected, result);
    }

    @Test
    public void testGetSize() {
        int size = competitorList.getSize();
        assertEquals(2, size);
    }

    @Test
    public void testGetAtIndex() {
        Competitor competitor = competitorList.getAtIndex(0);
        assertEquals("1", competitor.getId());
        assertEquals("John Doe", competitor.getName().getFullName());

        competitor = competitorList.getAtIndex(1);
        assertEquals("2", competitor.getId());
        assertEquals("Jane Doe", competitor.getName().getFullName());
    }

    @Test

    public void testFindById() {
        Competitor result = competitorList.findById("1");
        assertNotNull(result);
        assertEquals("1", result.getId());
        assertEquals("John", result.getName().getFirstName());
        assertEquals("Doe", result.getName().getLastName());
        assertEquals(25, result.getAge());
        assertEquals("Veteran", result.getCompLevel());
        assertEquals("USA", result.getCountry());
        assertArrayEquals(new String[]{"10", "10", "10", "10"}, result.getScores());
        assertEquals("Male", result.getGender());

        Competitor testNonId = competitorList.findById("3");
        assertNull(testNonId);
    }

    @Test
    public void testAddToId() {
        int result = competitorList.addToId();
        assertEquals(3, result);
    }

    @Test
    public void testGetScoreFrequency() {
        String scoreFrequencyReport = competitorList.getScoreFrequency();
        assertTrue(scoreFrequencyReport.contains("10\t4\n"));
    }
}