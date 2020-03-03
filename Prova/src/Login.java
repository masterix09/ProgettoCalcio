import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JDialog {
	private JTextField textField;
	private JTextField textField_1;

	
	public Login(Driver drive) {
		setTitle("ACCEDI");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 428, 244);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAccedi = new JLabel("ACCEDI");
		lblAccedi.setBounds(151, 38, 57, 20);
		panel.add(lblAccedi);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(42, 88, 82, 20);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(42, 146, 85, 20);
		panel.add(lblPassword);
		
		JLabel lblLogin = new JLabel("LOG-IN");
		lblLogin.setBounds(313, 196, 54, 20);
		panel.add(lblLogin);
		
		textField = new JTextField();
		textField.setBounds(155, 85, 146, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(155, 143, 146, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				String user = textField.getText();
				String pass = textField_1.getText();
				drive.ShowHomePage(user, pass);
				
			}
		});
		
		
		
	}
}
