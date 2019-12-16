package start;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFrame;

import config.Config;
import main.cover;


public class Welcome extends JFrame{
	  public Graphics g;
	  Image screenImage = null;
	  int count=1;
	  String[] str= {"̹","��","��","ս"};
	  Login lg;
	  
	  int width=800,height=470;
	  cover cvCover=new cover(0, 0, this);

	// ����һ����д�ķ���,����repaint()�����Զ�����
		public void paint(Graphics g) {
			screenImage = this.createImage(width, height);
			Graphics gps = screenImage.getGraphics();
			Color c = gps.getColor();
			gps.setColor(Color.GRAY);
			gps.fillRect(0, 0, width, height);
			gps.setColor(c);
			framPaint(gps);

			g.drawImage(screenImage, 0, 0, null);
		}

	  private void framPaint(Graphics g) {	
		  
			Color c = g.getColor();
			g.setColor(new Color(255,128,64)); // ����������ʾ����
			Font f1 = g.getFont();
			g.setFont(new Font("TimesRoman", Font.BOLD+ Font.ITALIC, 80));
			cvCover.draw(g);
			int i;
			for(i=0;i<count&&i<str.length;++i) {
				g.drawString(str[i], 140+150*i, 440 );
			}	
			g.setFont(f1);
			if(count>Config.PAUSE_COUNT) {
				dispose();
				lg = new Login();
				lg.init();
				
			}
			
	}

	  public Welcome() {
		  this.setSize(width,height);
		  this.setResizable(false);
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	      this.setLocation(400,150);  
	      
	     
	      this.setLayout(new FlowLayout());
	      this.setUndecorated(true);
	      
		  
		  this.setVisible(true);
		  new Thread(new PaintThread()).start(); // �߳�����
	}
	  
	public static void main(String[] args)  
	  {  
	      Welcome wl=new Welcome();  
	  }  
	  

	  
		private class PaintThread implements Runnable {
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					repaint();
					try {
						Thread.sleep(500);
						count++;
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

}