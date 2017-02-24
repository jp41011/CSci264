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
	//static boolean isDFS_solved = false;
	static int BFS_Count = 0;
	
	static ChessBoard GlobalBoard = new ChessBoard();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*** Start ***");
		
		// initialize static variables
		ChessPiece.ChessPieceID = 1;
		ChessMove.ChessMoveID = 1;
		
		
		ArrayList<ChessPiece> boardPieces = new ArrayList<ChessPiece>();
		
		//boardPieces = readPieces("puzzle1.txt");
		//boardPieces = readPieces("puzzle2.txt"); // testing piece properties. eligible moves.
		boardPieces = readPieces("puzzle3.txt");
		System.out.println("Pieces on board: " + boardPieces.size());
		
		GlobalBoard.printBoard(boardPieces);
		
		//Depth First Search
		System.out.println("========== DFS ==========");
		ArrayList<ChessPiece> board1 = boardPieces;
		ArrayList<ChessMove> solution1 = DFS_Solve(board1);
		System.out.println("Nodes touched: " + DFS_Count);
		System.out.println("Solution: ");
		printMoves(solution1);
		GlobalBoard.printBoard(board1);
		
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
				moves = DFS_helper(goodMove.get(moveID), board);
				// if solution is move return it 
				if(moves.size() > 0)
					return moves;
			}
		}
		
		return moves; // empty ... no solution
	}
	
	/**
	 * recursive helper function 
	 * @param move - next move to do
	 * @param board - current state
	 * @return
	 */
	static ArrayList<ChessMove> DFS_helper(ChessMove move, ArrayList<ChessPiece> board)
	{
		DFS_Count++; // increase the number of states that we are looking
		GlobalBoard.printBoard(board);
		move.print();
		
		//get information about piece that is going to get removed in case we have to put it back
		int removedX = move.xLocation;
		int removedY = move.yLocation;
		int removeIndex = getIndexOfPieceAt(board, removedX, removedY);
		ChessPiece removedPiece = board.get(removeIndex);
		
		board.remove(removeIndex); // remove piece from the board
		
		//update the location of the piece that took the removed piece
		int movedPieceIndex = getIndexOfPieceID(move.pieceID, board);
		int oldX = board.get(movedPieceIndex).xLocation; // incase we have to move this piece back
		int oldY = board.get(movedPieceIndex).yLocation;
		board.get(movedPieceIndex).xLocation = move.xLocation;
		board.get(movedPieceIndex).yLocation = move.yLocation;
		
		
		//check if this is a solution ... 1 piece on the board
		ArrayList<ChessMove> solution = new ArrayList<ChessMove>(); // empty
		if(board.size() == 1)
		{
			System.out.println("Found Solution");
			GlobalBoard.printBoard(board);
			solution.add(move);
			return solution;
		}
		
		// else has to look at all the next moves
		ArrayList<ChessMove> goodMoves = board.get(movedPieceIndex).getGoodMoves(board); //next moves for current piece
		//get possible moves with the other pieces
		for(int i=0; i<board.size(); i++)
		{
			if(i == movedPieceIndex)
				continue; // already added the moves for current piece
			
			ArrayList<ChessMove> tempMoves = board.get(i).getGoodMoves(board);
			goodMoves.addAll(tempMoves); // to to the list of moves we already have
		}
		
		//go through and check where the next moves lead to.
		for(int i=0; i<goodMoves.size(); i++)
		{
			solution = DFS_helper(goodMoves.get(i), board);
			if(solution.size() > 0)
			{
				//non empty, so solution was found
				solution.add(0,move); // so add move that got us to this point to the solution
				return solution;
			}
		}
		
		//undo move and replace taken piece
		board.get(movedPieceIndex).xLocation = oldX; // move moved piece back to the original location
		board.get(movedPieceIndex).yLocation = oldY;
		//replace taken piece to the board
		board.add(removeIndex, removedPiece);
		
		// did not find solution so return the empty array of moves
		return solution;
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
			System.out.println( "["+ moves.get(i).pieceID + "] " + moves.get(i).pieceType + " " + moves.get(i).xLocation + " " + moves.get(i).yLocation);
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
