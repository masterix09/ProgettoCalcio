import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class Giocatore extends JDialog {

	public Giocatore(Driver drive) {
		setTitle("DETTAGLI GIOCATORE");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblFoto = new JLabel("FOTO");
		lblFoto.setBounds(15, 16, 109, 75);
		getContentPane().add(lblFoto);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(139, 43, 69, 20);
		getContentPane().add(lblNome);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(242, 43, 69, 20);
		getContentPane().add(lblName);
		
		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setBounds(139, 85, 87, 20);
		getContentPane().add(lblCognome);
		
		JLabel lblSurname = new JLabel("surname");
		lblSurname.setBounds(242, 85, 69, 20);
		getContentPane().add(lblSurname);
		
		JLabel lblNgoal = new JLabel("N\u00B0Goal:");
		lblNgoal.setBounds(139, 138, 69, 20);
		getContentPane().add(lblNgoal);
		
		JLabel lblNgoal_1 = new JLabel("ngoal");
		lblNgoal_1.setBounds(242, 138, 69, 20);
		getContentPane().add(lblNgoal_1);
		
		JLabel lblSquadra = new JLabel("Squadra:");
		lblSquadra.setBounds(139, 189, 69, 20);
		getContentPane().add(lblSquadra);
		
		JLabel lblTeam = new JLabel("team");
		lblTeam.setBounds(242, 189, 69, 20);
		getContentPane().add(lblTeam);
		
		JLabel label = new JLabel("<-BACK");
		label.setBounds(15, 208, 69, 20);
		getContentPane().add(label);
	}
}
