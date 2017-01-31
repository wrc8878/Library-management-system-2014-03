package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import pojo.Book;
import pojo.Emp;
public class BookDao {
	public ArrayList<Book>  getAllBook()
	{		
		ArrayList< Book> list=new ArrayList<Book>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql1="select bkid,bkname,bkconcern,bkprice,bkwriter,bkisdn,bkbarcord,bkstatus from bookinfo";			
			PreparedStatement pre1=con.prepareStatement(sql1);			
			ResultSet rs1=pre1.executeQuery();			
			while(rs1.next())
			{
				Book book=new Book();
				book.setBkid(rs1.getInt(1));
				//book.setBoid(rs1.getInt(2));
				//book.setCoid(rs1.getInt(3));
				//book.setBname(rs1.getString(2));
				//book.setSname(rs1.getString(3));
				book.setBkname(rs1.getString(2));
				book.setBkconcern(rs1.getString(3));
				book.setBkprice(rs1.getString(4));
				book.setBkwriter(rs1.getString(5));
				book.setBkisdn(rs1.getString(6));
				book.setBkbarcord(rs1.getString(7));
				book.setBkstatus(rs1.getInt(8));
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
	
	public int addBook(int bid,int sid,String bkname,String bkconcern,String bkprice,String bkwriter,String bkisdn,String bkbarcord,int bkstatus){
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="insert  into bookinfo values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pre=con.prepareStatement(sql);
			
			pre.setInt(1,bid);
			pre.setInt(2,sid);
			pre.setString(3,bkname);
			pre.setString(4,bkconcern);
			pre.setString(5,bkprice);
			pre.setString(6,bkwriter);
			pre.setString(7,bkisdn);
			pre.setString(8,bkbarcord);
			pre.setInt(9, bkstatus);
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
	
	public ArrayList<Book>  getAllBook(String sql)
	{		
		ArrayList< Book> list=new ArrayList<Book>();
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
				Book book=new Book();
				book.setBkid(rs.getInt(1));
				//book.setBoid(rs.getInt(2));
				//book.setCoid(rs.getInt(3));
				//book.setBname(rs.getString(2));
				//book.setSname(rs.getString(3));
				book.setBkname(rs.getString(2));
				book.setBkconcern(rs.getString(3));
				book.setBkprice(rs.getString(4));
				book.setBkwriter(rs.getString(5));
				book.setBkisdn(rs.getString(6));
				book.setBkbarcord(rs.getString(7));
				book.setBkstatus(rs.getInt(8));
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

	
	
	
	public int updateBook(int bkid,int bkstatus){
		int n=0;
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="update bookinfo set bkstatus=? where bkid=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setInt(1,bkstatus);
			pre.setInt(2,bkid);
			
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
	
	
	//根据ID得到书籍对象
	public Book getBookById(int bkid)
	{
		Book book=new Book();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			 con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=CLibrary","sa","sasa");
			String sql="select top 1 bkid,bkname,bkstatus from bookinfo where bkid=?";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setInt(1, bkid);
			ResultSet rs = pre.executeQuery();		
			if(rs.next())
			{
				book.setBkid(rs.getInt(1));
				book.setBkname(rs.getString(2));
				book.setBkstatus(rs.getInt(3));
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
