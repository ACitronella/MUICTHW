
package src;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App{
    private static String filePath = "TH.txt";
    private static boolean fullInfo = false;
    public static void main(String[] args) {
        String[] processedArgs = flagProcess(args);
        Map<String, GeoName> postNumToLoc = loadMap(filePath);
        
        if(processedArgs.length > 0 && postNumToLoc != null){    
            GeoName o = null;
            for (int i = 0; i < processedArgs.length; i++){
                if(processedArgs[i].charAt(0) == '-'){
                    continue;
                }
                o = postNumToLoc.get(processedArgs[i].toLowerCase());
                if(o != null && fullInfo){
                    System.out.println(String.format("%s whole info is %s", o.placeName, o));
                }    
                else if(o != null && !fullInfo){   
                    System.out.println(String.format("%s postcode is %s", o.placeName, o.postalCode));
                }
                else{
                    System.out.println(String.format("%s is not found", processedArgs[i]));
                }
            }
        }
        else if(processedArgs.length > 0 && postNumToLoc == null){
            System.out.println("load map fail, terminating program.");
        }
        else{
            System.out.println("please provide place name that you want to find postalcode");
        }
    }

    public static Map<String, GeoName> loadMap(String filePath){
        Map<String, GeoName> postNumToLoc = null;
        String l, acc, searchFactor;
        String[] tabSep;
        GeoName t;
        try (Scanner scn = new Scanner(new File(filePath));){
            postNumToLoc = new HashMap<>();
            while(scn.hasNextLine()){
                l = scn.nextLine();
                tabSep = l.split("\t");
                acc = "";
                if (tabSep.length == 12){
                    acc = tabSep[11];                
                }
                t = new GeoName(tabSep[0], tabSep[1], tabSep[2], tabSep[3], tabSep[4], tabSep[5], tabSep[6], tabSep[7], tabSep[8], Double.parseDouble(tabSep[9]), Double.parseDouble(tabSep[10]), acc);
                searchFactor = t.placeName.toLowerCase();
                postNumToLoc.put(searchFactor, t);
            }
        }
        catch(IOException e){
            System.out.println("dataset file cannot be found from given path");
        }
        return postNumToLoc;
    }

    public static String[] flagProcess(String[] args){
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("-f")){
                fullInfo = true;
            }
            else if(args[i].equals("-p")){
                filePath = args[i + 1];
                args[i + 1] = "-";
            }
        }
        return args;
    }

}