import javax.swing.JFrame;
import javax.swing.JTree;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.tree.*;
import java.sql.*;


public class Categorystructure1 extends JFrame implements MouseListener {

	JTree jt;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	int rcount,i=0;
	DefaultMutableTreeNode roots;
	
	public Categorystructure1()
	{
		DefaultMutableTreeNode roots=new DefaultMutableTreeNode("CATEGORYS");
		DefaultMutableTreeNode subroot=new DefaultMutableTreeNode("ItemName");
		roots.add(subroot);
		DefaultMutableTreeNode subroot1=new DefaultMutableTreeNode("Category_Name");
		roots.add(subroot1);
		DefaultMutableTreeNode subroot2=new DefaultMutableTreeNode("GST_No");
		roots.add(subroot2);
		//DefaultMutableTreeNode nodes=new DefaultMutableTreeNode("hello");
		retrvData();
	
		for(int j=0;j<rcount;i++)
		{
			DefaultMutableTreeNode nodes=(DefaultMutableTreeNode) new DefaultMutableTreeNode();
			roots.add(nodes);
			//subroot.add(nodes);
			//subroot1.add(nodes);
			//subroot2.add(nodes);	
		}
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","root");
		    stmt=conn.createStatement();
		    rs=stmt.executeQuery("SELECT ItemName, Category_Name, GST_No FROM inventory.itemcategory");
		    while(rs.next())
		    {
		    	DefaultMutableTreeNode ItemName=new DefaultMutableTreeNode(rs.getString("ItemName"));
		    	subroot.add(ItemName);
		    	DefaultMutableTreeNode Category_Name=new DefaultMutableTreeNode(rs.getString("Category_Name"));
		    	subroot1.add(Category_Name);
		     	DefaultMutableTreeNode GST_No=new DefaultMutableTreeNode(rs.getString("GST_No"));
		     	subroot2.add(GST_No);
		     //	roots.add(new DefaultMutableTreeNode(rs.getString(i+1)));
		     	//subroot.add(new DefaultMutableTreeNode(rs.getString(1)));
		     	//subroot1.add(new DefaultMutableTreeNode(rs.getString(2)));
		     	//subroot2.add(new DefaultMutableTreeNode(rs.getString(3)));
				//i++;
		    }
			
		}
		catch (Exception ex) {
	
			System.out.println(ex.toString());
		}
        jt=new JTree(roots);
        add(jt);
		setVisible(true);
		setSize(600, 600);	
	}
	private void retrvRowCount() {
		// TODO Auto-generated method stub
		
	}
	private void retrvData() {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		
		Categorystructure1 obj=new Categorystructure1();

		
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		 mouseClicked(me);
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
