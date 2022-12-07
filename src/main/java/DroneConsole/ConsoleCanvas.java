package DroneConsole;

public class ConsoleCanvas {

    private String Map[][];

    public ConsoleCanvas(int x, int y){

    	Map = new String[y][x];
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[i].length; j++) {
                if (i == 0 || i == Map.length - 1) {
                	Map[i][j] = "#";
                } else if (j == 0 || j == Map[i].length - 1) {
                	Map[i][j] = "#";
                } else {
                	Map[i][j] = " ";
                }
            }
        }

       
    }

    public void showIt(int x, int y, String LetterInput){
    	Map[y][x] = LetterInput;
    }

    public String toString(){
        String arena = "";
        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[i].length; j++) {
                arena = arena + Map[i][j];
                
            }
            arena = arena + "\n";
        }
        return arena;
    }

    public static void main(String[] args) {
        for(int i = 0;i<4;i++) {
        	System.out.println("Test\n");
        	break;
        }
		ConsoleCanvas c = new ConsoleCanvas (15, 20);	// create a canvas
		c.showIt(4,3,"D");								// add a Drone at 4,3
		System.out.println(c.toString());				// display result
	}

}
