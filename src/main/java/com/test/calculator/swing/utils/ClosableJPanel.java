package com.test.calculator.swing.utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public abstract class ClosableJPanel extends JPanel {

    private List<Runnable> onClosing;

    protected ClosableJPanel() {
        onClosing = new ArrayList<Runnable>();
    }

    public void onClosing() {
        onClosing.forEach(Runnable::run);
        onClosing = new ArrayList<Runnable>();
    }

    public void doOnClosing(Runnable runnable) {
        onClosing.add(runnable);
    }
}
