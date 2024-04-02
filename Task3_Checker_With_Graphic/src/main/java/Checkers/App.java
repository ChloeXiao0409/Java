package Checkers;

//import org.reflections.Reflections;
//import org.reflections.scanners.Scanners;
import processing.core.PApplet;
import processing.core.PImage;
import processing.data.JSONObject;
import processing.core.PFont;
import processing.event.MouseEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.io.*;
import java.util.*;

public class App extends PApplet {

    public static final int CELLSIZE = 48;
    public static final int SIDEBAR = 0;
    public static final int BOARD_WIDTH = 8;
    public static final int[] BLACK_RGB = { 181, 136, 99 };
    public static final int[] WHITE_RGB = { 240, 217, 181 };
    public static final float[][][] coloursRGB = new float[][][] {
            // default - white & black
            {
                    { WHITE_RGB[0], WHITE_RGB[1], WHITE_RGB[2] },
                    { BLACK_RGB[0], BLACK_RGB[1], BLACK_RGB[2] }
            },
            // green
            {
                    { 105, 138, 76 }, // when on white cell
                    { 105, 138, 76 } // when on black cell
            },
            // blue
            {
                    { 196, 224, 232 },
                    { 170, 210, 221 }
            }
    };

    public static int WIDTH = CELLSIZE * BOARD_WIDTH + SIDEBAR;
    public static int HEIGHT = BOARD_WIDTH * CELLSIZE;

    public static final int FPS = 60;

    /* --------------------------------------- */
    // DATA STORAGE
    /* --------------------------------------- */
    private Cell[][] board;
    CheckersPiece currentSelected;
    private HashSet<Cell> selectedCells;
    private HashMap<Character, HashSet<CheckersPiece>> piecesInPlay = new HashMap<>();
    private char currentPlayer = 'w';
    private Cell selectedCell = null;

    /* --------------------------------------- */
    /* --------------------------------------- */

    public App() {

    }

    // public Cell getSelectedCell() {
    // return this.selectedCell;
    // }

