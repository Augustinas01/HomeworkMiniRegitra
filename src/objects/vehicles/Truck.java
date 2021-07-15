package objects.vehicles;

import objects.Vehicle;
import objects.VehicleOwner;

public class Truck extends Vehicle {

    public Truck(VehicleOwner owner) {
        super(owner, Vehicle.TYPE_TRUCK);
    }
}
