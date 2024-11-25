package koalinha;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class mesas {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void mesasScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mesas window = new mesas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mesas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(210, 222, 198));
		frame.setBounds(100, 100, 519, 480);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ricko\\eclipse-workspace\\Koala\\Design sem nome(1).png"));
		lblNewLabel.setBounds(89, 94, 300, 315);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mesas do Forkoala");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(111, 42, 250, 23);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
