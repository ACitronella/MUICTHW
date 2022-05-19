
import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String input = scn.nextLine();
        scn.close();
        
        String toPrint = "";
        if(isPalindrome(input)){
            toPrint = "The input word \"" + input + "\" is a palindrome";
        }
        else{
            toPrint = "The input word \"" + input + "\" is not a palindrome";
        }
        System.out.println(toPrint);
    }   
    
    public static boolean isPalindrome(String s){
        // trivial case
        if (s.length() == 0){
            return true;
        }

        // pre-processing 
        String sLowered = s.toLowerCase();
        char[] edited = new char[s.length()];
        int c = 0;
        for(int i = 0; i < s.length(); i++){
            if(Character.isLetterOrDigit(sLowered.charAt(i))){
                edited[c++] = sLowered.charAt(i);
            }
        }

        // actual palindrome
        for(int i = 0, j = c-1; i < c/2; i++, j--){
            if(edited[i] != edited[j]){
                return false;
            }
        }
        return true;
        
    }
}
