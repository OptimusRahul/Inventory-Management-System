import java.awt.Dimension;
import java.awt.Font;  
import java.awt.Toolkit;
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

public class Supplier_Nav extends JFrame implements ActionListener,FocusListener{
	JLabel shead,sname,sgst,span,saddress,scity,semail,scontect,scategory,sbank,sdate,sid,sinfo,saccountno,sifsc,jlname,jlcontact,jlemail,jlcity;
	JTextField tname,tgst,tpan,taddress,tcity,temail,tcontact,tcategory,tbank,tdate,tid,tbankacNO,tifsc,cocity,cocategory;
	JButton bfirst,blast,bprevious,bnext,back,bupdate,bdelete;
	ResultSet rs;
	DataUtil obj;
	Font fd;
	boolean boolname,boolcontact,boolemail;
	
	public Supplier_Nav() {
		obj=new DataUtil();
		
		shead=new JLabel("Supplier_Navigation");
		shead.setBounds(400,20,500,80);
		fd=new Font("Cooper",Font.BOLD,48);
		shead.setFont(fd);
		add(shead);
		
		sid=new JLabel("Supplier_ID");
		sid.setBounds(100,150,150,30);
		add(sid);
		
		tid=new JTextField();
		tid.setBounds(225,150, 150,30);
		add(tid);
		
		sdate=new JLabel("Date");
		sdate.setBounds(500,150,150,30);
		add(sdate);
		tdate=new JTextField();
		
		tdate.setBounds(625,150, 150,30);
		add(tdate);
		sname=new JLabel("Name");
		sname.setBounds(100,200,150,30);
		add(sname);
		
		tname=new JTextField();
		tname.setBounds(225,200, 150,30);
		add(tname);
		jlname=new JLabel("");
		jlname.setBounds(400,200,150,30);
		add(jlname);
		sgst=new JLabel("GST");
		sgst.setBounds(100,250,150,30);
		add(sgst);
		
		tgst=new JTextField();
		tgst.setBounds(225,250, 150,30);
		add(tgst);
		span=new JLabel("PAN/AADHAR");
		span.setBounds(100,300,150,30);
		add(span);
		
		tpan=new JTextField();
		tpan.setBounds(225,300, 150,30);
		add(tpan);
		saddress=new JLabel("Address");
		saddress.setBounds(100,350,150,30);
		add(saddress);
		
		taddress=new JTextField();
		taddress.setBounds(225,350, 150,30);
		add(taddress);
		
		scity=new JLabel("CITY");
		scity.setBounds(100,400,150,30);
		add(scity);
		
		tcity=new JTextField();
		tcity.setBounds(225,400, 150,30);
		add(tcity);
		
		semail=new JLabel("E-mail");
		semail.setBounds(100,450,150,30);
		add(semail);
		
		temail=new JTextField();
		temail.setBounds(225,450, 150,30);
		add(temail);
		jlemail=new JLabel("");
		jlemail.setBounds(400,450,150,30);
		add(jlemail);
		
		scontect=new JLabel("Contact");
		scontect.setBounds(500,200,150,30);
		add(scontect);
		
		tcontact=new JTextField();
		tcontact.setBounds(625,200, 150,30);
		add(tcontact);
		jlcontact=new JLabel("");
		jlcontact.setBounds(800,200,150,30);
		add(jlcontact);
		scategory=new JLabel("Category");
		scategory.setBounds(500,250,150,30);
		add(scategory);
		
		tcategory=new JTextField();
		tcategory.setBounds(625,250, 150,30);
		add(tcategory);
		
		sbank=new JLabel("Bank");
		sbank.setBounds(500,300,150,30);
		add(sbank);
		
		tbank=new JTextField();
		tbank.setBounds(625,300, 150,30);
		add(tbank);
		
		saccountno=new JLabel("Account No");
		saccountno.setBounds(500,350,150,30);
		add(saccountno);
		
		tbankacNO=new JTextField();
		tbankacNO.setBounds(625,350, 150,30);
		add(tbankacNO);
		
		sifsc=new JLabel("IFSC");
		sifsc.setBounds(500,400,150,30);
		add(sifsc);
		
		tifsc=new JTextField();
		tifsc.setBounds(625,400, 150,30);
		add(tifsc);
		
		bfirst=new JButton("FIRST");
		bfirst.setBounds(125,550, 100,40);
		add(bfirst);
		
		blast=new JButton("LAST");
		blast.setBounds(250,550, 100,40);
		add(blast);
		
		bprevious=new JButton("Previous");
		bprevious.setBounds(375,550, 100,40);
		add(bprevious);
		
		bnext=new JButton("NEXT");
		bnext.setBounds(500,550, 100,40);
		add(bnext);
		
		back=new JButton("BACK");
		back.setBounds(625,550, 100,40);
		add(back);
		
		bupdate=new JButton("UPDATE");
		bupdate.setBounds(300, 620, 100, 40);
		add(bupdate);
		
		bdelete=new JButton("DELETE");
		bdelete.setBounds(450, 620, 100,40);
		add(bdelete);
		bdelete.addActionListener(this);
		
		Editablefalse();
		Editabletrue();
		getFirst();
		tname.addFocusListener(this);
		tcontact.addFocusListener(this);
		temail.addFocusListener(this);
		bfirst.addActionListener(this);
		blast.addActionListener(this);
		bprevious.addActionListener(this);
		bnext.addActionListener(this);
		back.addActionListener(this);
		bupdate.addActionListener(this);
		setLayout(null);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Supplier_Nav();
	}
	public void getFirst()
	{
		
		rs=obj.getResultsSupplierInfo();
		showData();
	}
	public void showData() 
	{
		try{
			tid.setText(rs.getString(1));
			tname.setText(rs.getString(2));
			tgst.setText(rs.getString(3));
			tpan.setText(rs.getString(4));
			taddress.setText(rs.getString(5));
			tcity.setText(rs.getString(6));
			tcontact.setText(rs.getString(7));
			temail.setText(rs.getString(8));
			tcategory.setText(rs.getString(9));
			tdate.setText(rs.getString(11));
			tbank.setText(rs.getString(15));
			tbankacNO.setText(rs.getString(16));
			tifsc.setText(rs.getString(17));			
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
			 if((boolcontact||boolemail||boolname)==false)
			 	{
			 		JOptionPane.showMessageDialog(this,"Please fill correct Entry");
			 		
			 	}
			 	else
			 	{
			 try{
					
					Connection con=obj.getConnection();
					String dates=tdate.getText();
					String id=tid.getText();
					String d=obj.Update_SupplierData(con,tname.getText().trim(),tgst.getText().trim(),tpan.getText().trim(),
							 taddress.getText().trim(),tcity.getText().trim(),tcontact.getText().trim(),
							 temail.getText().trim(),tcategory.getText().trim(),tbank.getText().trim(),dates,id);
					String d1=obj.Update_BankData1(con,tname.getText().trim(),tbankacNO.getText().trim(),tbank.getText().trim(),tifsc.getText().trim(),id);
					JOptionPane.showMessageDialog(getContentPane(), d1);
					JOptionPane.showMessageDialog(getContentPane(), d);
				}catch(Exception e){e.toString();}
				
			}
			}
		 else if(ee.getSource()==bdelete)
		 {
				String msg=obj.Delete("supplier_info", "Supplier_Id", tid.getText());
				JOptionPane.showMessageDialog(getContentPane(), msg);
		 }
		 
			
		
		
	}
	public void Editablefalse()
	{
		tpan.setEditable(false);
		tid.setEditable(false);
		tdate.setEditable(false);
		tcategory.setEditable(false);
		tbank.setEditable(false);
		tbankacNO.setEditable(false);
		tifsc.setEditable(false);
		tgst.setEditable(false);
	}
	public void Editabletrue()
	{
		tname.setEditable(true);
		taddress.setEditable(true);
		tcity.setEditable(true);
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
			 boolname=obj.CheckLetter(text);
			if(boolname==false)
			{
				jlname.setText("Invalid Username");
				requestFocus();
			}
			else if(boolname==true)
			{
				jlname.setText("");
			
			}
			
		}
	
		if(e.getSource()==temail)
		{
			text=temail.getText();
			 boolemail=obj.CheckEmail(text);
			if(boolemail==false)
			{
				jlemail.setText("Invalid Email");
				requestFocus();
			}
			else if(boolemail==true)
			{
				jlemail.setText("");
			
			}
			
		}
		if(e.getSource()==tcontact)
		{
			text=tcontact.getText();
			 boolcontact=obj.CheckPhone(text);
			if(boolcontact==false)
			{
				jlcontact.setText("Invalid Phone_No");
				requestFocus();
			}
			else if(boolcontact==true)
			{
				jlcontact.setText("");
			
			}
			
		}
	}
}
