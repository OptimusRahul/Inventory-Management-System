import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class Offers_Apply extends JFrame implements ActionListener{
	JLabel lhead, loffername;
	Font fh, fl;
	JButton bday, bweek;
	JComboBox<String> cOfferName,mailoffer;
	public Offers_Apply() {
		super("Pending Orders");
		setLayout(null);
		lhead=new JLabel("Apply Offer");
		add(lhead);
		lhead.setBounds(575, 50, 200, 50);
		fh=new Font("Arial",Font.BOLD, 30);
		lhead.setFont(fh);
		loffername=new JLabel("Select Offer");
		add(loffername);
		fl=new Font("Arial",Font.PLAIN,20);
		loffername.setBounds(400, 150, 150, 20);
		loffername.setFont(fl);
		cOfferName=new JComboBox<String>();
		add(cOfferName);
		cOfferName.setBounds(600, 150, 275, 25);
		cOfferName.setFont(fl);
		cOfferName.addActionListener(this);
		bday=new JButton("Apply for a Day");
		add(bday);
		bday.setBounds(400, 200, 200, 35);
		bday.setFont(fl);
		bweek=new JButton("Apply for a Week");
		add(bweek);
		bweek.setBounds(675, 200, 200, 35);
		bweek.setFont(fl);
		
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Offers_Apply();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cOfferName){
			
		}
		
	}

}
