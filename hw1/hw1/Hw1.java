package hw1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import hw1.ChessBoard;

/*
 * Juan Pedraza - CSci 264 - Artificial Intelligence - 2/12/17
 * HW 1 - State Space Search w/ Solitaire Chess
 * 
 */

public class Hw1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("*** Start ***");
		
		ChessPiece.ChessPieceID = 1; // initialize
		ChessBoard board = new ChessBoard();
		ArrayList<ChessPiece> boardPieces = new ArrayList<ChessPiece>();
		
		boardPieces = readPieces("puzzle1.txt");
		System.out.println("Pieces on board: " + boardPieces.size());
		board.printBoard(boardPieces);
		
		
		System.out.println("*** End ***");
	}
	
	/*
	 * method to read in a file with the list of pieces on the board and return an array list with them
	 * TODO: explain the file standard ... 1 letter for piece 2 number for xLocation and yLocation no spaces
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
					
					// create new chess piece and add to the list of peices
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
