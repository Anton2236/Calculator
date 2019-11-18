package com.test.calculator.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.test.calculator.history.History;
import com.test.calculator.history.SessionHistory;
import com.test.calculator.operations.OperationsManager;

/**
 * SWT calculator app
 * 
 * @author SAIvanov
 *
 */
public class SWTApplication {

    /**
     * Starts the swt calculator app
     */
    public static void run(Display display, Point location) {
        History history = new SessionHistory();

        Shell shell = new Shell(display);
        shell.setText("Calculator");
        shell.setLayout(new FillLayout());

        shell.setMinimumSize(300, 200);
        shell.setLocation(location);
        TabFolder tabFolder = new TabFolder(shell, SWT.NONE);

        TabItem calculatorTab = new TabItem(tabFolder, SWT.NONE);

        calculatorTab.setText("Calculator");

        CalcuatorView calcuatorView = new CalcuatorView(tabFolder, new OperationsManager(history), SWT.NONE);
        calculatorTab.setControl(calcuatorView);

        TabItem historyTab = new TabItem(tabFolder, SWT.NONE);

        HistoryView historyView = new HistoryView(tabFolder, history, SWT.NONE);

        historyTab.setText("History");
        historyTab.setControl(historyView);

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
