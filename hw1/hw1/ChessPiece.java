package hw1;

import java.util.ArrayList;

/* Class for the base class of all chess pieces
 * 
 */

public class ChessPiece {

	public char pieceType;
	public int xLocation;
	public int yLocation;
	public int pieceID;
	public int pieceValue;
	
	public static int ChessPieceID; // keep a running id for the id of the pieces
	
	// default constructor
	ChessPiece()
	{
		pieceType = '0';
		xLocation = 0;
		yLocation = 0;
		pieceID = 0;
		pieceValue = 0;
	}
	
	//constructor
	ChessPiece(char newPieceType, int x, int y)
	{
		this.pieceType = newPieceType;
		xLocation = x;
		yLocation = y;
		pieceID = ChessPieceID++; // get next id and increase id
		
		if(this.pieceType == 'K'){
			pieceValue = 10; // in solitaire chess different than regular chess
		}
		else if(this.pieceType == 'Q'){
			pieceValue = 9;
		}
		else if(this.pieceType == 'R'){
			pieceValue = 5;
		}
		else if(this.pieceType == 'B'){
			pieceValue = 3;
		}
		else if(this.pieceType == 'N'){
			pieceValue = 3;
		}
		else if(this.pieceType == 'P'){
			pieceValue = 1;
		}
		else{
			System.out.println("Unknown Piece");
			System.out.println(newPieceType + " " + x + " " + y);
		}
	}
	
	// function to get possible chess moves for this piece
	//
	public ArrayList<ChessMove> getMoves()
	{
		ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
		
		// to hold the temp x and y coor
		int tempX;
		int tempY;
		
		if(this.pieceType == 'K')
		{
			//king can go:
			// up
			tempX = xLocation;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//else invalid location so do not add to list of moves.
			
			// king can go up and to the right
			tempX = xLocation + 1;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king can go to the right
			tempX = xLocation + 1;
			tempY = yLocation;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
		}
		
		
		return moves;
	}
	
	// check if location is valid and on the board
	private boolean isValidMove(int xLoc, int yLoc)
	{
		if(xLoc < 1 || xLoc > 4 || yLoc < 1 || yLoc > 4)
			return false;
		else
			return true;
	}
}
