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
import pojo.Emp;
import pojo.Supplier;
import dao.EmpDao;
import dao.SupplierDao;
public class UpdateSupplier extends JDialog{
		private int sid;
        public UpdateSupplier(final int sid){
        	this.sid=sid;
        	SupplierDao dao1=new SupplierDao();
        	Supplier supplier=dao1.getSupplierById(sid);
        	this.setSize(400,450);
        	this.setTitle("修改供货商信息");
        	this.setResizable(false);
        	this.setLocationRelativeTo(null);
        	this.setLayout(null);
        	this.setModal(true);
        	
        	JLabel   lbl_img=new JLabel();		
    		lbl_img.setIcon(new ImageIcon("src//image//1.jpg"));	
    		lbl_img.setBounds(0, 0, 400, 450);
    		
        	JLabel lab1=new JLabel("公司名称");
    		lab1.setBounds(50, 30, 100, 25);
    		final JTextField jf1=new JTextField(supplier.getSname());
    		jf1.setBounds(150, 30,140, 25);	
    		JLabel lab2=new JLabel("公司地址");
    		lab2.setBounds(50, 70, 90, 25);
    		final JTextField jf2=new JTextField(supplier.getSaddress());
    		jf2.setBounds(150, 70,140, 25);
    		JLabel lab3=new JLabel("联系人");
    		lab3.setBounds(50, 110, 90, 25);
    		final JTextField jf3=new JTextField(supplier.getSlinkname());
    		jf3.setBounds(150, 110,140, 25);
    		JLabel lab4=new JLabel("联系方式");
    		lab4.setBounds(50, 150, 90, 25);
    		final JTextField jf4=new JTextField(supplier.getSphone());
    		jf4.setBounds(150, 150,140, 25);
    		JButton bnt1=new JButton("修改");
    		bnt1.setBounds(70, 300, 90, 25);
    		bnt1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					
					String saddress=jf2.getText();
					String slinkname=jf3.getText();
			
					String sphone=jf4.getText();
					
					String sname=jf1.getText().trim();
					if(sname.length()>0){
					SupplierDao dao=new SupplierDao();				
					int n=(Integer)dao.updateSupplier(sid, sname, saddress, slinkname, sphone);					
					if(n>=1)
					{
						//JOptionPane.showMessageDialog(null, "修改成功");
						SupplierFRM.bindSupplier();
						jf1.setText("");
						jf2.setText("");
						jf3.setText("");
						jf4.setText("");
					}
				}
					else
					{
						JOptionPane.showMessageDialog(null, "修改失败");
						SupplierFRM.bindSupplier();	
					}
				}});
    		JButton bnt2=new JButton("取   消");
    		bnt2.setBounds(230, 300, 90, 25);
    		bnt2.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent arg0) {   				
    				UpdateSupplier.this.dispose();
    			}
    		});  		
    		this.add(jf1);	
    		this.add(jf2);
    		this.add(jf3);	
    		this.add(jf4);
    		this.add(lab1);
    		this.add(lab2);
    		this.add(lab3);
    		this.add(lab4);   		
    		this.add(bnt1);
    		this.add(bnt2);    	
    		this.add(lbl_img);
        }
      
		
}
