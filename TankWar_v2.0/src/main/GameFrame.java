package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import config.PlayerInRd;
import player.Player;
import time.Timers;

/**
 * 坦克大战的主类
 */

public class GameFrame extends JFrame implements ActionListener {
	Map mp =null;

	int[] speedx= {};
	private static final long serialVersionUID = 5972735870004738773L;

	public static final int Fram_width = 800; // 静态全局窗口大小
	public static final int Fram_length = 600;
	public static boolean printable = true; // 记录暂停状态，此时线程不刷新界面
	public static int level=1;
	public static int score=0;

	public static int endmin,endsec;
	
	MenuBar jmb = null;
	Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null,jm5=null;
	MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null, jmi6 = null, jmi7 = null, jmi8 = null,
			jmi9 = null,jmi10=null;
	Image screenImage = null;
	Timers timers =new Timers();
	PlayerInRd playerInRd=new PlayerInRd();
	List<Player> playlistList=null;
	Player player=null;
	
	static String  account=null;

	Tank homeTank = new Tank(300, 560, true, Direction.STOP, this,1,false);// 实例化坦克
	Blood blood = new Blood(); // 实例化血包
	Home home = new Home(373, 545, this);// 实例化home
	Font labelFont=new Font("TimesRoman", Font.BOLD, 20);
	Font dataFont = new Font("TimesRoman", Font.ITALIC, 30);

	// 以下集合变量在构造方法中进行了初始化
	List<River> theRiver = new ArrayList<River>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<BombTank> bombTanks = new ArrayList<BombTank>();
	List<Bullets> bullets = new ArrayList<Bullets>();
	List<Tree> trees = new ArrayList<Tree>();
	List<BrickWall> homeWall = new ArrayList<BrickWall>(); // 实例化对象容器
	List<BrickWall> otherWall = new ArrayList<BrickWall>();
	List<MetalWall> metalWall = new ArrayList<MetalWall>();

	////初始化地图数据、玩家类
	void init() {
		if(mp==null) {
			mp = new Map(this);
		}
		if(playlistList==null) {
			playlistList=playerInRd.getNode();
		}
		if(player==null) {
			for(Player play:playlistList) {
				if (this.account.equals(play.account)) {
					player=play;
					break;
				}
			}
		}
	}
	// 这是一个重写的方法,将由repaint()方法自动调用
	public void paint(Graphics g) {
		
		screenImage = this.createImage(Fram_width, Fram_length);
		Graphics gps = screenImage.getGraphics();
		Color c = gps.getColor();
		gps.setColor(Color.GRAY);
		gps.fillRect(0, 0, Fram_width, Fram_length);
		gps.setColor(c);

		framPaint(gps);

		g.drawImage(screenImage, 0, 0, null);
	}

	void showGameData(Graphics g) {
		Font f1 = g.getFont();
		g.setFont(labelFont);
		g.drawString("关卡: ", 680, 70 );
		g.drawString("区域内还有敌方坦克: ", 200, 70 );
		g.drawString("我方剩余生命值: ", 450, 70);
		g.drawString("游戏时间:", 20, 70);
		g.drawString("游戏分数:    ", 20, 100);
		
		g.setFont(dataFont);
		g.drawString(" " + level, 720, 70);
		g.drawString("" + tanks.size(), 400, 70);
		g.drawString(" " + homeTank.getLife(), 600, 70);
		g.drawString(""+score, 135, 100);
		
		if(Timers.ss<10&&Timers.mm<10) {
			g.drawString("  "+"0"+(Timers.mm)+":"+"0"+(Timers.ss), 100, 70 );
		}else if(Timers.ss>=10&&Timers.mm<10) {
			g.drawString("  "+"0"+(Timers.mm)+":"+(Timers.ss), 100, 70 );
		}else if(Timers.ss<10&&Timers.mm>=10) {
			g.drawString("  "+(Timers.mm)+":"+"0"+(Timers.ss), 100, 70 );
		}else if(Timers.ss>=10&&Timers.mm>=10) {
			g.drawString("  "+(Timers.mm)+":"+(Timers.ss), 100, 70 );
		}
		g.setFont(labelFont);
	}
	
