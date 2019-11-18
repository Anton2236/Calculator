package com.test.calculator.swt;

import java.util.Collections;
import java.util.stream.Collectors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import com.test.calculator.history.History;
import com.test.calculator.history.HistoryEntry;

/**
 * View for history
 * 
 * @author SAIvanov
 *
 */
public class HistoryView extends Composite {

    private History history;

    private List historyList;

    private Button clearButton;

    /**
     * Creates a view to show history in the tab
     * 
     * @param tabFolder - tab folder
     * @param history - history instance
     * @param style - SWT style
     */
    public HistoryView(TabFolder tabFolder, History history, int style) {

        super(tabFolder, style);
        this.history = history;

        GridLayout layout = new GridLayout(1, false);
        layout.verticalSpacing = 20;
        setLayout(layout);

        historyList = new List(this, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
        GridData textGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        historyList.setLayoutData(textGridData);

        history.setModifyListener(this::showHistory);

        clearButton = new Button(this, SWT.PUSH);
        clearButton.setText("Clear history");
        clearButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                history.clearHistory();
            }
        });

        GridData clearButtonGridData = new GridData(SWT.CENTER, SWT.CENTER, false, false);
        clearButton.setLayoutData(clearButtonGridData);
        showHistory();
    }

    private void showHistory() {
        java.util.List<HistoryEntry> list = history.getHistory();

        String[] items = new String[list.size()];

        list.stream().map(HistoryEntry::toString).collect(Collectors.toList()).toArray(items);

        historyList.setItems(items);
        clearButton.setEnabled(list.size() > 0);
    }

}
