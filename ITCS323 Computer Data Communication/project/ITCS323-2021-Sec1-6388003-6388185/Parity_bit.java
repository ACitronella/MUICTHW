import java.lang.reflect.Array;
import java.util.*;

public class Parity_bit {
	//count 1 in the data
	public static Integer Count(String data) {
		int sum = 0;
		char[] ch = new char[data.length()];
        for (int m = 0; m < data.length(); m++) {
            ch[m] = data.charAt(m);
            sum = sum + Character.getNumericValue(ch[m]);
        }
		return sum;
	}
	
	//do the parity bit for one dimension
	public static String Parity_Gen1(String data, int wordsize, int type, int arrsize) {
		String newdata = null;
		char zero = '0';
		char one = '1';
		int numsum = Count(data);
		if(type == 1) { //for one-dimensional-even
			if(numsum % 2 == 0) {
				newdata = data + zero;
			} else {
				newdata = data + one;
			}
		} else if(type == 2){ //for one dimensional odd
			if(numsum % 2 == 1) {
				newdata = data + "0";
			} else {
				newdata = data + "1";
			}
		}
		return newdata;
	}
	
	//do the parity bit for two dimension
	public static String[] Parity_Gen2(String[] data, int wordsize, int type, int arrsize) {
		int maxcolum = 0;
		int add = 0;
		String atlast = "";
		
		//get the max colum
		for (int n = 0; n < data.length; n++) {
			if(data[n].length() > maxcolum) {
				maxcolum = data[n].length();
			}
		}
		
		int sum[] = new int[maxcolum];
		String newdata[] = new String[arrsize + 1];
		
		//add 0 for equal word size
		for (int p = 0; p < data.length; p++) {
			if(data[p].length() < maxcolum) {
				add = maxcolum - data[p].length();
				for(int q = 0; q < add; q++) {
					data[p] = "0" + data[p];
				}
			}
		}
		
		//for two dimensional even
		for (int r = 0; r < data.length; r++) {
			char[] ch = new char[maxcolum];
		    for (int s = 0; s < maxcolum; s++) {
		        ch[s] = data[r].charAt(s);
		        Character.getNumericValue(ch[s]);
		        sum[s] = sum[s] + (ch[s]-48); // because of ascii table
		    }
		}
		for (int t = 0; t < maxcolum; t++) {
			sum[t] = sum[t] % 2;
			if(type == 3) {//for two dimensional even
				if(sum[t] == 1) {
					atlast = atlast + "1";
				} else {
					atlast = atlast + "0";
				}
			} else if(type == 4) { //for two dimensional odd
				if(sum[t] == 0) {
					atlast = atlast + "1";
				} else {
					atlast = atlast + "0";
				}
			}
		}
		
		//for geting the result of codeword
		for(int u = 0; u <= arrsize; u++) {
			if(u < arrsize) {
				newdata[u] = data[u];
			} else if(u == arrsize) {
				newdata[u] = atlast;
			}
			if(type == 3) {
				newdata[u] = Parity_Gen1(newdata[u], wordsize, 1, arrsize);
			} else if(type == 4) {
				newdata[u] = Parity_Gen1(newdata[u], wordsize, 2, arrsize);
			}
		}
		return newdata;
	}
	
	public static int Paritycheck1(String data, int type, int arrsize) {
		int numsum2 = Count(data);
		if(arrsize == 1) {
			if(type == 1) { //for one-dimensional-even
				if(numsum2 % 2 == 0) {
					return 1;
				}
			} else if(type == 2) { //for one-dimensional-odd
				if(numsum2 % 2 == 1) {
					return 1;
				}
			}
		}
		return 0;
	}
	
	public static int Paritycheck2(String[] data, int type, int arrsize) {
		int numsum[] = new int[arrsize];
		for(int w = 0; w < arrsize; w++) {
			numsum[w] = Count(data[w]);
		}
		if(arrsize > 1) {
			for(int x = 0; x < arrsize; x++) {
				if(type == 3) { //for two dimensional even
					if(numsum[x] % 2 == 1) {
						return 0;
					}
				} else if(type == 4) { //for two dimensional odd
					if(numsum[x] % 2 == 0) {
						return 0;
					}
				}
			}
			return 1;
		} else {
			return 0;
		}
	}
	
