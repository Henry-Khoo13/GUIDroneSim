package GUIDroneSim;

import GUIDroneSim.Directions.Direction;

public abstract class Drone {
	protected double x, y, rad;						// position and size of Drone
	protected int Cx,Cy;						//The amount each x and Y will change
	protected char col;								// used to set colour
	static int Counter = 0;						// used to give each Drone a unique identifier
	protected int DroneID;							// unique identifier for item
	Drone() {
		this(100, 100, 10);
	}
	/**
	 * construct a Drone of radius ir at ix,iy
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	Drone (double ix, double iy, double ir) {
		x = ix;
		y = iy;
		rad = ir;
		DroneID = Counter++;			// set the identifier and increment class static
		col = 'b';
	}
	/**
	 * return x position
	 * @return
	 */
	public double getX() { return x; }
	/**
	 * return y position
	 * @return
	 */
	public double getY() { return y; }
	/**
	 * return radius of Drone
	 * @return
	 */
	public double getRad() { return rad; }
	/** 
	 * set the Drone at position nx,ny
	 * @param nx
	 * @param ny
	 */
	public void setXY(double nx, double ny) {
		x = nx;
		y = ny;
	}
	/**
	 * return the identity of Drone
	 * @return
	 */
	public int getID() {return DroneID; }

	/**
	 * draw a Drone into the interface bi
	 * @param
	 */
	public void drawDrone(MyCanvas mc) {
		mc.showCircle(x, y, rad, col);
	}

	protected String getStrType() {
		return "Drone";
	}

	/** 
	 * return string describing Drone
	 */
	public String toString() {
		return getStrType()+ " at "+Math.round(x)+", "+Math.round(y);
	}

	/**
	 * abstract method for checking a Drone in arena b
	 * @param DA
	 */
	protected abstract void checkDrone(DroneArena DA);


	/**
	 * abstract method for adjusting a Drone (?moving it)
	 */

	protected abstract void adjustDrone();
	/**
	 * is Drone at ox,oy size or hitting this Drone
	 * @param ox
	 * @param oy
	 * @param or
	 * @return true if hitting
	 */
	public boolean hitting(double ox, double oy, double or) {
		return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or+rad)*(or+rad);
	}		// hitting if dist between Drone and ox,oy < ist rad + or
	
	/** is Drone hitting the other Drone
	 * 
	 * @param oDrone - the other Drone
	 * @return true if hitting
	 */
	public boolean hitting (Drone oDrone) {
		return hitting(oDrone.getX(), oDrone.getY(), oDrone.getRad());
	}
}
