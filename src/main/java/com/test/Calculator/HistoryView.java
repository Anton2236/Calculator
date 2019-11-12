package com.test.Calculator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Text;

import com.test.Calculator.history.History;

public class HistoryView {

	private History history;

	private Composite historyComposite;

	private Text historyText;

	/**
	 * 
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
		
		historyText.setLayoutData(new RowData(250,125));
		
		Button clearButton  = new Button(historyComposite,SWT.PUSH);
		clearButton.setLayoutData(new RowData(75, 30));
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
		historyText.setText(history.getHistory().stream().reduce("", StringUtils::concatWithLineBreaks));
	}

	public Control getControl() {
		return historyComposite;
	}

}
