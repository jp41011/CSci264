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
		
		SudokuBoard board21 = importBoard("board21.txt");
		System.out.println("Initial board(21): ");
		board21.print();
		boolean isSolved21 = board21.isSolved();
		if(isSolved21)
			System.out.println("Board Solved!");
		
		
		SudokuBoard board31 = importBoard("board31.txt");
		System.out.println("Initial board(31): ");
		board31.print();
		board31.addNum(7, 3, 1); // should error same square
		board31.addNum(4, 8, 2); // should error same column
		board31.addNum(6, 6, 2); // should error same row
		boolean isSolved31 = board31.isSolved();
		if(isSolved31)
			System.out.println("Board Solved!");
		
		// call DFS solution
		
		
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
	public static void DFS(SudokuBoard board)
	{
		// get next empty cell and start from there
		return; // 
	}

}
