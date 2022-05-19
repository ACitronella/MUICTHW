
import java.awt.Graphics;
import javax.swing.JPanel;



public abstract class Shape extends JPanel implements Comparable {

    public static double PI = Math.PI;
    public String color;
    public String description;
    
    public Shape(String color, String desciption){
        this.color = color;
        this.description = desciption;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }

    @Override
    public String toString(){
        return "%s (color=%s, area=%s)".formatted(this.description, this.color, this.getArea());
    }

    @Override
    public int compareTo(Object o){
        double a = this.getArea();
        double b = ((Shape)o).getArea();
        if(a > b){
            return 1;
        }
        else if(a < b){
            return -1;
        }
        return 0;
    }

    public abstract double getArea();

    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }


}
