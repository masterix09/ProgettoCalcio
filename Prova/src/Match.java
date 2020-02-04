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
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JScrollPane;

public class Match extends JDialog {
	private JTable table;
	

	public Match(Driver drive) {
		setBounds(100, 100, 702, 313);	
	
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			CreaTabellaMatch(scrollPane);
			
			drive.PopolaTabellaMatch(table); 
			
			
			
		
		
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
