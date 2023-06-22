package com.amrita.jpl.cys21052.mp;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
/*
* Program reads the text in the text area and saves the text in the given name and format
* Load button also loads the given text file and shows in the text area
 */
public class TextEditor {
    private JFrame frame;
    private JTextArea textArea;
    private JButton saveButton;
    private JButton loadButton;
    /*
    * following is the structure of the given text area
    * and adds the nessecery buttons and field areas
     */
    public TextEditor() {
        frame = new JFrame("Text Editor");
        textArea = new JTextArea(50, 50);
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        // Configure frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new java.awt.FlowLayout());

        // Add components to the frame
        frame.getContentPane().add(new JScrollPane(textArea));
        frame.getContentPane().add(saveButton);
        frame.getContentPane().add(loadButton);

        // Add action listeners to the buttons
        saveButton.addActionListener(new SaveButtonListener());
        loadButton.addActionListener(new LoadButtonListener());

        frame.setVisible(true);
    }

    /*
    * The following function reads the text and saves to the given file
     */
    private class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String fileName = JOptionPane.showInputDialog(frame, "Enter file name:");
            if (fileName != null) {
                try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                    writer.println(textArea.getText());
                    JOptionPane.showMessageDialog(frame, "Saved successfully.");
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error!");
                }
            }
        }
    }

    /*
    * following function loads the give file and display it in the text editor
    * it allows to edit the text also
     */
    private class LoadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String fileName = JOptionPane.showInputDialog(frame, "Enter file name to load:");
            if (fileName != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                    textArea.setText(content.toString());
                    JOptionPane.showMessageDialog(frame, "loaded successfully.");
                }
                catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error loading file");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextEditor();
            }
        });
    }
}
