package uk.ac.aber.users.jov2.gameoflife;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * The class for hold the information for the celds and his state for the board
 * 
 * @author Jota
 *
 */
public class Cell {
	
	/**
	 * The Cell size for know when is drawing the cells
	 */
	public static final int CELLSIZE = 4;
	
	/**
	 * Boolean for hold the state, because the state have only two possible values (on or off, live or dead, etc)
	 * i use a boolean because only can have two states
	 */
	private boolean state;
	
	/**
	 * The position for this cell instance
	 */
	private Vector2 position;
	
	/**
	 * Create a cell with his x and y positions, and his default state
	 * @param x the x position in the board
	 * @param y the y position in the board
	 * @param state the default state
	 */
	 public Cell(int x, int y, boolean state) {
		this.state = state;
		this.position = new Vector2(x, y);
	}

	/**
	* Know the state of this instance
	* @return the state of this instance
	*/
	public boolean isLive() { return state; }
	
	/**
	 * Get the Cell position
	 */
	public Vector2 getPosition(){ return this.position; }
	
	/**
	 * Toggle the state for this instance
	 */
	public void toggle(){ this.state = !state; }
	
	/**
	 * Render the cell creating a rectangle, and depending in his state draw black or white
	 * @param sr the ShapeRenderer class for draw
	 */
	public void render(ShapeRenderer sr) {
		if(state){
			sr.setColor(Color.BLACK);
		}else{
			sr.setColor(Color.WHITE);
		}
		sr.box(position.x * CELLSIZE, position.y * CELLSIZE, 0, CELLSIZE, CELLSIZE, 0);
	}

}
