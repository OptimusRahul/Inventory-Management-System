import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.util.CheckEntries;
import com.util.DataUtil;


public class Unit_Master extends JFrame implements ActionListener,FocusListener{

	JLabel labelUnitID,labelUnitName,labelDescription,jlhead,vname;
	JTextField jtfUnitID,jtfUnitName,jtfDescription;
	JButton jbtSave,jbtNew,jbtfirst,jbtlast,jbtNavigate,jbtprevious,jbtnext,jbupdate,jbdelete,Jback;
	DataUtil obj;
	Font fd;
	ResultSet rs;
	int temp=0;
	CheckEntries valid;
	String id;
	java.sql.Connection m;
	int status;
	Properties pr;
	FileReader fr;
	public Unit_Master() {	
		
		valid=new  CheckEntries();
		vname=new JLabel(" ");
		obj=new DataUtil();
		getLabels();
		 id=obj.AllId("UNT","unit_master", "UnitID");
		 m=obj.getConnection();
		vname.setBounds(780, 280, 250, 30);
		add(vname);
		jlhead=new JLabel(pr.getProperty("um_head1"));
		jlhead.setBounds(500, 30, 700, 80);
		fd=new Font("Aerial",Font.BOLD,48);
		jlhead.setFont(fd);
		add(jlhead);
		labelUnitID=new JLabel(pr.getProperty("um_id"));
		labelUnitID.setBounds(500, 230, 80, 30);
		add(labelUnitID);
		
		labelUnitName=new JLabel(pr.getProperty("usm_name"));
		labelUnitName.setBounds(500, 280, 80, 30);
		add(labelUnitName);
		
		labelDescription=new JLabel(pr.getProperty("usm_des"));
		labelDescription.setBounds(500, 330, 80, 30);
		add(labelDescription);
		
		
		jtfUnitID=new JTextField();
		jtfUnitID.setBounds(600, 230, 150, 30);
		add(jtfUnitID);
		
		jtfUnitName=new JTextField();
		jtfUnitName.setBounds(600, 280, 150, 30);
		add(jtfUnitName);
	
		
		jtfDescription= new JTextField();
		jtfDescription.setBounds(600, 330, 150, 30);
		add(jtfDescription);
		
		jbdelete=new JButton(pr.getProperty("Btn_delete"));
		jbdelete.setBounds(500, 425, 120, 30);
		jbdelete.addActionListener(this);
		add(jbdelete);
		jbupdate=new JButton(pr.getProperty("Btn_update"));
		jbupdate.setBounds(630, 425, 120, 30);
		jbupdate.addActionListener(this);
		add(jbupdate);
		jbtNew=new JButton(pr.getProperty("Btn_new"));
		jbtNew.setBounds(630, 380, 120, 30);
		add(jbtNew);
		Jback=new JButton(pr.getProperty("Btn_back"));
		Jback.setBounds(770,425,120,30);
		add(Jback);
		Jback.addActionListener(this);
		jbtNavigate=new JButton(pr.getProperty("Btn_navigate"));
		jbtNavigate.setBounds(770, 380, 120, 30);
		add(jbtNavigate);
		jbtNavigate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(temp==0)
				{
				jbtnext.setVisible(true);
				jbtfirst.setVisible(true);
				jbtlast.setVisible(true);
				jbtprevious.setVisible(true);
				temp=1;
				}
				else if(temp==1)
				{
					jbtnext.setVisible(false);
					jbtfirst.setVisible(false);
					jbtlast.setVisible(false);
					jbtprevious.setVisible(false);
					temp=0;
					}
			}
		});
		
		jbtnext=new JButton(pr.getProperty("Btn_next"));
		jbtnext.setBounds(800, 475, 120, 30);
		jbtnext.setVisible(false);
		jbtnext.addActionListener(this);
		add(jbtnext);
		jbtfirst=new JButton(pr.getProperty("Btn_first"));
		jbtfirst.setBounds(500, 475, 120, 30);
		jbtfirst.setVisible(false);
		jbtfirst.addActionListener(this);
		add(jbtfirst);
		jbtlast=new JButton(pr.getProperty("Btn_last"));
		jbtlast.setBounds(650, 475, 120, 30);
		jbtlast.setVisible(false);
		jbtlast.addActionListener(this);
		add(jbtlast);
		jbtprevious=new JButton(pr.getProperty("Btn_previous"));
		jbtprevious.setBounds(350,475, 120, 30);
		jbtprevious.setVisible(false);
		jbtprevious.addActionListener(this);
		add(jbtprevious);
		jtfUnitID.setText(id);
		jtfUnitID.setEditable(false);
		jbtNew.addActionListener(this); 
		jbtSave=new JButton(pr.getProperty("Btn_save"));
		jbtSave.addActionListener(this);
		jbtSave.setBounds(500, 380, 120, 30);
		add(jbtSave);
		jtfUnitName.addFocusListener(this);
		getFirst();
		showData();
		setLayout(null);
		setVisible(true);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		   setSize(screenSize.width,screenSize.height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Unit_Master();
	}
	public void getFirst()
	{
	rs=obj.getResultsShow("unit_master");
	showData();
	}
	public void showData() 
	{
		try{
		jtfUnitID.setText(rs.getString(1));
		jtfUnitName.setText(rs.getString(2));
		jtfDescription.setText(rs.getString(3));
		
		
		}
		catch(Exception e){System.out.println(e.toString());}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbtSave)
		 {
			if(status==0)
			{
				JOptionPane.showMessageDialog(getContentPane(),"Please Complete Validation");
			}
			else if(status==1)
			{
			String id=jtfUnitID.getText();
			String name=jtfUnitName.getText().trim();
			String desc=jtfDescription.getText().trim();
			String query=" INSERT INTO inventory.unit_master( UnitID,UnitName,Discription,status) VALUES (?,?,?,?)";
			String t=obj.insertUnit(query, m, id, name, desc,"Y");
			JOptionPane.showMessageDialog(getContentPane(), "Saved Data");
//			this.dispose();
//			new Unit_Master();
			Refresh();
		}
		 }
		if(e.getSource()==Jback)
		{
			new MenuList();
			this.dispose();
		}
		if(e.getSource()==jbtNew)
		 {
			Refresh();
		}
		if(e.getSource()==jbdelete)
		{
		String msg=obj.Delete("unit_master", "UnitID",jtfUnitID.getText());
		JOptionPane.showMessageDialog(this, msg);
		this.hide();
		new Unit_Master();
		}
		if(e.getSource()==jbupdate)
		{
		String msg=obj.Update_UnitMaster(m, jtfUnitName.getText(),jtfDescription.getText(),jtfUnitID.getText(),"Y");
		JOptionPane.showMessageDialog(this, msg);
		this.hide();
		new Unit_Master();
		}
		try{
			if(e.getSource()==jbtnext)
			{
				rs.next();
				showData();
			}
			else if(e.getSource()==jbtprevious)
			{
				rs.previous();
				showData();
			}
			else if(e.getSource()==jbtlast)
			{
				rs.last();
				showData();
			}
			if(e.getSource()==jbtfirst)
			{
				rs.first();
				showData();
			}			
			}
			catch(Exception ee){}		
	}
	private void Refresh() {
		
		jtfUnitID.setText(obj.AllId("UNT","unit_master", "UnitID"));
		jtfUnitName.setText(" ");
		jtfDescription.setText(" ");
		jtfUnitID.setEditable(false);
	}
	
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent ae) {
		if(ae.getSource()==jtfUnitName)
		{
			boolean ret=valid.CheckLetter(jtfUnitName.getText());
			if(ret==false){
				vname.setText("Please Enter Letter");
			status=0;
			}
			else{
				vname.setText(" ");
				status=1;
			}
			if(jtfUnitName.getText()==null)
			{
				vname.setText("Please Enter Letter");
				status=0;
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
