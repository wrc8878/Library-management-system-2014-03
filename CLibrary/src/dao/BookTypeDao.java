package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pojo.Book;
import pojo.BookType;

public class BookTypeDao {
	public ArrayList<BookType>  getAllBookType()
	{		
		ArrayList< BookType> list=new ArrayList<BookType>();
		Connection con=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Clibrary","sa","sasa");
			String sql1="select bid,bname from booktype";			
			PreparedStatement pre1=con.prepareStatement(sql1);			
			ResultSet rs1=pre1.executeQuery();			
			while(rs1.next())
			{
				BookType booktype=new BookType();
				booktype.setBid(rs1.getInt(1));
				booktype.setBname(rs1.getString(2));
				list.add(booktype);
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
