package com.test.Calculator.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.test.Calculator.history.History;
import com.test.Calculator.history.SessionHistory;
import com.test.Calculator.operations.OperationsManager;

public class SWTApplication {

	/**
	 * Starts the swt calculator app
	 */
	public static void run() {
		History history = new SessionHistory();

		Display display = Display.getDefault();
		Shell shell = new Shell(display);
		shell.setText("Calculator");
		shell.setLayout(new FillLayout());
		
		shell.setMinimumSize(300, 200);
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

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
