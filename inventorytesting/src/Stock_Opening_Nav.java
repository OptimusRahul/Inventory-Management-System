import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.util.CheckEntries;
import com.util.DataUtil;


public class Stock_Opening_Nav  extends JFrame implements ActionListener,FocusListener{
	JLabel  lhead,lcate,litem,lunit,lsubunit,lopen,lcurrent,lreorder,lprice,ldate,leffective,vopenq,vprice;
	JTextField tcate,titem,tunit,tsubunit,topen,tcurrent,treorder,tprice,tdate,teffective;
	Button bfirst,bnext,blast,bprev,bupdate,bdelete,bback,bsave;
	DataUtil obj;
	ResultSet rs;
	int status1,status2;
	Properties pr;
	FileReader fr;
	
	public Stock_Opening_Nav() {
		super("Stock Opening Navigation");
		setLayout(null);
		getLabels();
		lhead=new JLabel(pr.getProperty("itemnavigation"));
		Font fhead=new Font("Arial",Font.BOLD, 40);
		lhead.setFont(fhead);
		lhead.setBounds(600,50,400,40);
		add(lhead);
		Font lfont=new Font("Arial",Font.BOLD,20);
		lcate=new JLabel(pr.getProperty("cat_name"));
		lcate.setFont(lfont);
		lcate.setBounds(100,100,150,30);
		tcate=new JTextField();
		tcate.setBounds(280,100,200,30);
		litem=new JLabel(pr.getProperty("iname"));
		litem.setBounds(100,160,150,30);
		titem=new JTextField();
		titem.setBounds(280,160,200,30);
		lunit=new JLabel(pr.getProperty("ie_unit"));
		lunit.setBounds(100,220,150,30);
		tunit=new JTextField();
		tunit.setBounds(280,220,200,30);
		lsubunit=new JLabel(pr.getProperty("ie_sunit"));
		lsubunit.setBounds(100,280,150,30);
		tsubunit=new JTextField();
		tsubunit.setBounds(280,280,200,30);
		lopen=new JLabel(pr.getProperty("ie_oqun"));
		lopen.setBounds(100,340,150,30);
		topen=new JTextField();
		topen.setBounds(280,340,200,30);
		vopenq=new JLabel();
		vopenq.setBounds(500,340,120,30);
		add(vopenq);
		lcurrent=new JLabel(pr.getProperty("ie_cqun"));
		lcurrent.setBounds(100,400,150,30);
		tcurrent=new JTextField();
		tcurrent.setBounds(280,400,200,30);
		lreorder=new JLabel(pr.getProperty("ie_rqun"));
		lreorder.setBounds(100,460,150,30);
		treorder=new JTextField();
		treorder.setBounds(280,460,200,30);
		lprice=new JLabel(pr.getProperty("ie_ppunit"));
		lprice.setBounds(100,520,150,30);
		tprice=new JTextField();
		tprice.setBounds(280,520,200,30);
		vprice=new JLabel();
		vprice.setBounds(500,520,120,30);
		add(vprice);
		leffective=new JLabel(pr.getProperty("ie_effprice"));
		leffective.setBounds(100,580,150,30);
		add(leffective);
		leffective.setFont(lfont);
		teffective=new JTextField();
		teffective.setBounds(280,580,200,30);
		add(teffective);
		ldate=new JLabel(pr.getProperty("date"));
		ldate.setBounds(100,640,150,30);
		tdate=new JTextField();
		tdate.setBounds(280,640,200,30);
		bfirst=new Button(pr.getProperty("Btn_first"));
		bfirst.setBounds(560,480,120,30);
		add(bfirst);
		bfirst.setFont(lfont);
		bfirst.setBackground(Color.BLUE);
		bfirst.setForeground(Color.WHITE);
		bnext=new Button(pr.getProperty("Btn_next"));
		bnext.setBounds(720,480,120,30);
		add(bnext);
		bnext.setFont(lfont);
		bnext.setBackground(Color.cyan);
		bnext.setForeground(Color.WHITE);
		bprev=new Button(pr.getProperty("Btn_previous"));
		bprev.setBounds(880,480,120,30);
		add(bprev);
		bprev.setFont(lfont);
		bprev.setBackground(Color.LIGHT_GRAY);
		bprev.setForeground(Color.WHITE);
		blast=new Button(pr.getProperty("Btn_last"));
		blast.setBounds(1040,480,120,30);
		add(blast);
		blast.setFont(lfont);
		blast.setBackground(Color.pink);
		blast.setForeground(Color.WHITE);
		bupdate=new Button(pr.getProperty("Btn_update"));
		bupdate.setBounds(600,600,130,40);
		add(bupdate);
		bupdate.setFont(lfont);
		bupdate.setBackground(Color.GREEN);
		bupdate.setForeground(Color.WHITE);
		bdelete=new Button(pr.getProperty("Btn_delete"));
		bdelete.setBounds(780,600,130,40);
		add(bdelete);
		bdelete.setFont(lfont);
		bdelete.setBackground(Color.ORANGE);
		bdelete.setForeground(Color.WHITE);
		bback=new Button(pr.getProperty("Btn_back"));
		bback.setBounds(960,600,130,40);
		add(bback);
		bback.setFont(lfont);
		bback.setBackground(Color.MAGENTA);
		bback.setForeground(Color.WHITE);
		bback.addActionListener(this);
		bsave=new Button(pr.getProperty("Btn_save"));
		bsave.setBounds(1140,600,130,40);
		bsave.setFont(lfont);
		bsave.setBackground(Color.GREEN);
		bsave.setForeground(Color.WHITE);
		bsave.addActionListener(this);
		add(bsave);
		litem.setFont(lfont);
		lunit.setFont(lfont);
		lsubunit.setFont(lfont);
		lopen.setFont(lfont);
		lcurrent.setFont(lfont);
		lreorder.setFont(lfont);
		lprice.setFont(lfont);
		ldate.setFont(lfont);
		bfirst.addActionListener(this);
		bnext.addActionListener(this);
		bsave.setEnabled(false);
		tcate.setEditable(false);
		titem.setEditable(false);
		tunit.setEditable(false);
		tsubunit.setEditable(false);
		topen.setEditable(false);
		tcurrent.setEditable(false);
		treorder.setEditable(false);
		tprice.setEditable(false);
		tdate.setEditable(false);
		teffective.setEditable(false);
		blast.addActionListener(this);
		bprev.addActionListener(this);
		bupdate.addActionListener(this);
		bdelete.addActionListener(this);
		topen.addFocusListener(this);
		tprice.addFocusListener(this);
		add(lcate);add(tcate);add(litem);add(titem);add(lunit);add(tunit);add(lsubunit);add(tsubunit);add(lopen);add(topen);add(lcurrent);add(tcurrent);add(lreorder);add(treorder);add(lprice);add(tprice);add(ldate);add(tdate);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		setVisible(true);
		getFirst();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
}
	
