import java.awt.Graphics;

public class Stage1 extends MyScene {

	// 1度しかインスタンス化しなくないのでコンストラクタを外部から
	// 見れなくするためにprivateにする
	static final Stage1 STAGE1 = new Stage1();

	private Stage1() {
	}

	@Override
	void draw(Graphics g, int tick) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	MyScene update(int tick) {
		// TODO 自動生成されたメソッド・スタブ
		return this;
	}

}
