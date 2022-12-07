package GUIDroneSim;

import GUIDroneSim.Directions.Direction;

public abstract class Drone {
	protected double x, y, rad;						// position and size of ball
	protected int Cx,Cy;
	protected char col;								// used to set colour
	static int ballCounter = 0;						// used to give each ball a unique identifier
	protected int ballID;							// unique identifier for item
	Drone() {
		this(100, 100, 10);
	}
	/**
	 * construct a ball of radius ir at ix,iy
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	Drone (double ix, double iy, double ir) {
		x = ix;
		y = iy;
		rad = ir;
		ballID = ballCounter++;			// set the identifier and increment class static
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
	 * return radius of ball
	 * @return
	 */
	public double getRad() { return rad; }
	/** 
	 * set the ball at position nx,ny
	 * @param nx
	 * @param ny
	 */
	public void setXY(double nx, double ny) {
		x = nx;
		y = ny;
	}
	/**
	 * return the identity of ball
	 * @return
	 */
	public int getID() {return ballID; }
	/**
	 * draw a ball into the interface bi
	 * @param bi
	 */
	public void drawBall(MyCanvas mc) {
		mc.showCircle(x, y, rad, col);
	}
	protected String getStrType() {
		return "Ball";
	}
	/** 
	 * return string describing ball
	 */
	
	public String toString() {
		return getStrType()+ " at "+Math.round(x)+", "+Math.round(y);
	}
	/**
	 * abstract method for checking a ball in arena b
	 * @param b
	 */
	protected abstract void checkBall(DroneArena b);
	
	protected abstract void checkDrone(DroneArena b);
	/**
	 * abstract method for adjusting a ball (?moving it)
	 */
	protected abstract void adjustBall();
	
	protected abstract void adjustDrone();
	/**
	 * is ball at ox,oy size or hitting this ball
	 * @param ox
	 * @param oy
	 * @param or
	 * @return true if hitting
	 */
	public boolean hitting(double ox, double oy, double or) {
		return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or+rad)*(or+rad);
	}		// hitting if dist between ball and ox,oy < ist rad + or
	
	/** is ball hitting the other ball
	 * 
	 * @param oBall - the other ball
	 * @return true if hitting
	 */
	public boolean hitting (Drone oBall) {
		return hitting(oBall.getX(), oBall.getY(), oBall.getRad());
	}
}
