package view;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import pojo.UserLogin;
import dao.UserLoginDao;
public class UserLoginFRM extends JFrame{
	
	public UserLoginFRM() {
		this.setSize(350, 250); 
		this.setTitle("Clibrary");
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/image/1.jpg"));
		JLabel   lbl_img=new JLabel();		
		lbl_img.setIcon(new ImageIcon("src//image//2.jpg"));	
		lbl_img.setBounds(0, 0, 400, 250);
		JLabel lbl1=new JLabel("�û���");
		lbl1.setBounds(20, 20, 100, 25);
		final JTextField txt1=new JTextField("1021");
		txt1.setBounds(100, 20, 150, 25);
			 
		JLabel lbl2=new JLabel("��    ��");
		lbl2.setBounds(20, 70, 100, 25);
		final JPasswordField txt2=new JPasswordField("1021"); 
		txt2.setBounds(100, 70, 150, 25);
				
		JLabel lbl3=new JLabel("��֤��");
		lbl3.setBounds(20, 120, 70, 25);

		
		
		final JLabel lbl4=new JLabel(getCheckCode());
		lbl4.setBounds(180, 120, 65, 25);
		
		final JTextField txt3=new JTextField(lbl4.getText());
		txt3.setBounds(100, 120, 60, 25);
		
		JButton bnt3=new JButton("���һ�");
		bnt3.setBounds(220, 120,75, 25);
		bnt3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lbl4.setText(getCheckCode());
			}
		});
		
		JButton bnt1=new JButton("ȡ  ��");
		bnt1.setBounds(70, 175,70, 25);
		bnt1.addActionListener(new ActionListener(){  
			
			public void actionPerformed(ActionEvent e) {		
				//UserLogin.this.setVisible(false);
				System.exit(1);
			}			
		});
	
		JButton bnt2=new JButton("��  ½");
		bnt2.setBounds(60, 175,220, 30);
		
		bnt2.addActionListener(new  ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
								
				String uname=txt1.getText();
				char[] upass1=txt2.getPassword();
				String upass=new String(upass1);
				String code=txt3.getText();
				
				if(uname.trim().length()==0||upass.trim().length()==0||code.length()==0)
				{
					JOptionPane.showMessageDialog(null, "�û��� ���� ��֤�벻��Ϊ��");
					txt1.setText(null);
					txt2.setText(null);
					return;
				}
				
				if(!code.equalsIgnoreCase(lbl4.getText()))
				{
					JOptionPane.showMessageDialog(null, "��֤�����!");
					return;
				}
				
				UserLoginDao dao=new UserLoginDao();
				ArrayList<UserLogin> list=dao.checkLogin(uname, upass);
		
				if(list.size()>0)
				{
					UserLogin  ul=list.get(0);
					String uname1=ul.getUname();
					//JOptionPane.showMessageDialog(null, "��½�ɹ�");
					UserLoginFRM.this.setVisible(false);
			
					MainFRM frm=new MainFRM(uname1); // ���û����ɫ
					frm.setVisible(true);
					
				}
				else
				{
				
					JOptionPane.showMessageDialog(null, "�û��������������������룡");
					txt1.setText(null);
					txt2.setText(null);
					
				}
				
				
			}
		});		
		
		this.add(bnt3);
		this.add(bnt2);
		//this.add(bnt1);
		this.add(txt2);
		this.add(txt1);
		this.add(txt3);
		this.add(lbl1);
		this.add(lbl2);
		this.add(lbl3);
		this.add(lbl4);
		this.add(lbl_img);
			
	}
	
	
	
	
	//�õ���֤�뷽��
	private String getCheckCode()
	{	
		String demo="ABCDEabcdefghijklmnpqrstuvwxyzFGHIJKLMNPQRSTUVWXYZ123456789";
		StringBuffer sb=new StringBuffer();
		Random ran=new Random();
		
		for (int i = 0; i < 4; i++) {
			int n=ran.nextInt(demo.length());
			
			char ch=demo.charAt(n);
			sb.append(ch);
		}
		
		
		return sb.toString();
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserLoginFRM frm=new UserLoginFRM();
		frm.setVisible(true);//�ɼ�

	}

	

}
