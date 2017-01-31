package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.FinderException;

import pojo.UserLogin;

public class UserLoginDao {
	public ArrayList<UserLogin> checkLogin(String uname, String upass)
	{
		
		ArrayList<UserLogin> list=new ArrayList<UserLogin>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		    con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="select top 1 uid,eid,uname,upass from userlogin where uname=? and upass=?";			
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1, uname);
			pre.setString(2, upass);		
			ResultSet rs=pre.executeQuery();			
			if(rs.next())
			{
				UserLogin ul=new UserLogin();
				ul.setUid(rs.getInt(1));
				ul.setEid(rs.getInt(2));
				ul.setUname(rs.getString(3));
				ul.setUpass(rs.getString(4));
				list.add(ul);
			}
		} 
	 catch (Exception e) {
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
