package Battleships;

import java.util.Scanner;

public class TestBSGame {

	public static void main(String[] args) {
		
		byte x = 64;
		byte y = 5;
		byte z = (byte)(x*y);
		System.out.println(z);
		int attempts = 0;
		boolean gameFinished = false;	
		Scanner myScan = new Scanner(System.in);
        int[][] board = new int[10][10];
        BSGame.initialize(board);      
        //BSGame.show(board);	//comment out this line if you don't want to see the solution at the start of the test
        System.out.println("Starting the Battleships game!");
        
        while (!gameFinished) {
            System.out.println("Enter coordinates (row, col), e.g. A5: ");
            String userInput = myScan.nextLine();
            if(userInput.equals("show")){
            	BSGame.show(board);
            	continue;
            }
            int yValue = BSGame.getTargetRow(userInput);
           	int xValue = BSGame.getTargetColumn(userInput);
           	String shotReport = BSGame.fireAt(board, xValue, yValue);
           	System.out.println(shotReport);
           	attempts++;
           	System.out.println("Board state after shot: ");
           	BSGame.displayCurrentState(board);            
            
            boolean targetsLeft = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == BSGame.TAKEN) {
                        targetsLeft = true;
                    }
                }
            }

            if (!targetsLeft) {
                gameFinished = true;
            }
        } 

        System.out.println("Good job! You have won the game in " + attempts + " shots!");
        myScan.close();
    }

}


