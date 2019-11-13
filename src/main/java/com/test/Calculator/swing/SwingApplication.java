package com.test.Calculator.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.test.Calculator.history.History;
import com.test.Calculator.history.SessionHistory;
import com.test.Calculator.operations.OperationsManager;

public class SwingApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SwingApplication() {

		JTabbedPane tabbedPane = new JTabbedPane();

		History history = new SessionHistory();

		OperationsManager operationsManager = new OperationsManager(history);

		tabbedPane.addTab("Calculator", new SwingCalculatorView(operationsManager));
		tabbedPane.addTab("History", new SwingHistoryView(history));

		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container pane = getContentPane();
		
		pane.add(tabbedPane,BorderLayout.CENTER);
	}

	/**
	 * Starts the swing calculator app
	 */
	public static void run() {
		EventQueue.invokeLater(() -> {
			SwingApplication swingApplication = new SwingApplication();
			swingApplication.setVisible(true);
			swingApplication.setTitle("Calculator");
			swingApplication.setSize(400, 300);
			swingApplication.setMinimumSize(new Dimension(300,200));
		});
	}
}
