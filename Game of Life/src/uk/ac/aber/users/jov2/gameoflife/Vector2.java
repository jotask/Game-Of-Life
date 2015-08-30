package uk.ac.aber.users.jov2.gameoflife;

/**
 * This class is for hold the ant position in the board.
 * 
 * @author Jota
 *
 */
public class Vector2 {
	
	/**
	 * The x position
	 */
	public int x;
	/**
	 * The y position
	 */
	public int y;
	
	/**
	 * Default constructor for initialise this class without any parameter
	 */
	public Vector2() {
		this(0,0);
	}

	/**
	 * Constructor for initialise this class with parameters
	 * @param x the X value
	 * @param y the Y value
	 */
	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Set all the values for this instance
	 * @param x the new X value
	 * @param y the new Y value
	 */
	public void set(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Change the value for the X
	 * @param x new X value
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Change the value for the Y
	 * @param y the new Y value
	 */
	public void setY(int y){
		this.y = y;
	}

}
