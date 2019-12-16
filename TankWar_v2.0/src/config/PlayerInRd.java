package config;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
 
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import player.Player;
 
/**
 * ʹ��DOM����xml�ĵ�
 * @author ylg
 *
 */
//DOM��ʽ����
public class PlayerInRd {
	public File file = new File("src\\config\\PlayerInfo.xml");
	public SAXReader reader=null;
	public Document dcDocument=null;
	
	 //���ڱ������������Ϣ��List����
     public List<Player> playList = new ArrayList<Player>();
	 
	
	
     public void init() {
		if(reader==null) {
			reader=new SAXReader();
		}
		if(dcDocument==null) {
			try {
				dcDocument=reader.read(file);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
	}
	public  List<Player> getNode() {
		init();
		Element root = dcDocument.getRootElement();
		System.out.println("root elem:"+root.getName());
		List<Element> list = root.elements();
		
		int i=0;
		for(Element elem:list) {
//			Element nameele = elem.element("username");
			String account=elem.elementText("account");
			String username=elem.elementText("username");
			String passwd=elem.elementText("passwd");
			int rank=Integer.parseInt(elem.elementText("rank"));
			int score=Integer.parseInt(elem.elementText("score"));
			
			//����һ��player����
			Player player = new Player(username,account,passwd,rank,score);
			playList.add(player);
		}
		return playList;
	}
	

	
	public  void writeMess(Player player) {
		playList.add(player);
		
		//��playlistд��playinfo.xml
		
		Document doc 
        = DocumentHelper.createDocument();

    /*
     * 2
     * Document�ṩ����Ӹ�Ԫ�صķ���:
     * Element addElement(String name)
     * ��ǰ�ĵ������ָ�����ֵĸ�Ԫ�أ�����
     * ��Element�ͱ�ʾ�����Ԫ�ء�
     * ��Ҫע�⣬�÷���ֻ�ܵ���һ�Σ���Ϊһ��
     * �ĵ�ֻ����һ����Ԫ�ء�
     */
    Element root = doc.addElement("list");

    //3
    for(Player player2 : playList){
        /*
         * ElementҲ�ṩ��׷����Ԫ�صķ���:
         * Element addElement(String name)
         * ���ô���û�����ƣ�Ԫ�ؿ��԰�������
         * ��Ԫ�ء�
         */
        Element empEle = root.addElement("player");

        //���username��Ϣ
        Element nameEle = empEle.addElement("username");
        nameEle.addText(player2.getUsername());

        //���account��Ϣ
        Element accountEle = empEle.addElement("account");
        accountEle.addText(player2.getAccount());
        
        //���passwd��Ϣ
        Element passwdEle = empEle.addElement("passwd");
        passwdEle.addText(player2.getPasswd());
        
        //���rank��Ϣ
        Element rankEle = empEle.addElement("rank");
        rankEle.addText(""+player2.getRank());
        
        //���score��Ϣ
        Element scoreEle = empEle.addElement("score");
        scoreEle.addText(""+player2.getScore());
        /*
         * ��ǰԪ�������ָ�������Լ���Ӧֵ������
         */
//        empEle.addAttribute("id", player2.getRank()+"");

    }
    try{
        //4
        XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
        FileOutputStream fos
            = new FileOutputStream(file);
        writer.setOutputStream(fos);

        //5
        writer.write(doc);
        System.out.println("д�����!");
        writer.close();
    }catch(Exception e){
        e.printStackTrace();
    }
		
	}
	
	//��List<Player> playListд��xml�ļ�����
	public  void writeMessbylist(List<Player> playList) {
		
		
		//��playlistд��playinfo.xml
		
		Document doc 
        = DocumentHelper.createDocument();

    /*
     * 2
     * Document�ṩ����Ӹ�Ԫ�صķ���:
     * Element addElement(String name)
     * ��ǰ�ĵ������ָ�����ֵĸ�Ԫ�أ�����
     * ��Element�ͱ�ʾ�����Ԫ�ء�
     * ��Ҫע�⣬�÷���ֻ�ܵ���һ�Σ���Ϊһ��
     * �ĵ�ֻ����һ����Ԫ�ء�
     */
    Element root = doc.addElement("list");

    //3
    for(Player player2 : playList){
        /*
         * ElementҲ�ṩ��׷����Ԫ�صķ���:
         * Element addElement(String name)
         * ���ô���û�����ƣ�Ԫ�ؿ��԰�������
         * ��Ԫ�ء�
         */
        Element empEle = root.addElement("player");

        //���username��Ϣ
        Element nameEle = empEle.addElement("username");
        nameEle.addText(player2.getUsername());

        //���account��Ϣ
        Element accountEle = empEle.addElement("account");
        accountEle.addText(player2.getAccount());
        
        //���passwd��Ϣ
        Element passwdEle = empEle.addElement("passwd");
        passwdEle.addText(player2.getPasswd());
        
        //���rank��Ϣ
        Element rankEle = empEle.addElement("rank");
        rankEle.addText(""+player2.getRank());
        
        //���score��Ϣ
        Element scoreEle = empEle.addElement("score");
        scoreEle.addText(""+player2.getScore());
        /*
         * ��ǰԪ�������ָ�������Լ���Ӧֵ������
         */
//        empEle.addAttribute("id", player2.getRank()+"");

    }
    try{
        //4
        XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
        FileOutputStream fos
            = new FileOutputStream(file);
        writer.setOutputStream(fos);

        //5
        writer.write(doc);
        System.out.println("д�����!");
        writer.close();
    }catch(Exception e){
        e.printStackTrace();
    }
		
	}
	
//	public  static void main(String[]  args) {
//		PlayerInRd playerInRd = new PlayerInRd();
//		playerInRd.getNode();
//		
//	}
	
	//���list���players��������
	public static void showMessage(List<Player> players) {
		for(Player pl:players) {
			System.out.println(pl.account+",  "+pl.passwd+",  "+pl.rank);
		} 
	}
	
	//�޸Ľڵ�
	 public void modifyNode() {
	    	
	   }
	 
	 public void sortlist(List<Player> playlList) {
		 	Collections.sort(playList);
	 }
	   
	 
	 
	
}