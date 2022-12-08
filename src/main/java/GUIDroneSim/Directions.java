package GUIDroneSim;


import java.util.*;


public class Directions {

    static Random RandomDirection = new Random();
    public enum Direction {//Enum for Direction Used for applying a directional value to Simple Drone
        South,
        North,
        East,
        West;

        /**
         * getRandomDirection
         * generates a random direction based off the size of the enum
         * @return a random direction from enum direction
         */
        public static Direction getRandomDirection() {
    		return values()[RandomDirection.nextInt(values().length)];
    	}

    }

}