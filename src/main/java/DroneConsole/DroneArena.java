package DroneConsole;

import java.util.ArrayList;
import java.util.Random;
import DroneConsole.Directions.Direction;

public class DroneArena {
    private int MaxX;
    private int MaxY;
    private int MinX;
    private int MinY;
    private Drone TDrone;
    ArrayList <Drone> DroneList;
    Random RG;

    public DroneArena (int MaxXI, int MaxYI){
        MaxX = MaxXI;
        MaxY = MaxYI;
        MinX = 0;
        MinY = 0;
        RG = new Random();
        DroneList = new ArrayList<Drone>();
        
    }

    
    public void addDrone (){
        int XPosD,YPosD;
        do {
            XPosD = RG.nextInt(MinX+1, MaxX - 1);
            YPosD = RG.nextInt(MinY+1, MaxY - 1);
        }
        while(getDrone(XPosD,YPosD)!=null);
        TDrone = new Drone(XPosD,YPosD,Direction.getRandomDirection());//Somewhere in the middle of the Map
        DroneList.add(TDrone);
        

    }

    public String toString(){
        String Text;
        Text = "Arena Size: "+MaxX+" "+MaxY+"\nDrone: " + "\n";
        for (int i = 0; i < DroneList.size(); i++) {
        	Text = Text + DroneList.get(i).toString() + "\n";
        }
        return Text;


    }

    public Drone getDrone(int x, int y){
        for (int i = 0; i < DroneList.size(); i++) {
            if (DroneList.get(i).isHere(x, y) == true) {
                return DroneList.get(i);
            } 
        }
        return null;
    }

    public void showDrones(ConsoleCanvas c){
        for(Drone d: DroneList){
            d.displayDrone(c);
        }
    }
    
    //Getter Setter for X (Length) of Drone Arena
    public int getMaxX(){
        return MaxX; 
    }
    public void SetMaxX(int x){
        MaxX = x; 
    }
       
    //Getter Setter for Y (Length) of Drone Arena
    public int getMaxY(){
        return MaxY; 
    }
    public void SetMaxY(int y){
        MaxY = y; 
    }



    public void moveAllDrones(DroneArena DA){
        for (Drone d : DroneList){
            d.MoveDrone(DA);
        }
    }

    public boolean canMoveHere(int x, int y) {
        for (Drone d : DroneList){
            if ((d.isHere(x, y)) || ((x == 0) || (x == (MaxX - 1)) || (y == 0) || (y == (MaxY - 1)))){     //check for any obsticles such as arena borders and other drones and if it is in the arena
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
		DroneArena a = new DroneArena(20, 10);	// create drone arena
		a.addDrone();
        a.addDrone();
        System.out.println(a.toString());
        a.moveAllDrones(a);
		System.out.println(a.toString());	// print where is
	}
}
