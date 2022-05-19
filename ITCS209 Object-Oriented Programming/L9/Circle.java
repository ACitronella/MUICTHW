import java.awt.Color;
import java.awt.Graphics;



/*
 * DO NOT MODIFY THIS CLASS
 */
public class Circle extends Shape{
	double diameter;
	
	public Circle(double diameter){
		super("yellow", "Circle with diameter " + diameter);
		this.diameter = diameter;
	}
	
	public double getArea(){
		return Shape.PI * diameter * diameter;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillOval(250, 250, (int)this.diameter/2, (int)this.diameter/2);
	}

}