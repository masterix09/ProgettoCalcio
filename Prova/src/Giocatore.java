import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;

public class Giocatore extends JDialog {

	public Giocatore(Driver drive, Object item, String id_giocatore) throws SQLException {
		
		
		setTitle("DETTAGLI GIOCATORE");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblFoto = new JLabel();
		lblFoto.setBounds(15, 16, 128, 121);
		getContentPane().add(lblFoto);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(144, 43, 69, 20);
		getContentPane().add(lblNome);
		
		JLabel lblName = new JLabel();
		lblName.setBounds(242, 43, 69, 20);
		getContentPane().add(lblName);
		
		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setBounds(149, 86, 87, 20);
		getContentPane().add(lblCognome);
		
		JLabel lblSurname = new JLabel();
		lblSurname.setBounds(242, 85, 69, 20);
		getContentPane().add(lblSurname);
		
		JLabel lblNgoal = new JLabel("N\u00B0Goal:");
		lblNgoal.setBounds(159, 139, 69, 20);
		getContentPane().add(lblNgoal);
		
		JLabel lblNgoal_1 = new JLabel();
		lblNgoal_1.setBounds(242, 138, 69, 20);
		getContentPane().add(lblNgoal_1);
		
		JLabel lblSquadra = new JLabel("Squadra:");
		lblSquadra.setBounds(153, 189, 69, 20);
		getContentPane().add(lblSquadra);
		
		JLabel lblTeam = new JLabel();
		lblTeam.setBounds(242, 189, 69, 20);
		getContentPane().add(lblTeam);
		
		JLabel label = new JLabel("<-BACK");
		label.setBounds(15, 208, 69, 20);
		getContentPane().add(label);
		
		
		
		byte[] img = drive.immagine(item, id_giocatore);	
		
		AcquisisciImmagine(lblFoto, img);
		
		lblName.setText(drive.Nome(item, id_giocatore));
		lblSurname.setText(drive.Cognome(item, id_giocatore));
		lblNgoal_1.setText(drive.NGoal(item, id_giocatore));
		lblTeam.setText(drive.Squadra(item, id_giocatore));

	}
	
	
	public void  AcquisisciImmagine(JLabel lblFoto, byte[] img) {
		ImageIcon image = new ImageIcon(img);
		java.awt.Image im = image.getImage();
		lblFoto.setIcon(image);
	}

}
