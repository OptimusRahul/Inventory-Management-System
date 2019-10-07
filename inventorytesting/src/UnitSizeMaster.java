import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.util.CheckEntries;
import com.util.DataUtil;

public class UnitSizeMaster extends JFrame implements ActionListener,FocusListener{
JLabel Junitmaster,Junitsizeid,Jluid,Jlsname,Jldescription,vsize_name;
JTextField Jtunitsizeid,Jtuid,Jtname,Jtdescription;
JButton Jbinsert,Jbnew,Jback;
JComboBox<String> jcunitname;
Font f1,f2;
String s;
DataUtil data;
ResultSet rs;
CheckEntries ce;
int status;
FileReader fr;
Properties pr;
	public UnitSizeMaster(){
		data=new DataUtil();
		getLabels();
		Font f1=new Font("Cooper", Font.BOLD,20);
		Font f2=new Font("Elephant", Font.BOLD, 25);
		Junitmaster=new JLabel(pr.getProperty("usm_head"));
		Junitsizeid=new JLabel(pr.getProperty("usm_id"));
		Jtunitsizeid=new JTextField();
		Jtunitsizeid.setText(data.AllId("USM", "unit_size_master", "UnitSizeID"));
		Jtunitsizeid.setEditable(false);
		Jluid=new JLabel(pr.getProperty("usm_name"));
		Jtuid=new JTextField();
		Jlsname=new JLabel(pr.getProperty("usm_sname"));
		Jtname=new JTextField();
		Jldescription=new JLabel(pr.getProperty("usm_des"));
		Jtdescription=new JTextField();
		Jbinsert=new JButton(pr.getProperty("Btn_insert"));
		
		Jbnew=new JButton(pr.getProperty("Btn_new"));
		Jback=new JButton(pr.getProperty("Btn_back"));
		setLayout(null);
	    Junitmaster.setBounds(560,40,250,100);
		Junitsizeid.setBounds(500,170,200,40);
		Jtunitsizeid.setBounds(650, 170,200,40);
		//Jtunitsizeid.setText(s);
		Jluid.setBounds(500,220,200,40);
		
		Jlsname.setBounds(500,270,200,40);
		vsize_name=new JLabel();
		vsize_name.setBounds(880,270,200,40);
		add(vsize_name);
	
		Jtname.setBounds(650,270,200,40);
	
		Jldescription.setBounds(500,320,200,40);
		Jtdescription.setBounds(650,320,200,40);
		Jbinsert.setBounds(410,420,150,50);
		Jbinsert.addActionListener(this);
		Jback.setBounds(580, 420, 150, 50);
		Jback.addActionListener(this);
		Jbnew.setBounds(750, 420, 150, 50);
		Jbnew.addActionListener(this);
		
		add(Junitmaster);
		add(Junitsizeid);
		add(Jtunitsizeid);
		add(Jluid);
		add(Jtuid);
		add(Jlsname);
		add(Jtname);
	    add(Jldescription);
		add(Jtdescription);
		add(Jbinsert);
		add(Jbnew);
		add(Jback);
		Junitmaster.setFont(f2);
		Junitsizeid.setFont(f1);
		Jluid.setFont(f1);
		Jlsname.setFont(f1);
		Jldescription.setFont(f1);
		jcunitname=new JComboBox<String>();
		jcunitname.setBounds(650,220,200,40);
		data=new DataUtil();
		ArrayList<String> ar=new ArrayList<String>();
		ar.addAll(data.getCategory("unit_master", "UnitName","Y"));
		for(int i=0;i<ar.size();i++){
			jcunitname.addItem(ar.get(i));
		}
		Jtname.addFocusListener(this);
		add(jcunitname);
		getFirst();
		setSize(1360,750);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void getFirst()
	{
	rs=data.getResultsShow("unit_size_master");
	UnitShowData();
	}
	public static void main(String[] args) {
		new UnitSizeMaster();
	}
	public void UnitShowData() 
	{
		
		try{
		Jtunitsizeid.setText(rs.getString(2));
		Jtname.setText(rs.getString(3));
		Jtdescription.setText(rs.getString(4));
		}
		catch(Exception e){System.out.println(e.toString());}
	}
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==Jbnew)
		{
			Resetdata();
		}
		if(ae.getSource()==Jbinsert){
			if(status==0)
			{
				JOptionPane.showMessageDialog(getContentPane(),"Please Complete Validation");
			}
			else if(status==1){
			data=new DataUtil();
			String id=data.getItemFull("unit_master", "UnitID", "UnitName", (String) jcunitname.getSelectedItem());
			Connection c=data.getConnection();
			String msg=data.insert(c, "unit_size_master",id,Jtunitsizeid.getText(), Jtname.getText(),Jtdescription.getText(),"Y");
			JOptionPane.showMessageDialog(getContentPane(), msg);
			Resetdata();
			}
		}
		
		else if(ae.getSource()==Jback){
			this.dispose();
			new MenuList();
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
	private void Resetdata() {
		Jtunitsizeid.setText(data.AllId("USM", "Unit_Size_Master", "UnitSizeID"));
		Jtname.setText("");
		Jtdescription.setText("");
	}
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void focusLost(FocusEvent ae) {
		if(Jtname.getText().equals(""))
		{
			System.out.println("hello");
			status=0;
			JOptionPane.showMessageDialog(getContentPane(),"Please filled the required field");
		}
		else
		{
			System.out.println("hiiii");
			status=1;
		}
		
	}
	}


	
		
		
		
		
		
		
		
		
		
		
		
		
		
	    