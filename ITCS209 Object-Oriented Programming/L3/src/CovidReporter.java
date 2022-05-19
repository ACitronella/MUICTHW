package src;

public class CovidReporter {
    public static void main(String[] args){
        CovidProfile cp1 = new CovidProfile(); // empty instance
        CovidProfile cp2 = new CovidProfile("03-FEB-2021", "THAILAND", 20454, 13217, 79); // mock data 
        CovidProfile cp4 = new CovidProfile("03-FEB-2021", "CHINA", 984651, 465165, 50000); // mock data 
        cp1.printCovidInfo();
        cp2.printCovidInfo();
        cp4.printCovidInfo();

        System.out.println("Count how many instance were created: " + CovidProfile.getCount());
        System.out.println("Is case at " + cp2.getLocation() + " severe: " + cp2.isSevere());
        System.out.println("Is case at " + cp4.getLocation() + " severe: " + cp4.isSevere() + "\n");
    
        CovidProfile cp3 = new CovidProfile(cp4, cp2); // trying to merge 2 profiles
        cp3.printCovidInfo();

    }
}
