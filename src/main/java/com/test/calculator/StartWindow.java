package com.test.calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class StartWindow {

    private Display display;
    private Shell shell;
    private boolean useSwt;
    private boolean buttonClicked;
    private Button swtButton;
    private Button swingButton;
    private Point currentLocation;

    public StartWindow(Display display) {
        this.display = display;
        shell = new Shell(display);
        shell.setText("Choose framework");
        shell.setLayout(new FillLayout(SWT.HORIZONTAL));
        shell.setMinimumSize(300, 200);
        Rectangle rectangle = display.getPrimaryMonitor().getClientArea();

        int x = rectangle.width / 2 - shell.getMinimumSize().x / 2;
        int y = rectangle.height / 2 - shell.getMinimumSize().y / 2;

        shell.setLocation(new Point(x, y));

        swtButton = new Button(shell, SWT.PUSH);
        swtButton.setText("SWT");

        swingButton = new Button(shell, SWT.PUSH);
        swingButton.setText("Swing");

        swtButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                useSwt = true;
                buttonClicked = true;
                shell.dispose();
            }

        });
        swingButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                useSwt = false;
                buttonClicked = true;
                shell.dispose();
            }

        });
    }

    public void run() {
        useSwt = true;
        buttonClicked = false;
        swingButton.pack();
        swtButton.pack();
        shell.pack();
        shell.open();
        currentLocation = new Point(0, 0);

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                currentLocation = shell.getLocation();
                display.sleep();
            }
        }
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public boolean isUseSwt() {
        return useSwt;
    }

    public boolean isButtonClicked() {
        return buttonClicked;
    }
}
