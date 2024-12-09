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

public class suasReservas { // Declara a classe "suasReservas".

    private JFrame frame; // Janela principal da aplicação.

    /**
     * Método para iniciar a aplicação.
     * @param usuario Nome do usuário para exibir as reservas.
     */
    public static void reservasScreen(String usuario) {
        EventQueue.invokeLater(new Runnable() { // Garante que a interface gráfica seja inicializada na fila de eventos.
            public void run() {
                try {
                    suasReservas window = new suasReservas(usuario); // Cria uma instância de "suasReservas".
                    window.frame.setVisible(true); // Torna a janela visível.
                } catch (Exception e) { // Captura exceções.
                    e.printStackTrace(); // Exibe a pilha de erro no console.
                }
            }
        });
    }

    /**
     * Construtor da classe.
     * @param usuario Nome do usuário para exibir reservas associadas.
     */
    public suasReservas(String usuario) {
        initialize(usuario); // Inicializa os componentes da interface.
    }

    /**
     * Inicializa os componentes da interface.
     * @param usuario Nome do usuário para carregar reservas.
     */
    private void initialize(String usuario) {
        // Configuração da janela principal.
        frame = new JFrame(); // Cria a janela principal.
        frame.setBounds(100, 100, 520, 580); // Define o tamanho e a posição.
        frame.getContentPane().setBackground(new Color(210, 222, 198, 255)); // Define a cor de fundo.
        frame.getContentPane().setLayout(null); // Define o layout como nulo.

        // Rótulo principal.
        JLabel lblNewLabel = new JLabel("Suas Reservas"); // Rótulo para o título.
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30)); // Configura a fonte.
        lblNewLabel.setBounds(151, 116, 199, 46); // Define a posição e o tamanho.
        frame.getContentPane().add(lblNewLabel); // Adiciona o rótulo à janela.

        // Painel principal para armazenar os painéis de reservas.
        JPanel mainPanel = new JPanel(); // Cria o painel principal.
        mainPanel.setLayout(null); // Layout nulo para posicionar manualmente os componentes.

        // Componente de rolagem que envolve o painel principal.
        JScrollPane scrollPane = new JScrollPane(mainPanel); // Cria um JScrollPane para rolagem.
        scrollPane.setBounds(10, 180, 485, 340); // Define o tamanho e a posição.
        frame.getContentPane().add(scrollPane); // Adiciona o JScrollPane à janela.

        // Garante a presença da barra de rolagem vertical.
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Botão para voltar à tela principal.
        JButton btnNewButton = new JButton(""); // Cria um botão vazio.
        btnNewButton.setBounds(22, 10, 86, 94); // Define a posição e o tamanho.
        frame.getContentPane().add(btnNewButton); // Adiciona o botão.

        // Botão para retornar à tela inicial.
        JButton btnNewButton_1 = new JButton(""); // Cria outro botão vazio.
        btnNewButton_1.setBounds(387, 10, 81, 69); // Define a posição e o tamanho.
        frame.getContentPane().add(btnNewButton_1); // Adiciona o botão.

        // Conexão com o banco de dados para buscar reservas.
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://macfly.zapto.org:3306/sql10750695", "sql10750695", "FdsIjDECHi")) {
            String sql = "SELECT * FROM mesas WHERE usuario = ?"; // Consulta SQL para buscar reservas.

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario); // Substitui o parâmetro na consulta.
                
                try (ResultSet rs = stmt.executeQuery()) { // Executa a consulta e obtém os resultados.
                    int panelYPosition = 10; // Posição inicial para os painéis de reservas.
                    boolean foundReservation = false; // Flag para verificar se há reservas.

                    // Itera pelos resultados da consulta.
                    while (rs.next()) {
                        foundReservation = true; // Define que há reservas.

                        // Cria um painel para cada reserva.
                        JPanel panel = new JPanel();
                        panel.setBounds(10, panelYPosition, 460, 170); // Define a posição e o tamanho.
                        panel.setBackground(new Color(181, 143, 115, 255)); // Cor de fundo.
                        mainPanel.add(panel); // Adiciona o painel ao painel principal.
                        panel.setLayout(null); // Define layout nulo.

                        // Adiciona informações ao painel de reserva.
                        JLabel lblId = new JLabel("ID Reserva: " + rs.getInt("id")); // Exibe o ID da reserva.
                        lblId.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Define a fonte.
                        lblId.setBounds(10, 10, 200, 30); // Posição e tamanho.
                        panel.add(lblId); // Adiciona o rótulo.

                        JLabel lblDataReserva = new JLabel("Data: " + rs.getString("data")); // Exibe a data.
                        lblDataReserva.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        lblDataReserva.setBounds(10, 50, 200, 30);
                        panel.add(lblDataReserva);

                        JLabel lblHoraReserva = new JLabel("Hora: " + rs.getString("horario")); // Exibe o horário.
                        lblHoraReserva.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        lblHoraReserva.setBounds(10, 90, 200, 30);
                        panel.add(lblHoraReserva);

                        JLabel lblMesaNum = new JLabel("Número da Mesa: " + rs.getInt("mesa_num")); // Exibe o número da mesa.
                        lblMesaNum.setFont(new Font("Tahoma", Font.PLAIN, 16));
                        lblMesaNum.setBounds(10, 130, 200, 30);
                        panel.add(lblMesaNum);

                        // Ajusta a posição para o próximo painel.
                        panelYPosition += 180;

                        // Redimensiona o painel principal com base no conteúdo.
                        mainPanel.setPreferredSize(new Dimension(mainPanel.getWidth(), panelYPosition));
                        mainPanel.revalidate(); // Revalida o painel.
                        mainPanel.repaint(); // Requer redesenho.

                        // Ajusta a barra de rolagem para exibir o novo painel.
                        SwingUtilities.invokeLater(() -> {
                            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                        });
                    }

                    // Exibe mensagem caso não haja reservas.
                    if (!foundReservation) {
                        JLabel lblNewLabel_1 = new JLabel("Você não tem reservas."); // Mensagem de ausência de reservas.
                        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                        lblNewLabel_1.setBounds(145, 230, 250, 46);
                        frame.getContentPane().add(lblNewLabel_1);
                    }
                }
            }
        } catch (SQLException e1) { // Captura erros de SQL.
            JOptionPane.showMessageDialog(frame, "Erro ao conectar com o banco de dados: " + e1.getMessage());
        }

        // Configura os eventos do botão "Voltar".
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha a janela atual.
                telaPrincipal voltar = new telaPrincipal(usuario); // Cria a tela principal.
                voltar.telaScreen(usuario); // Exibe a tela principal.
            }
        });

        // Configura os eventos do botão "Home".
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha a janela atual.
                Koala deslog = new Koala(); // Cria uma instância de "Koala".
                deslog.main(null); // Volta ao menu principal.
            }
        });

        // Configura o ícone do botão "Voltar".
        String iconfilePath = this.getClass().getClassLoader().getResource("return_icon.png").getFile(); // Caminho do ícone.
        btnNewButton.setIcon(new ImageIcon(iconfilePath)); // Define o ícone.
        btnNewButton.setBorder(BorderFactory.createEmptyBorder()); // Remove a borda.
        btnNewButton.setContentAreaFilled(false); // Remove o fundo.
        btnNewButton.setFocusable(false); // Remove o foco visual.

        // Configura o ícone do botão "Home".
        String iconfilePath1 = this.getClass().getClassLoader().getResource("home_icon.png").getFile();
        btnNewButton_1.setIcon(new ImageIcon(iconfilePath1));
        btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
        btnNewButton_1.setContentAreaFilled(false);
        btnNewButton_1.setFocusable(false);
    }
}
