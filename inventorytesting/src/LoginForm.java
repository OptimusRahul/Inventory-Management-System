import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.swing.*;
import javax.swing.plaf.LayerUI;

import com.util.DataUtil;

public class LoginForm extends JFrame implements ActionListener{
	static String check;
	static JRadioButton urb,arb;
	static JLabel head,lname,pass,img;
	static ButtonGroup bg;
	static JTextField jt;
	static JPasswordField tp;
	static JButton login ,forgot,reset;
	static ImageIcon ic; 
	Font f,f1;
	public LoginForm() {
		
		LayerUI<JPanel> layerUI = new SpotlightLayerUI();
	    JPanel panel = createPanel();
	    JLayer<JPanel> jlayer = new JLayer<JPanel>(panel, layerUI);
	    add(jlayer);
	    f=new Font("SERIF",Font.ITALIC,40);
	    f1=new Font("SERIF",Font.ITALIC,20);
	    arb.setFont(f1);
	    urb.setFont(f1);
	    lname.setFont(f1);
	    pass.setFont(f1);
	    head.setFont(f);
	    arb.addActionListener(this);
	    urb.addActionListener(this);
	    login.addActionListener(this);
	    forgot.addActionListener(this);
	    reset.addActionListener(this);
	    Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
	    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo (null);
	    setVisible (true);
	}
  public static void main(String[] args) {
        new LoginForm();
  }
  private static JPanel createPanel() {
    JPanel p = new JPanel( new GridLayout(0, 1));
    head=new JLabel("LOGIN FORM");
    p.add(head);
    head.setBounds(500, 30, 500, 80);
    ic=new ImageIcon("login.png");
    img=new JLabel(ic);
    img.setBounds(650, 200, 271, 238);
    p.add(img);
    img.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(12,0,0)));
    bg=new ButtonGroup();

    arb=new JRadioButton("ADMIN");
    p.add(arb);
    bg.add(arb);
    arb.setBounds(150, 200,150, 30);
    
    urb=new JRadioButton("USER");
    p.add(urb);
    urb.setBounds(300, 200, 150, 30);
    bg.add(urb);
    
    
     lname=new JLabel("USER NAME");
    p.add(lname);
    lname.setBounds(100, 300, 150, 30);
    
    jt=new JTextField(10);
    p.add(jt);
    jt.setBounds(300, 300, 150, 30);
    
    pass=new JLabel("PASSWORD");
    p.add(pass);
    pass.setBounds(100, 350, 150, 30);
    
    tp=new JPasswordField(10);
    p.add(tp);
    tp.setBounds(300, 350, 150, 30); 
    
    login=new JButton("LOGIN");
    p.add(login);
    login.setBounds(100, 400, 100, 30);
    
    forgot=new JButton("Forgot Password");
    p.add(forgot);
    forgot.setBounds(250, 400, 100, 30);
    
    reset=new JButton("Reset");
    p.add(reset);
    reset.setBounds(400, 400, 100, 30);
    
    p.setLayout(null);
    return p;
  
}

class SpotlightLayerUI extends LayerUI<JPanel> {
  private boolean mActive;
  private int mX, mY;
  public void installUI(JComponent c) {
    super.installUI(c);
    JLayer jlayer = (JLayer)c;
    jlayer.setLayerEventMask(
      AWTEvent.MOUSE_EVENT_MASK |
      AWTEvent.MOUSE_MOTION_EVENT_MASK
    );
  }

  public void uninstallUI(JComponent c) {
    JLayer jlayer = (JLayer)c;
    jlayer.setLayerEventMask(0);
    super.uninstallUI(c);
  }
  public void paint (Graphics g, JComponent c) {
    Graphics2D g2 = (Graphics2D)g.create();
    super.paint (g2, c);
    if (mActive) {
      java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(mX, mY);
      float radius = 150;
      float[] dist = {0.0f, 1.0f};
      Color[] colors = {new Color(0.0f,0.0f,0.0f,0.0f), new Color(0,0,0)};
      RadialGradientPaint p =
          new RadialGradientPaint(center, radius, dist, colors);
      g2.setPaint(p);
      g2.setComposite(AlphaComposite.getInstance(
          AlphaComposite.SRC_OVER, .6f));
      g2.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    g2.dispose();
  }
  protected void processMouseEvent(MouseEvent e, JLayer l) {
    if (e.getID() == MouseEvent.MOUSE_ENTERED) mActive = true;
    if (e.getID() == MouseEvent.MOUSE_EXITED) mActive = false;
    l.repaint();
  }
  protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
    Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
    mX = p.x;
    mY = p.y;
    l.repaint();
  }
}
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==login ){
		DataUtil obj=new DataUtil<>();
		Connection con=obj.getConnection();
		int g=0;
		String u=jt.getText();
		String p=tp.getText();
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
	else if(e.getSource()==reset){
		jt.setText(null);
		tp.setText(null);
	}
	else if(e.getSource()==forgot)
	{
		new Forget_Password();
		this.dispose();
	}
	if(e.getSource()==arb){
		check="admin";
	}
	else if(e.getSource()==urb){
		check="user";
	}
}
}
