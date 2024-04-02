package Checkers;

import java.util.HashSet;

public class CheckersPiece {

	private char colour;
	private Cell position;
	private boolean isKing;
	private boolean hasMoved;
	
	public CheckersPiece(char c) {
		this.colour = c;
		this.isKing = false;
		this.hasMoved = false;
	}
	
	public char getColour() {
		return this.colour;
	}
	
	public void setPosition(Cell p) {
		this.position = p;
	}
	
	public Cell getPosition() {
		return this.position;
	}

	//getter and setter for moved
	public boolean hasMoved() {
		return hasMoved;
	}

	public void setMoved(boolean moved) {
		this.hasMoved = moved;
	}

	public void move() {
		if (isKing()) {
			this.isKing = true;
		}
	}


	// hashSet does not allow duplicate elements
	public HashSet<Cell> getAvailableMoves(Cell[][] board) {

		// Check if the move is diagonal and within the bounds of the board
		HashSet<Cell> available = new HashSet<Cell>();
		for (int i = 0; i < board.length; i++) { //for row
			for (int j = 0; j < board[i].length; j++) { // for col

				Cell currentCell = board[i][j];
				//Check if this cell contains the piece
				if (currentCell.getPiece() == this) {
					int rowChange1;
					int rowChange2;
					if (this.colour == 'w') {
						rowChange1 = 1;
					} else {
						// Black king moves upward, so rowChange1 should be negative
						rowChange1 = -1;
					}
					rowChange2 = 2 * rowChange1;

					if (withinBounds(board, i + rowChange1, j - 1)
							&& board[i + rowChange1][j - 1].getPiece() != null) {
						if (isValidMove(board, i + rowChange2, j - 2)
								&& withinBounds(board, i + rowChange2, j - 2)) {
							available.add(board[i + rowChange2][j - 2]);
						}
					} else {
						if (isValidMove(board, i + rowChange1, j - 1)
								&& withinBounds(board, i + rowChange1, j - 1)) {
							available.add(board[i + rowChange1][j - 1]);
						}
					}
					if (withinBounds(board, i + rowChange1, j + 1)
							&& board[i + rowChange1][j + 1].getPiece() != null) {
						if (isValidMove(board, i + rowChange2, j + 2)
								&& withinBounds(board, i + rowChange2, j + 2)) {
							available.add(board[i + rowChange2][j + 2]);
						}
					} else {
						if (isValidMove(board, i + rowChange1, j + 1)
								&& withinBounds(board, i + rowChange1, j + 1)) {
							available.add(board[i + rowChange1][j + 1]);
						}
					}

					if (isKing()) {
						if (this.colour == 'w') {
							// For white king
							if (withinBounds(board, i - rowChange1, j - 1)
									&& board[i - rowChange1][j - 1].getPiece() != null) {
								if (isValidMove(board, i - rowChange2, j - 2)
										&& withinBounds(board, i - rowChange2, j - 2)) {
									available.add(board[i - rowChange2][j - 2]);
								}
							} else {
								if (isValidMove(board, i - rowChange1, j - 1)
										&& withinBounds(board, i - rowChange1, j - 1)) {
									available.add(board[i - rowChange1][j - 1]);
								}
							}
							if (withinBounds(board, i - rowChange1, j + 1)
									&& board[i - rowChange1][j + 1].getPiece() != null) {
								if (isValidMove(board, i - rowChange2, j + 2)
										&& withinBounds(board, i - rowChange2, j + 2)) {
									available.add(board[i - rowChange2][j + 2]);
								}
							} else {
								if (isValidMove(board, i - rowChange1, j + 1)
										&& withinBounds(board, i - rowChange1, j + 1)) {
									available.add(board[i - rowChange1][j + 1]);
								}
							}
						}
						if (this.colour == 'b') {
							rowChange1 = 1;
							rowChange2 = 2;
							// For black king
							if (withinBounds(board, i + rowChange1, j - 1)
									&& board[i + rowChange1][j - 1].getPiece() != null) {
								if (isValidMove(board, i + rowChange2, j - 2)
										&& withinBounds(board, i + rowChange2, j - 2)) {
									available.add(board[i + rowChange2][j - 2]);
								}
							} else {
								if (isValidMove(board, i + rowChange1, j - 1)
										&& withinBounds(board, i + rowChange1, j - 1)) {
									available.add(board[i + rowChange1][j - 1]);
								}
							}
							if (withinBounds(board, i + rowChange1, j + 1)
									&& board[i + rowChange1][j + 1].getPiece() != null) {
								if (isValidMove(board, i + rowChange2, j + 2)
										&& withinBounds(board, i + rowChange2, j + 2)) {
									available.add(board[i + rowChange2][j + 2]);
								}
							} else {
								if (isValidMove(board, i + rowChange1, j + 1)
										&& withinBounds(board, i + rowChange1, j + 1)) {
									available.add(board[i + rowChange1][j + 1]);
								}
							}
						}
					}
				}
			}
		}
		return available;

	}
	

	
	// Example method to check if a move is valid (just a placeholder, replace with
	// actual logic)
	private boolean isValidMove(Cell[][] board, int row, int col) {
		// Check if the cell is within the bounds of the board and if it's empty
		if (row >= 0 && row < board.length && col >= 0 && col < board[row].length
				&& board[row][col].getPiece() == null) {
			return true;
		}
		return false;
	}

	private boolean withinBounds(Cell[][] board, int row, int col) {
		return row >= 0 && row < board.length && col >= 0 && col < board[row].length;
	}
	

	
	// promote this piece
	public boolean isKing() {
		if (this.isKing) {
			// System.out.println("Piece is already a king.");
			return true;
		}
		// System.out.println("Checking if piece is a king...");
		if ((this.colour == 'w' && this.position.getY() == 7) || (this.colour == 'b' && this.position.getY() == 0)) {
			// System.out.println("Piece is a king!");
			this.isKing = true;
			return true;
		} else {
			// System.out.println("Piece is not a king.");
			return false;
		}
	}
	
	//draw the piece
	public void draw(App app) {
		app.strokeWeight(3.0f);
		if (colour == 'w') {
			app.fill(255);
			app.stroke(0);
		} else if (colour == 'b') {
			app.fill(0);
			app.stroke(255);
		}
		if (!isKing) {
			app.ellipse(position.getX() * App.CELLSIZE + App.CELLSIZE / 2,
					position.getY() * App.CELLSIZE + App.CELLSIZE / 2, App.CELLSIZE * 0.8f, App.CELLSIZE * 0.8f);
			app.noStroke();
		} else if (this.isKing) {
			app.ellipse(position.getX() * App.CELLSIZE + App.CELLSIZE / 2,
					position.getY() * App.CELLSIZE + App.CELLSIZE / 2,
					App.CELLSIZE * 0.8f,
					App.CELLSIZE * 0.8f);
			// app.ellipse(position.getX() * App.CELLSIZE + App.CELLSIZE / 2,
			// 		position.getY() * App.CELLSIZE + App.CELLSIZE / 2,
			// 		App.CELLSIZE * 0.8f,
			// 		App.CELLSIZE * 0.8f);
			app.ellipse(position.getX() * App.CELLSIZE + App.CELLSIZE / 2,
					position.getY() * App.CELLSIZE + App.CELLSIZE / 2,
					App.CELLSIZE * 0.4f,
					App.CELLSIZE * 0.4f);
			app.noStroke();
		}
		
	}
}