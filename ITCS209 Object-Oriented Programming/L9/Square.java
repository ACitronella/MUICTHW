import java.awt.Graphics;
import java.awt.Color;

/*
 * DO NOT MODIFY THIS CLASS
 */
class Square extends Shape{
	double width;

	public Square(double width){
		super("green", "Square with width " + width);
		this.width = width;
	}
	
	public double getArea(){
		return width * width;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GREEN); // i hard code this shit since you also hard code color of square
		g.fillRect(250, 250, (int)width, (int)width);

	}
}
