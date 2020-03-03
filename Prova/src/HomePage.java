import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JLabel lblNewLabel_2;
	public HomePage(Driver drive, String user, String pass) {
		
		setResizable(false);
		setTitle("DIRETTA GOAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		comboBox = new JComboBox();
		try {
			drive.AggiuntaItemCombo(comboBox);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				Object item = comboBox.getSelectedItem();
				drive.ShowCampionatoDialog(item, user, pass);
				
			}
		});
		comboBox.setBounds(260, 394, 336, 56);
		contentPane.add(comboBox);
		
		 lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\rsz_backgroundhome.png"));
		lblNewLabel_2.setBounds(0, 0, 903, 499);
		contentPane.add(lblNewLabel_2);
	}
}
