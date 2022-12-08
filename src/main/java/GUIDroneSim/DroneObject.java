package GUIDroneSim;

public class DroneObject extends Drone {
    DroneObject (double ix, double iy, double ir) {
        x = ix;
        y = iy;
        rad = ir;
        DroneID = Counter++;			// set the identifier and increment class static
        col = 'B';
    }
    @Override
    protected void checkDrone(DroneArena DA) {}

    @Override
    protected void adjustDrone() {}

    /**
     * return string defining Drone type
     */
    protected String getStrType() {
        return "Object "+DroneID+" ";
    }
}
