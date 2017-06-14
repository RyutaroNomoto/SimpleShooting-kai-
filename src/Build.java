import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Runnableを持つクラスだけがスレッドで使える
public class Build extends JFrame implements Runnable {

	private Graphics g;
	private MyScene scene;
	private int fps = 60;
	private Image screen;

	// 以下は後にインターフェースへ移動
	private static final int G_WIDTH = 100, G_HEIGHT = 100;
	private static final MyKey KEY_LISTENER = new MyKey();

	Build(String title) {
		// このフレームのタイトルを設定する
		setTitle(title);
		// ×ボタンが押された時の挙動を設定する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// このフレーム(ウィンドウ)がリサイズ可能か設定する
		setResizable(false);
		// このJFrameのコンテント部分(メインとなる部品)を参照し、サイズの設定を行う
		getContentPane().setPreferredSize(new Dimension(G_WIDTH, G_HEIGHT));
		// 上記で指定したサイズはウィンドウのヘッダー部分等も含む値だが
		// それらを含まないようにしてリサイズするメソッド
		pack();
		// このウィンドウの初期表示位置(ディスプレイのどこに配置するか)
		// nullだと画面中央となる
		setLocationRelativeTo(null);

		// JPanelを継承した無名クラスのインスタンスを生成
		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(screen, 0, 0, this);
			}

			@Override
			public void update(Graphics g) {
				paint(g);
			}
		};

		// キー入力を検知するクラスのインスタンスをパネルに組み込む
		panel.addKeyListener(KEY_LISTENER);
		// 準備ができたら最後にpanelを可視化させるようにするメソッド
		panel.setFocusable(true);
		// このJFrameにpanelをaddすることでcontentpaneに張り付ける
		add(panel);

		screen = createImage(G_WIDTH, G_HEIGHT);

	}

	Build setFps(int fps) {
		this.fps = fps;
		return this;
	}

	Build start() {
		// sceneがヌルならStage1を表示する
		if (scene == null) {
			scene = Stage1.STAGE1;
		}
		new Thread(this);
		return this;
	}

	@Override
	public void run() {
		// 現在の時刻をミリ秒単位で得る
		long processTime = System.currentTimeMillis();

		// ここがスレッド内で実際にさせたい処理
		scene.sceneDraw();
		scene.sceneUpdate();

		// この処理にかかった時間が設定したFPS内で終わったか否か
		if (System.currentTimeMillis() - processTime <= 1000 / fps) {
			try {
				// 処理をいったん停止し、次の処理が始まる時間となるまで停止させる
				Thread.sleep(1000 / fps - processTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// FPSよりも処理が長くなった場合は意図しない挙動になるのでコンソールでエラーを通知
			System.out.println("ERRO -> 処理落ち");
		}
	}

}
