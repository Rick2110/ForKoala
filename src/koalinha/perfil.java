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

	private JFrame frame; // Janela principal
	private JTextField txtNome; // Campo de texto para nome do usuário
	private JTextField txtEmail; // Campo de texto para email
	private JTextField txtCpf; // Campo de texto para CPF
	private JTextField txtTelefone; // Campo de texto para telefone

	String usuario; // String para armazenar o nome do usuário

	/**
	 * Método principal para iniciar a aplicação de perfil.
	 */
	public static void perfilScreen(String usuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Cria uma instância da classe perfil e torna a janela visível
					perfil window = new perfil(usuario);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace(); // Trata possíveis exceções
				}
			}
		});
	}

	/**
	 * Construtor da classe perfil.
	 */
	public perfil(String usuario) {
		this.usuario = usuario; // Recebe o nome do usuário como argumento
		initialize(); // Inicializa os elementos da interface gráfica
	}

	/**
	 * Configuração da interface gráfica.
	 */
	private void initialize() {
		// Configuração da janela principal
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 610); // Define tamanho e posição da janela
		frame.getContentPane().setLayout(null); // Layout absoluto

		// Rótulo para imagem de perfil
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(158, 137, 140, 128); // Define posição e tamanho
		frame.getContentPane().add(lblNewLabel); // Adiciona à janela

		// Rótulo para o título "PERFIL"
		JLabel lblNewLabel_1 = new JLabel("PERFIL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Define fonte e tamanho
		lblNewLabel_1.setBounds(197, 104, 62, 23); // Define posição e tamanho
		frame.getContentPane().add(lblNewLabel_1); // Adiciona à janela

		// Painel para agrupar os campos de texto
		JPanel panel = new JPanel();
		panel.setBounds(77, 275, 312, 241); // Define posição e tamanho
		frame.getContentPane().add(panel); // Adiciona o painel à janela
		panel.setLayout(null); // Layout absoluto

		// Rótulo e campo de texto para Nome
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(22, 33, 45, 24);
		panel.add(lblNewLabel_2);

		txtNome = new JTextField(usuario); // Preenche o campo com o nome do usuário
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtNome.setBounds(103, 33, 186, 24);
		panel.add(txtNome);
		txtNome.setColumns(10);

		// Rótulo e campo de texto para Email
		JLabel lblNewLabel_2_1 = new JLabel("Email:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(22, 72, 45, 24);
		panel.add(lblNewLabel_2_1);

		txtEmail = new JTextField("Email"); // Placeholder para o email
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtEmail.setBounds(103, 72, 186, 24);
		panel.add(txtEmail);

		// Rótulo e campo de texto para CPF
		JLabel lblNewLabel_2_1_1 = new JLabel("CPF:");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_1.setBounds(22, 111, 45, 24);
		panel.add(lblNewLabel_2_1_1);

		txtCpf = new JTextField("CPF"); // Placeholder para o CPF
		txtCpf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCpf.setBounds(103, 111, 186, 24);
		panel.add(txtCpf);

		// Rótulo e campo de texto para Telefone
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Telefone:");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2_1_1_1.setBounds(22, 150, 63, 24);
		panel.add(lblNewLabel_2_1_1_1);

		txtTelefone = new JTextField("Telefone"); // Placeholder para telefone
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtTelefone.setBounds(103, 150, 186, 24);
		panel.add(txtTelefone);

		// Botão "Salvar"
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(89, 196, 101, 30);
		panel.add(btnNewButton);

		// Botão para voltar à tela principal
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(10, 10, 105, 98);
		frame.getContentPane().add(btnNewButton_1);

		// Botão para deslogar
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBounds(357, 10, 119, 109);
		frame.getContentPane().add(btnNewButton_1_1);

		// Evento para voltar à tela principal
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				telaPrincipal voltar = new telaPrincipal(usuario);
				voltar.telaScreen(usuario);
			}
		});

		// Evento para deslogar
		btnNewButton_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Koala deslog = new Koala();
				deslog.main(null);
			}
		});

		// Define ícone para o botão "Deslogar"
		String iconfilePath = this.getClass().getClassLoader().getResource("return_icon.png").getFile();
		btnNewButton_1_1.setIcon(new ImageIcon(iconfilePath));
		btnNewButton_1_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setFocusable(false);

		// Define ícone para o botão "Voltar"
		String iconfilePath1 = this.getClass().getClassLoader().getResource("home_icon.png").getFile();
		btnNewButton_1.setIcon(new ImageIcon(iconfilePath1));
		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusable(false);

		// Adiciona imagem de perfil no centro da janela
		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource("/profile.png"));
		} catch (IOException e) {
			e.printStackTrace(); // Trata erro ao carregar imagem
		}
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		wIcon.setBounds(172, 151, 114, 103);
		frame.getContentPane().add(wIcon);
	}
}