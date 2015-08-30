package uk.ac.aber.users.jov2.gameoflife;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MyInput implements InputProcessor{
	
	private OrthographicCamera cam;
	private float zoom = 1f;
	
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
