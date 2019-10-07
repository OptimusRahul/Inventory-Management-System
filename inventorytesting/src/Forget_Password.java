import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.util.DataUtil;


public class Forget_Password extends JFrame implements ActionListener{
	JTextField jtuname,jtemail;
	JLabel jluname,jlemail,jhead;
	JButton jok,jcancel,jreset;
	DataUtil obj;
	Connection con;
	JRadioButton Jruser,Jradmin;
	Properties pr;
	FileReader fr;
	Font f,f1;
	public Forget_Password() {
		obj=new DataUtil();
		con=obj.getConnection();
		getLabels();
		
		jhead=new JLabel("FORGOT PASSWORD");
		add(jhead);
		jhead.setBounds(500, 30, 500, 80);
		Jruser=new JRadioButton(pr.getProperty("jRuser"));
		Jradmin=new JRadioButton(pr.getProperty("jRadmin"));
		ButtonGroup bg=new ButtonGroup();
		Jruser.setBounds(50, 200, 100, 30);
		Jradmin.setBounds(200, 200, 100, 30);
	jluname=new JLabel(pr.getProperty("JLusername"));
	jluname.setBounds(50, 300, 150, 30);
	add(jluname);
	jlemail=new JLabel(pr.getProperty("email"));
	jlemail.setBounds(50, 370, 150, 30);
	add(jlemail);
	jtuname=new JTextField();
	jtuname.setBounds(200,300,150,30);
	add(jtuname);
	jtemail=new JTextField();
	jtemail.setBounds(200,370,150,30);
	add(jtemail);
	jok=new JButton(pr.getProperty("Btn_submit"));
	jok.setBounds(50, 450, 80, 40);
	add(jok);
	jok.addActionListener(this);
	jcancel=new JButton(pr.getProperty("Btn_cancel"));
	jcancel.setBounds(150, 450, 80, 40);
	add(jcancel);
	jcancel.addActionListener(this);
	jreset=new JButton(pr.getProperty("Btn_reset"));
	jreset.setBounds(250, 450, 80, 40);
	add(jreset);
	jreset.addActionListener(this);
	bg.add(Jruser);
	bg.add(Jradmin);
	add(Jruser);
	add(Jradmin);
	f=new Font("SERIF",Font.ITALIC,40);
    f1=new Font("SERIF",Font.ITALIC,20);
    jluname.setFont(f1);
    jlemail.setFont(f1);
    jhead.setFont(f);
    Jruser.setFont(f1);
    Jradmin.setFont(f1);
	setLayout(null);
	Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	   setSize(screenSize.width,screenSize.height);
	setVisible(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		Forget_Password obj1=new Forget_Password();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jok && Jruser.isSelected()==true)
		{
			String msg=obj.getPassword(con, "user", "USERNAME", "EMAIL", jtuname.getText(), jtemail.getText(), "PASSWORD");
			JOptionPane.showMessageDialog(this,"Dear "+jtuname.getText()+" Your Password is "+msg);
		}
		if(e.getSource()==jok && Jradmin.isSelected()==true)
		{
			String msg=obj.getPassword(con, "admin", "USERNAME", "EMAIL", jtuname.getText(), jtemail.getText(), "PASSWORD");
			JOptionPane.showMessageDialog(this,"Dear "+jtuname.getText()+" Your Password is "+msg);
		}
		else if(e.getSource()==jreset)
		{
			jtuname.setText("");
			jtemail.setText("");
		}
		else if(e.getSource()==jcancel)
		{
			new LoginForm();
			this.dispose();
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
