package GUI;

import SMMCompetitor.Competitor;
import SMMCompetitor.CompetitorList;

import javax.swing.*;
import java.awt.*;

public class GetDetails extends JFrame {
    private JTextArea shortDetailsField;
    private JTextArea longDetailsField;
    public CompetitorList competitorList;
    public Competitor competitor;

    public GetDetails(CompetitorList competitorList, int row) {
        this.competitorList = competitorList;
        Competitor selectedCompetitor = competitorList.getAtIndex(row);
            this.competitor = selectedCompetitor;

            setTitle("Details");
            setSize(400, 300);
            setFont(new Font("Times New Romans", Font.PLAIN, 30));
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(7, 2));

            JLabel shortTitleLabel = new JLabel("Short Details");
            shortTitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(shortTitleLabel);

            shortDetailsField = new JTextArea(selectedCompetitor.getShortDetails());
            panel.add(shortDetailsField);

            JLabel longTitleLabel = new JLabel("Long Details");
            longTitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(longTitleLabel);

            longDetailsField = new JTextArea(selectedCompetitor.getFullDetails());

            panel.add(longDetailsField);

            add(panel);
        }
}
