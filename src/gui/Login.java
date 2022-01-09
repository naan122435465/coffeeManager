package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Result;

import dao.UserNameDAO;
import dto.UserName;
import utilities.CoffeeDataSource;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField textField_Password;
	private JTextField textField_UserName;
	private JButton btn_dang_nhap;
	private JButton btnThot;
	private JLabel lblLogo;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Login() throws ClassNotFoundException, IOException, SQLException {
		
		CoffeeDataSource.init("database.properties");
	    Connection conn = CoffeeDataSource.getConnection();
	    UserNameDAO user_name_dao =  new UserNameDAO(conn);
		setTitle("Cafe good chóp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 300, 447, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLayout(null);
		
		JLabel user_label = new JLabel();
		user_label.setHorizontalAlignment(SwingConstants.CENTER);
		user_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		user_label.setText("T\u00EAn \u0111\u0103ng nh\u1EADp ");
		user_label.setBounds(68, 64, 112, 29);
		contentPane.add(user_label);
		
		textField_Password = new JPasswordField();
		textField_Password.setBounds(191, 100, 219, 29);
		contentPane.add(textField_Password);
		
		JLabel password_label = new JLabel();
		password_label.setHorizontalAlignment(SwingConstants.CENTER);
		password_label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password_label.setText("M\u1EADt kh\u1EA9u");
		password_label.setBounds(52, 98, 112, 29);
		contentPane.add(password_label);
		
		textField_UserName = new JTextField();
		textField_UserName.setBounds(190, 66, 220, 29);
		contentPane.add(textField_UserName);
		
		btn_dang_nhap = new JButton("\u0110\u0103ng nh\u1EADp");
		btn_dang_nhap.setBounds(191, 140, 103, 32);
		contentPane.add(btn_dang_nhap);
		
		btnThot = new JButton("Tho\u00E1t");
		btnThot.setBounds(304, 140, 78, 32);
		contentPane.add(btnThot);
		
		ImageIcon iconLogo = new ImageIcon(new ImageIcon("images/logo (2).png").getImage().getScaledInstance(95, 80,20));		
		lblLogo = new JLabel("");
		lblLogo.setBounds(10, 0, 103, 82);
		contentPane.add(lblLogo);
		lblLogo.setIcon(iconLogo);
		
		lblNewLabel = new JLabel("Cafe Good Chóp");
		lblNewLabel.setForeground(new Color(139, 69, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 34));
		lblNewLabel.setBounds(123, 11, 259, 41);
		contentPane.add(lblNewLabel);
		
		
		btn_dang_nhap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				String username = textField_UserName.getText();
				String pass  = textField_Password.getText();
				int id = user_name_dao.getUserRole(username, pass);
				if(id !=0) {
					 try {
						 Layout_form frame = new Layout_form(id);
						frame.setVisible(true);
						dispose();
						
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
				}
				else {
					
						JOptionPane.showMessageDialog(null,"Tên đăng nhập hoặc mật khẩu không đúng");
					
				}
				
			}
		});
	}
}
