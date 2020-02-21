import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;

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
		lblScegliIlCampionato.setBounds(92, 110, 181, 20);
		contentPane.add(lblScegliIlCampionato);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(112, 146, 147, 26);
		contentPane.add(comboBox);
	}
}
