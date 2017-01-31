package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Communityinfo;
import pojo.Emp;
import pojo.Supplier;
public class SupplierDao {
	public ArrayList<Supplier>  getAllSupplier()
	{		
		ArrayList< Supplier> list=new ArrayList<Supplier>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql="select sid,sname,saddress,slinkname,sphone from supplierinfo";			
			PreparedStatement pre=con.prepareStatement(sql);			
			ResultSet rs=pre.executeQuery();			
			while(rs.next())
			{
				Supplier supplier=new Supplier();
				supplier.setSid(rs.getInt(1));
				supplier.setSname(rs.getString(2));
				supplier.setSaddress(rs.getString(3));
				supplier.setSlinkname(rs.getString(4));
				supplier.setSphone(rs.getString(5));
				list.add(supplier);
			}		
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return list;		
	}
	public int addSupplier(String sname,String saddress,String slinkname,String sphone){
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="insert into supplierinfo values(?,?,?,?)";
			PreparedStatement pre=con.prepareStatement(sql);			
			pre.setString(1,sname);
			pre.setString(2,saddress);
			pre.setString(3,slinkname);
			pre.setString(4,sphone);
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n;
	}
	public int updateSupplier(int sid,String sname,String saddress,String slinkname,String sphone){
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="update supplierinfo set sname=?,saddress=?,slinkname=?,sphone=? where sid=?";
			PreparedStatement pre=con.prepareStatement(sql);			
			pre.setString(1,sname);
			pre.setString(2,saddress);
			pre.setString(3,slinkname);
			pre.setString(4,sphone);
			pre.setInt(5,sid);
			n = pre.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n;
	}
	public Supplier getSupplierById(int sid){
		
		Supplier supplier=new Supplier();
			Connection con=null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
				String sql="select top 1 sid,sname,saddress,slinkname,sphone from supplierinfo where sid=?";
				PreparedStatement pre=con.prepareStatement(sql);
				pre.setInt(1, sid);
				ResultSet rs=pre.executeQuery();
				if(rs.next())
				{			
					supplier.setSid(rs.getInt(1));
					supplier.setSname(rs.getString(2));
					supplier.setSaddress(rs.getString(3));
					supplier.setSlinkname(rs.getString(4));
					supplier.setSphone(rs.getString(5));
					
					
					
				}
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		return supplier;
		
	}


}
