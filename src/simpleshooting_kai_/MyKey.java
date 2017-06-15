package simpleshooting_kai_;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKey implements KeyListener {

	private boolean up, down, left, right, shot, isPose, testkeyC, testkeyS;

	final int nKEY_SHOT1 = KeyEvent.VK_Z;
	final int nKEY_LEFT = KeyEvent.VK_LEFT;// KeyEvent.VK_A;
	final int nKEY_RIGHT = KeyEvent.VK_RIGHT;// KeyEvent.VK_D;
	final int nKEY_UP = KeyEvent.VK_UP;// KeyEvent.VK_W;
	final int nKEY_DOWN = KeyEvent.VK_DOWN;// KeyEvent.VK_S;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// キーが押されている時の状態を検知
	@Override
	public void keyPressed(KeyEvent e) {
		// キーが押されたらboolean変数にtrueを代入
		switch (e.getKeyCode()) {
		case nKEY_SHOT1:
			shot = true;
			break;
		case KeyEvent.VK_SPACE:
			shot = true;
			break;
		case KeyEvent.VK_ESCAPE:
			isPose = !isPose;
			break;
		case nKEY_RIGHT:
			right = true;
			break;
		case nKEY_LEFT:
			left = true;
			break;
		case nKEY_UP:
			up = true;
			break;
		case nKEY_DOWN:
			down = true;
			break;
		case KeyEvent.VK_C:
			testkeyC = true;
			break;
		case KeyEvent.VK_S:
			testkeyS = true;
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// キーが離されたらboolean変数にfalseを代入
		switch (e.getKeyCode()) {
		case nKEY_SHOT1:
			shot = false;
			break;
		case KeyEvent.VK_SPACE:
			shot = false;
			break;
		case nKEY_RIGHT:
			right = false;
			break;
		case nKEY_LEFT:
			left = false;
			break;
		case nKEY_UP:
			up = false;
			break;
		case nKEY_DOWN:
			down = false;
			break;
		case KeyEvent.VK_C:
			testkeyC = false;
			break;
		case KeyEvent.VK_S:
			testkeyS = false;
			break;
		}
	}

	protected boolean isUp() {
		return up;
	}

	protected boolean isDown() {
		return down;
	}

	protected boolean isShot() {
		return shot;
	}

	protected boolean isTest() {
		return testkeyC;
	}

	protected boolean isTest2() {
		return testkeyS;
	}

	protected boolean isRight() {
		return right;
	}

	protected boolean isLeft() {
		return left;
	}

	protected boolean isPose() {
		return isPose;
	}

}