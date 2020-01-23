import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
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
import java.awt.Toolkit;
import java.awt.Font;

public class GUI extends JFrame {

	private JPanel contentPane;
	private Driver driver;
	JLabel label;
	
	public GUI(Driver drive) {
		setForeground(new Color(255, 255, 0));
		setResizable(false);
		setTitle("DIRETTA GoAL") ;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\logo2.png"));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 543);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\logoleeds.png"));
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				drive.ShowSquadre();
				
				
			}
		});
		label_1.setBounds(118, 155, 115, 115);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\calendario.png"));
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				drive.NotShowGui();
				drive.ShowPartite();
				
				
			}
		});
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_2.setBounds(125, 304, 93, 81);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
			}
		});
		label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_3.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\trofeo.png"));
		label_3.setBounds(470, 155, 115, 115);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				drive.ShowAggiorna();
				
				
			}
		});
		label_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_4.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\upload.png"));
		label_4.setBounds(470, 313, 85, 72);
		contentPane.add(label_4);
		
		JLabel lblListaSquadre = new JLabel("LISTA SQUADRE");
		lblListaSquadre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblListaSquadre.setForeground(new Color(0, 0, 255));
		lblListaSquadre.setBounds(103, 277, 148, 20);
		contentPane.add(lblListaSquadre);
		
		JLabel lblClassifica = new JLabel("CLASSIFICA");
		lblClassifica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblClassifica.setForeground(new Color(0, 0, 255));
		lblClassifica.setBounds(464, 286, 121, 20);
		contentPane.add(lblClassifica);
		
		JLabel lblCalendario = new JLabel("CALENDARIO");
		lblCalendario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblCalendario.setForeground(new Color(0, 0, 255));
		lblCalendario.setBounds(103, 390, 130, 20);
		contentPane.add(lblCalendario);
		
		JLabel lblUpload = new JLabel("UPLOAD");
		lblUpload.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUpload.setForeground(new Color(0, 0, 255));
		lblUpload.setBounds(470, 390, 69, 20);
		contentPane.add(lblUpload);
		
		JLabel lblBenvenutiSuDiretta = new JLabel("BENVENUTI SU DIRETTA GOAL");
		lblBenvenutiSuDiretta.setBackground(new Color(51, 102, 204));
		lblBenvenutiSuDiretta.setForeground(new Color(102, 204, 102));
		lblBenvenutiSuDiretta.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblBenvenutiSuDiretta.setBounds(113, 16, 472, 98);
		contentPane.add(lblBenvenutiSuDiretta);
		
		
		
		
	}
}
