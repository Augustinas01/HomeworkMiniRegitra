package objects.vehicles;

import objects.Vehicle;
import objects.VehicleOwner;

public class Supercar extends Car{

    public Supercar(VehicleOwner owner) {
        super(owner, Vehicle.TYPE_SUPERCAR);
    }
}
