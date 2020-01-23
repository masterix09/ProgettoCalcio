import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import javax.swing.JTextArea;


public class Aggiornamento extends JFrame {

	private JPanel contentPane;
	private JButton btnScegliFile;
	private Driver drive;
		

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Upload frame = new Upload();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Aggiornamento(Driver drive) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(53, 16, 360, 134);
		contentPane.add(textArea);
		
		
		
		JButton btnScegliFile = new JButton();
		btnScegliFile.setText("SCEGLI FILE..");
		btnScegliFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Scegli File");
				int result = fc.showSaveDialog(null);

				File file = fc.getSelectedFile();
				Reader in = null;
				try {
					in = new FileReader(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					textArea.read(in, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println(in);
				
//				textArea.setText(fc.getSelectedFile().toString());
			}
		});
		btnScegliFile.setBounds(282, 199, 131, 29);
		contentPane.add(btnScegliFile);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				drive.NotShowAggiorna();
				drive.ShowGui();
				
			}
		});
		label.setIcon(new ImageIcon("C:\\Users\\power\\git\\atomHuni\\ProgettoOODB\\img\\back.png"));
		label.setBounds(15, 155, 87, 73);
		contentPane.add(label);
		
	
		
		
		
		
			
		}
}


