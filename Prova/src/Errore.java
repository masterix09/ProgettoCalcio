import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Errore extends JDialog {

	private JPanel panel;
	private JLabel lblFoto;
	private JLabel lblError;
	private JLabel label;
	private JLabel lblRiprova;
	
	
	public Errore(Driver drive, String pane) {
		
		panel = new JPanel();
		panel.setBounds(15, 16, 379, 189);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblFoto = new JLabel("");
		ImageIcon AlertImage = new ImageIcon(Errore.class.getResource("img/alert.png"));
		lblFoto.setIcon(AlertImage);
		lblFoto.setBounds(99, 0, 185, 103);
		panel.add(lblFoto);
		
		lblError = new JLabel("ERRORE:");
		lblError.setBounds(71, 124, 69, 20);
		panel.add(lblError);
		
		label = new JLabel("");
		label.setBounds(155, 124, 129, 20);
		label.setText(pane);
		panel.add(label);
		
		if(label.getText() == "CREDENZIALI ERRATE") {

			lblRiprova = new JLabel();
			ImageIcon SquadreImage = new ImageIcon(Errore.class.getResource("img/riprova.png"));
			lblRiprova.setIcon(SquadreImage);
			lblRiprova.setBounds(71, 150, 170, 40);
			
			lblRiprova.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					drive.ShowLogin();
					drive.NotShowError();
					
				}
			});
			
			panel.add(lblRiprova);
			
		}
	}

}
