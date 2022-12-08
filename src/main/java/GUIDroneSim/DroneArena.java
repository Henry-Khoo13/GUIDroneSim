package GUIDroneSim;

import java.util.ArrayList;
import java.util.*;
import GUIDroneSim.Directions.Direction;
import java.util.random.RandomGenerator;

public class DroneArena {
	double xSize, ySize;						// size of arena

	private ArrayList<Drone> DroneList;			// array list of all Drones in arena
	private int score;
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
		score = 0;
	}
	/**
	 * return arena score
	 * @return
	 */
	public int getScore() {
		return score;
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

	
	public void adjustDrones() {for (Drone D : DroneList) D.adjustDrone();}     //Adjust all the drone positions

	/**
	 * removeDrones
	 * Removes drones that have been eaten.
	 */
	public void removeDrones() {
		for(int i = 0; i < DroneList.size();i++){
			if(DroneList.get(i).getEaten()){
				DroneList.remove(i);
				score++;
			}
		}

	}//Adjust all the drone positions
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

	/**
	 * UpdateDronePosition
	 * Evaluate if the Drone is against a wall or colliding with another drone
	 * If it isn't continue in the same direction by returning the same input direction
	 * If it is colliding with something, return a new direction based on what it's colliding with
	 * @param x
	 * @param y
	 * @param rad
	 * @param D
	 * @param notID
	 * @return
	 */
	public Direction UpdateDroneDirection(double x, double y, double rad, Direction D, int notID) {
		Direction TDirection = D;//Temporary Direction
		if (x < rad) return Direction.North;//If it's colliding with the West  wall go north
		if (x > xSize - rad) return  Direction.South;//If it's colliding with the East  wall go South
		if (y < rad) return Direction.East;//If it's colliding with the North  wall go East
		if (y > ySize - rad) return Direction.West;//If it's colliding with the North  South go West
		//if((x < rad)&&(y < rad))
		for (Drone Dr : DroneList) {//If it's colliding with a drone reverse the direction of the drone
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
		return TDirection;//return direction
	}

	/**
	 * If Drone hits food return true to indicate it's eaten
	 * @param x
	 * @param y
	 * @param rad
	 * @param notID
	 * @return
	 */
	public boolean DroneHitFood(double x, double y, double rad, int notID) {

		for (Drone Dr : DroneList) {//If it's colliding with a drone reverse the direction of the drone
			if (Dr.getID() != notID && Dr.hitting(x, y, rad)) {
				return true;
			}
		}
		return false;//return direction
	}

	/**
	 * AdjustXAgainstWall
	 * To prevent the drone from constantly grinding against the wall affecting the checkdrone function
	 * This function provides X coordinates to pull the drone away from the Wall
	 * @param x
	 * @param y
	 * @param rad
	 * @param D
	 * @param Speed
	 * @return X coordinate adjustment
	 */
	public double AdjustXAgainstWall(double x, double y, double rad, Direction D, double Speed) {
		double ans=0; //Temporary X var
		if (x < rad) ans = (Speed*1);//If the drone is hitting the west wall shift the drone to the east by one speed movement
		if (x > xSize - rad) ans = -(Speed*1);//If the drone is hitting the east wall shift the drone to the west by one speed movement
		return ans; //return the adjustment to X

	}

	/**
	 * 	 AdjustYAgainstWall
	 * 	 to prevent the drone from constantly grinding against the wall affecting the checkdrone function
	 * 	 This function provides Y coordinates to pull the drone away from the Wall
	 * @param x
	 * @param y
	 * @param rad
	 * @param D
	 * @param Speed
	 * @return Y coordinates Adjustment
	 */
	public double AdjustYAgainstWall(double x, double y, double rad, Direction D, double Speed) {
		double ans=0; //Temporary Y var
		if (y < rad) return (Speed*1);//If the drone is hitting the North wall shift the drone to the south by one speed movement
		if (y > ySize - rad) return -(Speed*1);//If the drone is hitting the South wall shift the drone to the North by one speed movement
		return ans;
	}


	/**
	 * AddDroneSimple
	 * adds a simple drone to the dronelist
	 * to the center
	 */
	public void addDroneSimple() {
		boolean AddDrone = true;
		for (Drone Dr : DroneList) {//checks if any drone is on the center location before placing the drone
			if(Dr.hitting(xSize/2, ySize/2, 15)) AddDrone = false;
		}
		if(AddDrone == true) DroneList.add(new DroneSimple(xSize/2, ySize/2, 15, Direction.getRandomDirection(), 1));
	}

	/**
	 * AddDroneReflect
	 * adds a reflect drone to the dronelist
	 * to the center
	 */
	public void addDroneReflect() {
		boolean AddDrone = true;
		for (Drone Dr : DroneList) {//checks if any drone is on the center location before placing the drone
			if(Dr.hitting(xSize/2, ySize/2, 15)) AddDrone = false;
		}
		if(AddDrone == true) DroneList.add(new DroneReflect(xSize/2, ySize/2, 15, 45, 1));
	}


	public void ClearDrones(){//Clears the drone list
		DroneList = new ArrayList<Drone>();
		score=0;
	}


	/**
	 * Checks if the drone location is valid
	 * Used in the adding of the drones in random locations
	 * @param x
	 * @param y
	 * @param rad
	 * @return
	 */
	public boolean checkDroneLocationValid(double x, double y, double rad) {
		/*
		Checks if the Drone is against a wall
		 */
		if (x < rad) return false;
		if (x > xSize - rad) return  false;
		if (y < rad) return false;
		if (y > ySize - rad) return false;

		for (Drone Dr : DroneList) {//Checks if the drone is on another drone
			if (Dr.hitting(x, y, rad)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * checkDroneLocationCenterValid
	 * Used to prevent placing objects on the center which would block the add drone center function
	 * @param x
	 * @param y
	 * @param rad
	 * @return
	 */
	public boolean checkDroneLocationCenterValid(double x, double y, double rad) {
		if (((xSize/2)-(rad*3) < x) && (x < (xSize/2)+(rad*3))&&((ySize/2)-(rad*3) < y) && (y < (ySize/2)+(rad*3))) return false;
		return true;
	}

	/**
	 * addDroneRandomSimple
	 * Add a simple drone to a random location
	 */
	public void addDroneRandomSimple(){
		Random RandomDirection = new Random();
		double Tx,Ty;
		int Counter = 0;
		try {
			do {//Generates a random location and checks if the location is valid before placing the drone
				Tx = RandomDirection.nextDouble(xSize);
				Ty = RandomDirection.nextDouble(ySize);
				Counter++;
				if(Counter > 100){//used for exception handling to stop the loop if it goes too far and to generate an error for the user
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
	/**
	 * addDroneRandomReflect
	 * Add a reflect drone to a random location
	 */
	public void addDroneRandomReflect(){
		Random RandomDirection = new Random();
		double Tx,Ty;
		int Counter = 0;
		try {
			do {//Generates a random location and checks if the location is valid before placing the drone
				Tx = RandomDirection.nextDouble(xSize);
				Ty = RandomDirection.nextDouble(ySize);
				Counter++;
				if(Counter > 100){//used for exception handling to stop the loop if it goes too far and to generate an error for the user
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

	/**
	 * addDroneObject
	 * Add a Object drone to a random location
	 */
	public void addDroneObject(){
		Random RandomDirection = new Random();
		int Counter = 0;
		double Tx, Ty;
		try {
			do {//Generates a random location and checks if the location is valid before placing the drone
				Tx = RandomDirection.nextDouble(xSize);
				Ty = RandomDirection.nextDouble(ySize);
				Counter++;
				if(Counter > 100){//used for exception handling to stop the loop if it goes too far and to generate an error for the user
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
	public void addDroneFood(double xi, double yi){
		try {
			if ((checkDroneLocationValid(xi, yi, GeneralDroneRad)) && (checkDroneLocationCenterValid(xi, yi, GeneralDroneRad)))
				DroneList.add(new DroneFood(xi, yi, 15));
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
