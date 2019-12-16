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
 * ̹�˴�ս������
 */

public class GameFrame extends JFrame implements ActionListener {
	Map mp =null;

	int[] speedx= {};
	private static final long serialVersionUID = 5972735870004738773L;

	public static final int Fram_width = 800; // ��̬ȫ�ִ��ڴ�С
	public static final int Fram_length = 600;
	public static boolean printable = true; // ��¼��ͣ״̬����ʱ�̲߳�ˢ�½���
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

	Tank homeTank = new Tank(300, 560, true, Direction.STOP, this,1,false);// ʵ����̹��
	Blood blood = new Blood(); // ʵ����Ѫ��
	Home home = new Home(373, 545, this);// ʵ����home
	Font labelFont=new Font("TimesRoman", Font.BOLD, 20);
	Font dataFont = new Font("TimesRoman", Font.ITALIC, 30);

	// ���¼��ϱ����ڹ��췽���н����˳�ʼ��
	List<River> theRiver = new ArrayList<River>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<BombTank> bombTanks = new ArrayList<BombTank>();
	List<Bullets> bullets = new ArrayList<Bullets>();
	List<Tree> trees = new ArrayList<Tree>();
	List<BrickWall> homeWall = new ArrayList<BrickWall>(); // ʵ������������
	List<BrickWall> otherWall = new ArrayList<BrickWall>();
	List<MetalWall> metalWall = new ArrayList<MetalWall>();

	////��ʼ����ͼ���ݡ������
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
	// ����һ����д�ķ���,����repaint()�����Զ�����
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
		g.drawString("�ؿ�: ", 680, 70 );
		g.drawString("�����ڻ��ез�̹��: ", 200, 70 );
		g.drawString("�ҷ�ʣ������ֵ: ", 450, 70);
		g.drawString("��Ϸʱ��:", 20, 70);
		g.drawString("��Ϸ����:    ", 20, 100);
		
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
		// ������Ӯ�ˣ������ǵз�̹��ȫ�𡢴�Ӫ���ڡ����̹������Ѫ����
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
							//Ӯ�˺��ܶ�
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
				switch(level) {//����������(��Ӫ��������̹����������ʱ�䵽��)
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
		g.setColor(Color.green); // ����������ʾ����
		showGameData(g);
		
		
		judgeGame(g);
		g.setColor(c);

		for (int i = 0; i < theRiver.size(); i++) { // ��������
			River r = theRiver.get(i);
			r.draw(g);
		}

		for (int i = 0; i < theRiver.size(); i++) {
			River r = theRiver.get(i);
			homeTank.collideRiver(r);

			r.draw(g);
		}

		home.draw(g); // ����home
		homeTank.draw(g); // �����Լ��ҵ�̹��
		homeTank.eat(blood);// ��Ѫ--����ֵ
		
		// ����ÿһ���з�̹��
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i); // ��ü�ֵ�Եļ�

			for (int j = 0; j < homeWall.size(); j++) {
				BrickWall cw = homeWall.get(j);
				t.collideWithWall(cw); // ÿһ��̹��ײ�������ǽʱ
				cw.draw(g);
			}
			for (int j = 0; j < otherWall.size(); j++) { // ÿһ��̹��ײ���������ǽ
				BrickWall cw = otherWall.get(j);
				t.collideWithWall(cw);
				cw.draw(g);
			}
			for (int j = 0; j < metalWall.size(); j++) { // ÿһ��̹��ײ������ǽ
				MetalWall mw = metalWall.get(j);
				t.collideWithWall(mw);
				mw.draw(g);
			}
			for (int j = 0; j < theRiver.size(); j++) {
				River r = theRiver.get(j); // ÿһ��̹��ײ������ʱ
				t.collideRiver(r);
				r.draw(g);
				// t.draw(g);
			}

			t.collideWithTanks(tanks); // ײ���Լ�����
			t.collideHome(home);

