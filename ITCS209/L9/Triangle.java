import java.awt.Graphics;

import java.awt.Color;

/*
 * DO NOT MODIFY THIS CLASS
 */
class Triangle extends Shape{
	double base;
	double height;
	
	public Triangle(double base, double height){
		super("red", "Triangle with base " + base + ", height " + height);
		this.base = base;
		this.height = height;
	}
	
	public double getArea(){
		return 0.5 * base * height;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponents(g);

		g.setColor(Color.RED);
		int[] x = new int[] {(int)(250-base/2), (int)(250+base/2), (int)(250)};
		int[] y = new int[] {(int)(250+height/2), (int)(250+height/2), (int)(250-height/2)};
		g.fillPolygon(x, y, 3);

	}

}