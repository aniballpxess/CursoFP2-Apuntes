package edu.dam2.ad.ejercicios.emojiapi;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
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

    // To customize the emojis font
    private static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); // GraphEnv entity to check for available fonts
    private static String staticFontsPath = "C:\\CursoFP2\\ASIGNATURAS\\AD\\PRACTICA\\Proyectos\\materiales\\src\\main\\resources\\Noto_Emoji\\static";
    private static String emojisFontFileName = "NotoEmoji-Medium.ttf";
    private static File emojisFontFile = new File(staticFontsPath, emojisFontFileName);
    // Predefined categories
    protected static final String[] CATEGORIES = {
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

        // Create Display Panel (Shows Emojis Filtered)
        emojiPanel = new JPanel(new GridLayout(0, 10, 5, 5)); // Grid layout (6 emojis per row)
        scrollPane = new JScrollPane(emojiPanel);

        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Load initial emojis
        loadEmojis(CATEGORIES[0]);
    }

    // Fetch emojis from API or cache to the display
    private void loadEmojis(String category) {
        emojiPanel.removeAll(); // Cleans emojis
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

    // Displays all the emojis in the list into the Display Panel
    private void displayEmojis(List<Emoji> emojis) {
        Font emojiFont = new Font(getEmojiFont(), Font.PLAIN, 24); // Gets the font for the emojis
        emojiPanel.removeAll();
        for (Emoji emoji : emojis) {
            // JLabel used to contain each emoji
            JLabel emojiLabel = new JLabel(emoji.getCharacter());
            emojiLabel.setFont(emojiFont);
            emojiLabel.setHorizontalAlignment(SwingConstants.CENTER);
            emojiLabel.setToolTipText(emoji.getName());
            // Add mouse click listener for copying to clipboard
            emojiLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    copyToClipboard(emoji.getCharacter());
                }
            });
            emojiPanel.add(emojiLabel);
        }
        emojiPanel.revalidate();
        emojiPanel.repaint();
    }

    // Selects an apropiate font for the emojis
    private String getEmojiFont() {;
        String[] fontNames = ge.getAvailableFontFamilyNames();
        for (String fontName : fontNames) {
            String fontFamilyName = fontName.toLowerCase();
            if (fontFamilyName.contains("noto")) {
                return fontFamilyName;
            }
        }
        return "monospace";
    }

    // Copies emoji to clipboard and notifies the user
    private void copyToClipboard(String emoji) {
        StringSelection selection = new StringSelection(emoji);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        JOptionPane.showMessageDialog(this, "Copied: " + emoji);
    }

    public static void main(String[] args) throws IOException, FontFormatException {
        Font emojisFont = Font.createFont(Font.TRUETYPE_FONT, emojisFontFile);
        ge.registerFont(emojisFont);
        SwingUtilities.invokeLater(() -> new EmojiApp().setVisible(true));
    }
}