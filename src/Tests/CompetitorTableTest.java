//package Tests;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.awt.*;
//import java.util.List;
//
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.SwingConstants;
//import javax.swing.border.TitledBorder;
//import javax.swing.table.DefaultTableModel;
//
//class CompetitorTableTest {
//    @
//            Test
//    public void testCreateGUI() {
//        // Given
//        List<Object[]> data = // Prepare test data
//
//        // When
//        createGUI(data);
//
//        // Then
//        Object frame;
//        assertNotNull(frame);
//        assertEquals("Competitor Table Board", frame.getTitle());
//
//        JPanel mainPanel = (JPanel) frame.getContentPane().getComponent(0);
//        assertNotNull(mainPanel);
//        assertEquals(BorderLayout.class, mainPanel.getLayout().getClass());
//
//        JPanel subtitlePanel = (JPanel) mainPanel.getComponent(0);
//        assertNotNull(subtitlePanel);
//        assertEquals(BorderLayout.class, subtitlePanel.getLayout().getClass());
//
//        JLabel subtitle = (JLabel) subtitlePanel.getComponent(0);
//        assertNotNull(subtitle);
//        assertEquals("Competitor Table", subtitle.getText());
//        assertEquals(SwingConstants.CENTER, subtitle.getHorizontalAlignment());
//
//        JPanel filterPanel = (JPanel) mainPanel.getComponent(1);
//        assertNotNull(filterPanel);
//        assertEquals(BorderLayout.class, filterPanel.getLayout().getClass());
//        assertNotNull(filterPanel.getBorder());
//        assertEquals(TitledBorder.class, filterPanel.getBorder().getClass());
//        assertEquals("Filter By Level", ((TitledBorder) filterPanel.getBorder()).getTitle());
//
//        JComboBox<String> filterDropdown = (JComboBox<String>) filterPanel.getComponent(0);
//        assertNotNull(filterDropdown);
//        Object filterOptions = null;
//        assertEquals(filterOptions.length, filterDropdown.getItemCount());
//
//        JPanel searchPanel = (JPanel) mainPanel.getComponent(2);
//        assertNotNull(searchPanel);
//
//        JLabel searchLabel = (JLabel) searchPanel.getComponent(0);
//        assertNotNull(searchLabel);
//        assertEquals("Enter competitor number: ", searchLabel.getText());
//
//        JTextField searchField = (JTextField) searchPanel.getComponent(1);
//        assertNotNull(searchField);
//        assertEquals(10, searchField
//}