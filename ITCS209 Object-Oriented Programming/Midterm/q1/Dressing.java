//ID: 6388003
//Name: Phuriwat Angkoondittaphong
//Section: 1

public class Dressing {
	
	public static int get_NumAllClothes(String[][] closet){
		int combination = 1;
		for(String[] type: closet){
			combination *= type.length;
		}
		return combination;
	}
	
	public static int get_NumAllCleanClothes(String[][] closet){
		int count = 0;
		for(String[] type: closet){
			for(String c: type){
				if(c.contains("true")){
					count++;
				}
			}
		}
		return count;
	}

// 	Row [0]: upper clothes: tshirt, shirt, sweater, jacket
//  Row [1]: lower clothes: jeans, trousers, short
//  Row [2]: footwears: sandals, shoes, sneakers, boots
//  Row [3]: socks: socks

// sweater, jacket, jeans, boots and socks are appropriate for wearing at a temperature less than 25 degrees (<25).
// tshirt, shirt, trousers, sneakers and shoes are appropriate for wearing temperatures between 25 – 65 degrees (25-65).
// short and sandals are appropriate for wearing at a temperature greater than 65 degrees (>65).

	public static int get_NumAllClothesByTemp(String[][] closet, int temperature){
		int count = 0;
		for (String[] type : closet) {
			for (String c : type) {
				if((temperature < 25) && (c.contains("sweater") || c.contains("jacket") || c.contains("jeans") || c.contains("boots") || c.contains("socks"))){
					count++;
				}
				else if((temperature >= 25 && temperature <= 65) && (c.contains("tshirt") || c.contains("shirt") || c.contains("trousers") || c.contains("sneakers") || c.contains("shoes"))){
					count++;
				}
				else if ((temperature > 65) && (c.contains("short") || c.contains("sandals"))){
					count++;
				}
			}
		}
		
		return count;
	}

}
