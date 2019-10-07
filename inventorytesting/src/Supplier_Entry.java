
import java.awt.Dimension;
import java.awt.Font;
import java.awt.JobAttributes; 
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.*;
import javax.swing.*;

import java.util.Properties;
import java.io.FileReader;
import com.util.CheckEntries;
import com.util.DataUtil;

public class Supplier_Entry extends JFrame implements FocusListener,ActionListener,KeyListener{
	JLabel sname,sgst,span,saddress,scity,semail,scontect,scategory,sbank,sdate,sid,sinfo,saccountno,sifsc;
	JLabel vname,vgst,vpan,vaddress,vcity,vemail,vcontect,vcategory,vbank,vaccount,vifsc;
	JTextField tname,tgst,tpan,temail,tcontect,tbank,tdates,tid,tbankac,tifsc;
	JButton bsave,bback,bfind,badd,breset;
	JComboBox cocity,cocategory;
	JRadioButton rpan,raadhar;
	ButtonGroup bg;
	JCheckBox check;
	JTextArea jtaddress;
	String choose=" ";
	boolean value=true;
	Date d;
	SimpleDateFormat df;
	CheckEntries valid;
	DataUtil obj;
	FileReader fr;
	Properties pr;
	int hf;
	Font f,f1;
	String ta;
	
