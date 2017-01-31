package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import pojo.BookType;
import pojo.Emp;
import pojo.Supplier;
import dao.BookDao;
import dao.BookTypeDao;
import dao.EmpDao;
import dao.SupplierDao;
public class AddBook extends JDialog{
	public AddBook(){
        	this.setSize(400,480);
        	this.setTitle("增加图书");
        	this.setResizable(false);
        	this.setLocationRelativeTo(null);
        	this.setLayout(null);
        	this.setModal(true);
        	
        	JLabel   lbl_img=new JLabel();
   		 lbl_img.setBounds(0, 0, 989, 663);
   		 lbl_img.setIcon(new ImageIcon("src//image//AddBook.jpg"));
   
        	JLabel lab1=new JLabel("图书类型");
    		lab1.setBounds(50, 20, 100, 25);
    		final JComboBox com1=new JComboBox();
    		com1.setBounds(150, 20, 160, 25);
    		BookTypeDao dao1=new BookTypeDao();
    		ArrayList<BookType> list1=dao1.getAllBookType();	
    		for (BookType booktype : list1) {
    			com1.addItem(booktype);    	
    		}
    		JLabel lab2=new JLabel("供货商");
    		lab2.setBounds(50, 60, 90, 25);
    		final JComboBox com2=new JComboBox();  		
    		com2.setBounds(150, 60,160, 25);
    		SupplierDao dao2=new SupplierDao();
    		ArrayList<Supplier> list2=dao2.getAllSupplier();	
    		for (Supplier supplier : list2) {
    			com2.addItem(supplier);    	
    		}
    		JLabel lab3=new JLabel("书籍");
    		lab3.setBounds(50, 100, 90, 25);
    		final JTextField jf3=new JTextField();
    		jf3.setBounds(150, 100,160, 25);
    		JLabel lab4=new JLabel("出版社");
    		lab4.setBounds(50, 140, 100, 25);
    		final JTextField jf4=new JTextField();
    		jf4.setBounds(150, 140,160, 25);
    		JLabel lab5=new JLabel("价格");
    		lab5.setBounds(50, 185, 90, 25);   
    		final JTextField jf5=new JTextField();
    		jf5.setBounds(150, 185,160, 25);
    		JLabel lab6=new JLabel("作者");
    		lab6.setBounds(50, 225, 90, 25);
    		final JTextField jf6=new JTextField();
    		jf6.setBounds(150, 225,160, 25);
    		JLabel lab7=new JLabel("ISBN码");
    		lab7.setBounds(50, 265, 100, 25);   		
    		final JTextField jf7=new JTextField();
    		jf7.setBounds(150, 265,160, 25);
    		JLabel lab8=new JLabel("条形码");
    		lab8.setBounds(50, 305, 100, 30);
    		final JTextField jf8=new JTextField();
    		jf8.setBounds(150, 305,160, 25);
    		//rb1.setSelected(true);
    		JLabel lab9=new JLabel("是否在库");
    		lab9.setBounds(50, 345, 100, 25);
    		//单选按钮
    		final JRadioButton  rb1= new JRadioButton();
    		rb1.setBounds(140, 345, 20, 20);
    		JLabel lab10=new JLabel("在库");
    		lab10.setBounds(160, 345, 60, 26);   		
    		final JRadioButton  rb2= new JRadioButton();
    		rb2.setBounds(190, 345, 20, 20);
    		JLabel lab11=new JLabel("不在库");
    		lab11.setBounds(210, 345, 60, 26);  		
    		//实现单选接钮，单选 功能
    		rb1.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent arg0) {
    				// TODO Auto-generated method stub    				
    				if(rb1.isSelected())
    				{
    					rb2.setSelected(false);
    				}   				
    			}
    		} ); 		
    		rb2.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent arg0) {
    				// TODO Auto-generated method stub
    				if(rb2.isSelected())
    				{
    					rb1.setSelected(false);
    				}
    			}
    		} );
    		rb1.setSelected(true);
    		JButton bnt1=new JButton("新   增");
    		bnt1.setBounds(70, 400, 90, 25);
    	 bnt1.addActionListener(new ActionListener(){           
				public void actionPerformed(ActionEvent arg0) {
					
					BookType booktype=(BookType)com1.getSelectedItem();
					int bid=booktype.getBid();
					Supplier supplierinfo=(Supplier)com2.getSelectedItem();
					int sid=supplierinfo.getSid();
					String bkname=jf3.getText();
					String bkconcern=jf4.getText();
					String bkprice=jf5.getText();
					String bkwriter=jf6.getText();
					String bkisdn=jf7.getText();
					String bkbarcord=jf8.getText();
					int bkstatus=0;
					if(rb1.isSelected())
					{
						bkstatus=1;
					}
	
					BookDao dao=new BookDao();
					
					int n=dao.addBook( bid, sid, bkname, bkconcern, bkprice, bkwriter, bkisdn, bkbarcord, bkstatus);
					if(n>=1)
					{
						//JOptionPane.showMessageDialog(null, "新增成功");
						BookFRM.bindBook();
						jf4.setText("");
						jf5.setText("");
						jf3.setText("");
						jf6.setText("");
						jf7.setText("");
						jf8.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "对不起，新增失败");
						//jf1.setText("");
						//jf2.setText("");
						jf3.setText("");						
					}
				}});				
    		JButton bnt2=new JButton("取   消");
    		bnt2.setBounds(230, 400, 90, 25);
    		bnt2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					AddBook.this.dispose();					
				}});    		
    		this.add(com1);	
    		this.add(com2);	
    		
    		this.add(jf3);	
    		this.add(jf4);
    		this.add(jf5);	
    		this.add(jf6);
    		this.add(jf7);	
    		this.add(jf8);
    		this.add(lab1);
    		this.add(lab2);
    		this.add(lab3);
    		this.add(lab4);  
    		this.add(lab5);
    		this.add(lab6);
    		this.add(lab7);
    		this.add(lab8);	
    		this.add(lab9);
    		this.add(lab10);
    		this.add(lab11);	
    		this.add(bnt1);
    		this.add(bnt2);
    		this.add(rb1);
    		this.add(rb2);   
    		this.add(lbl_img);
        }
		public static void main(String[] args) {
			AddBook frm=new AddBook();
			frm.setVisible(true);
		}

    		}
