package gui;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
public class GUI_ThongKe extends JPanel {

	/**
	 * Create the panel.
	 */
	public GUI_ThongKe() {
		this.setBounds(10, 62, 864, 388);
		this.setVisible(true);
		//this.setLayout(null);
		 initUI();
    }
	
	private void initUI() {
		
	    CategoryDataset dataset = createDataset();
	    
	    JFreeChart chart = createChart(dataset);
	    ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new Dimension(864, 388));
	    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
	    chartPanel.setBackground(Color.white);
	    chartPanel.setSize(500,388);
	    add(chartPanel);
	    chartPanel.setLayout(null);
	    
	    JComboBox comboBox = new JComboBox();
	    comboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    comboBox.setModel(new DefaultComboBoxModel(new String[] {"Th\u00E1ng 1", "Th\u00E1ng 2", "Th\u00E1ng 3"}));
	    comboBox.setBounds(748, 11, 92, 27);
	    chartPanel.add(comboBox);
	    
	    JLabel lblNewLabel = new JLabel("Doanh s\u1ED1 th\u00E1ng n\u00E0y");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    lblNewLabel.setBounds(43, 11, 137, 27);
	    chartPanel.add(lblNewLabel);
	    
	    
} 	
	
	
	private CategoryDataset createDataset() {

    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(23, "Gold medals", "USA");
        dataset.setValue(10, "Gold medals", "China");
        dataset.setValue(25, "Gold medals", "UK");
        dataset.setValue(31, "Gold medals", "Russia");
        dataset.setValue(40, "Gold medals", "South Korea");
        dataset.setValue(25, "Gold medals", "Germany");

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

    	GUI_ThongKe ex = new GUI_ThongKe();
        ex.setVisible(true);
    });
}
}
