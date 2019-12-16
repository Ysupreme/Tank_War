package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import config.PlayerInRd;
import player.Player;

public class ShowScore extends JFrame
{
   // Ĭ�ϱ��ģ��
   private DefaultTableModel model = null;
   private JTable table = null;

   private JButton renewBtn = null;
   List<Player> playlistList=null;

   public ShowScore(  List<Player> playlistList)
   {
      super("TableDemo");
      String[][] datas = {};
      String[] titles = { "rank", "username","account","score" };
      model = new DefaultTableModel(datas, titles);
      table = new JTable(model);

     this.playlistList = playlistList;
      addMessage();
      
      renewBtn = new JButton("���¼Ʒְ�");
      renewBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e)
         {
        	 
         }
      });
      
      

      add(renewBtn, BorderLayout.NORTH);
      add(new JScrollPane(table));

      setSize(400, 300);
      setLocationRelativeTo(null);
      setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      setVisible(true);

   }
   
   //��xml��Ϣ�ӵ�table��
   void addMessage(){
	   for(int i=1;i<=playlistList.size();i++) {
		   Player play=playlistList.get(i-1);
		   model.addRow(new String[] {""+i, play.getUsername() ,play.getAccount(),""+play.getScore()});
	   }
 	 
   }


   /**
    * �������ַ���,�÷��������ڻ������ַ��������Ժ���
    * 
    * @return
    */
   private String getRandomData()
   {
      String source = "0123456789abcdefghijklmnopqrstuvwxyz";
      int len = source.length();
      Random random = new Random(System.currentTimeMillis());
      return MessageFormat.format("{0}{0}{0}", source.charAt(random.nextInt(len)));
   }
}