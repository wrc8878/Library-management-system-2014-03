package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Book;
import pojo.Bookin;

import pojo.Supplier;



public class BookinDao {
	public ArrayList<Bookin>  getAllBookin()
	{		
		ArrayList< Bookin> list=new ArrayList<Bookin>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql="select biid,bkid,bkname,birate,bicount,bidate from view_1";			
			PreparedStatement pre=con.prepareStatement(sql);			
			ResultSet rs=pre.executeQuery();			
			while(rs.next())
			{
				Bookin bookin=new Bookin();
				bookin.setBiid(rs.getInt(1));
				bookin.setBkid(rs.getInt(2));
				bookin.setBkname(rs.getString(3));
				bookin.setBirate(rs.getString(4));
				bookin.setBicount(rs.getInt(5));
				bookin.setBidate(rs.getString(6));
				list.add(bookin);
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
}
	//ИљОн
	
