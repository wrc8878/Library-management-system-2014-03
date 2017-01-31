package view;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import pojo.Book;
import pojo.Bookin;
import pojo.Emp;
import pojo.Supplier;
import dao.BookDao;
import dao.BookinDao;
import dao.EmpDao;
import dao.SupplierDao;
public class BookinFRM extends JDialog{
	static JTable table=new JTable();
	public BookinFRM() {
		this.setTitle("书籍进库详细表");
		this.setSize(600,500);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		this.setModal(true);		
		JScrollPane js=new JScrollPane();
		js.setBounds(20, 20, 550, 400);
		js.add(table);
		js.setViewportView(table);		
		
		JLabel   lbl_img=new JLabel();		
		lbl_img.setIcon(new ImageIcon("src//image//EmpFRM.jpg"));	
		lbl_img.setBounds(0, 0, 700, 400);
		
		
		//表格中内容居中
		DefaultTableCellRenderer dtr=new DefaultTableCellRenderer();
		dtr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, dtr);
		
		bindEmp();
		this.add(js);
		this.add(lbl_img);
	}
public static void  bindEmp()
		{			
			BookinDao dao=new BookinDao();
			ArrayList<Bookin> list=dao.getAllBookin();
			//表模型
			DefaultTableModel dtm=new DefaultTableModel(){
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}		
			};
			dtm.addColumn("商品入库ID");
			dtm.addColumn("书籍ID");
			dtm.addColumn("书籍名称");
			dtm.addColumn("进价折率");
			dtm.addColumn("进货量");
			dtm.addColumn("入库时间");				
			for (Bookin bookin : list) {		
				Vector vec=new Vector();
				int biid=bookin.getBiid();
				int bkid=bookin.getBkid();	
				String bkname=bookin.getBkname();
				String birate=bookin.getBirate();
				int bicount=bookin.getBicount();
				String eh=bookin.getBidate();
				String ss[]=eh.split(" ");
				String bidate=ss[0];		
				vec.add(biid);
				vec.add(bkid);
				vec.add(bkname);
				vec.add(birate);
				vec.add(bicount);
				vec.add(bidate);		
				dtm.addRow(vec);				
		}	
			table.setModel(dtm);
			table.getColumnModel().getColumn(0).setMaxWidth(30);
			table.getColumnModel().getColumn(0).setMinWidth(30);
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(1).setMaxWidth(30);
			table.getColumnModel().getColumn(1).setMinWidth(30);
			table.getColumnModel().getColumn(1).setPreferredWidth(30);
			table.getColumnModel().getColumn(2).setMaxWidth(200);
			table.getColumnModel().getColumn(2).setMinWidth(200);
			table.getColumnModel().getColumn(2).setPreferredWidth(200);
			table.getColumnModel().getColumn(3).setMaxWidth(80);
			table.getColumnModel().getColumn(3).setMinWidth(80);
			table.getColumnModel().getColumn(3).setPreferredWidth(80);
			table.getColumnModel().getColumn(4).setMaxWidth(80);
			table.getColumnModel().getColumn(4).setMinWidth(80);
			table.getColumnModel().getColumn(4).setPreferredWidth(80);
		}
public static void main(String[] args){
	BookinFRM frm=new BookinFRM();
	frm.setVisible(true);
}
}
