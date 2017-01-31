package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import pojo.Book;
import pojo.Bookout1;
import dao.BookDao;
import dao.EmpDao;
public class BookoutFRM extends JDialog{
	static JTable table=new JTable();
	//private int bkid;
	public BookoutFRM(){
		//this.bkid=bkid;
		this.setTitle("书籍借出表");
		this.setSize(1200,650);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);	
		JScrollPane js=new JScrollPane();
		js.setBounds(105, 10, 1020, 400);
		js.add(table);
		js.setViewportView(table);	
		
		
		JLabel   lbl_img=new JLabel();		
		lbl_img.setIcon(new ImageIcon("src//image//1.png"));	
		lbl_img.setBounds(0, 0, 1200, 650);
		
		//表格中内容居中
		DefaultTableCellRenderer dtr=new DefaultTableCellRenderer();
		dtr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dtr);
		JButton jb1=new JButton("借阅");
		jb1.setBounds(540, 520, 100, 25);
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int row=table.getSelectedRow();
				if(row<0)
				{
					JOptionPane.showMessageDialog(null, "未选中要借阅的书");
					
				}
				else
				{				
					int bkid=(Integer)table.getValueAt(row, 0);		
					Bookout out=new Bookout(bkid);
					BookDao dao=new BookDao();
					Book book=dao.getBookById(bkid);
					int n=book.getBkstatus();
					if(n==0){
						JOptionPane.showMessageDialog(null, "该书不在库或丢失，请重新借阅！");
					}
					else{
						out.setVisible(true);
					}
					
					
				}
			}
		});
		JLabel lbl1=new JLabel("书籍名称");
		lbl1.setBounds(240, 450, 60, 25);		
		final JTextField txt1=new JTextField();
		txt1.setBounds(320, 450, 200, 26);	
		JLabel lbl2=new JLabel("作   者");
		lbl2.setBounds(570, 450, 60, 25);		
		final JTextField txt2=new JTextField();
		txt2.setBounds(610, 450, 200, 26);		
		JButton bnt3=new JButton("搜索");
		bnt3.setBounds(880, 450, 70, 25);
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
		this.add(bnt3);
		this.add(jb1);		
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
		dtm.addColumn("图书");
		dtm.addColumn("出版社");
		dtm.addColumn("价格");
		dtm.addColumn("作者");
		dtm.addColumn("ISDN码");
		dtm.addColumn("条形码");
		dtm.addColumn("是否在库");				
		for (Book book : list) {		
			Vector vec=new Vector();
			int bkid=book.getBkid();	
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
		table.getColumnModel().getColumn(0).setMaxWidth(40);
		table.getColumnModel().getColumn(0).setMinWidth(40);
		table.getColumnModel().getColumn(1).setMaxWidth(220);
		table.getColumnModel().getColumn(1).setMinWidth(220);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setMaxWidth(130);
		table.getColumnModel().getColumn(2).setMinWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);		
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setMaxWidth(220);
		table.getColumnModel().getColumn(4).setMinWidth(220);
		table.getColumnModel().getColumn(4).setPreferredWidth(220);
		table.getColumnModel().getColumn(5).setMaxWidth(130);
		table.getColumnModel().getColumn(5).setMinWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);
		table.getColumnModel().getColumn(6).setMaxWidth(130);
		table.getColumnModel().getColumn(6).setMinWidth(130);
		table.getColumnModel().getColumn(6).setPreferredWidth(130);
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
	dtm.addColumn("图书");
	dtm.addColumn("出版社");
	dtm.addColumn("价格");
	dtm.addColumn("作者");
	dtm.addColumn("ISDN码");
	dtm.addColumn("条形码");
	dtm.addColumn("是否在库");	
	BookDao dao=new BookDao();
	ArrayList<Book> list=dao.getAllBook(sql);	
	for (Book book : list) {		
		Vector vec=new Vector();
		int bkid=book.getBkid();	
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
	}
/*
public static void main(String[] args){
	BookoutFRM frm=new BookoutFRM();
	frm.setVisible(true);
	}
	*/
}
