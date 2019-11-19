package com.test.calculator.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import com.test.calculator.StringUtils;
import com.test.calculator.operations.Operation;
import com.test.calculator.operations.OperationSubject;
import com.test.calculator.operations.OperationsManager;

/**
 * View for all calculator elements
 * 
 * @author SAIvanov
 *
 */
public class CalcuatorView extends Composite {

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
     * @param tabFolder - tab folder
     * @param operationsManager - operations manager
     * @param style - SWT style
     */
    public CalcuatorView(TabFolder tabFolder, OperationsManager operationsManager, int style) {
        super(tabFolder, style);
        this.operationsManager = operationsManager;

        GridLayout layout = new GridLayout(5, true);
        layout.marginLeft = 10;
        layout.marginRight = 10;

        setLayout(layout);

        createOperationsPanel();

        createButtonsPanel();

        createResultPanel();
    }

    private void createOperationsPanel() {

        VerifyListener verifyListener = new VerifyListener() {

            public void verifyText(VerifyEvent e) {
                Text text = (Text) e.getSource();

                String oldString = text.getText();
                String newString = oldString.substring(0, e.start) + e.text + oldString.substring(e.end);
                boolean isDouble = true;
                try {
                    new OperationSubject(newString);
                } catch (NumberFormatException exception) {
                    isDouble = false;
                }
                e.doit = isDouble || StringUtils.isEmptyOrMinus(newString);

            }
        };

        GridData textGridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
        textGridData.horizontalSpan = 2;
        GridData comboBoxGridData = new GridData(SWT.CENTER, SWT.CENTER, false, true);

        firstNumberText = new Text(this, SWT.BORDER);
        operationCombo = new Combo(this, SWT.READ_ONLY | SWT.DROP_DOWN);
        secondNumberText = new Text(this, SWT.BORDER);

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

        autoCalculateCheckButton = new Button(this, SWT.CHECK);

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

        calculateButton = new Button(this, SWT.PUSH);
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

        resultLabel = new Label(this, SWT.NONE);
        resultLabel.setText("Result: ");
        resultLabel.setLayoutData(labelGridData);

        GridData resultGridData = new GridData(SWT.FILL, SWT.CENTER, false, true);
        resultGridData.horizontalSpan = 4;
        resultText = new Text(this, SWT.BORDER | SWT.READ_ONLY);
        resultText.setLayoutData(resultGridData);
    }

    private void showResult() {
        String firstNumberString = firstNumberText.getText();
        String secondNumberString = secondNumberText.getText();
        String resultString = "";
        int selectionIndex = operationCombo.getSelectionIndex();
        if (!StringUtils.isEmptyOrMinus(firstNumberString) && !StringUtils.isEmptyOrMinus(secondNumberString)
                && selectionIndex >= 0) {
            OperationSubject firstNumber = new OperationSubject(firstNumberString);
            OperationSubject secondNumber = new OperationSubject(secondNumberString);
            Operation operation = operationsManager.getOperation(selectionIndex);

            try {
                OperationSubject result = operationsManager.calculateResult(firstNumber, secondNumber, operation);
                resultString = (result.toString());
            } catch (ArithmeticException exception) {
                resultString = "Error: " + exception.getMessage();
            }
        }
        resultText.setText(resultString);
    }
}
