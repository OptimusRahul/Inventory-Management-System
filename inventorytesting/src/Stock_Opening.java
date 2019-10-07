import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.*;

import com.mysql.jdbc.Statement;
import com.util.DataUtil;
import com.util.CheckEntries;

public class Stock_Opening extends JFrame implements ItemListener,FocusListener,ActionListener{
	JLabel lcateid,litemname,lsubunit,lopendate,lhead,lprice,lunit,lopenq,lcurrentq,lreorderq,leffectivep;
	JComboBox<String> jcatechoose,jitemchoose, junitchooser,junitcatechooser;
	JTextField topenq,tcurrentq,treorderq,tprice,tdate,teffectivep;
	JButton bsave, bback,badd,bnavigation;
	JLabel vopenq,vprice;
	DataUtil obj;
	Statement st;
	ResultSet rs;
	int status1,status2;
	String cate2,itemid;
	Properties pr;
	FileReader fr;
	public Stock_Opening() {
		setLayout(null);
		getLabels();
		lhead=new JLabel(pr.getProperty("ie_head"));
		lhead.setBounds(530,40,400,50);
		add(lhead);
		Font fhead=new Font("Arial",Font.BOLD,50);
		lhead.setFont(fhead);
		Font lfont=new Font("Arial",Font.BOLD,20);
		lcateid=new JLabel(pr.getProperty("ie_cat"));
		litemname=new JLabel(pr.getProperty("ie_item"));
		lsubunit=new JLabel(pr.getProperty("ie_sunit"));
		jcatechoose=new JComboBox<String>();
		jitemchoose=new JComboBox<String>();
		junitcatechooser=new JComboBox<String>();
		lcateid.setBounds(400,120,200,30);
		lcateid.setFont(lfont);
		litemname.setFont(lfont);
		litemname.setBounds(400,170,250,30);
		jcatechoose.setBounds(675,120,275,30);
		jitemchoose.setBounds(675,170,275,30);
		lsubunit.setBounds(400,270,250,30);
		lsubunit.setFont(lfont);
		add(lsubunit);
		add(litemname);
		add(jitemchoose);
		add(lcateid);
		add(jcatechoose);
		junitcatechooser.setBounds(675,270,275,30);
		add(junitcatechooser);
		lunit=new JLabel(pr.getProperty("ie_unit"));
		lunit.setBounds(400,220,250,30);
		add(lunit);
		lunit.setFont(lfont);
		junitchooser=new JComboBox<String>();
		junitchooser.setBounds(675,220,275,30);
		add(junitchooser);
		
		lopenq=new JLabel(pr.getProperty("ie_oqun"));
		vopenq=new JLabel("");
		vprice=new JLabel("");
		lcurrentq=new JLabel(pr.getProperty("ie_cqun"));
		lreorderq=new JLabel(pr.getProperty("ie_rqun"));
		lopenq.setBounds(400,320,250,30);
		lcurrentq.setBounds(400,370,250,30);
		lreorderq.setBounds(400,420,250,30);
		lopenq.setFont(lfont);
		lcurrentq.setFont(lfont);
		lreorderq.setFont(lfont);
		add(lopenq);
		add(lcurrentq);
		add(lreorderq);
		topenq=new JTextField(100);
		topenq.addFocusListener(this);
		tcurrentq=new JTextField(100);

		treorderq=new JTextField(100);
	

		topenq.setBounds(675,320,275,30);
		vopenq.setBounds(980,320,275,30);
		add(vopenq);
		tcurrentq.setBounds(675,370,275,30);
		treorderq.setBounds(675,420,275,30);
		add(topenq);
		add(tcurrentq);
		add(treorderq);
		lprice=new JLabel(pr.getProperty("ie_ppunit"));
		lprice.setBounds(400,470,250,30);
		vprice.setBounds(980,470,250,30);
		add(vprice);
		lprice.setFont(lfont);
		add(lprice);
		tprice=new JTextField(50);
		tprice.setBounds(675,470,275,30);
		add(tprice);
		leffectivep=new JLabel(pr.getProperty("ie_effprice"));
		leffectivep.setBounds(400,520,250,30);
		add(leffectivep);
		leffectivep.setFont(lfont);
		teffectivep=new JTextField(100);
		teffectivep.setBounds(675,520,275,30);
		add(leffectivep);
		add(teffectivep);
		teffectivep.setEditable(false);
		tprice.addFocusListener(this);
		lopendate=new JLabel(pr.getProperty("ie_isod"));
		lopendate.setBounds(400,570,250,30);
		add(lopendate);
		tdate=new JTextField(100);
		tdate.setBounds(675,570,275,30);
		add(tdate);
		lopendate.setFont(lfont);
		bsave=new JButton(pr.getProperty("Btn_save"));
		bback=new JButton(pr.getProperty("Btn_back"));
		badd=new JButton(pr.getProperty("Btn_ADD"));
		
		bsave.setBounds(300,630,120,50);
		badd.setBounds(550,630,120,50);
		bback.setBounds(800,630,120,50);
		
		add(bsave);
		add(bback);
		add(badd);
		
		bsave.setBackground(Color.blue);
		bback.setBackground(Color.red);
		bsave.setForeground(Color.white);
		bback.setForeground(Color.white);
		bsave.setFont(lfont);
		bback.setFont(lfont);
		badd.setFont(lfont);
		badd.setBackground(Color.green);
		badd.setForeground(Color.white);
		bsave.addActionListener(this);
		bback.addActionListener(this);
		badd.addActionListener(this);
		 bnavigation=new JButton(pr.getProperty("Btn_navigate"));
		   bnavigation.setBounds(1050,630,180,50);
		   bnavigation.setFont(lfont);
		   bnavigation.setBackground(Color.orange);
		   bnavigation.setForeground(Color.WHITE);
		   add(bnavigation);
		  
		   bnavigation.addActionListener(this);
		
		jcatechoose.addItemListener(this);
		junitchooser.addItemListener(this);
		 Date date1 = new Date();
	    	GregorianCalendar calendar = new GregorianCalendar();
		    calendar.setTime(date1);
	        String strDate = calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
		    tdate.setText(strDate);
		    tdate.setEditable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		setVisible(true);
		forcombo();
		forunitchooser();
		tcurrentq.setEditable(false);
		treorderq.setEditable(false);
		jitemchoose.removeAllItems();

		
	}
		

