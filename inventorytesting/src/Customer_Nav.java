import java.awt.Font;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.util.CheckEntries;
import com.util.DataUtil;

public class Customer_Nav extends JFrame implements ActionListener,FocusListener{
	JLabel shead,sname,sgst,saddress,scity,semail,scontect,sdate,sid,status,jlname,jladdress,jlcontact,jlemail;
	JTextField tname,tgst,taddress,temail,tcontact,tstatus,tdate,tid;
	JButton bfirst,blast,bprevious,bnext,back,bupdate,bdelete;
	ResultSet rs;
	DataUtil obj;
	Font fd;
	boolean boolName,boolEmail,boolContact;
	
	public Customer_Nav() {

		obj=new DataUtil();
		shead=new JLabel("Customer_Navgation");
		shead.setBounds(400,20,600,80);
		fd=new Font("Cooper",Font.BOLD,48);
		shead.setFont(fd);
		add(shead);
		
		sdate=new JLabel("Date");
		sdate.setBounds(600,150,150,30);
		add(sdate);
		tdate=new JTextField();
		tdate.setBounds(725,150, 150,30);
		add(tdate);
		sname=new JLabel("Name");
		sname.setBounds(100,150,150,30);
		add(sname);
		
		tname=new JTextField();
		tname.setBounds(225,150, 150,30);
		add(tname);
		tname.addFocusListener(this);
		jlname=new JLabel("");
		jlname.setBounds(400,150,150,30);
		add(jlname);
		sgst=new JLabel("GST");
		sgst.setBounds(600,225,150,30);
		add(sgst);
		
		tgst=new JTextField();
		tgst.setBounds(725,225, 150,30);
		add(tgst);
	
		saddress=new JLabel("Address");
		saddress.setBounds(100,225,150,30);
		add(saddress);
		
		taddress=new JTextField();
		taddress.setBounds(225,225, 150,30);
		add(taddress);
		taddress.addFocusListener(this);
		jladdress=new JLabel("");
		jladdress.setBounds(400,225,150,30);
		add(saddress);
	
		
		semail=new JLabel("E-mail");
		semail.setBounds(600,300,150,30);
		add(semail);
		jlemail=new JLabel("");
		jlemail.setBounds(800,300,150,30);
		add(jlemail);
		
		temail=new JTextField();
		temail.setBounds(725,300, 150,30);
		add(temail);
		temail.addFocusListener(this);
		
	
		scontect=new JLabel("Contact");
		scontect.setBounds(100,300,150,30);
		add(scontect);
		
		tcontact=new JTextField();
		tcontact.setBounds(225,300, 150,30);
		add(tcontact);
		tcontact.addFocusListener(this);
		jlcontact=new JLabel("");
		jlcontact.setBounds(400,300,150,30);
		add(jlcontact);
		
		status=new JLabel("Status");
		status.setBounds(600,375,150,30);
		add(status);
		
		tstatus=new JTextField();
		tstatus.setBounds(725,375, 150,30);
		add(tstatus);
		
		bfirst=new JButton("FIRST");
		bfirst.setBounds(140,500, 100,40);
		add(bfirst);
		
		blast=new JButton("LAST");
		blast.setBounds(280,500, 100,40);
		add(blast);
		
		bprevious=new JButton("Previous");
		bprevious.setBounds(420,500, 100,40);
		add(bprevious);
		
		bnext=new JButton("NEXT");
		bnext.setBounds(560,500, 100,40);
		add(bnext);
		
		back=new JButton("BACK");
		back.setBounds(700,500, 100,40);
		add(back);
		
		bupdate=new JButton("UPDATE");
		bupdate.setBounds(300, 575, 100, 40);
		add(bupdate);
		
		bdelete=new JButton("DELETE");
		bdelete.setBounds(450, 575, 100,40);
		add(bdelete);
		bdelete.addActionListener(this);
		
		
		getFirst();
		Editablefalse();
		Editabletrue();
		bfirst.addActionListener(this);
		blast.addActionListener(this);
		bprevious.addActionListener(this);
		bnext.addActionListener(this);
		back.addActionListener(this);
		bupdate.addActionListener(this);
		setLayout(null);
		setSize(1000,1000);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Customer_Nav();
	}
	public void getFirst()
	{
		
		rs=obj.getResultsCustomer_Info();
		showData();
	}
	public void showData() 
	{
		try{
			
			tname.setText(rs.getString(1));
			tcontact.setText(rs.getString(2));
			taddress.setText(rs.getString(3));
			temail.setText(rs.getString(4));
			tgst.setText(rs.getString(5));
			tdate.setText(rs.getString(6));
			tstatus.setText(rs.getString(7));		
		}
		catch(Exception e){System.out.println(e.toString());}
	}
	@Override
	public void actionPerformed(ActionEvent ee) {
		try{
			if(ee.getSource()==bnext)
			{
				rs.next();
				showData();
			}
			else if(ee.getSource()==bprevious)
			{
				rs.previous();
				showData();
			}
			else if(ee.getSource()==blast)
			{
				rs.last();
				showData();
			}
			if(ee.getSource()==bfirst)
			{
				rs.first();
				showData();
			}
		}
		catch (Exception e) {
				System.out.println(e.toString());
		}
			if(ee.getSource()==back)
			{
				new MenuList();
				this.dispose();
				
			}
		 if(ee.getSource()==bupdate)
		 {	
		 	if((boolContact || boolEmail || boolName)==false)
		 	{
		 		JOptionPane.showMessageDialog(this,"Please fill correct Entry");
		 		
		 	}
		 	else
		 	{
			 try{
					
					Connection con=obj.getConnection();
					String dates=tdate.getText();
					String id=tid.getText();
					String d=obj.Update_CustomerData(con,tname.getText().trim(),taddress.getText().trim(),tcontact.getText().trim(),temail.getText().trim(),id);
						JOptionPane.showMessageDialog(getContentPane(), d);
				}catch(Exception e){e.toString();}
				
			}
		 }
		 else if(ee.getSource()==bdelete)
		 {
				String msg=obj.Delete("customer_info", "CustomerPhone", tcontact.getText());
				JOptionPane.showMessageDialog(getContentPane(), msg);
		 }
		 
	 }	
		
		
	
	public void Editablefalse()
	{
		

		tdate.setEditable(false);
		tgst.setEditable(false);
		tstatus.setEditable(false);
		
	}
	public void Editabletrue()
	{
		tname.setEditable(true);
		taddress.setEditable(true);
		temail.setEditable(true);
		tcontact.setEditable(true);
		
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		String text;
		CheckEntries obj=new CheckEntries();
		if(e.getSource()==tname)
		{
			text=tname.getText();
			 boolName=obj.CheckLetter(text);
			if(boolName==false)
			{
				jlname.setText("Invalid Username");
				requestFocus();
			}
			else if(boolName==true)
			{
				jlname.setText("");
			
			}
			
		}
		if(e.getSource()==tcontact)
		{
			text=tcontact.getText();
			boolContact=obj.CheckPhone(text);
			if(boolContact==false)
			{
				jlcontact.setText("Invalid Phone_No");
				requestFocus();
			}
			else if(boolContact==true)
			{
				jlcontact.setText("");
			
			}
			
		}
		if(e.getSource()==temail)
		{
			text=temail.getText();
			 boolEmail=obj.CheckEmail(text);
			if(boolEmail==false)
			{
				jlemail.setText("Invalid Email_id");
				requestFocus();
			}
			else if(boolEmail==true)
			{
				jlemail.setText("");
			
			}
			
		}
		
	}
	
}