	public static void main(String[] args) {
		String datawords[] = null;
		String codeword1 = null;
		String[] codeword2 = null;
		String dataword = null;
		int starting = 0;
		int starting2 = 0;
		int option = 0;
		int maximum = 0;
		int wordsize = 0;
		int arraysize = 0;
		
		while(starting == 0) { //loop checking the correct datawords
			//input the array size
			Scanner sc = new Scanner(System.in);
			System.out.println("--->Please input the array size");
			arraysize = sc.nextInt();
			
			//input the maximum size of each dataword
			Scanner sc1 = new Scanner(System.in);
			System.out.println("Please input the maximum size of each dataword");
			maximum = sc1.nextInt();
			
			//input the data
			System.out.println("What is your datawords?");
			Scanner sc2 = new Scanner(System.in);
			dataword = sc2.nextLine();
			datawords = dataword.split("\", \"");
			for(int i = 0; i < datawords.length; i++) {
				if(i == 0) { // excluding ["
					datawords[i] = datawords[i].substring(2);
				}
				if(i == datawords.length-1) { // excluding "]
					datawords[i] = datawords[i].substring(0, datawords[i].length()-2);
				}
			}
			
			//check the array size
			if(arraysize == datawords.length) {
				starting = 1;
			} else {
				System.out.println("Please check your array size and the dataword again!");
				continue;
			}
			//check the word size (>= 5 and <= max size)
			for(int k = 0; k < datawords.length; k++) {
				if(datawords[k].length() >= 5 && datawords[k].length() <= maximum) {
						starting = 1;
				} else {
					starting = 0;
					System.out.println("Please check your word size again at index "+(k+1)+"!");
					continue;
				}
			}
		}
		
		while(starting2 == 0) { //loop checking the correct option
			//choose type of parity
			System.out.println("Type of parity: press\"1\" one-dimensional-even, press\"2\" one-dimensional-odd,");
			System.out.println("                press\"3\" two-dimensional-even, press\"4\" two-dimensional-odd");
			Scanner sc3 = new Scanner(System.in);
			option = sc3.nextInt();
			if(option == 1 || option == 2 || option == 3 || option == 4) {
				starting2 = 1;
				break;
			} else {
				System.out.println("Please choose the correct option!");
			}
		}
		//conting word size
		for(int l = 0; l < datawords.length; l++) {
			wordsize = wordsize + datawords[l].length();
		}
		
		//print out the result of codeword
		System.out.println("Dataword: " + dataword);
		System.out.println("Word size: " + wordsize);
		if(option == 1) {
			System.out.println("Type of parity: one dimensional even");
		} else if (option == 2) {
			System.out.println("Type of parity: one dimensional odd");
		} else if (option == 3) {
			System.out.println("Type of parity: two dimensional even");
		} else {
			System.out.println("Type of parity: two dimensional odd");
		}
		System.out.println("Array size: " + datawords.length);
		if(option == 1 || option == 2 && arraysize == 1) {
			codeword1 = Parity_Gen1(datawords[0], wordsize, option, arraysize);
			System.out.println("Codeword: " + codeword1);
		} else if(option == 3 || option == 4 && arraysize > 1){
			System.out.print("Codeword: ");
			codeword2 = Parity_Gen2(datawords, wordsize, option, arraysize);
			for(int v = 0; v <= arraysize; v++) {
				System.out.print(codeword2[v] + " ");
			}
			System.out.println();
		}
		
		//checker
		if(option == 1 || option == 2 && arraysize == 1) {
			if(Paritycheck1(codeword1, option, arraysize) == 1) {
				System.out.println("Already check the parity codeword: Pass(1)");
			} else {
				System.out.println("Already check the parity codeword: Fail(0)");
			}
		} else if (option == 3 || option == 4 && arraysize > 1){
			if(Paritycheck2(codeword2, option, arraysize) == 1) {
			System.out.println("Already check the parity codeword: Pass(1)");
		} else {
			System.out.println("Already check the parity codeword: Fail(0)");
		}
		}
	}
}