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
		pieceID = -1;
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
			
			//king - down and to the right
			tempX = xLocation + 1;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - down
			tempX = xLocation;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - down and to the left
			tempX = xLocation - 1;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - left
			tempX = xLocation - 1;
			tempY = yLocation;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - left and up
			tempX = xLocation - 1;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
		}
		else if(this.pieceType == 'Q')
		{
			// queen can move all the way up, down, left, right, diagonals
			//calc dist to edges
			int distToRight = 4 - xLocation;
			int distToLeft = xLocation - 1;
			int distToTop = 4 - yLocation;
			int distToBottom = yLocation - 1;
			
			// diagonal distance is determined by the closest edge
			int distToTopRight = (distToRight < distToTop ? distToRight : distToTop);
			int distToTopLeft = (distToLeft < distToTop ? distToLeft : distToTop);
			int distToBottomRight = (distToRight < distToBottom ? distToRight : distToBottom);
			int distToBottomLeft = (distToLeft < distToBottom ? distToLeft : distToBottom);
			
			// moves to the right edge
			for(int x=1; x<=distToRight; x++)
			{
				tempX  = xLocation + x;
				if(isValidMove(tempX, yLocation) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, yLocation);
					moves.add(tempMove);
				}
			}
			// moves to the left edge
			for(int x=1; x<=distToLeft; x++)
			{
				tempX = xLocation - x;
				if(isValidMove(tempX, yLocation) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, yLocation);
					moves.add(tempMove);
				}
			}
			// moves to the top
			for(int y=1; y<=distToTop; y++)
			{
				tempY = yLocation + y;
				if(isValidMove(xLocation, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, xLocation, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the bottom
			for(int y=1; y<=distToBottom; y++)
			{
				tempY = yLocation - y;
				if(isValidMove(xLocation, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, xLocation, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the top right
			for(int tr = 1; tr<=distToTopRight; tr++)
			{
				tempX = xLocation + tr;
				tempY = yLocation + tr;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
			//moves to the top left
			for(int tl=1; tl<distToTopLeft; tl++)
			{
				tempX = xLocation - tl;
				tempY = yLocation + tl;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the bottom right
			for(int br=1; br<=distToBottomRight; br++)
			{
				tempX = xLocation + br;
				tempY = yLocation - br;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the bottom left
			for(int bl=1; bl<=distToBottomLeft; bl++)
			{
				tempX = xLocation - bl;
				tempY = yLocation - bl;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
		}
		else if(this.pieceType == 'R')
		{
			//Rook can move all the way up/down/left/right
			//calc dist to edges
			int distToRight = 4 - xLocation;
			int distToLeft = xLocation - 1;
			int distToTop = 4 - yLocation;
			int distToBottom = yLocation - 1;
			
			// moves to the right edge
			for(int x=1; x<=distToRight; x++)
			{
				tempX  = xLocation + x;
				if(isValidMove(tempX, yLocation) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, yLocation);
					moves.add(tempMove);
				}
			}
			// moves to the left edge
			for(int x=1; x<=distToLeft; x++)
			{
				tempX = xLocation - x;
				if(isValidMove(tempX, yLocation) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, yLocation);
					moves.add(tempMove);
				}
			}
			// moves to the top
			for(int y=1; y<=distToTop; y++)
			{
				tempY = yLocation + y;
				if(isValidMove(xLocation, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, xLocation, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the bottom
			for(int y=1; y<=distToBottom; y++)
			{
				tempY = yLocation - y;
				if(isValidMove(xLocation, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, xLocation, tempY);
					moves.add(tempMove);
				}
			}
		}
		else if(this.pieceType == 'B')
		{
			// Bishop can move diagonals
			//calc dist to edges
			int distToRight = 4 - xLocation;
			int distToLeft = xLocation - 1;
			int distToTop = 4 - yLocation;
			int distToBottom = yLocation - 1;
			
			// diagonal distance is determined by the closest edge
			int distToTopRight = (distToRight < distToTop ? distToRight : distToTop);
			int distToTopLeft = (distToLeft < distToTop ? distToLeft : distToTop);
			int distToBottomRight = (distToRight < distToBottom ? distToRight : distToBottom);
			int distToBottomLeft = (distToLeft < distToBottom ? distToLeft : distToBottom);
			
			// moves to the top right
			for(int tr = 1; tr<=distToTopRight; tr++)
			{
				tempX = xLocation + tr;
				tempY = yLocation + tr;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
			//moves to the top left
			for(int tl=1; tl<distToTopLeft; tl++)
			{
				tempX = xLocation - tl;
				tempY = yLocation + tl;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the bottom right
			for(int br=1; br<=distToBottomRight; br++)
			{
				tempX = xLocation + br;
				tempY = yLocation - br;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the bottom left
			for(int bl=1; bl<=distToBottomLeft; bl++)
			{
				tempX = xLocation - bl;
				tempY = yLocation - bl;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
		}
		else if(this.pieceType == 'N')
		{
			//knight can move in 'L' shape. 2 in one direction and 1 in the other
			//up and right
			tempY = yLocation + 2;
			tempX = xLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//up and left
			tempY = yLocation + 2;
			tempX = xLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//down and right
			tempY = yLocation - 2;
			tempX = xLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//down and left
			tempY = yLocation - 2;
			tempX = xLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//right and up
			tempX = xLocation + 2;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//right and down
			tempX = xLocation + 2;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//left and up
			tempX = xLocation - 2;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//left and down
			tempX = xLocation - 2;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
		}
		else if(this.pieceType == 'P')
		{
			//pawn can only go up/left or up/right
			//up and right
			tempX = xLocation + 1;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//up and left
			tempX = xLocation - 1;
			tempY = yLocation + 1;
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
