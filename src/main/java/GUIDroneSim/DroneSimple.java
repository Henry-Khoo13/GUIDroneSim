package GUIDroneSim;

import GUIDroneSim.Directions.Direction;

public class DroneSimple extends Drone {

	private double Speed;			// angle and speed of travel
	private double Cx, Cy;
	private Direction DroneDirection;
	/**
	 * 
	 */
	public DroneSimple() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Create game Drone, size ir ay ix,iy, moving at angle ia and speed is
	 * @param ix
	 * @param iy
	 * @param ir
	 * @param di
	 * @param is
	 */
	public DroneSimple(double ix, double iy, double ir, Direction di, double is) {
		super(ix, iy, ir);
		Speed = is;
		Eaten = false;
		DroneDirection = di;
		switch(DroneDirection) {//Applying direction
			case North:
				Cx = 0*Speed;
				Cy = -1*Speed;
				break;
			case South:
				Cx = 0*Speed;
				Cy = 1*Speed;
				break;
			case East:
				Cx = 1*Speed;
				Cy = 0*Speed;
				break;
			case West:
				Cx = -1*Speed;
				Cy = 0*Speed;
				break;
		}
		//bSpeed = is;
	}

	/**
	 * checkDrone - change angle of travel if hitting wall or another Drone
	 * @param DA DroneArena
	 */

	@Override
	protected void checkDrone(DroneArena DA) {
		DroneDirection = DA.UpdateDroneDirection(x, y, rad, DroneDirection, DroneID);
		x += DA.AdjustXAgainstWall(x, y, rad, DroneDirection, Speed);
		y += DA.AdjustYAgainstWall(x, y, rad, DroneDirection, Speed);
	}

	/**
	 * adjustDrone
	 * Here, move Drone depending direction, applying it's change in x and y
	 */
	@Override
	protected void adjustDrone() {
		//double radAngle = bAngle*Math.PI/180;		// put angle in radians
		switch(DroneDirection) {
			case North:
				Cx = 0*Speed;
				Cy = -1*Speed;
				break;
			case South:
				Cx = 0*Speed;
				Cy = 1*Speed;
				break;
			case East:
				Cx = 1*Speed;
				Cy = 0*Speed;
				break;
			case West:
				Cx = -1*Speed;
				Cy = 0*Speed;
				break;
		}
		x += Cx;		// new X position
		y += Cy;		// new Y position
	}
	/**
	 * return string defining Drone type
	 */
	protected String getStrType() {
		return "Drone "+DroneID+" " +" Moving: "+DroneDirection+ " ";
	}

}
