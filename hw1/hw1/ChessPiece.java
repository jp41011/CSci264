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
	
	/**
	 * Returns all the possible chess moves for the current piece. <br/>
	 * Not the same as eligible Solitaire Chess moves.
	 * @return
	 */
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
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//else invalid location so do not add to list of moves.
			
			// king can go up and to the right
			tempX = xLocation + 1;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king can go to the right
			tempX = xLocation + 1;
			tempY = yLocation;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - down and to the right
			tempX = xLocation + 1;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - down
			tempX = xLocation;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - down and to the left
			tempX = xLocation - 1;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - left
			tempX = xLocation - 1;
			tempY = yLocation;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			
			//king - left and up
			tempX = xLocation - 1;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
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
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, yLocation);
					moves.add(tempMove);
				}
			}
			// moves to the left edge
			for(int x=1; x<=distToLeft; x++)
			{
				tempX = xLocation - x;
				if(isValidMove(tempX, yLocation) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, yLocation);
					moves.add(tempMove);
				}
			}
			// moves to the top
			for(int y=1; y<=distToTop; y++)
			{
				tempY = yLocation + y;
				if(isValidMove(xLocation, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, xLocation, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the bottom
			for(int y=1; y<=distToBottom; y++)
			{
				tempY = yLocation - y;
				if(isValidMove(xLocation, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, xLocation, tempY);
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
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
			//moves to the top left
			for(int tl=1; tl<=distToTopLeft; tl++)
			{
				tempX = xLocation - tl;
				tempY = yLocation + tl;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
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
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
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
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
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
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, yLocation);
					moves.add(tempMove);
				}
			}
			// moves to the left edge
			for(int x=1; x<=distToLeft; x++)
			{
				tempX = xLocation - x;
				if(isValidMove(tempX, yLocation) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, yLocation);
					moves.add(tempMove);
				}
			}
			// moves to the top
			for(int y=1; y<=distToTop; y++)
			{
				tempY = yLocation + y;
				if(isValidMove(xLocation, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, xLocation, tempY);
					moves.add(tempMove);
				}
			}
			// moves to the bottom
			for(int y=1; y<=distToBottom; y++)
			{
				tempY = yLocation - y;
				if(isValidMove(xLocation, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, xLocation, tempY);
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
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
					moves.add(tempMove);
				}
			}
			//moves to the top left
			for(int tl=1; tl<=distToTopLeft; tl++)
			{
				tempX = xLocation - tl;
				tempY = yLocation + tl;
				if(isValidMove(tempX, tempY) == true)
				{
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
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
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
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
					ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
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
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//up and left
			tempY = yLocation + 2;
			tempX = xLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//down and right
			tempY = yLocation - 2;
			tempX = xLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//down and left
			tempY = yLocation - 2;
			tempX = xLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//right and up
			tempX = xLocation + 2;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//right and down
			tempX = xLocation + 2;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//left and up
			tempX = xLocation - 2;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//left and down
			tempX = xLocation - 2;
			tempY = yLocation - 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
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
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
			//up and left
			tempX = xLocation - 1;
			tempY = yLocation + 1;
			if(isValidMove(tempX, tempY) == true)
			{
				ChessMove tempMove = new ChessMove(this.pieceID, this.pieceType, tempX, tempY);
				moves.add(tempMove);
			}
		}
		
		return moves;
	}
	
	/**
	 * 
	 * @param xLoc - x coordinate (1-4) (left-right)
	 * @param yLoc - y coordinate (1-4) (down-up)
	 * @return
	 */
	private boolean isValidMove(int xLoc, int yLoc)
	{
		if(xLoc < 1 || xLoc > 4 || yLoc < 1 || yLoc > 4)
			return false;
		else
			return true;
	}
	
	protected ChessMove moveUp(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc, yloc+1);
		return move;
	}
	
	protected ChessMove moveDown(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc, yloc-1);
		return move;
	}
	
	protected ChessMove moveLeft(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc-1, yloc);
		return move;
	}
	
	protected ChessMove moveRight(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc+1, yloc);
		return move;
	}
	
	protected ChessMove moveUpLeft(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc-1, yloc+1);
		return move;
	}
	
	protected ChessMove moveUpRight(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc+1, yloc+1);
		return move;
	}
	
	protected ChessMove moveDownRight(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc+1, yloc-1);
		return move;
	}
	
	protected ChessMove moveDownLeft(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc-1, yloc-1);
		return move;
	}
	
	protected ChessMove moveKnight1(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc-1, yloc+2);
		return move;
	}
	
	protected ChessMove moveKnight2(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc+1, yloc+2);
		return move;
	}
	
	protected ChessMove moveKnight3(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc+2, yloc+1);
		return move;
	}
	
	protected ChessMove moveKnight4(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc+2, yloc-1);
		return move;
	}
	
	protected ChessMove moveKnight5(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc+1, yloc-2);
		return move;
	}
	
	protected ChessMove moveKnight6(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc-1, yloc-2);
		return move;
	}
	
	protected ChessMove moveKnight7(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc-2, yloc-1);
		return move;
	}
	
	protected ChessMove moveKnight8(int xloc, int yloc)
	{
		ChessMove move = new ChessMove(-1, '0', xloc-2, yloc+1);
		return move;
	}
	
	/**
	 * Given a array of pieces on the board this function will return the list of eligible solitaire chess moves.
	 * @param board - array list of chess pieces on the board
	 * @return - list of eligible solitaire chess moves
	 */
	protected ArrayList<ChessMove> getGoodMoves(ArrayList<ChessPiece> board)
	{
		ArrayList<ChessMove> moves = new ArrayList<ChessMove>();
		int pieceIndex = getIndexOfThisPiece(board);
		int thisX = board.get(pieceIndex).xLocation;
		int thisY = board.get(pieceIndex).yLocation;
		
		if(this.pieceType == 'K'){
			ChessMove tmove = moveUp(thisX, thisY);
			if(isValidMove(tmove.xLocation, tmove.yLocation))
			{
				if(isOccupied(tmove.xLocation, tmove.yLocation, board))
				{
					tmove.pieceID = this.pieceID;
					tmove.pieceType = this.pieceType;
					moves.add(tmove);
				}
			}
			// other king moves TODO
		}else if(this.pieceType == 'Q'){
			pieceValue = 9;
		}else if(this.pieceType == 'R'){
			pieceValue = 5;
		}else if(this.pieceType == 'B'){
			pieceValue = 3;
		}else if(this.pieceType == 'N'){
			pieceValue = 3;
		}else if(this.pieceType == 'P'){
			pieceValue = 1;
		}else{

		}
		
		return moves;
	}
	
	protected boolean isOccupied(int xloc, int yloc, ArrayList<ChessPiece> board)
	{
		for(int i=0; i<board.size(); i++)
		{
			if(board.get(i).xLocation == xloc && board.get(i).yLocation == yloc)
				return true;
		}
		return false;
	}
	
	/**
	 * Given an array of pieces it will find THIS piece in the array and return the index
	 * @param pieces
	 * @return index of THIS current piece in the array
	 */
	protected int getIndexOfThisPiece(ArrayList<ChessPiece> pieces)
	{
		for(int i=0; i<pieces.size(); i++)
		{
			// if found this piece in the array then return the index
			if(pieces.get(i).pieceID == this.pieceID && pieces.get(i).pieceType == this.pieceType)
				return i;
		}
		return -1; // did not find piece in the array
	}
}
