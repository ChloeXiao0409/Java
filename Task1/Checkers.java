package Task1;

import java.util.Scanner;
import java.lang.Math;

public class Checkers {

    private static char[][] board = new char[8][8];
    private static char currentPlayer = 'b'; // Black moves first

    private static void initialiseBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
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

    private static void startGame() {
        Scanner input = new Scanner(System.in);
        while (!isGameOver()) {
            if (currentPlayer == 'b') {
                // System.out.println("Black Turn");
            } else if (currentPlayer == 'w') {
                // System.out.println("White Turn");
            }
            if (input.hasNextLine()) {
                String playerInput = input.nextLine();

                if (playerInput.equals("view")) {
                    displayBoard();
                } else if (playerInput.equals("exit")) {
                    System.out.print("\n");
                    System.exit(0);
                } else {
                    String[] splitInput = playerInput.split(" to ");
                    if (splitInput.length != 2 || Character.isLowerCase(splitInput[0].charAt(0))
                            || Character.isLowerCase(splitInput[1].charAt(0))) {
                        System.out.print("Error!");
                        System.out.println("\n");

                    } else {

                        int fromRow = Character.getNumericValue(splitInput[0].charAt(1)) - 1;
                        int fromCol = Character.toUpperCase(splitInput[0].charAt(0)) - 'A';
                        int toRow = Character.getNumericValue(splitInput[1].charAt(1)) - 1;
                        int toCol = Character.toUpperCase(splitInput[1].charAt(0)) - 'A';

                        boolean correct = isValidMove(fromRow, fromCol, toRow, toCol);
                        if (correct) {
                            // player switch
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
                    displayBoard();
                }
            }
        }
        System.out.print("\n");
        System.exit(0);
    }

    private static boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {

        if (fromRow < 8 && fromCol < 8 && toRow < 8 && toCol < 8 && fromRow >= 0 && fromCol >= 0 && toRow >= 0
                && toCol >= 0) {

            if (currentPlayer == 'b' && (board[fromRow][fromCol] == 'b' || board[fromRow][fromCol] == 'B')
                    && board[toRow][toCol] == ' ') {

                if (Math.abs(toCol - fromCol) == 1 && (fromRow - toRow) == 1 && toRow != 0) {
                    // Move black
                    board[fromRow][fromCol] = ' ';
                    board[toRow][toCol] = 'b';
                    return true;

                    // jump once and became the B-king
                } else if (Math.abs(toCol - fromCol) == 1 && (fromRow - toRow) == 1 && toRow == 0) {
                    // Black King
                    board[fromRow][fromCol] = ' ';
                    board[toRow][toCol] = 'B';
                    return true;

                } else if (board[fromRow][fromCol] == 'B') {
                    if (Math.abs(toCol - fromCol) == 1 && Math.abs(fromRow - toRow) == 1) {
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'B';
                        return true;

                    } else if (Math.abs(toCol - fromCol) == 2 && Math.abs(fromRow - toRow) == 2) {
                        if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'w'
                                || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'W') {
                            board[fromRow][fromCol] = ' ';
                            board[toRow][toCol] = 'B';
                            board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ' ';
                            return true;

                        } else if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'b'
                                || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'B') {
                            board[fromRow][fromCol] = ' ';
                            board[toRow][toCol] = 'B';
                            return true;
                        }
                    }

                } else if (Math.abs(toCol - fromCol) == 2 && (fromRow - toRow) == 2) {
                    if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'w'
                            || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'W') {
                        // Move black
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'b';
                        board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ' ';
                        return true;
                    } else if (board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'b'
                            || board[(fromRow + toRow) / 2][(fromCol + toCol) / 2] == 'B') {
                        // Move black
                        board[fromRow][fromCol] = ' ';
                        board[toRow][toCol] = 'b';
                        return true;
                    }
                }

            } else if (currentPlayer == 'w' && (board[fromRow][fromCol] == 'w' || board[fromRow][fromCol] == 'W')
                    && board[toRow][toCol] == ' ') {
                if (Math.abs(toCol - fromCol) == 1 && (toRow - fromRow) == 1 && toRow != 7) {
                    // Move white
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
        return countB == 0 || countW == 0;
    }

    public static void main(String[] args) {
        initialiseBoard();
        displayBoard();
        startGame();
    }
}