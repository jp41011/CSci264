package hw2;

import java.util.Arrays;

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
	protected int[] rowCounts; // keep count of how many cells filled in this row
	protected int[] colCounts;  // keep count of how many cells filled in this column
	
	
	// constructor
	SudokuBoard(int bSize)
	{
		squareSize = bSize;
		boardWidth = bSize * bSize;
		boardHeight = bSize * bSize;
		board = new int[boardWidth][boardHeight];
		rowCounts = new int [boardHeight];
		colCounts = new int [boardWidth];
	}
	
	
	// copy constructor using deep copy
	public SudokuBoard(SudokuBoard b)
	{
		this.squareSize = b.squareSize;
		this.boardWidth = b.boardWidth;
		this.boardHeight = b.boardHeight;
		this.board = new int[b.boardWidth][b.boardHeight];
		
		//this.board = b.board; this doesn't do a deep copy
		// do a deep copy of the board
		for(int i=0; i < boardWidth; i++)
		{
			this.board[i] = Arrays.copyOf(b.board[i], b.board[i].length);
		}
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
		{
			board[tempX][tempY] = tempVal; // add to board
			
			//update rowCounts and colCounts arrays
			rowCounts[tempX]++;
			colCounts[tempY]++;
		}
		
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
		// remove from the row/col counts
		rowCounts[tempX]--;
		colCounts[tempY]--;
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
	
	/* getNextBestCell
	 * Select a cell to fill in next based on the number of adjacent cells that are filled
	 * check number of cells filed in the same row, column and square
	 
	public Cell getNextBestCell()
	{
		Cell tCell = new Cell(-1, -1); // if no more cells empty
		// might not need score board but it will making debugin easier
		int [][] scoreBoard = new int[boardWidth][boardHeight]; // store the score for each cell
		int curMax = 0; // keep track of current max val
		int curX=0, curY=0; // keep coor of current max val
		
		for(int y=0; y < boardHeight; y++)
		{
			for(int x=0; x < boardWidth; x++)
			{
				if(board[x][y] != 0) // if cell is filled
				{
					scoreBoard[x][y] = 0; 
				}
				else // empty cell
				{
					int rowCount=0, colCount=0, squareCount=0, cellsFilled=0;
					
					// count how many cells filled in this row
					for(int tempX=0; tempX < boardWidth; tempX++)
					{
						if(board[tempX][y] != 0) // if its a non empty cell
							rowCount++; // increase count
					}
					
					// count how many cells filled in this column
					for(int tempY=0; tempY < boardHeight; tempY++)
					{
						if(board[x][tempY] != 0) // if non empty cell
							colCount++;
					}
					
					//count how many cells filled in this square
					int xBase = x / squareSize;
					int yBase = y / squareSize;
					int startX = xBase * squareSize;
					int endX = (xBase+1) * squareSize;
					int startY = yBase * squareSize;
					int endY = (yBase+1) * squareSize;
					int boardVal;
					
					for(int tempX = startX; tempX < endX; x++)
					{
						for(int tempY = startY; tempY < endY; y++)
						{
							boardVal = board[tempX][tempY];
							if(boardVal != 0) // if non empty cell
								squareCount++;
								
						}
					}
					cellsFilled = rowCount + colCount + squareCount; // add up the cells filled
					scoreBoard[x][y] = cellsFilled; // fill out score board
					
					if(cellsFilled > curMax) // if we have a new max cell update our temp variables
					{
						curMax = cellsFilled;
						curX = x;
						curY = y;
					}
				}
					
			}
		}
		
		tCell = new Cell(curX, curY);
		
		return tCell;
	}
	*/
	
	/* getNextBestCell2()
	 * get the next best cell to try based on the rowCounts and colCounts
	 
	public Cell getNextBest2()
	{
		Cell tcell = new Cell(-1, -1);
		int maxVal=0, maxX = -1, maxY=-1;
		// find best col ... x coor
		for(int i=0; i < rowCounts.length; i++)
		{
			if(rowCounts[i] > maxVal)
				maxX = i;
		}
		maxVal = -1; //reset
		// get best row .. y coor
		for(int i=0; i < colCounts.length; i++)
		{
			if(colCounts[i] > maxVal)
				maxY = i;
		}
		
		
		return tcell;
	}
	*/
	
}
