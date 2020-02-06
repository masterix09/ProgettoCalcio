import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;

public class Match extends JDialog {
	private JTable table;
	private JTable table_1;
	

	public Match(Driver drive) {
		setTitle("CALENDARIO");
		setBounds(100, 100, 1121, 461);	
			getContentPane().setLayout(null);
	
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 684, 245);
			getContentPane().add(scrollPane);
			
			CreaTabellaMatch(scrollPane);
			
			drive.PopolaTabellaMatch(table);
			
			
			
			
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					
					//VISUALIZZAZIONE SECONDA TABELLA
					JScrollPane scrollPane_1 = new JScrollPane();
					scrollPane_1.setBounds(711, 32, 359, 213);
					getContentPane().add(scrollPane_1);
					
					table_1 = new JTable();
					table_1.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null, null},
						},
						new String[] {
							"Nome", "Cognome", "Partita", "Squadra", "NMaglia", "Time"
						}
					));
					scrollPane_1.setViewportView(table_1);
					
					
					drive.PopolatabellaGoal(table_1, table);
					
					
				}
			});
		
		
	}
	
	
	
	public void CreaTabellaMatch(JScrollPane scrollPane) {
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"GIORNATA", "CASA", "OSPITE", "GOAL CASA", "GOAL OSPITE", "ARBITRO"
			}
		));
		
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		
		
		scrollPane.setViewportView(table);
		
		
		
		
		
		
		
	}
}
