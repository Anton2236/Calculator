package com.test.Calculator.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.peer.ButtonPeer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DocumentFilter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;

import com.test.Calculator.StringUtils;
import com.test.Calculator.operations.OperationsManager;

public class SwingCalculatorView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OperationsManager operationsManager;

	private JTextField firstNumberText;
	private JTextField secondNumberText;

	private JTextField resultText;
	private JComboBox<String> operationsComboBox;

	private JCheckBox autocalculateCheckBox;

	private JButton calculateButton;

	public SwingCalculatorView(OperationsManager operationsManager) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.operationsManager = operationsManager;

		JPanel operationsPanel = createOperationsPanel();

		JPanel buttonsPanel = createButtonsPanel();
		JPanel resultPanel = createResultPanel();

		Dimension rigidAreaSize = new Dimension(5, 20);

		add(Box.createVerticalGlue());
		add(operationsPanel);
		add(Box.createRigidArea(rigidAreaSize));
		add(buttonsPanel);
		add(Box.createVerticalGlue());
		add(resultPanel);
		add(Box.createVerticalGlue());

	}

	private JPanel createResultPanel() {
		JPanel resultPanel = new JPanel();

		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.LINE_AXIS));

		Dimension rigidAreaSize = new Dimension(5, 20);

		resultText = new JTextField(25);

		resultText.setEditable(false);

		resultText.setMaximumSize(new Dimension(Integer.MAX_VALUE, resultText.getPreferredSize().height));

		resultPanel.add(Box.createRigidArea(rigidAreaSize));
		resultPanel.add(new JLabel("Result:"));
		resultPanel.add(Box.createRigidArea(rigidAreaSize));
		resultPanel.add(resultText);
		resultPanel.add(Box.createRigidArea(rigidAreaSize));

		return resultPanel;

	}

	private JPanel createButtonsPanel() {
		JPanel buttonsPanel = new JPanel();

		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));

		Dimension rigidAreaSize = new Dimension(50, 20);

		autocalculateCheckBox = new JCheckBox("Calculate on the fly");
		autocalculateCheckBox.addActionListener(e -> {
			if (autocalculateCheckBox.isSelected()) {
				showResult();
				calculateButton.setEnabled(false);
			} else {
				resultText.setText("");
				calculateButton.setEnabled(true);
			}
		});
		autocalculateCheckBox.setSelected(true);
		buttonsPanel.add(autocalculateCheckBox);
		buttonsPanel.add(Box.createRigidArea(rigidAreaSize));
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener((e) -> {
			showResult();
		});
		calculateButton.setEnabled(false);
		buttonsPanel.add(calculateButton);
		return buttonsPanel;
	}

	private JPanel createOperationsPanel() {
		JPanel operationsPanel = new JPanel();

		operationsPanel.setLayout(new BoxLayout(operationsPanel, BoxLayout.X_AXIS));

		firstNumberText = new JTextField(12);
		firstNumberText.setMaximumSize(new Dimension(300, firstNumberText.getPreferredSize().height));

		secondNumberText = new JTextField(12);
		secondNumberText.setMaximumSize(new Dimension(300, secondNumberText.getPreferredSize().height));

		SimpleDocumentListener documentListener = (e) -> {
			if (autocalculateCheckBox.isSelected()) {
				showResult();
			}
		};

		firstNumberText.getDocument().addDocumentListener(documentListener);
		secondNumberText.getDocument().addDocumentListener(documentListener);
		
		((PlainDocument) firstNumberText.getDocument()).setDocumentFilter(new DecimalDocumentFilter());
		((PlainDocument) secondNumberText.getDocument()).setDocumentFilter(new DecimalDocumentFilter());

		operationsComboBox = new JComboBox<String>(operationsManager.getOperationKeysArray());
		operationsComboBox.setEditable(false);

		operationsComboBox.addActionListener(e -> {
			if (autocalculateCheckBox.isSelected()) {
				showResult();
			}
		});

		Dimension comboBoxSize = new Dimension(50, 20);
		operationsComboBox.setMaximumSize(comboBoxSize);
		operationsComboBox.setPreferredSize(comboBoxSize);
		operationsComboBox.setMinimumSize(comboBoxSize);

		Dimension rigidAreaSize = new Dimension(10, 20);
		operationsPanel.add(Box.createRigidArea(rigidAreaSize));
		operationsPanel.add(firstNumberText, BorderLayout.LINE_START);
		operationsPanel.add(Box.createRigidArea(rigidAreaSize));
		operationsPanel.add(operationsComboBox, BorderLayout.CENTER);
		operationsPanel.add(Box.createRigidArea(rigidAreaSize));
		operationsPanel.add(secondNumberText, BorderLayout.LINE_END);
		operationsPanel.add(Box.createRigidArea(rigidAreaSize));

		return operationsPanel;
	}

	private void showResult() {
		String firstNumberString = firstNumberText.getText();
		String secondNumberString = secondNumberText.getText();
		int selectionIndex = operationsComboBox.getSelectedIndex();
		String resultString = "";
		if (!StringUtils.isEmpty(firstNumberString) && !StringUtils.isEmpty(secondNumberString)) {
			double firstNumber = Double.parseDouble(firstNumberString);
			double secondNumber = Double.parseDouble(secondNumberString);
			double result = operationsManager.getResult(firstNumber, secondNumber, selectionIndex);
			resultString = StringUtils.formatDouble(result);

		}
		resultText.setText(resultString);
	}

}
