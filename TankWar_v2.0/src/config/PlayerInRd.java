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
 * 使用DOM解析xml文档
 * @author ylg
 *
 */
//DOM方式解析
public class PlayerInRd {
	public File file = new File("src\\config\\PlayerInfo.xml");
	public SAXReader reader=null;
	public Document dcDocument=null;
	
	 //用于保存所有玩家信息的List集合
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
			
			//创建一个player对象
			Player player = new Player(username,account,passwd,rank,score);
			playList.add(player);
		}
		return playList;
	}
	

	
	public  void writeMess(Player player) {
		playList.add(player);
		
		//将playlist写到playinfo.xml
		
		Document doc 
        = DocumentHelper.createDocument();

    /*
     * 2
     * Document提供了添加根元素的方法:
     * Element addElement(String name)
     * 向当前文档中添加指定名字的根元素，返回
     * 的Element就表示这个根元素。
     * 需要注意，该方法只能调用一次，因为一个
     * 文档只能有一个根元素。
     */
    Element root = doc.addElement("list");

    //3
    for(Player player2 : playList){
        /*
         * Element也提供了追加子元素的方法:
         * Element addElement(String name)
         * 调用次数没有限制，元素可以包含若干
         * 子元素。
         */
        Element empEle = root.addElement("player");

        //添加username信息
        Element nameEle = empEle.addElement("username");
        nameEle.addText(player2.getUsername());

        //添加account信息
        Element accountEle = empEle.addElement("account");
        accountEle.addText(player2.getAccount());
        
        //添加passwd信息
        Element passwdEle = empEle.addElement("passwd");
        passwdEle.addText(player2.getPasswd());
        
        //添加rank信息
        Element rankEle = empEle.addElement("rank");
        rankEle.addText(""+player2.getRank());
        
        //添加score信息
        Element scoreEle = empEle.addElement("score");
        scoreEle.addText(""+player2.getScore());
        /*
         * 向当前元素中添加指定名字以及对应值的属性
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
        System.out.println("写出完毕!");
        writer.close();
    }catch(Exception e){
        e.printStackTrace();
    }
		
	}
	
	//将List<Player> playList写道xml文件里面
	public  void writeMessbylist(List<Player> playList) {
		
		
		//将playlist写到playinfo.xml
		
		Document doc 
        = DocumentHelper.createDocument();

    /*
     * 2
     * Document提供了添加根元素的方法:
     * Element addElement(String name)
     * 向当前文档中添加指定名字的根元素，返回
     * 的Element就表示这个根元素。
     * 需要注意，该方法只能调用一次，因为一个
     * 文档只能有一个根元素。
     */
    Element root = doc.addElement("list");

    //3
    for(Player player2 : playList){
        /*
         * Element也提供了追加子元素的方法:
         * Element addElement(String name)
         * 调用次数没有限制，元素可以包含若干
         * 子元素。
         */
        Element empEle = root.addElement("player");

        //添加username信息
        Element nameEle = empEle.addElement("username");
        nameEle.addText(player2.getUsername());

        //添加account信息
        Element accountEle = empEle.addElement("account");
        accountEle.addText(player2.getAccount());
        
        //添加passwd信息
        Element passwdEle = empEle.addElement("passwd");
        passwdEle.addText(player2.getPasswd());
        
        //添加rank信息
        Element rankEle = empEle.addElement("rank");
        rankEle.addText(""+player2.getRank());
        
        //添加score信息
        Element scoreEle = empEle.addElement("score");
        scoreEle.addText(""+player2.getScore());
        /*
         * 向当前元素中添加指定名字以及对应值的属性
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
        System.out.println("写出完毕!");
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
	
	//输出list表的players部分属性
	public static void showMessage(List<Player> players) {
		for(Player pl:players) {
			System.out.println(pl.account+",  "+pl.passwd+",  "+pl.rank);
		} 
	}
	
	//修改节点
	 public void modifyNode() {
	    	
	   }
	 
	 public void sortlist(List<Player> playlList) {
		 	Collections.sort(playList);
	 }
	   
	 
	 
	
}