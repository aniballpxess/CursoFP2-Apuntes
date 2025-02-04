package edu.dam2.ad.ejercicios.emojiapi;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmojiApp extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JTextField searchField;
    private JPanel emojiPanel;
    private JScrollPane scrollPane;

    private List<Emoji> currentEmojis;  // Stores current category's emojis
    private Map<String, List<Emoji>> emojiCache = new HashMap<>();  // Cache to avoid repeated API calls

    // Predefined categories
    private static final String[] CATEGORIES = {
            "smileys-emotion", "food-drink", "animals-nature", "travel-places",
            "activities", "objects", "symbols", "flags"
    };

    public EmojiApp() {
        setTitle("Emoji Selector");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create Top Panel (Category + Search)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Category Dropdown
        categoryComboBox = new JComboBox<>(CATEGORIES);
        categoryComboBox.addActionListener(e -> loadEmojis((String) categoryComboBox.getSelectedItem()));

        // Search Box
        searchField = new JTextField(20);
        searchField.addActionListener(e -> filterEmojis(searchField.getText()));

        topPanel.add(new JLabel("Category:"));
        topPanel.add(categoryComboBox);
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);

        // Emoji Display Panel
        emojiPanel = new JPanel(new GridLayout(0, 6, 5, 5)); // Grid layout (6 emojis per row)
        scrollPane = new JScrollPane(emojiPanel);

        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Load initial emojis
        loadEmojis(CATEGORIES[0]);
    }

    // Fetch emojis from API or cache
    private void loadEmojis(String category) {
        emojiPanel.removeAll();
        if (emojiCache.containsKey(category)) {
            currentEmojis = emojiCache.get(category);
        } else {
            currentEmojis = EmojiAPI.fetchEmojisByCategory(category);
            emojiCache.put(category, currentEmojis); // Store in cache
        }
        displayEmojis(currentEmojis);
    }

    // Filter emojis based on search input
    private void filterEmojis(String query) {
        List<Emoji> filtered = currentEmojis.stream()
                .filter(emoji -> emoji.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        displayEmojis(filtered);
    }

    // Display emojis in a grid
    private void displayEmojis(List<Emoji> emojis) {
        emojiPanel.removeAll();
        for (Emoji emoji : emojis) {
            JButton emojiButton = new JButton(emoji.getCharacter());

            // Set font with fallback for proper emoji rendering
            Font emojiFont = new Font(getEmojiFont(), Font.PLAIN, 24);
            emojiButton.setFont(emojiFont);

            emojiButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            emojiButton.setToolTipText(emoji.getName());
            emojiButton.addActionListener(e -> copyToClipboard(emoji.getCharacter()));

            emojiPanel.add(emojiButton);
        }
        emojiPanel.revalidate();
        emojiPanel.repaint();
    }

    private String getEmojiFont() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "Segoe UI Emoji";
        } else if (os.contains("mac")) {
            return "Apple Color Emoji";
        } else {
            return "Noto Color Emoji"; // Default for Linux
        }
    }

    // Copy emoji to clipboard
    private void copyToClipboard(String emoji) {
        StringSelection selection = new StringSelection(emoji);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        JOptionPane.showMessageDialog(this, "Copied: " + emoji);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EmojiApp().setVisible(true));
    }
}