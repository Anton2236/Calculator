package com.test.calculator;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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

    // public static void main(String[] args)
    // {
    // SwingApplication.run();
    // SWTApplication.run();
    // }
    /**
     * @param args
     */
    public static void main(String[] args) {

        Display display = new Display();

        Shell shell = new Shell(display);
        shell.setText("Choose framework");
        shell.setLayout(new FillLayout(SWT.HORIZONTAL));
        shell.setMinimumSize(300, 200);
        Rectangle rectangle = display.getPrimaryMonitor().getClientArea();

        int x = rectangle.width / 2 - shell.getMinimumSize().x / 2;
        int y = rectangle.height / 2 - shell.getMinimumSize().y / 2;
        
        shell.setLocation(new Point(x, y));

        Button swtButton = new Button(shell, SWT.PUSH);
        swtButton.setText("SWT");

        final AtomicBoolean useSwt = new AtomicBoolean(true);
        final AtomicBoolean buttonClicked = new AtomicBoolean(false);

        swtButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                useSwt.set(true);
                buttonClicked.set(true);
                shell.dispose();
            }

        });

        Button swingButton = new Button(shell, SWT.PUSH);
        swingButton.setText("Swing");

        swingButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                useSwt.set(false);
                buttonClicked.set(true);
                shell.dispose();
            }

        });

        swingButton.pack();
        swtButton.pack();

        shell.pack();
        shell.open();
        Point point = new Point(0, 0);
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                point = shell.getLocation();
                display.sleep();
            }
        }

        if (buttonClicked.get()) {
            if (useSwt.get()) {
                SWTApplication.run(display, point);
            } else {
                SwingApplication.run(point);
            }
        }

        display.dispose();
    }

}
