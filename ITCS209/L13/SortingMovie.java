import java.util.ArrayList;

public class SortingMovie {

	public static void main(String[] args) {
		ArrayList<Movie> movieList = new ArrayList<Movie>();
		movieList.add(new Movie(1, "The Intern", 2009));
		movieList.add(new Movie(2, "The Gift", 2009));
		movieList.add(new Movie(3, "The Lost Room", 2009));
		movieList.add(new Movie(4, "The Gift", 2012));
		movieList.add(new Movie(5, "Pasolini", 2012));
		movieList.add(new Movie(6, "The Intern", 2009));
		movieList.add(new Movie(7, "American Ultra", 2019));
		movieList.add(new Movie(8, "Sweet Red Bean Paste", 2019));
		
		sort(movieList);
	}
	
	public static void sort (ArrayList<Movie>  movies) {
		// YOUR CODE GOES HERE
		System.out.println("== unsorted movie list ==");
		movies.forEach(System.out::println);

		Movie temp;
        int index;
        // int k = 1;
        for (int i = movies.size()-1; i >= 0; i--) {
            index = i;
            for (int j = 0; j < i; j++) {
                if(movies.get(index).compareTo(movies.get(j)) < 0){
                    index = j;
                }
            }

            temp = movies.get(i);
            movies.set(i, movies.get(index));
            movies.set(index, temp);   
        }

		System.out.println();
		System.out.println("== sorted movie list (ascending) ==");
		movies.forEach(System.out::println);
	}

}
