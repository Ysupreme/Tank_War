package start;

import javax.swing.JFrame;

/*
 *com.jlong.myDesign.java
 */

import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import listener.Login_reg_ls;
import main.GameFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Login extends JFrame{
	

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    Login_reg_ls lobin_ls;
    GameFrame tcFrame;
    //设置一个Panel容器面板和Label标签存放背景图片
    private JPanel contentPanel = new JPanel();
    private JLabel label, label2;

    //设置按钮组件
    private JButton 
        login = new JButton("登录"),
        registered = new JButton("注册");

    //设置文本框组件
    private JTextField 
        account = new JTextField(),
        password = new JTextField();

    //设置复选框组件
    private JCheckBox 
        rememberAdmin = new JCheckBox("记住账号"),
        rememberPassword = new JCheckBox("记住密码");

   
    //初始化实例
    void initls() {
    	if(lobin_ls==null) {
    		lobin_ls = new Login_reg_ls(account,password,this);
    	}
    }
   
    
    public Login(){
    	initls();
        //初始化各组件
    	account.setText("账号/邮箱/手机号");
        password.setText("密码");
        //实例化图片
        ImageIcon image1 = new ImageIcon("src\\Images\\bg6.jpg");

        JLabel backLabel = new JLabel();
        backLabel.setIcon(image1);

        label=new JLabel(image1);
        //设置标签大小与位置
        label.setBounds(0, 0,500,350);
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        //将内容面板设置为透明，就能够看见添加在LayeredPane上的背景。
        ((JPanel)this.getContentPane()).setOpaque(false);

        /*
         * 添加组件到contentPanel容器中
         * 布局方式为自由布局。
         */
        contentPanel.setLayout(null);
        add(account);
        add(password);
        add(login);
        add(rememberAdmin);
        add(rememberPassword);
        add(registered);

        /*
         * 组件绝对位置
         */
        account.setBounds(95, 130, 300, 25);
        password.setBounds(95, 154, 300, 25);
        rememberAdmin.setBounds(145, 180, 100, 14);
        rememberPassword.setBounds(235, 180, 100, 14);
        registered.setBounds(125, 225, 90, 20);
        login.setBounds(275, 225, 90, 20);



        /*
         * 组件透明化
         */
        account.setOpaque(false);
        password.setOpaque(false);
        contentPanel.setOpaque(false);
        rememberAdmin.setOpaque(false);
        rememberPassword.setOpaque(false);
        getContentPane().add(contentPanel);

        /*
         * 组件边框颜色
         */
        textSet(account);
        textSet(password);
        //rememberAdmin.setBorder(new LineBorder(null, Color.OPAQUE));

        /*
         * 监听事件
         */
        account.addMouseListener(lobin_ls);
        password.addMouseListener(lobin_ls);
        login.addActionListener(lobin_ls);
        registered.addActionListener(lobin_ls);
        
        account.addCaretListener(new CaretListener(){
            public void caretUpdate(CaretEvent e){

            }
        });

       

    }

    /*
     * JTextField文本框设置方法.
     */
    public void textSet(JTextField field) {  
        field.setBackground(new Color(255, 255, 255));  
        field.setFont(new Font("宋体", Font.PLAIN, 16));
        field.setPreferredSize(new Dimension(150, 28));  
        MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(192, 192,  
        192));  
        field.setBorder(border);  
    }  
    
    public void init() {
    	
        setTitle("坦克大战——登录");
        //窗口退出行为
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口大小不可变
        setResizable(false);
        //设置窗口打开居中
        setLocation(500,200);
        //窗口大小
        setSize(500, 350);
        //展示窗口
        setVisible(true);
    }

    public static void main(String[] args){
       Login lg=new Login();
       lg.init();
    }

}

