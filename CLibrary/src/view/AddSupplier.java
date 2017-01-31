package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import dao.EmpDao;
import dao.SupplierDao;
public class AddSupplier extends JDialog{
        public AddSupplier(){
        	this.setSize(400,400);
        	this.setTitle("增加供货商");
        	this.setResizable(false);
        	this.setLocationRelativeTo(null);
        	this.setLayout(null);
        	this.setModal(true);      
        	
        	JLabel   lbl_img=new JLabel();		
    		lbl_img.setIcon(new ImageIcon("src//image//EmpFRM.jpg"));	
    		lbl_img.setBounds(0, 0, 400, 400);
    		
        	JLabel lab1=new JLabel("公司名称");
    		lab1.setBounds(70, 30, 100, 25);
    		final JTextField jf1=new JTextField();
    		jf1.setBounds(180, 30,140, 25);	
    		JLabel lab2=new JLabel("公司地址");
    		lab2.setBounds(70, 80, 90, 25);
    		final JTextField jf2=new JTextField();
    		jf2.setBounds(180, 80,140, 25);
    		JLabel lab3=new JLabel("联系人");
    		lab3.setBounds(70, 120, 90, 25);
    		final JTextField jf3=new JTextField();
    		jf3.setBounds(180, 120,140, 25);
    		JLabel lab4=new JLabel("联系方式");
    		lab4.setBounds(70, 160, 90, 25);
    		final JTextField jf4=new JTextField();
    		jf4.setBounds(180, 160,140, 25);
    		JButton bnt1=new JButton("新   增");
    		bnt1.setBounds(70, 250, 90, 25);
    		bnt1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					String sname=jf1.getText();
					String saddress=jf2.getText();
					String regex1="[\u4e00-\u9fa5]{2,4}";
					String slinkname=jf3.getText();
					if(!slinkname.matches(regex1))
					{
						JOptionPane.showMessageDialog(null, "对不起，姓名输入2到4个字");
						return;
					}
					String sphone=jf4.getText();
					String regex3="[1][3-8][0-9]{9}";
					if(!sphone.matches(regex3))
					{
						JOptionPane.showMessageDialog(null, "对不起，手机号码输入错误");
						return;
					}
					SupplierDao dao=new SupplierDao();
					int sid = 0;
					int n=dao.addSupplier(sname, saddress, slinkname, sphone);
					if(n>=1)
					{
					//	JOptionPane.showMessageDialog(null, "新增成功");
						SupplierFRM.bindSupplier();
						jf1.setText("");
						jf2.setText("");
						jf3.setText("");
						jf4.setText("");
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "新增失败");						
					}
				}});
    		JButton bnt2=new JButton("取   消");
    		bnt2.setBounds(230, 250, 90, 25);
    		bnt2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					AddSupplier.this.dispose();					
				}});    		
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
		public static void main(String[] args) {
			AddSupplier frm=new AddSupplier();
			frm.setVisible(true);
		}

}
