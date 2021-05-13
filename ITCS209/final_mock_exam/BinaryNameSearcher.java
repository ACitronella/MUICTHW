/**
 * Name: Phuriwat Angkoondittaphong
 * Student ID: 6388003
 * Section: 1
 */

public class BinaryNameSearcher extends NameSearcher{

    BinaryNameSearcher(String filename) {
        super(filename);
        
    }

    @Override
    public String find(String query) {
        if(readNames.size() == 0){
            return String.format(notFoundCase, query);
        }
        int start = 0;
        int end = readNames.size();
        int i = (start+end)/2;
        String queryLower = query.toLowerCase();
        int c = 1;
        while (start != end-1) {
            // System.out.println(start + " " + i + " " + end + " " + c);
            c = readNames.get(i).compareTo(queryLower);
            number_of_compared++;
            if(c > 0){
                end = i;
                i = (start+end)/2;
            }   
            else if(c < 0){
                start = i;
                i = (start+end)/2;
            }
            else{
                return String.format(foundCase, query, i);
            }
            
        }
        return String.format(notFoundCase, query);
    }

}