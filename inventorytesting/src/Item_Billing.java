import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.util.BCELifier;
import com.util.DataUtil;


@SuppressWarnings("serial")
public class Item_Billing extends JFrame implements ActionListener,FocusListener {
	JLabel head, ldate, litemname, lphone, lstock, lorderid, lcustomer, lquantity, litem,ltotal;
	JTextField torder, tdate, tphone, tquantity, tbarcode, tcusname,totalamount;
	JButton bguest, badditem, bpending, bconfirm, bgetname, bitemname,jback;
	DataUtil d;
	String stock, item, st;
	Font fhead, flabel;
	Date dd;
	SimpleDateFormat df,df1;
	JTable tb;
	int rcount;
	int l=0,i,j;
	JScrollPane jp;
	String data1[][];
	String col[];
	Connection c,con;
	Statement st1;
	int value,totalqty,GST;
	Float price,netprice;
	public Item_Billing() {
		super("BILLING");
		setLayout(null);
		dd=new Date();
		df=new SimpleDateFormat("ddMMyyyy");
		df1=new SimpleDateFormat("dd-MM-yyyy");
		String sdate=df1.format(dd);
		String ss=df.format(dd);
		head=new JLabel("Order Checkout");
		add(head);
		head.setBounds(550,20,300,50);
		fhead=new Font("SERIF",Font.ITALIC,40);
		head.setFont(fhead);
		flabel=new Font("SERIF",Font.ITALIC,20);
		lorderid=new JLabel("Order ID");
		add(lorderid);
		lorderid.setBounds(450, 90, 200, 20);
		lorderid.setFont(flabel);
		lphone=new JLabel("Customer Phone No.");
		add(lphone);
		lphone.setBounds(450, 140, 200, 20);
		lphone.setFont(flabel);
		lcustomer=new JLabel("Customer Name");
		add(lcustomer);
		lcustomer.setBounds(450, 190, 200, 20);
		lcustomer.setFont(flabel);
		litem=new JLabel("Item ID/Barcode");
		add(litem);
		litem.setBounds(450, 240, 200, 20);
		litem.setFont(flabel);
		lquantity=new JLabel("Quantity");
		add(lquantity);
		lquantity.setBounds(450, 290, 200, 20);
		lquantity.setFont(flabel);
		lstock=new JLabel("Stock - "+stock);
		add(lstock);
		lstock.setBounds(960, 285, 100, 30);
		lstock.setFont(flabel);
		ldate=new JLabel("Date");
		add(ldate);
		ldate.setBounds(200, 40, 50, 30);
		ldate.setFont(flabel);
		tdate=new JTextField(20);
		add(tdate);
		tdate.setBounds(150, 90, 150, 30);
		tdate.setFont(flabel);
		Date date1 = new Date();
    	tdate.setText(sdate);
    	tdate.setEditable(false);
		torder=new JTextField(50);
		torder.setBounds(650, 85, 300, 30);
		add(torder);
		torder.setFont(flabel);
		tphone=new JTextField(50);
		tphone.setBounds(650, 135, 300, 30);
		add(tphone);
		tphone.setFont(flabel);
		torder.setFont(flabel);
		tcusname=new JTextField(100);
		add(tcusname);
		tcusname.setBounds(650, 185, 300, 30);
		tcusname.setFont(flabel);
		tcusname.setEnabled(false);
		tbarcode=new JTextField(50);
		add(tbarcode);
		tbarcode.setBounds(650, 235, 300, 30);
		tbarcode.setFont(flabel);
		litemname=new JLabel(item);
		litemname.setBounds(1070, 235, 300, 30);
		litemname.setFont(flabel);
		add(litemname);
		tquantity=new JTextField(50);
		add(tquantity);
		tquantity.setBounds(650, 285, 300, 30);
		tquantity.setFont(flabel);
		tquantity.addFocusListener(this);
		bgetname=new JButton("Get Name");
		bgetname.setBounds(960, 135, 150, 35);
		add(bgetname);
		bgetname.addActionListener(this);
		bgetname.setFont(flabel);
		bguest=new JButton("Guest Checkout");
		bguest.setBounds(960, 185, 190, 35);
		bguest.setFont(flabel);
		bguest.addActionListener(this);
		add(bguest);
		bpending=new JButton("Pending Order");
		bpending.setBounds(500, 340, 200, 35);
		bpending.setFont(flabel);
		add(bpending);
		bpending.addActionListener(this);
		badditem=new JButton("Add Item");
		badditem.setBounds(1070, 285, 150, 35);
		badditem.setFont(flabel);
		add(badditem);
		badditem.addActionListener(this);
		bitemname=new JButton("Find");
		bitemname.setBounds(960, 235, 100, 35);
		bitemname.setFont(flabel);
		bitemname.addActionListener(this);
		add(bitemname);
		ltotal=new JLabel("Total Amount");
		ltotal.setBounds(60,350,150,30);
		add(ltotal);
		ltotal.setFont(flabel);
		totalamount=new JTextField();
		totalamount.setBounds(60, 400, 150, 30);
		add(totalamount);
		totalamount.setFont(flabel);
		bconfirm=new JButton("Confirm order");
		bconfirm.setBounds(750, 340, 200, 35);
		bconfirm.setFont(flabel);
		add(bconfirm);
		jback=new JButton("Back");
		add(jback);
		jback.setBounds(1000, 340, 150, 35);
		bconfirm.addActionListener(this);
		d=new DataUtil();
		String id=d.orderid(ss, "order_info", "Order_ID");
		torder.setText(id);
		table();
		TotalAmount();
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void table() {
		retrv();
		retrvRowCount();
		retrvData();
		tb=new JTable(data1,col);
		jp=new JScrollPane(tb);
		add(jp);
		jp.setBounds(250, 400, 1000, 250);
	}
	public void halfreset()
	{
		litemname.setText("");
		lstock.setText("");
		tquantity.setText("");
		tbarcode.setText("");
	}
	public static void main(String[] args) {
		new Item_Billing();
	}
	public void actionPerformed(ActionEvent ar) {
		if(ar.getSource()==bgetname){
			d=new DataUtil();
			String cus=d.getCustomerName(tphone.getText());
			if(cus.equals("Not Found")){
				JOptionPane.showMessageDialog(getContentPane(), cus);
			}
			else{
				tcusname.setText(cus);
			}
		}
		else if(ar.getSource()==bguest)
		{
			tcusname.setEnabled(true);
			tphone.setText("XXXX-XXX-XXX");
			tphone.setEnabled(false);
		}
		else if(ar.getSource()==bitemname){
			d=new DataUtil();
			ArrayList retData=d.getItemNameQty(tbarcode.getText());
			if(retData.size()==0){
				JOptionPane.showMessageDialog(getContentPane(), "Not Found");
			}
			else{
				litemname.setText(retData.get(0).toString());
				lstock.setText(retData.get(1).toString());
				price=Float.parseFloat(retData.get(2).toString());
				GST=Integer.parseInt(retData.get(3).toString());
				netprice=(price*GST)/100;
			}
		}
		else if(ar.getSource()==badditem){
			if(torder.getText().isEmpty()||tbarcode.getText().isEmpty()||tquantity.getText().isEmpty()||litemname.getText().isEmpty())
				JOptionPane.showMessageDialog(this, "Please complete blank field");
			else if((totalqty)<Integer.parseInt(lstock.getText())) 
			{	
					d=new DataUtil();
					ArrayList<String> data=new ArrayList<String>();
					data.add(torder.getText());
					data.add(tbarcode.getText().toUpperCase());
					data.add(tquantity.getText());
					int qty=Integer.parseInt(tquantity.getText());
					data.add(litemname.getText());
					data.add(String.valueOf(price));
					data.add(String.valueOf(GST+" %"));
					data.add(String.valueOf(netprice));
					data.add(String.valueOf((netprice+price)*qty));
					int stock=Integer.parseInt(lstock.getText());
					int quantity=Integer.parseInt(tquantity.getText());
					data.add(String.valueOf(stock-quantity));
					c=d.getConnection();
					String msg=d.insert(c, "temp_order_info", data);
					JLabel add=new JLabel(msg);
					table();
					TotalAmount();
					halfreset();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Stock are not available");
			}
		}
		else if(ar.getSource()==bpending){
			try{
				ArrayList al=new ArrayList();
				for(int i=0;i<rcount;i++)
				{
					al.add((String)tb.getValueAt(i, 0));
					al.add((String)tb.getValueAt(i, 1));
					al.add((String)tb.getValueAt(i, 2));
					al.add((String)tb.getValueAt(i, 3));
					al.add((String)tb.getValueAt(i, 4));
					al.add((String)tb.getValueAt(i, 5));
					al.add((String)tb.getValueAt(i, 6));
					al.add((String)tb.getValueAt(i, 7));
					al.add("Y");
				}
				String msg=d.tablemove(con, "pending_order", al,rcount);
				d.permanentlyDelete("temp_order_info");
				JOptionPane.showMessageDialog(this, msg);
				String msg1=d.orderadd(con, "order_info",torder.getText(),tphone.getText(),tcusname.getText(),totalamount.getText(),"N");
				JOptionPane.showMessageDialog(getContentPane(), msg1);
				this.dispose();
				new Item_Billing();
			}
			catch (Exception e) {
				e.toString();
			}
		}
		else if(ar.getSource()==bconfirm)
		{	String showinfo;
			ArrayList al=new ArrayList();
			for(int i=0;i<rcount;i++)
			{
				al.add((String)tb.getValueAt(i, 0));
				int rem=d.calStock((String) tb.getValueAt(i, 1));
				showinfo=d.stockupdate(rem,(String) tb.getValueAt(i, 1));
				System.out.println(showinfo);
				al.add((String)tb.getValueAt(i, 1));
				al.add((String)tb.getValueAt(i, 2));
				al.add((String)tb.getValueAt(i, 3));
				al.add((String)tb.getValueAt(i, 4));
				al.add((String)tb.getValueAt(i, 5));
				al.add((String)tb.getValueAt(i, 6));
				al.add((String)tb.getValueAt(i, 7));
				al.add("Y");
			}
			String msg=d.tablemove(con, "confirm_order", al,rcount);
			JOptionPane.showMessageDialog(this, msg);
			d.permanentlyDelete("temp_order_info");
			String msg1=d.orderadd(con, "order_info",torder.getText(),tphone.getText(),tcusname.getText(),totalamount.getText(),"Y");
			JOptionPane.showMessageDialog(getContentPane(), msg1);
			this.dispose();
			new Confirm_order();
		}
	}
	public void retrv()
	{
		try{
			con=d.getConnection();
			String str="SELECT Order_ID,Barcode,Quantity,Item_Name,Price,GST,Price_GST,Total_Price FROM inventory.temp_order_info";
			st1=con.createStatement();
			ResultSet rs=st1.executeQuery(str);
			ResultSetMetaData rd=rs.getMetaData();
			 l=rd.getColumnCount();
			col=new String[l];
			for(int i=1;i<=l;i++)
			{
				col[i-1]=rd.getColumnName(i);
			}
		}catch(Exception e){System.out.println(e.toString());}
	}
		public void retrvData()
		{
			try{
				String str1="SELECT Order_ID,Barcode,Quantity,Item_Name,Price,GST,Price_GST,Total_Price FROM inventory.temp_order_info";
				st1=con.createStatement();
				ResultSet rs=st1.executeQuery(str1);
				data1=new String[rcount][l];
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
				String str="Select count(*) FROM temp_order_info";
				st1=con.createStatement();
				ResultSet rs=st1.executeQuery(str);
				rs.first();
				rcount=rs.getInt(1);
			}catch(Exception e){System.out.println(e.toString());}
	}
		public int getqty(String itemid)
		{
			try{
				String str="select sum(Quantity) from temp_order_info group by barcode having barcode=?";
				PreparedStatement ps=con.prepareStatement(str);
				ps.setString(1, itemid);
				ResultSet rs=ps.executeQuery();
				rs.first();
				value=rs.getInt(1);
				System.out.println("in function"+value);
			}catch(Exception e){System.out.println(e.toString());}
			return value;
		}
		public void focusGained(FocusEvent e) {	
		}
		public void focusLost(FocusEvent e) {
			if(e.getSource()==tquantity)
			{
				totalqty=getqty(tbarcode.getText());
				totalqty=totalqty+Integer.parseInt(tquantity.getText());
				System.out.println("Total Qty "+totalqty);
			}	
		}
		public void TotalAmount()
		{
			try{
				Connection con=d.getConnection();
				String str="select sum(Total_Price)'Total Amount' FROM temp_order_info";
				 Statement st=con.createStatement();
				 ResultSet rs=st.executeQuery(str);
				rs.first();
				float totalr=rs.getInt(1);
				totalamount.setText(String.valueOf(totalr));
				
			}catch(Exception e){System.out.println(e.toString());}
		}
}
