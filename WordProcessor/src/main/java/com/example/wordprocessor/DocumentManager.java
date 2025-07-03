package com.example.wordprocessor;

import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFRun.FontCharRange;
import java.awt.Color;

public class DocumentManager {
    /********************
     * Pre: None
     * Post: Opens and reads a document from the specified file path into the JTextPane
     * @param textPane The JTextPane to display the opened document
     * @param filePath The file path of the document to open
     ********************/
    public void openDocument(JTextPane textPane, String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            textPane.read(reader, null);
        }
    }

    /********************
     * Pre: None
     * Post: Saves the contents of JTextPane as a DOCX file
     * @param textPane The JTextPane containing the document to save
     ********************/
    public void saveAsDocx(JTextPane textPane) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(null);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().endsWith(".docx")) {
                file = new File(file.getAbsolutePath() + ".docx");
            }
            try (FileOutputStream out = new FileOutputStream(file)) {
                XWPFDocument doc = new XWPFDocument();
                StyledDocument styledDoc = textPane.getStyledDocument();

                for (int i = 0; i < styledDoc.getLength(); ) {
                    Element element = styledDoc.getCharacterElement(i);
                    AttributeSet as = element.getAttributes();
                    int start = element.getStartOffset();
                    int end = element.getEndOffset();

                    String text = styledDoc.getText(start, end - start);
                    XWPFParagraph paragraph = doc.createParagraph();
                    XWPFRun run = paragraph.createRun();
                    run.setText(text);

                    // Set font family
                    String fontFamily = StyleConstants.getFontFamily(as);
                    if (fontFamily != null) {
                        run.setFontFamily(fontFamily);
                    }

                    // Set font size
                    int fontSize = StyleConstants.getFontSize(as);
                    if (fontSize != 0) {
                        run.setFontSize(fontSize);
                    }

                    // Set bold
                    boolean isBold = StyleConstants.isBold(as);
                    run.setBold(isBold);

                    // Set italic
                    boolean isItalic = StyleConstants.isItalic(as);
                    run.setItalic(isItalic);

                    // Set underline
                    boolean isUnderline = StyleConstants.isUnderline(as);
                    if (isUnderline) {
                        run.setUnderline(UnderlinePatterns.SINGLE);
                    }

                    // Set foreground color
                    Color color = StyleConstants.getForeground(as);
                    if (color != null) {
                        run.setColor(String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue()));
                    }

                    i = end;
                }

                doc.write(out);
                JOptionPane.showMessageDialog(null, "Document saved as DOCX", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | BadLocationException ex) {
                JOptionPane.showMessageDialog(null, "Error saving document as DOCX", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

}
