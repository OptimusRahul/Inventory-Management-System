import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.util.CheckEntries;
import com.util.DataUtil;

public class Item_Entry extends JFrame implements ActionListener,FocusListener {
	JLabel lhead,litemID,litemname,lcat_id,lcat_name,lgst_cat,ldate,vitem;
	JComboBox<String> jcb;
	JTextField txtitemname,txtitemID,txtcat_id,txtcat_name,txtgst_cat,txtdate;
	JButton jbsubmit,jbcancel,jbfind;
	Connection con;
	Statement st;
	ResultSet rs;
	DataUtil obj;
	int status;
	Properties pr;
	FileReader fr;	
	public Item_Entry()
	{
		setTitle("ITEM ENTRY");
		obj=new DataUtil();
		 con=obj.getConnection();
		getLabels();
		lhead=new JLabel("ITEM ENTRY");
		lhead.setBounds(525, 120, 200, 40);
		lhead.setFont(new Font("Arial", Font.BOLD, 30));
		lhead.setForeground(Color.GRAY);
		add(lhead);
		lcat_name=new JLabel(pr.getProperty("cat_name"));
		lcat_name.setBounds(500, 200, 200, 40);
		add(lcat_name);
		
		jcb=new JComboBox();
		jcb.setBounds(610, 200, 150, 30);
		jcb.setToolTipText("Enter Item");
		add(jcb);
		jcb.addActionListener(new ActionListener() {	
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							DataUtil obj = new DataUtil();
							Connection m = obj.getConnection();
							System.out.println(m.toString());
							st=m.createStatement();
							String que="select * from inventory.gst_slab where Cat_Name=? and Status='Y'";
							System.out.println(que);
							PreparedStatement pst3=m.prepareStatement(que);
							pst3.setString(1, (String)jcb.getSelectedItem());
							ResultSet rs1=pst3.executeQuery();
							while(rs1.next())
							{
								txtcat_id.setText(rs1.getString("Cat_Id"));
								txtcat_id.setEditable(false);
								txtgst_cat.setText(rs1.getString("Gst_No"));
								txtgst_cat.setEditable(false);
								txtdate.setEditable(false);
							}
							pst3.close();
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				});
		lcat_id=new JLabel(pr.getProperty("cat_id"));
		lcat_id.setBounds(500, 250, 200, 40);
		add(lcat_id);

		txtcat_id=new JTextField();
		txtcat_id.setBounds(610, 250, 150, 30);
		txtcat_id.setToolTipText("Category ID");
		txtcat_id.setEditable(false);
		add(txtcat_id);

		litemname=new JLabel(pr.getProperty("iname"));
		litemname.setBounds(500, 300, 200, 40);
		add(litemname);
		
		litemID=new JLabel("Item ID");
		litemID.setBounds(500, 350, 200, 40);
		add(litemID);
		txtitemID=new JTextField(obj.AllId("ITM", "itemcategory", "ItemId"));
		txtitemID.setEditable(false);
		txtitemID.setBounds(610, 350, 150, 30);
		txtitemID.setToolTipText("ITEM ID");
		add(txtitemID);

		txtitemname=new JTextField();
		txtitemname.setBounds(610, 300, 150, 30);
		txtitemname.setToolTipText("Select Category Name");
		add(txtitemname);
		txtitemname.addFocusListener(this);
		vitem=new JLabel("");
		vitem.setBounds(780,300,150,30);
		add(vitem);
		lgst_cat=new JLabel(pr.getProperty("cat_gst")+"%");
		lgst_cat.setBounds(480, 400, 210, 30);
		add(lgst_cat);
		ldate=new JLabel(pr.getProperty("date"));
		ldate.setBounds(500, 450, 200, 30);
		add(ldate);
		txtgst_cat=new JTextField();
		txtgst_cat.setBounds(610, 400, 150, 30);
		txtgst_cat.setEditable(false);
		add(txtgst_cat);
		txtdate=new JTextField();
		txtdate.setBounds(610, 450, 150, 30);
		txtdate.setEditable(false);
		add(txtdate);
        Date date1 = new Date();
    	GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(date1);
        String strDate = DateFormat.getDateTimeInstance().format(new java.util.Date());
	    txtdate.setText(strDate);
	    jbsubmit=new JButton(pr.getProperty("Btn_submit"));
		jbsubmit.setBounds(500, 500, 110, 35);
		add(jbsubmit);
		jbsubmit.addActionListener(this);
		Retrive();
		jbcancel=new JButton(pr.getProperty("Btn_cancel"));
		jbcancel.setBounds(640, 500, 110, 35);
		add(jbcancel);
		jbcancel.addActionListener(this);
		jbfind=new JButton(pr.getProperty("Btn_find"));
		jbfind.setBounds(550, 550, 150, 35);
		add(jbfind);
		jbfind.addActionListener(this);
		setLayout(null);
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	private void Retrive() {
		try
		{
			st=con.createStatement();
			String s="select * from inventory.gst_slab where status='Y'";
			rs=st.executeQuery(s);
			while(rs.next())
			{
				jcb.addItem(rs.getString(2));
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Wrong Details....");
		}
	}
	public static void main(String[] args) {
		new Item_Entry();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbsubmit)
		{
			if(status==0)
			{
				JOptionPane.showMessageDialog(getContentPane(), "Please Complete Validation");
			}
			else if(status==1){
			String in=txtitemname.getText().trim().toUpperCase();
			String cid=txtcat_id.getText();
			String cn=jcb.getSelectedItem().toString().trim().toUpperCase();
			String gstc=txtgst_cat.getText();
			String iid=txtitemID.getText();
			int gst=Integer.parseInt(gstc);
			
			String d1=txtdate.getText();
			
			if(obj.getCateoryName("itemcategory","ItemName",in)==true)
			{
				JOptionPane.showMessageDialog(this, "record alreay there pls choose other");
				txtitemname.setText(" ");
				txtitemname.requestFocus();
			}
			else
			{
			String f=obj.insert(con, "itemcategory",in,iid, cid,cn,gstc,d1,"Y");
			JOptionPane.showMessageDialog(getContentPane(),f);
			txtitemID.setText(obj.AllId("ITM", "itemcategory", "ItemId"));
			txtitemname.setText(" ");
			}
		}
		}
		else if(e.getSource()==jbfind)
		{
			setVisible(false);
			Category_Find obj2=new Category_Find();
			obj2.setVisible(true);
		}
		/*else if(e.getSource()==jbadd_cat)
		{
			setVisible(false);
			Categoryofgoods obj1=new Categoryofgoods();
			obj1.setVisible(true);
		}*/
		else if(e.getSource()==jbcancel)
		{
			new MenuList();
			dispose();
		}
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
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent ae) {
		if(ae.getSource()==txtitemname)
		{
			 CheckEntries ce=new CheckEntries();
			 String c=txtitemname.getText();
				boolean b=ce.CheckLetter(c);
				if(b==false){
					vitem.setText("Invalid Entry");
					status=0;
				}
				else if(b==true){
					status=1;
					vitem.setText(" ");
				}
			 if(c.equals(null))
					{
						vitem.setText("Invalid Entry");
						status=0;
					}
			}
		
		}
  }
