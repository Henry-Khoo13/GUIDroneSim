package GUIDroneSim;

public class DroneReflect extends Drone{

    double Angle, Speed;			// angle and speed of travel
    /**
     *
     */
    public DroneReflect() {
        // TODO Auto-generated constructor stub
    }

    /** Create game Drone, size ir ay ix,iy, moving at angle ia and speed is
     * @param ix
     * @param iy
     * @param ir
     * @param ia
     * @param is
     */
    public DroneReflect(double ix, double iy, double ir, double ia, double is) {
        super(ix, iy, ir);
        Angle = ia;
        Speed = is;
        col = 'g';
    }

    /**
     * checkDrone - change angle of travel if hitting wall or another Drone
     * @param DA   DroneArena
     */
    @Override
    protected void checkDrone(DroneArena DA) {
        Angle = DA.CheckDroneAngle(x, y, rad, Angle, DroneID);
    }

    /**
     * adjustDrone
     * Here, move Drone depending on speed and angle
     */
    @Override
    protected void adjustDrone() {
        double radAngle = Angle*Math.PI/180;		// put angle in radians
        x += Speed * Math.cos(radAngle);		// new X position
        y += Speed * Math.sin(radAngle);		// new Y position
    }
    /**
     * return string defining Drone type
     */
    protected String getStrType() {
        return "Reflect Drone "+DroneID+" ";
    }


}
