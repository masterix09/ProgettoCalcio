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

public class HomePage extends JFrame {

	private JPanel contentPane;

	
	public HomePage(Driver drive) {
		setResizable(false);
		setTitle("DIRETTA GOAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 917, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblScegliIlCampionato = new JLabel("SCEGLI IL CAMPIONATO");
		lblScegliIlCampionato.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblScegliIlCampionato.setForeground(Color.BLUE);
		lblScegliIlCampionato.setBounds(298, 349, 199, 20);
		contentPane.add(lblScegliIlCampionato);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(319, 378, 147, 26);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\logo off.png"));
		lblNewLabel.setBounds(293, 182, 204, 180);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\SCRITTA.png"));
		lblNewLabel_1.setBounds(472, 238, 230, 143);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\rsz_backgroundhome.jpg"));
		lblNewLabel_2.setBounds(0, 0, 917, 527);
		contentPane.add(lblNewLabel_2);
	}
}
