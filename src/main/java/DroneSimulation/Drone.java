package DroneSimulation;

import java.util.*;
public class Drone {
    private int XPos;
    private int YPos;
    private static int count = 0;
    private int DroneID;
    private Directions Dir;
    public Drone(int PosxI, int PosyI){
        XPos = PosxI;
        YPos = PosyI;
    }
    public String toString(){
        return "Drone is at "+XPos+" "+YPos;
    }
    public boolean isHere(int XI, int YI){
        if((XI==XPos)&&(YI==YPos)){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String[] args) {
        Drone d = new Drone(5, 3);		// create drone
        System.out.println(d.toString());	// print where is
    }
}
