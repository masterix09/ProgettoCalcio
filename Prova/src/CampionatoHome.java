import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Font;

public class CampionatoHome extends JDialog {

	private JPanel contentPane;
	private Driver driver;
	JLabel label;
	
	
	public CampionatoHome(Driver drive, Object item) {
		
		setResizable(false);
		setTitle("CAMPIONATO HOME");
		
		
	
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("img\\logo2.png"));
	
		
		setBounds(100, 100, 720, 541);
		
		
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 717, 515);
		
		getContentPane().add(panel);
		
		panel.setLayout(null);	
		
		
		
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(0, 20, 209, 188);
		panel.add(label_1);
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				drive.ShowSquadre(item);
				
				
			}
		});		
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.setIcon(new ImageIcon("img\\squadre.png"));
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(0, 316, 242, 156);
		panel.add(label_2);
		label_2.setIcon(new ImageIcon("img\\calendarioOFF.png"));
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				drive.ShowPartite(item);
				
				
			}
		});
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(512, 35, 100, 100);
		panel.add(label_3);
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				drive.ShowClassifica(item);
				
			}
		});
		label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_3.setIcon(new ImageIcon("img\\trofeo.png"));
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(439, 297, 277, 175);
		panel.add(label_4);
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				drive.ShowAggiorna();
				
				
			}
		});
		label_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_4.setIcon(new ImageIcon("img\\DOWNLO.png"));
		
		
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(0, 10, 716, 504);
		label_5.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\campoofFf.png"));
		panel.add(label_5);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
}
