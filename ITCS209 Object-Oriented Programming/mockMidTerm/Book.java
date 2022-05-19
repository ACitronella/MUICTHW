// Section: 1

public class Book extends Item{
	
	private StringBuilder note = null;
	
	public Book(double _purchasePrice, double _age, double weight) {
		super(_purchasePrice, _age, weight);
		note = new StringBuilder();
	}

	// implememt for deep clone only
	private Book(Book c){
		super(c.getPurchasePrice(), c.getAge(), c.getWeight());
		this.note = new StringBuilder(c.getNote());
	}

	@Override
	public double getCurrentValue() {
		return this.getPurchasePrice() * Math.pow(0.9, super.getAge());
	}

	@Override
	public String toString()
	{
		return "[Book: value = "+String.format( "%.2f", this.getCurrentValue())+" Baht, Note = \""+getNote()+"\"]";
	}
	
	// `takeNote` method has print statement, so i cannot use this method to append `note`
	public void takeNote(String _note)
	{
		System.out.println("Taking note: \""+_note+"\"");
		note.append(_note);
	}
	
	public String getNote()
	{
		return note.toString();
	}
	
	public void setNote(StringBuilder _note)
	{
	
		this.note = new StringBuilder(_note.toString());
		// this.note = _note;
	}	

	public Book clone()
	{
		//Your Code Goes Here
		// return new Book(this);
		Book d = new Book(super.getPurchasePrice(), super.getAge(), super.getWeight());
		// // d.setNote(this.note);
		d.note = new StringBuilder();
		d.note.append(this.note);
		
		return d;
	}
}
