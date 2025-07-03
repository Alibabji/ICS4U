# Graphical Word Processor Application

**Author:** Nick Jeong

## Files

- `pom.xml`
- `WrapEditorKit.java`
- `DocumentManager.java`
- `WordProcessor.java`

## Description

This project implements a simple word processor application in Java using Swing and Apache POI libraries. The application allows users to:
- Create, open, edit, and save text documents.
- Format text (font, size, bold, italic, underline, color).
- Adjust line spacing, margins, and tab spacing.
- Find and replace text with highlighting functionality.
- Save documents as DOCX files using Apache POI libraries.

The main components of the project include:
- `WordProcessor.java`: The main GUI class that integrates text editing functionalities and user interface components.
- `DocumentManager.java`: Manages document loading from and saving to files, including DOCX format conversion.
- `WrapEditorKit.java`: Custom editor kit to enable word wrapping in the text pane.
- `pom.xml`: Maven configuration file specifying dependencies, including Apache POI for DOCX handling.

## Keyboard Shortcuts

- **Ctrl + F**: Find text
- **Ctrl + H**: Replace text
- **Ctrl + S**: Save file
- **Ctrl + Shift + S**: Save file as
- **Ctrl + B**: Toggle bold
- **Ctrl + U**: Toggle underline
- **Ctrl + I**: Toggle italic

## Special Instructions

To run the application:
1. Ensure you have Java JDK 8 or higher installed.
2. Clone the project and import it into your preferred IDE.
3. Build the project using Maven (`mvn clean install`) to resolve dependencies.
4. Run the `WordProcessor` class (`com.example.wordprocessor.WordProcessor`) to launch the application.

Ensure that all dependencies specified in `pom.xml` are correctly resolved for proper functioning of the application, especially Apache POI dependencies for DOCX support.