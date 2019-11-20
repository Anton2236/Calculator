package com.test.calculator.history;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

/**
 * History of calculations, which stores entries in ArrayList
 * 
 * @author SAIvanov
 *
 */
public class SessionHistory implements History {

    private static final Logger LOG = Logger.getLogger(SessionHistory.class);

    private HistoryModifiedListener modifyListener;

    private List<HistoryEntry> history;

    public SessionHistory() {
        modifyListener = null;
        history = new ArrayList<HistoryEntry>();
    }

    @Override
    public List<HistoryEntry> getHistory() {
        return new ArrayList<HistoryEntry>(history);
    }

    @Override
    public void addToHistory(HistoryEntry historyEntry) {
        history.add(0, historyEntry);
        onModify();
    }

    @Override
    public void clearHistory() {
        history = new ArrayList<HistoryEntry>();
        onModify();

    }

    private void onModify() {
        if (modifyListener != null) {
            modifyListener.onModified(this);
        }
    }

    @Override
    public void setModifyListener(HistoryModifiedListener modifyListener) {
        this.modifyListener = modifyListener;
    }

    @Override
    public void importFromFile(File file) {
        Gson gson = new Gson();
        List<HistoryEntry> data = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            data = gson.fromJson(fileReader, new TypeToken<List<HistoryEntry>>() {
            }.getType());
        } catch (IOException | JsonIOException e) {
            LOG.error(e);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                LOG.error(e);
            }
        }

        if (data != null) {
            this.history = data;
            onModify();
        }
    }

    @Override
    public void exportToFile(File file) {
        FileWriter fileWriter = null;
        try {
            String jsonString = new Gson().toJson(history);

            fileWriter = new FileWriter(file);

            fileWriter.write(jsonString);

        } catch (IOException | JsonIOException e) {
            LOG.error(e);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException exception) {
                    LOG.error(exception);
                }
            }
        }
    }

}
