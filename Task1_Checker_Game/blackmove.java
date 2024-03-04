package Task1_Checker_Game;

import java.util.Scanner;

public class blackmove {

    /**
     * Represents the game board as a 2D array.
     * 'B' for Black pieces, 'W' for White pieces, 'E' for Empty spaces, 'BK' for
     * Black Kings, and 'WK' for White Kings.
     */
        private static char[][] board = new char[8][8];
        private static char currentPlayer = 'b'; //Black move first

    /**
     * Initialises the board with pieces in their starting positions.
     */

    private static char[][] initialiseBoard() {
        // Implement this method to fill the board array with pieces in their starting
        // positions.

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                //Pieces located on the place which row + col is odd number
                if((row + col) % 2 == 1) {
                    if(row < 3) {
                        board[row][col] = 'w';
                    } else if(row > 4) {
                        board[row][col] = 'b';
                    } else {
                        board[row][col] = ' ';
                    }
                } else {
                    board[row][col] = ' ';
                }
            }
        }
        return board;
    }

    /**
     * Displays the current state of the board to the console.
     */
    private static void displayBoard() {
        // Implement this method to print the board to the console.
         for (int i = 0; i < 8; i++) {
            //For the first col line of the board, dont need any other loop
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * Main game loop. Handles player turns and checks for game end conditions.
     */
    private static void startGame() {
        // Implement the game loop, handling player input, turn switching, and win
        // condition checking.
        Scanner input = new Scanner(System.in);

        while(true) {

            if(currentPlayer == 'b') {
                System.out.println("Black Turn");
            } else {
                System.out.println("White Turn");
            }
            
            //Get player input
            System.out.print("Enter the move(e.g. C3 to D4): ");
            //Read the player input
            String playerInput = input.nextLine();
            //split the row and col
            String[] splitInput = playerInput.split(" to ");
            //turn row & col into int num
            int fromRow = Character.getNumericValue(splitInput[0].charAt(1)) - 1;
            int fromCol = Character.toUpperCase(splitInput[0].charAt(0)) - 'A';
            int toRow = Character.getNumericValue(splitInput[1].charAt(1)) - 1;
            int toCol = Character.toUpperCase(splitInput[1].charAt(0)) - 'A';

            //process the move
            processMove(playerInput, fromRow, fromCol, toRow, toCol);
            displayBoard();
        }
    }


    private static boolean processMove(String move, int fromRow, int fromCol, int toRow, int toCol) {
        // Implement this method to process the player's move.
        // You should validate the move and execute it if it's valid.
        if(isValidMove(fromRow, fromCol, toRow, toCol)) {
            //Black move
            board[fromRow][fromCol] = ' ';
            board[toRow][toCol] = 'b';
            System.out.println();

            return true;

        } else {
            return false;
        }

    }

    private static boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Implement this method to check if a move is legal according to the rules of
        // Checkers.
        return true;
    }

    public static void main(String[] args) {
        board = initialiseBoard();
        displayBoard();
        startGame();
    }
}
