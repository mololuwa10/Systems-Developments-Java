package GUI;


import SMMCompetitor.CompetitorList;

import javax.swing.*;
import java.awt.*;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

//public class ScoreFrequency extends JFrame {
//    private CompetitorList competitorList;
//    private JFrame frame;
//
//    public ScoreFrequency(CompetitorList competitorList) {
//        this.competitorList = competitorList;
//        this.frame = this;
//        JFrame scoreFrequencyFrame = new JFrame("Score Frequency");
//        scoreFrequencyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        scoreFrequencyFrame.setLocationRelativeTo(null);
//        scoreFrequencyFrame.setSize(500, 400);
//
//        JPanel panel = new JPanel();
//        scoreFrequencyFrame.add(panel);
//        JTextArea reportFrequencyArea = new JTextArea();
//
//        reportFrequencyArea.setText(competitorList.getScoreFrequency());
//        System.out.println(competitorList.getScoreFrequency());
//        panel.add(reportFrequencyArea);
//        JLabel label = new JLabel();
//        label.setText("Number of Competitors: " + competitorList.getSize());
//
//        panel.add(label);
//
//        JButton closeButton = new JButton("Close");
//        closeButton .addActionListener(e -> {
//            try {
//                frame.dispose();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
//        panel.add(closeButton);
//        add(panel);
//        scoreFrequencyFrame.setVisible(true);
//    }
//}

//public class ScoreFrequency extends JFrame {
//    private CompetitorList competitorList;
//    private JFrame frame;
//
//    public ScoreFrequency(CompetitorList competitorList) {
//        this.competitorList = competitorList;
//        this.frame = this;
//        JFrame scoreFrequencyFrame = new JFrame("Score Frequency");
//        scoreFrequencyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        scoreFrequencyFrame.setLocationRelativeTo(null);
//        scoreFrequencyFrame.setSize(500, 400);
//
//        JPanel panel = new JPanel();
//        scoreFrequencyFrame.add(panel);
//        panel.setLayout(new BorderLayout());
//
//        JTextArea reportFrequencyArea = new JTextArea();
//        reportFrequencyArea.setFont(new Font("Arial", Font.PLAIN, 16));
//        reportFrequencyArea.setText(competitorList.getScoreFrequency());
//        panel.add(reportFrequencyArea, BorderLayout.CENTER);
//
//        JLabel label = new JLabel("Number of Competitors: " + competitorList.getSize(), SwingConstants.CENTER);
//        label.setFont(new Font("Arial", Font.BOLD, 18));
//        panel.add(label, BorderLayout.NORTH);
//
//        JButton closeButton = new JButton("Close");
//        closeButton.setFont(new Font("Arial", Font.PLAIN, 16));
//        closeButton.addActionListener(e -> {
//            try {
//                frame.dispose();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        });
//        panel.add(closeButton, BorderLayout.SOUTH);
//
//        scoreFrequencyFrame.setVisible(true);
//    }
//}

public class ScoreFrequency extends JFrame {
    private CompetitorList competitorList;
    private JFrame scoreFrequencyFrame;

    public ScoreFrequency(CompetitorList competitorList) {
        this.competitorList = competitorList;
        this.scoreFrequencyFrame = new JFrame("Score Frequency");
        scoreFrequencyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        scoreFrequencyFrame.setLocationRelativeTo(null);
        scoreFrequencyFrame.setSize(500, 400);

        JPanel panel = new JPanel();
        scoreFrequencyFrame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel numCompetitorsLabel = new JLabel("Number of Competitors: " + competitorList.getSize());
        numCompetitorsLabel.setFont(new Font(numCompetitorsLabel.getFont().getName(), Font.BOLD, 20));
        panel.add(numCompetitorsLabel);

        JTextArea reportFrequencyArea = new JTextArea();
        reportFrequencyArea.setText(competitorList.getScoreFrequency());
        reportFrequencyArea.setFont(new Font(reportFrequencyArea.getFont().getName(), Font.PLAIN, 18));
        panel.add(reportFrequencyArea);

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        closeButton.addActionListener(e -> scoreFrequencyFrame.dispose());
        panel.add(closeButton);

        scoreFrequencyFrame.setVisible(true);
    }
}




