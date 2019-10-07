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

public class USM_Nav extends JFrame implements ActionListener,FocusListener{
JLabel Junitmaster,Junitsizeid,Jluid,Jlsname,Jldescription,vsize_name;
JTextField Jtunitsizeid,Jtuid,Jtname,Jtdescription;
JButton Jbupdate,Jbdelete,Jbfirst,Jblast,Jbprevious,Jbnext,jback;
JComboBox jcunitname;
Font f1,f2;
String s;
DataUtil data;
ResultSet rs;
CheckEntries ce;
int status;
String value="true";
Properties pr;
FileReader fr;
	public USM_Nav(){
		data=new DataUtil();
		getLabels();
		Font f1=new Font("Cooper", Font.BOLD,20);
		Font f2=new Font("Elephant", Font.BOLD, 25);
		Junitmaster=new JLabel(pr.getProperty("usm_head")+" "+pr.getProperty("navi"));
		Junitsizeid=new JLabel("Unit size_Id");
		Jtunitsizeid=new JTextField();
		Jtunitsizeid.setText(data.AllId("USM", "Unit_Size_Master", "UnitSizeID"));
		Jtunitsizeid.setEditable(false);
		Jluid=new JLabel(pr.getProperty("usm_name"));
		Jtuid=new JTextField();
		Jlsname=new JLabel(pr.getProperty("usm_sname"));
		Jtname=new JTextField();
		Jldescription=new JLabel(pr.getProperty("usm_des"));
		Jtdescription=new JTextField();
		Jbupdate=new JButton(pr.getProperty("Btn_update"));
		Jbdelete=new JButton(pr.getProperty("Btn_delete"));
		Jbfirst=new JButton(pr.getProperty("Btn_first"));
		Jblast=new JButton(pr.getProperty("Btn_last"));
		Jbprevious=new JButton(pr.getProperty("Btn_previous"));
		Jbnext=new JButton(pr.getProperty("Btn_next"));
		
		setLayout(null);
	    Junitmaster.setBounds(400,40,700,100);
		Junitsizeid.setBounds(500,170,200,40);
		Jtunitsizeid.setBounds(650, 170,200,40);
		Jluid.setBounds(500,220,200,40);
		
		Jlsname.setBounds(500,270,200,40);
		vsize_name=new JLabel();
		vsize_name.setBounds(880,270,200,40);
		add(vsize_name);
		Jtname.setBounds(650,270,200,40);
		Jldescription.setBounds(500,320,200,40);
		Jtdescription.setBounds(650,320,200,40);
		Jbupdate.setBounds(410, 420, 150, 50);
		Jbupdate.addActionListener(this);
		Jbdelete.setBounds(580, 420, 150, 50);
		Jbdelete.addActionListener(this);
		jback=new JButton("Back");
		jback.setBounds(750, 420, 150, 50);
		add(jback);
		jback.addActionListener(this);
		Jbfirst.setBounds(350, 520, 150, 50);
		Jbfirst.addActionListener(this);
		Jbprevious.setBounds(510, 520, 150, 50);
		Jbprevious.addActionListener(this);
		Jbnext.setBounds(670, 520, 150, 50);
		Jbnext.addActionListener(this);
		Jblast.setBounds(830, 520, 150, 50);
		Jblast.addActionListener(this);
		add(Junitmaster);
		add(Junitsizeid);
		add(Jtunitsizeid);
		add(Jluid);
		add(Jtuid);
		add(Jlsname);
		add(Jtname);
	    add(Jldescription);
		add(Jtdescription);
		add(Jbupdate);
		add(Jbdelete);
		add(Jbfirst);
		add(Jbprevious);
		add(Jbnext);
		add(Jblast);
		Editablefalse();
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
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void getFirst()
	{
	rs=data.getResultsShow("unit_size_master");
	UnitShowData();
	}
	public static void main(String[] args) {
		new USM_Nav();
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
		if(ae.getSource()==Jbupdate && value.equals("true"))
		{
			Editabletrue();
			Jbupdate.setText("SAVE");
			value="false";
		}
	else if(ae.getSource()==Jbupdate && value.equals("false")){
		
			String msg=data.UpdateUnitSize(Jtname.getText(), Jtdescription.getText(), Jtunitsizeid.getText());
			JOptionPane.showMessageDialog(this, msg);
			this.dispose();
			new USM_Nav();
			
		}
		else if(ae.getSource()==Jbdelete){
			
			String msg=data.DeleteUnitSize(Jtunitsizeid.getText());
			JOptionPane.showMessageDialog(this, msg);
		}
		else if(ae.getSource()==jback)
		{
			this.dispose();
			new MenuList();
		}
		try
		{
		 if(ae.getSource()==Jbfirst){
			rs.first();
			UnitShowData();
		}
		 if(ae.getSource()==Jbnext){
			rs.next();
			UnitShowData();
		 }
		 if(ae.getSource()==Jblast){
				rs.last();
				UnitShowData();
			 }
		 if(ae.getSource()==Jbprevious){
				rs.previous();
				UnitShowData();	
			 }
		}
		 catch(Exception e)
		 {
			 System.out.println(e.toString());
		 }
}
	private void Resetdata() {
		Jtname.setText("");
		Jtdescription.setText("");
	}
	public void Editablefalse()
	{
		Jtname.setEditable(false);
		Jtdescription.setEditable(false);
	}
	public void Editabletrue()
	{
		Jtname.setEditable(true);
		Jtdescription.setEditable(true);
	}
	public void focusGained(FocusEvent arg0) {	
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
	public void getLabels(){
		try{
			fr=new FileReader("AllLabels");
			pr=new Properties();
			pr.load(fr);
		}
		catch (Exception e) { e.toString();	
		}
	}
}
	    