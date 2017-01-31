package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pojo.Communityinfo;
import pojo.Emp;
import pojo.Supplier;
public class CommunityinfoDao {
	public ArrayList<Communityinfo>  getAllCommunityinfo()
	{		
		ArrayList<Communityinfo> list=new ArrayList<Communityinfo>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql="select coid,coname,cocard,coaddress,cophone,libraryid from communityinfo";			
			PreparedStatement pre=con.prepareStatement(sql);			
			ResultSet rs=pre.executeQuery();	
			while(rs.next())
			{
				Communityinfo communityinfo=new Communityinfo();
				communityinfo.setCoid(rs.getInt(1));
				communityinfo.setConame(rs.getString(2));
				communityinfo.setCocard(rs.getString(3));
				communityinfo.setCoaddress(rs.getString(4));
				communityinfo.setCophone(rs.getString(5));
				communityinfo.setLibraryid(rs.getString(6));
				list.add(communityinfo);
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
		public boolean checkLogin(String libraryid)
		{
			boolean f=false;
			Connection con=null;
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
				String sql="select top 1 * from communityinfo where libraryid=?";			
				PreparedStatement pre=con.prepareStatement(sql);
				pre.setString(1, libraryid);
				ResultSet rs=pre.executeQuery();				
				if(rs.next())
				{
					f=true;
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
		return f;
		}

	public int addCommunity(String coname,String cocard,String coaddress,String cophone,String libraryid){
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="insert  into communityinfo values(?,?,?,?,?)";
			PreparedStatement pre=con.prepareStatement(sql);			
			pre.setString(1,coname);
			pre.setString(2,cocard);
			pre.setString(3,coaddress);
			pre.setString(4,cophone);
			pre.setString(5,libraryid);
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
	
	
	public int updateDept(int did,String dname)
	{
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=binxuefruit_db","sa","sasa");
			String sql="update dept set dname=? where did=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1,dname);
			pre.setInt(2, did);
			n = pre.executeUpdate();
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
		
		return n;
	}
	
	public int delCommunityinfo(int coid){
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="delete communityinfo where coid=?";
			PreparedStatement pre=con.prepareStatement(sql);			
			pre.setInt(1,coid);
			n = pre.executeUpdate();
			//JOptionPane.showMessageDialog(null,sql);
			//JOptionPane.showMessageDialog(null,n);
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
public Communityinfo getCommunityById(int coid){
		
	Communityinfo communityinfo=new Communityinfo();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql="select top 1 coid,coname,cocard,coaddress,cophone,libraryid from communityinfo where coid=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setInt(1, coid);
			ResultSet rs=pre.executeQuery();
			if(rs.next())
			{			
				communityinfo.setCoid(rs.getInt(1));
				communityinfo.setConame(rs.getString(2));
				communityinfo.setCocard(rs.getString(3));
				communityinfo.setCoaddress(rs.getString(4));
				communityinfo.setCophone(rs.getString(5));
				communityinfo.setLibraryid(rs.getString(6));
				
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
		
	
	return communityinfo;
	
}

public Communityinfo getCommunityBylibraryid(String libraryid){	
	Communityinfo communityinfo=new Communityinfo();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql="select top 1 coname,coid from communityinfo where libraryid=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1, libraryid);
			ResultSet rs=pre.executeQuery();
			if(rs.next())
			{							
				communityinfo.setConame(rs.getString(1));
				communityinfo.setCoid(rs.getInt(2));
				
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
		
	
	return communityinfo;
	
}

public ArrayList<Communityinfo>  getAllcommunity(String sql)
{		
	ArrayList< Communityinfo> list=new ArrayList<Communityinfo>();
	Connection con=null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
		//String sql="select bkname,bkwriterfrom bookinfo";	
		//JOptionPane.showMessageDialog(null, sql);
		PreparedStatement pre=con.prepareStatement(sql);			
		ResultSet rs=pre.executeQuery();			
		while(rs.next())
		{
			Communityinfo communityinfo=new Communityinfo();
			communityinfo.setCoid(rs.getInt(1));
			//book.setBname(rs.getString(2));
			//book.setSname(rs.getString(3));
			communityinfo.setConame(rs.getString(2));
			communityinfo.setCocard(rs.getString(3));
			communityinfo.setCoaddress(rs.getString(4));
			communityinfo.setCophone(rs.getString(5));
			communityinfo.setLibraryid(rs.getString(6));
			//communityinfo.setBkname(rs.getString(7));
			
			list.add(communityinfo);
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
	
