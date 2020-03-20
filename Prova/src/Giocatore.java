import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Giocatore extends JDialog {
	
	private JLabel lblFoto;
	private JLabel lblNome;
	private JLabel lblName;
	private JLabel lblCognome;
	private JLabel lblSurname;
	private JLabel lblNgoal;
	private JLabel lblNgoal_1;
	private JLabel lblSquadra;
	private JLabel lblTeam;
	private JLabel label;

	public Giocatore(Driver drive, Object item, String id_giocatore, String user, String pass) throws SQLException {
		
		
		setTitle("DETTAGLI GIOCATORE");
		setBounds(100, 100, 465, 323);
		getContentPane().setLayout(null);
		
		 lblFoto = new JLabel();
		lblFoto.setBounds(15, 16, 158, 165);
		getContentPane().add(lblFoto);
		
		 lblNome = new JLabel("Nome:");
		lblNome.setBounds(211, 43, 69, 20);
		getContentPane().add(lblNome);
		
		 lblName = new JLabel();
		lblName.setBounds(295, 43, 69, 20);
		getContentPane().add(lblName);
		
		 lblCognome = new JLabel("Cognome:");
		lblCognome.setBounds(211, 86, 87, 20);
		getContentPane().add(lblCognome);
		
		 lblSurname = new JLabel();
		lblSurname.setBounds(295, 86, 69, 20);
		getContentPane().add(lblSurname);
		
		 lblNgoal = new JLabel("N\u00B0Goal:");
		lblNgoal.setBounds(211, 139, 69, 20);
		getContentPane().add(lblNgoal);
		
		 lblNgoal_1 = new JLabel();
		lblNgoal_1.setBounds(295, 139, 69, 20);
		getContentPane().add(lblNgoal_1);
		
		 lblSquadra = new JLabel("Squadra:");
		lblSquadra.setBounds(211, 189, 69, 20);
		getContentPane().add(lblSquadra);
		
		 lblTeam = new JLabel();
		lblTeam.setBounds(295, 189, 69, 20);
		getContentPane().add(lblTeam);
		
		 label = new JLabel("");
		 label.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent arg0) {
		 		
		 		drive.ShowSquadre(item, user, pass);
		 		drive.NotShowGiocatore();
		 		
		 		
		 	}
		 });
		 ImageIcon BackImage = new ImageIcon(Login.class.getResource("img/back.png"));
		 label.setIcon(BackImage);
		label.setBounds(15, 197, 87, 70);
		getContentPane().add(label);
		
		
		
		byte[] img = drive.immagine(item, id_giocatore);	
		
		AcquisisciImmagine(lblFoto, img);
		
		lblName.setText(drive.Nome(item, id_giocatore));
		lblSurname.setText(drive.Cognome(item, id_giocatore));
		lblNgoal_1.setText(drive.NGoal(id_giocatore));
		lblTeam.setText(drive.Squadra(item, id_giocatore));

	}
	
	
	public void  AcquisisciImmagine(JLabel lblFoto, byte[] img) {
		ImageIcon image = new ImageIcon(img);
		java.awt.Image im = image.getImage();
		lblFoto.setIcon(image);
	}

}
