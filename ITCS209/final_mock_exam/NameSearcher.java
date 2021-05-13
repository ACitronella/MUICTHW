/**
 * Name: Phuriwat Angkoondittaphong
 * Student ID: 6388003
 * Section: 1
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public abstract class NameSearcher {

    protected static ArrayList<String> readNames = null;
    protected int number_of_compared = 0; 
    

    protected static final String foundCase = "Found '%s' AT_INDEX(%d)";
    protected static final String notFoundCase = "Not Found Name: '%s'";

    NameSearcher(String filename)
    {
        try {
            // readNames = (ArrayList<String>) FileUtils.readLines(new File(filename), "UTF-8");
            
            readNames = new ArrayList<>();
            File f = new File(filename);
            Scanner scn = new Scanner(f, "UTF-8");
            
            while(scn.hasNextLine()){
            	String s = scn.nextLine();
            	readNames.add(s);
            }
            scn.close();
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        //clean word
        readNames.replaceAll(name -> name.toLowerCase());  
        
            
    }

    public int getNumComparisons() {
        return number_of_compared;
    }

    public void resetCompareCounter() {
        number_of_compared = 0;
    }
    
    /**
     * This method will be used in the EXTRA TASK
     */
    public void sortName() {
        // YOUR CODE GOES HERE    
        for (int i = 0; i < readNames.size()-1; i++) {
            for (int j = i+1; j < readNames.size(); j++) {
                if(readNames.get(i).compareTo(readNames.get(j)) > 0){
                    String t = readNames.get(i);
                    readNames.set(i, readNames.get(j));
                    readNames.set(j, t);
                }
            }
        }

    }

    public abstract String find(String query);


}




