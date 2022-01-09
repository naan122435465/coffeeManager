package gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.MenuDAO;
import dto.Menu;

import utilities.CoffeeDataSource;

import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;

public class GUI_QuanLy extends JPanel {
	private MenuDAO menu_dao;
	JPanel curJPanel;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public GUI_QuanLy() throws ClassNotFoundException, IOException, SQLException {
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    menu_dao = new MenuDAO(conn);
		
		this.setBackground(Color.WHITE);
		this.setBounds(10, 62, 864, 388);
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.setLayout(null);
		
		ImageIcon iconLogo = new ImageIcon("images/logo.png");
		
		ImageIcon icon_QLMenu = new ImageIcon("images/mailinglist.jpg");
		
		ImageIcon icon_QLNhanVien = new ImageIcon("images/qluser.png");
		
		ImageIcon icon_QLTaiKhoan = new ImageIcon("images/qluser.png");
		
		
		ImageIcon icon_AddMenu = new ImageIcon("images/file_add.png");
		
		ImageIcon icon_EditMenu = new ImageIcon("images/document_edit.png");
		
		ImageIcon icon_DeleteMenu = new ImageIcon("images/file_delete.png");
		
		ImageIcon iconBack = new ImageIcon(new ImageIcon("images/back.png").getImage().getScaledInstance(40,40,20));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 864, 388);
		this.add(panel);
		panel.setLayout(null);
		
		JButton btnQLTD = new JButton("Quản lý thực đơn");
		btnQLTD.setBounds(10, 39, 165, 23);
		panel.add(btnQLTD);
		btnQLTD.setIcon(icon_QLMenu);
		
		
		
		JButton btnQLNV = new JButton("Quản lý nhân viên");
		btnQLNV.setBounds(10, 79, 165, 23);
		panel.add(btnQLNV);
		btnQLNV.setIcon(icon_QLNhanVien);
		
		JButton btnQLTK = new JButton("Quản lý tài khoản");
		btnQLTK.setBounds(10, 119, 165, 23);
		panel.add(btnQLTK);
		btnQLTK.setIcon(icon_QLTaiKhoan);
		
		JButton btnQLDH = new JButton("Quản lý đơn hàng");
		btnQLDH.setBounds(10, 157, 165, 23);
		panel.add(btnQLDH);
		curJPanel = new GUI__QuanLyTD() ;
		//Hien thi quan ly nhan vien
		btnQLNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

	
				try {
					btnQLNV.getParent().remove(curJPanel );
					btnQLNV.getParent().revalidate();
					curJPanel = new GUI_QuanLyNV();
					btnQLNV.getParent().add(curJPanel);
					btnQLNV.getParent().repaint();
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		});
		
		btnQLTD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnQLTD.getParent().remove(curJPanel );
				btnQLTD.getParent().revalidate();
				try {
					curJPanel = new GUI__QuanLyTD() ;
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnQLTD.getParent().add(curJPanel);
				btnQLNV.getParent().repaint();
				
			}
		});
		//Hien thi quan ly tai khoan
		btnQLTK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
//				if(e.getSource()== btnNewButton){
//					Panel1 myWindow = new Panel1(); 
//				}
				
					
				

				try {
					btnQLTK.getParent().remove(curJPanel);
					btnQLTK.getParent().revalidate();
					curJPanel = new GUI_QuanLyTK();
					btnQLTK.getParent().add(curJPanel);
					btnQLTK.getParent().repaint();
				} catch (ClassNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//btnNewButton.getParent().add(new JLabel("Test"));
				// Tao 1 label
				// Set tao do
				//btnNewButton.getParent().add( new testnao());
				
				
				
				
				
				//btnNewButton.getParent().getClass()
				//System.out.print(btnNewButton.getParent().getClass());
				
				
			}
		});
		btnQLDH.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnQLDH.getParent().remove(curJPanel);
				btnQLDH.getParent().revalidate();
				try {
					curJPanel = new GUI_QuanLyOrder();
					btnQLDH.getParent().add(curJPanel);
					btnQLDH.getParent().repaint();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnQLDH.getParent().add(curJPanel);
				btnQLDH.getParent().repaint();
			}
		});
	}
}
