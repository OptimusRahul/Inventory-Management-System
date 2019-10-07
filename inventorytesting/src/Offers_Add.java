import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.util.CheckEntries;
import com.util.DataUtil;


public class Offers_Add extends JFrame implements ActionListener,FocusListener{

	JLabel jlhead,jlid,jlname,jldate,vcate,jldescription;
	JTextField jtid,jtname,jtdate,jtdescription;
	JButton jbinsert,jbcancel,Jback,jbnext;
    String s;
	DataUtil data; 
	Connection con;
	int status;
	
	public Offers_Add(){
		
		setTitle("Offers Add");
		jlhead=new JLabel("ADD DISCOUNT OFFERS");
		jlhead.setBounds(500, 120, 300, 40);
		jlhead.setFont(new Font("Elephant", Font.PLAIN, 20));
		jlhead.setForeground(Color.GRAY);
		add(jlhead);
		

		jlid=new JLabel("OFFERS ID");
		jlid.setBounds(500, 200, 200, 40);
		add(jlid);
		
		data=new DataUtil();
	    s=data.AllId("OFR","offers","Offer_id");
	    con=data.getConnection();
		jtid=new JTextField();
		jtid.setText(s);
		jtid.setBounds(610, 200, 150, 30);
		add(jtid);
		jtid.setEditable(false);
		
		
		jlname=new JLabel("OFFERS NAME");
		jlname.setBounds(500, 250, 200, 40);
		add(jlname);
		
		jtname=new JTextField();
		jtname.setBounds(610, 250, 150, 30);
		add(jtname);
		jtname.addFocusListener(this);

		vcate=new JLabel("");
		vcate.setBounds(780,200,150,130);
		add(vcate);
		
		jldescription=new JLabel("MESSAGE TEXT");
		jldescription.setBounds(500,300,200,40);
		add(jldescription);
		
		jtdescription=new JTextField();
		jtdescription.setBounds(610,300,200,40);
		add(jtdescription);
		
		jldate=new JLabel("DATE");
		jldate.setBounds(500, 360, 200, 40);
		add(jldate);
		
		jtdate=new JTextField();
		jtdate.setBounds(610, 360, 150, 30);
		add(jtdate);
		jtdate.setEditable(false);
		
        Date date1 = new Date();
    	GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(date1);
        String strDate = DateFormat.getDateTimeInstance().format(new java.util.Date());
	    jtdate.setText(strDate.substring(0,12));
		
		jbinsert=new JButton("Submit");
		jbinsert.setBounds(500, 420, 110, 35);
		add(jbinsert);
		
		jbcancel=new JButton("Cancel");
		jbcancel.setBounds(700, 420, 110, 35);
		add(jbcancel);
		
		Jback=new JButton("Back");
		Jback.setBounds(500, 500, 110, 35);
		add(Jback);
		
		jbnext=new JButton("Next");
		jbnext.setBounds(700, 500, 110, 35);
		add(jbnext);
		

		jbinsert.addActionListener(this);
		jbcancel.addActionListener(this);
		Jback.addActionListener(this);
		jbnext.addActionListener(this);
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Offers_Add();

	}
	@Override
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
			String cname=jtname.getText();
			String cdate=jtdate.getText();
			String msg=data.insert(con,"offers",cid,cname,cdate,"Y");
			JOptionPane.showMessageDialog(this, msg);
			
			reset();
			jtid.setText(data.AllId("OFR","offers","offer_id"));
			}
		}
		else if(e.getSource()==jbcancel)
		{
			  dispose();
		}
		else if(e.getSource()==Jback)
		{
			setVisible(false);
			new MenuList();
			this.dispose();
		}
		else if(e.getSource()==jbnext)
		{
			reset();
			jtid.setText(data.AllId("OFR","offers","offer_id"));
		}
	}
	public void reset()
	
	{
	
		jtid.setText(" ");
		jtname.setText(" ");
		jtdate.removeAll();
	}
	@Override
	public void focusGained(FocusEvent e) {
		
		
	}
	@Override
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
					else if (data.getCateoryName("offers","Offer",jtname.getText())==true)
					{
					JOptionPane.showMessageDialog(this, "record alreay there pls choose other");
					jtname.setText(" ");
					jtname.requestFocus();
				}		 
			}
	        }
		 	}
		
