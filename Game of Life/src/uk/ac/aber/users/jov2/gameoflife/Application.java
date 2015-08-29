package uk.ac.aber.users.jov2.gameoflife;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Application implements ApplicationListener {
	
	private ShapeRenderer sr;
	private Board board;
	
	@Override
	public void create() {
		sr = new ShapeRenderer();
		board = new Board();
	}

	@Override
	public void resize(int width, int height) { }

	@Override
	public void render() {
		if(Gdx.input.justTouched()){
			board = new Board();
		}
		board.update(Gdx.graphics.getDeltaTime());
		board.render(sr);
	}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() {
		sr.dispose();
	}

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 600;
		config.fullscreen = false;
		config.resizable = false;
		config.title = "Game of Life";
		
		new LwjglApplication(new Application(), config);
	}

}
