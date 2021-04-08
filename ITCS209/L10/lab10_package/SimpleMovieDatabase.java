import java.io.File;
import java.io.FileNotFoundException;
// import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
// import java.util.TreeMap; // i want to use treemap instead of hashmap, but it make output differ from hashmap and it hard to check
import java.util.regex.Pattern;
import java.util.regex.Matcher;

// import org.apache.commons.io.FileUtils;

public class SimpleMovieDatabase {
	public Map<Integer, Movie> movies = null;
	
	public static Pattern MovieSeperator = Pattern.compile("([0-9]*),([A-Za-z\\s\\'0-9\\.\\(\\)\\:\\-\\?\\!]*),([A-Za-z\\|\\(\\)\\s\\-]+)");
	public static Pattern WhiteSpaceCheck = Pattern.compile("[\\s]+");
	public static Pattern TagSeperator = Pattern.compile("([a-zA-Z\\-]+)");
	public static String NoTags = "(no genres listed)";
	

	public void importMovies(String movieFilename) 
	{	
		try{
			File movie = new File(movieFilename);
			// System.out.println(FileUtils.readLines(movie, "UTF-8"));
			Scanner f = new Scanner(movie);
			this.movies = new HashMap<>();
			while(f.hasNext()){
				// for each line
				String line = f.nextLine();
				Matcher m = MovieSeperator.matcher(line);

				while(m.find()){
					int id = Integer.parseInt(m.group(1));
					String name = m.group(2);
					if(WhiteSpaceCheck.matcher(name).matches()){
						break;
					}
					String tags = m.group(3);
					// System.out.println(id +","+ name + "," + tags);
						
					Set<String> tagL = new HashSet<>();
					// Set.of(Arrays.asList(tags.split("\\|"))); // can be use and end my suffer

					if(NoTags.equals(tags)){
						tagL.add(NoTags);
					}
					else{
						Matcher t = TagSeperator.matcher(tags);
						while(t.find()){
							tagL.add(tags.substring(t.start(), t.end()));
						}
					}
					Movie curLine = new Movie(id, name);
					curLine.tags = tagL;
					this.movies.put(id, curLine);
				}

			}
			f.close();

		}
		catch(FileNotFoundException e){
			System.out.println("File not found");
			// just surpress it
		}
		// catch(IOException e){

		// }

	}
	
	
	//-------------------BONUS----------------------
	public List<Movie> searchMovies(String query) 
	{
		String qLower = query.toLowerCase();
		List<Movie> ml = new ArrayList<>();
		this.movies.forEach( // kekw, I just write weird func programming code
			(k, v) -> {
				if(v.title.toLowerCase().contains(qLower)){
					ml.add(v);
				}
			}
		);
		return ml;
	}

	public List<Movie> getMoviesByTag(String tag)
	{
		List<Movie> ml = new ArrayList<>();
		this.movies.forEach(
			(k, v) -> {
				if(v.tags.contains(tag)){
					ml.add(v);
				}
			}
		);
		return ml;
	}
	
	
	public static void main(String[] args)
	{
		SimpleMovieDatabase mdb = new SimpleMovieDatabase();
		mdb.importMovies("lab10_movies.txt");
		System.out.println("Done importing "+mdb.movies.size()+" movies");
		int[] mids = new int[]{139747, 141432, 139415, 139620, 141305};
		for(int mid: mids)
		{
			System.out.println("Retrieving movie ID "+mid+": "+mdb.movies.get(mid));
		}
		
		//Uncomment for bonus

		System.out.println("\n////////////////////////// BONUS ///////////////////////////////");
		String[] queries = new String[]{"america", "thai", "thailand"};
		for(String query: queries)
		{
			System.out.println("Results for movies that match: "+query);
			for(Movie m: mdb.searchMovies(query))
			{
				System.out.println("\t"+m);
			}
			System.out.println();
		}
		
		String[] tags = new String[]{"Musical", "Action", "Thriller"};
		for(String tag: tags)
		{
			System.out.println("Results for movies in category: "+tag);
			for(Movie m: mdb.getMoviesByTag(tag))
			{
				System.out.println("\t"+m);
			}
			System.out.println();
		}
		
		
	}

}


