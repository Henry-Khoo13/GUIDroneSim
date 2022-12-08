package GUIDroneSim;

public class DroneObject extends Drone {
    DroneObject (double ix, double iy, double ir) {
        x = ix;
        y = iy;
        rad = ir;
        DroneID = Counter++;			// set the identifier and increment class static
        col = 'B';//Colour black
        Eaten = false;
    }
    @Override
    protected void checkDrone(DroneArena DA) {}//No checks needed since it doesn't update

    @Override
    protected void adjustDrone() {}//Empty since this drone doesn't move

    /**
     * return string defining Drone type
     */
    protected String getStrType() {
        return "Object "+DroneID+" ";
    }
}
