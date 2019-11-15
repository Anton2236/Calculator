package com.test.calculator.swing;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Simple document listener
 * @author SAIvanov
 *
 */
@FunctionalInterface
public interface SimpleDocumentListener extends DocumentListener {
    /**
     * Method that executes upon any modification of the document
     * @param e - document event
     */
    void update(DocumentEvent e);

    @Override
    default void insertUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    default void removeUpdate(DocumentEvent e) {
        update(e);
    }

    @Override
    default void changedUpdate(DocumentEvent e) {
        update(e);
    }
}
