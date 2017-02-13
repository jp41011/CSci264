package hw1;

/* Class for the base class of all chess pieces
 * 
 */

public class ChessPiece {

	public char name;
	public int xLocation;
	public int yLocation;
	public int pieceID;
	public int pieceValue;
	
	public static int ChessPieceID; // keep a running id for the id of the pieces
	
	// default constructor
	ChessPiece()
	{
		name = '0';
		xLocation = 0;
		yLocation = 0;
		pieceID = 0;
		pieceValue = 0;
	}
	
	//constructor
	ChessPiece(char newName, int x, int y)
	{
		this.name = newName;
		xLocation = x;
		yLocation = y;
		pieceID = ChessPieceID++; // get next id and increase id
		
		if(this.name == 'K'){
			pieceValue = 10; // in solitaire chess different than regular chess
		}
		else if(this.name == 'Q'){
			pieceValue = 9;
		}
		else if(this.name == 'R'){
			pieceValue = 5;
		}
		else if(this.name == 'B'){
			pieceValue = 3;
		}
		else if(this.name == 'N'){
			pieceValue = 3;
		}
		else if(this.name == 'P'){
			pieceValue = 1;
		}
		else{
			System.out.println("Unknown Piece");
			System.out.println(newName + " " + x + " " + y);
		}
	}
}
