/**
 * 
 */
package com.test.Calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.test.Calculator.history.History;
import com.test.Calculator.history.SessionHistory;
import com.test.Calculator.operations.OperationsManager;

/**
 * @author SAIvanov
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		History history = new SessionHistory();

		Display display = new Display();
		Shell shell = new Shell(display, SWT.TITLE | SWT.CLOSE);
		shell.setText("Calculator");

		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

		tabFolder.setBounds(0, 0, 300, 200);

		TabItem calculatorTab = new TabItem(tabFolder, SWT.NONE);

		calculatorTab.setText("Calculator");

		CalcuatorView calcuatorView = new CalcuatorView(tabFolder, new OperationsManager(history));
		calculatorTab.setControl(calcuatorView.getControl());

		TabItem historyTab = new TabItem(tabFolder, SWT.NONE);

		HistoryView historyView = new HistoryView(tabFolder, history);

		historyTab.setText("History");
		historyTab.setControl(historyView.getControl());

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		historyView.dispose();
		calcuatorView.dispose();
		display.dispose();
	}

}
