package gui;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;

public class QuanLyNV_form extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public QuanLyNV_form() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 48, 693, 427);
		add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 476, 405);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "H\u1ECD t\u00EAn", "Ng\u00E0y sinh", "Ch\u1EE9c v\u1EE5", "L\u01B0\u01A1ng"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(34);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_3 = new JButton("Th\u00EAm nh\u00E2n vi\u00EAn");
		btnNewButton_3.setBounds(534, 11, 108, 45);
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("S\u1EEDa th\u00F4ng tin");
		btnNewButton_3_1.setBounds(534, 68, 108, 45);
		panel.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("X\u00F3a nh\u00E2n vi\u00EAn");
		btnNewButton_3_2.setBounds(534, 124, 108, 45);
		panel.add(btnNewButton_3_2);
		
		JLabel lblNewLabel_1 = new JLabel("Tổng số món");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(10, 23, 67, 14);
		add(lblNewLabel_1);

	}
}
