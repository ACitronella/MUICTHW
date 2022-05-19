//ID: 6388003
//Name: Phuriwat Angkoondittaphong
//Section: 1

public class App extends Product{

    private String appTitle;

    public App(double _basePrice, int _age, String _appTitle) {
        super(_basePrice, _age);
        this.appTitle = _appTitle;
    }

    @Override
    public double getPrice() {
        // TODO Auto-generated method stub
        return super.getPrice() * Math.pow(0.95, super.getAge());
    }
    
    public String getAppTitle(){
        return this.appTitle;
    }
    
    public void update(){
        super.setAge(0);
    }
}
