import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.text.DateFormat;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.util.CheckEntries;
import com.util.DataUtil;
import java.awt.event.*;
import java.io.FileReader;

public class Category_Entry extends JFrame implements ActionListener,FocusListener{
	JLabel jlhead,jlid,jlname,jlgst,ldate,vcate;
	JTextField jtid,jtname;
	JButton jbinsert,jbnavigation,jbcancel,Jback,jbnext;
	JComboBox jcombogst;
	String array[]={"0","5","12","18","28"};
	String s,pass="";
	DataUtil data; 
	Connection con;
	int status;
	FileReader fr;
	Properties pr;
	Font f,f1;
	public Category_Entry() {
		setTitle("Categoryofgoods");

		f=new Font("SERIF",Font.ITALIC,40);
	    f1=new Font("SERIF",Font.ITALIC,20);
		getLabels();
		jlhead=new JLabel(pr.getProperty("cog_head"));
		jlhead.setBounds(400, 120, 500, 40);
		
		jlhead.setForeground(Color.GRAY);
		add(jlhead);
		jcombogst=new JComboBox(array);
		
		ldate=new JLabel(DateFormat.getDateTimeInstance().format(new java.util.Date()));
		
		ldate.setForeground(Color.GRAY);
		ldate.setBounds(1100,20,200,50);
		
		add(ldate);

		jlid=new JLabel(pr.getProperty("cs_cat_id").toUpperCase());
		jlid.setBounds(400, 200, 200, 40);
		
		add(jlid);
		data=new DataUtil();
	    s=data.AllId("CAT","gst_slab","Cat_ID");
	    con=data.getConnection();
		jtid=new JTextField();
		jtid.setText(s);
		jtid.setBounds(610, 200, 150, 30);
		add(jtid);
		jtid.setEditable(false);
		
		jlname=new JLabel(pr.getProperty("fgc_cat_name").toUpperCase());
		jlname.setBounds(400, 250, 200, 40);
		add(jlname);
		
		jtname=new JTextField();
		jtname.setBounds(610, 250, 150, 30);
		jtname.setToolTipText("Category Name :");
		add(jtname);
		vcate=new JLabel("");
		vcate.setBounds(780,200,150,130);
		add(vcate);
		jtname.addFocusListener(this);
		jlgst=new JLabel(pr.getProperty("cat_gst").toUpperCase());
		jlgst.setBounds(400, 300, 210, 40);
		add(jlgst);
		
		
		jcombogst.setBounds(610, 300, 150, 30);
		//jtgst.setToolTipText("CATEGORY GST :");
		add(jcombogst);

		jbinsert=new JButton(pr.getProperty("Btn_insert"));
		jbinsert.setBounds(350, 400, 110, 35);
		add(jbinsert);
		
		jbnavigation=new JButton(pr.getProperty("Btn_navigate"));
		jbnavigation.setBounds(580, 400, 110, 35);
		add(jbnavigation);
		
		jbcancel=new JButton(pr.getProperty("Btn_cancel"));
		jbcancel.setBounds(830, 400, 110, 35);
		add(jbcancel);
		Jback=new JButton(pr.getProperty("Btn_back"));
		Jback.setBounds(430, 480, 110, 35);
		add(Jback);
		jbnext=new JButton(pr.getProperty("Btn_reset"));
		jbnext.setBounds(750, 480, 110, 35);
		add(jbnext);
		jbinsert.addActionListener(this);
		jbnavigation.addActionListener(this);
		jbcancel.addActionListener(this);
		Jback.addActionListener(this);
		jbnext.addActionListener(this);
		jlhead.setFont(f);
		jlid.setFont(f1);
		jlname.setFont(f1);
		jlgst.setFont(f1);ldate.setFont(f1);vcate.setFont(f1);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		   setSize(screenSize.width,screenSize.height);
		
	}
	public static void main(String[] args) {
		new Category_Entry();

	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbinsert)
		{
			if(status==0)
			{
				JOptionPane.showMessageDialog(getContentPane(), "Please complete Validation");
			}
			else if(status==1){
				status=0;
			String cid=jtid.getText();
			String cname=jtname.getText().trim();
			String cgst=(String) jcombogst.getSelectedItem();
			String msg=data.insert(con,"gst_slab",cid,cname,cgst,"Y");
			JOptionPane.showMessageDialog(this, msg);
			reset();
			jtid.setText(data.AllId("CAT","gst_slab","Cat_ID"));
			}
		}
		else if(e.getSource()==jbnavigation)
		{
		  
			this.hide();
			Category_Nav oops=new Category_Nav();
		}
		else if(e.getSource()==jbcancel)
		{
			  dispose();
		}
		else if(e.getSource()==Jback)
		{
			if(pass.equalsIgnoreCase("MOVE"))
			{
				new Supplier_Entry();
				this.dispose();
			}
			else
			{
			new MenuList();
			this.dispose();
			}
		}
		else if(e.getSource()==jbnext)
		{
			reset();
			jtid.setText(data.AllId("CAT","gst_slab","Cat_ID"));
		}
	}
	public void reset()
	
	{
	
		jtid.setText(" ");
		jtname.setText(" ");
		jcombogst.removeAll();
	}
	public void forword(String tt)
	{
		pass=tt;
	}
	@Override
	public void focusGained(FocusEvent e) {
	
		
	}
	public void focusLost(FocusEvent e) {
		 if(e.getSource()==jtname)
		{
			 CheckEntries ce=new CheckEntries();
			 String c=jtname.getText();
				boolean b=ce.CheckLetter(c);
				if(b==false ||c.equals(null)){
					vcate.setText("Invalid Entry");
					status=0;
				}
				else if(b==true ){
					status=1;
					vcate.setText(" ");
				}
				else if (data.getCateoryName("gst_slab","Cat_name",jtname.getText())==true)
				{
				JOptionPane.showMessageDialog(this, "record alreay there pls choose other");
				jtname.setText(" ");
				jtname.requestFocus();
			}
				 
		}
	}
	public void getLabels(){
		try{
			fr=new FileReader("AllLabels");
			pr=new Properties();
			pr.load(fr);
		}
		catch (Exception e) {
		}
	}
}