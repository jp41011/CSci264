package hw1;

/* Class to represent information about a chess move
 * 
 */
public class ChessMove {

	public char pieceType;
	public int xLocation;
	public int yLocation;
	public int moveID;
	
	public static int ChessMoveID;
	
	ChessMove(){
		pieceType = '0';
		xLocation = 0;
		yLocation = 0;
		moveID = -1;
	}
	
	ChessMove(char type, int xloc, int yloc)
	{
		pieceType = type;
		xLocation = xloc;
		yLocation = yloc;
		moveID = ChessMoveID++;
	}
	
}
