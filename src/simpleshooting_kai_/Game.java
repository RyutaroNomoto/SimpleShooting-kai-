package simpleshooting_kai_;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Runnableを持つクラスだけがスレッドで使える
public class Game extends JFrame implements Runnable {

	private MyScene scene;
	private int fps = 60;
	private Image screen;
	private String title = "タイトル未設定";
	private Graphics graphics;

	Game(String title) {
		// このフレームのタイトルを設定する
		this.title = title;
		setTitle(this.title);
		// ×ボタンが押された時の挙動を設定する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// このフレーム(ウィンドウ)がリサイズ可能か設定する
		setResizable(false);
		// このJFrameのコンテント部分(メインとなる部品)を参照し、サイズの設定を行う
		getContentPane().setPreferredSize(new Dimension(MyInterface.G_WIDTH, MyInterface.G_HEIGHT));
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
		panel.addKeyListener(MyInterface.KEY_LISTENER);
		// 準備ができたら最後にpanelを可視化させるようにするメソッド
		panel.setFocusable(true);
		// このJFrameにpanelをaddすることでcontentpaneに張り付ける
		add(panel);

		screen = createImage(MyInterface.G_WIDTH, MyInterface.G_HEIGHT);

	}

	Game setFps(int fps) {
		this.fps = fps;
		return this;
	}

	Game start() {
		// sceneがヌルならStage1を表示する
		if (scene == null) {
			scene = Stage1.STAGE1;
		}
		setVisible(true);
		new Thread(this).start();
		return this;
	}

	public static void main(String[] args) {
		Game g = new Game("タイトル");
		g.start().setFps(60).setTitle("サンプル");
	}

	@Override
	public void run() {

		Graphics gCanvas = screen.getGraphics();
		this.graphics = gCanvas;
		int tick = 0;
		int tickPause = 0;

		while (true) {
			// 現在の時刻をミリ秒単位で得る
			long processTime = System.currentTimeMillis();

			// ここがスレッド内で実際にさせたいメイン処理
			gCanvas.setColor(Color.BLUE);
			gCanvas.fillRect(0, 0, MyInterface.G_WIDTH, MyInterface.G_HEIGHT);
			gCanvas.drawImage(screen, 0, 0, this);
			repaint();
			scene.sceneDraw(gCanvas, tick);

			// updateを実行させつつ返り値であるシーンが切り替わっているか確認
			MyScene newScene = scene.sceneUpdate(tick);
			// シーンが切り替わっていれば自分のsceneに代入して実行するsceneを切り替える
			if (scene != newScene) {
				scene = newScene;
			}

			// この処理にかかった時間が設定したFPS内で終わったか否か
			processTime = System.currentTimeMillis() - processTime;
			if (processTime <= 1000 / fps) {
				try {
					// 処理をいったん停止し、次の処理が始まる時間となるまで停止させる
					Thread.sleep(1000 / fps - processTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				// FPSよりも処理が長くなった場合は意図しない挙動になるのでコンソールでエラーを通知
				System.out.println("ERROR -> 処理落ち");
			}
		}
	}

}
