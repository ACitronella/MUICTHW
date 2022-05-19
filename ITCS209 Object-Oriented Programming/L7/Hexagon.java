public class Hexagon extends Shape {
    private double side; // a side length
    private static final double c = 2.59807621; // rough approxmation of 3*(3)^(1/3)/2
    public Hexagon(){
        this("", 0.0);
    }
    public Hexagon(String color, double side){
        super(color);
        this.side = side;
    }
    
    @Override
    public double getArea(){
        return getArea(this.side); 
    }

    public double getArea(double a){
        this.side = a;
        return a * a * c;
    }

    @Override
    public String toString(){
        return "Hexagon[side=" + this.side + "," + super.toString() + "]";
    }
}
