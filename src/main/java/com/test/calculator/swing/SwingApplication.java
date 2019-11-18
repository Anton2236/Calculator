package com.test.calculator.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.eclipse.swt.graphics.Point;

import com.test.calculator.history.History;
import com.test.calculator.history.SessionHistory;
import com.test.calculator.operations.OperationsManager;

public class SwingApplication extends JFrame {

    /**
     * Creates instance of Swing calculator application
     */
    public SwingApplication() {

        JTabbedPane tabbedPane = new JTabbedPane();

        History history = new SessionHistory();

        OperationsManager operationsManager = new OperationsManager(history);

        tabbedPane.addTab("Calculator", new SwingCalculatorView(operationsManager));
        tabbedPane.addTab("History", new SwingHistoryView(history));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container pane = getContentPane();

        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Starts the swing calculator application
     */
    public static void run(Point location) {
        EventQueue.invokeLater(() -> {
            SwingApplication swingApplication = new SwingApplication();
            swingApplication.setVisible(true);
            swingApplication.setTitle("Calculator");
            swingApplication.setSize(300, 200);
            swingApplication.setMinimumSize(new Dimension(300, 200));
            swingApplication.setLocation(new java.awt.Point(location.x, location.y));
        });
    }
}
