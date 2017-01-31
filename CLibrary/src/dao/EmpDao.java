package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import pojo.Emp;

public class EmpDao {

	

	public ArrayList<Emp>  getAllEmp()
	{
		
		ArrayList< Emp> list=new ArrayList<Emp>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql="select eid,ename,ecardid,ephone,eaddress,ehiredate,eis from emp";
			PreparedStatement pre=con.prepareStatement(sql);
			ResultSet rs=pre.executeQuery();
			while(rs.next())
			{
				Emp emp=new Emp();
				emp.setEid(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setEcardid(rs.getString(3));
				emp.setEphone(rs.getString(4));
				emp.setEaddress(rs.getString(5));
				emp.setEhiredate(rs.getString(6));
				emp.setEis(rs.getInt(7));
				list.add(emp);
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
	public int addEmp(String ename,String ecardid,String ephone,String eaddress,String ehiredate,int eis){
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="insert  into emp values(?,?,?,?,?,?)";
			PreparedStatement pre=con.prepareStatement(sql);
			//pre.setInt(1,eid);
			pre.setString(1,ename);
			pre.setString(2,ecardid);
			pre.setString(3,ephone);
			pre.setString(4,eaddress);
			pre.setString(5,ehiredate);
			pre.setInt(6,eis);
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
	public int updateEmp(int eid,String ename,String ecardid,String ephone,String eaddress,String ehiredate,int eis){
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="update emp set ename=?,ecardid=?,ephone=?,eaddress=?,ehiredate=?,eis=? where eid=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1,ename);
			pre.setString(2,ecardid);
			pre.setString(3,ephone);
			pre.setString(4,eaddress);
			pre.setString(5,ehiredate);
			pre.setInt(6,eis);
			pre.setInt(7,eid);
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
	
	
	public Emp getEmpById(int eid){
		
		Emp emp=new Emp();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql="select top 1 eid,ename,ecardid,ephone,eaddress,ehiredate,eis from emp where eid=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setInt(1, eid);
			ResultSet rs=pre.executeQuery();
			if(rs.next())
			{			
				emp.setEid(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setEcardid(rs.getString(3));
				emp.setEphone(rs.getString(4));
				emp.setEaddress(rs.getString(5));
				emp.setEhiredate(rs.getString(6));
				emp.setEis(rs.getInt(7));
				
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
		
	
	return emp;
	
}

}
