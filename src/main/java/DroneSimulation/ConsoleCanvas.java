package DroneSimulation;

public class ConsoleCanvas {
    private String Map[][];
    public ConsoleCanvas(int XI, int YI){
        Map = new String[XI][YI];
        for(int i = 0; i < Map.length;i++){
            for(int z = 0; z < Map[i].length;z++){
                if((i==0)||i==Map.length-1){
                    Map[i][z] = "#";
                }
                else if((z == 0) || (z ==Map[i].length-1)){
                    Map[i][z] = "#";
                }
                else{
                    Map[i][z] = " ";
                }
            }
        }
    }
    public void showIt(int XI, int YI, String CharacterI){
        Map[XI][YI] = "D";
    }
    public String toString(){
        String Arena ="";
        for(int i = 0; i < Map.length;i++){
            for(int z = 0; z < Map[i].length;z++){
                Arena = Arena+Map[i][z];
            }
            Arena = Arena +"\n";
        }
        return Arena;
    }
    public static void main(String[] args) {
        ConsoleCanvas c = new ConsoleCanvas (5, 10);	// create a canvas
        c.showIt(2,3,"D");								// add a Drone at 4,3
        System.out.println(c.toString());				// display result
    }

}
