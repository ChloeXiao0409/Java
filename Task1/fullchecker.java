package Task1;

import java.util.Scanner;
import java.lang.Math;

public class fullchecker {

    /**
     * Represents the game board as a 2D array.
     * 'B' for Black pieces, 'W' for White pieces, 'E' for Empty spaces, 'BK' for
     * Black Kings, and 'WK' for White Kings.
     */
    private static char[][] board = new char[8][8];
    private static char currentPlayer = 'b'; // Black move first

    /**
     * Initialises the board with pieces in their starting positions.
     */

    private static void initialiseBoard() {
        // Implement this method to fill the board array with pieces in their starting
        // positions.

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                // Pieces located on the place which row + col is odd number
                if ((row + col) % 2 == 1) {
                    if (row < 3) {
                        board[row][col] = 'w';
                    } else if (row > 4) {
                        board[row][col] = 'b';
                    } else {
                        board[row][col] = ' ';
                    }
                } else {
                    board[row][col] = ' ';
                }
            }
        }
    }

    /**
     * Displays the current state of the board to the console.
     */
    private static void displayBoard() {
        // Implement this method to print the board to the console.
        for (int i = 0; i < 8; i++) {
            // For the first col line of the board, dont need any other loop
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.print("\n");
    }

    /**
     * Main game loop. Handles player turns and checks for game end conditions.
     */
    private static void startGame() {
        // Implement the game loop, handling player input, turn switching, and win
        // condition checking.
        Scanner input = new Scanner(System.in);

        while (!isGameOver()) {

            // player switch
            if (currentPlayer == 'b') {
                System.out.println("Black Turn");
            } else if (currentPlayer == 'w') {
                System.out.println("White Turn");
            }
            // Get player input
            // Read the player input
            if (input.hasNextLine()) {
                String playerInput = input.nextLine();

                //user view
                if (playerInput.equals("view")) {
                    displayBoard();
                    //user exit
                } else if (playerInput.equals("exit")) {
                    System.exit(0);
                }

                // split the row and col
                String[] splitInput = playerInput.split(" to ");
                if (!playerInput.contains(" to ")) {
                    System.out.println("Error!");
                }
                // turn row & col into int num
                if (splitInput.length == 2) {
                    int fromRow = Character.getNumericValue(splitInput[0].charAt(1)) - 1;
                    int fromCol = Character.toUpperCase(splitInput[0].charAt(0)) - 'A';
                    int toRow = Character.getNumericValue(splitInput[1].charAt(1)) - 1;
                    int toCol = Character.toUpperCase(splitInput[1].charAt(0)) - 'A';

                    // process the move
                    isValidMove(fromRow, fromCol, toRow, toCol);
                    displayBoard();

                    boolean correct = isValidMove(fromRow, fromCol, toRow, toCol);
                    if (correct) {
                        // player switch
                        if (currentPlayer == 'b') {
                            currentPlayer = 'w';
                        } else if (currentPlayer == 'w') {
                            currentPlayer = 'b';
                        }

                    } else {
                        System.out.println("Error!");
                        System.out.print("\n");
                        System.exit(0);
                    }

                } else {
                    // System.out.println("Error!");
                    System.out.print("\n");
                    // System.exit(0);
                }
            }
        }
        System.exit(0);
    }


    
    private static boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Implement this method to check if a move is legal according to the rules of
        // Checkers.

        //if the index over boundaries
        if (fromRow < 8 && fromCol < 8 && toRow < 8 && toCol < 8 && fromRow >= 0 && fromCol >= 0 && toRow >= 0
                && toCol >= 0) {
            //Check Black
            if (currentPlayer == 'b' && board[fromRow][fromCol] == 'b' && board[toRow][toCol] == ' ') {
                //move black
                board[fromRow][fromCol] = ' ';
                board[toRow][toCol] = 'b';
                //if jump once
                if (Math.abs(toCol - fromCol) == 1 && (fromRow - toRow) == 1) {
                    return true;
                    //if jump twice
                } else if (Math.abs(toCol - fromCol) == 2 && (fromRow - toRow) == 2) {
                    if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'b'
                            && board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] != ' ') {
                        return true;
                        
                    } else {
                        board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ' ';
                    }
                }
            //Check White
        } else if (currentPlayer == 'w' && board[fromRow][fromCol] == 'w' && board[toRow][toCol] == ' ') {
                //move white
                board[fromRow][fromCol] = ' ';
                board[toRow][toCol] = 'w';
                //if jump once
                if (Math.abs(toCol - fromCol) == 1 && (toRow - fromRow) == 1) {
                    return true;
                    // if jump twice
                } else if (Math.abs(toCol - fromCol) == 2 && (toRow - fromRow) == 2) {
                    if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'w'
                            && board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] != ' ') {
                        return true;
                    } else {
                        board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ' ';
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }
    
    //BW 判断  -> 跳一个   绝对值为1 Math.abs(toCol - fromCol) -> 目标值是否为空
    //        -> 跳两个   绝对值为2  -> 中间值为同  -> 目标为空
    //                              -> 中间可吃  中位数，绝对值/2

    private static boolean isGameOver() {
        //count if there is any left pieces of Black and White
        int countB = 0;
        int countW = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'b' || board[i][j] == 'B') {
                    countB += 1;
                } else if (board[i][j] == 'w' || board[i][j] == 'W') {
                    countW += 1;
                }
            }
        }

        if (countB == 0 || countW == 0) {
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        initialiseBoard();
        displayBoard();
        startGame();
    }
}
