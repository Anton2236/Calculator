package com.test.Calculator.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.peer.ButtonPeer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		add(Box.createVerticalGlue());
		add(operationsPanel);
		add(Box.createVerticalGlue());
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

		resultText.setMaximumSize(
				new Dimension(resultText.getPreferredSize().width * 2, resultText.getPreferredSize().height));

		resultPanel.add(Box.createHorizontalGlue());
		resultPanel.add(new JLabel("Result:"));
		resultPanel.add(Box.createRigidArea(rigidAreaSize));
		resultPanel.add(resultText);
		resultPanel.add(Box.createHorizontalGlue());

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
			} else {
				resultText.setText("");
			}
		});
		autocalculateCheckBox.setSelected(true);
		buttonsPanel.add(autocalculateCheckBox);
		buttonsPanel.add(Box.createRigidArea(rigidAreaSize));
		calculateButton = new JButton("Calculate");
		calculateButton.addActionListener((e) -> {
			showResult();
		});

		buttonsPanel.add(calculateButton);
		return buttonsPanel;
	}

	private JPanel createOperationsPanel() {
		JPanel operationsPanel = new JPanel();

		operationsPanel.setLayout(new BoxLayout(operationsPanel, BoxLayout.X_AXIS));

		firstNumberText = new JTextField(12);
		firstNumberText.setMaximumSize(firstNumberText.getPreferredSize());
		firstNumberText.setMinimumSize(firstNumberText.getPreferredSize());

		secondNumberText = new JTextField(12);
		secondNumberText.setMaximumSize(secondNumberText.getPreferredSize());
		secondNumberText.setMinimumSize(secondNumberText.getPreferredSize());

		SimpleDocumentListener documentListener = (e) -> {
			if (autocalculateCheckBox.isSelected()) {
				showResult();
			}
		};

		firstNumberText.getDocument().addDocumentListener(documentListener);
		secondNumberText.getDocument().addDocumentListener(documentListener);

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
		operationsPanel.add(Box.createHorizontalGlue());
		operationsPanel.add(firstNumberText, BorderLayout.LINE_START);
		operationsPanel.add(Box.createRigidArea(rigidAreaSize));
		operationsPanel.add(operationsComboBox, BorderLayout.CENTER);
		operationsPanel.add(Box.createRigidArea(rigidAreaSize));
		operationsPanel.add(secondNumberText, BorderLayout.LINE_END);
		operationsPanel.add(Box.createHorizontalGlue());

		return operationsPanel;
	}

	private void showResult() {
		String firstNumberString = firstNumberText.getText();
		String secondNumberString = secondNumberText.getText();
		int selectionIndex = operationsComboBox.getSelectedIndex();
		if (!StringUtils.isEmpty(firstNumberString) && !StringUtils.isEmpty(secondNumberString)
				&& selectionIndex >= 0) {
			String resultString = operationsManager.getResult(firstNumberString, secondNumberString, selectionIndex);
			resultText.setText(resultString);
		} else {
			resultText.setText("");
		}
	}

}
