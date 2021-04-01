import java.util.List;
import java.util.Arrays;

public class Student extends Customer{

	public static List<FoodStall.Menu> studentDishes = Arrays.asList(new FoodStall.Menu[]{FoodStall.Menu.DESSERT, FoodStall.Menu.DESSERT, FoodStall.Menu.DESSERT, FoodStall.Menu.DESSERT, FoodStall.Menu.DESSERT});

    public Student(CanteenICT _canteen){
        super(_canteen, 'S');
    }

    public Student(CanteenICT _canteen, char role){
        super(_canteen, role);
    }
}
