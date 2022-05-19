// Bunradar Chatchaiyadech
// ID: 6388185
// Section: 1

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

 public class My_Hashmap {

     public static void main(String[] pArgs) throws IOException {
      // ask the user for finding the postal code
         Scanner sc= new Scanner(System.in);
         String where = sc.nextLine();
         // Boolean for checking the answer
         Boolean check = false;
         // spliting the file and input the hashmap key,value
      HashMap<String, String> th_file = new HashMap<String, String>();
      
         String fileName = ".\\TH.txt";
         File file = new File(fileName);

         try (Stream<String> linesStream = Files.lines(file.toPath())) {
             linesStream.forEach(line -> {
              String str[] = line.split("\\t");
              List<String> spliting = new ArrayList<String>();
              spliting = Arrays.asList(str);
              th_file.put(spliting.get(2), spliting.get(1));
              //System.out.println(th_file);
             });
         }
         
        //  Iterator<String> Vmap = th_file.keySet().iterator();
        //  while(Vmap.hasNext()){
        String key = where;  // Key
        String val = th_file.get(key); // Value
        if (th_file.containsKey(key)) {
            System.out.println("The postal code of "+key+" is "+val);
            check = true;
        //    break;
        } 
        //  }
        else {
            System.out.println(where+" does not found");
        }
     }
 }