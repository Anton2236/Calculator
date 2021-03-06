package com.test.calculator.swing;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import com.test.calculator.StringUtils;

/**
 * Checks the text to be parsable as double
 * 
 * @author SAIvanov
 *
 */
public class DecimalDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);

        if (tryParse(sb.toString())) {
            super.insertString(fb, offset, string, attr);
        }
    }

    private boolean tryParse(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (Exception e) {
            return StringUtils.isEmptyOrMinus(text);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);

        if (tryParse(sb.toString())) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {

        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);
        System.out.println(sb.toString());
        if (tryParse(sb.toString())) {
            super.remove(fb, offset, length);
        }
    }

}
