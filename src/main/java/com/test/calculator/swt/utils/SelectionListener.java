package com.test.calculator.swt.utils;

import org.eclipse.swt.events.SelectionEvent;

@FunctionalInterface
public interface SelectionListener extends org.eclipse.swt.events.SelectionListener {
    @Override
    default void widgetDefaultSelected(SelectionEvent e) {
    }

}
