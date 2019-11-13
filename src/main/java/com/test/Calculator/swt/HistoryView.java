package com.test.Calculator.swt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import com.test.Calculator.StringUtils;
import com.test.Calculator.history.History;
import com.test.Calculator.history.HistoryEntry;

public class HistoryView {

	private History history;

	private Composite historyComposite;

	private Text historyText;

	private Button clearButton;

	/**
	 * Creates a view to show history in the tab
	 * 
	 * @param tabFolder
	 * @param history
	 */
	public HistoryView(TabFolder tabFolder, History history) {
		this.history = history;
		historyComposite = new Composite(tabFolder, SWT.NONE);

		RowLayout layout = new RowLayout(SWT.VERTICAL);
		layout.justify = true;
		layout.center = true;
		historyComposite.setLayout(layout);
		historyText = new Text(historyComposite, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		history.setModifyListener(this::showHistory);

		historyText.setLayoutData(new RowData(250, 130));

		clearButton = new Button(historyComposite, SWT.PUSH);
		clearButton.setLayoutData(new RowData(75, 20));
		clearButton.setText("Clear history");
		clearButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				history.clearHistory();
			}
		});
		showHistory();
	}

	private void showHistory() {
		List<HistoryEntry> list = history.getHistory();
		
		StringBuilder stringBuilder = new StringBuilder();
		for(HistoryEntry entry :list) 
		{
			stringBuilder.append(entry).append("\n");
		}
		historyText.setText(stringBuilder.toString());
		clearButton.setEnabled(list.size() > 0);
	}

	/**
	 * Returns history view parent control
	 * 
	 * @return
	 */
	public Control getControl() {
		return historyComposite;
	}

	public void dispose() {
		if (!historyComposite.isDisposed()) {
			historyComposite.dispose();
		}
	}

}
