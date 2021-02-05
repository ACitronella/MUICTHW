package src;

public class CovidProfile {

    private static int count = 0; 
    private String date;
    private String location;
    private int accumulatedCases;
    private int curedCases;
    private int deathCases;

    public CovidProfile(){
        this("none", "none", 0, 0, 0);
    }
    
    public CovidProfile(String _date, String loc, int noACC, int noCured, int noDeath) throws IllegalArgumentException{
        if(noACC < 0 || noCured < 0 || noDeath < 0){
            throw new IllegalArgumentException("Input noACC, noCured and noDeath argument must be non-negative integer.");
        }
        this.date = _date;
        this.location = loc;
        this.accumulatedCases = noACC;
        this.curedCases = noCured;
        this.deathCases = noDeath;
        count++;
    }

    // new Constructor
    // IN CASE YOU WANT TO MERGE PROFILE
    public CovidProfile(CovidProfile a, CovidProfile b) throws IllegalArgumentException{
        if((!a.getDate().equals(b.getDate()))){
            throw new IllegalArgumentException("Both CovidProfiles instance must be in the same date and location must not be the same. " + a.getDate() + " should be equal to " + b.getDate());
        }
        if(a.getLocation().equals(b.getLocation())){
            throw new IllegalArgumentException("Both CovidProfiles instance must be in the same date and location must not be the same. " + a.getLocation() + " should not be equal to " + b.getLocation());
        }
        this.date = a.getDate();
        this.location = a.getLocation() + " and " + b.getLocation();
        this.accumulatedCases = a.getAccCases() + b.getAccCases();
        this.curedCases = a.getCuredCases() + b.getCuredCases();
        this.deathCases = a.getDeathCases() + b.getDeathCases();
        count++;
    }
    

    public boolean isSevere(){
        return this.deathCases > 10000;
    }

    // a new method
    public int getInfected(){
        return this.accumulatedCases - this.curedCases - this.deathCases;
    }

    // a bunch of getter
    public String getDate(){
        return this.date;
    }
    public String getLocation(){
        return this.location;
    }
    public int getAccCases(){
        return this.accumulatedCases;
    }
    public int getCuredCases(){
        return this.curedCases;
    }
    public int getDeathCases(){
        return this.deathCases;
    }
    public static int getCount(){
        return count;
    }

    // a bunch of setter
    public void setLocation(String loc){
        this.location = loc;
    }
    public void setAccCases (int value){
        this.accumulatedCases = value;
    }
    public void setCuredCases (int value){
        this.curedCases = value;
    }
    public void setDeathCases (int value){
        this.deathCases = value;
    }
    // I don't think user should be able to modify of $count value


    public void printCovidInfo(){
        printCovidInfo("\n");
    }
    
    public void printCovidInfo(String end){
        // give some flexible to user to control end string
        System.out.println(this.location + " at " + this.date);
        System.out.println("Accumulative Patient: " + this.accumulatedCases);
        System.out.println("Cursed Patient: "+ this.curedCases);
        System.out.println("Death Case: " + this.deathCases + end);
    }
    
}