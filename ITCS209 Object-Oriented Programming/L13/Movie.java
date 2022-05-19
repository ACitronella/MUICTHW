public class Movie implements Comparable<Movie> {
	public int mid = -1;
	public String title = null;
	public int year = -1;
	
	public Movie(int _mid, String _title, int _year)
	{
		mid = _mid;
		title = _title;
		year = _year;
	}
	
	
	public String toString()
	{
		return "[mid:"+mid+" |"+title+" |"+year+"]";
	}
	
	
	public int compareTo(Movie m) {
		if(this == m || (this.mid == m.mid && this.title.equals(m.title) && this.year == m.year)){
			return 0;
		}
		else if(this.title.compareTo(m.title) > 0 || (this.title.equals(m.title) && this.year > m.year) || (this.title.equals(m.title) && this.year == m.year && this.mid > m.mid)){
			return 1;
		}
		
		return -1;
	}
}