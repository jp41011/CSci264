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
		System.out.println("Pieces on board: " + boardPieces.size());
		board.printBoard(boardPieces);
		
		//Depth First Search
		ArrayList<ChessPiece> board1 = boardPieces;
		ArrayList<ChessMove> solution1 = DFS_Solve(board1);
		System.out.println("Nodes touched: " + DFS_Count);
		
		//Breadth First Search
		//Depth Limited Search
		//iterative Deepening Search
		//A* search
		
		System.out.println("*** End ***");
	}
	
	static ArrayList<ChessMove> DFS_Solve(ArrayList<ChessPiece> board)
	{
		ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
		//TODO find solution using DFS
		
		
		return moves;
	}
	
	/*
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
