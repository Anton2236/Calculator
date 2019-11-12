package com.test.Calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
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

import com.test.Calculator.history.History;
import com.test.Calculator.history.SessionHistory;
import com.test.Calculator.operations.OperationsManager;

public class Calcuator {

	private Composite calculatorComposite;
	private Text firstNumberText;
	private Combo operationCombo;
	private Text secondNumberText;

	private Button autoCalculateCheckButton;
	private OperationsManager operationsManager;

	private Composite operationsComposite;
	private Composite buttonsComposite;
	private Composite resultComposite;

	private Button calculateButton;
	private Label resultLabel;
	private Label resultLabelDescription;

	public Calcuator(TabFolder tabFolder, OperationsManager operationsManager) {

		this.operationsManager = operationsManager;

		calculatorComposite = new Composite(tabFolder, SWT.NONE);
		FillLayout layout = new FillLayout(SWT.VERTICAL);
		calculatorComposite.setLayout(layout);
		
		createOperationsComposite();
		
		createButtonsComposite();

		createResultComposite();
	}

	private void createOperationsComposite() {
		

		operationsComposite = new Composite(calculatorComposite, SWT.NONE);

		
		RowLayout operationsLayout = new RowLayout(SWT.HORIZONTAL);
		operationsLayout.spacing = 15;
		operationsLayout.center = true;
		operationsLayout.justify = true;
		operationsComposite.setLayout(operationsLayout);

		firstNumberText = new Text(operationsComposite, SWT.BORDER);
		operationCombo = new Combo(operationsComposite, SWT.READ_ONLY | SWT.DROP_DOWN);
		secondNumberText = new Text(operationsComposite, SWT.BORDER);

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
	
	

	private void createButtonsComposite() {
		buttonsComposite = new Composite(calculatorComposite, SWT.NONE);

		RowLayout buttonsLayout = new RowLayout(SWT.HORIZONTAL);
		buttonsLayout.center = true;
		buttonsLayout.justify = true;
		buttonsComposite.setLayout(buttonsLayout);

		autoCalculateCheckButton = new Button(buttonsComposite, SWT.CHECK);

		autoCalculateCheckButton.setSelection(true);
		autoCalculateCheckButton.setText("Calculate on the fly");
		autoCalculateCheckButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (autoCalculateCheckButton.getSelection()) {
					showResult();
				} else {
					resultLabel.setText("");
				}
			}

		});

		calculateButton = new Button(buttonsComposite, SWT.PUSH);
		calculateButton.setText("Calculate");

		calculateButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				showResult();
			}
		});
	}
	
	

	private void createResultComposite() {
		resultComposite = new Composite(calculatorComposite, SWT.NONE);

		resultComposite.setLayout(new FormLayout());

		FormData resultDescFormData = new FormData();
		resultDescFormData.left = new FormAttachment(0,70);

		resultLabelDescription = new Label(resultComposite, SWT.NONE);
		resultLabelDescription.setText("Result: ");
		resultLabelDescription.setLayoutData(resultDescFormData);

		FormData resultFormData = new FormData();
		resultFormData.left = new FormAttachment(resultLabelDescription);
		resultLabel = new Label(resultComposite, SWT.NONE);
		resultLabel.setLayoutData(resultFormData);
	}

	private void showResult() {
		String firstNumberString = firstNumberText.getText();
		String secondNumberString = secondNumberText.getText();
		int selectionIndex = operationCombo.getSelectionIndex();
		if (!StringUtils.isEmpty(firstNumberString) && !StringUtils.isEmpty(secondNumberString)
				&& selectionIndex >= 0) {
			String resultString = operationsManager.getResult(firstNumberString, secondNumberString, selectionIndex);
			resultLabel.setText(resultString);
			resultComposite.pack();
		} else {
			resultLabel.setText("");
			resultComposite.pack();
		}
	}


	public void dispose() {
		if (!calculatorComposite.isDisposed()) {
			calculatorComposite.dispose();
		}
	}

	public Control getControl() {
		return calculatorComposite;
	}
}
