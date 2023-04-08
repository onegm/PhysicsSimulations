package oneDimensionalKinematics.model;

import java.util.HashMap;

public class Marker {
    private HashMap<Double, Double> locations;

    public Marker() {
        this.locations = new HashMap<>();
    }

    public void add(double x, double y){
        locations.put(x, y);
    }

    public void reset(){
        locations = new HashMap<>();
    }

    public HashMap<Double, Double> getLocations(){
        return locations;
    }
}
