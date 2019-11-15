package com.test.calculator.swt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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

    private Text historyText;

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

        historyText = new Text(this, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
        GridData textGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        historyText.setLayoutData(textGridData);

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
        List<HistoryEntry> list = history.getHistory();

        StringBuilder stringBuilder = new StringBuilder();
        for (HistoryEntry entry : list) {
            stringBuilder.append(entry).append("\n");
        }
        historyText.setText(stringBuilder.toString());
        clearButton.setEnabled(list.size() > 0);
    }

}
