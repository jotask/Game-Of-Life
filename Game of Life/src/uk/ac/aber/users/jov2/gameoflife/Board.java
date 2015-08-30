package uk.ac.aber.users.jov2.gameoflife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

/**
 * This class holds all the information and logic for the game of life
 * 
 * @author Jota
 *
 */
public class Board {
	
	/**
	 * The width and the height for the all the board
	 * In the constructor we calculate the optimum size for the board
	 */
	private final int WIDTH, HEIGTH;
	
	/**
	 * The camera for the graphics and know where we are looking
	 */
	private OrthographicCamera camera;
	
	/**
	 * An array for store all the cells the board have
	 */
	private Cell[][] cells;
	
	/**
	 * Know if the algorithm can work or not
	 */
	private boolean run;
	
	/**
	 * Know if we need draw debug information (deleted) or the bounds
	 */
	private boolean debug;
	
	/**
	 * The constructor for this class
	 */
	public Board() {
		
		// Calculate how many cell can fit on the screen
		WIDTH = Gdx.graphics.getWidth() / Cell.CELLSIZE;
		HEIGTH = Gdx.graphics.getHeight() / Cell.CELLSIZE;
		
		// Instantiate the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// We say to GDX to use our own InputProcessor
		Gdx.input.setInputProcessor(new MyInput(camera));
		
		// Instantiate the cells 2D array and we create a default cell for each position
		cells = new Cell[WIDTH][HEIGTH];
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				cells[i][j] = new Cell(i, j, false);
			}
		}
		
	}
	
	/**
	 * Handle input and update logic
	 * @param delta the delta time
	 */
	public void update(float delta){
		// If the screen had been touched we toggle the state of the cell the user clicked
		if(Gdx.input.justTouched()){
			run = false;
			Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			int x = (int) touch.x / Cell.CELLSIZE;
			int y = (int) touch.y / Cell.CELLSIZE;
			cells[x][y].toggle();
		}
		
		// If the key CTRL_LEFT is pressed we toggle debug
		if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)) debug = !debug;
		
		// If the key SPACE is pressed we toggle the run variable
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) run = !run;
		
		// If run is true, we update the game logic
		if(run) updateLogicAlgorithm();;
	}
	
	/**
	 * We see all the neighbours and implement the rules from the game of life algorithm
	 */
	private void updateLogicAlgorithm(){
		// Create a new 2D array for the new cells
		Cell[][] tmp = new Cell[WIDTH][HEIGTH];
		
		// See each cell on the board
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				Cell cell = cells[i][j];
				tmp[i][j] = new Cell(cell.getPosition().x, cell.getPosition().y, false);
				// Know many neighbouds this cell have
				int neighbours = manyLiveNeightbours(cell);
				// Any live cell with fewer than two live neighbours dies, as if caused by under-population.
				// Any live cell with two or three live neighbours lives on to the next generation.
				// Any live cell with more than three live neighbours dies, as if by overcrowding.
				// Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
				if(cell.isLive()){
					if ((neighbours == 2) || (neighbours == 3)) {
                        tmp[i][j].setState(true);
                    } else {
                        tmp[i][j].setState(false);
                    }
				}else{
                    if (neighbours == 3) {
                        tmp[i][j].setState(true);
                    } else {
                        tmp[i][j].setState(false);
                    }
				}
			}
		}
		// Copy the new board to the actual board
		cells = tmp;
	}
	
	/**
	 * Know how many live neighbours have one cell
	 * @param cell the cell we want know how many neighbours have
	 * @return the number of neighbours
	 */
	private int manyLiveNeightbours(Cell cell){
		int n = 0;
		Vector2 p = cell.getPosition();

		//Left
		if(p.x > 0 && cells[p.x - 1][p.y].isLive()) n++;
		// Right
		if(p.x < WIDTH - 1 && cells[p.x + 1][p.y].isLive()) n++;
		// Top
		if(p.y > 0 && cells[p.x][p.y - 1].isLive()) n++;
		// Bottom
		if(p.y < HEIGTH - 1 && cells[p.x][p.y + 1].isLive()) n++;
		// Top Left
		if((p.y > 0 && p.x > 0) && (cells[p.x - 1][p.y - 1].isLive())) n++;
		// Top Right
		if((p.y > 0 && p.x < WIDTH - 1) && (cells[p.x + 1][p.y - 1].isLive())) n++;
		// Bottom Left
		if((p.y < HEIGTH - 1 && p.x > 0) && (cells[p.x - 1][p.y + 1].isLive())) n++;
		// Bottom Right
		if((p.y < HEIGTH - 1 && p.x < WIDTH - 1) && (cells[p.x + 1][p.y + 1].isLive())) n++;
		
		return n;
	}
	
	/**
	 * Render each cell on the board
	 * @param sr
	 */
	public void render(ShapeRenderer sr){
		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeType.Filled);
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				cells[i][j].render(sr);
			}
		}
		sr.end();
		
		// If debug is true we draw a cell bound
		if(debug){
			sr.setProjectionMatrix(camera.combined);
			sr.begin(ShapeType.Line);
			sr.setColor(Color.CYAN);
			for(int i = 0; i < cells.length; i++){
				for (int j = 0; j < cells[0].length; j++){
					cells[i][j].renderDebug(sr);
					
				}
			}
			sr.end();
		}	
	}

}