package com.util;
import java.io.FileReader;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;
public class DataUtil<rs> {
	Connection con;
	java.sql.Statement st;
	
	ResultSet rs;
	PreparedStatement pst;
	String retst1,retst;
	String q=null;
	FileReader fr;
	Properties pr;
	/*This function is uesd to get connection and load Driver
*/	
	public Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","root");
		}
		catch (Exception e)
		{
			e.toString();
		}
		return con;
	}
	public String gst_slab(Connection m, String table_name,String cat_id,String cat_name,String cat_gst,int Operation){
		
		if(Operation==1){
			String query = "INSERT INTO "+table_name+" VALUES (?,?,?,?)";
			try{
				PreparedStatement pst=((java.sql.Connection) m).prepareStatement(query);
				pst.setString(1, cat_id);
				pst.setString(2, cat_name);
				pst.setString(3, cat_gst);
				pst.setString(4, "Y");
				pst.executeUpdate();		
			}
			catch (Exception ee) {
				System.out.println(ee.toString());
				return ee.toString();
			}
			return "Data Inserted Successfully";
		}
		else if(Operation==2){
			String query = "UPDATE "+table_name+" SET Cat_Name=(?),Gst_No=(?) WHERE Cat_ID=(?) AND Status='Y'";
			try{
				PreparedStatement ps1=((java.sql.Connection) m).prepareStatement(query);
				
				ps1.setString(1, cat_name);
				ps1.setString(2, cat_gst);
				ps1.setString(3, cat_id);
				ps1.executeUpdate();
				con.close();
			}
			catch (Exception arg2) {
				return arg2.toString();
		    }
		    return "data Updated";
		}
		
		else if(Operation==3){
			String query="UPDATE "+table_name+" SET Status='N' WHERE Cat_ID=?";
			try{
				PreparedStatement ps1=((java.sql.Connection) m).prepareStatement(query);
				ps1.setString(1, cat_id);
				ps1.executeUpdate();
				con.close();
			}
			catch (Exception arg1){
				return arg1.toString();
		    }
		    return "data deleted";
		}

		return "Problem Persisting in the code.";
	}
	
	public String itemcategory(Connection m, String table_name,String item_name,String cat_id,String cat_name,String cat_gst,int Operation){
		Calendar cal=Calendar.getInstance();
		java.sql.Date dd=new java.sql.Date(cal.getTime().getTime());
		if(Operation==1){
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
			String query = "INSERT INTO "+table_name+" VALUES (?,?,?,?,?,?)";
			try{
				PreparedStatement pst=((java.sql.Connection) m).prepareStatement(query);
				pst.setString(1, item_name);
				pst.setString(2, cat_id);
				pst.setString(3, cat_name);
				pst.setString(4, cat_gst);
				pst.setDate(5, dd);
				pst.setString(6,"Y");
				pst.executeUpdate();		
			}
			catch (Exception ee) {
				return ee.toString();
			}
			return "Data Inserted Successfully";
		}
		return "Problem Persisting in the code.";
	}

	public String openingaccount(Connection m, String table_name,String com_id,String com_name,String com_email,int com_openamt,int Operation){
		Calendar cal=Calendar.getInstance();
		java.sql.Date dd=new java.sql.Date(cal.getTime().getTime());
		if(Operation==1){
			Date d = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
			String query = "INSERT INTO  "+table_name+" VALUES(?,?,?,?,?,?)";
			try{
				PreparedStatement pst=((java.sql.Connection) m).prepareStatement(query);
				pst.setString(1, com_id);
				pst.setString(2, com_name);
				pst.setString(3, com_email);
				pst.setInt(4, com_openamt);
				pst.setDate(5, dd);
				pst.setString(6,"Y");
				pst.executeUpdate();		
			}
			catch (Exception ee) {
				System.out.println(ee);
			}
			return "Data Inserted Successfully";
		}
		return "Problem Persisting in the code.";
	}

	public String insert1(String query, Connection m, String in, String ci,	String cn,String gn) {
		try
		{
		PreparedStatement ps2=((java.sql.Connection) m).prepareStatement(query);
		ps2.setString(1, in);
		ps2.setString(2, ci);
		ps2.setString(3, cn);
		ps2.setString(4, gn);
		ps2.executeUpdate();
		
		}
		catch (Exception e) {
			return e.toString();
	}
		// TODO Auto-generated method stub
		return "SUBMIT....";
	}

	public String Update(String query, Connection m, String cn, String cname,
			String cgst) {
		try
		{
		PreparedStatement ps1=((java.sql.Connection) m).prepareStatement(query);
		ps1.setString(1, cn);
		ps1.setString(2, cname);
		ps1.setString(3, cgst);
	
		
		ps1.executeUpdate();
		con.close();
		}
		catch (Exception arg2) 
		{
			return arg2.toString();
	    }
	    return "Data Updated.....";
	
	}

	public ResultSet getResultsSupplierInfo()
	{
		Connection con=getConnection();
		ResultSet rs1=null;
		
		try{
			String str="select * from supplier_info,bank_info where supplier_info.Supplier_Id = bank_info.Supplier_ID and supplier_info.Status='Y'";
			st=con.createStatement();
			rs1=st.executeQuery(str);
			rs1.first();
		}
		catch(Exception e){ 
			System.out.println(e.toString());
			}
		return rs1;
	}

	public String Delete(String query, Connection m, String cn) {
		try
		{
		PreparedStatement ps1=((java.sql.Connection) m).prepareStatement(query);
		ps1.setString(1, cn);
		ps1.executeUpdate();
		con.close();
		}
		catch (Exception arg1) 
		{
			return arg1.toString();
	    }
	    return "Data Deleted.....";
	}
	public String getFullInfo(String col1,String ta1,String col2,String ta2,String col_name)
	{
		String real=null;
		String value_red = null;
		try{
			Connection c=getConnection();
			String str1="select "+col1+" from "+ta1+"";
			PreparedStatement pst=c.prepareStatement(str1);
			System.out.println(pst.toString());
			ResultSet rst=pst.executeQuery();
			rst.first();
			//while(rst.next()){
			value_red=rst.getString(1);
			//}
			
			System.out.println(rst.toString());
			String str2="select "+col2+" from "+ta2+" where "+col_name+"=?";
			PreparedStatement pst2=c.prepareStatement(str2);
			
			pst2.setString(1, value_red);
			ResultSet rst2=pst2.executeQuery();
			System.out.println(pst2.toString());
			while(rst2.next())
			{
				real=rst2.getString(1);
			}
			c.close();		
		}
		catch(Exception e){
			e.toString();
		}
		return real;
	}

		public String AllId(String Type, String table, String column)
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Inventory","root","root");
			st=con.createStatement();
			rs=st.executeQuery("SELECT "+column+" FROM "+table);
			if(rs.next())
			{
				rs.last();
				String st1=rs.getString(1);
				int x=Integer.parseInt(st1.substring(3,9));
				x=x+1;
				if(x<10)
				{
					 retst=Type+"00000"+String.valueOf(x);
				}
				else if(x>=10 && x<100)
				{
					 retst=Type+"0000"+String.valueOf(x);
				}
				else if(x>=100 && x<1000)
				{
					 retst=Type+"000"+String.valueOf(x);
				}
				else if(x>=1000 && x<10000)
				{
					 retst=Type+"00"+String.valueOf(x);
				}
				else if(x>=10000 )
				{
					 retst=Type+"0"+String.valueOf(x);
				}

			}
			else 
			{
				 retst=Type+"000001";
			}
		
	}
		catch(Exception ee)
		{
			System.out.println(ee.toString());
		}
		return retst;	
	}
		public ResultSet getResultsShow(String tname)
	{
		Connection c=getConnection();
		ResultSet rs1=null;
		
		try{
			String str="SELECT * FROM "+tname+" WHERE Status='Y'";
			st=con.createStatement();
			rs1=st.executeQuery(str);
			rs1.first();
		}
		catch(Exception e){ 
			System.out.println(e.toString());
			System.out.println("i am catch");
			}
		return rs1;
	}
	public java.sql.ResultSet getResults(String string) {
		// TODO Auto-generated method stub
		return null;
	}
				public String SupplierData(Connection con2, String tablename, String[] data,String dates) {
			String ret;
			try{
			int b=data.length;
			String s="?";
			for(int i=0; i<b+1; i++){
				s=s.concat(",?");
			}
			q="INSERT INTO "+tablename+" VALUES("+s+")";
			PreparedStatement ps1=con2.prepareStatement(q);
			for(int i=0,m=1;i<10;i++,m++)
			ps1.setString(m, data[i]);
			ps1.setDate(11, java.sql.Date.valueOf(dates));
			ps1.setString(12, "Y");
			ps1.executeUpdate();
			ret="data save";
			}
			catch(Exception e){
				ret=e.toString();
			}
			return ret;
		}
		public String Update_SupplierData(Connection con2, String...data) {
			String ret;
			try{
				int b=data.length;		
			q="UPDATE inventory.supplier_info SET  Supplier_Name=?,Supplier_GST=?,PAN_No=?,Address=?,City=?,Phone_No=?,Email_ID=?,Cat_id=?,BankAccount=?,Dates=? WHERE Supplier_Id=? and  Status=?";
			PreparedStatement ps1=con2.prepareStatement(q);
			ps1.setString(1, data[0]);
			ps1.setString(2, data[1]);
			ps1.setString(3, data[2]);
			ps1.setString(4, data[3]);
			ps1.setString(5, data[4]);
			ps1.setString(6, data[5]);
			ps1.setString(7, data[6]);
			ps1.setString(8, data[7]);
			ps1.setString(9, data[8]);
			ps1.setDate(10, java.sql.Date.valueOf(data[9]));
			ps1.setString(11, data[10]);
			ps1.setString(12, "Y");
			ps1.executeUpdate();
			ret="data Update";
			}
			catch(Exception e){
				ret=e.toString();
			}
			return ret;
		}	
		public String Update_BankData1(Connection con2, String...data) {
			String ret;
			try{
				int b=data.length;		
			q="UPDATE inventory.bank_info SET Supplier_Name=?,Bank_Name=?,Bank_Account=?,IFSC=? WHERE Supplier_Id=?";
			PreparedStatement ps1=con2.prepareStatement(q);
			ps1.setString(1, data[0]);
			ps1.setString(2, data[1]);
			ps1.setString(3, data[2]);
			ps1.setString(4, data[3]);
			ps1.setString(5, data[4]);
			ps1.executeUpdate();
			ret="data Update";
			}
			catch(Exception e){
				ret=e.toString();
			}
			return ret;
		}
		public String BankInfo(Connection con2, String table, String...bank) {
			String ret;
			try{
			int b=bank.length;
			String s="?";
			for(int i=0; i<b-1; i++){
				s=s.concat(",?");
			}
			q="INSERT INTO "+table+" VALUES("+s+")";
			System.out.println(q);
			PreparedStatement ps1=con2.prepareStatement(q);
			ps1.setString(1, bank[0]);
			ps1.setString(2, bank[1]);
			ps1.setString(3, bank[2]);
			ps1.setString(4, bank[3]);
			ps1.setString(5, bank[4]);
			ps1.executeUpdate();
			ret="data save";
			}
			catch(Exception e){
				ret=e.toString();
			}
			return ret;
		}
		public ArrayList getCompanyDetails()
		{
			ArrayList al=new ArrayList();
			Connection con=getConnection();
			try{
				String str="SELECT COMPID,COMPNAME,EMAIL FROM company_info where STATUS='Y'";
				st=con.createStatement();
				ResultSet rs1=st.executeQuery(str);
					if(rs1.first()==true)
					{
						al.add(rs1.getString(1));
						al.add(rs1.getString(2));
						al.add(rs1.getString(3));
					}
				}
			catch(Exception e){ 
				System.out.println(e.toString());
				}
			return al;
		}
		public boolean getCateoryName(String table,String colname,String name)
		{
			Connection con=getConnection();
			ResultSet rs1=null;
			try{
				String str="SELECT * from "+table+" where "+colname+"=? and status='Y'";
				pst=con.prepareStatement(str);
				pst.setString(1,name);
				rs1=pst.executeQuery();
				if (rs1.next())
					return true;
				else
					return false;
			}
			catch(Exception e){ 
				System.out.println(e.toString());
			return false;	
			}
		}

		public String insert(Connection c, String ta, ArrayList al){
			try{
			int b=al.size();
			b=b-1;
			String s="?";
			for(int i=0; i<b; i++){
				s=s.concat(",?");
			}
			System.out.println(c.toString());
			q="INSERT INTO "+ta+" VALUES("+s+")";
			System.out.println(q);
			PreparedStatement ps=c.prepareStatement(q);
			int m=0;
			for(int i=0;i<al.size();i++){
				m=m+1;
				System.err.println(al.get(i));
				ps.setString(m, (String)al.get(i));
			}
			System.out.println(ps.toString());
			int f=ps.executeUpdate();
			}
			catch(Exception e){
				System.out.println("Exception--"+e.toString());
			}
			String msg="Saved";
			return msg;
		}
		public String orderadd(Connection c, String ta, String...al){
				String msg="";
				/*Date d=new Date();
				java.sql.Date dd=new java.sql.Date(d.getTime());*/
				Date d=new Date();
				SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy");
				String dd=df.format(d);
			try{
			q="INSERT INTO "+ta+" VALUES(?,?,?,?,?,?)";
			System.out.println(q);
			PreparedStatement ps=c.prepareStatement(q);
				ps.setString(1, al[0]);
				ps.setString(2, al[1]);
				ps.setString(3, al[2]);
				ps.setString(4, dd);
				ps.setFloat(5, Float.parseFloat(al[3]));
				ps.setString(6, al[4]);
			int f=ps.executeUpdate();
			msg="Saved";
			}
			catch(Exception e){
				msg=e.toString();
			}
			return msg;
		}

		public ResultSet getResultsCustomer_Info()
		{
			Connection con=getConnection();
			ResultSet rs1=null;
			
			try{
				String str="SELECT * FROM customer_info where Status='Y'";
				st=con.createStatement();
				rs1=st.executeQuery(str);
				rs1.first();
			}
			catch(Exception e){ 
				System.out.println(e.toString());
				}
			return rs1;
		}
		public String Update_CustomerData(Connection con2, String...data) {
			String ret;
			for(int x=0;x<data.length; x++)
			{
				System.err.println(data[x]);
			}
			try{
				int b=data.length;		
			q="UPDATE customer_info SET  CustomerName = ?, CustomerPhone = ?, address = ?, CustomerEmail = ?, CustDate = ?, Status = ? 	WHERE  Customer_ID=? and Status=?";
			PreparedStatement ps1=con2.prepareStatement(q);
			ps1.setString(2, data[0]);
			ps1.setString(3, data[1]);
			ps1.setString(4, data[2]);
			ps1.setString(5, data[3]);
			ps1.setString(6, data[4]);
			ps1.setString(8, "Y");
			ps1.executeUpdate();
			ret="data Update";
			}
			catch(Exception e){
				ret="Updation failed"+e.toString();
			}
			return ret;
		}
				public ArrayList getData(Connection c, String ta){
					ArrayList darray=new ArrayList();
					try{
						q="select * from "+ta;
						System.out.println(q);
						PreparedStatement ps=c.prepareStatement(q);
						ResultSet rs;
						rs=ps.executeQuery();
						int i=0;
						rs.first();
						for(int j=1;j<12;j++)
						{	
							darray.add(rs.getString(j));
						}
					}
					catch(Exception e){e.toString();}
					return darray;
				}
		public String tablemove(Connection c, String ta, ArrayList al, int count){
			String msg="";
			try{
				int b=al.size();
				String s="?";
				for(int i=0; i<8; i++)
				{
					s=s.concat(",?");
				}
				q="INSERT INTO "+ta+" VALUES("+s+")";
				PreparedStatement ps=c.prepareStatement(q);
					for(int i=0;i<b;i=i+9)
					{
						ps.setString(1, (String)al.get(i));
						System.out.println("1-"+(String)al.get(i));
						ps.setString(2, (String)al.get(i+1));
						System.out.println("2-"+(String)al.get(i+1));
						ps.setFloat(3,Float.parseFloat(al.get(i+2).toString()));	
						System.out.println("3-"+(String)al.get(i+2));
						ps.setString(4, (String)al.get(i+3));
						System.out.println("4-"+(String)al.get(i+3));
						ps.setFloat(5, Float.parseFloat(al.get(i+4).toString()));
						System.out.println("5-"+(String)al.get(i+4));
						ps.setString(6, (String)al.get(i+5));
						System.out.println("6-"+(String)al.get(i+5));
						ps.setFloat(7, Float.parseFloat(al.get(i+6).toString()));
						System.out.println("7-"+(String)al.get(i+6));
						ps.setFloat(8, Float.parseFloat(al.get(i+7).toString()));
						System.out.println("8-"+(String)al.get(i+7));
						ps.setString(9, (String)al.get(i+8));
						System.out.println("9-"+(String)al.get(i+8));
						int f=ps.executeUpdate();
					}
					msg="save";
			}
			catch(Exception e){
				System.out.println(msg=e.toString());
			}
			return msg;
		}

		public String insert(Connection c, String ta, String...al){
			String msg=null;
			try{
			int b=al.length;
			b=b-1;
			String s="?";
			for(int i=0; i<b; i++){
				s=s.concat(",?");
			}
			System.out.println(c.toString());
			q="INSERT INTO "+ta+" VALUES("+s+")";
			System.out.println(q);
			PreparedStatement ps=c.prepareStatement(q);
			int m=0;
			for(int i=0;i<al.length;i++){
				m=m+1;
				System.out.println(m+"-=-"+al[i]);
				ps.setString(m, al[i]);
			}
			System.out.println(ps.toString());
			int f=ps.executeUpdate();
			msg="Data Save";
			System.out.println("execute"+f);
			}
			catch(Exception e){msg=e.toString();}
			return msg;
		}
		public String UpdateData(Connection c, String ta, ArrayList al, String c_name){
			String msg = null;
			try{
			q="UPDATE "+ta+" SET  OWNER1=?, OWNER2=?, ADDRESS1=?, ADDRESS2=?, PHONENO = ?, CITY = ?, EMAIL=? WHERE COMPNAME = ?";
			System.out.println(q);
			PreparedStatement ps=c.prepareStatement(q);
			int m=0;
			for(int i=0;i<al.size();i++){
				m=m+1;
				ps.setString(m, (String)al.get(i));
			}
			ps.setString(8, c_name);
			System.out.println(ps.toString());
			int f=ps.executeUpdate();
			msg="Saved";
			}
			catch(Exception e){System.out.println(e.toString());}
			return msg;
		}
		public String DeleteForAll(Connection c, String ta,String delcol, String colname, String name ,String value){
			try{
			con=getConnection();
			q="UPDATE "+ta+" SET "+delcol+" = ? where "+colname+" = ?";
			System.out.println(q);
			PreparedStatement ps=c.prepareStatement(q);
			ps.setString(1, value);
			ps.setString(2, name);
			System.out.println(ps.toString());
			int f=ps.executeUpdate();
			}
			catch(Exception e){
				System.err.println("Cal Stock "+e.toString());
			}
			String msg="Record Deleted";
			return msg;
		}
		public String stockupdate(int currentstock, String id)
		{	String msg="";
			try{
				con=getConnection();
				q="UPDATE item_stock SET CurrentQty = ? WHERE ItemId = ?";
				System.out.println(q);
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, String.valueOf(currentstock));
				ps.setString(2, id);
				System.out.println(ps.toString());
				int f=ps.executeUpdate();
				msg="Stock Update";
				}
				catch(Exception e){
					 System.out.println("Stock update"+e.toString());
				}
				return msg;
		}
		public int calStock(String item_id){
			int cqty,qty,ret = 0;
			String q;
			
			try{
				Connection con=getConnection();
				q="select CurrentQty,Quantity from temp_order_info,item_stock where temp_order_info.Barcode = item_stock.ItemId and temp_order_info.Barcode=?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, item_id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					cqty=Integer.parseInt(rs.getString(1));
					System.out.println("current");
					qty=Integer.parseInt(rs.getString(2));
					ret=cqty-qty;
				}
				con.close();
			}
			catch(Exception e){}
			return ret;
		}
		public int calStockadd(String inqty,String item_id){
			int cqty,qty,ret = 0;
			String q;
			qty=Integer.parseInt(inqty);
			
			try{
				Connection con=getConnection();
				q="select currentQty from item_stock where item_stock.ItemId=?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, item_id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					cqty=Integer.parseInt(rs.getString(1));
					ret=cqty+qty;
				}
				con.close();
			}
			catch(Exception e){}
			return ret;
		}
		
		public String orderid(String date, String table, String column) {
			String s="ORD";
			try{
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory","root","root");
				st=con.createStatement();
				/*String ff="SELECT "+column+" FROM "+table;
				System.out.println(ff);
*/				rs=st.executeQuery("SELECT "+column+" FROM "+table);
				if(rs.next())
				{
					rs.last();
					String st1=rs.getString(1);
					int x=Integer.parseInt(st1.substring(13,17));
					x=x+1;
					if(x<10)
					{
						 retst=s+date+"00000"+String.valueOf(x);
					}
					else if(x>=10 && x<100)
					{
						 retst=s+date+"0000"+String.valueOf(x);
					}
					else if(x>=100 && x<1000)
					{
						 retst=s+date+"000"+String.valueOf(x);
					}
					else if(x>=1000 && x<10000)
					{
						 retst=s+date+"00"+String.valueOf(x);
					}
					else if(x>=10000 )
					{
						 retst=s+date+"0"+String.valueOf(x);
					}

				}
				else 
				{
					 retst=s+date+"000001";
				}
					
		}
			catch(Exception ee)
			{
				System.out.println(ee.toString());
			}
			System.out.println(retst);
			return retst;
		}
		public ArrayList getItemNameQty(String item_id){
			String cus="Not Found";
			ArrayList al=new ArrayList();
			try{
				Connection con=getConnection();
				q="select itemName,CurrentQty,EffectivePrice,GST_No from itemcategory,item_stock where itemcategory.ItemId = item_stock.ItemId and itemcategory.itemid=?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, item_id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					al.add(rs.getString(1));
					al.add(rs.getString(2));
					al.add(rs.getString(3));
					al.add(rs.getString(4));
				}
				con.close();
			}
			catch(Exception e){}
			return al;
		}
		public ArrayList openingaccount(String ta)
		{
			Connection con=getConnection();
			ArrayList al=new ArrayList();
			try
			{
				String q="select * from "+ta+"";
				PreparedStatement ps=con.prepareStatement(q);
				ResultSet rs=ps.executeQuery();
			
				while(rs.next())
				{
					al.add(rs.getString(1));
					al.add(rs.getString(2));
					al.add(rs.getString(3));
					al.add(rs.getString(4));
					al.add(rs.getString(5));
					al.add(rs.getString(6));
					
				}
				System.out.println(ps.toString());
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			return al;
		}
		public String UpdateQuantity(int qty, String id){
			try{
				Connection c=getConnection();
				q="UPDATE item_stock SET CurrentQty=? WHERE ItemId = ?";
				PreparedStatement ps=c.prepareStatement(q);
				ps.setInt(1, qty);
				ps.setString(2, id);
				System.out.println(ps.toString());
				int f=ps.executeUpdate();
			}
			catch(Exception e){e.toString();}
			String msg="Saved";
			return msg;
		}
		public String getItemQuantity(String item_id){
			String cus="Out of Stock";
			try{
				Connection con=getConnection();
				q="select CurrentQty from item_stock where ItemId = ?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, item_id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					cus=rs.getString(1);
				}
				con.close();
			}
			catch(Exception e){}
			return cus;
		}
		
		public String getCustomerName(String cus_phone){
			String cus="Not Found";
			try{
				Connection con=getConnection();
				q="select CustomerName from customer_info where CustomerPhone = ?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, cus_phone);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					cus=rs.getString(1);
				}
				con.close();
			}
			catch(Exception e){}
			return cus;
		}
		public ArrayList FindData(Connection con2, String ta,String cv,String ff) {
			ArrayList al2=new ArrayList();
			try
			{
				System.out.println("Find");
				String str="select * from "+ta+" where UPPER("+ff+")=?";
				System.out.println(str);
				PreparedStatement pst=con.prepareStatement(str);
				pst.setString(1, cv.toUpperCase());
				ResultSet rs=pst.executeQuery();
				System.out.println(pst.toString());
				if((rs.next())){
					for(int j=1;j<12;j++)
					{
						al2.add(rs.getString(j));
					
					}				
				}				
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			return al2;			
		}
		/*public ArrayList getData(Connection c, String ta){
			ArrayList darray=new ArrayList();
			try{
				q="select * from "+ta;
				System.out.println(q);
				PreparedStatement ps=c.prepareStatement(q);
				ResultSet rs;
				rs=ps.executeQuery();
				int i=0;
				rs.first();
				for(int j=2;j<13;j++)
				{	
					darray.add(rs.getString(j));
				}
			}
			catch(Exception e){e.toString();}
			return darray;
		}
*/		public String UpdateCustomer(Connection c, ArrayList al, String c_name){
			try{
			System.out.println(c.toString());
			q="UPDATE customer_info SET CustomerPhone=?, CustomerEmail=?, Cus_Address=? WHERE CustomerName = ?";
			System.out.println(q);
			PreparedStatement ps=c.prepareStatement(q);
			ps.setString(1, (String) al.get(0));
			ps.setString(2, (String) al.get(1));
			ps.setString(3, (String) al.get(2));
			ps.setString(4, c_name);
			System.out.println(ps.toString());
			int f=ps.executeUpdate();
			}
			catch(Exception e){e.toString();}
			String msg="Updated";
			return msg;
		}
	
		public ArrayList<String> getItemName(String col,String ta0,String col2,String cat_name, String Item_col){
			String cat_id=null;
			ArrayList<String> data=new ArrayList<String>();
			try{
				Connection con=getConnection();
				q="select "+col+" from "+ta0+" where "+col2+" = ?";
				System.out.println("UP--"+q);
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, cat_name);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					cat_id=rs.getString(1);
				}
				System.err.println(cat_id);
				q="select "+Item_col+" from "+ta0+" where cat_id=?";
				System.out.println("down--"+q);
				PreparedStatement ps1=con.prepareStatement(q);
				ps1.setString(1, cat_id);
				System.err.println(ps1);
				ResultSet rs1=ps1.executeQuery();
				System.err.println("hello");
				while(rs1.next()){
					data.add(rs1.getString(1));
				}
				con.close();
			}
			catch(Exception e){}
			return data;
		}
		public ArrayList<String> getUnitSize(String size_colname, String unit_name){
			ArrayList<String> data=new ArrayList<String>();
			String unit_id=null;
			try{
				Connection con=getConnection();
				q="select UnitID from unit_master where UnitName = ?";
				PreparedStatement ps1=con.prepareStatement(q);
				ps1.setString(1, unit_name);
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next()){
					unit_id=rs1.getString(1);
				}
				q="select SizeName from unit_size_master where unitID = ?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, unit_id);
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					data.add(rs.getString(1));
				}
				con.close();
			}
			catch(Exception e){}
			return data;
		}
		public ArrayList<String> getCategory(String tab_name,String col_name,String status)
		{
			ArrayList<String> data=new ArrayList<String>();
			try
			{
				Connection con=getConnection();
				String str="select "+col_name+" from "+tab_name+" where Status=?";
				System.out.println("Q---"+str);
				PreparedStatement ps=con.prepareStatement(str);
				ps.setString(1, status);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					data.add(rs.getString(1));
				}
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
			return data;
		}
		public String getItemFull(String ta1,String col1,String va,String val1){
			String ret_val=null;
			try{
				Connection con=getConnection();
			String str="select "+col1+" from "+ta1+" where "+va+"=?";
			System.out.println(str);
			System.err.println(str);
			PreparedStatement ps=con.prepareStatement(str);
			ps.setString(1, val1);
			ResultSet rs1=ps.executeQuery();
			System.out.println("DataUtil");
			System.out.println(ps.toString());
			System.out.println(rs1.toString());
			while(rs1.next())
			{
				ret_val=rs1.getString(1);
			}
			con.close();
			
			}
			catch(Exception e){
				System.out.println(e.toString());
			}
			return ret_val;
		}
		public String insertUnit(String query, Connection m, String id, String name,String desc,String status ) {
			{
		    try
		    {
			PreparedStatement ps3=((java.sql.Connection) m).prepareStatement(query);
			ps3.setString(1, id);
			ps3.setString(2, name);
			ps3.setString(3, desc);
			ps3.setString(4, status);
			ps3.executeUpdate();
			}
		catch (Exception ee) {
			return ee.toString();
	        }
		// TODO Auto-generated method stub
		return "SUBMIT INfORMATION.....";
	        }
			
		}

			public String DeleteUnitSize(String UnitSizeID) {
			DataUtil d=new DataUtil();
			try
			{
				Connection con=getConnection();
				q="UPDATE unit_size_master SET Status=? WHERE UnitSizeId = ?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, "N");
				ps.setString(2, UnitSizeID);
				int p=ps.executeUpdate();
				System.err.println(ps.toString());
				con.close();
			}
			catch (Exception e) 
			{
				System.err.println(e);
		    }
		    return "Data Deleted.....";
		}
		public ArrayList getUnitSizeData() {
			ArrayList<String> data=new ArrayList<String>();
			try{
				Connection con=getConnection();
				q="select * from unit_size_master";
				PreparedStatement ps=con.prepareStatement(q);
				ResultSet rs=ps.executeQuery();
				System.err.println(rs.toString());
					while(rs.next()){
						for(int i=1;i<=5;i++){
						data.add(rs.getString(i));
					}
				}
				con.close();
			}
			catch(Exception e){ 
				System.err.println(e);
			}
			return data;
		}
	
		public String UpdateUnitSize(String SizeName, String Description, String UnitSizeId) {
			DataUtil d=new DataUtil();
			try
			{
				Connection con=getConnection();
				q="UPDATE unit_size_master SET SizeName=?, Discription=? WHERE UnitSizeId = ?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, SizeName);
				ps.setString(2, Description);
				ps.setString(3, UnitSizeId);
				System.err.println(ps.toString());
				int p=ps.executeUpdate();
				con.close();
			}
			catch (Exception e) 
			{
				System.err.println(e);
		    }
			return "Updated";
		}
		public String Stockadd(int crstock,String price,String itmid)
		{
		DataUtil d=new DataUtil();
		try
		{
			Connection con=getConnection();
			q="UPDATE item_stock SET CurrentQty= ?,EffectivePrice = ? WHERE ItemId = ? and Status = 'Y'";
			PreparedStatement ps=con.prepareStatement(q);
			ps.setString(1, String.valueOf(crstock));
			ps.setString(2, price);
			ps.setString(3, itmid);
			System.err.println(ps.toString());
			int p=ps.executeUpdate();
			con.close();
		}
		catch (Exception e) 
		{
			System.err.println(e);
	    }
		return "Updated";
		}
		public static void main(String[] args) {
			new DataUtil();
		}
		public String Update_UnitMaster(Connection con2, String...data) {
			String ret;
			try{
				int b=data.length;		
			q="UPDATE unit_master SET UnitName=?,Discription=? WHERE UnitID=? and Status=?";
			PreparedStatement ps1=con2.prepareStatement(q);
			ps1.setString(1, data[0]);
			ps1.setString(2, data[1]);
			ps1.setString(3, data[2]);
			ps1.setString(4, data[3]);
			ps1.executeUpdate();
			ret="data Update";
			}
			catch(Exception e){
				ret=e.toString();
			}
			return ret;
		}	
		public String updateitementries(String ta,String Itemid,String...list)
		{
			String msg=null;
			Connection con=getConnection();
			try
			{
				String q="UPDATE "+ta+" set OpenQty=?,CurrentQty=?,ReorderQty=?,PricePerUnit=?,EffectivePrice=? where ItemId=?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1,list[0]);
				ps.setString(2,list[1]);
				ps.setString(3,list[2]);
				ps.setString(4,list[3]);
				ps.setString(5,list[4]);
				ps.setString(6, Itemid);
				int x=ps.executeUpdate();
				System.out.println(ps.toString());
				msg="Record Updated";
				con.close();
			}
				catch(Exception e)
			{
					System.out.println(e.toString());
			}
			return msg;
		}
		public void permanentlyDelete(String tablename)
		{
			System.out.println("insert delete");
			try{
			Connection con=getConnection();
			String str4="delete from "+tablename ;
			st=con.createStatement();
			int p=st.executeUpdate(str4);
			}
			catch (Exception e) {
				System.out.println(e.toString());
			}	
		}
