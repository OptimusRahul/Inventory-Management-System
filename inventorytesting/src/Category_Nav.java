import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Properties;
import javax.swing.*;
import java.io.FileReader;
import com.mysql.jdbc.Connection;
import com.util.DataUtil;

public class Category_Nav extends JFrame implements ActionListener{

	JLabel jlhead,jlid,jlname,jlgst;
	JTextField jtid,jtname,jtgst;
	JButton jbNext,jbLast,jbFirst,jbPrevious,back,jbupdate,jbdelete;
	ResultSet rs;
	DataUtil obj;
	FileReader fr;
	Properties pr;
	Connection con;
	public Category_Nav()
	{
		getLabels();
		
		jlhead=new JLabel(pr.getProperty("cds_head"));
		jlhead.setBounds(500, 120, 800, 40);
		jlhead.setFont(new Font("Elephant", Font.PLAIN, 20));
		jlhead.setForeground(Color.GRAY);
		add(jlhead);
		jlid=new JLabel(pr.getProperty("cds_id"));
		jlid.setBounds(500, 200, 200, 40);
		add(jlid);
		
		jtid=new JTextField();
		jtid.setBounds(610, 200, 150, 30);
		add(jtid);  
		jtid.setEditable(false);
		
		jlname=new JLabel(pr.getProperty("cat_name"));
		jlname.setBounds(500, 250, 200, 40);
		add(jlname);
		
		jtname=new JTextField();
		jtname.setBounds(610, 250, 150, 30);
		add(jtname);

		jlgst=new JLabel(pr.getProperty("cs_gst")+" %");
		jlgst.setBounds(500, 300, 200, 40);
		add(jlgst);
		
		jtgst=new JTextField();
		jtgst.setBounds(610, 300, 150, 30);
		add(jtgst);
		
		jbNext=new JButton(pr.getProperty("Btn_next"));
		jbNext.setBounds(610, 400, 110, 35);
		add(jbNext);
		
		jbPrevious=new JButton(pr.getProperty("Btn_previous"));
		jbPrevious.setBounds(480, 400, 110, 35);
		add(jbPrevious);
		
		jbFirst=new JButton(pr.getProperty("Btn_first"));
		jbFirst.setBounds(350, 400, 110, 35);
		add(jbFirst);
		
		jbLast=new JButton(pr.getProperty("Btn_last"));
		jbLast.setBounds(740, 400, 110, 35);
		add(jbLast);
		
		back=new JButton(pr.getProperty("Btn_back"));
		back.setBounds(545,450, 110, 35);
		add(back);
		
		jbupdate=new JButton(pr.getProperty("Btn_update"));
		jbupdate.setBounds(410,450, 110, 35);
		add(jbupdate);
		
		jbdelete=new JButton(pr.getProperty("Btn_delete"));
		jbdelete.setBounds(680,450, 110, 35);
		add(jbdelete);
		
		obj=new DataUtil();
		con=(Connection) obj.getConnection();
		
		back.addActionListener(this);
		jbNext.addActionListener(this);
		jbFirst.addActionListener(this);
		jbLast.addActionListener(this);
		jbPrevious.addActionListener(this);
		jbupdate.addActionListener(this);
		jbdelete.addActionListener(this);
		
		getFirst();
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		   setSize(screenSize.width,screenSize.height);
	}
	
	public static void main(String[] args) {
		new Category_Nav();
	}
	
	public void getFirst(){
		obj=new DataUtil();
		rs=obj.getResultsShow("gst_slab");
		showData();
	}
	
	public void showData(){
		try{
		jtid.setText(rs.getString(1));
		jtname.setText(rs.getString(2));
		jtgst.setText(rs.getString(3));		
		}
		catch(Exception e){System.out.println(e.toString());}
	}
	public void actionPerformed(ActionEvent ee) {
		 
		try{
			if(ee.getSource()==jbNext){
				rs.next();
				showData();
			}
			else if(ee.getSource()==jbPrevious) {
				rs.previous();
				showData();
			}
			else if(ee.getSource()==jbLast)	{
				rs.last();
				showData();
			}
			if(ee.getSource()==jbFirst)	{
				rs.first();
				showData();
			}
			if(ee.getSource()==back){
				this.hide();
				new MenuList();
			}
			else if(ee.getSource()==jbupdate){
				String  cn=jtid.getText();
				String cname=jtname.getText();
				String cgst=jtgst.getText();
				String msg=obj.gst_slab(con,"inventory.gst_slab",cn,cname,cgst,2);
				JOptionPane.showMessageDialog(this, msg);
				this.hide();
				new Category_Nav();
			}
			else if(ee.getSource()==jbdelete){
				String  cn=jtid.getText();
				String cname=jtname.getText();
				String cgst=jtgst.getText();
				String msg=obj.gst_slab(con,"inventory.gst_slab",cn,cname,cgst,3);
				JOptionPane.showMessageDialog(this,msg);
				this.hide();
				new Category_Nav();
			}
		}
			catch(Exception e){}
	}	
	public void getLabels(){
	try{
		 fr=new FileReader("AllLabels");
		 pr=new Properties();
		 pr.load(fr);
	}
	catch(Exception e){}
	}
}
