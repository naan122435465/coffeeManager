package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class QuanLy_form extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public QuanLy_form() {
		setTitle("Quản lý");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon iconLogo = new ImageIcon("images/logo.png");
		setIconImage(iconLogo.getImage());
		
		
		ImageIcon icon_QLMenu = new ImageIcon("images/mailinglist.jpg");
		JButton btnNewButton = new JButton("Qu\u1EA3n l\u00FD th\u1EF1c \u0111\u01A1n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 113, 165, 23);
		contentPane.add(btnNewButton);
		btnNewButton.setIcon(icon_QLMenu);
		
		ImageIcon icon_QLNhanVien = new ImageIcon("images/qluser.png");
		JButton btnNewButton_1 = new JButton("Qu\u1EA3n l\u00FD nh\u00E2n vi\u00EAn");
		btnNewButton_1.setBounds(10, 153, 165, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.setIcon(icon_QLNhanVien);
		
		ImageIcon icon_QLTaiKhoan = new ImageIcon("images/qluser.png");
		JButton btnNewButton_2 = new JButton("Qu\u1EA3n l\u00FD t\u00E0i kho\u1EA3n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(10, 193, 165, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.setIcon(icon_QLTaiKhoan);
		
		
		ImageIcon icon_AddMenu = new ImageIcon("images/file_add.png");
		JButton btnNewButton_3 = new JButton("Thêm món");
		btnNewButton_3.setBounds(725, 113, 108, 45);
		contentPane.add(btnNewButton_3);
		btnNewButton_3.setIcon(icon_AddMenu);
		
		ImageIcon icon_EditMenu = new ImageIcon("images/document_edit.png");
		JButton btnNewButton_3_1 = new JButton("Sửa món");
		btnNewButton_3_1.setBounds(725, 170, 108, 45);
		contentPane.add(btnNewButton_3_1);
		btnNewButton_3_1.setIcon(icon_EditMenu);
		
		ImageIcon icon_DeleteMenu = new ImageIcon("images/file_delete.png");
		JButton btnNewButton_3_2 = new JButton("Xóa món");
		btnNewButton_3_2.setBounds(725, 226, 108, 45);
		contentPane.add(btnNewButton_3_2);
		btnNewButton_3_2.setIcon(icon_DeleteMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(197, 113, 469, 315);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "T\u00EAn m\u00F3n", "Gi\u00E1"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("T\u00ECm m\u00F3n");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(473, 88, 59, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(542, 85, 124, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("T\u1ED5ng s\u1ED1 m\u00F3n");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setBounds(213, 88, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		ImageIcon iconBack = new ImageIcon(new ImageIcon("images/back.png").getImage().getScaledInstance(40,40,20));
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(10, 11, 78, 45);
		contentPane.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(iconBack);
	}
}
