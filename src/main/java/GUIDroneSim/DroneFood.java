package GUIDroneSim;

public class DroneFood extends Drone {

    DroneFood(double ix, double iy, double ir) {
        x = ix;
        y = iy;
        rad = ir;
        Eaten = false;
        col = 'g';//Colour black
    }

    /**
     * checkDrone
     * Checks if a drone has hit the food drone
     * @param DA
     */
    @Override
    protected void checkDrone(DroneArena DA) {
        Eaten = DA.DroneHitFood(x,y,rad,DroneID);
    }//Checks if drone has been eaten

    @Override
    protected void adjustDrone() {}//Empty since this drone doesn't move

    /**
     * return string defining Drone type
     */
    protected String getStrType() {
        return "Food "+DroneID+" ";
    }
}
