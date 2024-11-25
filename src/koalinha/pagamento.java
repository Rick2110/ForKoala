package koalinha;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JButton;

public class pagamento {

	private JFrame frame;
	private String usuario;

	/**
	 * Launch the application.
	 */
	public static void telaPagamento(String usuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pagamento window = new pagamento(usuario);
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
	public pagamento(String usuario) {
		this.usuario = usuario;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 556);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Valor:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel.setBounds(132, 42, 226, 36);
		frame.getContentPane().add(lblNewLabel);
		
		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource("/image.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Erro ao conectar com o banco de dados: " + e.getMessage());
		}
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		wIcon.setBounds(58, 107, 366, 309);
		frame.getContentPane().add(wIcon);
		
		JButton btnNewButton_1 = new JButton("Pagamento Realizado");
		btnNewButton_1.setBounds(143, 449, 180, 36);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				suasReservas reservas = new suasReservas(usuario);
				reservas.reservasScreen(usuario);
			}
		});
	}
}
