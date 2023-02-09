package GUI;

import SMMCompetitor.Competitor;
import SMMCompetitor.CompetitorList;
import SMMCompetitor.Name;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CompetitorTable {
    public DefaultTableModel tableModel;
    public CompetitorList competitorList;
    public JFrame frame;
    private JTable table;
    public Competitor competitor;

    public CompetitorTable(CompetitorList competitorList) {
        this.competitorList = competitorList;
//      Competitor selectedCompetitor = competitorList.getAtIndex();
        createGUI(competitorList.prepareData());
    }

    public void createGUI(List<Object[]> data) {
        frame = new JFrame("Competitor Table Board");
        frame.setFont(new Font("Arial", Font.PLAIN, 40));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel subtitlePanel = new JPanel();
        subtitlePanel.setLayout(new BorderLayout());
        JLabel subtitle = new JLabel("Competitor Table");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 40));
        subtitlePanel.add(subtitle, BorderLayout.NORTH);
        mainPanel.add(subtitlePanel, BorderLayout.NORTH);

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BorderLayout());
        filterPanel.setBorder(new TitledBorder("Filter By Level"));
        String[] filterOptions = {"All", "Beginner", "Standard", "Veteran", "Ultimate"};
        JComboBox<String> filterDropdown = new JComboBox<>(filterOptions);
        filterDropdown.setFont(new Font("Arial", Font.PLAIN, 18));
        filterPanel.add(filterDropdown, BorderLayout.EAST);
        mainPanel.add(filterPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton frequencyButton = new JButton("Score Frequency");
        bottomPanel.add(frequencyButton);
//        mainPanel.add(frequencyButton, BorderLayout.SOUTH);

        JPanel searchPanel = new JPanel();
        JLabel searchLabel = new JLabel("Enter competitor number: ");
        JTextField searchField = new JTextField(10);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.SOUTH);

        JButton fullShortDetails = new JButton("View Full and Short Details");
        searchPanel.add(fullShortDetails);

        JButton addCompetitor = new JButton("Add Competitor");
        searchPanel.add(addCompetitor);

//      bottomPanel.add(fullShortDetails);
        bottomPanel.add(searchPanel);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Create the table model with column names
        String[] columns = {"Competitor Number", "Full Name", "Age", "Level", "Country", "Scores", "Gender", "Overall Score"};
        tableModel = new DefaultTableModel(columns, 0);

        // Add data to the table model
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
        frame.add(mainPanel);

        // Create a JPanel and add it to the JFrame
        JPanel panel = new JPanel();
        mainPanel.setFont(new Font("Arial", Font.PLAIN, 100));
        mainPanel.add(panel, BorderLayout.CENTER);

        tableModel.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row > 0 && column > 0) {
                    Competitor selectedCompetitor = competitorList.getAtIndex(row);
                    String value = (String) tableModel.getValueAt(row, column);

                    switch (tableModel.getColumnName(column)) {
                        case "Full Name":
                            selectedCompetitor.setName(new Name(value));
                            break;
                        case "Age":
                            selectedCompetitor.setAge(Integer.parseInt(value));
                            break;
                        case "Level":
                            selectedCompetitor.setCompLevel(value);
                            break;
                        case "Country":
                            selectedCompetitor.setCountry(value);
                            break;
                        case "Scores":
                            String[] scoreOutput = value.substring(1, value.length() - 1).split(",");
                            selectedCompetitor.setScores(scoreOutput);
                            break;
                        case "Gender":
                            selectedCompetitor.setGender(value);
                            break;
                        case "Overall Score":
                            selectedCompetitor.getOverallScore();
                    }
                }
            }
        });

        // Create the table and add it to the panel
        table = new JTable(tableModel);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 800));
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Arial", Font.PLAIN, 14));

        table.setAutoCreateRowSorter(true);
        mainPanel.add(new JScrollPane(table));

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
                    int row = table.rowAtPoint(e.getPoint());
                    table.getSelectionModel().setSelectionInterval(row, row);

                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem edit = new JMenuItem("Edit Competitor");
                    JMenuItem shortLongDetails = new JMenuItem("View Short and Long Details");
                    popup.add(edit);
                    popup.add(shortLongDetails);
                    table.setComponentPopupMenu(popup);

                    popup.show(e.getComponent(), e.getX(), e.getY());

                    edit.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            int selectedRowInModel = table.convertRowIndexToModel(table.getSelectedRow());
                            EditCompetitor editFrame = null;
                            try {
                                editFrame = new EditCompetitor(competitorList, selectedRowInModel, tableModel);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            editFrame.setVisible(true);
                        }
                    });

                    shortLongDetails.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int selectedRowInModel = table.convertRowIndexToModel(table.getSelectedRow());
                            GetDetails getDetails = new GetDetails(competitorList, selectedRowInModel);
                            getDetails.setVisible(true);
                        }
                    });
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = searchField.getText();
                boolean found = false;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 0).equals(input)) {
                        table.setRowSelectionInterval(i, i);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(null, "No competitor found with number " + input);
                }
            }
        });

        addCompetitor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCompetitor addCompetitorFrame = new AddCompetitor(competitorList, tableModel);
                table.repaint();
            }
        });

        TableRowSorter<DefaultTableModel> tableRowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(tableRowSorter);

        filterDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) filterDropdown.getSelectedItem();

                if (!"All".equals(selectedOption)) {
                    // Use the tableRowSorter to only display rows with "Level" equal to the selectedOption
                    tableRowSorter.setRowFilter(RowFilter.regexFilter("^" + selectedOption + "$", 3));
                } else {
                    // Reset the filter to display all rows if "All" is selected
                    tableRowSorter.setRowFilter(null);
                }
            }
        });

        frequencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScoreFrequency scoreFrequencyFrame = new ScoreFrequency(competitorList);
            }
        });

        fullShortDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String competitorNumber = searchField.getText();
                Competitor competitor = competitorList.findById(competitorNumber);
                if (competitor != null) {
                    JOptionPane.showMessageDialog(null, "Short Details: " + competitor.getShortDetails() +
                            "\n\nLong Details: " + competitor.getFullDetails(), "Competitor Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Competitor not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set the size and location of the JFrame
        frame.pack();
        frame.setLocationRelativeTo(null);

        // Show the JFrame
        frame.setVisible(true);
    }
}