	boolean check1,check2,check3,check4,check6,check7,check8,check9;
	public Supplier_Entry() {
		setTitle("Supplier Entry");
		getLabels();
		valid=new CheckEntries();
		d=new Date();
		df=new SimpleDateFormat("yyyy-MM-dd");
		String ss=df.format(d);
		f=new Font("SERIF",Font.ITALIC,40);
	    f1=new Font("SERIF",Font.ITALIC,20);
		sinfo=new JLabel(pr.getProperty("shead"));
		sinfo.setBounds(500, 00, 400, 80);
		add(sinfo);
		sdate=new JLabel(pr.getProperty("date"));
		sdate.setBounds(50, 100, 100, 30);
		add(sdate);
		tdates=new JTextField();
		tdates.setBounds(200,100,150,30);
		add(tdates);
		tdates.setText(ss);
		tdates.setEditable(false);
		sid=new JLabel(pr.getProperty("sup_id"));
		sid.setBounds(50, 150, 100, 30);
		add(sid);
		obj=new DataUtil();
		tid=new JTextField();
		tid.setBounds(200, 150, 150, 30);
		add(tid);
		tid.setText(obj.AllId("SUP","supplier_info","Supplier_Id"));
		tid.setEditable(false);
		saccountno=new JLabel(pr.getProperty("bank_acno"));
		saccountno.setBounds(50, 500, 140, 30);
		add(saccountno);
		
		tbankac=new JTextField();
		tbankac.setBounds(200, 500, 150, 30);
		add(tbankac);
		tbankac.addFocusListener(this);
		
		sifsc=new JLabel(pr.getProperty("ifsc"));
		sifsc.setBounds(50, 550, 140, 30);
		add(sifsc);
		
		tifsc=new JTextField();
		tifsc.setBounds(200, 550, 150, 30);
		add(tifsc);
		tifsc.addFocusListener(this);
		
		sname=new JLabel(pr.getProperty("name"));
		sname.setBounds(400, 100, 150, 30);
		add(sname);
		tname=new JTextField();
		tname.setBounds(550, 100, 150, 30);
		add(tname);
		tname.addFocusListener(this);
		vname=new JLabel();
		vname.setBounds(700, 100, 150, 30);
		add(vname);
		sgst=new JLabel(pr.getProperty("gst"));
		sgst.setBounds(400, 150, 150, 30);
		add(sgst);
		tgst=new JTextField();
		tgst.setBounds(550, 150, 150, 30);
		add(tgst);
		tgst.addFocusListener(this);
		vgst=new JLabel();
		vgst.setBounds(700, 150, 150, 30);
		add(vgst);
		tgst.addFocusListener(this);
		span=new JLabel(pr.getProperty("pan"));
		span.setBounds(400, 200, 300, 30);
		add(span);
		bg=new ButtonGroup();
		rpan=new JRadioButton(pr.getProperty("pans"));
		raadhar=new JRadioButton(pr.getProperty("aadhar"));
		rpan.setBounds(550,200, 100, 30);
		raadhar.setBounds(680,200, 150, 30);
		bg.add(rpan);
		bg.add(raadhar);
		add(rpan);
		add(raadhar);
		rpan.addActionListener(this);
		raadhar.addActionListener(this);
		tpan=new JTextField();
		tpan.setBounds(855, 200, 150, 30);
		add(tpan);
		vpan=new JLabel();
		vpan.setBounds(760, 230, 150, 30);
		add(vpan);
		tpan.addFocusListener(this);
		saddress=new JLabel(pr.getProperty("address"));
		saddress.setBounds(400, 250, 150, 30);
		add(saddress);
		jtaddress=new JTextArea();
		jtaddress.setBounds(550, 250, 200, 60);
		add(jtaddress);
		vaddress=new JLabel();
		vaddress.setBounds(770, 280, 150, 30);
		add(vaddress);
		scity=new JLabel(pr.getProperty("city"));
		scity.setBounds(400, 350, 150, 30);
		add(scity);
		cocity=new JComboBox();
		cocity.addItem("AGRA");
		cocity.addItem("DELHI");
		cocity.addItem("KANPUR");
		cocity.addItem("JAIPUR");
		cocity.addItem("MUMBAI");
		cocity.addItem("CHENNAI");
		cocity.addItem("BANGLORE");
		cocity.addItem("AMRITSAR");
		cocity.setBounds(550,350, 150, 30);
		add(cocity);
		vcity=new JLabel();
		vcity.setBounds(700, 350, 150, 30);
		add(vcity);
		semail=new JLabel(pr.getProperty("email"));
		semail.setBounds(400,400, 150, 30);
		add(semail);
		temail=new JTextField();
		temail.setBounds(550,400,150,30);
		add(temail);
		temail.addFocusListener(this);
		vemail=new JLabel();
		vemail.setBounds(700, 400, 150, 30);
		add(vemail);
		scontect=new JLabel(pr.getProperty("contact"));
		scontect.setBounds(400,450, 150, 30);
		add(scontect);
		tcontect=new JTextField();
		tcontect.setBounds(550, 450, 150, 30);
		add(tcontect);
		vcontect=new JLabel();
		vcontect.setBounds(700, 450, 150, 30);
		add(vcontect);
		tcontect.addFocusListener(this);
		scategory=new JLabel(pr.getProperty("cat_name"));
		scategory.setBounds(400, 500, 150, 30);
		add(scategory);
		cocategory=new JComboBox();
		cocategory.setBounds(550, 500, 150, 30);
		add(cocategory);
		badd=new JButton(pr.getProperty("Btn_ADD"));
		badd.setBounds(730, 500, 100, 30);
		add(badd);
		badd.addActionListener(this);
		vcategory=new JLabel();
		vcategory.setBounds(700, 500, 150, 30);
		add(vcategory);
		sbank=new JLabel(pr.getProperty("bank_name"));
		sbank.setBounds(400, 550, 150, 30);
		add(sbank);
		tbank=new JTextField();
		tbank.setBounds(550,550,150,30);
		add(tbank);
		tbank.addFocusListener(this);
		vbank=new JLabel();
		vbank.setBounds(700, 550, 150, 30);
		add(vbank);
		check=new  JCheckBox(pr.getProperty("check"));
		check.setBounds(400,600,500,25);
		add(check);
		check.addActionListener(this);
		bsave=new JButton(pr.getProperty("Btn_save"));
		bsave.setBounds(400, 640, 120, 40);
		add(bsave);
		bsave.setEnabled(false);
		bsave.addActionListener(this);
		bback=new JButton(pr.getProperty("Btn_cancel"));
		bback.setBounds(550,640, 120, 40);
		add(bback);
		bback.addActionListener(this);
		breset=new JButton(pr.getProperty("Btn_reset"));
		breset.setBounds(700,640, 120, 40);
		add(breset);
		bfind=new JButton(pr.getProperty("Btn_find"));
		bfind.setBounds(850,100, 120, 40);
		add(bfind);
		sname.setFont(f1);
		sgst.setFont(f1);
		span.setFont(f1);
		saddress.setFont(f1);
		scity.setFont(f1);
		semail.setFont(f1);
		scontect.setFont(f1);
		scategory.setFont(f1);
		sbank.setFont(f1);
		sdate.setFont(f1);
		sid.setFont(f1);
		sinfo.setFont(f);
		saccountno.setFont(f1);
		sifsc.setFont(f1);
		rpan.setFont(f1);
		raadhar.setFont(f1);
		check.setFont(f1);
		bfind.addActionListener(this);
		breset.addActionListener(this);
		tname.addKeyListener(this);
		tgst.addKeyListener(this);
		tpan.addKeyListener(this);
		raadhar.addKeyListener(this);
		rpan.addKeyListener(this);
		tpan.addKeyListener(this);
		jtaddress.addKeyListener(this);
		cocity.addKeyListener(this);
		temail.addKeyListener(this);
		tcontect.addKeyListener(this);
		cocategory.addKeyListener(this);
		tbank.addKeyListener(this);
		tbankac.addKeyListener(this);
		tifsc.addKeyListener(this);
		forcombo();
		setLayout(null);
		setVisible(true);
		getadddata();
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		Supplier_Entry obj=new Supplier_Entry();
	}
	public void focusGained(FocusEvent e) {
	}
	public void focusLost(FocusEvent arg0) {
		if(arg0.getSource()==temail)
		{
			check1=valid.CheckEmail(temail.getText());
			System.out.println("email-"+check1);
			if(check1==false)
			{
					vemail.setText(pr.getProperty("xemail"));
			}
			else
			{
				vemail.setVisible(false);
			}
			}
		else if(arg0.getSource()==tcontect)
		{
			check2=valid.CheckPhone(tcontect.getText());
			System.out.println("mobile-"+check2);
			if(check2==false)
			{
				vcontect.setText(pr.getProperty("xphone"));
			}
			else
			{
				vcontect.setVisible(false);
			}
			}
		else if(arg0.getSource()==tbank) 
		{
			check3=valid.CheckLetter(tbank.getText());
			System.out.println("bank name-"+check3);
			if(check3==false)
			{
				vbank.setText(pr.getProperty("onlttr"));
			}
			else
			{
				vbank.setVisible(false);
			}
			}
		else if(arg0.getSource()==tgst)
		{
			check4=valid.CheckGST(tgst.getText());
			System.out.println("gst-"+check4);
			if(check4==false)
			{
				vgst.setText(pr.getProperty("xgstno"));
			}
			else
			{	
				vgst.setVisible(false);
				if (obj.getCateoryName("supplier_info","Supplier_GST",tgst.getText())==true)
				{
					JOptionPane.showMessageDialog(this, "record alreay there pls choose other");
					tgst.setText(" ");
					tgst.requestFocus();
					
				}
			}
			
		}
		else if((arg0.getSource()==tpan)&&(choose.equals("PAN")))
		{
			check6=valid.CheckPAN(tpan.getText());
			System.out.println("pan no-"+check6);
			if(check6==false)
			{
				vpan.setText(pr.getProperty("xpan"));	
			}
			else
			{
				vpan.setVisible(false);
			}
		}
		else if((arg0.getSource()==tpan)&&(choose.equals("AADHAR")))
		{
			check6=valid.CheckAadhaar(tpan.getText());
			System.out.println("AAdhar-"+check6);
			if(check6==false)
			{
				vpan.setText(pr.getProperty("xaadhaar"));
			}
				else
			{
				vpan.setVisible(false);
			}	
			}
		else if(arg0.getSource()==tname)
		{
			check7=valid.CheckLetter(tname.getText());
			System.out.println("name-"+check7);
			if(check7==false)
			{
				vname.setText(pr.getProperty("onlttr"));
			}
				else
			{
				vname.setVisible(false);
			}}
		if(arg0.getSource()==tbankac)
		{
			check8=valid.CheckBank(tbankac.getText());
			System.out.println("Account no-"+check8);
			if(check8==false)
			{
				JOptionPane.showMessageDialog(this,pr.getProperty("xaccount"));
				tbankac.requestFocus();
			}
				else
			{	
				tifsc.requestFocus();
			}
		}
		if(arg0.getSource()==tifsc)
		{
			check9=valid.CheckIfsc(tifsc.getText());
			System.out.println("IFSC-"+check9);
			if(check9==false)
			{
				JOptionPane.showMessageDialog(this, pr.getProperty("xifsc"));
				tifsc.requestFocus();
			}
				else 
			{
				check.requestFocus();
			}}
	}
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==rpan)
		{
			choose="PAN";
		}
		else if(arg0.getSource()==raadhar)
		{
			choose="AADHAR";
		}
		else if(arg0.getSource()==bsave)
		{
			 if(check1==false||check2==false||check3==false||check4==false||check6==false||check7==false||check8==false||check9==false)
			{
				JOptionPane.showMessageDialog(this, "Invalid Data Cannot Insert");
			}
			else 
			{
			 Connection con=obj.getConnection();
			 String catid=obj.getItemFull("gst_slab","Cat_ID","Cat_Name",cocategory.getSelectedItem().toString());
			 String data[]={tid.getText().trim(),tname.getText().trim(),tgst.getText().trim(),tpan.getText().trim(),
					 jtaddress.getText().trim(),cocity.getSelectedItem().toString(),tcontect.getText().trim()
					 ,temail.getText().trim().toLowerCase(),catid,tbank.getText().trim()};
			String dates=tdates.getText();
			String BankData[]={tid.getText().trim(),tname.getText().trim(),tbank.getText().trim(),tbankac.getText().trim(),
					tifsc.getText().trim()};
			String d=obj.SupplierData(con,"supplier_info",data,dates);
			String d1=obj.BankInfo(con,"bank_info",BankData);
			JOptionPane.showMessageDialog(this, d1);
			 resetdata();
			 tid.setText(obj.AllId("SUP","supplier_info","Supplier_Id"));
			 check1=check2=check3=check4=check6=check7=check8=check9=false;
			 check.setSelected(false);
			 bsave.setEnabled(false);
			}	 
		}
		else if(arg0.getSource()==bfind)
		{
			try{
				System.out.println("I am Find");
				obj=new DataUtil();
				String xcv=tname.getText();
				Connection con=obj.getConnection();
				ArrayList al3=obj.FindData(con,"supplier_info",xcv,"Supplier_Name");
				if(al3.isEmpty())
				{
					JOptionPane.showMessageDialog(getContentPane(), "DATA NOT FOUND");					
				}
				else{
				System.out.print(al3);
				tid.setText((String)al3.get(0).toString());
				tname.setText((String)al3.get(1).toString());
				tgst.setText((String)al3.get(2).toString());
				tpan.setText((String)al3.get(3).toString());
				jtaddress.setText((String)al3.get(4).toString());
				cocity.setSelectedItem((String)al3.get(5).toString());
				tcontect.setText((String)al3.get(6).toString());
				temail.setText((String)al3.get(7).toString());
				cocategory.setSelectedItem((String)al3.get(8).toString());
				tdates.setText((String)al3.get(10).toString());
				ArrayList al4=obj.FindData(con,"bank_info",xcv,"Supplier_Name");
				tbankac.setText((String)al4.get(2).toString());
				tbank.setText((String)al4.get(3).toString());
				tifsc.setText((String)al4.get(4).toString());
				Editablefalse();
			}
			}
			catch(Exception e)
			{
				e.toString();
			}			
		}
		else if(arg0.getSource()==badd)
		{
			this.dispose();
			Category_Entry obj=new Category_Entry();
			obj.forword("MOVE");
			
		}
		else if(arg0.getSource()==breset)
		{
			resetdata();
			Editabletrue();
		}
		else if(arg0.getSource()==check)
		{
			if(value==true)
			{
				value=false;
				String expression="^";
				Pattern p=Pattern.compile(expression);
				Matcher m=p.matcher(tname.getText());
				Matcher m1=p.matcher(tid.getText());
				Matcher m2=p.matcher(tpan.getText());
				Matcher m3=p.matcher(temail.getText());
				Matcher m4=p.matcher(jtaddress.getText());
				Matcher m5=p.matcher(tgst.getText());
				Matcher m6=p.matcher(tcontect.getText());
				Matcher m7=p.matcher(tbank.getText());
				Matcher m8=p.matcher(tbankac.getText());
				Matcher m9=p.matcher(tifsc.getText());
				if(m.matches()|| m1.matches()|| m2.matches()|| m3.matches()|| m4.matches()|| m5.matches()|| m6.matches()||m7.matches()||m8.matches()||m9.matches())
				{
					JOptionPane.showMessageDialog(this,"Please Fill required field...");
					check.setSelected(false);value=true;
				}
				else{
					bsave.setEnabled(true);}
			}
			else if(value==false){
				bsave.setEnabled(false);
				value=true;
			}
		}
		else if(arg0.getSource()==bback){
			new MenuList();
			this.dispose();
		}
	}
	private void resetdata() {
		tid.setText(obj.AllId("SUP","supplier_info","Supplier_Id"));
		tname.setText("");
		tgst.setText("");
		tpan.setText("");
		jtaddress.setText("");
		cocity.setSelectedItem("");
		temail.setText("");
		tcontect.setText("");
		cocategory.setSelectedItem("");
		tbank.setText("");
		tbankac.setText("");
		tifsc.setText("");
	}
	public void keyPressed(KeyEvent a) {
		if(a.getSource()==tname && a.getKeyCode()==a.VK_ENTER)
			tgst.requestFocus();
		else if(a.getSource()==tgst && a.getKeyCode()==a.VK_ENTER)
			rpan.requestFocus();
		else if(a.getSource()==rpan && a.getKeyCode()==a.VK_ENTER)
			raadhar.requestFocus();
		else if(a.getSource()==raadhar && a.getKeyCode()==a.VK_ENTER)
			tpan.requestFocus();
		else if(a.getSource()==tpan && a.getKeyCode()==a.VK_ENTER)
			jtaddress.requestFocus();
		else if(a.getSource()==jtaddress && a.getKeyCode()==a.VK_ENTER)
			cocity.requestFocus();
		else if(a.getSource()==cocity && a.getKeyCode()==a.VK_ENTER)
			temail.requestFocus();
		else if(a.getSource()==temail && a.getKeyCode()==a.VK_ENTER)
			tcontect.requestFocus();
		else if(a.getSource()==tcontect && a.getKeyCode()==a.VK_ENTER)
			cocategory.requestFocus();
		else if(a.getSource()==cocategory && a.getKeyCode()==a.VK_ENTER)
			tbank.requestFocus();
		else if(a.getSource()==tbank && a.getKeyCode()==a.VK_ENTER)
			tbankac.requestFocus();
		else if(a.getSource()==tbankac && a.getKeyCode()==a.VK_ENTER)
			tifsc.requestFocus();
		else if(a.getSource()==tifsc && a.getKeyCode()==a.VK_ENTER)
			check.requestFocus();	
	}
	public void keyReleased(KeyEvent arg0) {	
	}
	public void keyTyped(KeyEvent arg0) {		
	}
	public void forcombo(){
		try
		{
			obj = new DataUtil();
			ArrayList<String> al7=obj.getCategory("gst_slab", "Cat_Name","Y");
			System.out.println(al7);
			for(int i=0;i<al7.size();i++)
			{
				cocategory.addItem(al7.get(i));
			}
		}
		catch(Exception e)
		{
		JOptionPane.showMessageDialog(null, "Wrong Details....");
		}
	}
	public Properties getLabels(){
		try{
			 fr=new FileReader("AllLabels");
			 pr=new Properties();
			pr.load(fr);
			System.out.println(pr);
		}
		catch(Exception e){}
		return pr;
	}
	public void Editablefalse()
	{
		tname.setEditable(false);
		tgst.setEditable(false);
		tpan.setEditable(false);
		jtaddress.setEnabled(false);
		cocity.setEditable(false);
		temail.setEditable(false);
		tcontect.setEditable(false);
		cocategory.setEditable(false);
		tbank.setEditable(false);
		tbankac.setEditable(false);
		tifsc.setEditable(false);
	}
	public void getadddata()
	{
		if(hf==1)
		{
			tname.setText(ta);
		}
	}
	public void Editabletrue()
	{
		tname.setEditable(true);
		tgst.setEditable(true);
		tpan.setEditable(true);
		jtaddress.setEnabled(true);
		cocity.setEditable(true);
		temail.setEditable(true);
		tcontect.setEditable(true);
		cocategory.setEditable(true);
		tbank.setEditable(true);
		tbankac.setEditable(true);
		tifsc.setEditable(true);
	}
}
