public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(){
        this("", 0.0, 0.0);
    }
    public Triangle(String color, double base, double height){
        super(color);
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double getArea(){
        return getArea(this.height, this.base);
    }

    public double getArea(double a, double b){
        this.base = a;
        this.height = b;
        return a * b * 0.5;
    }

    @Override
    public String toString(){
        return "Triangle[base=" + this.base + ",height=" + this.height + "," + super.toString() + "]";
    }
}
