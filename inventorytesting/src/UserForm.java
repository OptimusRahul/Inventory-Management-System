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
import java.sql.*;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import com.util.CheckEntries;
import com.util.DataUtil;
public class UserForm extends JFrame implements ActionListener,FocusListener,KeyListener{

	JLabel JLadminHeading,JLusername,JLpassword,JLemail,JLphone;
	JTextField JTusername,JTemail,JTphone;
	JLabel vemail,vmobile,vname;
	JPasswordField Jpassword;
	JButton JBsubmit,JBcancel;
	JRadioButton jRuser,jRadmin;
	ButtonGroup buttonGroup;
	Connection con;
	PreparedStatement prepareStatement;
	DataUtil util;
	CheckEntries valid;
	Properties pr;
	FileReader fr;
	Font f,f1;
	public UserForm() {
		util=new DataUtil();
		valid=new CheckEntries();
		getLabels();
		f=new Font("SERIF",Font.ITALIC,40);
	    f1=new Font("SERIF",Font.ITALIC,20);
		JLadminHeading=new JLabel(pr.getProperty("JLadminHeading"));
		JLusername=new JLabel(pr.getProperty("JLusername"));
		JLemail=new JLabel(pr.getProperty("email"));
		JLphone=new JLabel(pr.getProperty("contact"));
		JLpassword=new JLabel(pr.getProperty("JLpassword"));
		jRuser=new JRadioButton(pr.getProperty("jRuser"));
		jRadmin=new JRadioButton(pr.getProperty("jRadmin"));
		ButtonGroup bg=new ButtonGroup();
		JTusername=new JTextField();
		JTemail=new JTextField();
		JTphone=new JTextField();
		Jpassword=new JPasswordField();
		JBsubmit=new JButton(pr.getProperty("Btn_save"));
		JBsubmit.addActionListener(this);
		JBcancel=new JButton(pr.getProperty("Btn_cancel"));
		JBcancel.addActionListener(this);
		JTemail.addFocusListener(this);
		JTphone.addFocusListener(this);
		JTusername.addFocusListener(this);
		
		vname=new JLabel();
		vmobile=new JLabel();
		vemail=new JLabel();
		
		jRuser.setBounds(200, 200,200, 40);
		jRadmin.setBounds(400, 200, 200, 40);
		JLadminHeading.setBounds(250, 30, 1500, 100);
		
		JLusername.setBounds(200,300 ,250, 30);
		JLemail.setBounds(200,370,150,30);
		JLphone.setBounds(200, 440, 150, 30);
		JLpassword.setBounds(200,510,150,30);
		
		JTusername.setBounds(400,300 ,150, 30);
		JTemail.setBounds(400,370,150,30);
		JTphone.setBounds(400, 440, 150, 30);
		Jpassword.setBounds(400,510,150,30);
		
		JBsubmit.setBounds(200, 600, 100,40);
		JBcancel.setBounds(400, 600, 100,40);
		
		vname.setBounds(570, 300, 100, 40);
		vemail.setBounds(570, 370, 100, 40);
		vmobile.setBounds(570, 440, 100, 40);
		
	    bg.add(jRuser);
		bg.add(jRadmin);
		add(jRuser);
		add(jRadmin);
		add(JLadminHeading);
		add(JLusername);
		add(JLemail);
		add(JLphone);
		add(JLpassword);
		add(JTusername);
		add(JTemail);
		add(JTphone);
		add(Jpassword);
		add(JBsubmit);
		add(JBcancel);	
		add(vname);
		add(vmobile);
		add(vemail);
		jRadmin.addKeyListener(this);
		jRuser.addKeyListener(this);
		JBsubmit.addKeyListener(this);
		JBcancel.addKeyListener(this);
		JTusername.addKeyListener(this);
		JTemail.addKeyListener(this);
		JTphone.addKeyListener(this);
		Jpassword.addKeyListener(this);
		JLadminHeading.setFont(f);
		JLusername.setFont(f1);
		JLpassword.setFont(f1);
		JLemail.setFont(f1);
		JLphone.setFont(f1);
		jRuser.setFont(f1);
		jRadmin.setFont(f1);
		JBsubmit.setFont(f1);
		JBcancel.setFont(f1);
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		setLayout(null);
		setVisible(true);		
	}
	public static void main(String[] args) {
		new UserForm();
	}
	public void actionPerformed(ActionEvent ae) {	
		String username=JTusername.getText();
		String email=JTemail.getText().toLowerCase();
		String phone=JTphone.getText();
		String password=Jpassword.getText();
		if(ae.getSource()==JBsubmit)
		{
			
			String expression="^";
			Pattern p=Pattern.compile(expression);
			Matcher m=p.matcher(JTusername.getText());
			Matcher m1=p.matcher(JTphone.getText());
			Matcher m2=p.matcher(JTemail.getText());
			Matcher m3=p.matcher(Jpassword.getText());
			if(m.matches()|| m1.matches()|| m2.matches()|| m3.matches())
			{
				JOptionPane.showMessageDialog(this,"Please Fill required field...");	
			}
			else
			{	String msg;
				if(ae.getSource().equals(JBsubmit) && jRuser.isSelected()==true)
				{
					con=util.getConnection();
					msg=util.insert(con,"user",username,email,phone,password);
					JOptionPane.showMessageDialog(this, username+" your "+msg);
					refreshfunction();
				}
				else if(ae.getSource().equals(JBsubmit) && jRadmin.isSelected()==true){
					con=util.getConnection();
					msg=util.insert(con,"admin",username,email,phone,password);
					JOptionPane.showMessageDialog(this, username+" your "+msg);
					refreshfunction();
				}
			
			}
		}
			if(ae.getSource()==JBcancel){
			new MenuList();
				this.hide();
			}
}
	public void refreshfunction(){
		JTusername.setText("");
		JTemail.setText("");
		JTphone.setText("");
		Jpassword.setText("");
	}
	public void focusGained(FocusEvent e) {		
	}
	public void focusLost(FocusEvent e) {
		if(e.getSource()==JTemail)
		{
			boolean ret=valid.CheckEmail(JTemail.getText());
			if(ret==false)
					vemail.setText("Invalid Email");
			else
				vemail.setVisible(false);
		}
		if(e.getSource()==JTphone)
		{
			boolean ret=valid.CheckPhone(JTphone.getText());
			if(ret==false)
				vmobile.setText("Invalid Mobile No");
			else
				vmobile.setVisible(false);
		}
		if(e.getSource()==JTusername)
		{
			boolean ret=valid.CheckLetter(JTusername.getText());
			if(ret==false)
					vname.setText("Invalid Only Letter");
			else
				vname.setVisible(false);		
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getSource()==jRuser && e.getKeyCode()==e.VK_ENTER)
			jRadmin.requestFocus();
		else if(e.getSource()==jRadmin && e.getKeyCode()==e.VK_ENTER)
			JTusername.requestFocus();
		else if(e.getSource()==JTusername && e.getKeyCode()==e.VK_ENTER)
			JTemail.requestFocus();
		else if(e.getSource()==JTemail && e.getKeyCode()==e.VK_ENTER)
			JTphone.requestFocus();
		else if(e.getSource()==JTphone && e.getKeyCode()==e.VK_ENTER)
			Jpassword.requestFocus();
		else if(e.getSource()==Jpassword && e.getKeyCode()==e.VK_ENTER)
			JBsubmit.requestFocus();
			}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}