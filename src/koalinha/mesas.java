package koalinha;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
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
	    frame.getContentPane().setBackground(new Color(210, 222, 198)); // Define a cor de fundo
	    frame.setBounds(100, 100, 519, 480); // Define o tamanho e posição da janela
	    frame.getContentPane().setLayout(null); // Usar layout absoluto

	    // JLabel para exibir uma imagem sem texto
	    JLabel lblImage = new JLabel();
	    lblImage.setBounds(89, 94, 300, 315); // Define a posição e tamanho do JLabel
	    frame.getContentPane().add(lblImage); // Adiciona o JLabel ao frame

	    // Adiciona a imagem ao JLabel
	    try {
	        BufferedImage image = ImageIO.read(this.getClass().getResource("/Design sem nome(1).png"));
	        lblImage.setIcon(new ImageIcon(image)); // Configura a imagem como ícone
	    } catch (IOException e) {
	        e.printStackTrace(); // Mostra erros se a imagem não for carregada
	    }

	    // Título da janela
	    JLabel lblTitle = new JLabel("Mesas do Forkoala");
	    lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 30)); // Define a fonte e tamanho
	    lblTitle.setBounds(111, 42, 250, 23); // Define a posição do título
	    frame.getContentPane().add(lblTitle); // Adiciona o título ao frame

	    frame.setLocationRelativeTo(null); // Centraliza a janela na tela
	}
}
