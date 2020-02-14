import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.prism.Graphics;
import com.sun.prism.Image;

import javafx.scene.layout.Pane;

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
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Font;

public class GUI extends JFrame {

	private JPanel contentPane;
	private Driver driver;
	JLabel label;
	
	
	public GUI(Driver drive) {
		setForeground(new Color(255, 255, 0));
		setResizable(false);
		setTitle("DIRETTA GOAL") ;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\logo2.png"));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 543);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 716, 503);
		
		contentPane.add(panel);
		
		panel.setLayout(null);		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(93, 168, 100, 100);
		panel.add(label_1);
		label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_1.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\logoleeds.png"));
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(77, 338, 154, 114);
		panel.add(label_2);
		label_2.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\calenprovapng.png"));
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				drive.ShowPartite();
				
				
			}
		});
		label_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(450, 210, 100, 100);
		panel.add(label_3);
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				drive.ShowClassifica();
				
			}
		});
		label_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_3.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\trofeo.png"));
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(463, 388, 64, 64);
		panel.add(label_4);
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				drive.ShowAggiorna();
				
				
			}
		});
		label_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label_4.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\upload.png"));
		
		JLabel lblCalendario = new JLabel("CALENDARIO");
		lblCalendario.setBounds(93, 451, 109, 20);
		panel.add(lblCalendario);
		lblCalendario.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblCalendario.setForeground(new Color(0, 0, 255));
		
		JLabel lblBenvenutiSuDiretta = new JLabel("\r\n");
		lblBenvenutiSuDiretta.setBounds(262, 16, 204, 200);
		panel.add(lblBenvenutiSuDiretta);
		lblBenvenutiSuDiretta.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\c03a9fd6-ca62-4a0f-9def-c219cc9ff241_200x200.png"));
		lblBenvenutiSuDiretta.setBackground(new Color(51, 102, 204));
		lblBenvenutiSuDiretta.setForeground(new Color(102, 204, 102));
		lblBenvenutiSuDiretta.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JLabel lblUpload = new JLabel("UPLOAD");
		lblUpload.setBounds(463, 451, 68, 20);
		panel.add(lblUpload);
		lblUpload.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUpload.setForeground(new Color(0, 0, 255));
		
		JLabel lblListaSquadre = new JLabel("LISTA SQUADRE");
		lblListaSquadre.setBounds(77, 284, 134, 20);
		panel.add(lblListaSquadre);
		lblListaSquadre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblListaSquadre.setForeground(new Color(0, 0, 255));
		
		JLabel lblClassifica = new JLabel("CLASSIFICA");
		lblClassifica.setBounds(450, 326, 98, 20);
		panel.add(lblClassifica);
		lblClassifica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblClassifica.setForeground(new Color(0, 0, 255));
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(0, 0, 716, 503);
		label_5.setIcon(new ImageIcon("C:\\Users\\power\\git\\ProgettoCalcio\\Prova\\img\\campooff.png"));
		panel.add(label_5);
		
		
		
		
		
		
		
		
		
		
		
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				drive.ShowSquadre();
				
				
			}
		});
		
		
		
		
	}
	
	
	
	
	
}
