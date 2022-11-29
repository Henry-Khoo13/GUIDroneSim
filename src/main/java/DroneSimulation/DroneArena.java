package DroneSimulation;

import java.util.*;
public class DroneArena {
    Random RG;
    ArrayList<Drone> DroneList;
    private int MaxX;
    private int MaxY;
    private int MinX;
    private int MinY;
    private Drone TDrone;
    public DroneArena(int MaxXI,int MaxYI){
        MaxX = MaxXI;
        MaxY = MaxYI;
        MinX = 0;
        MinY = 0;
        RG = new Random();
        DroneList = new ArrayList<Drone>();
    }
    public int getX(){
        return MaxX;
    }
    public int getY(){
        return MaxY;
    }
    public void addDrone(){
        int XPosD,YPosD;
        do {
            XPosD = RG.nextInt(MinX+1, MaxX - 1);
            YPosD = RG.nextInt(MinY+1, MaxX - 1);
        }
        while(getDrone(XPosD,YPosD)!=null);
        TDrone = new Drone(XPosD,YPosD);//Somewhere in the middle of the Map
        DroneList.add(TDrone);
    }
    public Drone getDrone(int XI, int YI){
        for(int i = 0; i < DroneList.size();i++) {
            if(DroneList.get(i).isHere(XI,YI)==true){
                return DroneList.get(i);
            }
        }
        return null;
    }
    public String toString(){
        String Temp = "Arena Size: "+MaxX+" "+ MaxY+"\n";
        for(int i = 0; i < DroneList.size();i++) {
            Temp = Temp + DroneList.get(i).toString() + "\n";
        }
        return Temp;
    }
    public void ShowDrones(ConsoleCanvas C){
        for(int i = 0; i < DroneList.size();i++) {
            DroneList.get(i).displayDrone(C);
        }
    }
    public static void main(String[] args) {
        DroneArena a = new DroneArena(20, 10);	// create drone arena
        a.addDrone();
        a.addDrone();
        System.out.println(a.toString());	// print where is
    }

}
