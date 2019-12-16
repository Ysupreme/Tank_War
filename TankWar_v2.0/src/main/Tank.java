package main;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.sun.glass.ui.Window.Level;



/**
 * 坦克类（适用敌方坦克和玩家坦克）
 */

public class Tank {
	public static int speedX = 6, speedY = 6; // 静态全局变量速度
	public static int count = 12;

	public static final int width = 34, length = 35; // 坦克的全局大小，具有不可改变性
	private Direction direction = Direction.STOP; // 初始化状态为静止
	private Direction Kdirection = Direction.U; // 初始化方向为向上
	GameFrame tc;
	
	private static int fire = 1;
	private boolean good;
	private boolean boss;
	
	private int x, y;
	private int oldX, oldY;
	private boolean live = true; // 初始化为活着
    int life=200;
    public static int level;
    public  static int score;

	private static Random r = new Random();
	private int step = r.nextInt(10) + 5; // 产生一个随机数,随机模拟坦克的移动路径

	private boolean bL = false, bU = false, bR = false, bD = false;//用于监听键盘事件

	private static Toolkit tk = Toolkit.getDefaultToolkit();// 控制面板
	private static Image[] tankImags = null; // 存储全局静态
	
	static {
		tankImags = new Image[] { 
				tk.getImage(BombTank.class.getResource("..//Images/tankD.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/tankU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/tankL.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/tankR.gif")), 
				tk.getImage(BombTank.class.getResource("..//Images/tankLU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/tankRU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/tankLD.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/tankRD.gif")), 
				tk.getImage(BombTank.class.getResource("..//Images/HtankD.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/HtankU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/HtankL.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/HtankR.gif")), 
				tk.getImage(BombTank.class.getResource("..//Images/HtankLU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/HtankRU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/HtankLD.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/HtankRD.gif")), 
				tk.getImage(BombTank.class.getResource("..//Images/BtankD.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/BtankU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/BtankL.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/BtankR.gif")), 
				tk.getImage(BombTank.class.getResource("..//Images/BtankLU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/BtankRU.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/BtankLD.gif")),
				tk.getImage(BombTank.class.getResource("..//Images/BtankRD.gif")), };

	}

	public Tank(int x, int y, boolean good) {// Tank的构造函数1
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}

	public Tank(int x, int y, boolean good, Direction dir, GameFrame tc, int level, boolean boss) {// Tank的构造函数2
		this(x, y, good);
		this.direction = dir;
		this.tc = tc;
		this.level=level;
		this.boss = boss;
		setBloodNum(level,boss);
	}
	
	void setBloodNum(int level, boolean boss) {
		if(good) {
			setLife(200);
		}
		else if(!boss){	//设置血量
			switch(level) {
			case 1:
				setLife(100);
				break;
			case 2:
			case 3:
			case 4:
				setLife(200);
				break;
		    }
		}else {	//boss血量1000
			setLife(1000);
		}
		if(level==1) {	//设置数量
			Tank.count=10;
		}
		else if(level==2) {
			Tank.count=13;
			}
		else if(level==3) {
			Tank.count=17;
		}
		else if(level==4) {
			Tank.count=20;
		}
	}

