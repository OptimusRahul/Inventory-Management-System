import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import com.sun.jmx.remote.util.OrderClassLoaders;
import com.util.DataUtil;

public class Pending_Order extends JFrame implements ItemListener,ActionListener{
	JLabel lhead, lCusName, lOrderId, lAmount;
	JComboBox ccusname, cid;
	JTable tb;
	JScrollPane jp;
	DataUtil d;
	int rcount;
	int l,i,j;
	String[] col;
	String[][] data1;
	String ss="";
	Connection con;
	Statement st;
	PreparedStatement ps;
	ResultSet rs;
	JButton jback,jconfirm;
	Font f,f1;
	public Pending_Order() {
		super("Pending Orders");
		setLayout(null);
		lhead=new JLabel("Pending Orders");
		add(lhead);
		lhead.setBounds(550, 50, 300, 50);
		
		
		
		lCusName=new JLabel("Customer Name");
		add(lCusName);
		lCusName.setBounds(450, 150, 150, 20);
		
		ccusname=new JComboBox();
		add(ccusname);
		ccusname.setBounds(650, 150, 225, 25);
		
		showcustomer();
		lOrderId=new JLabel("Order ID");
		add(lOrderId);
		lOrderId.setBounds(450, 200, 150, 20);
		
		cid=new JComboBox();
		cid.setBounds(650, 200, 225, 25);
		
		add(cid);		
		lAmount=new JLabel("Pending Amount :NULL");
		add(lAmount);
		lAmount.setBounds(450, 250, 300, 20);
		
		jback=new JButton("Back");
		add(jback);
		jback.setBounds(450, 300, 100, 40);
		jback.addActionListener(this);
		jconfirm=new JButton("Confirm");
		add(jconfirm);
		jconfirm.setBounds(600, 300, 100, 40);
		jconfirm.addActionListener(this);
		f=new Font("SERIF",Font.ITALIC,40);
	    f1=new Font("SERIF",Font.ITALIC,20);

		lhead.setFont(f);
		lCusName.setFont(f1); 
		lOrderId.setFont(f1);
		lAmount.setFont(f1);
		getdata();
		ccusname.addItemListener(this);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void createtable() {
		retrv();
		retrvRowCount();
		data1=new String[rcount][l];
		retrvData(ss);
		tb=new JTable(data1,col);
		jp=new JScrollPane(tb);
		add(jp);
		jp.setBounds(175, 400, 1000, 250);
	}
	public static void main(String[] args) {
		new Pending_Order();
	}
	public void showcustomer() {
		d = new DataUtil();
		ArrayList<String> al7=d.getCategory("order_info", "Customer_Name","N");
		System.out.println(al7);
		for(int i=0;i<al7.size();i++)
		{
			ccusname.addItem(al7.get(i));
		}	
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==ccusname)
		{
			getdata();
		}
	}
	private void getdata() {
		try
		{
			 con=d.getConnection();
			String str="select order_ID,Totalprice from order_info where Customer_Name=? and Status='N'";
			PreparedStatement ps=con.prepareStatement(str);
			ps.setString(1, ccusname.getSelectedItem().toString());
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				cid.addItem(rs.getString(1));
				ss=rs.getString(1);
				lAmount.setText("Pending Amount :"+rs.getString(2));
			}
			createtable();
		}
		catch(Exception ea)
		{
			System.out.println(ea.toString());
		}
	}
	public void retrv()
	{
		try{
			String str="select Barcode,Quantity,Item_Name,Price,GST,Price_GST,Total_Price from pending_order";
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
	{
	String str1="select Barcode,Quantity,Item_Name,Price,GST,Price_GST,Total_Price from order_info,pending_order where order_info.Order_ID = pending_order.Order_ID and order_info.Status ='N'";
	PreparedStatement st;
	try{
		if(ss.length()==0)
		{
			st=con.prepareStatement(str1);
		}
		else
		{
			str1="select Barcode,Quantity,Item_Name,Price,GST,Price_GST,Total_Price from order_info,pending_order where order_info.Order_ID = pending_order.Order_ID and order_info.Order_ID =? ";
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
			String str="Select count(*) FROM pending_order";
			st=con.createStatement();
			rs=st.executeQuery(str);
			rs.first();
			rcount=rs.getInt(1);
		}catch(Exception e){System.out.println(e.toString());}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jconfirm)
		{
			String id=cid.getSelectedItem().toString();
			String showinfo;
			ArrayList al=new ArrayList();
			for(int i=0;i<rcount;i++)
			{
				al.add(id);
				int rem=d.calStock(id);
				showinfo=d.stockupdate(rem,id);
				System.out.println(showinfo);
				al.add((String)tb.getValueAt(i, 0));
				al.add((String)tb.getValueAt(i, 1));
				al.add((String)tb.getValueAt(i, 2));
				al.add((String)tb.getValueAt(i, 3));
				al.add((String)tb.getValueAt(i, 4));
				al.add((String)tb.getValueAt(i, 5));
				al.add((String)tb.getValueAt(i, 6));
				al.add("Y");
			}
			String msg=d.tablemove(con, "confirm_order", al,rcount);
			JOptionPane.showMessageDialog(this, msg);
			delectdata("pending_order",id);
			String msg1=d.DeleteForAll(con, "order_info", "Status", "Order_ID", id,"Y");
			JOptionPane.showMessageDialog(getContentPane(), "update--"+msg1);
			this.dispose();
			new Confirm_order();
		}
		
	}
	public void delectdata(String table,String oid) {
		try{
		Connection con=d.getConnection();
		String q="delete from pending_order where order_id=?";
		ps=con.prepareStatement(q);
		ps.setString(1, oid);
		int p=ps.executeUpdate();
		System.out.println("delete");
		}
		catch(Exception ee)
		{
			ee.toString();
		}
		
	}

	
	
}
