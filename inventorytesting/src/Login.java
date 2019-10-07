import java.awt.Color; 
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

import com.util.DataUtil;
import java.io.FileReader;
import java.util.Properties;
public class Login extends JFrame implements ActionListener{
	JLabel jLlogin,jLusername,jLpassword,jLforgetPassword;
	JRadioButton jRuser,jRadmin;
	JTextField jTusername;
	JPasswordField jPassword;
	Font font1,font2,font3;
	JButton JBlogin,JBreset,JBForgetPassword;
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet resultset;
	ImageIcon Backimage;
	JLabel background;
	ButtonGroup buttonGroup;
	String check=" ";
	FileReader fr;
	Properties pr;
	public Login() {
		getLabels();
	jLlogin=new JLabel(pr.getProperty("jLlogin"));
		
		jLusername=new  JLabel(pr.getProperty("JLusername"));
		jLpassword=new JLabel(pr.getProperty("JLpassword"));
		jLforgetPassword=new JLabel(pr.getProperty("jLforgetPassword"));
		jRuser=new JRadioButton(pr.getProperty("jRuser"));
		jRadmin=new JRadioButton(pr.getProperty("jRadmin"));
		ButtonGroup bg=new ButtonGroup();
		jTusername=new JTextField();
		jPassword=new JPasswordField();
		JBlogin=new JButton(pr.getProperty("JBlogin"));
		JBreset=new JButton(pr.getProperty("Btn_reset"));
		JBForgetPassword=new JButton(pr.getProperty("JBForgetPassword"));
		font1=new Font("Stencil ", Font.BOLD, 70);
		font2=new Font("Arial", Font.PLAIN, 20);
		font3=new Font("Arial", Font.PLAIN, 20);
		setLayout(null);
		
		jLlogin.setBounds(500, 30, 500, 80);
		jRuser.setBounds(350, 170,200, 40);
		jRadmin.setBounds(700, 170, 200, 40);
		jLusername.setBounds(350, 300, 200, 40);
		jLpassword.setBounds(350, 400, 200, 40);
		jTusername.setBounds(650, 300, 300, 40);
		jPassword.setBounds(650, 400, 300, 40);
		jLforgetPassword.setBounds(700,450, 300, 30);
		JBForgetPassword.setBounds(860, 450, 125, 30);
		JBlogin.setBounds(300, 520, 200, 50);
		JBlogin.addActionListener(this);
		JBreset.setBounds(800, 520, 200, 50);
		JBreset.addActionListener(this);
		//JBregister.setBounds(720,650,125,30);
		//JBregister.addActionListener(this);
		
		bg.add(jRuser);
		bg.add(jRadmin);
		jRuser.addActionListener(this);
		jRadmin.addActionListener(this);
		add(jRuser);
		add(jRadmin);
		add(jLlogin);
		add(jLusername);
		add(jLpassword);
		add(jTusername);
		add(jPassword);
		add(JBlogin);
		add(JBreset);
		add(jLforgetPassword);
		add(JBForgetPassword);
		//add(JBregister);
		
		jLlogin.setFont(font1);
		jRuser.setFont(font2);
		jRadmin.setFont(font2);
		jLusername.setFont(font2);
		jLpassword.setFont(font2);
		jTusername.setFont(font2);
		jPassword.setFont(font2);
		JBlogin.setFont(font2);
		JBreset.setFont(font2);
		JBlogin.setFont(font3);
		
		jLforgetPassword.setFont(font3);
		JBForgetPassword.setFont(font3);
		setSize(1360,750);
		setVisible(true);
	}
	public static void main(String args[]){
		new Login();
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
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==JBlogin ){
			DataUtil obj=new DataUtil<>();
			con=obj.getConnection();
			int g=0;
			String u=jTusername.getText();
			String p=jPassword.getText();
			try
			{
				String str="select * from "+check+" where USERNAME=? and PASSWORD=?";
				PreparedStatement st=con.prepareStatement(str);
				st.setString(1, u);
				st.setString(2, p);
				ResultSet rs=st.executeQuery();
				while(rs.next())
				{
					g=1;
					break;
				}
				
				if(g==1)
				{
					if(check=="user")
					{
						MenuList mobj=new MenuList();
						mobj.Enabledfalse(check);
						this.dispose();
					}
					else{
						new MenuList();
						this.dispose();
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Incorrect Login");
				}
			}
			catch(Exception ee)
			{
				JOptionPane.showMessageDialog(this, "Plz Choose user type");
			}
		}
		if(ae.getSource()==JBreset){
			jTusername.setText(null);
			jPassword.setText(null);
		}
		if(ae.getSource()==jRadmin){
			check="admin";
		}
		else if(ae.getSource()==jRuser){
			check="user";
		}
	}
}
	

