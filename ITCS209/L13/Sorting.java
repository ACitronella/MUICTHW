import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sorting {
    public static void main(String[] args) {
        String fileName = "unsorted.txt";
        String[] words = Sorting.read(fileName);
        Sorting.sort(words);
    }

    public static String[] read(String fileName){
        Scanner scn = null;
        StringBuilder l = new StringBuilder();
        try{
            File f = new File(fileName);
            scn = new Scanner(f);
            while(scn.hasNext()){
                l.append(scn.nextLine());
            }
        }
        catch(FileNotFoundException e){
            e.printStackTrace();    
            return null;
        }
        finally{
            if(scn != null){
                scn.close();
            }
        }
        return l.toString().split(" ");
    }

    public static void print(String[] words){
        System.out.print(Arrays.toString(words));
    }

    /**
     * 
     * this sort method implement selection sort to sort String[]
     * in-place, time complexity: O(n^2), space complexity: O(1)
     * 
     * @param words String[] to be sorted
     * 
     */
    public static void sort(String[] words){
        System.out.print("Original: ");
        Sorting.print(words);
        System.out.println();

        String temp;
        int index;
        int k = 1;
        for (int i = words.length-1; i >= 0; i--) {
            index = i;
            for (int j = 0; j < i; j++) {
                if(words[index].compareTo(words[j]) > 0){
                    index = j;
                }
            }

            temp = words[i];
            words[i] = words[index];
            words[index] = temp;

            System.out.print("Pass " + (k++) + ": ");
            Sorting.print(words);
            System.out.println();
        }
    }

}
