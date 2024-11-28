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

public class pagamento { // Declara a classe "pagamento".

    private JFrame frame; // Janela principal da aplicação.
    private String usuario; // Armazena o nome do usuário.

    /**
     * Método principal para lançar a tela de pagamento.
     * @param usuario Nome do usuário que está realizando o pagamento.
     */
    public static void telaPagamento(String usuario) {
        EventQueue.invokeLater(new Runnable() { // Garante que a interface gráfica seja inicializada na fila de eventos.
            public void run() {
                try {
                    pagamento window = new pagamento(usuario); // Cria uma instância da classe "pagamento".
                    window.frame.setVisible(true); // Torna a janela visível.
                } catch (Exception e) { // Captura possíveis exceções.
                    e.printStackTrace(); // Exibe a pilha de erros no console.
                }
            }
        });
    }

    /**
     * Construtor da classe "pagamento".
     * @param usuario Nome do usuário que está realizando o pagamento.
     */
    public pagamento(String usuario) {
        this.usuario = usuario; // Inicializa o atributo "usuario" com o valor passado.
        initialize(); // Chama o método que inicializa os componentes da interface gráfica.
    }

    /**
     * Método que inicializa os componentes da interface gráfica.
     */
    private void initialize() {
        // Configura a janela principal.
        frame = new JFrame(); // Cria a janela principal.
        frame.setBounds(100, 100, 500, 556); // Define o tamanho e a posição.
        frame.getContentPane().setLayout(null); // Define o layout como nulo para posicionamento manual.

        // Rótulo para exibir "Valor:".
        JLabel lblNewLabel = new JLabel("Valor:"); // Cria um rótulo com o texto "Valor:".
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27)); // Define a fonte e o tamanho do texto.
        lblNewLabel.setBounds(132, 42, 226, 36); // Define a posição e o tamanho do rótulo.
        frame.getContentPane().add(lblNewLabel); // Adiciona o rótulo à janela.

        // Variável para armazenar a imagem.
        BufferedImage wPic = null;

        // Tentativa de carregar a imagem "image.png" do recurso do projeto.
        try {
            wPic = ImageIO.read(this.getClass().getResource("/image.png")); // Lê a imagem "image.png".
        } catch (IOException e) { // Captura erros de entrada e saída.
            // Exibe uma mensagem de erro caso a imagem não seja encontrada ou carregada.
            JOptionPane.showMessageDialog(frame, "Erro ao carregar a imagem: " + e.getMessage());
        }

        // Rótulo para exibir a imagem carregada.
        JLabel wIcon = new JLabel(new ImageIcon(wPic)); // Cria um rótulo com a imagem carregada.
        wIcon.setBounds(58, 107, 366, 309); // Define a posição e o tamanho do rótulo.
        frame.getContentPane().add(wIcon); // Adiciona o rótulo à janela.

        // Botão para confirmar o pagamento.
        JButton btnNewButton_1 = new JButton("Pagamento Realizado"); // Cria um botão com o texto "Pagamento Realizado".
        btnNewButton_1.setBounds(143, 449, 180, 36); // Define a posição e o tamanho do botão.
        frame.getContentPane().add(btnNewButton_1); // Adiciona o botão à janela.

        // Adiciona um ouvinte para tratar o clique no botão.
        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela atual.
                frame.dispose();
                
                // Abre a tela de reservas do usuário.
                suasReservas reservas = new suasReservas(usuario); // Cria uma instância da classe "suasReservas".
                reservas.reservasScreen(usuario); // Chama o método para exibir a tela de reservas.
            }
        });
    }
}