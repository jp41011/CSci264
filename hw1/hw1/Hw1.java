package hw1;

//import hw1.ChessBoard;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Juan Pedraza - CSci 264 - Artificial Intelligence - 2/12/17
 * HW 1 - State Space Search w/ Solitaire Chess
 * 
 */

public class Hw1 {

	static int DFS_Count = 0;
	static boolean isDFS_solved = false;
	static int BFS_Count = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*** Start ***");
		
		// initialize static variables
		ChessPiece.ChessPieceID = 1;
		ChessMove.ChessMoveID = 1;
		
		ChessBoard board = new ChessBoard();
		ArrayList<ChessPiece> boardPieces = new ArrayList<ChessPiece>();
		
		boardPieces = readPieces("puzzle1.txt");
		//boardPieces = readPieces("puzzle2.txt"); // testing piece properties. eligible moves.
		System.out.println("Pieces on board: " + boardPieces.size());
		board.printBoard(boardPieces);
		
		//Depth First Search
		ArrayList<ChessPiece> board1 = boardPieces;
		ArrayList<ChessMove> solution1 = DFS_Solve(board1);
		System.out.println("Nodes touched: " + DFS_Count);
		printMoves(solution1);
		
		//Breadth First Search
		//Depth Limited Search
		//iterative Deepening Search
		//A* search
		
