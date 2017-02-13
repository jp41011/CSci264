package hw1;

import java.util.ArrayList;

/* class to represent the 4 x 4 chess board
 * Board location: XY
 * 		X - Column the piece is located on
 * 		Y - Row the piece is located on
 * Pieces: 
 * 		King 	- K
 * 		Queen	- Q
 * 		Rook	- R
 * 		Bishop	- B
 * 		Knight	- N
 * 		Pawn	- P
 * 
 * Ex. K11 - Move King to the columns 1 and row 1 (bottom left corner)
 * Ex. Q34 - Move Queen to column 3 and row 4
 * Ex. R44 - Move the Rook to column 4 and row 4 (top right corner)
 */

public class ChessBoard {

	
	
	// function to add piece to board
	public void addPiece(){
		
	}
	
	// function to print the current view of the board
	public void printBoard(ArrayList<ChessPiece> boardPieces){
		System.out.println(" - - - - ");
		boolean pieceFound = false;
		
		// left to right first
		for(int curY=4; curY>=1; curY--)
		{
			System.out.print("|");
			// top to bottom
			for(int curX=1; curX<=4; curX++)
			{
				pieceFound = false;
				for(int pieceIndex = 0; pieceIndex < boardPieces.size(); pieceIndex++)
				{
					if(boardPieces.get(pieceIndex).xLocation == curX && boardPieces.get(pieceIndex).yLocation == curY)
					{
						System.out.print(boardPieces.get(pieceIndex).name);
						pieceFound = true;
						break;
					}
				}
				//check if there is a piece at this spot ... if so then print piece letter
				//else print nothing at this spot
				if(pieceFound == false)
					System.out.print(" ");
				
				System.out.print("|");
			}
			System.out.println("");
		}
		
		System.out.println(" - - - - ");
	}
}
