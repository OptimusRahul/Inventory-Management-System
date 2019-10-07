import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.io.FileReader;
import java.util.Properties;
import javax.swing.*;

import sun.net.www.content.image.jpeg;

public class WelCome_Page extends JFrame implements Runnable{

	ImageIcon Backimage;
	JProgressBar jProgressBar;
	JLabel heading,background;
	JButton jbExit;
	Font font;
	Thread thread;
	Properties pr;
	FileReader fr;
	int i=0,j=300;
	
	public WelCome_Page() {
		setTitle("WelCome Page");
		getLabels();
		Backimage=new ImageIcon("stocks.jpg");
		background=new JLabel(Backimage);
		setContentPane(background);
		setBackground(new Color(0,0,0,0));
		jProgressBar=new JProgressBar(i,j);
		heading=new JLabel(pr.getProperty("heading"));
		font= new Font("Rockwell Extra Bold", Font.ITALIC, 70);
		
		setLayout(null);
		heading.setBounds(30, 170, 1500, 100);
		jProgressBar.setBounds(300, 450, 700, 40);
		jProgressBar.setStringPainted(true);
		
		add(heading);
		add(jProgressBar);

		heading.setFont(font);
		heading.setForeground(new Color(240,0,150));
		jProgressBar.setForeground(new Color(200,0,150));
			
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width,screenSize.height);
	
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		thread=new Thread(this);
		thread.start();
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {   
            public void run() {
          	new WelCome_Page();
            }
	});	
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
	public void run() {
		while(i<j){
		try {
			Thread.sleep(200);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error is "+e);
		}
			i=i+5;
			jProgressBar.setValue(i);
			
			} 
		if(i<=j){
			new LoginForm();
			this.dispose();
		}
		
		
		
	}

}
