// Student ID: 6388003
// Name: Phuriwat Angkoondittaphong
// Section: 1

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class Movie {
	// ---- DO NOT MODIFY THIS -------
	public static final DecimalFormat df = new DecimalFormat("0.00");
	
	public int mid = -1;
	public String title = null;
	public Set<String> tags = null;
	// -------------------------------
	
	// You can add your own instance fields here
	public List<Rating> rating;
	// private double avgRating = -1.0;

	public Movie(int _mid, String _title){
		mid = _mid;
		title = _title;
		tags = new HashSet<String>();
		
		// YOUR CODE GOES HERE (if any)
		rating = new ArrayList<>();
	}
	
	
	/**
	 * Calculate and return the average rating of the movies
	 * @return the average rating
	 * @throws MovieException with message "no_rating" if there is no rating for this movie yet
	 */
	public double getAverageRating() throws MovieException {
		if(this.rating.isEmpty()){
			throw new MovieException("no_rating");
		}
		// if(this.avgRating == -1.0){
		// 	return this.avgRating = this.rating.stream().mapToDouble(x->x.rating).average().getAsDouble();	
		// }
		
		return this.rating.stream().mapToDouble(x->x.rating).average().getAsDouble();
		// double s = 0.0;
		// for (Rating r : this.rating) {
		// 	s += r.rating;
		// }
		
		// return s/this.rating.size();
	}
	
	
	/**
	 * DO NOT MODIFY THIS METHOD
	 */
	public String toString(){
		try {
			return "[mid:" + mid + "->" + title + " " + tags
				+ " (rating:" + df.format(this.getAverageRating()) + "/5)]";
		} catch (MovieException e) {
			return "[mid:" + mid + "->" + title + " " + tags
					+ " (rating:" + e.getMessage() + ")]";
		}
	}
	
}
