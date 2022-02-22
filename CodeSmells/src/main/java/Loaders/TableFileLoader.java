package Loaders;

import Entities.SingleTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableFileLoader {

    private final static String[] H = {"Table Number", "Number of Seats"};
    private String fileName;
    public TableFileLoader(String fileName){
        this.fileName = fileName;
    }

    public File getDefaultFile() {
        return new File("./tables.txt");
    }
    public List read()
    {
        int numberOfColumns = H.length;
        boolean useDefault = true;
        BufferedReader b = null;
        File file;
        List<List<String>> result = new ArrayList<List<String>>();
        String err="The specified Components.Menu File does not exist.";
        try {
            file = new File(fileName);
            String s="UTF-8";
            if (!file.exists()) {
                if(useDefault){
                    file = getDefaultFile();
                }else {
                    throw new IllegalArgumentException(err);
                }b = new BufferedReader(new InputStreamReader(new FileInputStream(file), s));
            } else {
                b = new BufferedReader(new InputStreamReader(new FileInputStream(file), s));
            }
            String line = b.readLine();
            while(line != null){
                String[] entries = line.split(",");
                List<String> lineEntry = new ArrayList<String>();
                if(entries.length != numberOfColumns){
                    throw new IllegalArgumentException("The specified Columns are incorrect.");
                }
                Collections.addAll(lineEntry, entries);
                result.add(lineEntry);
                line = b.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return result;
    }
    public List<SingleTable> load()
    {List<SingleTable> tables = new ArrayList<SingleTable>();
        List<List<String>> result = new ArrayList<List<String>>();
        result=this.read();
            for(List<String> line : result ){
            int tableIndex = Integer.parseInt(line.get(0).trim());
            int tableSeats = Integer.parseInt(line.get(1).trim());
            SingleTable table = new SingleTable(tableIndex,tableSeats);
            tables.add(table);
        }
        return tables;
    }
}
