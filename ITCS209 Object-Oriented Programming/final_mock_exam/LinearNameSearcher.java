/**
 * Name: Phuriwat Angkoondittaphong
 * Student ID: 6388003
 * Section: 1
 */

public class LinearNameSearcher extends NameSearcher{

    public LinearNameSearcher(String filename) {
        super(filename);

        
    }

    @Override
    public String find(String query) {
        String queryLower = query.toLowerCase();
        for (int i = 0; i < readNames.size(); i++) {
            number_of_compared++;
            if(queryLower.equals(readNames.get(i))){
                return String.format(foundCase, query, number_of_compared-1);
            }
        }
        return String.format(notFoundCase, query);
    }

    

}
