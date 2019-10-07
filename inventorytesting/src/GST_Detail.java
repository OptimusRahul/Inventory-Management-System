import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.*;

import com.util.DataUtil;
public class GST_Detail extends JFrame implements ActionListener{
	Connection con;
	Statement st;
	ResultSet rs;
	ResultSetMetaData rd;
	JTable tb;
	int rcount;
	int l=0,i,j;
	JButton jbsearch,jback;
	JScrollPane jp;
	String data1[][];
	String col[];
	String ss="";
	JTextField jtSearch;
	DataUtil util;

	public GST_Detail() {
		setTitle("Show_Info");
		util=new DataUtil();
		con=util.getConnection();
		retrv();
		retrvRowCount();
		data1=new String[rcount][l];
		retrvData(ss);
		jtSearch=new JTextField();
		jtSearch.setBounds(450, 40, 400, 30);
		add(jtSearch);
		jbsearch=new JButton("Search");
		jbsearch.setBounds(900, 40, 100, 30);
		add(jbsearch);
		jback=new JButton("Back");
		jback.setBounds(100, 40, 100, 30);
		add(jback);
		jback.addActionListener(this);
		jbsearch.addActionListener(this);
		tb=new JTable(data1,col);
		jp=new JScrollPane(tb);
		add(jp);
		jp.setBounds(20, 100, 800, 500);

		setLayout(null);
		setSize(500, 600);
		setVisible(true);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		//getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {	
		GST_Detail obj1=new GST_Detail();
	}
	public void retrv()
	{
		try{
			String str="SELECT Cat_ID,Cat_Name,Gst_No,Status FROM gst_slab";
			st=con.createStatement();
			rs=st.executeQuery(str);
			ResultSetMetaData rd=rs.getMetaData();
			l=rd.getColumnCount();
			col=new String[l];
			for(int i=1;i<=l;i++)
			{
				col[i-1]=rd.getColumnName(i);
			}
		}catch(Exception e){System.out.println(e.toString());}

	}
	public void retrvData(String ss)
	{String str1="SELECT Cat_ID,Cat_Name,Gst_No,Status FROM gst_slab";
	PreparedStatement st;
	try{
		if(ss.length()==0)
		{
			st=con.prepareStatement(str1);
		}
		else
		{
			str1=str1.concat(" where Cat_Name=?");
			//rcount=1;
			st=con.prepareStatement(str1);
			st.setString(1, ss);
		}
		rs=st.executeQuery();
		for(i=0;i<rcount;i++)
		{rs.next();
		for(j=0;j<l;j++)
		{
			data1[i][j]=rs.getString(j+1);	
		}
		}
	}
	catch(Exception e){System.out.println(e.toString());}
	}


	public void retrvRowCount()
	{
		try{
			String str="Select count(*) FROM gst_slab";
			st=con.createStatement();
			rs=st.executeQuery(str);
			rs.first();
			rcount=rs.getInt(1);
		}catch(Exception e){System.out.println(e.toString());}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==jbsearch) {
			if(jtSearch.getText().isEmpty())
				JOptionPane.showMessageDialog(this, "plz insert Category Name");
			else
			{
			retrv();
			retrvRowCount();
			data1=new String[rcount][l];
			retrvData(jtSearch.getText());
			//tb.removeAll();
			//					 tb=new JTable(data1,col);
			tb=new JTable(data1,col);
			jp=new JScrollPane(tb);
			add(jp);
			jp.setBounds(20, 100, 800, 500);
			}
		}
		else if(arg0.getSource()==jback)
		{
			System.out.println("Back");
			new MenuList();
			this.dispose();
		}

	}
}