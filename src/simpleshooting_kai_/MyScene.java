package simpleshooting_kai_;
import java.awt.Graphics;

// MyScene型は少なくともupdateとdrawメソッドは定義しなければならない
abstract class MyScene {

	void sceneDraw(Graphics g, int tick) {
		draw(g, tick);
	}

	MyScene sceneUpdate(int tick){
		return update(tick++);
	}

	abstract void draw(Graphics g, int tick);

	abstract MyScene update(int tick);
}
