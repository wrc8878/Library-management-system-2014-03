package view;
import java.awt.Color;
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
import pojo.Communityinfo;
import pojo.Emp;
import pojo.Supplier;
import dao.BookDao;
import dao.BookTypeDao;
import dao.CommunityinfoDao;
import dao.EmpDao;
import dao.SupplierDao;
public class AddCommunity extends JDialog{
	public AddCommunity(){
        	this.setSize(400,350);
        	this.setTitle("增加住户");
        	this.setResizable(false);
        	this.setLocationRelativeTo(null);
        	this.setLayout(null);
        	this.setModal(true);
        	
        	JLabel   lbl_img=new JLabel();
   		 lbl_img.setBounds(0, 0, 400, 350);
   		 lbl_img.setIcon(new ImageIcon("src//image//Addcommunity.jpg"));
   		 
        	JLabel lab1=new JLabel("住户姓名");
    		lab1.setBounds(50, 20, 100, 25);
    		//lab1.setForeground(Color.RED);
    		final JTextField jf1=new JTextField();
    		jf1.setBounds(150, 20,160, 25);  
    		JLabel lab3=new JLabel("住户地址");    		
    		lab3.setBounds(50, 60, 90, 25);  
    		//lab3.setForeground(Color.RED);
    		final JComboBox jb_dong=new JComboBox();//几栋
    		jb_dong.setBounds(150, 60,40, 20);
    		for(int i=1;i<=9;i++)
    		{
    			jb_dong.addItem(i);
    		}
    		JLabel lab4=new JLabel("栋");
    		lab4.setBounds(192, 60, 40, 25);  	
    		//lab4.setForeground(Color.RED);
    		final JComboBox jb_lou=new JComboBox();//几楼
    		jb_lou.setBounds(206, 60,40, 20);
    		for(int i=1;i<=6;i++)
    		{
    			jb_lou.addItem(i);
    		}
    		JLabel lab5=new JLabel("楼");
    		lab5.setBounds(248, 60, 40, 25);
    		//lab5.setForeground(Color.RED);
    		final JComboBox jb_hao=new JComboBox();//几号
    		jb_hao.setBounds(265, 60,40, 20);
    		for(int i=1;i<=5;i++)
    		{
    				jb_hao.addItem(i);   			
    		}
    		JLabel lab6=new JLabel("号");
    		lab6.setBounds(305, 60, 40, 25);
    		//lab6.setForeground(Color.RED);
    		JLabel lab2=new JLabel("身份证号码");
    		lab2.setBounds(50, 100, 90, 25);
    		//lab2.setForeground(Color.RED);
    		final JTextField jf2=new JTextField();
    		jf2.setBounds(150, 100,160, 25);   		
    		JLabel lab7=new JLabel("住户联系电话");
    		lab7.setBounds(50, 140, 100, 25);
    		//lab7.setForeground(Color.RED);
    		final JTextField jf4=new JTextField();
    		jf4.setBounds(150, 140,160, 25);  
    		JLabel lab8=new JLabel("借阅编号");
    		lab8.setBounds(50, 180, 100, 25);
    		//lab8.setForeground(Color.RED);
    		final JTextField jf5=new JTextField();
    		jf5.setBounds(150, 180,160, 25);  
    		JButton bnt1=new JButton("新   增");
    		bnt1.setBounds(60, 250, 90, 25);
    	 bnt1.addActionListener(new ActionListener(){            
				public void actionPerformed(ActionEvent arg0) {				
					String coname=jf1.getText();
					String cocard=jf2.getText();
					int a=(Integer)jb_dong.getSelectedItem();
					int b=(Integer)jb_lou.getSelectedItem();
					int c=(Integer)jb_hao.getSelectedItem();
					String coaddress=a+"栋"+b+"楼"+c+"号";
					String cophone=jf4.getText();					
					String regex1="[\u4e00-\u9fa5]{2,4}";
					if(!coname.matches(regex1))
					{
						JOptionPane.showMessageDialog(null, "对不起，姓名输入2到4个字");
						return;
					}
					String regex2="[1-9][0-9]{16}[0-9Xx]";
					if(!cocard.matches(regex2))
					{
						JOptionPane.showMessageDialog(null, "身份证号码输入有误，请重新输入");
						jf2.setText("");
						return;
					}					
					String regex3="[1][3-8][0-9]{9}";
					if(!cophone.matches(regex3))
					{
						JOptionPane.showMessageDialog(null, "手机号码输入有误，请重新输入");
						jf4.setText("");
						return;
					}
					String libraryid=jf5.getText();
					CommunityinfoDao dao=new CommunityinfoDao();					
					int n=dao.addCommunity(coname, cocard, coaddress, cophone, libraryid);
					if(n>=1)
					{						
						//JOptionPane.showMessageDialog(null, "新增成功");
						CommunityinfoFRM.bindCommunity();	
						jf1.setText("");
						jf2.setText("");
						jf5.setText("");
						jf4.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "借阅证编号已存在,请重新输入");	
						jf5.setText("");
						return;
					}
				}});				
    		JButton bnt2=new JButton("取   消");
    		bnt2.setBounds(230, 250, 90, 25);
    		bnt2.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					AddCommunity.this.dispose();					
				}});    		  		
    		this.add(jf1);	
    		this.add(jf2);	
    		this.add(jf4);
    		this.add(jf5);
    		this.add(lab1);
    		this.add(lab2);
    		this.add(lab3);
    		this.add(lab4);  
    		this.add(lab5);
    		this.add(lab6);
    		this.add(lab7);
    		this.add(lab8);
    		this.add(bnt1);
    		this.add(bnt2); 
    		this.add(jb_dong);
    		this.add(jb_lou);
    		this.add(jb_hao);
    		this.add(lbl_img);
        }
		public static void main(String[] args) {
			AddCommunity frm=new AddCommunity();
			frm.setVisible(true);
		}

    		}
