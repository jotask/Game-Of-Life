package uk.ac.aber.users.jov2.gameoflife;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Board {
	
	private final int WIDTH, HEIGTH;
	
	private Cell[][] cells;
	private boolean run;
	
	public Board() {
		WIDTH = Gdx.graphics.getWidth() / Cell.CELLSIZE;
		HEIGTH = Gdx.graphics.getHeight() / Cell.CELLSIZE;
		
		cells = new Cell[WIDTH][HEIGTH];
		
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				cells[i][j] = new Cell(i, j, new Random().nextBoolean());
			}
		}
		
	}
	
	public void update(float delta){
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) run = !run;
		if(run) updateLogicAlgorithm();;
	}
	
	private void updateLogicAlgorithm(){

	}
	
	public void render(ShapeRenderer sr){
		sr.begin(ShapeType.Filled);
		for(int i = 0; i < cells.length; i++){
			for (int j = 0; j < cells[0].length; j++){
				cells[i][j].render(sr);
			}
		}
		sr.end();
	}

}
