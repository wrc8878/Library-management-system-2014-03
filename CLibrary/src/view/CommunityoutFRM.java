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

import pojo.Communityinfo;
import dao.BookDao;
import dao.BookoutDao;
import dao.CommunityinfoDao;

import dao.EmpDao;
public class CommunityoutFRM extends JDialog{
	static JTable table=new JTable();
	// private int bkid;
	public CommunityoutFRM(){
		//this.bkid=bkid;
		this.setTitle("住户借阅信息表");
		this.setSize(1350,750);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);	
		JScrollPane js=new JScrollPane();
		js.setBounds(150, 10, 1060, 300);
		js.add(table);
		js.setViewportView(table);	
		
		JLabel   lbl_img=new JLabel();		
		lbl_img.setIcon(new ImageIcon("src//image//2.png"));	
		lbl_img.setBounds(0, 0, 1350, 750);
		
		//表格中内容居中
		DefaultTableCellRenderer dtr=new DefaultTableCellRenderer();
		dtr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dtr);
		
		JButton jb1=new JButton("借阅");
		jb1.setBounds(380, 450, 100, 25);
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub		
				
				//int bkid=(Integer)table.getValueAt(row, 0);		
					BookoutFRM frm=new BookoutFRM();
					frm.setVisible(true);				
			}
		});
		JButton jb2=new JButton("归还");
		jb2.setBounds(880, 450, 100, 25);
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			int row=table.getSelectedRow();
			
				if(row>=0)
				{
					int bkid=(Integer)table.getValueAt(row, 0);
					int boid= (Integer) table.getValueAt(row,2 );
					BookoutDao dao=new BookoutDao();
					int ch=JOptionPane.showConfirmDialog(null, "是否真的归还？", "提示", JOptionPane.YES_NO_OPTION);
					//JOptionPane.showMessageDialog(null,boid);
					if(ch==JOptionPane.YES_OPTION)
					{
						BookDao dao1=new BookDao();
						Book book=dao1.getBookById(bkid);
						int m=book.getBkstatus();
						//JOptionPane.showMessageDialog(null, m);
						int n=dao.deleteBookout(boid);
						if(n>=1)
						{
							if(m==0){
								//JOptionPane.showMessageDialog(null, "还书成功！");
								m=1;
								BookDao dao3=new BookDao();
								dao3.updateBook(bkid, m);							
								bindCommunity();
							}							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "还书失败！");
						}
						BookoutFRM.bindBook();
					}			
				}
				else
				{
					JOptionPane.showMessageDialog(null, "未选中要归还的书籍！");
				}			
			}
		});
		JLabel lbl1=new JLabel("书籍名称");
		lbl1.setBounds(280, 350, 60, 25);		
		final JTextField txt1=new JTextField();
		txt1.setBounds(360, 350, 200, 26);	
		JLabel lbl2=new JLabel("借阅证编号");
		lbl2.setBounds(590, 350, 100, 25);		
		final JTextField txt2=new JTextField();
		txt2.setBounds(680, 350, 200, 26);		
		JButton bnt3=new JButton("搜索");
		bnt3.setBounds(960, 350, 70, 25);
		bnt3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub			
			String sql="select bkid,coid,boid,coname,cocard,cophone,libraryid,bkname,bkprice,bkwriter,bkisdn,bkbarcord,bodate from view_2 where 1=1";
			StringBuffer sb=new StringBuffer();			
			sb.append(sql);
			//按书籍模糊查询
			if(txt1.isEnabled()&&txt1.getText().trim().length()>0)
			{
				
				String bkname=txt1.getText();				
				sb.append(" and bkname like '%");
				sb.append(bkname);
				sb.append("%'");					
			}	
				
			if(txt2.isEnabled()&&txt2.getText().trim().length()>0)
			{
				//得到选的借阅证对象
				String libraryid=txt2.getText();				
				sb.append(" and libraryid like '%");
				sb.append(libraryid);
				sb.append("%'");					
			}	
		//调用重载的方法
			bindCommunity(sb.toString());	
			}
		});		
		bindCommunity();
		this.add(js);
		this.add(bnt3);
		this.add(jb1);	
		this.add(jb2);		
		this.add(lbl1);
		this.add(lbl2);
		this.add(txt1);
		this.add(txt2);
		this.add(lbl_img);
		//this.add(ch1);
	}
	public static void  bindCommunity()
	{		
		BookoutDao dao=new BookoutDao();
		ArrayList<Bookout1> list=dao.getAllBookout();
		DefaultTableModel dtm=new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}		
		};
		dtm.addColumn("图书ID");
		dtm.addColumn("住户ID");	
		dtm.addColumn("ID");
		dtm.addColumn("住户姓名");
		dtm.addColumn("身份证号码");
		dtm.addColumn("联系方式");
		dtm.addColumn("借阅证");
		dtm.addColumn("图书");
		dtm.addColumn("价格");
		dtm.addColumn("作者");
		dtm.addColumn("ISDN码");
		dtm.addColumn("条形码");		
		dtm.addColumn("借阅时间");
		
		for (Bookout1 book : list) {		
			Vector vec=new Vector();
			Communityinfo com=new Communityinfo();
			int bkid=book.getBkid();
			int coid=book.getCoid();
			int boid=book.getBoid();
			String coname=book.getConame();		
			String cocard=book.getCocard();
			String cophone=book.getCophone();
			String libraryid=book.getLibraryid();	
			String bkname=book.getBkname();
			String bkprice=book.getBkprice();
			String bkwriter=book.getBkwriter();
			String bkisdn=book.getBkisdn();
			String bkbarcord=book.getBkbarcord();
			//String bodate=book.getBodate();
			
			String eh=book.getBodate();			
			String ss[]=eh.split(" ");
			String bodate=ss[0];
			
			vec.add(bkid);
			vec.add(coid);
			vec.add(boid);
			vec.add(coname);
			vec.add(cocard);
			vec.add(cophone);
			vec.add(libraryid);
			vec.add(bkname);
			vec.add(bkprice);
			vec.add(bkwriter);			
			vec.add(bkisdn);
			vec.add(bkbarcord);	
			vec.add(bodate);
			dtm.addRow(vec);
	}
		table.setModel(dtm);	
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);	
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getColumnModel().getColumn(2).setPreferredWidth(0);		
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(80);
		table.getColumnModel().getColumn(4).setMinWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		/*table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		*/
	}	
	
	public static void bindCommunity(String sql)
	{
		BookoutDao dao=new BookoutDao();
		ArrayList<Bookout1> list=dao.getAllBookout(sql);
		DefaultTableModel dtm=new DefaultTableModel(){
			@Override 
			public boolean isCellEditable(int arg0, int arg1) {
				// TODO Auto-generated method stub
				return false;  //表中数据不能改
			}
		};
		dtm.addColumn("图书ID");
		dtm.addColumn("住户ID");	
		dtm.addColumn("ID");
		dtm.addColumn("住户姓名");
		dtm.addColumn("身份证号码");
		dtm.addColumn("联系方式");
		dtm.addColumn("借阅证");
		dtm.addColumn("图书");
		dtm.addColumn("价格");
		dtm.addColumn("作者");
		dtm.addColumn("ISDN码");
		dtm.addColumn("条形码");		
		dtm.addColumn("借阅时间");
		
	
	for (Bookout1 book : list) {		
		Vector vec=new Vector();	
		//Communityinfo com=new Communityinfo();		
		int bkid=book.getBkid();
		int coid=book.getCoid();
		int boid=book.getBoid();
		String coname=book.getConame();		
		String cocard=book.getCocard();
		String cophone=book.getCophone();
		String libraryid=book.getLibraryid();	
		String bkname=book.getBkname();
		String bkprice=book.getBkprice();
		String bkwriter=book.getBkwriter();
		String bkisdn=book.getBkisdn();
		String bkbarcord=book.getBkbarcord();
		//String bodate=book.getBodate();
		String eh=book.getBodate();			
		String ss[]=eh.split(" ");
		String bodate=ss[0];
		
		
		vec.add(bkid);
		vec.add(coid);
		vec.add(boid);
		vec.add(coname);
		vec.add(cocard);
		vec.add(cophone);
		vec.add(libraryid);
		vec.add(bkname);
		vec.add(bkprice);
		vec.add(bkwriter);			
		vec.add(bkisdn);
		vec.add(bkbarcord);	
		vec.add(bodate);
		dtm.addRow(vec);
}		
	table.setModel(dtm);	
	table.getColumnModel().getColumn(0).setMaxWidth(0);
	table.getColumnModel().getColumn(0).setMinWidth(0);
	table.getColumnModel().getColumn(0).setPreferredWidth(0);	
	table.getColumnModel().getColumn(1).setMaxWidth(0);
	table.getColumnModel().getColumn(1).setMinWidth(0);
	table.getColumnModel().getColumn(1).setPreferredWidth(0);
	table.getColumnModel().getColumn(2).setMaxWidth(0);
	table.getColumnModel().getColumn(2).setMinWidth(0);
	table.getColumnModel().getColumn(2).setPreferredWidth(0);		
	}

	public static void main(String[] args){
	CommunityoutFRM frm=new CommunityoutFRM();
	frm.setVisible(true);
	}



}
