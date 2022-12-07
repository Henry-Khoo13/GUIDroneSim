package GUIDroneSim;

import java.util.ArrayList;
import GUIDroneSim.Directions.Direction;

public class DroneArena {
	double xSize, ySize;						// size of arena
	private ArrayList<Drone> DroneList;			// array list of all balls in arena
	/**
	 * construct an arena
	 */
	DroneArena() {
		this(500, 400);			// default size
	}
	/**
	 * construct arena of size xS by yS
	 * @param xS
	 * @param yS
	 */
	DroneArena(double xS, double yS){
		xSize = xS;
		ySize = yS;
		DroneList = new ArrayList<Drone>();					// list of all balls, initially empty
		//DroneList.add(new DroneSimple(xS/2, yS/2, 50, 180, 1));	// add SimpleDrone
		DroneList.add(new DroneSimple(xS/2, yS/2, 15, Direction.getRandomDirection(), 1));	// add SimpleDrone
		//allBalls.add(new TargetBall(xS/2, 30, 15));			// add target ball
		//allBalls.add(new PaddleBall(xS/2, yS-20, 20));		// add paddle
		//allBalls.add(new BlockerBall(xS/3, yS/4, 15));		// add blocker
		//allBalls.add(new BlockerBall(2*xS/3, yS/4, 15));	// add blocker
	}
	/**
	 * return arena size in x direction
	 * @return
	 */
	public double getXSize() {
		return xSize;
	}
	/**
	 * return arena size in y direction
	 * @return
	 */
	public double getYSize() {
		return ySize;
	}
	/**
	 * draw all balls in the arena into interface bi
	 * @param bi
	 */
	public void drawArena(MyCanvas mc) {
		for (Drone b : DroneList) b.drawBall(mc);		// draw all balls
	}
	/**
	 * check all balls .. see if need to change angle of moving balls, etc 
	 */
	public void checkBalls() {
		for (Drone b : DroneList) b.checkBall(this);	// check all balls
	}
	
	public void checkDrones() {
		for (Drone b : DroneList) b.checkDrone(this);	// check all balls
	}
	/**
	 * adjust all balls .. move any moving onesadjustDrone
	 */
	public void adjustBalls() {
		for (Drone b : DroneList) b.adjustBall();
	}
	
	public void adjustDrones() {
		for (Drone b : DroneList) b.adjustDrone();
	}
	/**
	 * return list of strings defining each ball
	 * @return
	 */
	public ArrayList<String> describeAll() {
		ArrayList<String> ans = new ArrayList<String>();		// set up empty arraylist
		for (Drone b : DroneList) ans.add(b.toString());			// add string defining each ball
		return ans;												// return string list
	}
	/** 
	 * Check angle of ball ... if hitting wall, rebound; if hitting ball, change angle
	 * @param x				ball x position
	 * @param y				y
	 * @param rad			radius
	 * @param ang			current angle
	 * @param notID			identify of ball not to be checked
	 * @return				new angle 
	 */
	
	public double CheckBallAngle(double x, double y, double rad, double ang, int notID) {
		double ans = ang;
		if (x < rad || x > xSize - rad) ans = 90;
			// if ball hit (tried to go through) left or right walls, set mirror angle, being 180-angle
		if (y < rad || y > ySize - rad) ans = -0;
			// if try to go off top or bottom, set mirror angle
		
		for (Drone Dr : DroneList) 
			if (Dr.getID() != notID && Dr.hitting(x, y, rad)) ans = 180*Math.atan2(y-Dr.getY(), x-Dr.getX())/Math.PI;
				// check all balls except one with given id
				// if hitting, return angle between the other ball and this one.
		
		return ans;		// return the angle
	}
	
	public Direction UpdateDroneDirection(double x, double y, double rad, Direction D, int notID) {
		Direction TDirection = D;
		if (x < rad) return Direction.North;
		if (x > xSize - rad) return  Direction.South;
		if (y < rad) return Direction.East;
		if (y > ySize - rad) return Direction.West;
		//if((x < rad)&&(y < rad))
		for (Drone Dr : DroneList) {
			if (Dr.getID() != notID && Dr.hitting(x, y, rad)) {
				switch(D){
					case North: 
						TDirection = Direction.South;
						break;
					case East: 
						TDirection = Direction.West;
						break;
					case South: 
						TDirection = Direction.North;
						break;
					case West: 
						TDirection = Direction.East;
						break;
				}
			}
		}	
		return TDirection;
	}
	public double AdjustXAgainstWall(double x, double y, double rad, Direction D, double Speed) {
		double ans=0;
		if (x < rad) ans = (Speed*1);
		if (x > xSize - rad) ans = -(Speed*1);
		return ans;

	}
	public double AdjustYAgainstWall(double x, double y, double rad, Direction D, double Speed) {
		double ans=0;
		if (y < rad) return (Speed*1);
		if (y > ySize - rad) return -(Speed*1);
		return ans;
	}
	/**
	 * check if the target ball has been hit by another ball
	 * @param target	the target ball
	 * @return 	true if hit
	 */
	/*
	public boolean checkHit(Drone target) {
		boolean ans = false;
		for (Ball b : allBalls)
			if (b instanceof GameBall && b.hitting(target)) ans = true;
				// try all balls, if GameBall, check if hitting the target
		return ans;
	}
	*/
	public void addBall() {
		DroneList.add(new DroneSimple(xSize/2, ySize/2, 10, 60, 5));
	}
	public void addDrone() {
		boolean AddDrone = true;
		for (Drone Dr : DroneList) {
			if(Dr.hitting(xSize/2, ySize/2, 15)) AddDrone = false;
		}
		if(AddDrone == true) DroneList.add(new DroneSimple(xSize/2, ySize/2, 15, Direction.getRandomDirection(), 1));
	}
}
