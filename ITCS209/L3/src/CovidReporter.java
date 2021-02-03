package src;

public class CovidReporter {
    public static void main(String[] args){
        CovidProfile cp1 = new CovidProfile(); // empty instance
        CovidProfile cp2 = new CovidProfile("03-FEB-2021", "THAILAND", 20454, 13217, 79);
        cp1.printCovidInfo();
        cp2.printCovidInfo();

        // CovidProfile cp3 = new CovidProfile(cp1, cp2); // trying to merge 2 profiles
        // System.out.println(); 
        // cp3.printCovidInfo();

    }
}
