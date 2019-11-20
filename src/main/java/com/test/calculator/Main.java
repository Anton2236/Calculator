package com.test.calculator;

import org.eclipse.swt.widgets.Display;

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
        boolean run = true;
        while (run) {
            run = true;
            StartWindow startWindow = new StartWindow(display);
            startWindow.run();

            if (startWindow.isButtonClicked()) {
                if (startWindow.isUseSwt()) {
                    SWTApplication.run(display, startWindow.getCurrentLocation());
                } else {
                    SwingApplication.run(display, startWindow.getCurrentLocation());
                }
            } else {
                run = false;
            }

        }
        display.dispose();
    }

}
