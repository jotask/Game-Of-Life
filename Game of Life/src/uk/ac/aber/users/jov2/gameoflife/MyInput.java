package uk.ac.aber.users.jov2.gameoflife;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created a input handling for mouse wheel, this class implements InputProccessor from GDX
 * 
 * @author Jota
 *
 */
public class MyInput implements InputProcessor{
	
	/**
	 * We want a camera instance for change is zoom value
	 */
	private OrthographicCamera cam;
	private float zoom = 1f;
	
	/**
	 * Constructor for this class
	 * @param cam the camera we are using
	 */
	public MyInput(OrthographicCamera cam) {
		this.cam = cam;
	}

	@Override
	public boolean keyDown(int keycode) { return false; }

	@Override
	public boolean keyUp(int keycode) { return false; }

	@Override
	public boolean keyTyped(char character) { return false; }

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }

	@Override
	public boolean mouseMoved(int screenX, int screenY) { return false; }

	/**
	 * This method is call when the mouse scroll wheel have been moved.
	 * We see increase or decrease the zoom depending how much we move the mouse wheel
	 * return true for let know GDX we process the input and they doen's need to do anything with this process
	 */
	@Override
	public boolean scrolled(int amount) {
		//Zoom out
		if (amount > 0 && zoom < 1) zoom += 0.1f;
		 //Zoom in
		if (amount < 0 && zoom > 0.1) zoom -= 0.1f;
		cam.zoom = zoom;
		cam.update();
		return true;
	}
	
}
