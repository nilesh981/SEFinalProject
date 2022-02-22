package Pizza;
/**
 * Created by Felix on 23.02.2016.
 */
public enum Topping {
    DIETPILLS(0),
    MARGHERITA (1000),
    SALAMI(1400),
    PROSCIUTTO(1200);

    public double calories;

    Topping(double calories){
        this.calories = calories;
    }
}