public String Delete( String tablename,String id,String value) {
			DataUtil d=new DataUtil();
			try
			{
				Connection con=getConnection();
				q="UPDATE "+tablename+" SET Status='N' WHERE "+id+" = ?";
				PreparedStatement ps=con.prepareStatement(q);
				ps.setString(1, value);
				int p=ps.executeUpdate();
				System.err.println(ps.toString());
				con.close();
			}
			catch (Exception e) 
			{
				System.err.println(e);
		    }
		    return "Data Deleted.....";
		}

public String getPassword(Connection con,String table,String col1,String col2,String name,String email,String fatchcol) {
		String getpass = null;
	try
	{
		String str="select "+fatchcol+" from  "+table+" where "+col1+"=? and "+col2+"=?";
		PreparedStatement pst=con.prepareStatement(str);
		pst.setString(1, name);
		pst.setString(2, email);
		ResultSet rs=pst.executeQuery();
		if((rs.next())){
			getpass=rs.getString(1);
		}				
		}
	catch(Exception e){getpass=e.toString();}
	return getpass;
}
public String getonedata(String ta,String col,String status)
{
	String da=null;
	try
	{
		Connection c=getConnection();
	String q="select "+col+" from "+ta+" where status=?";
	PreparedStatement ps=c.prepareStatement(q);
	ps.setString(1,status);
	System.out.println(ps.toString());
	ResultSet rs=ps.executeQuery();
	rs.last();
	da=rs.getString(1);
	
	System.out.println(rs.toString());
	c.close();
	
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}

	return da;
	
}
public ArrayList<String> GetCustomerName() {
	ArrayList<String> list=new ArrayList<String>();
	try
	{
		Connection c=getConnection();
		String str="select Customer_Name from order_info where Status='N'";
		PreparedStatement pst=c.prepareStatement(str);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			list.add(rs.getString(1));
		}
		else{
			list.add("No Pending Payments");
		}
	}
	catch(Exception e){}
	return list;
}
public ArrayList<String> GetOrderID(String cname) {
	ArrayList<String> list=new ArrayList<String>();
	try
	{
		Connection c=getConnection();
		String str="select Order_ID from order_info where Customer_Name= ?";
		PreparedStatement pst=c.prepareStatement(str);
		pst.setString(1, cname);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			list.add(rs.getString(1));
		}
		else{
			list.add("N/A");
		}
	}
	catch(Exception e){}
	return list;
}
public String GetAmount(String cid) {
	ArrayList<String> list=new ArrayList<String>();
	String am = null;
	try
	{
		Connection c=getConnection();
		String str="select Totalprice from order_info where Order_ID= ?";
		PreparedStatement pst=c.prepareStatement(str);
		pst.setString(1, cid);
		ResultSet rs=pst.executeQuery();
		if(rs.next()){
			am=rs.getString(1);
		}
		else{
			am="-";
		}
	}
	catch(Exception e){}
	return am;
}

}

	