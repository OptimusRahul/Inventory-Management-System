import java.awt.Button;  
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.util.DataUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.FileReader;
import java.util.Properties;

public class Category_Find extends JFrame implements ActionListener  {

	private static final long serialVersionUID = 1L;
	JLabel jlcat_name, jlhead, jlcat_id, jlgst, jldate;
	JTextField jtcat_name, jtcat_id, jtgst, jtdate;
	JButton jbfind,jbprevious,jbreset;
	Connection con;
	Statement st;
	ResultSet rs;
	FileReader fr;
	Properties pr;
	Font f,f1;
	public Category_Find(){
		
		super("Search Here");
		getLabels();
		f=new Font("SERIF",Font.ITALIC,40);
	    f1=new Font("SERIF",Font.ITALIC,20);
		jlhead = new JLabel(pr.getProperty("cs_head"));	
		jlhead.setFont(new Font("Elephant", Font.PLAIN, 20));
		jlhead.setForeground(Color.GRAY);
		jlcat_name = new JLabel(pr.getProperty("cs_cat_name"));
		jtcat_name = new JTextField("",20);
		jlcat_id = new JLabel(pr.getProperty("cs_cat_id"));
		jtcat_id = new JTextField(20);
		jlgst = new JLabel(pr.getProperty("cs_gst"));
		jtgst = new JTextField(10);
		jldate = new JLabel(pr.getProperty("cs_date"));
		jtdate = new JTextField();
		jbfind = new JButton(pr.getProperty("Btn_find"));
		jbprevious=new JButton(pr.getProperty("Btn_back"));
		jbreset=new JButton(pr.getProperty("Btn_reset"));
		Date date1 = new Date();
    	GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(date1);
        String strDate = calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);

		jlhead.setForeground(Color.GRAY);
		jlhead.setBounds(400, 120, 600, 40);
		jlcat_name.setBounds(400, 200, 200, 40);
		jtcat_name.setBounds(610, 200, 150, 30);
		jbfind.setBounds(780,200,110,35);		
		jlcat_id.setBounds(400, 250, 200, 40);
		jtcat_id.setBounds(610, 250, 150, 30);
		jtcat_id.setEditable(false);
		jlgst.setBounds(400, 300, 200, 40);
		jtgst.setBounds(610, 300, 150, 30);
		jtgst.setEditable(false);
		jldate.setBounds(400, 350, 200, 40);
		jtdate.setBounds(610, 350, 150, 30);
		jtdate.setText(strDate);
		jtdate.setEditable(false);
		jbprevious.setBounds(430, 480, 110, 35);
		jbreset.setBounds(750, 480, 110, 35);
		
		jbprevious.addActionListener(this);
		jbreset.addActionListener(this);
		jbfind.addActionListener(this);
		add(jbprevious);
		add(jbreset);
		add(jlhead);
		add(jlcat_name);
		add(jtcat_name);
		add(jbfind);
		add(jlcat_id);
		add(jtcat_id);
		add(jlgst);
		add(jtgst);
		add(jldate);
		add(jtdate);
		add(jlhead);
		jlcat_name.setFont(f1); jlhead.setFont(f); jlcat_id.setFont(f1); jlgst.setFont(f1); jldate.setFont(f1);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
	}	
	public static void main(String[] args) {
		Category_Find obj1=new Category_Find();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbprevious)
		{
			new Item_Entry();
		}
		else if(e.getSource()==jbreset)
		{
			Reset();
		}
		else if(e.getSource()==jbfind)
		{
			try{
				DataUtil obj = new DataUtil();
				Connection m =obj.getConnection();
				ArrayList al=new ArrayList();
				String cs=jtcat_name.getText().trim();
				al=obj.FindData(m,"gst_slab",cs,"Cat_Name");
				System.out.println(al);
				if(al.equals(null))
				{
					JOptionPane.showMessageDialog(getContentPane(), "Item Not Found");
				}
				else
				{
					jtcat_id.setText((String) al.get(0));
					jtgst.setText((String)al.get(2));
				}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(getContentPane(),"Data Not Found");
				jtcat_name.setText("");
			}				

		}
	
		
	}
	private void Reset() {
		jtcat_name.setText("");
		jtcat_id.setText("");
		jtgst.setText("");
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
