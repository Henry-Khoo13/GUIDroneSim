package DroneConsole;
//Drones that roam the canvas

import DroneConsole.Directions.Direction;

public class Drone {

    private int PosX;//X Position of the Drone
    private int PosY;//Y Position of the Drone
    private static int Counter = 0;//Counter used to allocate unique IDs for the Drones
    private int DroneID;
    private Direction DroneDirection;

    public Drone(int x, int y) {
    	PosX = x;
    	PosY = y;
    }
    public Drone(int x, int y, Direction D) {
    	PosX = x;
    	PosY = y;
    	DroneDirection = D;
    
        Counter++;
        DroneID = Counter;
    }

    public String toString() {
        return "Drone: " + DroneID + " is at: " + PosX + "  " + PosY + " in direction " + DroneDirection;
    }
    
    //Getter Setter Functions for PositionX of Drone
    public int getPosX() {
        return PosX;
    } 
    public void setPosX(int x) {
    	PosX = x;
    }

    
    //Getter Setter Functions for PositionY of Drone
    public int getPosY(){
        return PosY;
    }
    public void setPosY(int y) {
    	PosY = y;
    }

    
    //Getter Setter Functions for Direction of Drone
    public Direction getDirection() {
        return DroneDirection;  
    }
    public void setDirection(Direction d) {
    	DroneDirection = d;
        
    }

    //Getter Setter Functions for DroneID of Drone
    public int getDroneID(){
        return DroneID;
    }

    public void setDroneID(int ID) {
    	DroneID = ID;    
    }

    /**
     * IsHere 
     * Function is to check if the Drone is at a given location
     * @param X
     * @param Y
     * @return true or false
     */
    public boolean isHere (int X, int Y) {
		if ((PosX == X) && (PosY == Y)) {
            return true;
        }		
        else {
            return false;
        }
	}

    /**
     * displayDrone
     * Displays the drone on the Canvas
     * @param c
     */
    public void displayDrone (ConsoleCanvas c){
        c.showIt(PosX, PosY, "D");
    }
/*
    public void MoveDroneRandom(DroneArena DA) {//Roll random all the time
    	
    }
    */
    public void MoveDrone(DroneArena DA) {//Next Free
        int xTemp, yTemp;
        for(int i = 0;i<4;i++) {
        	xTemp = PosX;
        	yTemp = PosY;
        	switch(DroneDirection) {
        		case North:
        			yTemp += 1;
        			if(!(DA.canMoveHere(xTemp, yTemp))) {
        				DroneDirection = Direction.East;
        			}
        			break;
        		case South:
        			yTemp -= 1;
        			if(!(DA.canMoveHere(xTemp, yTemp))) {
        				DroneDirection = Direction.West;
        			}
        			break;
        		case East:
        			xTemp += 1;
        			if(!(DA.canMoveHere(xTemp, yTemp))) {
        				DroneDirection = Direction.South;
        			}
        			break;
        		case West:
        			xTemp -= 1;
        			if(!(DA.canMoveHere(xTemp, yTemp))) {
        				DroneDirection = Direction.North;
        			}
        			break;
        			
        	}
        	if(DA.canMoveHere(xTemp, yTemp)){
				PosX = xTemp;
				PosY = yTemp;
				break;
        	}
        }
    } 
    

    public static void main(String[] args) {
        Drone d = new Drone(5, 3, Direction.South);		// create drone
        System.out.println(d.toString());	// print where is
    }

}
