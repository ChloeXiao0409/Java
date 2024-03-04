package Task1_Checker_Game;
public class display {

    private static char[][] board = new char[8][8];

    public static void main(String[] args) {
        board = initialiseBoard();
        displayBoard();
    }

    private static char[][] initialiseBoard() {
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
        return board;
    }

    // Display board method
    private static void displayBoard() {
        for (int i = 0; i < 8; i++) {
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + "|");
            }
            System.out.println();
        }
        System.out.print("\n");
        System.out.print("\n");
    }
}


