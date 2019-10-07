import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;

public class Discount_Offer extends JFrame implements ActionListener {
	JLabel Mail_head;
	ButtonGroup bg;
	JRadioButton FOffer1,FOffer2,FOffer3,FOffer4;
	JButton mail;
	SimpleDateFormat df;
	Date dates,predate;
	Calendar cal;
	String todate,predates;
	public Discount_Offer(){
		
		 df = new SimpleDateFormat("dd-MM-yyyy");
        dates = new Date();
         todate = df.format(dates);
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        predate = cal.getTime();    
         predates = df.format(predate);
         Date d=new Date();
			java.sql.Date dd=new java.sql.Date(d.getTime());
			System.out.println("hello dates"+dd);
        System.out.println(todate);
        System.out.println(predates);
		setTitle("Send Offer Mail");
		
		Mail_head=new JLabel("SEND OFFER");
		Mail_head.setBounds(450, 120, 350, 40);
		Mail_head.setFont(new Font("Elephant", Font.PLAIN, 20));
		Mail_head.setForeground(Color.GRAY);
		add(Mail_head);
		
		FOffer1=new JRadioButton("Last week Customer");
		FOffer1.setBounds(460,160,150,40);
		add(FOffer1);
		
		FOffer2=new JRadioButton("Shop>1000");
		FOffer2.setBounds(460,220,350,40);
		add(FOffer2);
		
		FOffer3=new JRadioButton("Retailer");
		FOffer3.setBounds(460,280,350,40);
		add(FOffer3);
		
		FOffer4=new JRadioButton("All");
		FOffer4.setBounds(460,340,350,40);
		add(FOffer4);
		
		bg=new ButtonGroup();
		bg.add(FOffer1);
		bg.add(FOffer2);
		bg.add(FOffer3);
		bg.add(FOffer4);
		
		FOffer1.addActionListener(this);
		FOffer2.addActionListener(this);
		FOffer3.addActionListener(this);
		FOffer4.addActionListener(this);
		mail=new JButton("Send Mail");
		mail.setBounds(460,400,100,40);
		add(mail);
		setExtendedState(MAXIMIZED_BOTH);
		setLayout(null);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Discount_Offer();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==FOffer1){
			
		}
		else if(e.getSource()==FOffer2){
			
		}
		else if(e.getSource()==FOffer3){
			
		}
		else if(e.getSource()==FOffer4){
			
		}


	}

}
