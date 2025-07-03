    package com.example.wordprocessor;

    import javax.swing.*;
    import javax.swing.border.EmptyBorder;
    import javax.swing.text.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.awt.event.KeyEvent;
    import java.awt.event.KeyAdapter;
    import java.io.*;

    public class WordProcessor extends JFrame {

        private JTextPane textPane;
        private JComboBox<String> fontComboBox;
        private JComboBox<Integer> fontSizeComboBox;
        private JComboBox<String> lineSpacingComboBox;
        private JTextField marginField;
        private JTextField tabSpacingField;
        private String currentFilePath;
        private DocumentManager documentManager;
        private Highlighter.HighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
        private Highlighter.HighlightPainter replaceHighlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.CYAN);
        private Object currentHighlight = null;

        public WordProcessor() {
            initUI();
        }

        /**
         * Initializes the user interface components for the Word Processor application.
         * This method sets up the text editing area, menus, toolbars, window properties,
         * key bindings, and default values.
         */
        private void initUI() {
            // Create a JTextPane for text editing and set it to wrap text
            textPane = new JTextPane();
            textPane.setEditorKit(new WrapEditorKit()); // Use a custom EditorKit for text wrapping
            documentManager = new DocumentManager(); // Initialize document manager (assuming it handles document operations)

            // Create the menu bar and toolbar for the application
            createMenuBar();
            createToolBar();

            // Create a scrollable pane for the text area and add it to the center of the frame
            JScrollPane scrollPane = new JScrollPane(textPane);
            add(scrollPane, BorderLayout.CENTER);

            // Set the title, size, close operation, and center the window on the screen
            setTitle("Word Processor");
            setSize(800, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Set up key bindings for keyboard shortcuts
            addKeyBindings();

            // Set default values or initial state for components
            setDefaultValues();
        }


        /**
         * Creates and sets up the menu bar for the application.
         * This method adds File menu with New, Open, Save, Save as DOCX, and Exit options.
         * Each menu item has an associated action listener for performing respective actions.
         */
        private void createMenuBar() {
            // Create a new menu bar instance
            JMenuBar menuBar = new JMenuBar();

            // Create File menu and its items
            JMenu fileMenu = new JMenu("File");

            // Menu item for creating a new file
            JMenuItem newFileItem = new JMenuItem("New");
            newFileItem.addActionListener((event) -> newFile()); // Action listener to handle "New" action

            // Menu item for opening an existing file
            JMenuItem openFileItem = new JMenuItem("Open");
            openFileItem.addActionListener((event) -> openFile()); // Action listener to handle "Open" action

            // Menu item for saving the current file
            JMenuItem saveFileItem = new JMenuItem("Save");
            saveFileItem.addActionListener((event) -> saveFile()); // Action listener to handle "Save" action

            // Menu item for saving the current file as a DOCX document
            JMenuItem saveAsDocxItem = new JMenuItem("Save as DOCX");
            saveAsDocxItem.addActionListener((event) -> saveAsDocx()); // Action listener to handle "Save as DOCX" action

            // Menu item for exiting the application
            JMenuItem exitItem = new JMenuItem("Exit");
            exitItem.addActionListener((event) -> System.exit(0)); // Action listener to exit the application

            // Add menu items to the File menu
            fileMenu.add(newFileItem);
            fileMenu.add(openFileItem);
            fileMenu.add(saveFileItem);
            fileMenu.add(saveAsDocxItem);
            fileMenu.addSeparator(); // Separator line in the menu
            fileMenu.add(exitItem);

            // Add the File menu to the menu bar
            menuBar.add(fileMenu);

            // Set the menu bar to be the application's menu bar
            setJMenuBar(menuBar);
        }

        /**
         * Creates and sets up the toolbar for the Word Processor application.
         * This method adds components for font selection, font size selection,
         * text formatting (bold, italic, underline), text color selection,
         * line spacing adjustment, margin setting, and tab spacing setting.
         */
        private void createToolBar() {
            // Create a new toolbar instance
            JToolBar toolBar = new JToolBar();

            // Font selection dropdown
            fontComboBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getAvailableFontFamilyNames());
            fontComboBox.addActionListener((event) -> changeFont()); // Action listener for font selection

            // Font size selection dropdown
            fontSizeComboBox = new JComboBox<>(new Integer[]{8, 10, 12, 14, 16, 18, 20, 24, 28, 32, 36, 40, 48, 56, 64, 72});
            fontSizeComboBox.addActionListener((event) -> changeFontSize()); // Action listener for font size selection

            // Bold button
            JButton boldButton = new JButton("B");
            boldButton.setFont(new Font("Arial", Font.BOLD, 12));
            boldButton.setPreferredSize(new Dimension(40, 40));
            boldButton.addActionListener((event) -> toggleBold()); // Action listener for toggling bold text

            // Italic button
            JButton italicButton = new JButton("I");
            italicButton.setFont(new Font("Arial", Font.ITALIC, 12));
            italicButton.setPreferredSize(new Dimension(40, 40));
            italicButton.addActionListener((event) -> toggleItalic()); // Action listener for toggling italic text

            // Underline button
            JButton underlineButton = new JButton("U");
            underlineButton.setFont(new Font("Arial", Font.PLAIN, 12));
            underlineButton.setPreferredSize(new Dimension(40, 40));
            underlineButton.addActionListener((event) -> toggleUnderline()); // Action listener for toggling underline text

            // Font color selection button
            JButton fontColorButton = new JButton("A");
            fontColorButton.setFont(new Font("Arial", Font.PLAIN, 12));
            fontColorButton.setPreferredSize(new Dimension(40, 40));
            fontColorButton.addActionListener((event) -> chooseFontColor()); // Action listener for choosing font color

            // Highlight color selection button
            JButton highlightButton = new JButton("A");
            highlightButton.setFont(new Font("Arial", Font.PLAIN, 12));
            highlightButton.setForeground(Color.BLUE);
            highlightButton.setPreferredSize(new Dimension(40, 40));
            highlightButton.addActionListener((event) -> chooseHighlightColor()); // Action listener for choosing highlight color

            // Line spacing selection dropdown
            lineSpacingComboBox = new JComboBox<>(new String[]{"Single", "1.5x", "Double"});
            lineSpacingComboBox.addActionListener((event) -> changeLineSpacing()); // Action listener for changing line spacing

            // Margin input field
            marginField = new JTextField(2);
            marginField.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        setMargins(); // Action listener for setting margins on Enter key press
                    }
                }
            });

            // Tab spacing input field
            tabSpacingField = new JTextField(2);
            tabSpacingField.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        setTabSpacing(); // Action listener for setting tab spacing on Enter key press
                    }
                }
            });

            // Add components to the toolbar
            toolBar.add(fontComboBox);
            toolBar.add(fontSizeComboBox);
            toolBar.add(boldButton);
            toolBar.add(italicButton);
            toolBar.add(underlineButton);
            toolBar.add(fontColorButton);
            toolBar.add(highlightButton);
            toolBar.add(lineSpacingComboBox);
            toolBar.add(new JLabel("Margins:"));
            toolBar.add(marginField);
            toolBar.add(new JLabel("Tab Spacing:"));
            toolBar.add(tabSpacingField);

            // Add the toolbar to the top (north) of the frame's layout
            add(toolBar, BorderLayout.NORTH);
        }

        private void newFile() {
            textPane.setText("");
            currentFilePath = null; // Reset the current file path
        }

        private void openFile() {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    documentManager.openDocument(textPane, fileChooser.getSelectedFile().getAbsolutePath());
                    currentFilePath = fileChooser.getSelectedFile().getAbsolutePath(); // Update current file path
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "File could not be opened", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        /**
         * Saves the content of the text pane to a file.
         * If no current file path is set, prompts the user to save the file as a new file.
         * If a current file path is set, directly saves the content to that file path.
         * Displays an error message dialog if the file could not be saved.
         */
        private void saveFile() {
            if (currentFilePath == null) {
                // If no current file path is set, prompt user to save the file as a new file
                saveFileAs();
            } else {
                try {
                    // Attempt to write the text pane content to the current file path
                    textPane.write(new FileWriter(currentFilePath));
                } catch (IOException ex) {
                    // Catch and handle IOException if the file could not be saved
                    JOptionPane.showMessageDialog(this, "File could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        /**
         * Prompts the user to choose a file path and saves the content of the text pane to that file.
         * Updates the current file path if the file is successfully saved.
         * Displays an error message dialog if the file could not be saved.
         */
        private void saveFileAs() {
            // Create a file chooser dialog for saving files
            JFileChooser fileChooser = new JFileChooser();

            // Show the save file dialog and capture the user's choice
            int option = fileChooser.showSaveDialog(this);

            // If the user selects a file and clicks "Save"
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    // Write the content of the text pane to the selected file
                    textPane.write(new FileWriter(fileChooser.getSelectedFile()));

                    // Update the current file path to the absolute path of the selected file
                    currentFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                } catch (IOException ex) {
                    // Catch and handle IOException if the file could not be saved
                    JOptionPane.showMessageDialog(this, "File could not be saved", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }


        /**
         * Saves the content of the text pane as a DOCX file using the document manager.
         * Delegates the saving process to the document manager.
         */
        private void saveAsDocx() {
            documentManager.saveAsDocx(textPane);
        }


        /**
         * Changes the font of the text in the text pane based on the selected font and font size.
         * Retrieves the selected font name and font size from their respective combo boxes,
         * then sets the font of the text pane accordingly.
         */
        private void changeFont() {
            // Retrieve the selected font name from the font combo box
            String selectedFont = (String) fontComboBox.getSelectedItem();

            // Retrieve the selected font size from the font size combo box
            int fontSize = (int) fontSizeComboBox.getSelectedItem();

            // Check if a font is selected
            if (selectedFont != null) {
                // Set the font of the text pane with the selected font name and size
                textPane.setFont(new Font(selectedFont, Font.PLAIN, fontSize));
            }
        }


        /**
         * Changes the font size of the text in the text pane based on the selected size.
         * Retrieves the selected font size from the font size combo box,
         * then sets the font of the text pane with the current font family and the selected size.
         */
        private void changeFontSize() {
            // Retrieve the current selected font family from the font combo box
            String selectedFont = (String) fontComboBox.getSelectedItem();

            // Retrieve the selected font size from the font size combo box
            int fontSize = (int) fontSizeComboBox.getSelectedItem();

            // Check if a font is selected
            if (selectedFont != null) {
                // Set the font of the text pane with the current font family, plain style, and the selected font size
                textPane.setFont(new Font(selectedFont, Font.PLAIN, fontSize));
            }
        }

        /**
         * Changes the line spacing of the text in the text pane based on the selected spacing option.
         * Retrieves the selected line spacing option from the line spacing combo box,
         * then adjusts the paragraph attributes of the text pane accordingly.
         */
        private void changeLineSpacing() {
            // Retrieve the selected line spacing option from the combo box
            String selectedSpacing = (String) lineSpacingComboBox.getSelectedItem();

            // Initialize the spacing factor
            float spacing = 0.0f;

            // Determine the spacing factor based on the selected option
            if ("1.5x".equals(selectedSpacing)) {
                spacing = 0.5f; // 1.5 times line spacing
            } else if ("Double".equals(selectedSpacing)) {
                spacing = 1.0f; // Double line spacing
            }

            // Create a mutable attribute set to modify paragraph attributes
            MutableAttributeSet attrs = new SimpleAttributeSet();

            // Set the line spacing attribute in the attribute set
            StyleConstants.setLineSpacing(attrs, spacing);

            // Apply the modified paragraph attributes to the text pane
            textPane.setParagraphAttributes(attrs, false);
        }

        /**
         * Sets the margins (padding) around the text in the text pane.
         * Retrieves the margin value from the margin input field,
         * then sets the border of the text pane to create margins of equal size on all sides.
         * Displays an error message if the entered margin value is invalid (not a positive integer).
         */
        private void setMargins() {
            try {
                // Retrieve the margin value from the margin input field
                int margin = Integer.parseInt(marginField.getText());

                // Check if the margin value is valid (positive integer)
                if (margin < 0) {
                    throw new NumberFormatException();
                }

                // Set the border of the text pane to create margins of equal size on all sides
                textPane.setBorder(new EmptyBorder(margin, margin, margin, margin));

            } catch (NumberFormatException ex) {
                // Catch and handle NumberFormatException if the entered margin value is invalid
                JOptionPane.showMessageDialog(this, "Invalid margin value", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


        /**
         * Sets the tab spacing for the text in the text pane.
         * Retrieves the tab spacing value from the tab spacing input field,
         * calculates the tab stops based on the spacing value,
         * and sets the paragraph attributes of the text pane to apply the calculated tab stops.
         * Displays an error message if the entered tab spacing value is invalid (not a positive integer).
         */
        private void setTabSpacing() {
            try {
                // Retrieve the tab spacing value from the tab spacing input field
                int tabSpacing = Integer.parseInt(tabSpacingField.getText());

                // Check if the tab spacing value is valid (positive integer)
                if (tabSpacing < 0) {
                    throw new NumberFormatException();
                }

                // Calculate the width of a single tab stop based on the tab spacing value
                FontMetrics fm = textPane.getFontMetrics(textPane.getFont());
                int tabWidth = fm.charWidth('m') * tabSpacing;

                // Create an array of tab stops based on the calculated width
                TabStop[] tabs = new TabStop[10]; // Create 10 tab stops for demonstration purposes
                for (int j = 0; j < tabs.length; j++) {
                    tabs[j] = new TabStop((j + 1) * tabWidth); // Set each tab stop at multiples of tabWidth
                }

                // Create a TabSet with the array of tab stops
                TabSet tabSet = new TabSet(tabs);

                // Create a SimpleAttributeSet to modify paragraph attributes
                SimpleAttributeSet attributes = new SimpleAttributeSet();

                // Set the tab set attribute in the attribute set
                StyleConstants.setTabSet(attributes, tabSet);

                // Apply the modified paragraph attributes to the text pane
                textPane.setParagraphAttributes(attributes, false);

            } catch (NumberFormatException ex) {
                // Catch and handle NumberFormatException if the entered tab spacing value is invalid
                JOptionPane.showMessageDialog(this, "Invalid tab spacing value", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        /**
         * Opens a color chooser dialog to allow the user to select a font color.
         * Sets the selected color as the foreground color of the text in the text pane.
         */
        private void chooseFontColor() {
            // Open a color chooser dialog with the current foreground color of the text pane as the initial color
            Color color = JColorChooser.showDialog(this, "Choose Font Color", textPane.getForeground());

            // If a color is selected from the color chooser dialog
            if (color != null) {
                // Create a SimpleAttributeSet to modify character attributes
                SimpleAttributeSet attributes = new SimpleAttributeSet();

                // Set the foreground color attribute in the attribute set
                StyleConstants.setForeground(attributes, color);

                // Apply the modified character attributes to the text pane
                textPane.setCharacterAttributes(attributes, false);
            }
        }

        /**
         * Opens a color chooser dialog to allow the user to select a highlight color.
         * Sets the selected color as the background highlight color of the selected text in the text pane.
         */
        private void chooseHighlightColor() {
            // Open a color chooser dialog with yellow as the initial highlight color
            Color color = JColorChooser.showDialog(this, "Choose Highlight Color", Color.YELLOW);

            // If a color is selected from the color chooser dialog
            if (color != null) {
                // Create a SimpleAttributeSet to modify character attributes
                SimpleAttributeSet attributes = new SimpleAttributeSet();

                // Set the background color attribute in the attribute set
                StyleConstants.setBackground(attributes, color);

                // Apply the modified character attributes to the selected text in the text pane
                textPane.setCharacterAttributes(attributes, false);
            }
        }

        /**
         * Toggles the bold attribute of the selected text in the text pane.
         * If text is selected, checks the current bold state of the selection.
         * Sets or removes the bold attribute based on the current state.
         */
        private void toggleBold() {
            // Get the styled document of the text pane
            StyledDocument doc = textPane.getStyledDocument();

            // Get the start and end positions of the selected text
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();

            // Check if there is a selection (start and end positions are different)
            if (start != end) {
                // Retrieve the attributes of the character element at the start position
                Element element = doc.getCharacterElement(start);
                AttributeSet as = element.getAttributes();

                // Check the current bold state of the selected text
                boolean isBold = StyleConstants.isBold(as);

                // Create a new SimpleAttributeSet to modify character attributes
                SimpleAttributeSet sas = new SimpleAttributeSet();

                // Toggle the bold attribute: set to bold if it's not bold, remove if it is bold
                StyleConstants.setBold(sas, !isBold);

                // Apply the modified character attributes to the selected text range
                doc.setCharacterAttributes(start, end - start, sas, false);
            }
        }

        /**
         * Toggles the italic attribute of the selected text in the text pane.
         * If text is selected, checks the current italic state of the selection.
         * Sets or removes the italic attribute based on the current state.
         */
        private void toggleItalic() {
            // Get the styled document of the text pane
            StyledDocument doc = textPane.getStyledDocument();

            // Get the start and end positions of the selected text
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();

            // Check if there is a selection (start and end positions are different)
            if (start != end) {
                // Retrieve the attributes of the character element at the start position
                Element element = doc.getCharacterElement(start);
                AttributeSet as = element.getAttributes();

                // Check the current italic state of the selected text
                boolean isItalic = StyleConstants.isItalic(as);

                // Create a new SimpleAttributeSet to modify character attributes
                SimpleAttributeSet sas = new SimpleAttributeSet();

                // Toggle the italic attribute: set to italic if it's not italic, remove if it is italic
                StyleConstants.setItalic(sas, !isItalic);

                // Apply the modified character attributes to the selected text range
                doc.setCharacterAttributes(start, end - start, sas, false);
            }
        }
        /**
         * Toggles the underline attribute of the selected text in the text pane.
         * If text is selected, checks the current underline state of the selection.
         * Sets or removes the underline attribute based on the current state.
         */
        private void toggleUnderline() {
            // Get the styled document of the text pane
            StyledDocument doc = textPane.getStyledDocument();

            // Get the start and end positions of the selected text
            int start = textPane.getSelectionStart();
            int end = textPane.getSelectionEnd();

            // Check if there is a selection (start and end positions are different)
            if (start != end) {
                // Retrieve the attributes of the character element at the start position
                Element element = doc.getCharacterElement(start);
                AttributeSet as = element.getAttributes();

                // Check the current underline state of the selected text
                boolean isUnderline = StyleConstants.isUnderline(as);

                // Create a new SimpleAttributeSet to modify character attributes
                SimpleAttributeSet sas = new SimpleAttributeSet();

                // Toggle the underline attribute: set to underline if it's not underlined, remove if it is underlined
                StyleConstants.setUnderline(sas, !isUnderline);

                // Apply the modified character attributes to the selected text range
                doc.setCharacterAttributes(start, end - start, sas, false);
            }
        }


        /**
         * Configures key bindings for various actions in the text pane:
         * - Bold (Ctrl + B)
         * - Italic (Ctrl + I)
         * - Underline (Ctrl + U)
         * - Save (Ctrl + S)
         * - Save As (Ctrl + Shift + S)
         * - Find (Ctrl + F)
         * - Replace (Ctrl + H)
         */
        private void addKeyBindings() {
            InputMap inputMap = textPane.getInputMap(JComponent.WHEN_FOCUSED);
            ActionMap actionMap = textPane.getActionMap();

            // Bold
            KeyStroke boldKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK);
            inputMap.put(boldKeyStroke, "toggle-bold");
            actionMap.put("toggle-bold", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleBold();
                }
            });

            // Italic
            KeyStroke italicKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK);
            inputMap.put(italicKeyStroke, "toggle-italic");
            actionMap.put("toggle-italic", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleItalic();
                }
            });

            // Underline
            KeyStroke underlineKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_U, KeyEvent.CTRL_DOWN_MASK);
            inputMap.put(underlineKeyStroke, "toggle-underline");
            actionMap.put("toggle-underline", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    toggleUnderline();
                }
            });

            // Save
            KeyStroke saveKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
            inputMap.put(saveKeyStroke, "save-file");
            actionMap.put("save-file", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveFile();
                }
            });

            // Save As
            KeyStroke saveAsKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK);
            inputMap.put(saveAsKeyStroke, "save-file-as");
            actionMap.put("save-file-as", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveFileAs();
                }
            });

            // Find
            KeyStroke findKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
            inputMap.put(findKeyStroke, "find-text");
            actionMap.put("find-text", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    findText();
                }
            });

            // Replace
            KeyStroke replaceKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK);
            inputMap.put(replaceKeyStroke, "replace-text");
            actionMap.put("replace-text", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    replaceText();
                }
            });
        }

        /**
         * Displays a dialog box to find and highlight text within the text pane.
         * Allows the user to enter text to search for and highlights all occurrences
         * of the text in the text pane.
         */
        private void findText() {
            // Create components for the find dialog
            JTextField findField = new JTextField(20);
            JButton closeButton = new JButton("Close");
            JDialog findDialog = new JDialog(this, "Find Text", false);
            findDialog.setLayout(new BorderLayout());

            // Panel for text field and label
            JPanel panel = new JPanel(new FlowLayout());
            panel.add(new JLabel("Find:"));
            panel.add(findField);

            // Add components to the find dialog
            findDialog.add(panel, BorderLayout.CENTER);
            findDialog.add(closeButton, BorderLayout.SOUTH);

            // Action listener for close button: clears highlights and closes the dialog
            closeButton.addActionListener(e -> {
                unhighlightAll();
                findDialog.dispose();
            });

            // Action listener for find field: highlights found text when Enter is pressed
            findField.addActionListener(e -> highlightFoundText(findField.getText()));

            // Display the find dialog
            findDialog.pack();
            findDialog.setLocationRelativeTo(this);
            findDialog.setVisible(true);
        }

        /**
         * Displays a dialog box to find and replace text within the text pane.
         * Allows the user to enter text to search for and specify text to replace
         * it with. Provides options to replace next occurrence, replace all
         * occurrences, and close the dialog.
         */
        private void replaceText() {
            // Create components for the replace dialog
            JTextField findField = new JTextField(10);
            JTextField replaceField = new JTextField(10);
            JButton replaceAllButton = new JButton("Replace All");
            JButton closeButton = new JButton("Close");
            JDialog replaceDialog = new JDialog(this, "Replace Text", false);
            replaceDialog.setLayout(new BorderLayout());

            // Panel for find and replace fields
            JPanel panel = new JPanel(new FlowLayout());
            panel.add(new JLabel("Find:"));
            panel.add(findField);
            panel.add(new JLabel("Replace with:"));
            panel.add(replaceField);

            // Add find and replace fields to the replace dialog
            replaceDialog.add(panel, BorderLayout.CENTER);

            // Panel for buttons
            JPanel buttonsPanel = new JPanel(new FlowLayout());
            buttonsPanel.add(replaceAllButton);
            buttonsPanel.add(closeButton);

            // Add buttons panel to the replace dialog
            replaceDialog.add(buttonsPanel, BorderLayout.SOUTH);

            // Action listener for replace field: replaces next occurrence when Enter is pressed
            replaceField.addActionListener(e -> replaceNext(findField.getText(), replaceField.getText()));

            // Action listener for replace all button: replaces all occurrences
            replaceAllButton.addActionListener(e -> replaceAll(findField.getText(), replaceField.getText()));

            // Action listener for close button: clears highlights and closes the dialog
            closeButton.addActionListener(e -> {
                unhighlightAll();
                replaceDialog.dispose();
            });

            // Display the replace dialog
            replaceDialog.pack();
            replaceDialog.setLocationRelativeTo(this);
            replaceDialog.setVisible(true);
        }
        /**
         * Highlights all occurrences of the specified search text within the text pane.
         * Clears any existing highlights before applying new highlights.
         *
         * @param searchText The text to search for and highlight within the text pane.
         */
        private void highlightFoundText(String searchText) {
            // Clear existing highlights
            unhighlightAll();

            // Get the highlighter for the text pane
            Highlighter highlighter = textPane.getHighlighter();

            // Get the content of the text pane as lowercase
            String content = textPane.getText().toLowerCase();
            searchText = searchText.toLowerCase();

            // Find and highlight all occurrences of the search text
            int index = content.indexOf(searchText);
            while (index >= 0) {
                try {
                    int end = index + searchText.length();
                    highlighter.addHighlight(index, end, highlightPainter);
                    index = content.indexOf(searchText, end);
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }

        /**
         * Replaces the next occurrence of the specified search text with the given replacement text
         * within the text pane. If no text is currently highlighted, highlights all occurrences of
         * the search text before replacing the first occurrence found.
         *
         * @param searchText  The text to search for and replace within the text pane.
         * @param replaceText The text to replace the found occurrences of searchText.
         */
        private void replaceNext(String searchText, String replaceText) {
            // If no current highlight, highlight all occurrences of searchText
            if (currentHighlight == null) {
                unhighlightAll(); // Clear existing highlights
                highlightFoundText(searchText); // Highlight all occurrences of searchText
                currentHighlight = getFirstHighlight(); // Set current highlight to the first occurrence
            }

            // If there is a current highlight, replace it with replaceText
            if (currentHighlight != null) {
                try {
                    Highlighter.Highlight highlight = (Highlighter.Highlight) currentHighlight;
                    int start = highlight.getStartOffset();
                    int end = highlight.getEndOffset();
                    textPane.getDocument().remove(start, end - start); // Remove highlighted text
                    textPane.getDocument().insertString(start, replaceText, null); // Insert replacement text
                    unhighlightAll(); // Clear all highlights
                    highlightFoundText(searchText); // Highlight all occurrences of searchText again
                    currentHighlight = getFirstHighlight(); // Update current highlight to the first occurrence
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        }
        /**
         * Replaces all occurrences of the specified search text with the given replacement text
         * within the text pane.
         *
         * @param searchText  The text to search for and replace within the text pane.
         * @param replaceText The text to replace all occurrences of searchText.
         */
        private void replaceAll(String searchText, String replaceText) {
            unhighlightAll(); // Clear existing highlights
            highlightFoundText(searchText); // Highlight all occurrences of searchText
            Highlighter highlighter = textPane.getHighlighter();
            Highlighter.Highlight[] highlights = highlighter.getHighlights();

            // Iterate through each highlighted occurrence and replace with replaceText
            for (Highlighter.Highlight highlight : highlights) {
                try {
                    int start = highlight.getStartOffset();
                    int end = highlight.getEndOffset();
                    textPane.getDocument().remove(start, end - start); // Remove highlighted text
                    textPane.getDocument().insertString(start, replaceText, null); // Insert replacement text
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }
            }

            unhighlightAll(); // Clear all highlights after replacement
        }

        /**
         * Retrieves the first highlight in the text pane.
         *
         * @return The first highlight in the text pane, or null if no highlights are present.
         */
        private Highlighter.Highlight getFirstHighlight() {
            Highlighter highlighter = textPane.getHighlighter();
            Highlighter.Highlight[] highlights = highlighter.getHighlights();
            return highlights.length > 0 ? highlights[0] : null; // Return the first highlight or null if none
        }

        /**
         * Clears all text highlights in the text pane and resets the current highlight to null.
         * This method removes any visual highlighting applied to text within the pane.
         */
        private void unhighlightAll() {
            Highlighter highlighter = textPane.getHighlighter();
            highlighter.removeAllHighlights(); // Remove all highlights from the text pane
            currentHighlight = null; // Reset current highlight reference
        }

        /**
         * Sets default values for margin and tab spacing fields in the user interface.
         * This method initializes the margin field to "0", tab spacing field to "4",
         * and applies these values immediately to the text pane.
         */
        private void setDefaultValues() {
            marginField.setText("0"); // Set margin field to default value "0"
            tabSpacingField.setText("4"); // Set tab spacing field to default value "4"
            setMargins(); // Apply default margin value to the text pane
            setTabSpacing(); // Apply default tab spacing value to the text pane
        }


        /**
         * Entry point for the Word Processor application.
         * Creates an instance of WordProcessor and makes it visible on the screen.
         *
         * @param args Command-line arguments (not used in this application).
         */
        public static void main(String[] args) {
            EventQueue.invokeLater(() -> {
                WordProcessor ex = new WordProcessor(); // Create a new instance of WordProcessor
                ex.setVisible(true); // Make the WordProcessor window visible
            });
        }

        /**
         * Custom editor kit for wrapping text in a JTextPane.
         * Uses a custom ViewFactory (WrapColumnFactory) to create views for different types of elements.
         */
        private static class WrapEditorKit extends StyledEditorKit {
            private ViewFactory defaultFactory = new WrapColumnFactory(); // Default ViewFactory for this editor kit

            @Override
            public ViewFactory getViewFactory() {
                return defaultFactory; // Return the default ViewFactory
            }

            /**
             * Custom ViewFactory that creates views for different element types.
             */
            private static class WrapColumnFactory implements ViewFactory {
                @Override
                public View create(Element elem) {
                    String kind = elem.getName();
                    if (kind != null) {
                        // Check the type of element and create corresponding views
                        if (kind.equals(AbstractDocument.ContentElementName)) {
                            return new WrapLabelView(elem); // WrapLabelView for content elements
                        } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                            return new ParagraphView(elem); // ParagraphView for paragraph elements
                        } else if (kind.equals(AbstractDocument.SectionElementName)) {
                            return new BoxView(elem, View.Y_AXIS); // BoxView for section elements
                        } else if (kind.equals(StyleConstants.ComponentElementName)) {
                            return new ComponentView(elem); // ComponentView for component elements
                        } else if (kind.equals(StyleConstants.IconElementName)) {
                            return new IconView(elem); // IconView for icon elements
                        }
                    }
                    return new LabelView(elem); // Default LabelView for unknown elements
                }
            }

            /**
             * Custom LabelView that allows wrapping of text content.
             */
            private static class WrapLabelView extends LabelView {
                public WrapLabelView(Element elem) {
                    super(elem);
                }

                @Override
                public float getMinimumSpan(int axis) {
                    switch (axis) {
                        case View.X_AXIS:
                            return 0; // Allow wrapping in the X_AXIS direction
                        case View.Y_AXIS:
                            return super.getMinimumSpan(axis); // Use default behavior in the Y_AXIS direction
                        default:
                            throw new IllegalArgumentException("Invalid axis: " + axis);
                    }
                }
            }
        }

        /**
         * Utility class for managing document operations such as opening and saving documents.
         */
        private static class DocumentManager {

            /**
             * Opens a text document and loads its content into the specified JTextPane.
             *
             * @param textPane  The JTextPane to which the document content will be loaded.
             * @param filePath  The file path of the document to be opened.
             * @throws IOException If an I/O error occurs while reading the document.
             */
            public void openDocument(JTextPane textPane, String filePath) throws IOException {
                FileReader reader = new FileReader(filePath); // Create a FileReader for the specified file path
                BufferedReader br = new BufferedReader(reader); // Wrap FileReader in a BufferedReader for efficient reading
                textPane.read(br, null); // Load the document content into the JTextPane
                br.close(); // Close the BufferedReader to release resources
            }

            /**
             * Saves the content of the JTextPane as a DOCX (Microsoft Word) document.
             *
             * @param textPane The JTextPane containing the document content to be saved.
             */
            public void saveAsDocx(JTextPane textPane) {
                // TODO: Implement DOCX saving functionality
                // Steps to implement:
                // 1. Extract text content from the JTextPane.
                // 2. Convert the extracted text to DOCX format.
                // 3. Save the converted DOCX content to a file or perform further processing.
                // Example steps:
                // - Extract text: String textContent = textPane.getText();
                // - Convert and save as DOCX using a library like Apache POI or other DOCX generation libraries.
                // - Handle exceptions and display error messages if saving fails.
            }
        }
    }
