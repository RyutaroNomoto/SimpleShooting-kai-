package simpleshooting_kai_;
import java.awt.Graphics;

public class Stage1 extends MyScene {

	// 1度しかインスタンス化しなくないのでコンストラクタを外部から
	// 見れなくするためにprivateにする
	static final Stage1 STAGE1 = new Stage1();

	private Stage1() {
	}

	@Override
	void draw(Graphics g, int tick) {
		System.out.println("draw");
	}

	@Override
	MyScene update(int tick) {
		System.out.println("update");
		return this;
	}

}