	public static void main(String[] args) {
		new Stock_Opening_Nav();
	}
	public void getFirst()
	{
		try{
			DataUtil obj=new DataUtil();
		rs=obj.getResultsShow("item_stock");
		System.out.println(rs.toString());
		showData();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	public void showData()
	{try{
		obj=new DataUtil<>();
		String s1=rs.getString(1);
		String s2=rs.getString(2);
		String s3=rs.getString(4);
		String s4=rs.getString(5);
		String catename=obj.getItemFull("gst_slab", "Cat_Name", "Cat_ID", s1);
		String itemname=obj.getItemFull("itemcategory", "ItemName", "ItemId", s2);
		String unitname=obj.getItemFull("unit_master", "UnitName", "UnitID", s3);
		String subname=obj.getItemFull("unit_size_master", "SizeName", "UnitSizeId", s4);
		tcate.setText(catename);
		titem.setText(itemname);
		tunit.setText(unitname);
		tsubunit.setText(subname);
		topen.setText(rs.getString(6));
		tcurrent.setText(rs.getString(7));
		treorder.setText(rs.getString(8));
		tprice.setText(rs.getString(9));
		teffective.setText(rs.getString(10));
		tdate.setText(rs.getString(3));
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
	}
	public void actionPerformed(ActionEvent ae) {
	if(ae.getSource()==bfirst)
	{
		try{
		rs.first();
		showData();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	if(ae.getSource()==bnext)
	{
		try{
		rs.next();
		showData();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	else if(ae.getSource()==bprev)
	{
		try{
		rs.previous();
		showData();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	else if(ae.getSource()==blast)
	{

		try{
		rs.last();
		showData();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	else if(ae.getSource()==bupdate)
	{
		topen.setEditable(true);
		tprice.setEditable(true);
		bsave.setEnabled(true);
		bupdate.setEnabled(false);
	}
	else if(ae.getSource()==bsave)
	{
	if(status1==0 || status2==0)
	{
		JOptionPane.showMessageDialog(getContentPane(), "Please Complete Validation");
	}
	else if(status1==1 && status2==1)
	{
		obj=new DataUtil();
		String i1=topen.getText();
		String i2=tcurrent.getText();
		String i3=treorder.getText();
		String i4=tprice.getText();
		String i5=teffective.getText();
		String itemn=titem.getText();
		String itemid=obj.getItemFull("item_caremaster","ItemId","ItemName",itemn);// care master no available 
		String out=obj.updateitementries("item_stock",itemid,i1,i2,i3,i4,i5);
		System.out.println(" hello "+itemid);
		JOptionPane.showMessageDialog(getContentPane(), out);
		this.dispose();
		new Stock_Opening_Nav();
	}
	}
	else if(ae.getSource()==bdelete)
	{
		obj=new DataUtil<>();
		String itemn=titem.getText();
		Connection c=obj.getConnection();
		String itemid=obj.getItemFull("item_caremaster","ItemId","ItemName",itemn);
		System.out.println(itemid);
		String dele=obj.DeleteForAll(c, "item_stock","Status","ItemId", itemid,"N");
		JOptionPane.showMessageDialog(getContentPane(), dele);
	}
	else if(ae.getSource()==bback)
	{
		new Stock_Opening();
		this.dispose();
	}
}


	public void getLabels(){
		try{
			fr=new FileReader("AllLabels");
			pr=new Properties();
			pr.load(fr);
		}
		catch (Exception e) { e.toString();	
		}
	}

	public void focusGained(FocusEvent arg0) {
	}
		public void focusLost(FocusEvent ae) {
		CheckEntries ce=new CheckEntries();
		if(ae.getSource()==topen)
		{
			String c=topen.getText();
			boolean b=ce.CheckOpenAmount(c);
			if(b==false){
				status1=0;
				vopenq.setText("Invalid Amount");
				
			}
			else if(b==true){
				status1=1;
				vopenq.setText("");
			}
			tcurrent.setText(topen.getText());
			String op=topen.getText();
			int reo=Integer.parseInt(op);
			treorder.setText(String.valueOf(reo/10));
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
			teffective.setText(prc);
			
		}
		}

		
	}


