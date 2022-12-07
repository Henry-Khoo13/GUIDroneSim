package GUIDroneSim;

import GUIDroneSim.Directions.Direction;

public class DroneSimple extends Drone {

	double bAngle, bSpeed;			// angle and speed of travel
	double Cx, Cy;
	Direction DroneDirection;
	/**
	 * 
	 */
	public DroneSimple() {
		// TODO Auto-generated constructor stub
	}

	/** Create game ball, size ir ay ix,iy, moving at angle ia and speed is
	 * @param ix
	 * @param iy
	 * @param ir
	 * @param ia
	 * @param is
	 */
	public DroneSimple(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir);
		bAngle = ia;
		bSpeed = is;
	}
	public DroneSimple(double ix, double iy, double ir, Direction di, double is) {
		super(ix, iy, ir);
		bSpeed = is;
		//bAngle = ia;
		DroneDirection = di;
		switch(DroneDirection) {
			case North:
				Cx = 0*bSpeed;
				Cy = -1*bSpeed;
				break;
			case South:
				Cx = 0*bSpeed;
				Cy = 1*bSpeed;
				break;
			case East:
				Cx = 1*bSpeed;
				Cy = 0*bSpeed;
				break;
			case West:
				Cx = -1*bSpeed;
				Cy = 0*bSpeed;
				break;
		}
		//bSpeed = is;
	}

	/**
	 * checkball - change angle of travel if hitting wall or another ball
	 * @param b   ballArena
	 */
	@Override
	protected void checkBall(DroneArena b) {
		bAngle = b.CheckBallAngle(x, y, rad, bAngle, ballID);
	}
	
	@Override
	protected void checkDrone(DroneArena b) {
		DroneDirection = b.UpdateDroneDirection(x, y, rad, DroneDirection, ballID);
		x += b.AdjustXAgainstWall(x, y, rad, DroneDirection, bSpeed);
		y += b.AdjustYAgainstWall(x, y, rad, DroneDirection, bSpeed);
	}
	/**
	 * adjustBall
	 * Here, move ball depending on speed and angle
	 */
	@Override
	protected void adjustBall() {
		double radAngle = bAngle*Math.PI/180;		// put angle in radians
		x += bSpeed * Math.cos(radAngle);		// new X position
		y += bSpeed * Math.sin(radAngle);		// new Y position
	}
	
	@Override
	protected void adjustDrone() {
		//double radAngle = bAngle*Math.PI/180;		// put angle in radians
		switch(DroneDirection) {
			case North:
				Cx = 0*bSpeed;
				Cy = -1*bSpeed;
				break;
			case South:
				Cx = 0*bSpeed;
				Cy = 1*bSpeed;
				break;
			case East:
				Cx = 1*bSpeed;
				Cy = 0*bSpeed;
				break;
			case West:
				Cx = -1*bSpeed;
				Cy = 0*bSpeed;
				break;
		}
		x += Cx;		// new X position
		y += Cy;		// new Y position
	}
	/**
	 * return string defining ball type
	 */
	protected String getStrType() {
		return "Drone "+ballID+" " +" Moving: "+DroneDirection+ " ";
	}

}
