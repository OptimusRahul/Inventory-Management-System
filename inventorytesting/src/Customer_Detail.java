import java.awt.Dimension;
import java.awt.Font;
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
public class Customer_Detail extends JFrame implements ActionListener{
	Connection con;
	Statement st;
	ResultSet rs;
	JLabel jlhead;
	ResultSetMetaData rd;
	JTable tb;
	int rcount;
	int l=0,i,j;
	JButton jbsearch,jback,jbrefresh;
	JScrollPane jp;
	String data1[][];
	String col[];
	String ss="";
	JTextField jtSearch;
	DataUtil util;
	Font fd;
	public Customer_Detail() {
		setTitle("Show_Info");
		jlhead=new JLabel("Customer_Info");
		jlhead.setBounds(500,40,300,80);
		fd=new Font("Cooper",Font.BOLD,36);
		jlhead.setFont(fd);
		add(jlhead);
		util=new DataUtil();
		con=util.getConnection();
		retrv();
		retrvRowCount();
		data1=new String[rcount][l];
		retrvData(ss);
		jtSearch=new JTextField();
		jtSearch.setBounds(450, 140, 400, 30);
		add(jtSearch);
		jbsearch=new JButton("Search");
		jbsearch.setBounds(900, 140, 100, 30);
		add(jbsearch);
		jback=new JButton("Back");
		jback.setBounds(100, 140, 100, 30);
		add(jback);
		jback.addActionListener(this);
		jbrefresh=new JButton("Refresh");
		jbrefresh.setBounds(1100, 140, 100, 30);
		add(jbrefresh);
		jbrefresh.addActionListener(this);
		jbsearch.addActionListener(this);
		tb=new JTable(data1,col);
		jp=new JScrollPane(tb);
		add(jp);
		jp.setBounds(20, 200, 1300, 550);

		setLayout(null);
		setSize(500, 600);
		setVisible(true);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		//getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {	
		new Customer_Detail();
	}
	/*public void fatchdata()
	{ ss=jtSearch.setText("");
		retrv();
		retrvRowCount();
		data1=new String[rcount][l];
		retrvData(ss);
		tb=new JTable(data1,col);
		jp=new JScrollPane(tb);
		add(jp);
		jp.setBounds(20, 200, 1300, 550);
	}*/
	public void retrv()
	{
		try{
			String str=" SELECT  CustomerName, CustomerPhone, address, CustomerEmail, CustomerGST, CustDate, Status FROM customer_info  ";
			st=con.createStatement();
			rs=st.executeQuery(str);
			ResultSetMetaData rd=rs.getMetaData();
			l=rd.getColumnCount();
			col=new String[l];
			for(int i=1;i<=l;i++)
			{
				col[i-1]=rd.getColumnName(i);
			}
		}catch(Exception e){	String msg =e.toString();
		JOptionPane.showMessageDialog(this, msg);}

	}
	public void retrvData(String ss)
	{String str1=" SELECT  CustomerName, CustomerPhone, address, CustomerEmail, CustomerGST, CustDate, Status FROM customer_info  ";
	PreparedStatement st;
	try{
		if(ss.length()==0)
		{
			st=con.prepareStatement(str1);
		}
		else
		{
			str1=str1.concat(" where customerName=?");
//			rcount=1;
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
	catch(Exception e){
		String msg =e.toString();
		JOptionPane.showMessageDialog(this,"Please check this Data is not found in the table");}
	}


	public void retrvRowCount()
	{
		try{
			String str="Select count(*) FROM customer_info";
			st=con.createStatement();
			rs=st.executeQuery(str);
			rs.first();
			rcount=rs.getInt(1);
		}catch(Exception e){	String msg =e.toString();
		JOptionPane.showMessageDialog(this, msg);}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==jbsearch) {
			retrv();
			retrvRowCount();
			data1=new String[rcount][l];
			retrvData(jtSearch.getText());
			tb=new JTable(data1,col);
			jp=new JScrollPane(tb);
			add(jp);
			jp.setBounds(20, 200, 1300, 550);

		}
		else if(arg0.getSource()==jback)
		{
			new MenuList();
			this.dispose();
		}
		if(arg0.getSource()==jbrefresh)
		{	
	
		new Customer_Detail();
		this.hide();
		
			
		}

	}
}
