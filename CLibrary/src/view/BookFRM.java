package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import dao.EmpDao;
import dao.UserLoginDao;

import pojo.Book;

import pojo.Emp;

public class BookFRM extends JDialog{
	static JTable table=new JTable();
	public BookFRM() {
		this.setTitle("图书表");
		this.setSize(900,650);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);		
		JScrollPane js=new JScrollPane();
		js.setBounds(20, 100, 860, 388);
		js.add(table);
		js.setViewportView(table);
		
		
		JLabel   lbl_img=new JLabel();
		 lbl_img.setBounds(0, 0, 989, 663);
		 lbl_img.setIcon(new ImageIcon("src//image//BookFRM.jpg"));
		 
		//表格中内容居中
		DefaultTableCellRenderer dtr=new DefaultTableCellRenderer();
		dtr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dtr);
		
		
		
		//增加图书
		JButton bnt1=new JButton("增加图书");
		bnt1.setBounds(440, 510, 100, 25);
		bnt1.setForeground(Color.blue);
		bnt1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				AddBook add=new AddBook();
				add.setVisible(true);
			}
		});		
		JLabel lbl1=new JLabel("书籍名称");
		lbl1.setBounds(50, 30, 60, 25);	
		lbl1.setForeground(Color.white);
		final JTextField txt1=new JTextField();
		txt1.setBounds(120, 30, 200, 26);	
		JLabel lbl2=new JLabel("作   者");
		lbl2.setBounds(370, 30, 60, 25);	
		lbl2.setForeground(Color.white);
		final JTextField txt2=new JTextField();
		txt2.setBounds(410, 30, 200, 26);		
		JButton bnt3=new JButton("搜索");
		bnt3.setBounds(680, 30, 70, 25);
		bnt3.setForeground(Color.blue);
		bnt3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
	        String sql="select bkid,bkname,bkconcern,bkprice,bkwriter,bkisdn,bkbarcord,bkstatus from bookinfo  where  1=1";			
			StringBuffer sb=new StringBuffer();
			sb.append(sql);
			//按书籍模糊查询
			if(txt1.isEnabled()&&txt1.getText().trim().length()>0)
			{
				//得到选的部门对象
				String bkname=txt1.getText();				
				sb.append(" and bkname like '%");
				sb.append(bkname);
				sb.append("%'");					
			}		
			//按作者模糊查询
			if(txt2.isEnabled()&&txt2.getText().trim().length()>0)
			{
				String bkwriter=txt2.getText();
				sb.append(" and bkwriter like '%");
				sb.append(bkwriter);
				sb.append("%'");
			}
		//调用重载的方法
			bindBook(sb.toString());						
			}
		});
		bindBook();	
		this.add(js);
		this.add(bnt1);
		this.add(bnt3);
		this.add(lbl1);
		this.add(lbl2);
		this.add(txt1);
		this.add(txt2);
		this.add(lbl_img);
		//this.add(ch1);
	}
	public static void  bindBook()
	{		
		BookDao dao=new BookDao();
		ArrayList<Book> list=dao.getAllBook();
		DefaultTableModel dtm=new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}		
		};
		dtm.addColumn("书籍ID");
		//dtm.addColumn("图书类型");
		//dtm.addColumn("供货商");
		dtm.addColumn("图书");
		dtm.addColumn("出版社");
		dtm.addColumn("价格");
		dtm.addColumn("作者");
		dtm.addColumn("ISBN码");
		dtm.addColumn("条形码");
		dtm.addColumn("是否在库");				
		for (Book book : list) {		
			Vector vec=new Vector();
			Integer bkid=book.getBkid();			
			//String bname=book.getBname();			
			//String sname=book.getSname();
			String bkname=book.getBkname();		
			String bkconcern=book.getBkconcern();
			String bkprice=book.getBkprice();
			String bkwriter=book.getBkwriter();
			String bkisdn=book.getBkisdn();
			String bkbarcord=book.getBkbarcord();		
			int a=book.getBkstatus();
			String ee=(a==1?"在库":"不在库");			
			vec.add(bkid);
			vec.add(bkname);
			vec.add(bkconcern);
			vec.add(bkprice);
			vec.add(bkwriter);
			vec.add(bkisdn);
			vec.add(bkbarcord);
			vec.add(ee);
			dtm.addRow(vec);
	}		
		table.setModel(dtm);		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);	
		table.getColumnModel().getColumn(1).setMaxWidth(180);
		table.getColumnModel().getColumn(1).setMinWidth(180);
		table.getColumnModel().getColumn(1).setPreferredWidth(180);
		table.getColumnModel().getColumn(2).setMaxWidth(110);
		table.getColumnModel().getColumn(2).setMinWidth(110);
		table.getColumnModel().getColumn(2).setPreferredWidth(110);		
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setMaxWidth(210);
		table.getColumnModel().getColumn(4).setMinWidth(210);
		table.getColumnModel().getColumn(4).setPreferredWidth(210);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setMaxWidth(100);
		table.getColumnModel().getColumn(6).setMinWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setMaxWidth(90);
		table.getColumnModel().getColumn(7).setMinWidth(90);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);		
	}		
	public static void bindBook(String sql)
	{
		DefaultTableModel dtm=new DefaultTableModel(){
			@Override 
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;  //表中数据不能改
			}
		};
	dtm.addColumn("书籍ID");
	//dtm.addColumn("图书类型");
	//dtm.addColumn("供货商");
	dtm.addColumn("图书");
	dtm.addColumn("出版社");
	dtm.addColumn("价格");
	dtm.addColumn("作者");
	dtm.addColumn("ISBN码");
	dtm.addColumn("条形码");
	dtm.addColumn("是否在库");	
	BookDao dao=new BookDao();
	ArrayList<Book> list=dao.getAllBook(sql);	
	for (Book book : list) {		
		Vector vec=new Vector();
		Integer bkid=book.getBkid();			
		//String bname=book.getBname();			
		//String sname=book.getSname();
		String bkname=book.getBkname();		
		String bkconcern=book.getBkconcern();
		String bkprice=book.getBkprice();
		String bkwriter=book.getBkwriter();
		String bkisdn=book.getBkisdn();
		String bkbarcord=book.getBkbarcord();		
		int a=book.getBkstatus();
		String ee=(a==1?"在库":"不在库");			
		vec.add(bkid);
		vec.add(bkname);
		vec.add(bkconcern);
		vec.add(bkprice);
		vec.add(bkwriter);
		vec.add(bkisdn);
		vec.add(bkbarcord);
		vec.add(ee);
		dtm.addRow(vec);
}		
	table.setModel(dtm);		
	table.getColumnModel().getColumn(0).setMaxWidth(0);
	table.getColumnModel().getColumn(0).setMinWidth(0);
	table.getColumnModel().getColumn(0).setPreferredWidth(0);	
	table.getColumnModel().getColumn(1).setMaxWidth(180);
	table.getColumnModel().getColumn(1).setMinWidth(180);
	table.getColumnModel().getColumn(1).setPreferredWidth(180);
	table.getColumnModel().getColumn(2).setMaxWidth(110);
	table.getColumnModel().getColumn(2).setMinWidth(110);
	table.getColumnModel().getColumn(2).setPreferredWidth(110);		
	table.getColumnModel().getColumn(3).setMaxWidth(50);
	table.getColumnModel().getColumn(3).setMinWidth(50);
	table.getColumnModel().getColumn(3).setPreferredWidth(50);
	table.getColumnModel().getColumn(4).setMaxWidth(210);
	table.getColumnModel().getColumn(4).setMinWidth(210);
	table.getColumnModel().getColumn(4).setPreferredWidth(210);
	table.getColumnModel().getColumn(5).setMaxWidth(100);
	table.getColumnModel().getColumn(5).setMinWidth(100);
	table.getColumnModel().getColumn(5).setPreferredWidth(100);
	table.getColumnModel().getColumn(6).setMaxWidth(100);
	table.getColumnModel().getColumn(6).setMinWidth(100);
	table.getColumnModel().getColumn(6).setPreferredWidth(100);
	table.getColumnModel().getColumn(7).setMaxWidth(90);
	table.getColumnModel().getColumn(7).setMinWidth(90);
	table.getColumnModel().getColumn(7).setPreferredWidth(90);		
}
	
	public static void main(String[] args) {
		BookFRM frm=new BookFRM();
		frm.setVisible(true);
	}

}
		
	
