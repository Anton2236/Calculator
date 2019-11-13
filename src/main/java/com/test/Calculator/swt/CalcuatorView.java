package com.test.Calculator.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

import com.test.Calculator.StringUtils;
import com.test.Calculator.history.History;
import com.test.Calculator.history.SessionHistory;
import com.test.Calculator.operations.OperationsManager;

public class CalcuatorView {

	private Composite calculatorComposite;
	private Text firstNumberText;
	private Combo operationCombo;
	private Text secondNumberText;

	private Button autoCalculateCheckButton;
	private OperationsManager operationsManager;

	private Button calculateButton;
	private Text resultText;
	private Label resultLabel;

	/**
	 * Creates view to show calculator in the tab
	 * 
	 * @param tabFolder
	 * @param operationsManager
	 */
	public CalcuatorView(TabFolder tabFolder, OperationsManager operationsManager) {

		this.operationsManager = operationsManager;

		calculatorComposite = new Composite(tabFolder, SWT.NONE);
		GridLayout layout = new GridLayout(5, true);
		layout.marginLeft = 10;
		layout.marginRight = 10;

		calculatorComposite.setLayout(layout);

		createOperationsPanel();

		createButtonsPanel();

		createResultPanel();
	}

	private void createOperationsPanel() {

		VerifyListener verifyListener = new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				Text text = (Text) e.getSource();

				String oldString = text.getText();
				String newString = oldString.substring(0, e.start) + e.text + oldString.substring(e.end);
				boolean isDouble = true;
				try {
					Double.parseDouble(newString);
				} catch (Exception exception) {
					isDouble = false;
				}
				e.doit = isDouble || newString.equals("");

			}
		};

		GridData textGridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
		textGridData.horizontalSpan = 2;
		GridData comboBoxGridData = new GridData(SWT.CENTER, SWT.CENTER, false, true);

		firstNumberText = new Text(calculatorComposite, SWT.BORDER);
		operationCombo = new Combo(calculatorComposite, SWT.READ_ONLY | SWT.DROP_DOWN);
		secondNumberText = new Text(calculatorComposite, SWT.BORDER);

		operationCombo.setLayoutData(comboBoxGridData);
		firstNumberText.setLayoutData(textGridData);
		secondNumberText.setLayoutData(textGridData);

		firstNumberText.addVerifyListener(verifyListener);
		secondNumberText.addVerifyListener(verifyListener);

		operationCombo.setItems(operationsManager.getOperationKeysArray());
		operationCombo.select(0);
		ModifyListener listener = e -> {
			if (autoCalculateCheckButton.getSelection()) {
				showResult();
			}
		};
		operationCombo.addModifyListener(listener);
		firstNumberText.addModifyListener(listener);
		secondNumberText.addModifyListener(listener);
	}

	private void createButtonsPanel() {

		autoCalculateCheckButton = new Button(calculatorComposite, SWT.CHECK);

		autoCalculateCheckButton.setSelection(true);
		autoCalculateCheckButton.setText("Calculate on the fly");
		autoCalculateCheckButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (autoCalculateCheckButton.getSelection()) {
					showResult();
					calculateButton.setEnabled(false);
				} else {
					resultText.setText("");
					calculateButton.setEnabled(true);
				}
			}

		});

		GridData checkBoxGridData = new GridData(SWT.CENTER, SWT.CENTER, false, true);
		checkBoxGridData.horizontalSpan = 3;
		autoCalculateCheckButton.setLayoutData(checkBoxGridData);

		calculateButton = new Button(calculatorComposite, SWT.PUSH);
		calculateButton.setText("Calculate");
		calculateButton.setEnabled(false);
		calculateButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				showResult();
			}
		});

		GridData buttonGridData = new GridData(SWT.LEFT, SWT.CENTER, true, true);
		buttonGridData.horizontalSpan = 2;
		calculateButton.setLayoutData(buttonGridData);
	}

	private void createResultPanel() {
		GridData labelGridData = new GridData(SWT.CENTER, SWT.CENTER, false, true);

		resultLabel = new Label(calculatorComposite, SWT.NONE);
		resultLabel.setText("Result: ");
		resultLabel.setLayoutData(labelGridData);

		GridData resultGridData = new GridData(SWT.FILL, SWT.CENTER, false, true);
		resultGridData.horizontalSpan = 4;
		resultText = new Text(calculatorComposite, SWT.BORDER | SWT.READ_ONLY);
		resultText.setLayoutData(resultGridData);
	}

	private void showResult() {
		String firstNumberString = firstNumberText.getText();
		String secondNumberString = secondNumberText.getText();
		String resultString = "";
		int selectionIndex = operationCombo.getSelectionIndex();
		if (!StringUtils.isEmpty(firstNumberString) && !StringUtils.isEmpty(secondNumberString)
				&& selectionIndex >= 0) {
			double firstNumber = Double.parseDouble(firstNumberString);

			double secondNumber = Double.parseDouble(secondNumberString);

			double result = operationsManager.getResult(firstNumber, secondNumber, selectionIndex);
			resultString = StringUtils.formatDouble(result);
		}
		resultText.setText(resultString);
	}

	/**
	 * Disposes calculator views
	 */
	public void dispose() {
		if (!calculatorComposite.isDisposed()) {
			calculatorComposite.dispose();
		}
	}

	/**
	 * Return calculator`s parent control
	 * 
	 * @return
	 */
	public Control getControl() {
		return calculatorComposite;
	}
}
