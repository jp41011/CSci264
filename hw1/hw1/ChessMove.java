package hw1;

/* Class to represent information about a chess move
 * 
 */
public class ChessMove {

	public int pieceID;
	public char pieceType;
	public int xLocation;
	public int yLocation;
	public int moveID;
	
	public static int ChessMoveID;
	
	ChessMove(){
		pieceID = -1;
		pieceType = '0';
		xLocation = 0;
		yLocation = 0;
		moveID = -1;
	}
	
	ChessMove(int newID, char type, int xloc, int yloc)
	{
		pieceID = newID;
		pieceType = type;
		xLocation = xloc;
		yLocation = yloc;
		moveID = ChessMoveID++;
	}
	
}
