import java.awt.Graphics;

// MyScene型は少なくともupdateとdrawメソッドは定義しなければならない
abstract class MyScene {

	private int tick;
	private Graphics g;

	void sceneDraw() {
		draw(g, tick);
	}

	void sceneUpdate(){
		update(tick++);
	}

	abstract void draw(Graphics g, int tick);

	abstract MyScene update(int tick);
}
