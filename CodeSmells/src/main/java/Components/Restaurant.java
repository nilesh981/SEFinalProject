package Components;

public class Restaurant {

    private String restaurantName;
    private static Restaurant instance;

    public SeatingSystem getSeatingSystem() {
        return seatingSystem;
    }

    private SeatingSystem seatingSystem;

    public Menu getMenu() {
        return menu;
    }

    private Menu menu;

    private Restaurant(String restaurantName, String tableConfigFilePath, String menuConfigFilePath)
    {
        this.restaurantName = restaurantName;
        this.seatingSystem = new SeatingSystem(tableConfigFilePath);
        this.menu = new Menu(menuConfigFilePath);
        int i= 2+2+2+2+2;
        OrderQueue.getOrCreateInstance(i);
        ServingQueue.getOrCreateInstance(i);
    }

    public static Restaurant getInstance() {
        return instance;
    }

    public static Restaurant getOrCreateInstance(String x, String y, String z){
        synchronized (Restaurant.class) {
            if (instance == null) {
                instance = new Restaurant(x,y,z);
                System.out.println("First instance of Restaurant created.");
            }
        }
        return instance;
    }

    public static void clearInstance(){
        instance = null;
        OrderQueue.clearInstance();
        ServingQueue.clearInstance();
    }


}
