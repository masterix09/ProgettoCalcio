import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

public class HomePage extends JFrame {

	private JPanel contentPane;

	
	public HomePage(Driver drive) {
		setTitle("DIRETTA GOAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblScegliIlCampionato = new JLabel("SCEGLI IL CAMPIONATO");
		lblScegliIlCampionato.setBounds(134, 197, 181, 20);
		contentPane.add(lblScegliIlCampionato);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(123, 227, 147, 26);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\asus512-bq039\\git\\ProgettoCalcio\\Prova\\img\\logo off.png"));
		lblNewLabel.setBounds(-14, 10, 204, 180);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\asus512-bq039\\git\\ProgettoCalcio\\Prova\\img\\SCRITTA.png"));
		lblNewLabel_1.setBounds(185, 44, 230, 143);
		contentPane.add(lblNewLabel_1);
	}
}
