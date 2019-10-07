
import java.awt.Color;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.util.CheckEntries;
import com.util.DataUtil;

public class Company_Opening_Account extends JFrame implements ActionListener,FocusListener {

	protected static final String String = null;
	JLabel lhead,lcomp_id,lcomp_name,lcomp_email,lopen_acc,ldate,jl6,jl7;
	JTextField jtfcomp_id,jtfcomp_name,jtfcomp_email,jtfopen_acc,jtfdate;
	JButton jbtsubm_info,jbtcancel;
	String retst,s;
	FileReader fr;
	Properties pr;
	int status;
	DataUtil obj;
	Connection m;
	Statement st;
	ResultSet rs;
	
	public Company_Opening_Account() {
		super("Company Opening Account");
		getLabels();
		obj=new DataUtil();
		ArrayList lt=new ArrayList();
		lt=obj.getCompanyDetails();
		if(lt.size()>0)
		
	    obj=new DataUtil();
		m=obj.getConnection();
	
		lhead=new JLabel(pr.getProperty("oca_head"));
		lhead.setBounds(480, 120, 450, 40);
		lhead.setFont(new Font("Elephant", Font.PLAIN, 20));
		lhead.setForeground(Color.GRAY);
		add(lhead);
		
		lcomp_id=new JLabel(pr.getProperty("cmp_id"));
		lcomp_id.setBounds(500, 200, 200, 40);
		add(lcomp_id);
		
		lcomp_name=new JLabel(pr.getProperty("cmp_name"));
		lcomp_name.setBounds(500, 250, 200, 40);
		add(lcomp_name);
		
		lcomp_email=new JLabel(pr.getProperty("email"));
		lcomp_email.setBounds(500, 300, 200, 40);
		add(lcomp_email);
		
		lopen_acc=new JLabel(pr.getProperty("camount"));
		lopen_acc.setBounds(500, 350, 200, 40);
		add(lopen_acc);
		
		jl6=new JLabel(pr.getProperty("blank"));
		jl6.setBounds(880, 350, 150, 30);
		add(jl6);
		
		ldate=new JLabel(pr.getProperty("date"));
		ldate.setBounds(500, 400, 200, 40);
		add(ldate);
		
		jtfcomp_id=new JTextField(lt.size()>0?lt.get(0).toString():" ");
		jtfcomp_id.setBounds(700, 200, 150, 30);
		jtfcomp_id.setEditable(false);
		add(jtfcomp_id);
		if(jtfcomp_id.getText().length()>0)
			
		
		jl7=new JLabel("");
		jl7.setBounds(780, 200, 300, 40);
		add(jl7);
		
		jtfcomp_name=new JTextField(lt.size()>0?lt.get(1).toString():" ");
		jtfcomp_name.setBounds(700, 250, 150, 30);
		jtfcomp_name.setEditable(false);
		add(jtfcomp_name);
		
		jtfcomp_email=new JTextField(lt.size()>0?lt.get(2).toString():" ");
		jtfcomp_email.setBounds(700, 300, 150, 30);
		jtfcomp_email.setEditable(false);
		add(jtfcomp_email);
		
		jtfopen_acc=new JTextField();
		jtfopen_acc.setBounds(700, 350, 150, 30);
		add(jtfopen_acc);
		
		jtfopen_acc.addFocusListener(this);
		
		jtfdate=new JTextField();
		jtfdate.setBounds(700, 400, 150, 30);
		add(jtfdate);
		jtfdate.setEditable(false);
		
		  Date date1 = new Date();
	    	GregorianCalendar calendar = new GregorianCalendar();
		    calendar.setTime(date1);
	        String strDate = calendar.get(Calendar.DATE)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
		    jtfdate.setText(strDate);


		jbtsubm_info=new JButton(pr.getProperty("Btn_submit"));
		jbtsubm_info.setBounds(500, 470, 100, 30);
		add(jbtsubm_info);
		
		jbtcancel=new JButton(pr.getProperty("Btn_cancel"));
		jbtcancel.setBounds(650, 470, 100, 30);
		add(jbtcancel);
		jbtsubm_info.addActionListener(this);
		jbtcancel.addActionListener(this);
		//System.out.println(retst);
		setLayout(null);
		change();
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
	new Company_Opening_Account();
	
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jbtsubm_info){
		if(status==0)
		{
			JOptionPane.showMessageDialog(getContentPane(), "Please Complete Validdation");
		}
		else if(status==1){
		
		 
		    	String n=obj.AllId("CAT","gst_slab","Cat_ID");
				String cid=jtfcomp_id.getText();
				String cn=jtfcomp_name.getText();
				String cem=jtfcomp_email.getText();
				String oa=jtfopen_acc.getText();
				String d=jtfdate.getText();
				String t=obj.insert(m,"openingaccount",cid,cn,cem,oa,d,"Y");
				JOptionPane.showMessageDialog(this,t);
				jtfopen_acc.setEditable(false);
				jbtsubm_info.setEnabled(false);
	   }  
		}
		
		
		else if(e.getSource()==jbtcancel){
			MenuList obj1=new MenuList();
			this.dispose();
			}
	}
	public void getLabels(){
	try{
		 fr=new FileReader("AllLabels");
		 pr=new Properties();
		pr.load(fr);
	}
	catch(Exception e){}
}
		
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent ae) {
		CheckEntries ce=new CheckEntries();
		if(ae.getSource()==jtfopen_acc)
		{
			String c=jtfopen_acc.getText();
			boolean b=ce.CheckOpenAmount(c);
			if(b==false){
				status=0;
				jl6.setText("Invalid Amount");
				
			}
			else if(b==true){
				status=1;
				jl6.setText("");
			}
	}
	}
	public void change()
	{
		DataUtil obj=new DataUtil();
		ArrayList al=new ArrayList();
		try
		{
			al=obj.openingaccount("openingaccount");
			System.out.println(al.get(0).toString());
			String openamount=al.get(3).toString();
			if(openamount.isEmpty())
			{
				jtfopen_acc.setEditable(true);
			}
			else
			{
				
				jtfopen_acc.setEditable(false);
				jbtsubm_info.setEnabled(false);
				jtfopen_acc.setText(openamount);
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
}

	