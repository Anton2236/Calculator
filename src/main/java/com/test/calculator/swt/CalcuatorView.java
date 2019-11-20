package com.test.calculator.swt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
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
import com.test.calculator.swt.utils.SelectionListener;

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

        VerifyListener verifyListener = (e) -> {
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
        addDisposeListener((e) -> {
            firstNumberText.removeVerifyListener(verifyListener);
            secondNumberText.removeVerifyListener(verifyListener);
        });

        operationCombo.setItems(operationsManager.getOperationKeysArray());
        operationCombo.select(0);
        ModifyListener modifyListener = e -> {
            if (autoCalculateCheckButton.getSelection()) {
                showResult();
            }
        };
        operationCombo.addModifyListener(modifyListener);
        firstNumberText.addModifyListener(modifyListener);
        secondNumberText.addModifyListener(modifyListener);
        addDisposeListener((e) -> {
            operationCombo.removeModifyListener(modifyListener);
            firstNumberText.removeModifyListener(modifyListener);
            secondNumberText.removeModifyListener(modifyListener);
        });
    }

    private void createButtonsPanel() {

        autoCalculateCheckButton = new Button(this, SWT.CHECK);

        autoCalculateCheckButton.setSelection(true);
        autoCalculateCheckButton.setText("Calculate on the fly");
        SelectionListener autoCalculateSelectionListener = (e) -> {

            if (autoCalculateCheckButton.getSelection()) {
                showResult();
                calculateButton.setEnabled(false);
            } else {
                resultText.setText("");
                calculateButton.setEnabled(true);
            }

        };
        autoCalculateCheckButton.addSelectionListener(autoCalculateSelectionListener);
        addDisposeListener((e) -> {
            autoCalculateCheckButton.removeSelectionListener(autoCalculateSelectionListener);
        });

        GridData checkBoxGridData = new GridData(SWT.CENTER, SWT.CENTER, false, true);
        checkBoxGridData.horizontalSpan = 3;
        autoCalculateCheckButton.setLayoutData(checkBoxGridData);

        calculateButton = new Button(this, SWT.PUSH);
        calculateButton.setText("Calculate");
        calculateButton.setEnabled(false);

        SelectionListener calculateListener = (e) -> {
            showResult();
        };
        calculateButton.addSelectionListener(calculateListener);
        addDisposeListener((e) -> {
            calculateButton.removeSelectionListener(calculateListener);
            System.out.println("removed");
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
