package com.test.calculator.swt;

import java.io.File;
import java.util.stream.Collectors;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;

import com.test.calculator.history.History;
import com.test.calculator.history.HistoryEntry;

/**
 * View for history
 * 
 * @author SAIvanov
 *
 */
public class HistoryView extends Composite {

    private List historyList;

    private Button clearButton;

    private Button importButton;

    private Button exportButton;

    /**
     * Creates a view to show history in the tab
     * 
     * @param tabFolder - tab folder
     * @param history - history instance
     * @param style - SWT style
     */
    public HistoryView(TabFolder tabFolder, History history, int style) {

        super(tabFolder, style);

        GridLayout layout = new GridLayout(3, true);
        layout.verticalSpacing = 20;
        setLayout(layout);

        historyList = new List(this, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
        GridData textGridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        textGridData.horizontalSpan = 3;
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

        GridData gridData = new GridData(SWT.CENTER, SWT.CENTER, true, false);
        clearButton.setLayoutData(gridData);

        importButton = new Button(this, SWT.PUSH);
        importButton.setText("Import history");
        importButton.setLayoutData(gridData);
        importButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                String pathString = getFilePath(tabFolder.getShell(), SWT.OPEN);
                if (pathString != null) {
                    history.importFromFile(new File(pathString));
                }
            }
        });

        exportButton = new Button(this, SWT.PUSH);
        exportButton.setText("Export history");
        exportButton.setLayoutData(gridData);
        exportButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                String pathString = getFilePath(tabFolder.getShell(), SWT.SAVE);
                if (pathString != null) {
                    history.exportToFile(new File(pathString));
                }
            }

        });

        showHistory(history);
    }

    private String getFilePath(Shell shell, int options) {
        FileDialog fileDialog = new FileDialog(shell, options);
        fileDialog.setFilterExtensions(new String[] { "*.json" });
        return fileDialog.open();
    }

    private void showHistory(History history) {
        java.util.List<HistoryEntry> list = history.getHistory();

        String[] items = new String[list.size()];

        list.stream().map(HistoryEntry::toString).collect(Collectors.toList()).toArray(items);

        historyList.setItems(items);
        clearButton.setEnabled(list.size() > 0);
    }

}
