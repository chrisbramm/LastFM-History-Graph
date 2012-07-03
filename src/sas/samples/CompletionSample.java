/*package sas.samples;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

*//**
 * An example on how to use the {@link AutoCompleteDocument}.
 * 
 * @see AutoCompleteDocument
 * @see CompletionService
 * 
 * @author Samuel Sjoberg, http://samuelsjoberg.com
 * @version 1.0.0
 *//*
public class CompletionSample implements Runnable {

    *//**
     * Create the GUI and display it to the world.
     *//*
    public void run() {
        // Create the completion service.
        NameService nameService = new NameService();

        // Create the input field.
        JTextField input = new JTextField();

        // Create the auto completing document model with a reference to the
        // service and the input field.
        Document autoCompleteDocument = new AutoCompleteDocument(nameService,
                input);

        // Set the auto completing document as the document model on our input
        // field.
        input.setDocument(autoCompleteDocument);
        
        // Below we just create a frame and display the GUI.

        JFrame frame = new JFrame("Autocompletion sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel description = new JLabel();
        description.setText("<html><b>Autocompletion sample for the most<br>"
                + " popular baby names (2008)</b><br><br>"
                + nameService.toString().replace("\n", "<br>") + "<br>");

        JComponent contentPane = (JComponent) frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(input, BorderLayout.SOUTH);
        contentPane.add(description, BorderLayout.CENTER);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.pack();
        frame.setVisible(true);
    }

    *//**
     * Application entry point.
     * 
     * @param args
     *            not in use
     *//*
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new CompletionSample());
//    }

    *//**
     * A simple {@link CompletionService} providing completion for most popular
     * baby names 2008.
     *//*
   
}
*/