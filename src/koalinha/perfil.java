package koalinha;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class perfil {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	
	String usuario;

	/**
	 * Launch the application.
	 */
	public static void perfilScreen(String usuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					perfil window = new perfil(usuario);
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
	public perfil(String usuario) {
		this.usuario = usuario;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 610);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(158, 137, 140, 128);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PERFIL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(197, 104, 62, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(77, 275, 312, 241);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(22, 33, 45, 24);
		panel.add(lblNewLabel_2);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNome.setText(usuario);
		txtNome.setBounds(103, 33, 186, 24);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Email:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(22, 72, 45, 24);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("CPF:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(22, 111, 45, 24);
		panel.add(lblNewLabel_2_1_1);
		
		txtEmail = new JTextField();
		txtEmail.setText("Email");
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setColumns(10);
		txtEmail.setBounds(103, 72, 186, 24);
		panel.add(txtEmail);
		
		txtCpf = new JTextField();
		txtCpf.setText("CPF");
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCpf.setColumns(10);
		txtCpf.setBounds(103, 111, 186, 24);
		panel.add(txtCpf);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Telefone:");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(22, 150, 63, 24);
		panel.add(lblNewLabel_2_1_1_1);
		
		txtTelefone = new JTextField();
		txtTelefone.setText("Telefone");
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(103, 150, 186, 24);
		panel.add(txtTelefone);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(89, 196, 101, 30);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(10, 10, 105, 98);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBounds(357, 10, 119, 109);
		frame.getContentPane().add(btnNewButton_1_1);

		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				telaPrincipal voltar = new telaPrincipal(usuario);
				voltar.telaScreen(usuario);
			}
			
		});
		btnNewButton_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Koala deslog = new Koala();
				deslog.main(null);
				
			}
			
		});
		String iconfilePath = this.getClass().getClassLoader().getResource("return_icon.png").getFile();
		btnNewButton_1_1.setIcon(new ImageIcon(iconfilePath));
		btnNewButton_1_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setFocusable(false);
		
		String iconfilePath1 = this.getClass().getClassLoader().getResource("home_icon.png").getFile();
		btnNewButton_1.setIcon(new ImageIcon(iconfilePath1));
		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusable(false);
		
		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource("/profile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		wIcon.setBounds(172, 151, 114, 103);
		frame.getContentPane().add(wIcon);
	}
	
}
