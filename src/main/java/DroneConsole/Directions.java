package DroneConsole;


import java.util.*;


public class Directions {

    static Random RandomDirection = new Random();
    public enum Direction {
        South,
        North,
        East,
        West;
    	public static Direction getRandomDirection() {
    		return values()[RandomDirection.nextInt(values().length)];
    	}
    }

    /*
    public static final Direction[] value = values();
    
    public static Direction get_random_Direction(){
        return values()[rand_direction.nextInt(values().length)];
    }

    public Direction get_next_pos(){
        return value[(this.ordinal()+1) % value.length];
    }
*/
}