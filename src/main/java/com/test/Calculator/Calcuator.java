package com.test.Calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.test.Calculator.operations.OperationsManager;

import history.History;
import history.SessionHistory;

public class Calcuator {
	
	private Display display;
	private Shell shell;
    private Text firstNumberText;
    private Combo operationCombo;
    private Text secondNumberText;
    
    private Button autoCalculateCheckButton;
    private Label autoCalculateCheckButtonDescription;
    
    private History history;
    private OperationsManager operationsManager;
    
    
	private Composite operationsComposite;
	private Composite buttonsComposite;
	private Composite resultComposite;
	
	private Button calculateButton;
	private Label resultLabel;
	private Label resultLabelDescription;
	
	
	
    public Calcuator() 
    {
    	
    	history = new SessionHistory();
		
		operationsManager = new OperationsManager(history);		
		
		display = Display.getDefault();
        shell = new Shell(display);
        FillLayout shellLayout = new FillLayout(SWT.VERTICAL);
		shell.setLayout(shellLayout);
        
        operationsComposite = new Composite(shell,SWT.NONE);
        
        RowLayout operationsLayout = new RowLayout(SWT.HORIZONTAL);
        operationsLayout.center = true;
        operationsLayout.justify = true;
		operationsComposite.setLayout(operationsLayout);
        
        
        firstNumberText = new Text(operationsComposite, SWT.NONE);
        operationCombo = new Combo(operationsComposite, SWT.READ_ONLY|SWT.DROP_DOWN);
        secondNumberText = new Text(operationsComposite, SWT.NONE);

        operationCombo.setItems(operationsManager.getOperationKeysArray());
        operationCombo.select(0);
        ModifyListener listener = e->{
        	if(autoCalculateCheckButton.getSelection()) {
        	showResult();
        	}
        };
		operationCombo.addModifyListener(listener);
        firstNumberText.addModifyListener(listener);
        secondNumberText.addModifyListener(listener);
        
        
        buttonsComposite = new Composite(shell,SWT.NONE);
        
        RowLayout buttonsLayout = new RowLayout(SWT.HORIZONTAL);
        buttonsLayout.center = true;
        buttonsLayout.justify = true;
		buttonsComposite.setLayout(buttonsLayout);
        
        autoCalculateCheckButton = new Button(buttonsComposite,SWT.CHECK);
        
        autoCalculateCheckButton.setSelection(true);
        autoCalculateCheckButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if(autoCalculateCheckButton.getSelection()) 
				{
					showResult();
				}else 
				{
					resultLabel.setText("");
				}
			}
        	
		});
        
        autoCalculateCheckButtonDescription = new Label(buttonsComposite,SWT.NONE);
        autoCalculateCheckButtonDescription.setText("Calculate on the fly");
        calculateButton = new Button(buttonsComposite, SWT.PUSH);
        calculateButton.setText("Calculate");
        
        calculateButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				showResult();
			}
		});
        
        resultComposite = new Composite(shell,SWT.NONE);
        
        RowLayout resultLayout = new RowLayout(SWT.HORIZONTAL);
        resultLayout.center = true;
        resultLayout.justify = true;
		resultComposite.setLayout(resultLayout);
        
        resultLabelDescription = new Label(resultComposite,SWT.NONE);
        resultLabelDescription.setText("Result: ");
        resultLabel = new Label(resultComposite,SWT.NONE);
        
    }
    
    
    private void showResult()
    {
    	String firstNumberString = firstNumberText.getText();
 		String secondNumberString = secondNumberText.getText();
 		int selectionIndex = operationCombo.getSelectionIndex();
 		if(!StringUtils.isEmpty(firstNumberString) && !StringUtils.isEmpty(secondNumberString) && selectionIndex>=0) 
 		{
 			String resultString = operationsManager.getResult(firstNumberString, secondNumberString, selectionIndex);
 			resultLabel.setText(resultString);
 			resultComposite.pack();
 		}else
 		{
 			resultLabel.setText("");
 			resultComposite.pack();
 		}
    }
    
    public void run() 
    {
        firstNumberText.pack();
        operationCombo.pack();
        secondNumberText.pack();
        operationsComposite.pack();
        autoCalculateCheckButton.pack();
        autoCalculateCheckButtonDescription.pack();
        calculateButton.pack();
        buttonsComposite.pack();
        resultLabel.pack();
        resultLabelDescription.pack();
        resultComposite.pack();
        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
    
    public void dispose() 
    {
    	if(!shell.isDisposed()) 
    	{
    		shell.dispose();
    	}
    	
    	display.dispose();
    }
}
