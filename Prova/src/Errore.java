import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Errore extends JDialog {

	private JPanel panel;

	public Errore(Driver drive, String pane) {
		
		panel = new JPanel();
		panel.setBounds(15, 16, 379, 189);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon("C:\\Users\\Pablo\\git\\ProgettoCalcio-miaCopia\\Prova\\img\\alert.png"));
		lblFoto.setBounds(99, 0, 185, 103);
		panel.add(lblFoto);
		
		JLabel lblError = new JLabel("ERRORE:");
		lblError.setBounds(71, 124, 69, 20);
		panel.add(lblError);
		
		JLabel label = new JLabel("");
		label.setBounds(155, 124, 129, 20);
		label.setText(pane);
		panel.add(label);
		
		
		
		
		
		
	}

}
