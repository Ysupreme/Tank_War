package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.org.apache.xml.internal.security.Init;

import config.PlayerInRd;
import main.GameFrame;
import player.Player;
import start.Login;
import start.Register;

public class Login_reg_ls implements ActionListener,MouseListener{
	
	JTextField 
    account, password, confirmpaw;
	GameFrame gfFrame;
	Register register;
	Login  login;
	String acot_input,paw_input,confm_paw_input;
	List<Player> players =null;
	PlayerInRd playerInRd=null;
	
	void Init(){
		if(register==null) {
			register = new Register();
		}
		if(playerInRd==null) {
			playerInRd=new PlayerInRd();
			players=playerInRd.getNode();
		}
	}
	
	public Login_reg_ls() {
		
	}

	public Login_reg_ls(JTextField account,JTextField password,Login login) {
		this.account=account;
		this.password=password;
		this.login=login;
	}
	
	public Login_reg_ls(JTextField account,JTextField password,JTextField confirmpaw,Register reg) {
		this.account=account;
		this.password=password;
		this.confirmpaw=confirmpaw;
		this.register=reg;
	}
	
	//判断登录情况————参数为：账户名称字符串；密码字符串
	int  judgeLogin(String account,String paw,List<Player> players) {
		int flag=0;
		for(Player player:players) {
			if(account.equals(player.account)) {
				flag=1;	//有账号，但密码不正确，则flag设为1
				if(paw.equals(player.passwd)) {
					flag=2;	//有账号，且密码正确，则flag设为2，
					break;
				}
			}
		}
		return flag;
	}
	
	//处理登录结果
	void dealLogin(int flag) {
		switch (flag) {
		case 0:
			JOptionPane.showMessageDialog(login, "无此账号", "登陆结果", 1);
			break;
		case 1:
			JOptionPane.showMessageDialog(login, "请输入正确的密码", "登陆结果", 1);
			break;
		case 2:
			login.dispose();
			new GameFrame(acot_input);	
		default:
			break;
		}
	}
	
	//判断注册情况————确保账号不重复
	int  judgereg(String account,String paw,String comfirpasswd,List<Player> players) {
		int flag=0;
		System.out.println("account="+account+",paw="+paw+",comfirpasswd"+comfirpasswd);
		for(Player player:players) {
			if(account.equals(player.account)) {
				System.out.println("系统已有此账号");
				flag=1;	//系统已存在该账号
				break;
			}
		}
		if(flag!=1) {
			if(comfirpasswd.equals(paw)) {
				flag=2;	//系统无此账号，且两次密码无错误
				System.out.println("符合注册条件");
			}
		}
		return flag;
	}
	
	//处理注册结果
	void dealreg(int flag,String username,String account,String passwd) {
		switch (flag) {
		case 1:
			JOptionPane.showMessageDialog(register, "系统已有此账号，请重新选择账号", "注册结果", 1);
			break;
		case 2:
			if(login!=null) {
				login.dispose();	
			}
			
			register.dispose();
			new GameFrame(acot_input);
			playerInRd.showMessage(players);
			Player player = new Player(username,account,passwd,players.size(),0);
			playerInRd.showMessage(players);
			playerInRd.writeMess(player);
			playerInRd.showMessage(players);
			break;
		default:
			break;
		}
	}
	
	
	


	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if("登录".equals(cmd)) {
			Init();
			//得到输入的账户名称和密码
			acot_input=account.getText();
			paw_input=password.getText();
			
			int flag=judgeLogin(acot_input,paw_input,players);
			dealLogin(flag);
			
		}
		else if("注册".equals(cmd)) {
			Init();
			if(login!=null) {
				login.dispose();
			}
			register.init();
			
		}else if("点击注册".equals(cmd)){
			Init();
			System.out.println("点击了");
			acot_input=account.getText();
			paw_input=password.getText();
			confm_paw_input =confirmpaw.getText();
			
			int flag=judgereg(acot_input, paw_input, confm_paw_input, players);
			dealreg(flag, acot_input, paw_input, confm_paw_input);
		}
			
	}

	public void mouseClicked(MouseEvent e) {
//		int c = e.getButton();
//		String string=account.getText();
//		if(c == MouseEvent.BUTTON1 &&string.equals("账号/邮箱/手机号") &&e.getClickCount()==1) {
//        	account.setText(null);
//            password.setText("密码");
//        }
//        
//		else {
//            password.setText(null);
//            account.setText("账号/邮箱/手机号");
//        }
//        int c = e.getButton();
//		String string=account.getText();
//		if(c == MouseEvent.BUTTON1 &&e.getClickCount()==1) {
//			if(string.equals("�˺�/����/�ֻ���")) {
//				account.setText(null);
//	            password.setText("����");
//			}else {
//				password.setText(null);
//	            account.setText("�˺�/����/�ֻ���");
//			}
//        }
    }
	 
	

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
