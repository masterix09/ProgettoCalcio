import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Classifica extends JDialog {
	private JTable table;
	private JButton btnChange;
	private JButton CHANGE;	
	private JTable table_1;

	public Classifica(Driver drive, Object item){
		setTitle("CLASSIFICA");
		
		setBounds(100, 100, 1058, 372);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 45, 584, 230);
		getContentPane().add(scrollPane);
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(69, 45, 571, 230);
		
		
			
		
		CreaTabella(scrollPane);
		
		drive.PopolaTabellaClassifica(table, item);
		
		
		btnChange = new JButton("CLASSIFICA SQUADRA");
		btnChange.setVisible(false);
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				scrollPane2.setVisible(false);
				btnChange.setVisible(false);
				CHANGE.setVisible(true);
				scrollPane.setVisible(true);
				getContentPane().add(scrollPane);
				CreaTabella(scrollPane);
				drive.PopolaTabellaClassifica(table, item);
				scrollPane.setViewportView(table);
				
				
				
			}
		});
		btnChange.setBounds(653, 63, 223, 29);
		getContentPane().add(btnChange);
		
		
		
		CHANGE = new JButton("CLASSIFICA MARCATORI");
		CHANGE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				scrollPane.setVisible(false);
				CHANGE.setVisible(false);
				btnChange.setVisible(true);
				scrollPane2.setVisible(true);

				getContentPane().add(scrollPane2);
				
				CreaTabellaMarcatori(scrollPane2);
				drive.PopolaTabellaMarcatori(table, item);
				scrollPane2.setViewportView(table);
				

				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setVisible(false);
				scrollPane_1.setBounds(663, 108, 358, 192);
				getContentPane().add(scrollPane_1);
				
				table_1 = new JTable();
				table_1.setVisible(false);
				table_1.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null},
					},
					new String[] {
						"Nome", "Cognome", "Partita"
					}
				));
				scrollPane_1.setViewportView(table_1);
				
				
				
				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						
						scrollPane_1.setVisible(true);
						table_1.setVisible(true);
						drive.PopolaTabellaListaVittimeGiocatore(table_1, table);
						
					}
				});
				
					
				
			}
		});
		CHANGE.setBounds(653, 63, 225, 29);
		getContentPane().add(CHANGE);
		
			
	}
	
	public void CreaTabella(JScrollPane scrollPane) {
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null,null, null, null, null},
			},
			new String[] {
				"SQUADRA", "PT","VINTE", "PERSE", "PAREGGIATE", "GF", "GS", 
			}
		));
		scrollPane.setViewportView(table);
		
	}
	
	
	
	public void CreaTabellaMarcatori(JScrollPane scrollPane2) {
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID", "NOME", "COGNOME", "GF", "SQUADRA"
			}
		));
		scrollPane2.setViewportView(table);
		
	}
}
	
	

