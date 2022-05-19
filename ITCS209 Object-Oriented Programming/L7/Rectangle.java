public class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(){
        this("", 0.0, 0.0);
    }
    public Rectangle(String color, double base, double height){
        super(color);
        this.length = base;
        this.width = height;
    }
    
    @Override
    public double getArea(){
        return getArea(this.length, this.width);
    }

    public double getArea(double a, double b){
        this.length = a;
        this.width = b;
        return a * b;
    }

    @Override
    public String toString(){
        return "Rectangle[length=" + this.length + ",width=" + this.width + "," + super.toString() + "]";
    }
}