			t.draw(g);
		}

		for (int i = 0; i < bullets.size(); i++) { // ��ÿһ���ӵ�
			Bullets m = bullets.get(i);
			m.hitTanks(tanks); // ÿһ���ӵ���̹����
			m.hitTank(homeTank); // ÿһ���ӵ����Լ��ҵ�̹����ʱ
			m.hitHome(); // ÿһ���ӵ��򵽼���ʱ

			for (int j = 0; j < metalWall.size(); j++) { // ÿһ���ӵ��򵽽���ǽ��
				MetalWall mw = metalWall.get(j);
				m.hitWall(mw);
			}

			for (int j = 0; j < otherWall.size(); j++) {// ÿһ���ӵ�������ǽ��
				BrickWall w = otherWall.get(j);
				m.hitWall(w);
			}

			for (int j = 0; j < homeWall.size(); j++) {// ÿһ���ӵ��򵽼ҵ�ǽ��
				BrickWall cw = homeWall.get(j);
				m.hitWall(cw);
			}
			m.draw(g); // ����Ч��ͼ
		}
		
		
		

		blood.draw(g);// ������Ѫ��

		for (int i = 0; i < trees.size(); i++) { // ����trees
			Tree tr = trees.get(i);
			tr.draw(g);
		}

		for (int i = 0; i < bombTanks.size(); i++) { // ������ըЧ��
			BombTank bt = bombTanks.get(i);
			bt.draw(g);
		}

		for (int i = 0; i < otherWall.size(); i++) { // ����otherWall
			BrickWall cw = otherWall.get(i);
			cw.draw(g);
		}

		for (int i = 0; i < metalWall.size(); i++) { // ����metalWall
			MetalWall mw = metalWall.get(i);
			mw.draw(g);
		}

		homeTank.collideWithTanks(tanks);
		homeTank.collideHome(home);

		for (int i = 0; i < metalWall.size(); i++) {// ײ������ǽ
			MetalWall w = metalWall.get(i);
			homeTank.collideWithWall(w);
			w.draw(g);
		}

		for (int i = 0; i < otherWall.size(); i++) {
			BrickWall cw = otherWall.get(i);
			homeTank.collideWithWall(cw);
			cw.draw(g);
		}

		for (int i = 0; i < homeWall.size(); i++) { // �����̹��ײ���Լ���
			BrickWall w = homeWall.get(i);
			homeTank.collideWithWall(w);
			w.draw(g);
		}
		if(level==4) {
			if (tanks.size() == 0 && home.isLive() && homeTank.isLive()) {
				Font f = g.getFont();
				g.setColor(Color.green);
				g.setFont(new Font("TimesRoman", Font.BOLD, 60));
				g.drawString("��Ӯ�ˣ� ", 310, 280);
				g.setFont(f);
		//Ӯ�˺��ܶ�
				
		}
		}
	
	}
	
	// �����˵����˵�ѡ��
		void initMenu(){
			jmb = new MenuBar();
			jm1 = new Menu("��Ϸ");
			jm2 = new Menu("��ͣ/����");
			jm3 = new Menu("����");
			jm4 = new Menu("��Ϸ����");
			jm5 = new Menu("���� ���а�");
			jm1.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
			jm2.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
			jm3.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
			jm4.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
			jm5.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������

			jmi1 = new MenuItem("��ʼ����Ϸ");
			jmi2 = new MenuItem("�˳�");
			jmi3 = new MenuItem("��ͣ");
			jmi4 = new MenuItem("����");
			jmi5 = new MenuItem("��Ϸ˵��");
			jmi6 = new MenuItem("����1");
			jmi7 = new MenuItem("����2");
			jmi8 = new MenuItem("����3");
			jmi9 = new MenuItem("����4");
			jmi10 = new MenuItem("���� ���а�");
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
			jmi10.setActionCommand("�������а�");
			this.setMenuBar(jmb);// �˵�Bar�ŵ�JFrame��
		}

		void setMapByLevel(int level){
			mp.homewall();
			mp.brickwall(level);
			mp.metalwall(level);
			mp.tree(level);
			mp.river(level);
		}
		
		void initTankLoc() {
			for (int i = 0; i <Tank.count; i++) { // ��ʼ��12��̹��
				if (i < 8) // ����̹�˳��ֵ�λ��
					tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this,level,false));
				else if (i < 9) // ����̹�˳��ֵ�λ��
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
		 //��ʱ��
		Timers.m=timers.minute;
		Timers.s=timers.second;
		init();		//��ʼ����ͼ���ݡ������
		initMenu();		// �����˵����˵�ѡ��
		setMapByLevel(this.level);		//������Ϸlevel������ͼ
			

		initTankLoc();	//��ʼ��̹��λ��

		this.setSize(Fram_width, Fram_length); // ���ý����С
		setLocationRelativeTo(null);// �ô������
		this.setTitle("̹�˴�ս����(���¿�ʼ��R��  ����F��)                 ");
		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		this.addKeyListener(new KeyMonitor());// ���̼���
		new Thread(new PaintThread()).start(); // �߳�����
		
		//����
		if(this.level==1) {
		  StartAudio startAudio = new StartAudio("audio/7301.wav");
		  startAudio.start();
		}
		
	}

//	public static void main(String[] args) {
//		new GameFrame(); // ʵ����
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

		public void keyReleased(KeyEvent e) { // ���������ͷ�
			homeTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) { // �������̰���
			homeTank.keyPressed(e);
		}
	}
	
	
	//���µ�ǰ��ҵķ���
	void storeScore() {
		int pscore=player.getScore();
		if(pscore<this.score) {
			player.setScore(this.score);
			for(Player play:playlistList) {
				if((player.account).equals(play.account)) {
					play=player;
					playerInRd.sortlist(playlistList);//���б�List����
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
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ��ʼ����Ϸ��", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				storeScore();		//���·���
				this.score=0;
				printable = true;
				this.dispose();
				new GameFrame(account);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // �߳�����
			}

		} else if (cmd.endsWith("Stop")) {// ��ͣ
			printable = false;
			Timers.minutestop=Timers.mm;
			Timers.secondstop=Timers.ss;
		} else if (cmd.equals("Continue")) {// ����
			if (!printable) {
				printable = true;
				Timers timers=new Timers();
				Timers.m=timers.minute-Timers.minutestop;
				Timers.s=timers.second-Timers.secondstop;
				new Thread(new PaintThread()).start(); // �߳�����
			}
			// System.out.println("����");
		} else if (cmd.equals("Exit")) {// �˳�
			printable = false;
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ�˳���", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.out.println("�˳�");
				storeScore();
				System.exit(0);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // �߳�����
			}
		} else if (cmd.equals("help")) {
			printable = false;
			JOptionPane.showMessageDialog(null, "�á� �� �� �����Ʒ���F���̷��䣬R���¿�ʼ��", "��ʾ��", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			printable = true;
			new Thread(new PaintThread()).start(); // �߳�����
		} else if (cmd.equals("level1")) {
			level=1;
			storeScore();
			this.score=0;
			Tank.speedX = 6;
			Tank.speedY = 6;
			Bullets.speedX = 10;
			Bullets.speedY = 10;
			this.score=0;	//ѡ���Ѷȼ���1�������Ϊ0
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
		}else if(cmd.equals("�������а�")) {
			new ShowScore(playlistList);
			System.out.print("dd");
		}
	}
}
