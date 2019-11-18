package com.test.calculator.swing;

import java.awt.Dimension;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.test.calculator.history.History;
import com.test.calculator.history.HistoryEntry;

/**
 * View for history
 * 
 * @author SAIvanov
 *
 */
public class SwingHistoryView extends JPanel {

    private History history;

    private JList<HistoryEntry> historyList;

    private JButton clearButton;

    /**
     * Creates hisotry view instance
     * 
     * @param history - history instance
     */
    public SwingHistoryView(History history) {
        this.history = history;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        historyList = new JList<HistoryEntry>();

        JScrollPane scrollPane = new JScrollPane(historyList);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        clearButton = new JButton("Clear history");

        clearButton.addActionListener((e) -> {
            history.clearHistory();
        });

        add(scrollPane);
        add(Box.createRigidArea(new Dimension(20, 10)));
        add(clearButton);
        add(Box.createRigidArea(new Dimension(20, 10)));

        history.setModifyListener(this::showHistory);
        showHistory();

    }

    private void showHistory() {
        List<HistoryEntry> list = history.getHistory();

        HistoryEntry[] items = new HistoryEntry[list.size()];

        list.toArray(items);
        
        historyList.setListData(items);
        clearButton.setEnabled(list.size() > 0);
    }

}
