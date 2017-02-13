package hw1;

/* Class for the base class of all chess pieces
 * 
 */

public class ChessPiece {

	public String name;
	public int xLocation;
	public int yLocation;
	public int pieceID;
	public int pieceValue;
	
	public static int ChessPieceID; // keep a running id for the id of the pieces
	
	// default constructor
	ChessPiece()
	{
		name = "PieceName";
		xLocation = 0;
		yLocation = 0;
		pieceID = 0;
		pieceValue = 0;
	}
	
	//constructor
	ChessPiece(String newName, int x, int y)
	{
		name = newName;
		xLocation = x;
		yLocation = y;
		pieceID = ChessPieceID++; // get next id and increase id
		
		if(name == "K"){
			pieceID = ChessPieceID++;
			pieceValue = 10; // in solitaire chess different than regular chess
		}
		else if(name == "Q"){
			pieceID = ChessPieceID++;
			pieceValue = 9;
		}
		else if(name == "R"){
			pieceID = ChessPieceID++;
			pieceValue = 5;
		}
		else if(name == "B"){
			pieceID = ChessPieceID++;
			pieceValue = 3;
		}
		else if(name == "N"){
			pieceID = ChessPieceID++;
			pieceValue = 3;
		}
		else if(name == "P"){
			pieceID = ChessPieceID++;
			pieceValue = 1;
		}
		else{
			System.out.println("Unknown Piece");
		}
	}
}
