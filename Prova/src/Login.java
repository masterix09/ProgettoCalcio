import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Login extends JDialog {
	private JPanel panel;
	private JPanel panel2; 
	private JLabel lbluser;
	private JLabel lblpass;
	private JLabel lblLogin;
	private JTextField textuser;
	private JTextField textpass;
	private String user = "", pass = "";
	
	public Login(Driver drive) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		panel= new JPanel();
		panel.setBounds(30, 28, 362, 200);
		panel2= new JPanel();
		panel2.setBounds(30, 28, 372, 166);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAdmin = new JLabel("");
		lblAdmin.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\admin botton.png"));
		lblAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				panel.setVisible(false);
				panel2.setVisible(true);
				getContentPane().add(panel2);
				panel2.setLayout(null);
				
				textpass = new JTextField("");
				textuser = new JTextField("");
				textuser.setBounds(130, 50, 100, 20);
				textpass.setBounds(130, 80, 100, 20);
				lbluser = new JLabel("username:");
				lblpass = new JLabel("password:");
				lblLogin = new JLabel("");
				lblLogin.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\login logo.png"));
				lblLogin.setBounds(60, 80, 200, 125);
				lblpass.setBounds(66, 80, 69, 20);
				lbluser.setBounds(66, 50, 69, 20);
				panel2.add(textpass);
				panel2.add(textuser);
				panel2.add(lblpass);
				panel2.add(lbluser);
				panel2.add(lblLogin);
				
				
				lblLogin.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
				
						user = getText(textuser);
						pass = getText(textpass);
						System.out.println(user+" "+pass);
						if(user.equals("admin") && pass.equals("pass")) {
						
							drive.NotShowLogin();
							drive.ShowHomePage(user, pass);
							
						}else{
							drive.NotShowLogin();
							drive.ShowError("CREDENZIALI ERRATE");
							
						}
					}
				});
					
				}
		});
		lblAdmin.setBounds(53, 16, 222, 79);
		panel.add(lblAdmin);
		
		JLabel lblGuest = new JLabel("");
		lblGuest.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\guest logoff.png"));
		lblGuest.setBounds(53, 111, 222, 79);
		panel.add(lblGuest);
		
		
		lblGuest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		
				drive.ShowHomePage("guest", "guest");
				drive.NotShowLogin();
			}
		});
	}
		
			
	public String getText(JTextField text) {
		
		return text.getText();
		
		
		
	}
}