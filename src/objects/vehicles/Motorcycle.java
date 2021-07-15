package objects.vehicles;

import objects.Vehicle;
import objects.VehicleOwner;

public class Motorcycle extends Vehicle {

    public Motorcycle(VehicleOwner owner) {
        super(owner, Vehicle.TYPE_MOTORCYCLE);
    }
}