	public void draw(Graphics g) {
		if (!live) {
			if (!good) {
				tc.tanks.remove(this); // 删除无效的
			}
			return;
		}

		if (good)
			new DrawBloodbBar().draw(g); // 玩家坦克的血量条

		if(good) {
			switch (Kdirection) {
			// 根据方向选择坦克的图片
			case D:
				g.drawImage(tankImags[8], x, y, null);
				break;
			case U:
				g.drawImage(tankImags[9], x, y,null);
				break;
			case L:
				g.drawImage(tankImags[10], x, y, null);
				break;
			case R:
				g.drawImage(tankImags[11], x, y, null);
				break;
			case LU:
				g.drawImage(tankImags[12], x, y ,null);
				break;
			case RU:
				g.drawImage(tankImags[13], x, y, null);
				break;	
			case LD:
				g.drawImage(tankImags[14], x, y, null);
				break;	
			case RD:
				g.drawImage(tankImags[15], x, y, null);
				break;
			}
		}else if(boss) {
			switch (Kdirection) {
			// 根据方向选择坦克的图片
			case D:
				g.drawImage(tankImags[16], x, y, null);
				break;
			case U:
				g.drawImage(tankImags[17], x, y, null);
				break;
			case L:
				g.drawImage(tankImags[18], x, y, null);
				break;
			case R:
				g.drawImage(tankImags[19], x, y, null);
				break;	
			case LU:
				g.drawImage(tankImags[20], x, y, null);
				break;	
			case RU:
				g.drawImage(tankImags[21], x, y, null);
				break;	
			case LD:
				g.drawImage(tankImags[22], x, y, null);
				break;
			case RD:
				g.drawImage(tankImags[23], x, y, null);
				break;
			}
		}else {
			switch (Kdirection) {
			// 根据方向选择坦克的图片
			case D:
				g.drawImage(tankImags[0], x, y, null);
				break;
			case U:
				g.drawImage(tankImags[1], x, y, null);
				break;
			case L:
				g.drawImage(tankImags[2], x, y, null);
				break;
			case R:
				g.drawImage(tankImags[3], x, y, null);
				break;	
			case LU:
				g.drawImage(tankImags[4], x, y, null);
				break;
			case RU:
				g.drawImage(tankImags[5], x, y, null);
				break;
			case LD:
				g.drawImage(tankImags[6], x, y, null);
				break;
			case RD:
				g.drawImage(tankImags[7], x, y,null);
				break;
			}
		}
		

		move(); // 调用move函数
	}

	void move() {

		this.oldX = x;
		this.oldY = y;

		switch (direction) { // 选择移动方向
		case L:
			x -= speedX;
			break;
		case U:
			y -= speedY;
			break;
		case R:
			x += speedX;
			break;
		case D:
			y += speedY;
			break;
		case LU:
			x -= speedX;
			y -= speedY;
			break;
		case RU:
			y -= speedY;
			x += speedX;
			break;
		case LD:
			x -= speedX;
			y += speedY;
			break;
		case RD:
			x += speedX;
			y += speedY;
			break;
		case STOP:
			break;
		}

		if (this.direction != Direction.STOP) {
			this.Kdirection = this.direction;
		}

		if (x < 0)
			x = 0;
		if (y < 50) // 防止走出规定区域
			y = 50;
		if (x + Tank.width > GameFrame.Fram_width) // 超过区域则恢复到边界
			x = GameFrame.Fram_width - Tank.width;
		if (y + Tank.length > GameFrame.Fram_length)
			y = GameFrame.Fram_length - Tank.length;

		if (!good) {
			Direction[] directons = Direction.values();
			if (step == 0) {
				step = r.nextInt(12) + 3; // 产生随机路径
				int rn = r.nextInt(directons.length);
				direction = directons[rn]; // 产生随机方向
			}
			step--;

			if (r.nextInt(40) > 38)// 产生随机数，控制敌人开火
				this.fire();
		}
	}

	private void changToOldDir() {
		x = oldX;
		y = oldY;
	}

	public void keyPressed(KeyEvent e) { // 接受键盘事件
		int key = e.getKeyCode();
		switch (key) {
		
		case KeyEvent.VK_R: // 当按下R时，重新开始游戏
			
			new GameFrame(GameFrame.account); // 重新创建面板
			tc.printable = true;
			tc.dispose();
			break;
		case KeyEvent.VK_RIGHT: // 监听向右键
			bR = true;
			break;

		case KeyEvent.VK_LEFT:// 监听向左键
			bL = true;
			break;

		case KeyEvent.VK_UP: // 监听向上键
			bU = true;
			break;

		case KeyEvent.VK_DOWN:// 监听向下键
			bD = true;
			break;
		
		}
		decideDirection();// 调用函数确定移动方向
	}

