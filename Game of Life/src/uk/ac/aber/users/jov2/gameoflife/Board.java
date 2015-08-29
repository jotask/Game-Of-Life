package uk.ac.aber.users.jov2.gameoflife;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Board {
	
	private final int WIDTH, HEIGTH;
	private OrthographicCamera camera;
	
	private Cell[][] cells;
	private boolean run;
	
	public Board() {
		WIDTH = Gdx.graphics.getWidth() / Cell.CELLSIZE;
		HEIGTH = Gdx.graphics.getHeight() / Cell.CELLSIZE;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		cells = new Cell[WIDTH][HEIGTH];
		
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				cells[i][j] = new Cell(i, j, new Random().nextBoolean());
			}
		}
		
	}
	
	public void update(float delta){
		if(Gdx.input.justTouched()){
			Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			int x = (int) touch.x / Cell.CELLSIZE;
			int y = (int) touch.y / Cell.CELLSIZE;
			cells[x][y].toggle();
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) run = !run;
		if(run) updateLogicAlgorithm();;
	}
	
	private void updateLogicAlgorithm(){

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
	}

}