	void judgeGame(Graphics g) {
		// 如果玩家赢了（条件是敌方坦克全灭、大本营健在、玩家坦克仍有血量）
				if (tanks.size() == 0 && home.isLive() && homeTank.isLive()) {
					if(level<4) {
//					bombTanks.clear();
//					theRiver.clear();
//				    bullets.clear();
					storeScore();
					level++;
					dispose();
					
					new GameFrame(account);
					}
					else if(level==4) {
						storeScore();
						if (tanks.size() == 0 && home.isLive() && homeTank.isLive()) {
							//赢了后不能动
							printable=false;
						}
					}
				}else{
					Timers timers =new Timers();
					Timers.mm=timers.minute-Timers.m;
					Timers.ss=timers.second-Timers.s;
						
				       if(Timers.ss<0) {
				    	   Timers.mm--;
				    	   Timers.ss+=60;
				       }
				}
				switch(level) {//如果玩家输了(大本营死亡或者坦克死亡或者时间到了)
				    case 1:
				    	endmin = 0;
				    	endsec = 30;
				    	break;
				    case 2:
				    	endmin = 1;
				    	endsec = 0;
				    	break;
				    case 3:
				    	endmin = 1;
				    	endsec = 30;
				    	break;
				    case 4:
				    	endmin = 2;
				    	endsec = 0;
				    	break;
				}
				if (homeTank.isLive() == false || (Timers.mm == endmin &&Timers.ss == endsec)) {
				       home.gameOver(g);
				       printable = false;
			    }		
	}
	public void framPaint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.green); // 设置字体显示属性
		showGameData(g);
		
		
		judgeGame(g);
		g.setColor(c);

		for (int i = 0; i < theRiver.size(); i++) { // 画出河流
			River r = theRiver.get(i);
			r.draw(g);
		}

		for (int i = 0; i < theRiver.size(); i++) {
			River r = theRiver.get(i);
			homeTank.collideRiver(r);

			r.draw(g);
		}

		home.draw(g); // 画出home
		homeTank.draw(g); // 画出自己家的坦克
		homeTank.eat(blood);// 加血--生命值
		
		// 画出每一辆敌方坦克
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i); // 获得键值对的键

			for (int j = 0; j < homeWall.size(); j++) {
				BrickWall cw = homeWall.get(j);
				t.collideWithWall(cw); // 每一个坦克撞到家里的墙时
				cw.draw(g);
			}
			for (int j = 0; j < otherWall.size(); j++) { // 每一个坦克撞到家以外的墙
				BrickWall cw = otherWall.get(j);
				t.collideWithWall(cw);
				cw.draw(g);
			}
			for (int j = 0; j < metalWall.size(); j++) { // 每一个坦克撞到金属墙
				MetalWall mw = metalWall.get(j);
				t.collideWithWall(mw);
				mw.draw(g);
			}
			for (int j = 0; j < theRiver.size(); j++) {
				River r = theRiver.get(j); // 每一个坦克撞到河流时
				t.collideRiver(r);
				r.draw(g);
				// t.draw(g);
			}

			t.collideWithTanks(tanks); // 撞到自己的人
			t.collideHome(home);

			t.draw(g);
		}

		for (int i = 0; i < bullets.size(); i++) { // 对每一个子弹
			Bullets m = bullets.get(i);
			m.hitTanks(tanks); // 每一个子弹打到坦克上
			m.hitTank(homeTank); // 每一个子弹打到自己家的坦克上时
			m.hitHome(); // 每一个子弹打到家里时

			for (int j = 0; j < metalWall.size(); j++) { // 每一个子弹打到金属墙上
				MetalWall mw = metalWall.get(j);
				m.hitWall(mw);
			}

			for (int j = 0; j < otherWall.size(); j++) {// 每一个子弹打到其他墙上
				BrickWall w = otherWall.get(j);
				m.hitWall(w);
			}

			for (int j = 0; j < homeWall.size(); j++) {// 每一个子弹打到家的墙上
				BrickWall cw = homeWall.get(j);
				m.hitWall(cw);
			}
			m.draw(g); // 画出效果图
		}
		
		
		

		blood.draw(g);// 画出加血包

		for (int i = 0; i < trees.size(); i++) { // 画出trees
			Tree tr = trees.get(i);
			tr.draw(g);
		}

		for (int i = 0; i < bombTanks.size(); i++) { // 画出爆炸效果
			BombTank bt = bombTanks.get(i);
			bt.draw(g);
		}

		for (int i = 0; i < otherWall.size(); i++) { // 画出otherWall
			BrickWall cw = otherWall.get(i);
			cw.draw(g);
		}

		for (int i = 0; i < metalWall.size(); i++) { // 画出metalWall
			MetalWall mw = metalWall.get(i);
			mw.draw(g);
		}

		homeTank.collideWithTanks(tanks);
		homeTank.collideHome(home);

		for (int i = 0; i < metalWall.size(); i++) {// 撞到金属墙
			MetalWall w = metalWall.get(i);
			homeTank.collideWithWall(w);
			w.draw(g);
		}

		for (int i = 0; i < otherWall.size(); i++) {
			BrickWall cw = otherWall.get(i);
			homeTank.collideWithWall(cw);
			cw.draw(g);
		}

		for (int i = 0; i < homeWall.size(); i++) { // 家里的坦克撞到自己家
			BrickWall w = homeWall.get(i);
			homeTank.collideWithWall(w);
			w.draw(g);
		}
		if(level==4) {
			if (tanks.size() == 0 && home.isLive() && homeTank.isLive()) {
				Font f = g.getFont();
				g.setColor(Color.green);
				g.setFont(new Font("TimesRoman", Font.BOLD, 60));
				g.drawString("你赢了！ ", 310, 280);
				g.setFont(f);
		//赢了后不能动
				
		}
		}
	
	}
	
	// 创建菜单及菜单选项
		void initMenu(){
			jmb = new MenuBar();
			jm1 = new Menu("游戏");
			jm2 = new Menu("暂停/继续");
			jm3 = new Menu("帮助");
			jm4 = new Menu("游戏级别");
			jm5 = new Menu("分数 排行榜");
			jm1.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体
			jm2.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体
			jm3.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体
			jm4.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体
			jm5.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体

			jmi1 = new MenuItem("开始新游戏");
			jmi2 = new MenuItem("退出");
			jmi3 = new MenuItem("暂停");
			jmi4 = new MenuItem("继续");
			jmi5 = new MenuItem("游戏说明");
			jmi6 = new MenuItem("级别1");
			jmi7 = new MenuItem("级别2");
			jmi8 = new MenuItem("级别3");
			jmi9 = new MenuItem("级别4");
			jmi10 = new MenuItem("分数 排行榜");
			jmi1.setFont(new Font("TimesRoman", Font.BOLD, 15));
			jmi2.setFont(new Font("TimesRoman", Font.BOLD, 15));
			jmi3.setFont(new Font("TimesRoman", Font.BOLD, 15));
			jmi4.setFont(new Font("TimesRoman", Font.BOLD, 15));
			jmi5.setFont(new Font("TimesRoman", Font.BOLD, 15));

			jm1.add(jmi1);
			jm1.add(jmi2);
			jm2.add(jmi3);
			jm2.add(jmi4);
			jm3.add(jmi5);
			jm4.add(jmi6);
			jm4.add(jmi7);
			jm4.add(jmi8);
			jm4.add(jmi9);
			jm5.add(jmi10);
			

			jmb.add(jm1);
			jmb.add(jm2);

			jmb.add(jm4);
			jmb.add(jm3);
			jmb.add(jm5);

			jmi1.addActionListener(this);
			jmi1.setActionCommand("NewGame");
			jmi2.addActionListener(this);
			jmi2.setActionCommand("Exit");
			jmi3.addActionListener(this);
			jmi3.setActionCommand("Stop");
			jmi4.addActionListener(this);
			jmi4.setActionCommand("Continue");
			jmi5.addActionListener(this);
			jmi5.setActionCommand("help");
			jmi6.addActionListener(this);
			jmi6.setActionCommand("level1");
			jmi7.addActionListener(this);
			jmi7.setActionCommand("level2");
			jmi8.addActionListener(this);
			jmi8.setActionCommand("level3");
			jmi9.addActionListener(this);
			jmi9.setActionCommand("level4");
			jmi10.addActionListener(this);
			jmi10.setActionCommand("分数排行榜");
			this.setMenuBar(jmb);// 菜单Bar放到JFrame上
		}

		void setMapByLevel(int level){
			mp.homewall();
			mp.brickwall(level);
			mp.metalwall(level);
			mp.tree(level);
			mp.river(level);
		}
		
		void initTankLoc() {
			for (int i = 0; i <Tank.count; i++) { // 初始化12辆坦克
				if (i < 8) // 设置坦克出现的位置
					tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,level,false));
				else if (i < 9) // 设置坦克出现的位置
					tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,level,true));
				else if (i < 14)
					tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.L, this,level,false));
				else if (i < 15)
					tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.L, this,level,true));
				else if (i < 19)
					tanks.add(new Tank(10, 50 * (i - 12), false, Direction.R, this,level,false));
				else 
					tanks.add(new Tank(10, 50 * (i - 12), false, Direction.R, this,level,true));
			}
		}
	public GameFrame(String account) {
		this.account=account;
		 printable = true;
		 //计时器
		Timers.m=timers.minute;
		Timers.s=timers.second;
		init();		//初始化地图数据、玩家类
		initMenu();		// 创建菜单及菜单选项
		setMapByLevel(this.level);		//根据游戏level创建地图
			

		initTankLoc();	//初始化坦克位置

		this.setSize(Fram_width, Fram_length); // 设置界面大小
		setLocationRelativeTo(null);// 让窗体居中
		this.setTitle("坦克大战——(重新开始：R键  开火：F键)                 ");
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.addKeyListener(new KeyMonitor());// 键盘监听
		new Thread(new PaintThread()).start(); // 线程启动
		
		//声音
		if(this.level==1) {
		  StartAudio startAudio = new StartAudio("audio/7301.wav");
		  startAudio.start();
		}
		
	}

