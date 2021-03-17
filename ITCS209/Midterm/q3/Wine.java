//ID: 6388003
//Name: Phuriwat Angkoondittaphong
//Section: 1

public class Wine extends Product {

    private double baseVoulume;
    private double actualVolume;

    public Wine(double _basePrice, int _age, double _baseVolume) {
        super(_basePrice, _age);
        this.baseVoulume = _baseVolume;
        this.actualVolume = _baseVolume;
    }
    
    @Override
    public double getPrice() {
        // TODO Auto-generated method stub
        return super.getPrice() * this.actualVolume/this.baseVoulume * (1+0.05 * super.getAge());
    }

    public void consume(double volume){
        this.actualVolume -= volume;
    }
}