    /**
     * Initialise the setting of the window size.
     */
    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
    }

    @Override
    public void setup() {
        frameRate(FPS);

        // Set up the data structures used for storing data in the game
        this.board = new Cell[BOARD_WIDTH][BOARD_WIDTH];
        HashSet<CheckersPiece> w = new HashSet<>();
        HashSet<CheckersPiece> b = new HashSet<>();
        piecesInPlay.put('w', w);
        piecesInPlay.put('b', b);

        for (int i = 0; i < board.length; i++) {
            for (int i2 = 0; i2 < board[i].length; i2++) {
                board[i][i2] = new Cell(i2, i);

                if ((i2 + i) % 2 == 1) {
                    if (i < 3) {
                        // white piece
                        board[i][i2].setPiece(new CheckersPiece('w'));
                        w.add(board[i][i2].getPiece());
                    } else if (i >= 5) {
                        // black piece
                        board[i][i2].setPiece(new CheckersPiece('b'));
                        b.add(board[i][i2].getPiece());
                    }
                }

            }
        }
    }

    /**
     * Receive key pressed signal from the keyboard.
     */
    // @Override
    // public void keyPressed(){

    // }

    /**
     * Receive key released signal from the keyboard.
     */
    // @Override
    // public void keyReleased(){

    // }

    @Override
    public void mousePressed(MouseEvent e) {
        // Check if the user clicked on a piece which is theirs - make sure only
        // whoever's current turn it is, can click on pieces
        int x = e.getX();
        int y = e.getY();

        if (x < 0 || x >= App.WIDTH || y < 0 || y >= App.HEIGHT)
            return;

        Cell clicked = board[y / App.CELLSIZE][x / App.CELLSIZE];
        // System.out.println("Mouse clicked at Cell: (" + clicked.getX() + ", " + clicked.getY() + ")");
        if (clicked.getPiece() != null && clicked.getPiece().getColour() == currentPlayer) {
            // valid piece to click
            if (clicked.getPiece() == currentSelected) {
                // System.out.println("Deselecting current piece.");
                currentSelected = null;
            } else {
                // System.out.println("Selecting new piece.");
                currentSelected = clicked.getPiece();
            }

        }

        if (currentSelected != null) {
            // Check if the clicked cell is one of the available move cells
            HashSet<Cell> availableMoves = currentSelected.getAvailableMoves(board);
            // System.out.println(piecesInPlay);

            if (clicked.getPiece() == null) {
                boolean isValidMove = false; // Flag to track valid move
                // System.out.println("Available Moves:");
                // availableMoves cant be null otherwise it will show null point
                if (availableMoves != null) {
                    for (Cell availableMove : availableMoves) {
                        int cellX = availableMove.getX();
                        int cellY = availableMove.getY();
                        // System.out.println("(" + cellX + ", " + cellY + ")");
                        // System.out.println("Checking move: (" + cellX + ", " + cellY + ")");
                        // System.out.println("CurrentSelected Position: (" + clicked.getX() + ", " + clicked.getY() + ")");

                        // System.out.println(availableMoves.contains(clicked));
                        if (cellX == clicked.getX() && cellY == clicked.getY()) {
                            isValidMove = true;
                            // Check if it's a capture move
                            if (Math.abs(cellX - currentSelected.getPosition().getX()) == 2 &&
                                    Math.abs(cellY - currentSelected.getPosition().getY()) == 2) {
                                // Calculate the coordinates of the middle cell
                                int midX = (clicked.getX() + currentSelected.getPosition().getX()) / 2;
                                int midY = (clicked.getY() + currentSelected.getPosition().getY()) / 2;
                                // Get the middle cell
                                Cell middleCell = board[midY][midX];
                                // Check if there's a piece in the middle cell
                                if (middleCell.getPiece() != null) {
                                    // Check if the piece belongs to the opponent
                                    if (middleCell.getPiece().getColour() != currentPlayer) {
                                        // Remove the piece from the middle cell
                                        piecesInPlay.get(middleCell.getPiece().getColour()).remove(
                                                middleCell.getPiece());
                                        middleCell.setPiece(null);
                                        // redraw();
                                    }
                                }
                            }
                            piecesInPlay.get(currentSelected.getColour()).remove(currentSelected);
                         
                            // Remove the piece from its previous cell
                            currentSelected.getPosition().setPiece(null);
                            // Move the piece to the clicked cell
                            clicked.setPiece(currentSelected); // here saved the clicked position, need to change the piece
                            currentSelected.setPosition(clicked);
                            // Add the piece to its new position in the HashSet
                            piecesInPlay.get(currentSelected.getColour()).add(currentSelected);
                            // for promote the king!
                            currentSelected.move();
                            // switch player
                            switchPlayerTurn();
                            // Deselect the current piece
                            currentSelected = null;
                            
                            // -------------------------------------------------------------
                            // check if the middle piece has removed
                            // System.out.println("Pieces in play:");
                            // for (Map.Entry<Character, HashSet<CheckersPiece>> entry : piecesInPlay.entrySet()) {
                            //     char color = entry.getKey();
                            //     HashSet<CheckersPiece> pieces = entry.getValue();
                            //     System.out.println("Color: " + color);
                            //     for (CheckersPiece piece : pieces) {
                            //         Cell position = piece.getPosition();
                            //         if (position != null) {
                            //             System.out.println("Piece at (" + position.getX() + ", " + position.getY() + ")");
                            //         } else {
                            //             System.out.println("Piece is not on the board.");
                            //         }
                            //     }
                            // }
                            // ---------------------------------------------------------------

                        }
                       
                    }
                }
            }
        }
    }


    public boolean isMoved() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Cell cell = board[i][j];
                if (cell.getPiece() != null && cell.getPiece().hasMoved()) {
                    return true;
                }
            }
        }
        return false;
    }

    // if isMoved -> call swtich

    private void switchPlayerTurn() {
        // change player
        if (currentPlayer == 'w') {
            currentPlayer = 'b';
        } else {
            currentPlayer = 'w';
        }
    }

    // @Override
    // public void mouseDragged(MouseEvent e) {

    // }

    /**
     * Draw all elements in the game by current frame.
     * 
     * @param available
     */
    @Override
    public void draw() {
        this.noStroke();
        // white background
        background(WHITE_RGB[0], WHITE_RGB[1], WHITE_RGB[2]);
        // draw the board
        for (int i = 0; i < board.length; i++) {
            for (int i2 = 0; i2 < board[i].length; i2++) {
                // if cell is selected, highlight in green
                // TODO: draw highlighted cells
                if (isMoved()) {
                    switchPlayerTurn();
                }
                if (currentSelected != null && board[i][i2].getPiece() == currentSelected) {
                    this.setFill(1, (i2 + i) % 2);
                    this.rect(i2 * App.CELLSIZE, i * App.CELLSIZE, App.CELLSIZE, App.CELLSIZE);

                } else if ((i2 + i) % 2 == 1) {
                    // black cell
                    this.fill(BLACK_RGB[0], BLACK_RGB[1], BLACK_RGB[2]);
                    this.rect(i2 * App.CELLSIZE, i * App.CELLSIZE, App.CELLSIZE, App.CELLSIZE);
                }
                // if cell is available to move -> highlight in blue
                if (currentSelected != null) {
                    HashSet<Cell> availableMoves = currentSelected.getAvailableMoves(board);
                    if (availableMoves.contains(board[i][i2])) {
                        if (currentPlayer == 'w') {
                            this.setFill(2, 0); // Blue for white player
                        } else {
                            this.setFill(2, 1); // Blue for black player
                        }
                        this.rect(i2 * App.CELLSIZE, i * App.CELLSIZE, App.CELLSIZE, App.CELLSIZE);
                    }
                }
                board[i][i2].draw(this); // draw the piece
            }
        }

        // check if any player has no more pieces. The winner is the player who still
        // has pieces remaining
        if (piecesInPlay.get('w').size() == 0 || piecesInPlay.get('b').size() == 0) {
            fill(255);
            stroke(0);
            strokeWeight(5.0f);
            rect(App.WIDTH * 0.19f, App.HEIGHT * 0.33f, App.CELLSIZE * 3.1f, App.CELLSIZE * 0.92f);
            fill(200, 0, 200);
            textSize(24.0f * (CELLSIZE / 48.0f));
            if (piecesInPlay.get('w').size() == 0) {
                text("Black wins!", App.WIDTH * 0.2f, App.HEIGHT * 0.4f);
            } else if (piecesInPlay.get('b').size() == 0) {
                text("White wins!", App.WIDTH * 0.2f, App.HEIGHT * 0.4f);
            }
        }
    }

    // private boolean isAvailableMoves(Cell cell) {
    // for (Cell a : currentSelected.getAvailableMoves(board)) {
    // if (a.equals(cell)) {
    // return true;
    // }
    // }
    // return false;
    // }

    /**
     * Set fill colour for cell background
     * 
     * @param colourCode   The colour to set
     * @param blackOrWhite Depending on if 0 (white) or 1 (black) then the cell may
     *                     have different shades
     */
    public void setFill(int colourCode, int blackOrWhite) {
        this.fill(coloursRGB[colourCode][blackOrWhite][0], coloursRGB[colourCode][blackOrWhite][1],
                coloursRGB[colourCode][blackOrWhite][2]);
    }

    public static void main(String[] args) {
        PApplet.main("Checkers.App");
    }

}
