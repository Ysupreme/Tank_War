package start;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import listener.Login_reg_ls;

public class Register extends JFrame {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    Login_reg_ls lobin_ls;		//监听器
    
    Font labelFont = new Font("楷体", Font.BOLD, 16);
    Font buttonFont = new Font("楷体", Font.BOLD, 20);
    BevelBorder bb = new BevelBorder(0, Color.gray,Color.white);//按钮特效

    //设置一个Panel容器面板和Label标签存放背景图片
    private JPanel contentPanel = new JPanel();
    private JLabel label, label2;

    //设置按钮组件
    private JButton 
        registered = new JButton("点击注册");
    //设置输入框之前的文字提示
    JLabel 
    username_text = new JLabel("用  户  名"),
	account_text=new JLabel("  账   号  "),
	password_text=new JLabel("  密   码 "),
	comfpassword_text=new JLabel("确 认 密 码");
    //设置文本框组件
    private JTextField
    	username = new JTextField(),
        account = new JTextField(),
        password = new JTextField(),
    	comfpassword = new JTextField();
    /*
     * 我的设计
     */
    
    void initls() {
    	if(lobin_ls==null) {
    		lobin_ls = new Login_reg_ls(account,password,comfpassword,this);
    	}
    }
    public  Register() {
    	initls();
        //初始化各组件
    	username.setText("用户名");
    	account.setText("账号/邮箱/手机号");
        password.setText("密码");
        comfpassword_text.setText("确认密码");
        //实例化图片
        ImageIcon image1 = new ImageIcon("src\\Images\\bg8.png");

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
        
        add(username);
        add(username_text);
        add(account_text);
        add(account);
        add(password_text);
        add(password);
        add(comfpassword_text);
        add(comfpassword);
        add(registered);

        /*
         * 组件绝对位置
         */
        username_text.setBounds(50, 101, 100, 25);
        username.setBounds(165, 101, 200, 25);
        account_text.setBounds(50, 130, 100, 25);
        account.setBounds(165, 130, 200, 25);
        password_text.setBounds(50, 159, 100, 25);
        password.setBounds(165, 159, 200, 25);
        comfpassword_text.setBounds(50, 188, 100, 25);
        comfpassword.setBounds(165,188, 200, 25);
        
        registered.setBounds(190, 235, 90, 30);



        /*
         * 组件透明化
         */
        account.setOpaque(false);
        password.setOpaque(false);
        contentPanel.setOpaque(false);
        comfpassword.setOpaque(false);
        username.setOpaque(false);
        getContentPane().add(contentPanel);

        /*
         * 组件边框颜色
         */
        textSet(account);
        textSet(password);
        textSet(comfpassword);
        textSet(username);
        //rememberAdmin.setBorder(new LineBorder(null, Color.OPAQUE));
        
        //设置文本标签样式
        labelSet(account_text);
        labelSet(password_text);
        labelSet(comfpassword_text);
        labelSet(username_text);
        
      //设置按钮样式
        buttonSet(registered);
        /*
         * 监听事件
         */
        account.addMouseListener(lobin_ls);
        password.addMouseListener(lobin_ls);
        comfpassword.addMouseListener(lobin_ls);
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
    /*
     * JLabel设置方法.
     */
    public void labelSet(JLabel label) {
    	label.setFont(labelFont);
    	label.setPreferredSize(new Dimension(80,25));
//    	 MatteBorder border = new MatteBorder(0, 0, 2, 0, new Color(192, 192,  
//    		        192));  
//    	 label.setBorder(border);
    }
    
    /*
     * JButton设置方法.
     */
    public void buttonSet(JButton button) {
    	button.setFont(buttonFont);
    	button.setBackground(new Color(21,234,228));
    	button.setBorder(bb);
    }
    public void init() {
    	
        setTitle("坦克大战——注册");
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
       Register reg=new Register();
       reg.init();
    }

}