//	public static void main(String[] args) {
//		new GameFrame(); // 实例化
//		printable=true;
//	}
	
	private class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			while (printable) {
				repaint();
				storeScore();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) { // 监听键盘释放
			homeTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) { // 监听键盘按下
			homeTank.keyPressed(e);
		}
	}
	
	
	//更新当前玩家的分数
	void storeScore() {
		int pscore=player.getScore();
		if(pscore<this.score) {
			player.setScore(this.score);
			for(Player play:playlistList) {
				if((player.account).equals(play.account)) {
					play=player;
					playerInRd.sortlist(playlistList);//对列表List排序
					break;
				}
			}
			playerInRd.writeMessbylist(playlistList);
		}
	}
	
	public void actionPerformed(ActionEvent e) {

		String cmd=e.getActionCommand();
		
		if (cmd.equals("NewGame")) {
			printable = false;
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				storeScore();		//更新分数
				this.score=0;
				printable = true;
				this.dispose();
				new GameFrame(account);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // 线程启动
			}

		} else if (cmd.endsWith("Stop")) {// 暂停
			printable = false;
			Timers.minutestop=Timers.mm;
			Timers.secondstop=Timers.ss;
		} else if (cmd.equals("Continue")) {// 继续
			if (!printable) {
				printable = true;
				Timers timers=new Timers();
				Timers.m=timers.minute-Timers.minutestop;
				Timers.s=timers.second-Timers.secondstop;
				new Thread(new PaintThread()).start(); // 线程启动
			}
			// System.out.println("继续");
		} else if (cmd.equals("Exit")) {// 退出
			printable = false;
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要退出吗", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.out.println("退出");
				storeScore();
				System.exit(0);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // 线程启动
			}
		} else if (cmd.equals("help")) {
			printable = false;
			JOptionPane.showMessageDialog(null, "用→ ← ↑ ↓控制方向，F键盘发射，R重新开始！", "提示！", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			printable = true;
			new Thread(new PaintThread()).start(); // 线程启动
		} else if (cmd.equals("level1")) {
			level=1;
			storeScore();
			this.score=0;
			Tank.speedX = 6;
			Tank.speedY = 6;
			Bullets.speedX = 10;
			Bullets.speedY = 10;
			this.score=0;	//选择难度级别1，则分数为0
			this.dispose();
			new GameFrame(account);
		} else if (cmd.equals("level2")) {
			level=2;
			storeScore();
			this.score=0;
			Tank.speedX = 10;
			Tank.speedY = 10;
			Bullets.speedX = 12;
			Bullets.speedY = 12;
			this.dispose();
			new GameFrame(account);
			
		} else if (cmd.equals("level3")) {
			level=3;
			storeScore();
			this.score=0;
			
			Tank.speedX = 14;
			Tank.speedY = 14;
			Bullets.speedX = 16;
			Bullets.speedY = 16;
			this.dispose();
			new GameFrame(account);
		} else if (cmd.equals("level4")) {
			level=4;
			storeScore();
			this.score=0;
			Tank.speedX = 16;
			Tank.speedY = 16;
			Bullets.speedX = 18;
			Bullets.speedY = 18;
			this.dispose();
			new GameFrame(account);
		}else if(cmd.equals("分数排行榜")) {
			new ShowScore(playlistList);
			System.out.print("dd");
		}
	}
}
