import javax.swing.*;
import java.awt.*;

/** Swing front end for the SortedList (Lab 10). */
public class SortedListGUI extends JFrame {

    private final SortedList list = new SortedList();

    private final JTextField entryField  = new JTextField(15);
    private final JTextField searchField = new JTextField(15);
    private final JTextArea  display     = new JTextArea(18, 40);

    public SortedListGUI() {
        super("Sorted List - Binary Search");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // --- top: add + search controls ---
        JPanel controls = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add");
        addPanel.add(new JLabel("Add string:"));
        addPanel.add(entryField);
        addPanel.add(addButton);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Search for:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        controls.add(addPanel);
        controls.add(searchPanel);
        add(controls, BorderLayout.NORTH);

        // --- center: log of operations ---
        display.setEditable(false);
        display.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        add(new JScrollPane(display), BorderLayout.CENTER);

        // --- actions (Enter key works too) ---
        addButton.addActionListener(e -> doAdd());
        entryField.addActionListener(e -> doAdd());
        searchButton.addActionListener(e -> doSearch());
        searchField.addActionListener(e -> doSearch());

        pack();
        setLocationRelativeTo(null);
    }

    private void doAdd() {
        String s = entryField.getText().trim();
        if (s.isEmpty()) return;

        int index = list.add(s);
        display.append("ADD \"" + s + "\" -> inserted at index " + index + "\n");
        display.append("List now:\n" + list + "\n");
        entryField.setText("");
        entryField.requestFocusInWindow();
    }

    private void doSearch() {
        String s = searchField.getText().trim();
        if (s.isEmpty()) return;

        int result = list.binarySearch(s);
        if (result >= 0) {
            display.append("SEARCH \"" + s + "\" -> FOUND \"" + list.get(result)
                    + "\" at index " + result + "\n\n");
        } else {
            int where = -(result + 1);
            display.append("SEARCH \"" + s + "\" -> NOT FOUND; it would be inserted at index "
                    + where + "\n\n");
        }
        searchField.setText("");
        searchField.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SortedListGUI().setVisible(true));
    }
}