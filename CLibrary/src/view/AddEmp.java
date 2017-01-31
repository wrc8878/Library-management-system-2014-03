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



public class AddEmp extends JDialog{

        public AddEmp(){
        	this.setSize(400,450);
        	this.setTitle("增加员工");
        	this.setResizable(false);
        	this.setLocationRelativeTo(null);
        	this.setLayout(null);
        	this.setModal(true);
        	
        	JLabel   lbl_img=new JLabel();		
    		lbl_img.setIcon(new ImageIcon("src//image//EmpFRM.jpg"));	
    		lbl_img.setBounds(0, 0, 400, 450);
        	
        	//得到系统的年月日
    		java.util.Date date=new Date();
    		Calendar cal=Calendar.getInstance();
    		cal.setTime(date);    		
    		int year=cal.get(Calendar.YEAR);   		
    		int month=cal.get(Calendar.MONTH)+1;  		
    		int day=cal.get(Calendar.DAY_OF_MONTH);
    		
        	JLabel lab1=new JLabel("员工姓名",JLabel.CENTER);
        	//JLabel jlabel = new JLabel(bnt1,JLabel.CENTER);
    		lab1.setBounds(50, 30, 100, 25);
    		final JTextField jf1=new JTextField();
    		jf1.setBounds(150, 30,140, 25);	
    		JLabel lab2=new JLabel("身份证号码",JLabel.CENTER);
    		lab2.setBounds(50, 70, 90, 25);
    		final JTextField jf2=new JTextField();
    		jf2.setBounds(150, 70,140, 25);
    		JLabel lab3=new JLabel("手机号码",JLabel.CENTER);
    		lab3.setBounds(50, 110, 90, 25);
    		final JTextField jf3=new JTextField();
    		jf3.setBounds(150, 110,140, 25);
    		JLabel lab4=new JLabel("住址",JLabel.CENTER);
    		lab4.setBounds(50, 150, 90, 25);
    		final JTextField jf4=new JTextField();
    		jf4.setBounds(150, 150,140, 25);
    		JLabel lab11=new JLabel("入职日期",JLabel.CENTER);
    		lab11.setBounds(50, 195, 90, 25);   		
    		final JComboBox jb_year=new JComboBox();//年份
    		jb_year.setBounds(135, 195,60, 20);
    		for(int i=2000;i<=year;i++)
    		{
    			jb_year.addItem(i);
    		}
    		jb_year.setSelectedItem(year);
    		JLabel lab5=new JLabel("年");
    		lab5.setBounds(198, 195, 90, 25);  		
    		final JComboBox jb_month=new JComboBox();//月份
    		jb_month.setBounds(218, 195,45, 20);
    		for(int i=1;i<=12;i++)
    		{
    			jb_month.addItem(i);
    		}
    		jb_month.setSelectedItem(month);
    		JLabel lab6=new JLabel("月");
    		lab6.setBounds(267, 195, 90, 25);
    		final JComboBox jb_day=new JComboBox();//日期
    		jb_day.setBounds(285, 195,40, 20);
    		for(int i=1;i<=31;i++)
    		{
    			jb_day.addItem(i);
    		}
    		jb_day.setSelectedItem(day);
    		JLabel lab7=new JLabel("日");
    		lab7.setBounds(330, 195, 90, 25);	
    		
    		//动态实现日期转换
    		jb_year.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int a=(Integer)jb_year.getSelectedItem();
					int b=(Integer)jb_month.getSelectedItem();
					int c=getCurrDays(a,b);
					//清掉以前的
					jb_day.removeAllItems();
					for (int i = 1; i <=c; i++) {
						jb_day.addItem(i);
					}
					
				}
    		  
    		});
    		jb_month.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int a=(Integer)jb_year.getSelectedItem();
					int b=(Integer)jb_month.getSelectedItem();
					int c=getCurrDays(a,b);
					//清掉以前的
					jb_day.removeAllItems();
					
					for (int i = 1; i <=c; i++) {
						jb_day.addItem(i);
					}
					
				}
    		  
    		});
    		JLabel lab8=new JLabel("是否在职",JLabel.CENTER);
    		lab8.setBounds(50, 235, 90, 25);
    		final JRadioButton rb1=new JRadioButton();
    		rb1.setBounds(150, 235, 20, 20);
    		JLabel lab9=new JLabel("在职",JLabel.CENTER);
    		lab9.setBounds(175, 235, 30, 30);   		
    		final JRadioButton rb2=new JRadioButton();
    		rb2.setBounds(215, 235, 20, 20);   		
    		JLabel lab10=new JLabel("不在职",JLabel.CENTER);
    		lab10.setBounds(245, 235, 50, 30);
    		rb1.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					if(rb1.isSelected())
					{
						rb2.setSelected(false);
					}
					
				}});
    		rb2.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					if(rb2.isSelected())
					{
						rb1.setSelected(false);
					}
					
				}});
    		rb1.setSelected(true);
    		JButton bnt1=new JButton("新   增");
    		bnt1.setBounds(70, 300, 90, 25);
    		bnt1.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					String ename=jf1.getText();
					String regex1="[\u4e00-\u9fa5]{2,4}";
					if(!ename.matches(regex1))
					{
						JOptionPane.showMessageDialog(null, "对不起，姓名输入2到4个字");
						return;
					}
					String ecardid=jf2.getText();
					String regex2="[1-9][0-9]{16}[0-9Xx]";
					if(!ecardid.matches(regex2))
					{
						JOptionPane.showMessageDialog(null, "对不起身份证号码输入错误");
						return;
					}
					String ephone=jf3.getText();
					String regex3="[1][3-8][0-9]{9}";
					if(!ephone.matches(regex3))
					{
						JOptionPane.showMessageDialog(null, "对不起，手机号码输入错误");
						return;
					}
					String eaddress=jf4.getText();
					int a=(Integer)jb_year.getSelectedItem();
					int b=(Integer)jb_month.getSelectedItem();
					int c=(Integer)jb_day.getSelectedItem();
					String ehiredate=a+"-"+b+"-"+c;
					
					int eis=0;
					if(rb1.isSelected())
					{
						eis=1;
					}
					EmpDao dao=new EmpDao();
					int eid = 0;
					int n=dao.addEmp( ename, ecardid, ephone, eaddress, ehiredate, eis);
					if(n>=1)
					{
						//JOptionPane.showMessageDialog(null, "新增成功");
						EmpFRM.bindEmp();
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
    		bnt2.setBounds(230, 300, 90, 25);
    		bnt2.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					AddEmp.this.dispose();
					
				}});
    		
    		this.add(jb_year);
    		this.add(jb_month);
    		this.add(jb_day);  		
    		this.add(jf1);	
    		this.add(jf2);
    		this.add(jf3);	
    		this.add(jf4);
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
    		this.add(lab9);
    		this.add(lab10);
    		this.add(lab11);
    		this.add(rb1);
    		this.add(rb2);
    		this.add(lbl_img);
        }
        
        protected int getCurrDays(int year, int month) {
			// TODO Auto-generated method stub
			int[] a=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
			if(year%400==0||year%4==0&&year%100!=0)
			{
				if(month==2)
				{
					return a[1]+1;
				}
			}
			return a[month-1];
		}
		public static void main(String[] args) {
			AddEmp frm=new AddEmp();
			frm.setVisible(true);
		}

}
