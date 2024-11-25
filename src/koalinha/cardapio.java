package koalinha;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class cardapio {

	private JFrame frame;
	String usuario;

	/**
	 * Launch the application.
	 */
	public static void cardapioScreen(String usuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cardapio window = new cardapio(usuario);
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
	public cardapio(String usuario) {
		this.usuario = usuario;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(210, 222, 198));
		frame.setBounds(100, 100, 500, 552);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cardapio");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(170, 92, 119, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Acesse o cardapio escaneando");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(138, 131, 205, 29);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("o QR code abaixo:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(177, 155, 128, 29);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource("/image.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		frame.getContentPane().add(wIcon);
		
		wIcon.setBounds(92, 194, 300, 311);
		frame.getContentPane().add(wIcon);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(10, 10, 89, 97);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(387, 10, 89, 71);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				telaPrincipal voltar = new telaPrincipal(usuario);
				voltar.telaScreen(usuario);
			}
			
		});
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Koala deslog = new Koala();
				deslog.main(null);
				
			}
			
		});
		String iconfilePath = this.getClass().getClassLoader().getResource("return_icon.png").getFile();
		btnNewButton.setIcon(new ImageIcon(iconfilePath));
		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusable(false);
		
		String iconfilePath1 = this.getClass().getClassLoader().getResource("home_icon.png").getFile();
		btnNewButton_2.setIcon(new ImageIcon(iconfilePath1));
		btnNewButton_2.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFocusable(false);
	}
}
