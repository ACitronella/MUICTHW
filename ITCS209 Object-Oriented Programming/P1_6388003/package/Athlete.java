import java.util.List;
import java.util.Arrays;


public class Athlete extends Customer{
    
	public static List<FoodStall.Menu> athleteDishes = Arrays.asList(new FoodStall.Menu[]{FoodStall.Menu.MEAT, FoodStall.Menu.MEAT, FoodStall.Menu.MEAT, FoodStall.Menu.SALAD, FoodStall.Menu.BEVERAGE});

    public Athlete(CanteenICT c){
        super(c, 'A');
    }
}
