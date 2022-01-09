package gui;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.ThongKeDAO;
import utilities.CoffeeDataSource;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
public class GUI_ThongKe extends JPanel {
	private JTextField tf_nam;
	Connection conn;
	ThongKeDAO thong_ke_dao;
	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public GUI_ThongKe() throws ClassNotFoundException, IOException, SQLException {
		setBackground(SystemColor.activeCaption);
		this.setBounds(10, 62, 864, 388);
		this.setVisible(true);
		//this.setLayout(null);
		 initUI();
    }
	
	private void initUI() throws ClassNotFoundException, IOException, SQLException {
		CoffeeDataSource.init("database.properties");
		Connection conn = CoffeeDataSource.getConnection();
		thong_ke_dao = new ThongKeDAO(conn);
		setLayout(null);
		JLabel lblNewLabel = new JLabel("N\u0103m:");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblNewLabel.setBounds(639, 12, 43, 19);
	    add(lblNewLabel);
	    
	    tf_nam = new JTextField();
	    tf_nam.setHorizontalAlignment(SwingConstants.CENTER);
	    tf_nam.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    tf_nam.setText("2022");
	    tf_nam.setBounds(687, 9, 60, 25);
	    add(tf_nam);
	    tf_nam.setColumns(10);
	    int nam = Integer.parseInt(tf_nam.getText());
	   
	    JPanel panel = new JPanel();
	    panel.setBounds(10, 45, 844, 343);
	    add(panel);
	    
	    JButton btnDoanhThu = new JButton("Doanh thu");
	    btnDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnDoanhThu.setBounds(34, 11, 100, 23);
	    add(btnDoanhThu);
	    btnDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel.removeAll();
					panel.getParent().revalidate();
				 	CategoryDataset dataset;
					try {
						try {
							dataset = DoanhThuDataset(nam);
							JFreeChart chart = createChart(dataset);
						    setLayout(null);
						    ChartPanel chartPanel = new ChartPanel(chart);
						    chartPanel.setPreferredSize(new Dimension(844, 350));
						    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
						    chartPanel.setBackground(Color.white);				    
						    chartPanel.setLayout(null);
						    panel.add(chartPanel);
							panel.repaint();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	    
				    
				
			}
		});
	    	
	    
	    
	    
	    
	    JButton btnChiPhi = new JButton("Chi ph\u00ED");
	    btnChiPhi.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnChiPhi.setBounds(144, 11, 100, 23);
	    add(btnChiPhi);
	    btnChiPhi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel.removeAll();
					panel.getParent().revalidate();
				 	CategoryDataset dataset;
					try {
						try {
							dataset = chiPhiDataset(nam);
							JFreeChart chart = createChart(dataset);
						    setLayout(null);
						    ChartPanel chartPanel = new ChartPanel(chart);
						    chartPanel.setPreferredSize(new Dimension(844, 350));
						    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
						    chartPanel.setBackground(Color.white);				    
						    
						    chartPanel.setLayout(null);
						    panel.add(chartPanel);
							panel.repaint();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	    
				    
				
			}
		});
	    
	    
	    JButton btnLoiNhuan = new JButton("L\u1EE3i nhu\u1EADn");
	    btnLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnLoiNhuan.setBounds(254, 11, 100, 23);
	    add(btnLoiNhuan);
	    btnLoiNhuan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					panel.removeAll();
					panel.getParent().revalidate();
				 	CategoryDataset dataset;
					try {
						try {
							dataset = LoiNhuanDataset(nam);
							JFreeChart chart = createChart(dataset);
							  setLayout(null);
							  ChartPanel chartPanel = new ChartPanel(chart);
							  chartPanel.setPreferredSize(new Dimension(844, 350));
							  chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
							  chartPanel.setBackground(Color.white);				      
							  chartPanel.setLayout(null);
							  panel.add(chartPanel);
							  panel.repaint();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	    
				   
				
			}
		});
	    
	    
	    
	    
	    
	    
} 	
	
	
	private CategoryDataset DoanhThuDataset(int nam) throws SQLException, ClassNotFoundException, IOException {
		CoffeeDataSource.init("database.properties");
		
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       for(int i = 1 ; i <= 12 ; i++) {
    	   Connection conn = CoffeeDataSource.getConnection();
   			thong_ke_dao = new ThongKeDAO(conn);
    	   int dt = thong_ke_dao.doanhthu(i, nam);
	   		
	   		dataset.setValue(dt, "VND", "Tháng " + i);
	   		
       }
       

        return dataset;
    }
	private CategoryDataset LoiNhuanDataset(int nam) throws SQLException, ClassNotFoundException, IOException {
		CoffeeDataSource.init("database.properties");
		
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       for(int i = 1 ; i <= 12 ; i++) {
    	   Connection conn = CoffeeDataSource.getConnection();
   		thong_ke_dao = new ThongKeDAO(conn);
    	   int dt = thong_ke_dao.loiNhuan(i, nam);
    	   dataset.setValue(dt, "VND", "Tháng " + i);
    	   
       }
       

        return dataset;
    }
	private CategoryDataset chiPhiDataset(int nam) throws SQLException, ClassNotFoundException, IOException {
		CoffeeDataSource.init("database.properties");
		
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       for(int i = 1 ; i <= 12 ; i++) {
    	   Connection conn = CoffeeDataSource.getConnection();
   		thong_ke_dao = new ThongKeDAO(conn);
    	   int dt = thong_ke_dao.chiphi(i, nam);
    	   dataset.setValue(dt, "VND", "Tháng " + i);
    	   conn.close();
       }
       

        return dataset;
    }
	 private JFreeChart createChart(CategoryDataset dataset) {

	        JFreeChart barChart = ChartFactory.createBarChart(
	                "CAFE GOOD CHÓP",
	                "",
	                "",
	                dataset,
	                PlotOrientation.VERTICAL,
	                false, true, false);

	        return barChart;
	    }
	
	public static void main(String[] args) {

    EventQueue.invokeLater(() -> {

    	GUI_ThongKe ex;
		try {
			ex = new GUI_ThongKe();
			  ex.setVisible(true);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    });
}
}
