package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pojo.Book;
import pojo.Bookout1;

public class BookoutDao {
	public ArrayList<Bookout1>  getAllBookout()
	{		
		ArrayList< Bookout1> list=new ArrayList<Bookout1>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql1="select bkid,bkname,bkprice,bkwriter,bkisdn,bkbarcord,coid,coname,cocard,cophone,libraryid,bodate,boid from view_2";			
			PreparedStatement pre1=con.prepareStatement(sql1);			
			ResultSet rs1=pre1.executeQuery();			
			while(rs1.next())
			{
				Bookout1 book= new Bookout1();
				book.setBkid(rs1.getInt(1));
				book.setBkname(rs1.getString(2));
				book.setBkprice(rs1.getString(3));
				book.setBkwriter(rs1.getString(4));
				book.setBkisdn(rs1.getString(5));
				book.setBkbarcord(rs1.getString(6));
				book.setCoid(rs1.getInt(7));
				book.setConame(rs1.getString(8));
				book.setCocard(rs1.getString(9));
				book.setCophone(rs1.getString(10));
				book.setLibraryid(rs1.getString(11));
				book.setBodate(rs1.getString(12));
				book.setBoid(rs1.getInt(13));
				list.add(book);
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
	
	
	public int addBookout(int bkid,int coid,String bodate){
		int n=0;
		
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="insert into bookout values(?,?,?)";
			PreparedStatement pre=con.prepareStatement(sql);
			
			pre.setInt(1,bkid);
			pre.setInt(2,coid);
			pre.setString(3,bodate);
			
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
	
	public int deleteBookout(int boid){
		int n=0;
		
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="delete bookout where boid=?";
			PreparedStatement pre=con.prepareStatement(sql);		
			pre.setInt(1,boid);			
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
	
	
	public ArrayList<Bookout1>  getAllBookout(String sql)
	{		
		ArrayList< Bookout1> list=new ArrayList<Bookout1>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			//String sql1="select bkid,bkname,bkprice,bkwriter,bkisdn,bkbarcord,coid,coname,cocard,cophone,libraryid,bodate,boid from view_2";			
			PreparedStatement pre1=con.prepareStatement(sql);			
			ResultSet rs1=pre1.executeQuery();			
			while(rs1.next())
			{
				Bookout1 book= new Bookout1();
				book.setBkid(rs1.getInt(1));
				book.setCoid(rs1.getInt(2));
				book.setBoid(rs1.getInt(3));
				book.setConame(rs1.getString(4));
				book.setCocard(rs1.getString(5));
				book.setCophone(rs1.getString(6));
				book.setLibraryid(rs1.getString(7));
				
				book.setBkname(rs1.getString(8));
				book.setBkprice(rs1.getString(9));
				book.setBkwriter(rs1.getString(10));
				book.setBkisdn(rs1.getString(11));
				book.setBkbarcord(rs1.getString(12));
				book.setBodate(rs1.getString(13));
				
				
				
				list.add(book);
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
	
	public Bookout1 getBookoutById(int boid)
	{
		Bookout1 book=new Bookout1();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="select top 1 coid,bkid,boid,coname,cocard,cophone,libraryid,bkname,bkprice,bkwriter,bkisdn,bkbarcord,bodate from bookout where boid=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setInt(3, boid);
			ResultSet rs = pre.executeQuery();		
			if(rs.next())
			{
				book.setCoid(rs.getInt(1));
				book.setBkid(rs.getInt(2));
				book.setBoid(rs.getInt(3));
				book.setConame(rs.getString(4));
				book.setCocard(rs.getString(5));
				book.setCophone(rs.getString(6));
				book.setLibraryid(rs.getString(7));
				book.setBkname(rs.getString(8));
				book.setBkprice(rs.getString(9));
				book.setBkwriter(rs.getString(10));
				book.setBkisdn(rs.getString(11));
				book.setBkbarcord(rs.getString(12));
				book.setBodate(rs.getString(13));
			}
			
		} catch (Exception e) {
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
		return book;
		
		
		
		
	}		
	
}
