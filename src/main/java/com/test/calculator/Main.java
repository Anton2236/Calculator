package com.test.calculator;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.test.calculator.swing.SwingApplication;
import com.test.calculator.swt.SWTApplication;

/**
 * @author SAIvanov
 *
 */
public class Main {

	
//	public static void main(String[] args) 
//	{
//		SwingApplication.run();
//		SWTApplication.run();
//	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Display display = Display.getDefault();

		Shell shell = new Shell(display);
		shell.setText("Choose framework");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setMinimumSize(300, 200);
		Button swtButton = new Button(shell, SWT.PUSH);
		swtButton.setText("SWT");

		swtButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				SWTApplication.run();
			}

		});

		Button swingButton = new Button(shell, SWT.PUSH);
		swingButton.setText("Swing");

		swingButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
				SwingApplication.run();
			}

		});

		swingButton.pack();
		swtButton.pack();

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