		System.out.println("*** End ***");
	}
	
	/** DFS_Solve <br/>
	 * Find solution using Depth First Search
	 * @param board
	 * @return
	 */
	static ArrayList<ChessMove> DFS_Solve(ArrayList<ChessPiece> board)
	{
		ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
		//printPieceSummary(board);
		
		//go through each piece left on the board
		for(int pieceIndex=0; pieceIndex<board.size(); pieceIndex++)
		{
			ArrayList<ChessMove> goodMove = board.get(pieceIndex).getGoodMoves(board);
			//int pieceID = board.get(pieceIndex).pieceID;
			
			//printMoves(goodMoves);
			// go through each valid move
			for(int moveID=0; moveID<goodMove.size(); moveID++)
			{
				ArrayList<ChessPiece> tempBoard = board;
				int goodMoveX = goodMove.get(moveID).xLocation;
				int goodMoveY = goodMove.get(moveID).yLocation;
				
				tempBoard.get(pieceIndex).xLocation = goodMoveX;
				tempBoard.get(pieceIndex).yLocation = goodMoveY;
				
				int removeIndex = getIndexOfPieceAt(board, goodMoveX, goodMoveY);
				tempBoard.remove(removeIndex);
				
				// are we at a solution
				if(tempBoard.size() == 1)
				{
					isDFS_solved = true;
					ChessMove newMove = new ChessMove(goodMove.get(moveID).pieceID, goodMove.get(moveID).pieceType, goodMoveX, goodMoveY);
					moves.add(0,newMove);
					return moves;
				}
				//continue to next move
			}
			if(isDFS_solved == true)
			{
				
			}
			
		}
		
		return moves;
	}
	
	static ArrayList<ChessMove> DFS_helper(ChessMove move, ArrayList<ChessPiece> board)
	{
		if(board.size() == 2) // next valid move will reduce board size to 1 so that would be a solution
		{
			ArrayList<ChessMove> solution = new ArrayList<ChessMove>();
			solution.add(move);
			return solution;
		}
		
		// not at solution yet so make move and then recursive call
		ArrayList<ChessPiece> tempBoard = board;
		
		// remove piece that will be taken
		int takenPieceIndex = getIndexOfPieceAt(tempBoard, move.xLocation, move.yLocation);
		tempBoard.remove(takenPieceIndex);
		
		// move piece to new location
		int curPieceIndex = getIndexOfPieceID(move.pieceID, tempBoard);
		tempBoard.get(curPieceIndex).xLocation = move.xLocation;
		tempBoard.get(curPieceIndex).yLocation = move.yLocation;
		
		//get next valid moves
		ArrayList<ChessMove> goodMove = tempBoard.get(curPieceIndex).getGoodMoves(tempBoard);
		//empty array of chess move to store move if solution is found
		ArrayList<ChessMove> solutionMoves = new ArrayList<ChessMove>();
		
		for(int moveIndex=0; moveIndex<goodMove.size(); moveIndex++)
		{
			solutionMoves = DFS_helper(goodMove.get(moveIndex), tempBoard);
			if(solutionMoves.size() > 0)
			{
				solutionMoves.add(0,move);
				return solutionMoves;
			}
		}
		return solutionMoves; // empty ... no solution found
	}
	
	/**
	 * Given an array of pieces and an xLocation and a yLocation <br/>
	 * Return the index of the piece that is currently at that location.
	 * @param xloc
	 * @param yloc
	 * @return
	 */
	static int getIndexOfPieceAt(ArrayList<ChessPiece> board, int xloc, int yloc)
	{
		// go through board pieces find the piece with matching coordinates and return its index
		for(int i=0; i<board.size(); i++)
		{
			if(board.get(i).xLocation == xloc && board.get(i).yLocation==yloc)
				return i;
		}
		return -1;
	}
	
	/**
	 * Given the pieceID and an array of pieces return the index of the piece that has that pieceID
	 * @param pID
	 * @param board
	 * @return
	 */
	static int getIndexOfPieceID(int pID, ArrayList<ChessPiece> board)
	{
		for(int i=0; i<board.size(); i++)
			if(board.get(i).pieceID == pID)
				return i;
		
		return -1;
	}
	
	/**
	 * Given an array of piece moves, print out the list of moves.
	 * @param moves
	 */
	static void printMoves(ArrayList<ChessMove> moves)
	{
		System.out.println("");
		for(int i=0; i<moves.size(); i++)
		{
			System.out.println( moves.get(i).pieceType + " " + moves.get(i).xLocation + " " + moves.get(i).yLocation);
		}
	}
	
	/**
	 * Given pieces on the board print a summary of the possible moves for each piece
	 * @param board - array with chess pieces
	 */
	static void printPieceSummary(ArrayList<ChessPiece> board)
	{
		for(int i=0; i<board.size();i++)
		{
			System.out.print(board.get(i).pieceID + " " + board.get(i).pieceType);
			System.out.print(" @ " + board.get(i).xLocation + "," + board.get(i).yLocation);
			printMoves(board.get(i).getMoves());
			System.out.println();
		}
	}
	
	/**
	 * method to read in a file with the list of pieces on the board and return an array list with them
	 * 1 letter for piece 2 number for xLocation and yLocation no spaces
	 * Use one capital letter to id piece type, one number for the x location, one number for the y location
	 */
	static ArrayList<ChessPiece> readPieces(String filename){
		
		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
		
		// there is probably a better way of reading from the file ... but my java is rusty.
		File file = new File(filename);
		try{
			FileInputStream fileStream = new FileInputStream(file);
			char pieceType;
			char xloc, yloc;
			while(fileStream.available() > 0)
			{
				pieceType = (char)fileStream.read();
				xloc = (char) fileStream.read();
				yloc = (char) fileStream.read();
				fileStream.read(); // get rid of the \n
				//System.out.println("Read: " + pieceType + " @ " + xloc + ", " + yloc);
				
				// ignore non piece characters
				if(pieceType == 'K' || pieceType == 'Q' || pieceType == 'R' || pieceType == 'B' || pieceType == 'N' || pieceType == 'P')
				{
					// convert to datatypes that the constructor will need
					//String name = String.valueOf(pieceType);
					int xlocation = Character.getNumericValue(xloc);
					int ylocation = Character.getNumericValue(yloc);
					
					// create new chess piece and add to the list of pieces
					ChessPiece newPiece = new ChessPiece(pieceType, xlocation, ylocation);
					pieces.add(newPiece);
				}
			}
			fileStream.close();
		}catch (IOException e){
			
		}
		
		return pieces;
	}

}
