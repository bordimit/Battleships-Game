package Battleships;

import java.util.*;

public class BSGame {

	public final static int FREE = 0; //constants to represent the state 
	public final static int TAKEN = 1; //of a single field in the grid/battlefield
	public final static int FREE_HIT = 2; //e.g. FREE represents a field with no ship that 
	public final static int TAKEN_HIT = 3; //also has not been fired at
		
	public static Random rand = new Random();	 //random number generator

	public static void show(int[][] board){	//shows the solution
		System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9 \t10");
	    for (int i = 0; i < 10; i++) {
	    	System.out.print(myIntToChar(i + 1) + "");
	    	for (int j = 0; j < 10; j++) {
	    		if ((board[i][j] == TAKEN) || (board[i][j] == TAKEN_HIT)) {
	    			System.out.print("\t" + "X");
	    		} else {
	    			System.out.print("\t" + " ");
	            }
	    	}
	    	System.out.println();
	    }	    
	}

	public static void displayCurrentState(int[][] board) {	//updates the board
		System.out.println("\t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9 \t10");
		for (int i = 0; i < 10; i++) {
			System.out.print(myIntToChar(i + 1) + "");
	    	for (int j = 0; j < 10; j++) {
	    		if (board[i][j] == TAKEN_HIT) {
	    			System.out.print("\t" + "X");
	    		} else if (board[i][j] == FREE_HIT) {
	    			System.out.print("\t" + "-");
	            } else {
	            	System.out.print("\t" + ".");
	            }
	    	}
	    	System.out.println();
	    }
	}
	
	public static void initialize(int[][] board){
		if ((board.length != 10) || (board[0].length != 10)){
			System.out.println("Error: Please provide a 10x10 board for the game!");
		}
		else{
			for (int i = 0; i < 10; i++){
				for (int j = 0; j < 10; j++){
					board[i][j] = FREE;
				}
			}
			insertShip(board, 5);
			insertShip(board, 4);
			insertShip(board, 4);
			System.out.println("Successfully initialized the board!");
		}
	}
	
	public static void insertShip(int[][] board, int shipSize){
		boolean done = false;
		while (!done){
			int pos1 = rand.nextInt(10);
			int pos2 = rand.nextInt((10 - shipSize) + 1);
			int direction = rand.nextInt(2);
			if (direction == 0){	//case horizontal
				boolean noOverlap = true;
				for (int i = 0; i < shipSize; i++){
					if(board[pos1][pos2+i] != FREE) {
                        noOverlap = false;
                        break;
                    }
				}
				if (!noOverlap){
					continue;	//jump to start of the loop and generate new values
				}
				for (int i = 0; i < shipSize; i++){
					board[pos1][pos2+i] = TAKEN;
				}
				done = true;
			}else{		//case vertical
				boolean noOverlap = true;
                for(int i = 0; i < shipSize; i++) {
                    if(board[pos2+i][pos1] != FREE) {
                        noOverlap = false;
                        break;
                    }
                }
                if(!noOverlap) {
                    continue;
                }
                for (int i = 0; i < shipSize; i++){
                	board[pos2+i][pos1] = TAKEN;
                }
                done = true;
			}
		}
	}
	
	public static String fireAt(int[][] board, int x, int y){
		if ((x>9 || x<0) || (y>9 || y<0)){
			return "Error: Please make sure you enter valid coordinates!";
		}
		if (board[y][x] == TAKEN) {
			board[y][x] = TAKEN_HIT;
			return "You have hit!";
		}
		else{
	        board[y][x] = FREE_HIT;
	        return "You have missed!";
		}
	}

	public static int getTargetRow(String input){	//gets the y coordinate from the input of the user	
		char column = input.charAt(0);	
		int result = myCharToInt(column);
		return result;
	}
	
	public static int getTargetColumn(String input){		//gets the x coordinate from the input of the user		
		int result = Integer.parseInt(input.substring(1));
		return (result - 1);
	}
	
	public static int myCharToInt(char c){
		int result;
		char myC = Character.toUpperCase(c);
		switch(myC){
			case 'A': result = 0; break;
			case 'B': result = 1; break;
			case 'C': result = 2; break;
			case 'D': result = 3; break;
			case 'E': result = 4; break;
			case 'F': result = 5; break;
			case 'G': result = 6; break;
			case 'H': result = 7; break;
			case 'I': result = 8; break;
			case 'J': result = 9; break;
			default: result = 99; break;	//invalid input, will be handled in the fireAt method
		}
		return result;
	}

	public static char myIntToChar(int n){
		char result;
		switch(n){
			case 1: result = 'A'; break;
			case 2: result = 'B'; break;
			case 3: result = 'C'; break;
			case 4: result = 'D'; break;
			case 5: result = 'E'; break;
			case 6: result = 'F'; break;
			case 7: result = 'G'; break;
			case 8: result = 'H'; break;
			case 9: result = 'I'; break;
			case 10: result = 'J'; break;
			default: result = 'Z'; break;
		}
		return result;
	}
}

