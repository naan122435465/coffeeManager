package gui;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class hinhNen extends JPanel {
	public hinhNen() {
		setLayout(null);
		setBounds(100, 100, 900, 500);
		JPanel panel = new JPanel();
		panel.setBounds(224, 5, 1, 1);
		panel.setLayout(null);
		add(panel);
		ImageIcon iconLogo = new ImageIcon("images/logo.png");
		JLabel lblHinhNen = new JLabel();
		lblHinhNen.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhNen.setBounds(0, 0, 864, 388);
		panel.add(lblHinhNen);
		
		JLabel lblHinhNen_1 = new JLabel();
		lblHinhNen_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblHinhNen_1.setBounds(-48, -22, 864, 388);
		add(lblHinhNen_1);
	
		
		
	}
}