	public void getLabels(){
		try{
			fr=new FileReader("AllLabels");
			pr=new Properties();
			pr.load(fr);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void forcombo(){
		try
		{
			obj = new DataUtil();
			ArrayList<String> al7=obj.getCategory("gst_slab", "Cat_Name","Y");
			System.out.println(al7);
			for(int i=0;i<al7.size();i++)
			{
				jcatechoose.addItem(al7.get(i));
			}
		}
		catch(Exception e)
		{
		JOptionPane.showMessageDialog(null, "Wrong Details....");
		}
		
	}
	
	public void forunitchooser(){
		try
		{
			obj=new DataUtil();
			ArrayList al6=obj.getCategory("unit_master", "UnitName","Y");
			System.out.println(al6);
			for(int i=0;i<al6.size();i++)
			{
				junitchooser.addItem((String) al6.get(i));
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	public static void main(String[] args) {
		new Stock_Opening();
		}
	
	
	@Override
	public void itemStateChanged(ItemEvent ae) {
		if(ae.getSource()==junitchooser)
		{
			if(ae.SELECTED==1){
				try
				{			
					obj = new DataUtil();
					ArrayList<String> ar=new ArrayList<String>();
					String cate=(String) junitchooser.getSelectedItem();
					System.err.println(cate);
					ar=obj.getUnitSize("SizeName", cate);
					System.out.println(ar);
					junitcatechooser.removeAllItems();
					for(int i=0;i<ar.size();i++){
						junitcatechooser.addItem(ar.get(i));
					}
				}
				catch(Exception e){
					System.err.println(e);
				}
			}
		}
	else if(ae.getSource()==jcatechoose)
	{	
		if(ItemEvent.SELECTED==1){
			try
			{			
				obj = new DataUtil();
				ArrayList<String> ar=new ArrayList<String>();	
				String cate=(String) jcatechoose.getSelectedItem();
				System.err.println(cate);
				ar=obj.getItemName("cat_Id","itemcategory","Category_Name",cate,"ItemName");
				System.out.println("For item");
				System.out.println(ar);
				jitemchoose.removeAllItems();
				for(int j=0;j<ar.size();j++){
					jitemchoose.addItem(ar.get(j));
				}
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
	}
	}
	public void focusGained(FocusEvent arg0) {		
	}
	public void focusLost(FocusEvent ae) {
		CheckEntries ce=new CheckEntries();
		if(ae.getSource()==topenq)
		{
			String c=topenq.getText();
			boolean b=ce.CheckOpenAmount(c);
			if(b==false){
				status1=0;
				vopenq.setText("Invalid Amount");			
			}
			else if(b==true){
				status1=1;
				vopenq.setText("");
			}
			tcurrentq.setText(topenq.getText());
			String op=topenq.getText();
			int reo=Integer.parseInt(op);
			treorderq.setText(String.valueOf(reo/10));
		}
		else if(ae.getSource()==tprice)
		{
			String cp=tprice.getText();
			boolean b=ce.CheckOpenAmount(cp);
			if(b==false){
				status2=0;
				vprice.setText("Invalid Amount");
			
			}
			else if(b==true){
				status2=1;
				vprice.setText("");
			}
			String prc=tprice.getText();
			teffectivep.setText(prc);
		}
		}



	@Override
	public void actionPerformed(ActionEvent ac) {
		if(ac.getSource()==bsave)
		{
			if(status1==0 || status2==0)
			{
				JOptionPane.showMessageDialog(getContentPane(), "Please Complete Validations");
			}
			else if(status1==1 || status2==1)
			{
				Connection c=obj.getConnection();
				System.out.println("BUTTON");
				String cate1=(String) jcatechoose.getSelectedItem();
				cate2=(String) jitemchoose.getSelectedItem();
				String cate3=(String)junitchooser.getSelectedItem();
				String cate4=(String) junitcatechooser.getSelectedItem();
				String catid=obj.getItemFull("gst_slab","Cat_ID","Cat_Name",cate1);
				itemid=obj.getItemFull("itemcategory","ItemId","ItemName",cate2);
				String unitid=obj.getItemFull("unit_master","UnitID","UnitName",cate3);
				String unitsizeid=obj.getItemFull("unit_size_master","UnitSizeId","SizeName",cate4);
				ArrayList<String> al9=new ArrayList<String>();
				al9.add(catid);
				al9.add(itemid);
				al9.add(tdate.getText());
				al9.add(unitid);
				al9.add(unitsizeid);
				al9.add(topenq.getText().trim());
				al9.add(tcurrentq.getText());
				al9.add(treorderq.getText());
				al9.add(tprice.getText().trim());
				al9.add(teffectivep.getText());
				al9.add("Y");
				bsave.setEnabled(false);
				System.out.println(al9);
				if(obj.getCateoryName("item_stock","ItemId",itemid)==true)
				{
					JOptionPane.showMessageDialog(this, "record alreay there pls choose other");
					jitemchoose.removeAllItems();
					topenq.setText(pr.getProperty("blank"));
					treorderq.setText(pr.getProperty("blank"));
					tcurrentq.setText(pr.getProperty("blank"));
					tprice.setText(pr.getProperty("blank"));
					teffectivep.setText(pr.getProperty("blank"));
					bsave.setEnabled(true);
				}
				else if(obj.getCateoryName("item_stock","ItemId",itemid)==false)
				{
					String ste=obj.insert(c, "item_stock", al9);
				JOptionPane.showMessageDialog(getContentPane(), ste);
				
				}
				else if(topenq.getText().isEmpty() || tcurrentq.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Please fill the fields");
				}
				
				
			}
				
			}
		
		else if(ac.getSource()==badd)
		{
			
			jitemchoose.removeAll();
			junitcatechooser.removeAll();
			topenq.setText("");
			tprice.setText("");
			vprice.setText("");
			vopenq.setText("");
			treorderq.setText("");
			tcurrentq.setText("");
			teffectivep.setText("");
			bsave.setEnabled(true);
		}
		else if(ac.getSource()==bback)
		{
			new MenuList();
		}
		else if(ac.getSource()==bnavigation)
		{
			new Stock_Opening_Nav();
		}
			
		}
	
		
	}
		
	

	
		
	


