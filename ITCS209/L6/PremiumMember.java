import java.util.ArrayList;

public class PremiumMember extends Member{

    private double fee;
    private static double Family_FEE = 80.0;
    private ArrayList<String> family;
    private final int MAX_FAM = 2;
    private final String PREMIUM_FORMAT = "---- PREMIUM MEMBER ----\nMember fee: %.1f";
    private final String ADD_SUCC_FORMAT = "%s is added successfully.";
    private final String ADD_FAIL_FORMAT = "user: %s cannot be added, the Family user is reached the limit";
    private final String REMOVE_SUCC_FORMAT = "%s is removed successfully.";
    private final String REMOVE_FAIL_FORMAT = "user: %s does not exist and cannot be removed.";

    public PremiumMember(String email, String password, double fee){
        super(email, password);
        this.fee = fee;
        this.family = new ArrayList<>();
    }

    @Override
    public void printMemberInfo(){
        if(this.family.size() != 0){
            System.out.println(String.format(PREMIUM_FORMAT, this.fee));
            super.printMemberInfo();
            System.out.println("---------------------\nList of Family");
            for(String person: family){
                System.out.print(String.format("%s, ", person));
            }
            System.out.println();
        }
    }

    public boolean addFamily(String username){
        if(username != null){
            if(this.family.size() < MAX_FAM){
                this.family.add(username);
                System.out.println(String.format(ADD_SUCC_FORMAT, username));
                return true;
            }
        }
        System.out.println(String.format(ADD_FAIL_FORMAT, username));
        return false;
    }

    public boolean removeFamily(String username){
        for(int i = 0; i < this.family.size(); i++){
            if(this.family.get(i).equals(username)){
                System.out.println(String.format(REMOVE_SUCC_FORMAT, username));
                this.family.remove(i);
                return true;
            }
        }
        System.out.println(String.format(REMOVE_FAIL_FORMAT, username));
        return false;
    }

    public double getMonthlyBill(){
        return this.fee + this.family.size() * Family_FEE;
    }

}
