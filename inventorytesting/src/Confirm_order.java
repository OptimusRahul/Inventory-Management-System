import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.table.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.Statement;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;
import com.util.DataUtil;


public class Confirm_order extends JFrame implements ActionListener {
	JLabel cname,lorder,lcusname,ltotal,ldate;
	JTextField ttotal;
	Font lfont,lfont1;
	DataUtil d;
	JTable tb;
	SimpleDateFormat df,df1;
	JButton bcancel,bdelete,bprint;
	String data1[][];
	String col[];
	Connection con;
	JScrollPane jp;
	java.sql.Statement st1;
	int l=0,i,j;
	int rcount;
	Date dd;
	DefaultTableModel model;
	
	
	public Confirm_order() {
		setLayout(null);
	lfont=new Font("Arial", Font.BOLD, 40);
	lfont1=new Font("Arial", Font.BOLD, 20);
	dd=new Date();
	df1=new SimpleDateFormat("dd-MM-yyyy");
	String sdate=df1.format(dd);
	
		cname=new JLabel();
		cname.setBounds(600,40,300,45);
		add(cname);
		cname.setFont(lfont);
		lorder=new JLabel();
		lorder.setBounds(60,100,220,20);
		add(lorder);
		lorder.setFont(lfont1);
		ltotal=new JLabel("TOTAL AMOUNT-:");
		ltotal.setBounds(800, 500, 180, 20);
		ltotal.setFont(new Font("Arial",Font.BOLD,20));
		add(ltotal);
		ldate=new JLabel(sdate);
		ldate.setBounds(70,140,220,20);
		add(ldate);
		ldate.setFont(new Font("Arial",Font.BOLD,20));
		
		ttotal=new JTextField();
		ttotal.setBounds(1000,500,150,20);
		ttotal.setFont(new Font("Arial",Font.BOLD,20));
		add(ttotal);
		ttotal.setEditable(false);
		getorderid();
		getAmount(lorder.getText());
		table();
		getcompname();
		bcancel=new JButton("Cancel Order");
		bcancel.setBounds(180,600,200,40);
		add(bcancel);
		bcancel.setBackground(Color.red);
		bcancel.setForeground(Color.WHITE);
		bcancel.setFont(new Font("Arial",Font.BOLD,20));
		bcancel.addActionListener(this);
		bdelete=new JButton("Delele Item");
		bdelete.setBounds(520,600,200,40);
		add(bdelete);
		bdelete.setBackground(Color.ORANGE);
		bdelete.setForeground(Color.WHITE);
		bdelete.setFont(new Font("Arial",Font.BOLD,20));
		bdelete.addActionListener(this);
		//bdelete.setEnabled(false);
		bprint=new JButton("Print Bill");
		bprint.setBounds(860,600,200,40);
		add(bprint);
		bprint.setBackground(Color.GREEN);
		bprint.setForeground(Color.WHITE);
		bprint.setFont(new Font("Arial",Font.BOLD,20));
		bprint.addActionListener(this);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		new Confirm_order();
	}
	public void getcompname()
	{
		d=new DataUtil<>();
		String data=d.getonedata("company_info","COMPNAME","Y");
		System.out.println(data);
		cname.setText(data);
	}
	public void getorderid()
	{
		d=new DataUtil<>();
		String data=d.getonedata("confirm_order","Order_ID","Y");
		System.out.println(data);
		lorder.setText(data);
	}
	private void table() {
		retrv();
		retrvRowCount(lorder.getText());
		data1=new String[rcount][l];
		retrvData(lorder.getText());
		tb=new JTable(data1,col);
		jp=new JScrollPane(tb);
		add(jp);
		jp.setBounds(200, 200, 1000, 250);
	}
	public void retrv()
	{
		d=new DataUtil<>();
		try{
			con=d.getConnection();
			String str="SELECT Item_Name,Quantity,Price,GST,Price_GST,Total_Price FROM inventory.confirm_order";
			st1=con.createStatement();
			ResultSet rs=st1.executeQuery(str);
			ResultSetMetaData rd=rs.getMetaData();
			 l=rd.getColumnCount();
			 System.out.println("coloumsdfs"+l);
			col=new String[l];
			for(int i=1;i<=l;i++)
			{
				col[i-1]=rd.getColumnName(i);
			}
		}catch(Exception e){System.out.println(e.toString());}
	}
		public void retrvData(String oid)
		{
			String str1="SELECT Item_Name,Quantity,Price,GST,Price_GST,Total_Price FROM confirm_order";
		PreparedStatement st;
		try{
			if(oid.length()==0)
			{
				st=con.prepareStatement(str1);
			}
			else
			{
				str1="SELECT Item_Name,Quantity,Price,GST,Price_GST,Total_Price FROM confirm_order where Order_ID=? ";
				st=con.prepareStatement(str1);
				st.setString(1, oid);
			}
			ResultSet rs=st.executeQuery();
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
		public void retrvRowCount(String oid)
		{
			d=new DataUtil<>();
			try{
				String str="Select count(*) FROM confirm_order where Order_ID=?";
				PreparedStatement st1=con.prepareStatement(str);
				st1.setString(1, oid);
				 ResultSet rs=st1.executeQuery();
				rs.first();
				rcount=rs.getInt(1);
			}catch(Exception e){System.out.println(e.toString());}
	}
		public void getAmount(String oid)
		{
			d=new DataUtil<>();
			try{

				Connection con=d.getConnection();
				String str="select sum(Total_Price)'Total Amount' FROM confirm_order where confirm_order.Order_ID=?";
				 PreparedStatement st=con.prepareStatement(str);
				 st.setString(1, oid);
				 ResultSet rs=st.executeQuery();
				rs.first();
				int totalr=rs.getInt(1);
				ttotal.setText(String.valueOf(totalr).concat("/-"));
				
			}catch(Exception e){System.out.println(e.toString());}

		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			if(ae.getSource()==bdelete)
			{
						try
						{
							model=(DefaultTableModel)tb.getModel();
						int h=tb.getSelectedRow();
						System.out.println("sfsfsfs"+h);
						JOptionPane.showMessageDialog(this, "Item Deleted");
						model.removeRow(h);
				
					}
					catch(Exception e)
					{
						System.out.println(e.toString());
					}
				}
			else if(ae.getSource()==bprint)
			{
				JOptionPane.showMessageDialog(this, "print");
			}
			
			
		}
	

}
