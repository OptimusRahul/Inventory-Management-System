import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import oracle.jrockit.jfr.tools.ConCatRepository;

import com.util.DataUtil;

public class Stock_Inregister extends JFrame implements ActionListener,ItemListener{
	JLabel lhead,lcat_id,lsup_id,litem_id,lqty,price,ldate;
	JComboBox jcbcat_id,jcbsup_id,jcbitem_id;
	JTextField txtqty_in,txtdate,effectprice;
	JButton jbsubmit,jbcancel,jbback;
	Date d;
	Connection con;
	Statement st;
	ResultSet rs;
	DataUtil obj;
	String ss;
	public Stock_Inregister()
	{
		setTitle("Stock In Registers");
		lhead=new JLabel("IN REGISTER FORM");
		lhead.setBounds(500, 120, 400, 40);
		lhead.setFont(new Font("Elephant", Font.PLAIN, 20));
		lhead.setForeground(Color.GRAY);
		add(lhead);

		lcat_id=new JLabel("CATEGORY NAME");
		lcat_id.setBounds(500, 250, 200, 40);
		add(lcat_id);
		
		obj=new DataUtil();
		jcbcat_id=new JComboBox();
		jcbcat_id.setBounds(610, 250, 150, 30);
		jcbcat_id.setToolTipText("Category Name");
		jcbcat_id.setEditable(false);
		add(jcbcat_id);
		jcbcat_id.addItemListener(this);
		
		litem_id=new JLabel("ITEM NAME");
		litem_id.setBounds(500, 300,200, 40);
		add(litem_id);

		jcbsup_id=new JComboBox();
		jcbsup_id.setBounds(610, 200, 150, 30);
		jcbsup_id.setEditable(false);
		add(jcbsup_id);
		
		lsup_id=new JLabel("SUPPLIER NAME");
		lsup_id.setBounds(500, 200, 200, 40);
		add(lsup_id);

		jcbitem_id=new JComboBox();
		jcbitem_id.setBounds(610, 300, 150, 30);
		add(jcbitem_id);

		lqty=new JLabel("QTY IN");
		lqty.setBounds(500, 350, 200, 40);
		add(lqty);
		
		txtqty_in=new JTextField();
		txtqty_in.setBounds(610, 350, 150, 30);
		add(txtqty_in);
		
		price=new JLabel("Price/Unit");
		price.setBounds(500,400,150,30);
		add(price);
		
		effectprice=new JTextField();
		effectprice.setBounds(610, 400, 150, 30);
		add(effectprice);
		
		ldate=new JLabel("DATE/TIME");
		ldate.setBounds(500, 450, 200, 40);
		add(ldate);
		
		txtdate=new JTextField();
		txtdate.setBounds(610, 450, 150, 30);
		txtdate.setEditable(false);
		add(txtdate);
		  Date date1 = new Date();
	    	GregorianCalendar calendar = new GregorianCalendar();
		    calendar.setTime(date1);
	        String strDate = calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
		    txtdate.setText(strDate);
        
	    jbsubmit=new JButton("SUBMIT");
		jbsubmit.setBounds(450, 530, 120, 40);
		add(jbsubmit);

		jbcancel=new JButton("CANCEL");
		jbcancel.setBounds(600, 530, 120, 40);
		add(jbcancel);

		jbback=new JButton("BACK");
		jbback.setBounds(750, 530, 120, 40);
		add(jbback);
		
		jcbsup_id.addItemListener(this);	
		jbsubmit.addActionListener(this);
		jbcancel.addActionListener(this);
		jbback.addActionListener(this);
		forcombo();
		setLayout(null);
		setVisible(true);
		setSize(1360,750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
		public static void main(String[] args) {
		new Stock_Inregister();
	}
	public void forcombo(){
		try
		{
			obj = new DataUtil();
			ArrayList<String> al7=obj.getCategory("supplier_info", "Supplier_Name","Y");
			System.out.println(al7);
			for(int i=0;i<al7.size();i++)
			{
				jcbsup_id.addItem(al7.get(i));
			}
		}
		catch(Exception e)
		{
		JOptionPane.showMessageDialog(null, "Wrong Details....");
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbcancel)
		{
		    dispose();
		}	
		else if(e.getSource()==jbback)
		{
		    new MenuList();
		    this.hide();
		}	
		else if(e.getSource()==jbsubmit)
		{	if(txtqty_in.getText().isEmpty() || effectprice.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Blank Data Can Not Insert");
			}
		else
		{
			obj=new DataUtil();
			Connection con=obj.getConnection();
			String cate2=(String) jcbcat_id.getSelectedItem();
			String cate3=(String) jcbitem_id.getSelectedItem();
			String cate1=(String) jcbsup_id.getSelectedItem();
			String Supplierid=obj.getItemFull("supplier_info","Supplier_Id","Supplier_Name",cate1);
			System.out.println(Supplierid);
			String catid=obj.getItemFull("gst_slab","Cat_ID","Cat_Name",cate2);
			String itemid=obj.getItemFull("itemcategory","ItemId","ItemName",cate3);
			String qtyin=txtqty_in.getText();
			String price=effectprice.getText();
			String date=txtdate.getText();
			String status="Y";
			ArrayList al=new ArrayList<>();
			al.add(Supplierid);
			al.add(catid);
			al.add(itemid);
			al.add(qtyin);
			al.add(price);
			al.add(date);
			al.add(status);
			System.out.println(al);
			String msg=obj.insert(con, "in_register", al);
			int crstock=obj.calStockadd(qtyin,itemid);
			String info=obj.Stockadd(crstock, price, itemid);
			JOptionPane.showMessageDialog(this, msg+" -- "+info);
			txtqty_in.setText("");
			effectprice.setText("");
			}
		}
	}
	public void getitem()
	{
		String catname;
	try
	{	jcbitem_id.removeAllItems();
		Connection con=obj.getConnection();
		String str="select ItemId from supplier_info,item_stock where supplier_info.cat_id=item_stock.CategoryId and supplier_info.Supplier_Name=?";
		PreparedStatement ps=con.prepareStatement(str);
		ps.setString(1,jcbsup_id.getSelectedItem().toString());
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			catname=obj.getItemFull("itemcategory","itemName","ItemId",rs.getString(1));
			System.out.println("catname --"+catname);
			jcbitem_id.addItem(catname);
		}
	}
	catch(Exception ea)
	{
		System.out.println(ea.toString());
	}
	}
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()==jcbsup_id)
		{	
			try
			{	jcbcat_id.removeAllItems();
				Connection con=obj.getConnection();
				String str="select cat_name from gst_slab,supplier_info where gst_slab.Cat_ID = supplier_info.cat_id and supplier_info.Supplier_Name=?";
				PreparedStatement ps=con.prepareStatement(str);
				ps.setString(1,jcbsup_id.getSelectedItem().toString());
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					jcbcat_id.addItem(rs.getString(1));
				}
				getitem();
			}
			catch(Exception ea)
			{
				System.out.println(ea.toString());
			}
			
		}
	}	
}
                                  
