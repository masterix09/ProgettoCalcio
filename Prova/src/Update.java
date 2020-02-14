import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONException;
import org.json.simple.parser.ParseException;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Update extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	public Update(Driver drive) {
		setBounds(100, 100, 450, 300);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 30, 383, 144);
		getContentPane().add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		
		JButton btnScegli = new JButton("SCEGLI");
		btnScegli.setBounds(298, 199, 115, 29);
		btnScegli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fileChooser = new JFileChooser();
				int n = fileChooser.showOpenDialog(Update.this);
				
				//ACQUISISCE FILE
				File f = fileChooser.getSelectedFile();

				try {
					drive.Update(f);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
 catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnScegli);
		
		
		
		
	}
}
