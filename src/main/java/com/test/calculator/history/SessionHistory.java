package com.test.calculator.history;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.test.calculator.operations.Operation;

/**
 * History of calculations, which stores entries in ArrayList
 * 
 * @author SAIvanov
 *
 */
public class SessionHistory implements History {

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
        List<HistoryEntry> data;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            data = gson.fromJson(fileReader, new TypeToken<List<HistoryEntry>>() {
            }.getType());
            fileReader.close();
        } catch (IOException e) {
            data = null;
            e.printStackTrace();
        } catch (JsonIOException e) {
            data = null;
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (data != null)

        {
            this.history = data;
            onModify();
        }
    }

    @Override
    public void exportToFile(File file) {
        try {
            String jsonString = new Gson().toJson(history);

            FileWriter fileWriter;

            fileWriter = new FileWriter(file);

            fileWriter.write(jsonString);

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
    }

}
