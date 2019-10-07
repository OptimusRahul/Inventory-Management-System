import java.awt.Color;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileReader;

import javax.swing.*;
import com.util.*;

@SuppressWarnings("serial")
public class Company_Master extends JFrame implements FocusListener,ItemListener,ActionListener {
	int status=1;
	JLabel lcname,lgst,lownid,lown1,lown2,laddres1,laddres2,lcity,lph,lemail;
	JLabel  vgs,vc,vid,vown1,vown2, ldate,vph;
	JLabel head;
	JTextField tcname,tgst,townid,town1,town2,taddres1,taddres2,tphone,temail;
	JComboBox jcity;
	JButton bsave,bback,bedit,breset;
	ButtonGroup bg;
	JRadioButton rbpan,ra;
	String check="EDIT";
	int select=0,stat=0;
	static int cc=1;
	DataUtil d1;
	int backstatus=1;
	FileReader fr;
	Properties pr;
	public Company_Master() {
	super("COMPANY INFORMATION");
	setLayout(null);
	getLabels();
	head=new JLabel(pr.getProperty("cmp_info"));
	add(head);
	Font fh=new Font("Arial Rounded MT Bold",Font.PLAIN,30);
	head.setFont(fh);
	head.setBounds(10,10,350,50);
	ldate=new JLabel(DateFormat.getDateTimeInstance().format(new java.util.Date()));
	Font fd=new Font("Arial Rounded MT Bold",Font.PLAIN,15);
	ldate.setBounds(350,15,200,50);
	ldate.setFont(fd);
	add(ldate);
	lcname=new JLabel(pr.getProperty("cmp_name"));
	tcname=new JTextField(100);
	lgst=new JLabel(pr.getProperty("gst"));
	tgst=new JTextField(20);
	tgst.addFocusListener(this);
	lownid=new JLabel(pr.getProperty("own_id"));
	add(lownid);
	lown1=new JLabel(pr.getProperty("own1"));
	town1=new JTextField(50);
	lown2=new JLabel(pr.getProperty("own2"));
	town2=new JTextField(50);
	laddres1=new JLabel(pr.getProperty("add1"));
	taddres1=new JTextField(150);
	laddres2=new JLabel(pr.getProperty("add2"));
	taddres2=new JTextField(150);
	lcity=new JLabel(pr.getProperty("city"));
	jcity=new JComboBox();
	jcity.addItem("Agra");
	jcity.addItem("Delhi");
	jcity.addItem("Mumbai");
	jcity.addItem("Chennai");
	jcity.addItem("Kolkata");
	lph=new JLabel(pr.getProperty("ph_no"));
	tphone=new JTextField();
	lemail=new JLabel(pr.getProperty("email"));
	temail=new JTextField();
	bsave=new JButton(pr.getProperty("Btn_save"));
	bback=new JButton(pr.getProperty("Btn_back"));
	bedit=new JButton(pr.getProperty("Btn_edit"));
	breset=new JButton(pr.getProperty("Btn_reset"));
	townid=new JTextField();
	bg=new ButtonGroup();
	rbpan=new JRadioButton(pr.getProperty("pans"));
	ra=new JRadioButton(pr.getProperty("aadhar"));
	bg.add(rbpan);
	bg.add(ra);
	rbpan.addItemListener(this);
	ra.addItemListener(this);
	townid.addFocusListener(this);
	Font f=new Font("Arial",Font.PLAIN,15);
	Font f1=new Font("Arial",Font.PLAIN,13);
	lcname.setBounds(20,80,120,20);
	lcname.setFont(f);
	tcname.setBounds(200,80,250,30);
	lgst.setBounds(20,120,120,20);
	lgst.setFont(f);
	tgst.setBounds(200,115,250,30);
	lownid.setBounds(20,160,120,20);
	lownid.setFont(f);
	rbpan.setBounds(20,190,120,20);
	rbpan.setFont(f1);
	ra.setBounds(20,220,150,20);
	ra.setFont(f1);
	townid.setBounds(200,195,250,30);
	lown1.setBounds(20,260,120,20);
	lown1.setFont(f);
	town1.setBounds(200,255,250,30);
	town1.addFocusListener(this);
	lown2.setBounds(20,300,120,20);
	lown2.setFont(f);
	town2.setBounds(200,295,250,30);
	town2.addFocusListener(this);
	laddres1.setBounds(20,340,120,20);
	laddres1.setFont(f);
	taddres1.setBounds(200,335,250,30);
	laddres2.setBounds(20,380,120,20);
	laddres2.setFont(f);
	taddres2.setBounds(200,375,250,30);
	lph.setBounds(20,420,120,20);
	lph.setFont(f);
	tphone.setBounds(200,415,250,30);
	tphone.addFocusListener(this);
	lcity.setBounds(20,460,120,20);
	lcity.setFont(f);
	jcity.setBounds(200,455,250,30);
	lemail.setBounds(20,500,120,20);
	lemail.setFont(f);
	temail.setBounds(200,495,250,30);
	bsave.setBounds(200,550,80,30);
	bsave.setBackground(Color.blue);
	bsave.setForeground(Color.white);
	add(bedit);
	bedit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			tcname.setEnabled(false);
			tgst.setEnabled(false);
			setTextBoxEnabled();
			backstatus=2;
		}
	});
	bedit.setBounds(310,550,80,30);
	bedit.setBackground(Color.darkGray);
	bedit.addActionListener(this);
	bedit.setForeground(Color.white);
	bsave.addActionListener(this);
	Font f2=new Font("Arial",Font.BOLD,15);
	bsave.setFont(f2);
	bedit.setFont(f2);
	bback.setBounds(420,550,80,30);
	bback.setBackground(Color.red);
	bback.setForeground(Color.white);
	bback.setFont(f2);
	bback.addActionListener(this);
	add(lcname);add(townid);add(tcname);add(lgst);add(tgst);add(lown1);add(town1);add(lown2);add(town2);add(laddres1);add(taddres1);add(laddres2);add(taddres2);
	add(lph);add(tphone);add(lcity);add(jcity);add(lemail);add(temail);add(bsave);add(bback);add(rbpan);add(ra);
	tcname.addFocusListener(this);
	vc=new JLabel(" ");
	
	add(vc);
	vc.setForeground(Color.red);
	vc.setBounds(460,80,250,30);
	vgs=new JLabel(" ");
	add(vgs);
	vgs.setForeground(Color.red);
	vgs.setBounds(460,120,250,30);
	vown1=new JLabel(" ");
	add(vown1);
	vown1.setForeground(Color.red);
	vown1.setBounds(460,260,250,30);
	vown2=new JLabel(" ");
	add(vown2);
	vown2.setForeground(Color.red);
	vown2.setBounds(460,300,250,30);
	vid=new JLabel(" ");
	add(vid);
	vid.setForeground(Color.red);
	vid.setBounds(460,195,250,30);
	vph=new JLabel(" ");
	add(vph);
	vph.setForeground(Color.red); 
	vph.setBounds(460,415,250,30);
	setSize(1360,750);
	setVisible(true);
	getData();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
	Company_Master frm=new Company_Master();
	}
	public void getData()
	{
		try{
		d1=new DataUtil();
		Connection c=d1.getConnection();
		ArrayList al1=d1.getData(c, "company_info");
			System.out.println(al1);
		if(al1.size()>0)
		{
		System.out.println(al1);
		setTextBoxDisabled();
		bsave.setEnabled(false);
		tcname.setText((String)al1.get(0).toString());
		tgst.setText((String)al1.get(1).toString());
		
		if((String)al1.get(2)=="N/A"){
			townid.setText((String)al1.get(3).toString());
			town1.setText((String)al1.get(4).toString());
			town2.setText((String)al1.get(5).toString());
			taddres1.setText((String)al1.get(6).toString());
			taddres2.setText((String)al1.get(7).toString());
			tphone.setText((String)al1.get(8).toString());
			temail.setText((String)al1.get(10).toString());
			}
		else {
		townid.setText((String)al1.get(2).toString());
		town1.setText((String)al1.get(4).toString());
		town2.setText((String)al1.get(5).toString());
		taddres1.setText((String)al1.get(6).toString());
		taddres2.setText((String)al1.get(7).toString());
		tphone.setText((String)al1.get(8).toString());
		temail.setText((String)al1.get(10).toString());}
		
		c.close();
		}
		else
		{
			setTextBoxEnabled();
		}
		}
		catch(Exception e){
			e.toString();
		}
	}
	public void itemStateChanged(ItemEvent ae) {//this func select pan or aadhaar
		if(ae.getItemSelectable()==rbpan)
		{
			select=1;
		}
		else if(ae.getItemSelectable()==ra){
			select=2;
		}
		System.err.println(select);
	}
	public void focusGained(FocusEvent ae) {	
	}
	public void focusLost(FocusEvent ae) {//return validation of gst
		CheckEntries ce=new CheckEntries();
		if(ae.getSource()==tgst){
			String g=tgst.getText();
			boolean bsave=ce.CheckGST(g);
			try{
				if(bsave==true){
					status=1;
					vgs.setText(" ");
				}
				else if(bsave==false){
					status=0;
					vgs.setText("Invalid GST Number");
				}
			}
			catch(StringIndexOutOfBoundsException e){
				status=0;
				vgs.setText("Invalid GST Number");
			}
		}
		else if(ae.getSource()==tcname){//return validation of companyname
			String c=tcname.getText();
			boolean b=ce.CheckLetter(c);
			if(b==true){
				status=1;
				vc.setText(" ");
			}
			else if(b==false){
				vc.setText("Only Letters are Allowed");
				status=0;
			}
		}
		else if(ae.getSource()==town1){//return validation of owner1
			String c=town1.getText();
			boolean b=ce.CheckLetter(c);
			if(b==false){
				vown1.setText("Only Letters are Allowed");
				status=0;
			}
			else if(b==true){
				status=1;
				vown1.setText(" ");
			}
		}
		else if(ae.getSource()==town2){//return validation of owner2
			String c=town2.getText();
			boolean b=ce.CheckLetter(c);
			if(b==false){
				vown2.setText("Only Letters are allowed");
				status=0;
			}
			else if(b==true){
				status=1;
				vown2.setText(" ");
			}
		}
		else if(ae.getSource()==townid){//return validation of aadhaar
			try{
			if(select==2){
				String h=townid.getText();
				boolean bsave=ce.CheckAadhaar(h);
				try{
					if(bsave==true){
						status=1;
						vid.setText(" ");
					}
					else if(bsave==false){
						status=0;
						vid.setText("Invalid Aadhaar Number");
					}
				}
				catch(StringIndexOutOfBoundsException e){
					status=0;
					vgs.setText("Invalid Aadhaar Number");
				}
			}
			else if(select==1){//return validation of pan
				String h=townid.getText();
				boolean bsave=ce.CheckPAN(h);
				try{
					if(bsave==true){
						status=1;
						vid.setText(" ");
					}
					else if(bsave==false){
						status=0;
						vid.setText("Invalid PAN Number");
					}
				}
				catch(StringIndexOutOfBoundsException e){
					status=0;
					vgs.setText("Invalid PAN Number");
				}
			}
			}
			catch(Exception e){
				System.err.println(e);
			}
		}
		else if(ae.getSource()==tphone){	//return validation of phoneno
			String ph=tphone.getText();
			boolean b=ce.CheckPhone(ph);
			if(b==false){
				vph.setText("Only Digits are Allowed");
				status=0;
			}
			else if(b==true){
				status=1;
				vph.setText(" ");
			}
		}
	}
	public void actionPerformed(ActionEvent ar) {//save data in database
		String id;
		if(ar.getSource()==bsave){
			if(status==1){
				d1=new DataUtil();
				Connection c=d1.getConnection();
				String city=jcity.getSelectedItem().toString();
				ArrayList<String> data=new ArrayList<String>();
				String t=tcname.getText().substring(0,3);
				String u="001";
				id=t.concat(u);
				data.add(id);
				data.add(tcname.getText());
				data.add(tgst.getText());
				if(select==1){
					data.add(townid.getText());
					data.add("N/A");
				}
				else if(select==2){
					data.add("N/A");
					data.add(townid.getText());
				}
				data.add(town1.getText());
				data.add(town2.getText());
				data.add(taddres1.getText());
				data.add(taddres2.getText());
				data.add(tphone.getText());
				data.add(city);
				data.add(temail.getText());
				data.add("Y");
				data.add(ldate.getText());
				String msg=d1.insert(c, "company_info", data);
				JOptionPane.showMessageDialog(getContentPane(), msg);
				cc=cc+1;
				setTextBoxDisabled();
			}
			else if(status==0){
				JOptionPane.showMessageDialog(getContentPane(), "Validations Not Completed....Unable to Save");
			}
		}
		else if(ar.getSource()==bedit && check.equals("EDIT")){//for edit 
			bsave.setEnabled(false);
			townid.setEnabled(false);
			town1.setEnabled(true);
			town2.setEnabled(true);
			taddres1.setEnabled(true);
			taddres2.setEnabled(true);
			tphone.setEnabled(true);
			temail.setEnabled(true);
			jcity.setEnabled(true);
			check="UPDATE";
			bedit.setText(check);
		}
		else if(ar.getSource()==bedit && check.equals("UPDATE")){//for save after in database
			d1=new DataUtil();
			Connection c=d1.getConnection();
			String city=(String)jcity.getSelectedItem();
			ArrayList<String> data=new ArrayList<String>();
			data.add(town1.getText());
			data.add(town2.getText());
			data.add(taddres1.getText());
			data.add(taddres2.getText());
			data.add(tphone.getText());
			data.add(city);
			data.add(temail.getText());
			String msg=d1.UpdateData(c, "company_info", data, tcname.getText());
			JOptionPane.showMessageDialog(getContentPane(), msg);
			new Company_Master();
			this.dispose();
		}
		if(ar.getSource()==bback)//for Back after in database
			if(backstatus==1)
			{						
					setVisible(false);
					MenuList obj=new MenuList();
					obj.setVisible(true);
			}
			else if(backstatus==2){
				
				Company_Master obj=new Company_Master();
				obj.setVisible(true);
			}
		}
	public void setTextBoxDisabled(){
		tcname.setEnabled(false);
		tgst.setEnabled(false);
		townid.setEnabled(false);
		town1.setEnabled(false);
		town2.setEnabled(false);
		taddres1.setEnabled(false);
		taddres2.setEnabled(false);
		tphone.setEnabled(false);
		temail.setEnabled(false);
		jcity.setEnabled(false);
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
	public void setTextBoxEnabled(){
		town1.setEnabled(true);
		town2.setEnabled(true);
		taddres1.setEnabled(true);
		taddres2.setEnabled(true);
		tphone.setEnabled(true);
		temail.setEnabled(true);
		jcity.setEnabled(true);
	}
	public void setBlank(){
		tcname.setText(" ");
		tgst.setText(" ");
		townid.setText(" ");
		town1.setText(" ");
		town2.setText(" ");
		taddres1.setText(" ");
		taddres2.setText(" ");
		tphone.setText(" ");
		temail.setText(" ");
	}
}
