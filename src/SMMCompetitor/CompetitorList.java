package SMMCompetitor;

import java.io.*;
import java.util.*;

public class CompetitorList {
    public ArrayList<Competitor> competitorList;

    //create an empty arraylist
    public CompetitorList() {
        competitorList = new ArrayList<Competitor>();
    }

    public void add(Competitor c) {
        competitorList.add(c);
    }

    public String getSelectedFile() {
        return selectedFile;
    }
    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
    }

    public String selectedFile;

    public String getAllCompetitors() {
        String report = "";
        report += String.format("%-16s", "CiD");
        report += String.format("%-16s", "Competitor Name");
        report += String.format("%-16s", "Age");
        report += String.format("%-16s", "Level");
        report += String.format("%-16s", "Country");
        report += String.format("%-16s", "Scores");
        report += String.format("%-16s", "Gender");
        report += String.format("%-16s", "Overall");

        report += "\n";
        for (Competitor c : competitorList) {
            report += String.format("%-16s", c.getId());
            report += String.format("%-16s", c.getName().getFullName());
            report += String.format("%-16s", c.getAge());
            report += String.format("%-16s", c.getCompLevel());
            report += String.format("%-16s", c.getCountry());
            report += String.format("%-16s", Arrays.toString(c.getScores()));
            report += String.format("%-16s", c.getGender());
            report += String.format("%-16s", c.getOverallScore());
            report += "\n";
        }
        return report;
    }

    public double getAverageScore() {
        double average = 0;
        for (Competitor c : competitorList) {
            double sum = 0;
            String[] numbers = c.getScores();
            for (String number : numbers) {
                sum += Integer.parseInt(number);
            }
            average = sum / numbers.length;
            System.out.println("Average score of competitor " + c.getId() + ": " + average);
        }
        return average;
    }

    public double getMaxScore() {
        int max = Integer.MIN_VALUE;
        Competitor maxCompetitor = null;
        for (Competitor c : competitorList) {
            String[] numbers = c.getScores();
            double sumScore = 0;
            for (String number : numbers) {
                sumScore += Integer.parseInt(number);
            }
            if (sumScore > max) {
                max = (int) sumScore;
                maxCompetitor = c;
            }
        }
        System.out.println("The Competitor with the highest score is " + maxCompetitor.getName().getFullName() + " with a score of " + max);
        return max;
    }

    public double getMinScore() {
        int min = Integer.MAX_VALUE;
        Competitor minCompetitor = null;
        for (Competitor c : competitorList) {
            String[] numbers = c.getScores();
            double sumScore = 0;
            for (String number : numbers) {
                sumScore += Integer.parseInt(number);
            }
            if (sumScore < min) {
                min = (int) sumScore;
                minCompetitor = c;
            }
        }
        System.out.println("The Competitor with the lowest score is " + minCompetitor.getName().getFullName() + "(" + minCompetitor.getId() + ") with a score of " + min);
        return min;
    }

    //returns the number of elements in the list
    public int getSize() {
        return competitorList.size();
    }

    //returns the Staff object at specified index position
    public Competitor getAtIndex(int index) {
        return competitorList.get(index);
    }

    //returns the Staff object with a specified id
    //demonstrates searching through the array
    //and stopping by returning when a match is found
    public Competitor findById(String id) {
        for (Competitor comp : competitorList) {
            if (comp.getId().equals(id)) {
                return comp;
            }
        }
        return null;
    }

    /**
     * writes supplied text to file
     *
     * @param filename the name of the file to be written to
     * @param report   the text to be written to the file
     */
    public void writeToFile(String filename, String report) {
        FileWriter fw;
        try {
            fw = new FileWriter(filename);
            fw.write("The report\n");
            fw.write(report);
            fw.close();
        }
        //message and stop if file not found
        catch (FileNotFoundException fnf) {
            System.out.println(filename + " not found ");
            System.exit(0);
        }
        //stack trace here because we don't expect to come here
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(1);
        }
    }
    /**
     * reads file with given name, extracting student data, creating student objects
     * and adding them to the list of students
     * Blank lines are skipped
     * Validation for integer year, missing items
     *
     * @param filename the name of the input file
     */
    public void readFile(String filename) {
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                //read first line and process it
                String inputLine = scanner.nextLine();
                if (inputLine.length() != 0) {//ignored if blank line
                    processLine(inputLine);
                }

            }
        }
        //if the file is not found, stop with system exit
        catch (FileNotFoundException fnf) {
            System.out.println(filename + " not found ");
            System.exit(0);
        }
    }

    /**
     * Processes line, extracts data, creates Competitor object
     * and adds to list
     * Checks for non-numeric year and missing items
     * Will still crash if name entered without a space
     *
     * @param line the line to be processed
     */

    private void processLine(String line) {
        try {
            String[] parts = line.split(",");
            Name name = new Name(parts[1]);
            String id = parts[0];
            int age = Integer.parseInt(parts[3]);
            String country = parts[5];
            String gender = parts[4];
            String compLevel = parts[2];

            //the qualifications are at the end of the line
            int scoreLength = parts.length - 6;
            String[] scores = new String[scoreLength];
            System.arraycopy(parts, 6, scores, 0, scoreLength);

            //create Competitor object and add to the list
            Competitor c = new Competitor(id, name, age, country, scores, gender, compLevel);
            this.add(c);
        }

        //for these two formatting errors, ignore lines in error and try and carry on
        //this catches trying to convert a String to an integer
        
        catch (NumberFormatException nfe) {
            String error = "Number conversion error in '" + line + "'  - "
                    + nfe.getMessage();
            System.out.println(error);
        }
        //this catches missing items if only one or two items
        //other omissions will result in other errors
        catch (ArrayIndexOutOfBoundsException air) {
            String error = "Not enough items in  : '" + line
                    + "' index position : " + air.getMessage();
            System.out.println(error);
        }
    }

    public int addToId() {
        int maxId = 0;
        for (Competitor c: competitorList) {
            if(Integer.parseInt(c.getId()) > maxId){
                maxId = Integer.parseInt(c.getId());
            }
        }
        return maxId + 1;
    }

public String getScoreFrequency() {
    Map<Double, Integer> scoreFrequency = new HashMap<>();
    for (Competitor competitor : competitorList) {
        double[] scores = competitor.getScoreArray();
        for (double score : scores) {
            scoreFrequency.put(score, scoreFrequency.getOrDefault(score, 0) + 1);
        }
    }

    StringBuilder report = new StringBuilder("Score\tFrequency\n");
    for (Map.Entry<Double, Integer> entry : scoreFrequency.entrySet()) {
        report.append(entry.getKey()).append("\t").append(entry.getValue()).append("\n");
    }
    return report.toString();
}

    public List<Object[]> prepareData() {
        List<Object[]> data = new ArrayList<>();
        for(Competitor cpList: competitorList) {
            data.add(new Object[]{cpList.getId(), cpList.getName().getFullName(), cpList.getAge(), cpList.getCompLevel(), cpList.getCountry(), Arrays.toString(cpList.getScores()), cpList.getGender(), cpList.getOverallScore()});
        }
        return data;
    }

}
