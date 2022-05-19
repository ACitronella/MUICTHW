//ID: 6388003
//Name: Phuriwat Angkoondittaphong
//Section: 1

import java.util.ArrayList;

public class SmartPhone extends Product {

    private String model = null; //Model of this smartphone
    private boolean used = false;
    //True if this is a used phone, false if this is a new phone.
    private ArrayList<App> apps = null; //list of apps installed on this phone.

    public SmartPhone(double _basePrice, int _age, String _model, boolean _used) {
        super(_basePrice, _age);
        //TODO Auto-generated constructor stub
        this.used = _used;
        this.model = _model;
        this.apps = new ArrayList<>();
    }

    @Override
    public double getPrice() {
        // TODO Auto-generated method stub
        double s = super.getPrice() * Math.pow(0.95, super.getAge()) + this.apps.stream().mapToDouble(a -> a.getPrice()).sum();
        if(this.used){
            s = s/2.0;
        }
        return s;
    }

    public String getModel() {
        return this.model;
    }

    public void install(App newApp){
        this.apps.add(newApp);
    }

    public void install(App[] newApps){
        for(App a: newApps){
            this.install(a);
        }
    }

    public void updateApp(String appTitle){
        for(App a: this.apps){
            if(a.getAppTitle().equals(appTitle)){
                a.update();
                return;
            }
        }
        // this.apps.stream().filter(a -> a.getAppTitle().equals(appTitle)).forEach(a -> a.update());
    }

    public void updateApp(){
        // this.apps.stream().forEach(a -> a.update());
        for (App app : apps) {
            app.update();
        }
    }

}