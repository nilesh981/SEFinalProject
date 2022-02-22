package Loaders;

import Entities.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuFileLoader {

  private final static String[] H = {"MenuItem Name", "Type" ,"Price", "Calorie"};
  private String fileName;
  public MenuFileLoader(String fileName){
    this.fileName = fileName;
  }
  public File getDefaultFile() {
    return new File("./menu.txt");
  }
  public List read()
  {
    int numberOfColumns = H.length;
    boolean useDefault = true;
    BufferedReader b = null;
    File file;
    List<List<String>> result = new ArrayList<List<String>>();
    try {
      file = new File(fileName);
      String s="UTF-8";
      if (!file.exists()) {
        if(useDefault){
          file = getDefaultFile();
        }else {
          throw new IllegalArgumentException("The specified Components.Menu File does not exist.");
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
  public List load()
  {List<MenuItem> menuItems;
    menuItems= new ArrayList<MenuItem>();
    List<List<String>> result = new ArrayList<List<String>>();
    result=this.read();
    for(List<String> line : result ){
      int i=2+1;
      String dishName = line.get(0).trim();
      String dishType = line.get(1).toUpperCase().trim();
      double dishPrice = Double.parseDouble(line.get(2).trim());
      double dishCalorie = Double.parseDouble(line.get(i).trim());
      MenuItem menuItem;
      menuItem=new MenuItem(dishName,dishType,dishPrice,dishCalorie);
      menuItems.add(menuItem);
    }
    return menuItems;
  }
}
