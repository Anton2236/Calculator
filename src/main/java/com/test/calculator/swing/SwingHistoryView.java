package com.test.calculator.swing;

import java.awt.Dimension;
import java.awt.Panel;
import java.io.File;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.test.calculator.history.History;
import com.test.calculator.history.HistoryEntry;

/**
 * View for history
 * 
 * @author SAIvanov
 *
 */
public class SwingHistoryView extends JPanel {

    private JList<HistoryEntry> historyList;

    private JButton clearButton;
    private JButton importButton;
    private JButton exportButton;

    /**
     * Creates hisotry view instance
     * 
     * @param history - history instance
     */
    public SwingHistoryView(History history) {

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        historyList = new JList<HistoryEntry>();

        JScrollPane scrollPane = new JScrollPane(historyList);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        Panel buttonsPanel = new Panel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));

        clearButton = new JButton("Clear history");

        clearButton.addActionListener((e) -> {
            history.clearHistory();
        });

        importButton = new JButton("Import histroy");

        importButton.addActionListener((e) -> {
            File file = getFile(false);
            if (file != null) {
                history.importFromFile(file);
            }
        });

        exportButton = new JButton("Export histroy");

        exportButton.addActionListener((e) -> {
            File file = getFile(true);
            if (file != null) {
                history.exportToFile(file);
            }
        });

        add(scrollPane);
        add(Box.createRigidArea(new Dimension(20, 10)));
        add(buttonsPanel);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        buttonsPanel.add(importButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(5, 10)));
        buttonsPanel.add(exportButton);
        add(Box.createRigidArea(new Dimension(20, 10)));

        history.setModifyListener(this::showHistory);
        showHistory(history);
    }

    private void showHistory(History history) {
        List<HistoryEntry> list = history.getHistory();

        HistoryEntry[] items = new HistoryEntry[list.size()];

        list.toArray(items);

        historyList.setListData(items);
        clearButton.setEnabled(list.size() > 0);
    }

    private File getFile(boolean save) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON file", "json");
        fileChooser.addChoosableFileFilter(filter);
        
        int returnValue;
        if (save) {
            returnValue = fileChooser.showSaveDialog(this);
        } else {
            returnValue = fileChooser.showOpenDialog(this);
        }

        File selectedFile = null;

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
        }
        return selectedFile;
    }

}
