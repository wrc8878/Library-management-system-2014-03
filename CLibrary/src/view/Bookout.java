package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;

import dao.BookoutDao;
import dao.CommunityinfoDao;
import dao.EmpDao;
import dao.SupplierDao;
import pojo.Book;



import pojo.Bookout1;
import pojo.Communityinfo;
import pojo.Emp;
import pojo.Supplier;
public class Bookout extends JDialog{	
	static JTable table=new JTable();
	
	private int bkid;	
	public Bookout(final int bkid){					    
		this.setTitle("信息确认");
		this.setSize(330,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);	
		JScrollPane js=new JScrollPane();
		js.setBounds(20, 40, 720, 150);
		js.add(table);
		js.setViewportView(table);	
		
		BookDao dao=new BookDao();
		Book book=dao.getBookById(bkid);
		
    	JLabel   lbl_img=new JLabel();		
		lbl_img.setIcon(new ImageIcon("src//image//EmpFRM.jpg"));	
		lbl_img.setBounds(0, 0, 330, 600);
		
		
		//表格中内容居中
		DefaultTableCellRenderer dtr=new DefaultTableCellRenderer();
		dtr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dtr);
		
		JLabel lab1=new JLabel("请输入借阅证编号");
		lab1.setBounds(105, 30, 150, 25);		
		final JTextField txt1=new JTextField("1011");
		txt1.setBounds(100,70,120,25);	
		JButton jb1=new JButton("确定");
		jb1.setBounds(130, 120, 60, 25);
		
		JLabel lab2=new JLabel("借阅编号");
		lab2.setBounds(20, 270, 100, 25);
		final JLabel lab3=new JLabel();
		lab3.setBounds(110, 270,160, 25);  
		final JLabel lab4=new JLabel("住户姓名");
		lab4.setBounds(20, 190, 100, 25);
		final JLabel lab5=new JLabel();
		lab5.setBounds(110,190,160, 25);  
		JLabel lab6=new JLabel("借阅书籍");
		lab6.setBounds(20, 350, 100, 25);
		JLabel lab7=new JLabel(book.getBkname());
		lab7.setBounds(110, 350,160, 25);  
		JButton jb2=new JButton("确定");
		jb2.setBounds(130, 450, 60, 25);
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				lab3.setText(txt1.getText());
				String libraryid=txt1.getText();
				CommunityinfoDao dao1=new CommunityinfoDao();
				Communityinfo communityinfo=dao1.getCommunityBylibraryid(libraryid);				
				lab5.setText(communityinfo.getConame());
				if(lab5.getText()!=null){
					
				}
				else{
					JOptionPane.showMessageDialog(null, "对不起，该用户不存在");
					txt1.setText("");
				}
				
				
			}
		});
		
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				CommunityinfoDao dao=new CommunityinfoDao();
				Communityinfo com=dao.getCommunityBylibraryid(txt1.getText());
				int coid=com.getCoid();
				String bodate=getTime();				
				BookDao dao1=new BookDao();
				Book book=dao1.getBookById(bkid);
				int n=book.getBkstatus();
		  if(n>0){
			  BookoutDao dao2=new BookoutDao();
				int a=dao2.addBookout(bkid, coid, bodate);
		
			if(lab5.getText()!=null &&a>=1){			
			if(n==1){
					n=0;
					BookDao dao3=new BookDao();
					dao3.updateBook(bkid, n);
					//JOptionPane.showMessageDialog(null, "借书成功");
					CommunityoutFRM.bindCommunity();
					
				}			
		        else{
					JOptionPane.showMessageDialog(null, "借书失败");
					
				}
				BookoutFRM.bindBook();
				Bookout.this.dispose();
			}	
		  }
		  else{
			  JOptionPane.showMessageDialog(null, "书籍不在或丢失");
			  Bookout.this.dispose();
		  }
				
			
			}
		});

	this.add(txt1);
	this.add(lab1);
	this.add(lab2);
	this.add(lab3);
	this.add(lab4);
	this.add(lab5);
	this.add(lab6);
	this.add(lab7);
	this.add(jb1);
	this.add(jb2);
	this.add(lbl_img);
	
	}
	
	
	public String getTime()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date=new Date();
		String a=sdf.format(date);
		return a;
	}
	
	
}
