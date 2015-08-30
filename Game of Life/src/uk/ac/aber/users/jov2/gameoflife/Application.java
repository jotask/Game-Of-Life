package uk.ac.aber.users.jov2.gameoflife;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Main class with the main method.
 * This class implements ApplicationListener for the libGDX logic and how it work inside
 * 
 * @author Jota
 *
 */
public class Application implements ApplicationListener {
	
	/**
	 * The ShapeRenderer class provided by GDX. This class create a batch of objects with basics shapes for be renderer
	 * by GPU.
	 */
	private ShapeRenderer sr;
	
	/**
	 * The board who holds all board logic and his cells
	 */
	private Board board;
	
	/**
	 * This methods is call when GDX finish load all classes and create this.
	 * This is used for initialise the class
	 */
	@Override
	public void create() {
		sr = new ShapeRenderer();
		board = new Board();
	}

	/**
	 * This method is where is the logic updates, and render everything
	 * When we call update, we pass the delta time
	 * When we call the render method, we pass the ShapeRenderer
	 */
	@Override
	public void render() {
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) board = new Board();
		
		// Update logic
		board.update(Gdx.graphics.getDeltaTime());
		
		// Render method
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		board.render(sr);
	}
	
	/**
	 * This is call when we resize the window, but because is disabled change the window size, this have not effect
	 */
	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	/**
	 * Some class needs to be dispose for free his memory use
	 */
	@Override
	public void dispose() { sr.dispose(); }

	/**
	 * Main method
	 * Create a LWJGL configuration class, for configured some aspect for or application
	 * And create the application with the configuration create before
	 * @param args Java parameter
	 */
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
