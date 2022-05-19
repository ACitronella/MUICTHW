import java.util.List;
import java.util.Arrays;

public class Professor extends Customer{

	public static List<FoodStall.Menu> professorDishes = Arrays.asList(new FoodStall.Menu[]{FoodStall.Menu.NOODLES, FoodStall.Menu.BEVERAGE});

    public Professor(CanteenICT c){
        super(c, 'P');
    }
}
