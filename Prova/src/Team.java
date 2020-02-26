import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class Team extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	public Team(Driver drive, Object item) {
		setTitle("LISTA SQUADRE");
		setBounds(100, 100, 1111, 300);
		getContentPane().setLayout(null);
		
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 999, 228);
			getContentPane().add(scrollPane);
			
					CreaTabella(scrollPane);
					
					drive.PopolaTabellaTeam(table, item);
					
					JLabel label = new JLabel(item.toString());
					label.setBounds(1005, 56, 69, 20);
					getContentPane().add(label);
					

			
		
			
	}
	
	
	public void CreaTabella(JScrollPane scrollPane) {
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id_Team", "Nome", "Presidente", "Allenatore", "Stadio"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		
		
		scrollPane.setViewportView(table);
	}
}
