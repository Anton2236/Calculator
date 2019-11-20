package com.test.calculator.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import com.test.calculator.history.History;
import com.test.calculator.history.SessionHistory;
import com.test.calculator.operations.OperationsManager;

public class SwingApplication extends JFrame {

    private static SwingApplication swingApplication;

    /**
     * Creates instance of Swing calculator application
     */
    private SwingApplication() {

        JTabbedPane tabbedPane = new JTabbedPane();

        History history = new SessionHistory();

        OperationsManager operationsManager = new OperationsManager(history);

        tabbedPane.addTab("Calculator", new SwingCalculatorView(operationsManager));
        tabbedPane.addTab("History", new SwingHistoryView(history));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container pane = getContentPane();

        pane.add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Starts the swing calculator application
     */
    public static void run(Display display, Point location) {
        EventQueue.invokeLater(() -> {
            swingApplication = new SwingApplication();
            swingApplication.setVisible(true);
            swingApplication.setTitle("Calculator");
            swingApplication.setSize(400, 300);
            swingApplication.setMinimumSize(new Dimension(400, 300));
            swingApplication.setLocation(new java.awt.Point(location.x, location.y));

        });
        while (swingApplication == null || !swingApplication.isDisplayable()) {
            display.sleep();
        }

        while (swingApplication.isDisplayable()) {
            display.sleep();
        }
    }
}