package hw1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

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
		
		board.printBoard(boardPieces);
		
		
		System.out.println("*** End ***");
	}
	
	static ArrayList<ChessPiece> readPieces(String filename){
		
		
		ArrayList<ChessPiece> pieces = new ArrayList<ChessPiece>();
		
		return pieces;
	}

}
