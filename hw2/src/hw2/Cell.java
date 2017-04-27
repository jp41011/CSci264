package hw2;

public class Cell {
	
	public int x;
	public int y;
	public int val;
	
	// constructor with only coordinates given
	Cell(int tempX, int tempY)
	{
		x = tempX;
		y = tempY;
		val = 0;
	}
	
	// constructor with all values given
	Cell(int tempX, int tempY, int tempVal)
	{
		x = tempX;
		y = tempY;
		val = tempVal;
	}
	
	// getters
	int getX(){return x;}
	int getY(){return y;}
	int getVal(){return val;}

}
