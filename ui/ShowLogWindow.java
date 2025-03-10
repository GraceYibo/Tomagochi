package ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Event;
import model.EventLog;

/**
 * Represents a screen printer for printing event log to screen.
 */
public class ShowLogWindow extends JFrame {
    
    private JTextArea logArea;

    // EFFECTS: shows the window of log
    public ShowLogWindow() {
        super("Event Log");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        // Initialize the text area to display logs
        logArea = new JTextArea(); 
        logArea.setEditable(false); 
    
        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        populateLogs(EventLog.getInstance());
    
        setVisible(true);
    }
    
    //EFFECTS: helper function to print log
    public void populateLogs(EventLog el) {
        for (Event e : el) {
            logArea.append(e.toString() + "\n\n"); 
        }
        repaint();
    }
}