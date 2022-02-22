package Terminals;

import Components.Restaurant;
import Components.SeatingSystem;
import Components.ServingQueue;
import Entities.Dish;
import Entities.Serving;
import Entities.SingleTable;
import Entities.TerminalPrintType;

import java.util.ArrayList;
import java.util.List;

public class ServiceDeskTerminal extends Terminal{

    private List<SingleTable> tables;
    public String tab="Table ";
    public ServiceDeskTerminal(){
        super();
        tables = new ArrayList<SingleTable>();
    }

    public KitchenTerminal grandOpening(String x, String y, String z){
        Restaurant.getOrCreateInstance(x,y,z);
        return new KitchenTerminal();
    }
    public KitchenTerminal grandOpening(){

        return grandOpening("MyRestaurant","tables.txt","menu.txt");
    }

    public void closeBusinesss(){
        printToScreen("Business closed.");
        Restaurant.clearInstance();
    }
    public CustomerTerminal checkIn(int n){
        Restaurant re = Restaurant.getInstance();
        SeatingSystem ss = re.getSeatingSystem();
        SingleTable table = ss.getAvailableTable(n);
        CustomerTerminal t;
        if(table != null){
            if(ss.occupy(table)){
                tables.add(table);
                int k=table.getIndex();
                printToScreen("New table "+k+" checked in, number of people: "+n);
                t=new CustomerTerminal(table);
            }else{
                int k=table.getIndex();
                printToScreen(tab+k+" occupied. Check in failed.",TerminalPrintType.Error);
                t= null;
            }
        }else{
            printToScreen("Not enough seat", TerminalPrintType.Error);
            t=null;
        }
        return t;
    }

    public void checkOut(SingleTable table){
        Restaurant re = Restaurant.getInstance();
        SeatingSystem ss = re.getSeatingSystem();
        if(table.isCheckingOut()){
            if(ss.vacate(table)){
                tables.remove(table);
                printToScreen( tab+table.getIndex()+" checked out.");
            }else {
                int k=table.getIndex();
                printToScreen("Vacating table " +k+ " failed.", TerminalPrintType.Error);
            }
        }
    }

    public void serveDish(){
        ServingQueue sq = ServingQueue.getInstance();
        Serving serving = sq.take();
        Dish dish = serving.getDish();
        SingleTable table = serving.getToTable();
        table.addDish(dish);
        printToScreen(dish.getMenuItem().getDishName()+ " served to table " + table.getIndex());
    }

}
