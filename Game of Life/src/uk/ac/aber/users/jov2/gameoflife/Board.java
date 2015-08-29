package uk.ac.aber.users.jov2.gameoflife;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Board {
	
	private final int WIDTH, HEIGTH;
	private OrthographicCamera camera;
	
	private Cell[][] cells;
	private boolean run;
	private boolean debug;
	
	public Board() {
		
		WIDTH = Gdx.graphics.getWidth() / Cell.CELLSIZE;
		HEIGTH = Gdx.graphics.getHeight() / Cell.CELLSIZE;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		Gdx.input.setInputProcessor(new MyInput(camera));
		
		cells = new Cell[WIDTH][HEIGTH];
		
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				cells[i][j] = new Cell(i, j, false);
			}
		}
		
	}
	
	public void update(float delta){
		if(Gdx.input.justTouched()){
			run = false;
			Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			int x = (int) touch.x / Cell.CELLSIZE;
			int y = (int) touch.y / Cell.CELLSIZE;
			cells[x][y].toggle();
		}
		if(Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)) debug = !debug;
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) run = !run;
		if(run) updateLogicAlgorithm();;
	}
	
	private void updateLogicAlgorithm(){
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				Cell cell = cells[i][j];
				int neighbours = manyLiveNeightbours(cell);
				// Any live cell with fewer than two live neighbours dies, as if caused by under-population.
				// Any live cell with two or three live neighbours lives on to the next generation.
				// Any live cell with more than three live neighbours dies, as if by overcrowding.
				// Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
				if(cell.isLive()){
					if(neighbours < 2){
						cell.toggle();
					}else if(neighbours == 2 || neighbours == 3){
						break;
					}else if(neighbours > 3){
						cell.toggle();
					}
				}else{
					if(neighbours == 3) cell.toggle();
				}
			}
		}
	}
	
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
	
	public void render(ShapeRenderer sr){
		sr.setProjectionMatrix(camera.combined);
		sr.begin(ShapeType.Filled);
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				cells[i][j].render(sr);
			}
		}
		sr.end();
		
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
