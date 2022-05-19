import java.util.Scanner;


public class ParkingTicket{

    static final int hourToMinute = 60;

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        String enterTime = scn.nextLine();
        String leaveTime = scn.nextLine();
        scn.close();
        int[] time = printParkingDuration(enterTime, leaveTime);
        int finalHour = time[0];
        int finalMinute = time[1];

        double cost = computeCost(finalHour, finalMinute);
        System.out.println("Parking duration: " + finalHour + " hours and " + finalMinute + " minutes");
        
        printTime(finalHour, finalMinute);
        System.out.println("Parking fee: " + cost);

    }

    public static void printMinute(int minute){
        // System.out.print("|");
        String s;
        for(int i = 0; i < hourToMinute; i++){
            if(i % 15 == 0){
                System.out.print("|");
            }
            if(i <= minute){
                s = "*";
            }
            else{
                s = " ";
            }
            System.out.print(s);
            
        }
    }

    public static void printHour(int hour){
        for(int i = 1; i <= hour; i++){
            printMinute(hourToMinute);
            System.out.println(" " + i + " hr");
        }
    }

    public static void printTime(int hour, int minute){
        printHour(hour);
        if(minute != 0){
            printMinute(minute);
            System.out.println(" " + minute + " min");
        }

    }

    public static double computeCost(int hour, int minute){
        // eliminate side effect for sure
        int temphour = hour;
        int tempminute = minute;
        if(temphour < 2 || temphour == 2 && tempminute == 0){
            return 0.0;
        }
        if(tempminute > 0 && tempminute <= 30){
            tempminute = 30;
        }
        else{
            tempminute = 0;
            temphour++;
        }
        
        double effectiveHour = (temphour - 2) + tempminute/60.0;
        double cost = effectiveHour * 99.0;

        return cost;
    }

    public static int[] printParkingDuration(String enterTime, String leaveTime){
        // i dev everything with duration in hour and minute, then this challenge appear
        // so i have no choice but to return array of int to migrate with my original code (too lazy to fix lmao)
        int enterHour = Integer.parseInt(enterTime.substring(0, 2));
        int enterMinute = Integer.parseInt(enterTime.substring(2, 4));
        int leaveHour = Integer.parseInt(leaveTime.substring(0, 2));
        int leaveMinute = Integer.parseInt(leaveTime.substring(2, 4));
        int finalMinute = leaveMinute - enterMinute;
        int finalHour = leaveHour - enterHour;
        System.out.println("Entering time -> " + enterTime);
        System.out.print("Leaving time -> " + leaveTime);
        if(finalHour < 0){
            System.out.print(" (overnight)");
            finalHour += 24;
        }
        if(finalMinute < 0){
            finalMinute += 60;
            finalHour -= 1;
        }
        System.out.println();

        return new int[]{finalHour, finalMinute};
    }
}
