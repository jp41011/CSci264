package hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 Juan Pedraza - CSci 264 - HW2
 Sudoku Solver
 This program will read in information describing the dimensions and information
 about a sudoku puzzle.
 */

public class hw2 {

	static int DFS_Count = 0;	// count states explored with DFS
	static int H_Count = 0;		// count states explored with heuristic approach 
	
	public static void main(String[] args) {
		
		/*
		System.out.println("Board 2");
		SudokuBoard board2 = new SudokuBoard(2);
		board2.print();
		System.out.println("Board 3");
		SudokuBoard board3 = new SudokuBoard(3);
		board3.print();
		*/

		int statesExplored; // how many states were explored
		
		/*
		// already solved 4x4 board
		SudokuBoard board21 = importBoard("board21.txt");
		System.out.println("Initial board(21): ");
		board21.print();
		statesExplored = DFS(board21); // solve using DFS
		printSummary(board21, statesExplored);
		*/
		
		// board 4x4 w/ missing numbers
		SudokuBoard board22 = importBoard("board22.txt");
		System.out.println("Initial board(22): ");
		board22.print();
		statesExplored = DFS(board22); // solve using DFS
		printSummary(board22, statesExplored);
		
		
		
		// board 9x9 w/ missing numbers
		SudokuBoard board32 = importBoard("board32.txt");
		System.out.println("Initial board(32): ");
		board32.print();
		statesExplored = DFS(board32);
		printSummary(board32, statesExplored);
		
		
		
		// board 9x9 w/ missing numbers ... "worlds hardest sudoku"
		SudokuBoard board33 = importBoard("board33.txt");
		//SudokuBoard boardTest = new SudokuBoard(board33);
		System.out.println("Initial board(33): ");
		board33.print();
		statesExplored = DFS(board33);
		printSummary(board33, statesExplored);
		
		//boardTest.print();
		/*
		// solve with different method
		SudokuBoard board33_2 = importBoard("board33.txt");
		System.out.println("Initial board(33_2): ");
		board33_2.print();
		statesExplored = solver1(board33_2);
		printSummary(board33_2, statesExplored);
		*/
		
		
		// board 16x16 w/ missing numbers
		SudokuBoard board41 = importBoard("board41.txt");
		System.out.println("Initial board(41): ");
		board41.print();
		statesExplored = DFS(board41);
		printSummary(board41, statesExplored);
		
		
		
		// board 25x25 w/ missing numbers ... infinite loop?? or just takes really long
		SudokuBoard board51 = importBoard("board51.txt");
		System.out.println("Initial board(51): ");
		board51.print();
		//statesExplored = DFS(board51);
		//printSummary(board51, statesExplored);
		
		
		// call heuristic solution

	}
	
	// given a filename
	// read in the information about the board
	public static SudokuBoard importBoard(String fileName)
	{
		SudokuBoard board = new SudokuBoard(0);
		try {
			Scanner inFile = new Scanner(new File(fileName));
			// first get the size of 1 square on the board
			int squareSize = inFile.nextInt();
			board = new SudokuBoard(squareSize);
			
			int tempX, tempY, tempVal; // temp vars
			
			// now read in the values on the board and location
			while(inFile.hasNextLine())
			{
				tempX = inFile.nextInt()-1;
				tempY = inFile.nextInt()-1;
				tempVal = inFile.nextInt();
				
				board.addNum(tempX, tempY, tempVal); // add to board
			}
			
			inFile.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return board;
	}
	
	/* DFS Solution
	 	Start from the bottom left corner and start populated cells,
	 	moving left to right, bottom up.
	*/
	public static int DFS(SudokuBoard board)
	{
		//init DFS_Count for this board
		DFS_Count = 0;
		 
		// call helper function
		DFS_helper(board);
		
		return DFS_Count; // 
	}
	
	/* DFS_helper
	 * recursive function to do the DFS searching
	 */
	public static boolean DFS_helper(SudokuBoard board)
	{
		//System.out.println("DFS_Helper");
		boolean isFinished = false; // finished going through board? either found solution or tried every possible state and no solution
		isFinished = board.isSolved(); // check if board is solved
		
		if(isFinished) // board solved
			return true; // exit
		
		// check if there is an empty cell to try
		
		Cell cell = board.getNextEmptyCell();
		int tempX = cell.getX();
		int tempY = cell.getY();
		
		//System.out.println("Currently @ " + tempX + ", " + tempY);
		//board.print();
		
		if(tempX == -1 && tempY == -1) // no more empty cells
			return true; // exit. no solution
		
		//else explore this cell and continue
		// get possible numbers that this cell could be
		// try every number until solution found or end is reached
		int maxNum = board.squareSize * board.squareSize; // get the max number that can appear on the board
		for(int tval = 1; tval <= maxNum; tval++)
		{
			DFS_Count++; // increase the states explored count
			
			/*
			//set flag for testing
			if( board.getVal(0,0) == 7
				//&& board.getVal(1,0) == 8
				//&& board.getVal(2,0) == 9
					)
				tempX = tempX + 0; // No-Op
			*/
			
			
			// check if placing the number in the cell is an valid move
			boolean isGoodMove = board.addNum(tempX, tempY, tval); // try tval in empty cell
			if(isGoodMove)
			{
				isFinished = DFS_helper(board);
				if(isFinished) // found solution
					return true; // exit return true
				else
					board.removeNum(tempX, tempY); // remove the number that had been added, since it does not lead to solution
			}
			//else continue on to the next number
		}
		
		// went through all numbers in this cell and nothing returned solution so return false no solution
		return false;
	}
	
	/* solver1
	 * user better cell selection to solve sudoku puzzle faster
	 
	public static int solver1(SudokuBoard board)
	{
		H_Count = 0; // reset counter
		
		solver1_helper(board); // call recursive function
		
		return H_Count;
	}
	*/
	
	/* solver1_helper
	 * recursive function to solve the sudoku puzzle using better cell selection
	
	public static boolean solver1_helper(SudokuBoard board)
	{
		boolean isFinished = false; // finished going through board? either found solution or tried every possible state and no solution
		isFinished = board.isSolved(); // check if board is solved
		
		if(isFinished) // board solved
			return true; // exit
		
		// check if there is an empty cell to try
		
		Cell cell = board.getNextBestCell();
		int tempX = cell.getX();
		int tempY = cell.getY();
		
		//System.out.println("Currently @ " + tempX + ", " + tempY);
		//board.print();
		
		if(tempX == -1 && tempY == -1) // no more empty cells
			return true; // exit. no solution
		
		// start at the given cell
		int maxNum = board.squareSize * board.squareSize;
		for(int tval = 1; tval <= maxNum; tval++)
		{
			H_Count++;
			// check if filling out the cell with tval is valid
			boolean isGoodMove = board.addNum(tempX, tempY, tval);
			if(isGoodMove) // then cell was filled
			{
				isFinished = solver1_helper(board);
				if(isFinished) // solution found
					return true;
				else
					board.removeNum(tempX, tempY); // remove and move on to next value to try at this cell
			}
		}
		
		// if we tried everything and no solution was found then there is no solution
		return false;
	}
	*/
	
	// helper function to output summary of board results
	public static void printSummary(SudokuBoard board, int stateCount)
	{
		if(board.isSolved())
			System.out.println("Solution Found! :) ");
		else
			System.out.println("No solution found. :( ");
		
		System.out.println("States explored: " + stateCount);
		board.print(); // display final board
		System.out.println("===================================\n");
		return;
	}

}
