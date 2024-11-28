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

public class cardapio { // Declaração da classe "cardapio".

	private JFrame frame; // Janela principal da aplicação.
	String usuario; // Variável para armazenar o nome do usuário.

	/**
	 * Método principal para iniciar a aplicação.
	 * @param usuario Nome do usuário a ser exibido.
	 */
	public static void cardapioScreen(String usuario) {
		EventQueue.invokeLater(new Runnable() { // Garante que a interface gráfica seja inicializada na fila de eventos.
			public void run() {
				try {
					cardapio window = new cardapio(usuario); // Cria uma nova instância de "cardapio".
					window.frame.setVisible(true); // Torna a janela visível.
				} catch (Exception e) { // Captura qualquer exceção.
					e.printStackTrace(); // Exibe o rastreamento do erro no console.
				}
			}
		});
	}

	/**
	 * Construtor da classe.
	 * @param usuario Nome do usuário passado para a classe.
	 */
	public cardapio(String usuario) {
		this.usuario = usuario; // Armazena o nome do usuário.
		initialize(); // Chama o método para inicializar os componentes da interface.
	}

	/**
	 * Método para inicializar os componentes da interface.
	 */
	private void initialize() {
		// Configuração da janela principal.
		frame = new JFrame(); // Cria a janela.
		frame.getContentPane().setBackground(new Color(210, 222, 198)); // Define a cor de fundo.
		frame.setBounds(100, 100, 500, 552); // Define o tamanho e posição da janela.
		frame.getContentPane().setLayout(null); // Define o layout como nulo.

		// Criação e configuração de um JLabel para o título.
		JLabel lblNewLabel = new JLabel("Cardapio"); // Cria o rótulo.
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30)); // Define a fonte e o tamanho.
		lblNewLabel.setBounds(170, 92, 119, 29); // Define a posição e o tamanho.
		frame.getContentPane().add(lblNewLabel); // Adiciona o rótulo à janela.

		// Rótulo com uma mensagem adicional.
		JLabel lblNewLabel_1 = new JLabel("Acesse o cardapio escaneando"); // Mensagem de instrução.
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15)); // Define a fonte.
		lblNewLabel_1.setBounds(138, 131, 205, 29); // Posição e tamanho.
		frame.getContentPane().add(lblNewLabel_1); // Adiciona o rótulo.

		// Rótulo com a continuação da mensagem.
		JLabel lblNewLabel_1_1 = new JLabel("o QR code abaixo:"); // Continuação da mensagem.
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15)); // Define a fonte.
		lblNewLabel_1_1.setBounds(177, 155, 128, 29); // Posição e tamanho.
		frame.getContentPane().add(lblNewLabel_1_1); // Adiciona o rótulo.

		// Carregamento de uma imagem do QR code.
		BufferedImage wPic = null; // Declaração da variável para a imagem.
		try {
			wPic = ImageIO.read(this.getClass().getResource("/image.png")); // Carrega a imagem do recurso.
		} catch (IOException e) { // Captura erros de entrada/saída.
			e.printStackTrace(); // Exibe o erro no console.
		}
		JLabel wIcon = new JLabel(new ImageIcon(wPic)); // Cria um rótulo com a imagem carregada.
		frame.getContentPane().add(wIcon); // Adiciona o rótulo à janela.
		wIcon.setBounds(92, 194, 300, 311); // Define a posição e o tamanho.

		// Criação do botão "Voltar".
		JButton btnNewButton = new JButton(""); // Cria o botão vazio.
		btnNewButton.setBounds(10, 10, 89, 97); // Define a posição e o tamanho.
		frame.getContentPane().add(btnNewButton); // Adiciona o botão à janela.

		// Criação do botão "Home".
		JButton btnNewButton_2 = new JButton(""); // Cria outro botão vazio.
		btnNewButton_2.setBounds(387, 10, 89, 71); // Define a posição e o tamanho.
		frame.getContentPane().add(btnNewButton_2); // Adiciona o botão.

		// Evento de clique no botão "Voltar".
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Método executado ao clicar.
				frame.dispose(); // Fecha a janela atual.
				telaPrincipal voltar = new telaPrincipal(usuario); // Cria uma nova tela principal.
				voltar.telaScreen(usuario); // Exibe a tela principal.
			}
		});

		// Evento de clique no botão "Home".
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // Método executado ao clicar.
				frame.dispose(); // Fecha a janela atual.
				Koala deslog = new Koala(); // Cria uma nova instância da classe Koala.
				deslog.main(null); // Chama o método principal.
			}
		});

		// Configuração do ícone do botão "Voltar".
		String iconfilePath = this.getClass().getClassLoader().getResource("return_icon.png").getFile(); // Localiza o arquivo de ícone.
		btnNewButton.setIcon(new ImageIcon(iconfilePath)); // Define o ícone no botão.
		btnNewButton.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda.
		btnNewButton.setContentAreaFilled(false); // Remove o fundo.
		btnNewButton.setFocusable(false); // Remove o foco visual.

		// Configuração do ícone do botão "Home".
		String iconfilePath1 = this.getClass().getClassLoader().getResource("home_icon.png").getFile(); // Localiza o ícone.
		btnNewButton_2.setIcon(new ImageIcon(iconfilePath1)); // Define o ícone no botão.
		btnNewButton_2.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda.
		btnNewButton_2.setContentAreaFilled(false); // Remove o fundo.
		btnNewButton_2.setFocusable(false); // Remove o foco visual.
	}
}