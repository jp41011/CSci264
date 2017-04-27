package hw2;

//import java.util.ArrayList;


/*
Sudoku Board class
sudoku board construct to store functions for the
sudoku board.
 */

public class SudokuBoard {

	protected int squareSize;
	protected int boardWidth;
	protected int boardHeight;
	//protected ArrayList<ArrayList <Integer> > board;
	protected int[][] board;
	
	// constructor
	SudokuBoard(int bSize)
	{
		squareSize = bSize;
		boardWidth = bSize * bSize;
		boardHeight = bSize * bSize;
		board = new int[boardWidth][boardHeight];
	}
	
	// getter function to get value at particular square
	public int getVal(int x, int y){return board[x][y];}
	
	// print board
	public void print()
	{
		// top to bottom
		for(int y = boardHeight-1; y >= 0; y--)
		{
			// left to right
			for(int x = 0; x < boardWidth; x++)
			{
				if(board[x][y] == 0)
					System.out.print("\u25A1\t");
				else
					System.out.print(board[x][y] + "\t");
				
				// right edge of small square
				if( (x+1) % squareSize == 0)
					System.out.print("|\t");
			}
			
			System.out.println(""); // new line
			// bottom edge of the small square
			if( y % squareSize == 0)
			{
				// left to right
				for(int x = 0; x < boardWidth; x++)
				{
					// right edge of small square
					System.out.print("-\t");
					if( (x+1) % squareSize == 0 && x != 0)
						System.out.print("|\t");
						
				}
				System.out.println("");
			}
		}
		return;
	}
	
	// function to add values to the board
	// coordinates are 0 index based
	public boolean addNum(int tempX, int tempY, int tempVal)
	{
		boolean isValid = isGoodMove(tempX, tempY, tempVal); // is the move ok
		
		if(isValid) // if valid move then add to board
			board[tempX][tempY] = tempVal;
		
		return isValid;
	}
	
	/* removeNum
	 * clears out the cell designated by the given x and y coordinates.
	 * 0 index based
	 */
	public void removeNum(int tempX, int tempY)
	{
		if(tempX < 0 || tempX >= boardWidth || tempY < 0 || tempY >= boardHeight)
		{
			if(GlobalVar.isDebugMode)
				System.out.println("Invliad coor: " + tempX + ", " + tempY);
			return;
		}
		board[tempX][tempY] = 0;
		return;
	}
	
	// checks to see if this entry is valid
	// and doesn't break any sudoku rules
	// tempX and tempY 0 index based
	public boolean isGoodMove(int tempX, int tempY, int tempVal)
	{
		// check value range
		if( tempVal <= 0 || tempVal > boardWidth)
		{
			if(GlobalVar.isDebugMode)
				System.out.println("Invalid value: " + tempVal + " @ " + (tempX+1) + ", " + (tempY+1) );
			return false;
		}
		// check row
		for(int x = 0; x < boardWidth; x++)
		{
			if(board[x][tempY] == tempVal) // value is already in this row
			{
				if(GlobalVar.isDebugMode)
					System.out.println("Value " + tempVal + " is already in the same row @ " + (x+1) + ", " + (tempY+1) );
				return false;
			}
		}
		// check column
		for(int y=0; y < boardHeight; y++)
		{
			if(board[tempX][y] == tempVal)
			{
				if(GlobalVar.isDebugMode)
					System.out.println("Value " + tempVal + " is already in the same column @ " + (tempX+1) + ", " + (y+1) );
				return false;
			}
		}
		// check square
		// figure out what square to check
		int xBase = tempX / squareSize;
		int yBase = tempY / squareSize;
		int startX = xBase * squareSize;
		int endX = (xBase+1) * squareSize;
		int startY = yBase * squareSize;
		int endY = (yBase+1) * squareSize;
		int boardVal;
		
		for(int x = startX; x < endX; x++)
		{
			for(int y = startY; y < endY; y++)
			{
				boardVal = board[x][y];
				if(boardVal == tempVal)
				{
					if(GlobalVar.isDebugMode)
						System.out.println("Value " + tempVal + " is already in the same square @ " + (x+1) + ", " + (y+1));
					return false;
				}
			}
		}
		
		// else return true
		return true;
	}
	
	// check to see if the board is solved
	// only have to check for empty cells.
	// since the constraints were checked every time a cell was populated.
	public boolean isSolved()
	{
		for(int x=0; x < boardWidth; x++)
		{
			for(int y=0; y < boardHeight; y++)
			{
				if(board[x][y] == 0)
				{
					if(GlobalVar.isDebugMode)
						System.out.println("Empty square @ " + (x+1) + ", " + (y+1) );
					return false;
				}
			}
		}
		return true;
	}
	
	/* getNextEmptyCell
	 	returns the x and y coordinate of the next empty cell.
	 	going from bottom left corner. left to right, bottom to top.
	*/
	public Cell getNextEmptyCell()
	{
		Cell tCell = new Cell(-1, -1); // if no more cells empty
		
		for(int y=0; y < boardHeight; y++)
		{
			for(int x=0; x < boardWidth; x++)
			{
				if(board[x][y] == 0) // is empty
				{
					tCell = new Cell(x, y);
					return tCell;
				}
			}
		}
		return tCell;
	}
	
}
