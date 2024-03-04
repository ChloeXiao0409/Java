package Task1_Checker_Game;

import java.util.Scanner;
import java.lang.Math;

public class Checkers {

    //Board Decalaration
    private static char[][] board = new char[8][8];
    // Black moves first Definition
    private static char currentPlayer = 'b';

    //Board Initialization
    private static void initialiseBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                //The odd(row + col) square has pieces
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

    //Board Display
    private static void displayBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.print("\n");
    }

    //StartGame method
    private static void startGame() {
        //Scan user input
        Scanner input = new Scanner(System.in);
        //If game is not over, then start
        while (!isGameOver()) {
            //Make sure the current player before check
            if (currentPlayer == 'b') {
                // System.out.println("Black Turn");
            } else if (currentPlayer == 'w') {
                // System.out.println("White Turn");
            }

            //Check if user input invalid input
            if (input.hasNextLine()) {
                //Read the user input
                String playerInput = input.nextLine();

                //User view -> displayboard
                if (playerInput.equals("view")) {
                    displayBoard();
                    //User exit -> exit the system
                } else if (playerInput.equals("exit")) {
                    System.out.print("\n");
                    System.exit(0);
                    //Split the input, check the invalid error output: length & case sensitive
                } else {
                    String[] splitInput = playerInput.split(" to ");
                    if (splitInput.length != 2 || Character.isLowerCase(splitInput[0].charAt(0))
                            || Character.isLowerCase(splitInput[1].charAt(0))) {
                        System.out.print("Error!");
                        System.out.println("\n");

                    } else {

                        //Analyze the coordinate
                        int fromRow = Character.getNumericValue(splitInput[0].charAt(1)) - 1;
                        int fromCol = Character.toUpperCase(splitInput[0].charAt(0)) - 'A';
                        int toRow = Character.getNumericValue(splitInput[1].charAt(1)) - 1;
                        int toCol = Character.toUpperCase(splitInput[1].charAt(0)) - 'A';

                        //Call the isvalidmove method
                        boolean correct = isValidMove(fromRow, fromCol, toRow, toCol);
                        if (correct) {
                            //If move valid -> player switch
                            if (currentPlayer == 'b') {
                                currentPlayer = 'w';
                            } else if (currentPlayer == 'w') {
                                currentPlayer = 'b';
                            }
                        } else {
                            System.out.print("Error!");
                            System.out.println("\n");
                        }
                    }
                    //After move, display board
                    displayBoard();
                }
            }
        }
        System.out.print("\n");
        System.exit(0);
    }

    //Check all the validmove -> return true; invalid -> return false
    private static boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {

        //Check the boundary
        if (fromRow < 8 && fromCol < 8 && toRow < 8 && toCol < 8 && fromRow >= 0 && fromCol >= 0 && toRow >= 0
                && toCol >= 0) {

            //Check b side, make sure origin position is b or B, target position is space
            if (currentPlayer == 'b' && (board[fromRow][fromCol] == 'b' || board[fromRow][fromCol] == 'B')
                    && board[toRow][toCol] == ' ') {

                //Move option 1: Jump one step diagonally
                if (Math.abs(toCol - fromCol) == 1 && (fromRow - toRow) == 1 && toRow != 0) {
                    // Move black
                    board[fromRow][fromCol] = ' ';
                    board[toRow][toCol] = 'b';
                    return true;

                    // Also jump one step and became the B-king
                } else if (Math.abs(toCol - fromCol) == 1 && (fromRow - toRow) == 1 && toRow == 0) {
                    // Black King
                    board[fromRow][fromCol] = ' ';
                    board[toRow][toCol] = 'B';
                    return true;

                    //B king movement
                } else if (board[fromRow][fromCol] == 'B') {

                    // Jump one step diagonally
                    if (Math.abs(toCol - fromCol) == 1 && Math.abs(fromRow - toRow) == 1) {
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'B';
                        return true;

                        // Jump two step far diagonally
                    } else if (Math.abs(toCol - fromCol) == 2 && Math.abs(fromRow - toRow) == 2) {
                        //B king eat
                        if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'w'
                                || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'W') {
                            board[fromRow][fromCol] = ' ';
                            board[toRow][toCol] = 'B';
                            board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ' ';
                            return true;

                            //B king move
                        } else if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'b'
                                || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'B') {
                            board[fromRow][fromCol] = ' ';
                            board[toRow][toCol] = 'B';
                            return true;
                        }
                    }

                    //// Move option 2: Jump two step far diagonally
                } else if (Math.abs(toCol - fromCol) == 2 && (fromRow - toRow) == 2) {
                    if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'w'
                            || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'W') {

                        // b eat
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'b';
                        board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ' ';
                        return true;

                        //b move
                    } else if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'b'
                            || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'B') {
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'b';
                        return true;
                    }
                }

                //Same as b side
            } else if (currentPlayer == 'w' && (board[fromRow][fromCol] == 'w' || board[fromRow][fromCol] == 'W')
                    && board[toRow][toCol] == ' ') {
                if (Math.abs(toCol - fromCol) == 1 && (toRow - fromRow) == 1 && toRow != 7) {
                    board[fromRow][fromCol] = ' ';
                    board[toRow][toCol] = 'w';
                    return true;

                } else if (Math.abs(toCol - fromCol) == 1 && (toRow - fromRow) == 1 && toRow == 7) {
                    board[fromRow][fromCol] = ' ';
                    board[toRow][toCol] = 'W';
                    return true;

                } else if (board[fromRow][fromCol] == 'W') {
                    if (Math.abs(toCol - fromCol) == 1 && Math.abs(fromRow - toRow) == 1) {
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'W';
                        return true;

                    } else if (Math.abs(toCol - fromCol) == 2 && Math.abs(fromRow - toRow) == 2) {
                        if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'b'
                                || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'B') {
                            board[fromRow][fromCol] = ' ';
                            board[toRow][toCol] = 'W';
                            board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ' ';
                            return true;

                        } else if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'w'
                                || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'W') {
                            board[fromRow][fromCol] = ' ';
                            board[toRow][toCol] = 'W';
                            return true;
                        }
                    }
                } else if (Math.abs(toCol - fromCol) == 2 && (toRow - fromRow) == 2) {
                    if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'b'
                            || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'B') {
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'w';
                        board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ' ';
                        return true;

                    } else if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'w'
                            || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'W') {
                        // Move white
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'w';
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //Gameover definition
    private static boolean isGameOver() {
        int countB = 0;
        int countW = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'b' || board[i][j] == 'B') {
                    countB++;
                } else if (board[i][j] == 'w' || board[i][j] == 'W') {
                    countW++;
                }
            }
        }
        if (countB == 0 || countW == 0) {
            return true;
        }
        return false;
    }

    //Main method -> call every methods
    public static void main(String[] args) {
        initialiseBoard();
        displayBoard();
        startGame();
    }
}