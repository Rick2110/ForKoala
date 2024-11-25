package koalinha;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class suasReservas {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void reservasScreen(String usuario) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    suasReservas window = new suasReservas(usuario);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * @param usuario 
     */
    public suasReservas(String usuario) {
        initialize(usuario);
    }

    /**
     * Initialize the contents of the frame.
     * @param usuario 
     */
    private void initialize(String usuario) {
        frame = new JFrame();
        frame.setBounds(100, 100, 520, 580);
        frame.getContentPane().setBackground(new Color(210, 222, 198, 255)); 
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Suas Reservas");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(151, 116, 199, 46);
        frame.getContentPane().add(lblNewLabel);
        
        // Criar um painel principal para armazenar os painéis de reservas
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);  // Usar um layout nulo para adicionar manualmente os componentes
        
        // Adicionando o JScrollPane para rolagem na lateral esquerda
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBounds(10, 180, 485, 340); // Define a área visível com rolagem
        frame.getContentPane().add(scrollPane);
        
        // Garantir que o JScrollPane tenha rolagem vertical
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.setBounds(22, 10, 86, 94);
        frame.getContentPane().add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("");
        btnNewButton_1.setBounds(387, 10, 81, 69);
        frame.getContentPane().add(btnNewButton_1);
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://macfly.zapto.org:3306/koalinha", "root", "")) {
            String sql = "SELECT * FROM mesas WHERE usuario = ?";
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario);
                
                try (ResultSet rs = stmt.executeQuery()) {
                    int panelYPosition = 10;  // Posição inicial para o painel de reservas
                    boolean foundReservation = false;
                    
                    while (rs.next()) {
                        foundReservation = true;
                        
                        // Cria um painel para cada reserva
                        JPanel panel = new JPanel();
                        panel.setBounds(10, panelYPosition, 460, 170);  // Posição dinâmica no eixo Y
                        panel.setBackground(new Color(181, 143, 115, 255));
                        mainPanel.add(panel);
                        panel.setLayout(null);
                        
                        // Adiciona informações da reserva no painel
                        JLabel lblId = new JLabel("ID Reserva: " + rs.getInt("id"));
                        lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        lblId.setBounds(10, 10, 200, 30);
                        panel.add(lblId);
                        
                        JLabel lblDataReserva = new JLabel("Data: " + rs.getString("data"));
                        lblDataReserva.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        lblDataReserva.setBounds(10, 50, 200, 30);
                        panel.add(lblDataReserva);
                        
                        JLabel lblHoraReserva = new JLabel("Hora: " + rs.getString("horario"));
                        lblHoraReserva.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        lblHoraReserva.setBounds(10, 90, 200, 30);
                        panel.add(lblHoraReserva);
                        
                        JLabel lblMesaNum = new JLabel("Número da Mesa: " + rs.getInt("mesa_num"));
                        lblMesaNum.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        lblMesaNum.setBounds(10, 130, 200, 30);
                        panel.add(lblMesaNum);
                        
                        // Atualiza a posição do próximo painel
                        panelYPosition += 180;  // Distância entre os painéis
                        
                        // Redimensiona o painel para se ajustar ao conteúdo
                        mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), panelYPosition));
                        
                        // Força a revalidação e redesenho do painel
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        
                        // Depois de adicionar o painel, rolar para baixo
                        SwingUtilities.invokeLater(() -> {
                            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                        });
                    }
                    
                    if (!foundReservation) {
                        JLabel lblNewLabel_1 = new JLabel("Você não tem reservas.");
                        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                        lblNewLabel_1.setBounds(145, 230, 250, 46);
                        frame.getContentPane().add(lblNewLabel_1);
                    }
                }
            }
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(frame, "Erro ao conectar com o banco de dados: " + e1.getMessage());
        }
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				telaPrincipal voltar = new telaPrincipal(usuario);
				voltar.telaScreen(usuario);
			}
			
		});
		btnNewButton_1.addActionListener(new ActionListener() {

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
		btnNewButton_1.setIcon(new ImageIcon(iconfilePath1));
		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setFocusable(false);
    }

}