	void decideDirection() {
		if (!bL && !bU && bR && !bD) // 向右移动
			direction = Direction.R;

		else if (bL && !bU && !bR && !bD) // 向左移动
			direction = Direction.L;

		else if (!bL && bU && !bR && !bD) // 向上移动
			direction = Direction.U;

		else if (!bL && !bU && !bR && bD) // 向下移动
			direction = Direction.D;
		
		else if (bU && !bD & bL && !bR) // 向左上移动
			direction = Direction.LU;
		
		else if (bU && !bD & !bL && bR) // 向右上移动
			direction = Direction.RU;
		
		else if (!bU && bD & bL && !bR) // 向左下移动
			direction = Direction.LD;
		
		else if (!bU && bD & !bL && bR) // 向右下移动
			direction = Direction.RD;
		
		else if (!bL && !bU && !bR && !bD)
			direction = Direction.STOP; // 没有按键，就保持不动
	}

	TimerTask task = new TimerTask() {		
		public void run() {
        Tank.fire = 1;
		}
	};
	
	public void keyReleased(KeyEvent e) { // 键盘释放监听
		int key = e.getKeyCode();
		switch (key) {

		case KeyEvent.VK_F:
			if(this.level == 4) {
				if(fire == 1) {
				fire();
			    fire = 0;
			    new Audio(2);
			    Timer timer = new Timer(); 
			    timer.schedule(task, 0,1000);
			    //timer.cancel();
			    break;
			    }
			}else{
				fire();
				new Audio(2);
				break;
			}

		case KeyEvent.VK_RIGHT:
			bR = false;
			new Audio(1);
			break;

		case KeyEvent.VK_LEFT:
			bL = false;
			new Audio(1);
			break;

		case KeyEvent.VK_UP:
			bU = false;
			new Audio(1);
			break;

		case KeyEvent.VK_DOWN:
			bD = false;
			new Audio(1);
			break;

		}
		decideDirection(); // 释放键盘后确定移动方向
	}

	public Bullets fire() { // 开火方法
		if (!live)
			return null;
		int x = this.x + Tank.width / 2 - Bullets.width / 2; // 开火位置
		int y = this.y + Tank.length / 2 - Bullets.length / 2;
		Bullets m = new Bullets(x, y + 2, good, Kdirection, this.tc); // 没有给定方向时，向原来的方向发火
		tc.bullets.add(m);
		return m;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isGood() {
		return good;
	}

	public boolean collideWithWall(BrickWall w) { // 碰撞到普通墙时
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir(); // 转换到原来的方向上去
			return true;
		}
		return false;
	}

	public boolean collideWithWall(MetalWall w) { // 撞到金属墙
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideRiver(River r) { // 撞到河流的时候
		if (this.live && this.getRect().intersects(r.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideHome(Home h) { // 撞到家的时候
		if (this.live && this.getRect().intersects(h.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithTanks(java.util.List<Tank> tanks) {// 撞到坦克时
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					this.changToOldDir();
					t.changToOldDir();
					return true;
				}
			}
		}
		return false;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		
		this.life = life;
	}

	private class DrawBloodbBar {
		public void draw(Graphics g) {
			Color c = g.getColor();
			g.setColor(Color.RED);
			g.drawRect(375, 585, width, 10);// 显示玩家坦克的血量条
			int w = width * life / 200;
			g.fillRect(375, 585, w, 10);// 显示玩家坦克的血量条
			g.setColor(c);
		}
	}

	public boolean eat(Blood b) {
		if (this.live && b.isLive() && this.getRect().intersects(b.getRect())) {
			if (this.life <= 100)
				this.life = this.life + 100; // 每吃一个，增加100生命点
			else
				this.life = 200;
			b.setLive(false);
			new Audio(4);
			return true;
			
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
