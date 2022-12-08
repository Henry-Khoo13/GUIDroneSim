package GUIDroneSim;

import java.util.ArrayList;
import java.util.*;
import GUIDroneSim.Directions.Direction;
import java.util.random.RandomGenerator;

public class DroneArena {
	double xSize, ySize;						// size of arena
	private ArrayList<Drone> DroneList;			// array list of all Drones in arena

	private double GeneralDroneRad = 15;
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
		DroneList = new ArrayList<Drone>();					// list of all Drones, initially empty
		DroneList.add(new DroneSimple(xS/2, yS/2, 15, Direction.getRandomDirection(), 1));	// add SimpleDrone

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
	 * draw all Drones in the arena into interface bi
	 * @param
	 */
	public void drawArena(MyCanvas mc) {
		for (Drone D : DroneList) D.drawDrone(mc);		// draw all Drones
	}

	public void checkDrones() {
		for (Drone D : DroneList) D.checkDrone(this);	// check all Drones
	}

	
	public void adjustDrones() {
		for (Drone D : DroneList) D.adjustDrone();
	}
	/**
	 * return list of strings defining each Drone
	 * @return
	 */
	public ArrayList<String> describeAll() {
		ArrayList<String> ans = new ArrayList<String>();		// set up empty arraylist
		for (Drone b : DroneList) ans.add(b.toString());			// add string defining each Drone
		return ans;												// return string list
	}
	/** 
	 * Check angle of Drone ... if hitting wall, rebound; if hitting Drone, change angle
	 * This Check is only used by the Reflection Drone
	 * @param x				Drone x position
	 * @param y				y
	 * @param rad			radius
	 * @param ang			current angle
	 * @param notID			identify of Drone not to be checked
	 * @return				new angle 
	 */
	
	public double CheckDroneAngle(double x, double y, double rad, double ang, int notID) {
		double ans = ang;
		if (x < rad || x > xSize - rad) ans = 180 - ans;
		// if ball hit (tried to go through) left or right walls, set mirror angle, being 180-angle
		if (y < rad || y > ySize - rad) ans = - ans;
		// if try to go off top or bottom, set mirror angle

		for (Drone Dr : DroneList)
			if (Dr.getID() != notID && Dr.hitting(x, y, rad)) ans = 180*Math.atan2(y-Dr.getY(), x-Dr.getX())/Math.PI;
				// check all Drones except one with given id
				// if hitting, return angle between the other Drone and this one.

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



	public void addDroneSimple() {
		boolean AddDrone = true;
		for (Drone Dr : DroneList) {
			if(Dr.hitting(xSize/2, ySize/2, 15)) AddDrone = false;
		}
		if(AddDrone == true) DroneList.add(new DroneSimple(xSize/2, ySize/2, 15, Direction.getRandomDirection(), 1));
	}
	public void addDroneReflect() {
		boolean AddDrone = true;
		for (Drone Dr : DroneList) {
			if(Dr.hitting(xSize/2, ySize/2, 15)) AddDrone = false;
		}
		if(AddDrone == true) DroneList.add(new DroneReflect(xSize/2, ySize/2, 15, 45, 1));
	}
	public void ClearDrones(){
		DroneList = new ArrayList<Drone>();
	}



	public boolean checkDroneLocationValid(double x, double y, double rad) {
		if (x < rad) return false;
		if (x > xSize - rad) return  false;
		if (y < rad) return false;
		if (y > ySize - rad) return false;
		//if((x < rad)&&(y < rad))
		for (Drone Dr : DroneList) {
			if (Dr.hitting(x, y, rad)) {
				return false;
			}
		}
		return true;
	}

	public boolean checkDroneLocationCenterValid(double x, double y, double rad) {
		if (((xSize/2)-(rad*5) < x) && (x < (xSize/2)+(rad*5))&&((ySize/2)-(rad*5) < y) && (y < (ySize/2)+(rad*5))) return false;
		return true;
	}
	public void addDroneRandomSimple(){
		Random RandomDirection = new Random();
		double Tx,Ty;
		int Counter = 0;
		try {
			do {
				Tx = RandomDirection.nextDouble(xSize);
				Ty = RandomDirection.nextDouble(ySize);
				Counter++;
				if(Counter > 100){
					throw new RuntimeException();
				}
			}
			while (!(checkDroneLocationValid(Tx, Ty, GeneralDroneRad)));
			DroneList.add(new DroneSimple(Tx, Ty, GeneralDroneRad, Direction.getRandomDirection(), 1));
		}
		catch(Exception e){
				System.out.println(e);
		}
	}

	public void addDroneRandomReflect(){
		Random RandomDirection = new Random();
		double Tx,Ty;
		int Counter = 0;
		try {
			do {
				Tx = RandomDirection.nextDouble(xSize);
				Ty = RandomDirection.nextDouble(ySize);
				Counter++;
				if(Counter > 100){
					throw new RuntimeException();
				}
			}
			while(!(checkDroneLocationValid(Tx,Ty,GeneralDroneRad)));
			DroneList.add(new DroneReflect(Tx, Ty,  15, 45, 1));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	public void addDroneObject(){
		Random RandomDirection = new Random();
		int Counter = 0;
		try {
			double Tx, Ty;
			do {
				Tx = RandomDirection.nextDouble(xSize);
				Ty = RandomDirection.nextDouble(ySize);
				Counter++;
				if(Counter > 100){
					throw new RuntimeException();
				}
			}
			while (!(checkDroneLocationValid(Tx, Ty, GeneralDroneRad)) || !(checkDroneLocationCenterValid(Tx, Ty, GeneralDroneRad)));

			DroneList.add(new DroneObject(Tx, Ty, 15));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
