import java.util.Scanner;

public class Hamming_Code {
	public static String Hamming_gen(String dataword) {
		int m = 0; //wordsize
		int r = 0;
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		
		//find m (word size)
		m = dataword.length();

		//find r
		r = (int)Math.ceil(Math.log(m) / Math.log(2)) + 1; 
		int r_array[] = new int[r];
		
		//find position of r
		int[] twopow = new int[r];
		for (int j = 0; j < r; j++) {
			twopow[j] = (int) Math.pow(2, j);
		}
				
		// find amount of send bit
		String[] sendword = new String[r+m+1];
		String[] newsendword = new String[r+m+1];
		String[] newsendword2 = new String[r+m+1];
				
		//return the bit from ending to beginning
		char[] ch = new char[dataword.length()];
		int[] newarr = new int[dataword.length()];
		String[] newstr = new String[dataword.length()];
		for (int k = dataword.length()-1; k >= 0; k--) {
			ch[k] = dataword.charAt(k);
		    newarr[k]= Character.getNumericValue(ch[k]);
		    newstr[count1] = String.valueOf(newarr[k]);
		    count1++;
		    
		}
		        
		// sendword without r value
		for(int l = 0; l < r+m; l++) {
			for(int n = 0; n < r; n++) {
		    	if(l+1 == twopow[n]) {
		        	sendword[l] = "(r" + twopow[n] + ")";
		        } 
		    }
		}
		for(int p = 0; p < r+m; p++) {
			if(sendword[p] == null) {
		     	sendword[p] = newstr[count];
		    	count++;
		    }
			newsendword[p] = sendword[p];
		}
		        
		//start execute the redundant bit
		for(int s = 0; s < r; s++) {
			for(int t = twopow[s]-1; t < r+m; t++) {
				if((t-(twopow[s]-1)) % (twopow[s]*2) == 0) {
					for(int u = 0; u < twopow[s]; u++) {
						if((t+u) >= (r+m)) {
							continue;
						}
						if(sendword[t+u].contains("(r") == true) {
							sendword[t+u] = "0";
						}
						if(sendword[t+u].equals(null)) {
							sendword[t+u] = "0";
						}
						r_array[s] = r_array[s] + Integer.valueOf(sendword[t+u]);
					}
				}
			} 
			r_array[s] = r_array[s] % 2;
		}
		
		//all Data Bit and Redundant Bit
		int a = 0;
		for(int w = 0; w < r; w++) {
			a = (int) Math.pow(2, w);
			newsendword[a-1] = String.valueOf(r_array[w]);
		}
		
		//codeword
		String codeword = "";
		for(int x = r+m-1; x >= 0 ; x--) {
			newsendword2[count2] = newsendword[x];
			count2++;
		}
		for(int y = 0; y < r+m; y++) {
			codeword = codeword + newsendword2[y];
		}
		return codeword;
	}
	
	public static String extractHammingCode(String codeword){
		StringBuffer sb = new StringBuffer();
		int c = 1;
		int len = codeword.length();
		while(c < len){
			sb.append(codeword.charAt(len - c));
			c *= 2;
		}
		return sb.reverse().toString();
	}

	public static String extractDataWord(String codeword){
		StringBuffer sb = new StringBuffer();
		int c = 1;
		int idx = codeword.length() - 1;
		while(idx >= 0){
			if(codeword.length() - c == idx){
				c *= 2;
			}
			else{
				sb.append(codeword.charAt(idx));
			}
			idx--;
		}
		return sb.reverse().toString();
	}

	public static int bitStringXOR(String s1, String s2){
		int c = 0;
		for(int idx = s1.length() - 1, i = 1; idx >= 0; idx--, i*=2){
			if(s1.charAt(idx) != s2.charAt(idx)){
				c += i;
			}
		}
		return c;
	}

	public static Integer Hamming_check(String codeword) {
		String oldHammingCode = extractHammingCode(codeword);
		String extractedDataWord = extractDataWord(codeword);
		String newCodeWord = Hamming_gen(extractedDataWord);
		String newHammingCode = extractHammingCode(newCodeWord);
		int xored = bitStringXOR(oldHammingCode, newHammingCode);
		if(xored == 0){
			xored = -1;
		}
		return xored;
	}
	
	public static void main(String[] args) {
		String dataword = null;
		int error_pos = 0;
		String codeword = "";
		int loop = 0;
		
		System.out.println("What is your datawords?");
		Scanner sc = new Scanner(System.in);
		dataword = sc.nextLine();
		
		while(loop == 0) {
			//option for the user
			System.out.println("Choose the option: press \"1\" Generate the code word or press \"2\" check the error position of the codeword,");
			int option = sc.nextInt();
			
			if(option == 1) {
				
				//to do the hamming code
				System.out.println("Dataword: " + dataword);
		        codeword = Hamming_gen(dataword);
		        System.out.print("Code: " + codeword);
		    	loop = 1;
			} else if(option == 2) {
		        //to check the
				codeword = dataword;
				System.out.println("Codeword: " + codeword);
		        error_pos = Hamming_check(codeword);
		        if(error_pos == -1) {
		        	System.out.println("There is no error");
					System.out.println("Data: " + extractDataWord(codeword));
		        } else {
		        	System.out.println("There is an error at position " + error_pos);
		        	System.out.println("The correct codeword should be: ");
					StringBuffer sb = new StringBuffer(codeword);
					char newVal = '1';
					if (sb.charAt(sb.length() - error_pos) == '1') {
						newVal = '0';
					}
					sb.setCharAt(sb.length() - error_pos, newVal);
					System.out.println(extractDataWord(sb.toString()));
		        }
		        loop = 1;
	        } else {
	        	System.out.println("Please choose the options that the program provided!");
	        }
		}	
	}
}
