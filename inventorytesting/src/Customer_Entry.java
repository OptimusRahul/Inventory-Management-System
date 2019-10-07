
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import com.util.CheckEntries;
import com.util.DataUtil;
public class Customer_Entry extends JFrame implements ActionListener,FocusListener,KeyListener
{
	JLabel jladdress, jlHeading,jlCustomerID,jlCustomerName,jlCustomerPhoneNo,jlCustomerEmailID,jlCustomerType,jlCustomerGST,jlDate,validname,validphone,validemail,validgst;
	JTextField jtCustomerName,jtCustomerPhoneNo,jtCustomerEmailID,jtDate,jtCustomerGST;
	JTextArea jtaddress;
	JButton jbSave,  jbreset, jbback,jbfind,jbnavigate;
	int vsname=0, vsphone=0, vsemail=0, vsgst=0;
	JRadioButton jRetailer,jConsumer;
	ButtonGroup bg;
	Properties pr;
	ArrayList<String> list;
	DataUtil obj;
	Connection m;
	String retst;
	String label="UPDATE";
	CheckEntries valid;
	FileReader fr;
	
	 public Customer_Entry() 
	 {
		setLayout(null);
		obj=new DataUtil();
		m=obj.getConnection();
		jlHeading=new JLabel("Customer Entry");
		jlHeading.setFont(new Font("Impact", Font.BOLD, 40));
		jlHeading.setBounds(450, 20, 500, 60);
		add(jlHeading);
		jlCustomerName=new JLabel("Customer Name");
		jlCustomerName.setBounds(400, 140, 100, 30);
		add(jlCustomerName);
		validname=new JLabel("");
		validname.setBounds(760, 140, 200, 30);
		add(validname);
		jlCustomerPhoneNo=new JLabel("Phone No.");
		jlCustomerPhoneNo.setBounds(400, 180, 100, 30);
		add(jlCustomerPhoneNo);
		validphone=new JLabel("");
		validphone.setBounds(760, 180, 200, 30);
		add(validphone);
		jlCustomerEmailID=new JLabel("Email ID");
		jlCustomerEmailID.setBounds(400, 220, 100, 30);
		add(jlCustomerEmailID);
		validemail=new JLabel(" ");
		validemail.setBounds(760, 220, 100, 30);
		add(validemail);
		jlCustomerType=new JLabel("Customer Type");
		jlCustomerType.setBounds(400, 260, 100, 30);
		add(jlCustomerType);
		bg=new ButtonGroup();
		jRetailer=new JRadioButton("Retailer");
		jRetailer.addActionListener(this);
		jConsumer=new JRadioButton("Consumer");
		jConsumer.addActionListener(this);
		bg.add(jRetailer);
		bg.add(jConsumer);
		jRetailer.setBounds(600, 260, 100, 30);
		jConsumer.setBounds(700, 260, 100, 30);
		add(jRetailer);
		add(jConsumer);
		jlCustomerGST=new JLabel("Customer GST");
		jlCustomerGST.setBounds(800,260,100,30);
		add(jlCustomerGST);
		jlCustomerGST.setVisible(false);
		jlDate=new JLabel("Date");
		jlDate.setBounds(400, 300, 100, 30);
		add(jlDate);
		jladdress=new JLabel("Address");
		add(jladdress);
		jladdress.setBounds(400, 340, 100, 30);
		jtaddress=new JTextArea();
		add(jtaddress);
		jtaddress.setBounds(600, 340, 200, 50);
		validgst=new JLabel(" ");
		validgst.setBounds(1020,260,100,30);
		add(validgst);
		
		//TextFields
		
		jtCustomerName=new JTextField();
		jtCustomerName.setBounds(600, 140, 150, 30);
		add(jtCustomerName);
		jtCustomerName.addFocusListener(this);
		jtCustomerPhoneNo=new JTextField();
		jtCustomerPhoneNo.setBounds(600, 180, 150, 30);
		add(jtCustomerPhoneNo);
		jtCustomerPhoneNo.addFocusListener(this);
		jtCustomerEmailID=new JTextField();
		jtCustomerEmailID.setBounds(600, 220, 150, 30);
		add(jtCustomerEmailID);
		jtCustomerEmailID.addFocusListener(this);
		jtCustomerGST=new JTextField(15);
		jtCustomerGST.setBounds(910,260,100,30);
		add(jtCustomerGST);
		jtCustomerGST.addFocusListener(this);
		jtCustomerGST.setVisible(false);
		jtDate=new JTextField();
		jtDate.setBounds(600, 300, 150, 30);
		add(jtDate);
		Date date1 = new Date();
    	GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(date1);
        String strDate = calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
        jtDate.setText(strDate);
		
		//Button...
        jbfind=new JButton("Find Customer");
        jbfind.addActionListener(this);
        jbfind.setBounds(775, 140, 150, 35);
		add(jbfind);
		 jbnavigate=new JButton("Navigation");
	        jbnavigate.addActionListener(this);
	        jbnavigate.setBounds(625, 550, 150, 40);
			add(jbnavigate);
		jbSave=new JButton("SAVE");
		jbSave.addActionListener(this);
		jbSave.setBounds(580, 500, 100, 40);
		add(jbSave);
		jbreset=new JButton("Refresh");
		jbreset.addActionListener(this);
		jbreset.setBounds(460, 500, 100, 40);
		add(jbreset);
		
		jbback=new JButton("Back");
		jbback.addActionListener(this);
		jbback.setBounds(450, 550, 150, 40);
		add(jbback);
		jtCustomerName.addKeyListener(this);
		jtCustomerPhoneNo.addKeyListener(this);
		jtCustomerEmailID.addKeyListener(this);
		jtDate.addKeyListener(this);
		jtCustomerGST.addKeyListener(this);
		jRetailer.addKeyListener(this);
		jConsumer.addKeyListener(this);
		jtaddress.addKeyListener(this);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args)
	{
		
		new Customer_Entry();
	}
	public int CheckEmpty(){
		int stat;
		String name=jtCustomerName.getText();
		String phone=jtCustomerPhoneNo.getText();
		String mail=jtCustomerEmailID.getText();
		String gst=jtCustomerGST.getText();
		String address=jtaddress.getText();
		if(address.isEmpty()==true||name.isEmpty()==true||phone.isEmpty()==true||mail.isEmpty()==true||gst.isEmpty()==true){
			stat=0;
		}
		else{
			stat=1;
		}
		return stat;
	}
	public int CheckEmptyConsumer(){
		int stat;
		String name=jtCustomerName.getText();
		String phone=jtCustomerPhoneNo.getText();
		String mail=jtCustomerEmailID.getText();
		String gst=jtaddress.getText();
		if(name.isEmpty()==true||phone.isEmpty()==true||mail.isEmpty()==true||gst.isEmpty()==true){
			stat=0;
		}
		else{
			stat=1;
		}
		return stat;
	}
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jRetailer)
		{
			jtCustomerGST.setEditable(true);
			jlCustomerGST.setVisible(true);
			jtCustomerGST.setVisible(true);
		}
		else if(e.getSource()==jConsumer)
		{
			jlCustomerGST.setVisible(false);
			jtCustomerGST.setVisible(false);
		}
		else if(e.getSource()==jbSave)
		{
			int st=CheckEmpty();
			int st1=CheckEmptyConsumer();
			if(st1==0){
				JOptionPane.showMessageDialog(getContentPane(), "Fill all Entries");
			}
				if(jRetailer.isSelected()==true){
					if(st==0){
						JOptionPane.showMessageDialog(getContentPane(), "Fill all Entries");
					}
					if(vsname==1 && vsphone==1 && vsemail==1 && vsgst==1){
							list=new ArrayList<String>();
							list.add(jtCustomerName.getText());
							list.add(jtCustomerPhoneNo.getText());
							list.add(jtaddress.getText());
							list.add(jtCustomerEmailID.getText());
							list.add(jtCustomerGST.getText());
							list.add(jtDate.getText());
							list.add("Y");
							
							String t=obj.insert(m,"customer_info",list);
							JOptionPane.showMessageDialog(this,t);
							refresh();
							
						}
						else if(vsname==0 && vsphone==0 && vsemail==0 && vsgst==0){
							JOptionPane.showMessageDialog(this, "Validation Occur");
						}
				}
				if(jConsumer.isSelected()==true){
					if(vsname==1 && vsphone==1 && vsemail==1){
							list=new ArrayList<String>();
							list.add(jtCustomerName.getText());
							list.add(jtCustomerPhoneNo.getText());
							list.add(jtaddress.getText());
							list.add(jtCustomerEmailID.getText());
							list.add("N/A");
							list.add(jtDate.getText());
							list.add("Y");
							
							String t=obj.insert(m,"customer_info",list);
							JOptionPane.showMessageDialog(this,t);
							refresh();
						}
						else if(vsname==0 && vsphone==0 && vsemail==0){
							JOptionPane.showMessageDialog(this, "Validation Occur");
						}
					}
			}
		else if(e.getSource()==jbreset){
			refresh();
		}
			else if(e.getSource()==jbback){
				new MenuList();
				this.dispose();
				}
		else if(e.getSource()==jbfind)
		{
			new Customer_Detail();
			this.hide();
		}
		else if(e.getSource()==jbnavigate)
		{
			new Customer_Nav();
			this.hide();
		}
	}
	@Override
	public void focusGained(FocusEvent arg0) {
	}
	@Override
	public void focusLost(FocusEvent ar) {
		CheckEntries ck=new CheckEntries();
		String text;
		boolean b;
		if(ar.getSource()==jtCustomerGST){
			text=jtCustomerGST.getText();
			b=ck.CheckGST(text);
			if(b==false){
				vsgst=0;
				validgst.setText("Invalid GST");
			}
			else if(b==true){
				vsgst=1;
				validgst.setText(" ");
			}
		}
		else if(ar.getSource()==jtCustomerName){//return validation of Customername
			text=jtCustomerName.getText();
			b=ck.CheckLetter(text);
			if(b==true){
				vsname=1;
				validname.setText(" ");
			}
			else if(b==false){
				validname.setText("Only Letters are Allowed");
				vsname=0;
			}
		}
		else if(ar.getSource()==jtCustomerPhoneNo){	//return validation of phoneno
			text=jtCustomerPhoneNo.getText();
			b=ck.CheckPhone(text);
			if(b==false){
				validphone.setText("Only Digits are Allowed");
				vsphone=0;
			}
			else if(b==true){
				vsphone=1;
				validphone.setText("");
				
			}
		}
		if(ar.getSource()==jtCustomerEmailID)
		{
			text=jtCustomerEmailID.getText();
			b=ck.CheckEmail(text);
			if(b==false){
				vsemail=0;
				validemail.setText("Invalid Email");
			}
			else if(b==true){
				vsemail=1;
				validemail.setText(" ");
			}
		}
	}
	public void refresh(){
		jtCustomerName.setText("");
		jtCustomerPhoneNo.setText("");
		jtCustomerEmailID.setText("");
		jtDate.setText("");
		jtaddress.setText("");
		jtCustomerGST.setText("");
		jlCustomerGST.setVisible(false);
		jtCustomerGST.setVisible(false);
		jtCustomerName.requestFocus();
	}
	public void keyTyped(KeyEvent e) {
	}
	public void keyPressed(KeyEvent a) {
		if(a.getSource()==jtCustomerName && a.getKeyCode()==a.VK_ENTER)
			jtCustomerPhoneNo.requestFocus();
		else if(a.getSource()==jtCustomerPhoneNo && a.getKeyCode()==a.VK_ENTER)
			jtCustomerEmailID.requestFocus();
		else if(a.getSource()==jtCustomerEmailID && a.getKeyCode()==a.VK_ENTER)
			jRetailer.requestFocus();
		else if(a.getSource()==jRetailer && a.getKeyCode()==a.VK_ENTER)
			if(jRetailer.isSelected()==true)
				jtCustomerGST.requestFocus();
			else 
				jConsumer.requestFocus();
		else if((a.getSource()==jtCustomerGST || a.getSource()==jConsumer) && a.getKeyCode()==a.VK_ENTER)
			jtDate.requestFocus();
		else if(a.getSource()==jtDate && a.getKeyCode()==a.VK_ENTER)
			jtaddress.requestFocus();
		else if(a.getSource()==jtaddress && a.getKeyCode()==a.VK_ENTER)
			jbSave.requestFocus();
	}
	public void keyReleased(KeyEvent e) {
	}
	public void getlabel()
	{	try
		{
		fr=new FileReader("AllLabels");
		pr=new Properties();
		pr.load(fr);
	}
	catch(Exception ee){};
	}
}