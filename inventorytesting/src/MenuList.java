
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;


public class MenuList extends JFrame implements ActionListener {
JMenuBar Jmb;
JMenu Jcompany,Jsupplier,Jcategory,Jitementry,Jiteminregister,Junit,JBilling,JReport,JHelp,JCustomer,register;
JMenuItem JCadd,JCopenacc,JSadd,JSsearch,JCsearch,Jhelp,JSnavigate,JCGadd,JCatGoods,Jcustomer,JCGsearch,JCGnavigate,JIEadd,JIEdelete,JIEsearch,JIEnavigate,JIRadd,JIRdelete,JIRsearch,JIRnavigate,JUnitsizemaster,JUnitnavigation,JUnitmaster,addregister,pendingorder,orderbilling;
Properties pr;
FileReader fr;
public MenuList()
{
	Jmb=new JMenuBar();
	getLabels();
	Jcompany=new JMenu(pr.getProperty("company"));
	Jcompany.setMnemonic(KeyEvent.VK_C);
	Jsupplier=new JMenu(pr.getProperty("supplier"));
	Jsupplier.setMnemonic(KeyEvent.VK_S);
	Jcategory=new JMenu(pr.getProperty("catname"));
	Jcategory.setMnemonic(KeyEvent.VK_T);
	Jitementry=new JMenu(pr.getProperty("itementry"));
	Jitementry.setMnemonic(KeyEvent.VK_E);
	Jiteminregister=new JMenu(pr.getProperty("itemin"));
	Jiteminregister.setMnemonic(KeyEvent.VK_I);
	Junit=new JMenu(pr.getProperty("unit"));
	Junit.setMnemonic(KeyEvent.VK_U);
	JReport=new JMenu("SHOW REPORTS");
	JReport.setMnemonic(KeyEvent.VK_R);
    JHelp=new JMenu("HELP");
	JHelp.setMnemonic(KeyEvent.VK_H);
    JCustomer=new JMenu("CUSTOMER");
    JCustomer.setMnemonic(KeyEvent.VK_C);
    register=new JMenu(pr.getProperty("JBregister"));
    register.setMnemonic(KeyEvent.VK_G);
	JBilling=new JMenu("BILLING");
	JBilling.setMnemonic(KeyEvent.VK_B);
	pendingorder=new JMenuItem("PENDING ORDER");
	orderbilling=new JMenuItem("ORDER BILLING");
	JCadd=new JMenuItem(pr.getProperty("add"));
	JCadd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
	JCopenacc=new JMenuItem(pr.getProperty("openacc"));
	JCopenacc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
	JSadd=new JMenuItem(pr.getProperty("add"));
	JSadd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
	JSsearch=new JMenuItem("SEARCH SUPPLIERS");
	JSsearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
	JSnavigate=new JMenuItem(pr.getProperty("navi"));
	JSnavigate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
	JCGadd=new JMenuItem(pr.getProperty("itemadd"));
	JCGadd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,ActionEvent.CTRL_MASK));
	JCatGoods=new JMenuItem(pr.getProperty("add")+" "+pr.getProperty("catname"));
	JCatGoods.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
	JCGsearch=new JMenuItem("SEARCH CATEGORY");
	JCGsearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
	JCGnavigate=new JMenuItem(pr.getProperty("navi"));
	JCGnavigate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));
	JIEadd=new JMenuItem(pr.getProperty("add"));
	JIEadd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
	JIEdelete=new JMenuItem(pr.getProperty("del"));
	JIEdelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
	JIEsearch=new JMenuItem("SEARCH ITEM");
	JIEsearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
	JIEnavigate=new JMenuItem(pr.getProperty("navi"));
	JIEnavigate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
    JIRadd=new JMenuItem(pr.getProperty("add"));
    JIRadd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,ActionEvent.CTRL_MASK));
    JIRdelete=new JMenuItem(pr.getProperty("del"));
    JIRdelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,ActionEvent.CTRL_MASK));
    JIRsearch=new JMenuItem(pr.getProperty("search"));
    JIRsearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
    JIRnavigate=new JMenuItem(pr.getProperty("navi"));
    JIRnavigate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
    JUnitmaster=new JMenuItem(pr.getProperty("um_head1"));
    JUnitmaster.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
    JUnitsizemaster=new JMenuItem(pr.getProperty("usm_head"));
    JUnitsizemaster.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
    JUnitnavigation=new JMenuItem(pr.getProperty("usm_head")+" "+pr.getProperty("navi"));
    JUnitnavigation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));
    JCsearch=new JMenuItem("SEARCH CUSTOMERS");
    JCsearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
    Jhelp=new JMenuItem("HELP US");
    Jhelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
    Jcustomer=new JMenuItem("CUSTOMER");
    Jcustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
    addregister=new JMenuItem("Add Register");
    addregister.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));
   
    Jcompany.add(JCadd);
    Jcompany.add(JCopenacc);
    Jsupplier.add(JSadd);
   // Jsupplier.add(JSsearch);
    Jsupplier.add(JSnavigate);
    Jcategory.add(JCatGoods);
   // Jcategory.add(JCGsearch);
    Jcategory.add(JCGnavigate);
    Jcategory.add(JCGadd);
    Jitementry.add(JIEadd);
    Jitementry.add(JIEdelete);
    //Jitementry.add(JIEsearch);
    Jitementry.add(JIEnavigate);
    Jiteminregister.add(JIRadd);
    Jiteminregister.add(JIRdelete);
    register.add(addregister);
  //  Jiteminregister.add(JSsearch);
   	Jiteminregister.add(JIRnavigate);
   	Junit.add(JUnitmaster);
   	Junit.add(JUnitsizemaster);
   	Junit.add(JUnitnavigation);
   	JBilling.add(pendingorder);
   	JBilling.add(orderbilling);
   	JReport.add(JCGsearch);
	JReport.add(JSsearch);
	JReport.add(JIEsearch);
	JReport.add(JCsearch);
	JHelp.add(Jhelp);
	JCustomer.add(Jcustomer);
   	Jmb.add(Jcompany);
	Jmb.add(Jcategory);
	Jmb.add(Jsupplier);
	Jmb.add(Junit);
	Jmb.add(Jitementry);
	Jmb.add(JCustomer);
	Jmb.add(Jiteminregister);
	Jmb.add(JBilling);
	Jmb.add(JReport);
	Jmb.add(JHelp);
	Jmb.add(register);
	
	setJMenuBar(Jmb);
	 JCadd.addActionListener(this);
		JCopenacc.addActionListener(this);
	    JSadd.addActionListener(this);
	    JSsearch.addActionListener(this);
	    JSnavigate.addActionListener(this);
	    JCGsearch.addActionListener(this);
	    JCGadd.addActionListener(this);
	    JCGnavigate.addActionListener(this);
	    JIEadd.addActionListener(this);
	    JUnitmaster.addActionListener(this);
	    JUnitsizemaster.addActionListener(this);
	    JCatGoods.addActionListener(this);
	    JUnitnavigation.addActionListener(this);
	    JIRadd.addActionListener(this);
	   JIEsearch.addActionListener(this);
	   JCsearch.addActionListener(this);
	   Jhelp.addActionListener(this);
	   Jcustomer.addActionListener(this);
	   addregister.addActionListener(this);
	   pendingorder.addActionListener(this);
	   orderbilling.addActionListener(this);
	    setLayout(null);
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
	    JLabel background=new JLabel(new ImageIcon("D:\\All project\\Inventory\\Inventory\\src\\wallpaper.png"));
	    add(background);
	    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
	setVisible(true);
   
}
	public static void main(String[] args) {
		new MenuList();

	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==JCadd)
		{
		new Company_Master();
		this.dispose();
	}
		else if(ae.getSource()==JCopenacc)
		{
		new Company_Opening_Account();
		this.dispose();
		}
		else if(ae.getSource()==JSadd)
		{
			new Supplier_Entry();
			this.dispose();	
		}
		else if(ae.getSource()==JSnavigate)
		{
			new Supplier_Nav();
			this.dispose();	
		}
		else if(ae.getSource()==JSsearch)
		{
			new Supplier_Detail();
			this.dispose();
		}
		else if(ae.getSource()==JCGsearch)
		{
			new GST_Detail();
			this.dispose();
		}
		else if(ae.getSource()==JCGadd)
		{
			new Item_Entry();
			this.dispose();
		}
		else if(ae.getSource()==JCatGoods)
		{
			new Category_Entry();
			this.dispose();
		}
		else if(ae.getSource()==JUnitnavigation)
		{
			new USM_Nav();
			this.dispose();
		}
		else if(ae.getSource()==JIRadd)
		{
			new Stock_Inregister();
			this.dispose();
		}
		else if(ae.getSource()==JCGnavigate)
		{
			new Category_Nav() ;
			this.dispose();
		}
		else if(ae.getSource()==JIEadd)
		{
			new Stock_Opening();
			this.dispose();	
		}
		else if(ae.getSource()==JUnitmaster)
		{	
			new Unit_Master();
			this.dispose();
		}
		else if(ae.getSource()==JUnitsizemaster)
		{
			new UnitSizeMaster();
			this.dispose();
		}
		else if(ae.getSource()==JIEsearch)
		{
			new Item_Detail();
			this.dispose();
		}
		else if(ae.getSource()==JCsearch)
		{
			new Customer_Detail();
			this.dispose();
		}
		else if(ae.getSource()==Jcustomer)
		{
			new Customer_Entry();
			this.dispose();
		}
		else if(ae.getSource()==Jhelp)
		{
	
			this.dispose();
		}
		else if(ae.getSource()==addregister)
		{
			new UserForm();
			this.dispose();
		}
		else if(ae.getSource()==pendingorder)
		{
			new Pending_Order();
			this.dispose();
		}
		else if(ae.getSource()==orderbilling)
		{
			new Item_Billing();
			this.dispose();
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
	public void Enabledfalse(String stat)
	{
			JCopenacc.setEnabled(false);
			JSadd.setEnabled(false);
			JCGadd.setEnabled(false);
			JCatGoods.setEnabled(false);
			JIEadd.setEnabled(false);
			JIEdelete.setEnabled(false);
			JSadd.setEnabled(false);
			JSsearch.setEnabled(false);
			Junit.setEnabled(false);
			Jiteminregister.setEnabled(false);
		
	}

}
		
		


