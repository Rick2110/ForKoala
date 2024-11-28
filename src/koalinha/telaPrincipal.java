package koalinha;

import javax.swing.*;

// Importações de bibliotecas necessárias
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Declaração da classe principal telaPrincipal
public class telaPrincipal {
	String horaDigitada = null;
	// Declaração dos componentes da interface
	private JFrame frmKoalaApp;

	// Método para abrir a janela principal do aplicativo
	public static void telaScreen(String usuario) {
		EventQueue.invokeLater(new Runnable() {
			// Executa o código para iniciar a tela em uma fila de eventos
			public void run() {
				try {
					telaPrincipal window = new telaPrincipal(usuario); // Criação do objeto telaPrincipal
					window.frmKoalaApp.setVisible(true); // Exibição da janela principal
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor da classe - cria a aplicação
	public telaPrincipal(String usuario) {
		initialize(usuario); // Chama o método de inicialização
	}

	// Inicializa os componentes da janela
	private void initialize(String usuario) {
		// Configuração da janela principal
		frmKoalaApp = new JFrame();
		frmKoalaApp.getContentPane().setBackground(new Color(210,222,198,255)); // Cor de fundo da janela
		frmKoalaApp.setTitle("ForKoala"); // Título da janela
		frmKoalaApp.setBounds(100, 100, 576, 543); // Define posição e tamanho
		frmKoalaApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportamento ao fechar
		frmKoalaApp.setLocationRelativeTo(null); // Centraliza a janela
		frmKoalaApp.getContentPane().setLayout(null);

		// Texto de boas-vindas
		JLabel lblNewLabel = new JLabel("Bem-vindo " + usuario + "!");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30)); // Fonte do texto
		lblNewLabel.setBounds(141, 85, 399, 75); // Localização do texto
		frmKoalaApp.getContentPane().add(lblNewLabel);

		// Botão "Marcar horário"
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(217, 217, 217)); // Cor de fundo do botão
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Fonte do texto do botão
		btnNewButton.setBounds(150, 197, 110, 103); // Localização e tamanho
		frmKoalaApp.getContentPane().add(btnNewButton);

		// Botão "Suas Reservas"
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBackground(new Color(217, 217, 217));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(289, 197, 110, 103);
		frmKoalaApp.getContentPane().add(btnNewButton_1);

		// Botão "Perfil"
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBackground(new Color(217, 217, 217));
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1.setBounds(150, 310, 110, 100);
		frmKoalaApp.getContentPane().add(btnNewButton_1_1);

		// Botão "cardapio"
		JButton btnNewButton_1_1_1 = new JButton("");
		btnNewButton_1_1_1.setBackground(new Color(217, 217, 217));
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1_1_1.setBounds(289, 310, 110, 100);
		frmKoalaApp.getContentPane().add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEADING);
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(20, 10, 90, 105);
		frmKoalaApp.getContentPane().add(btnNewButton_2);

		// Ação do botão "Marcar horário"
		marcarHorario hrs = new marcarHorario(usuario,horaDigitada); // Instância da classe marcarHorario
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmKoalaApp.dispose();
				hrs.horarioScreen(usuario); // Chama o método para marcar horário
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmKoalaApp.dispose();
				Koala deslog = new Koala();
				deslog.main(null);
				
			}
			
		});
		

		// Ação do botão "cardapio"
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmKoalaApp.dispose();
				cardapio card = new cardapio(usuario);
				card.cardapioScreen(usuario);
			}
		});
		
		//ação de ir para suas reservas
		btnNewButton_1.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmKoalaApp.dispose();
				suasReservas reservas = new suasReservas(usuario);
				reservas.reservasScreen(usuario);
			}
			
		});
		
		//carrega imagem icone de retornar
		String iconfilePath = this.getClass().getClassLoader().getResource("return_icon.png").getFile();
		btnNewButton_2.setIcon(new ImageIcon(iconfilePath));
		btnNewButton_2.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setFocusable(false);
		
		//carrega imagem de ir para reserva
		String iconfilePath1 = this.getClass().getClassLoader().getResource("reserva.png").getFile();
		btnNewButton.setIcon(new ImageIcon(iconfilePath1));
		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusable(false);
		
		//carrega imagem de ir para suas reservas
		String iconfilePath2 = this.getClass().getClassLoader().getResource("suas_reservas.png").getFile();
		btnNewButton_1.setIcon(new ImageIcon(iconfilePath2));
		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusable(false);
		
		//carrega imagem de ir para perfil
        String iconfilePath3 = this.getClass().getClassLoader().getResource("perfil.png").getFile();
        btnNewButton_1_1.setIcon(new ImageIcon(iconfilePath3)); // Define o ícone para o botão "Perfil"
        btnNewButton_1_1.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda do botão
        btnNewButton_1_1.setContentAreaFilled(false); // Remove o preenchimento do botão
        btnNewButton_1_1.setFocusable(false); // Remove o foco do botão
        
        //carrega imagem de ir para o cardapio
        String iconfilePath4 = this.getClass().getClassLoader().getResource("cardapio.png").getFile();
        btnNewButton_1_1_1.setIcon(new ImageIcon(iconfilePath4)); // Define o ícone para o botão "Cardápio"
        btnNewButton_1_1_1.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda do botão
        btnNewButton_1_1_1.setContentAreaFilled(false); // Remove o preenchimento do botão
        btnNewButton_1_1_1.setFocusable(false); // Remove o foco do botão

        // Ação do botão "Perfil", que abre a tela de perfil do usuário
        btnNewButton_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmKoalaApp.dispose(); // Fecha a janela principal
                perfil perf = new perfil(usuario); // Cria uma instância da tela de perfil
                perf.perfilScreen(usuario); // Abre a tela de perfil do usuário
            }
        });
    }
}
