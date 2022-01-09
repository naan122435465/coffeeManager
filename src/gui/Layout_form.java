package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.PanelUI;
import javax.xml.crypto.Data;

import dao.UserNameDAO;
import dto.UserName;

import utilities.CoffeeDataSource;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class Layout_form extends JFrame {
	private JLabel labelClock;
	private JPanel contentPane, layoutPanel;
	GridBagConstraints c;
	protected Component lblHinhNen;
	JPanel curJPanel;
	 UserName user_dto;
	/**
	 * Launch the application.
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
//	public static void main(String[] args) {
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Layout_form frame = new Layout_form(id);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Layout_form(int id) throws ClassNotFoundException, IOException, SQLException {
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    UserNameDAO user_DAO = new UserNameDAO(conn);
	    user_dto = user_DAO.getUserID(id);
		setTitle("Cafe good chóp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImageIcon iconLogo = new ImageIcon("images/logo.png");
		setIconImage(iconLogo.getImage());
		
		
		//ImageIcon HinhNen = new ImageIcon("images/background.jpg");

		JPanel panel = new JPanel();
		panel.setBounds(10, 62, 864, 388);
		contentPane.add(panel);
		panel.setLayout(null);
		curJPanel = panel;
		
		
		
		ImageIcon HinhNen = new ImageIcon(new ImageIcon("images/background1.jpg").getImage().getScaledInstance(864, 393,20));
		JLabel lblHinhNen = new JLabel();
		lblHinhNen.setBounds(0, 0, 864, 388);
		panel.add(lblHinhNen);
		lblHinhNen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhNen.setIcon(HinhNen);
		
		ImageIcon iconBanHang = new ImageIcon("images/sellicon.png");
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOrder.getParent().remove(curJPanel);
				btnOrder.getParent().revalidate();

				try {
					curJPanel =new GUI_Order(user_dto);
					 btnOrder.getParent().add(curJPanel);
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnOrder.getParent().repaint();	
			}
		});
		btnOrder.setBounds(10, 11, 142, 40);
		contentPane.add(btnOrder);
		btnOrder.setIcon(iconBanHang);
		
		
		ImageIcon iconQL = new ImageIcon("images/icon-hoc-nau-an-Huong-nghiep-a-au-2015.png");
		JButton btnQL = new JButton("Quản lý");
		
		btnQL.setBounds(162, 11, 142, 40);
		contentPane.add(btnQL);
		btnQL.setIcon(iconQL);
		
		btnQL.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	
				btnQL.getParent().remove(curJPanel );
				btnQL.getParent().revalidate();
				try {
					curJPanel = new GUI_QuanLy();
					btnQL.getParent().add(curJPanel);
					btnQL.getParent().repaint();
					
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		ImageIcon iconThongKe = new ImageIcon("images/thongke.png");
		JButton btn_thong_ke = new JButton("Thống kê");
		btn_thong_ke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQL.getParent().remove(curJPanel );
				btnQL.getParent().revalidate();
			
					try {
						curJPanel = new GUI_ThongKe();
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
				
				btnQL.getParent().add(curJPanel);
				btnQL.getParent().repaint();
			}
		});
		btn_thong_ke.setBounds(314, 11, 142, 40);
		contentPane.add(btn_thong_ke);
		btn_thong_ke.setIcon(iconThongKe);
		
		
		ImageIcon iconKho = new ImageIcon("images/kho.png");
		JButton btn_kho = new JButton("Kho_NVL");
		btn_kho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnQL.getParent().remove(curJPanel );
				btnQL.getParent().revalidate();
				try {
					curJPanel = new GUI_Kho_NVL();
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
				btnQL.getParent().add(curJPanel);
				btnQL.getParent().repaint();
			}
		});
		
		
		
		
		btn_kho.setBounds(466, 11, 142, 40);
		contentPane.add(btn_kho);
		btn_kho.setIcon(iconKho);
		
		JLabel lblTime = new JLabel("17:50:22_PM");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(618, 11, 89, 35);
		contentPane.add(lblTime);
		int role = user_dto.getRole();
		if(role == 0) {
			btnQL.setEnabled(false);
			btn_thong_ke.setEnabled(false);
			btn_kho.setEnabled(false);
			
		}
		
		
		/*
		 * try { while (true) { Calendar calendar = Calendar.getInstance(); String hour
		 * = (calendar.getTime().getHours() > 9) ? "" + calendar.getTime().getHours() +
		 * "" : "0" + calendar.getTime().getHours(); String minute =
		 * (calendar.getTime().getMinutes() > 9) ? "" + calendar.getTime().getMinutes()
		 * + "" : "0" + calendar.getTime().getMinutes(); String second =
		 * (calendar.getTime().getSeconds() > 9) ? "" + calendar.getTime().getSeconds()
		 * + "" : "0" + calendar.getTime().getSeconds(); lblTime.setText(hour + ":" +
		 * minute + ":" + second); Thread.sleep(1000); } } catch (InterruptedException
		 * e) { e.printStackTrace(); }
		 */
		
		ImageIcon iconDangXuat = new ImageIcon("images/Logout.png");
		JButton btnDangXuat = new JButton("Đăng xuất");
		btnDangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Login();
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
				dispose();
				
			}
		});
		btnDangXuat.setBounds(743, 23, 131, 23);
		contentPane.add(btnDangXuat);
		btnDangXuat.setIcon(iconDangXuat);
		
		
		
        
        
		
		
		
		
		
		 
		
	}

	
}
