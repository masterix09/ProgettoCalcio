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

	public Classifica(Driver drive){
		setTitle("CLASSIFICA");
		
		setBounds(100, 100, 1058, 372);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 45, 584, 230);
		getContentPane().add(scrollPane);
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(69, 45, 571, 230);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setVisible(false);
		lblNome.setBounds(660, 216, 69, 20);
		getContentPane().add(lblNome);
		
		JLabel lblCognome = new JLabel("Cognome:");
		lblCognome.setVisible(false);
		lblCognome.setBounds(660, 169, 94, 20);
		getContentPane().add(lblCognome);
		
		JLabel lblGoalFatti = new JLabel("Goal Fatti:");
		lblGoalFatti.setVisible(false);
		lblGoalFatti.setBounds(660, 252, 81, 20);
		getContentPane().add(lblGoalFatti);
		
		JLabel label3 = new JLabel("");
		label3.setVisible(true);
		label3.setBounds(764, 252, 124, 20);
		getContentPane().add(label3);
		
		JLabel label = new JLabel("");
		label.setBounds(754, 216, 124, 20);
		getContentPane().add(label);
		
		JLabel label2 = new JLabel("");
		label2.setBounds(771, 169, 124, 20);
		getContentPane().add(label2);
		
		
			
		
		CreaTabella(scrollPane);
		
		drive.PopolaTabellaClassifica(table);
		
		
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
				drive.PopolaTabellaClassifica(table);
				scrollPane.setViewportView(table);
				lblNome.setVisible(false);
				lblCognome.setVisible(false);
				lblGoalFatti.setVisible(false);
				label.setVisible(false);
				label2.setVisible(false);
				label3.setVisible(false);
				
				
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
				drive.PopolaTabellaMarcatori(table);
				scrollPane2.setViewportView(table);
				

				table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
					
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						
						
						lblNome.setVisible(true);
						lblCognome.setVisible(true);
						lblGoalFatti.setVisible(true);
						label.setVisible(true);
						label2.setVisible(true);
						label3.setVisible(true);
						label.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
						label2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
						label3.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
						
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
				{null, null, null, null},
			},
			new String[] {
				"SQUADRA", "PT", "GF", "GS"
			}
		));
		scrollPane.setViewportView(table);
		
	}
	
	
	
	public void CreaTabellaMarcatori(JScrollPane scrollPane2) {
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"NOME", "COGNOME", "GF", "SQUADRA"
			}
		));
		scrollPane2.setViewportView(table);
		
	}
}
	
	

