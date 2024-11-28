package koalinha;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class marcarHorario {
    String dataDigitada = null;
    String horaDigitada;
    String usuario;
    String mesanum = null;
    boolean numeroValido = false;
    Connection conn;
    boolean reservaExistente = false;

    private JFrame frmKoalaApp;

    public static void horarioScreen(String usuario) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    marcarHorario window = new marcarHorario(usuario, null); // Pode passar null para horaDigitada caso não tenha
                    window.frmKoalaApp.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Modifique o construtor para aceitar a horaDigitada
    public marcarHorario(String usuario, String horaDigitada) {
        this.usuario = usuario;
        this.horaDigitada = horaDigitada; // Agora recebe o valor de horaDigitada
        initialize();
    }

    private void initialize() {
        frmKoalaApp = new JFrame();
        frmKoalaApp.setTitle("ForKoala App");
        frmKoalaApp.getContentPane().setBackground(new Color(210, 222, 198));
        frmKoalaApp.setBounds(100, 100, 600, 610);
        frmKoalaApp.setLocationRelativeTo(null);
        frmKoalaApp.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(181, 143, 115));
        panel.setBounds(151, 172, 261, 83);
        frmKoalaApp.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Data");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(106, 10, 44, 19);
        panel.add(lblNewLabel);

        JButton btnNewButton_3 = new JButton("Selecionar Data");
        btnNewButton_3.setBackground(new Color(255, 255, 255));
        btnNewButton_3.setBounds(65, 39, 129, 34);
        panel.add(btnNewButton_3);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(181, 143, 115));
        panel_1.setBounds(151, 276, 261, 83);
        frmKoalaApp.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        //botões e texto
        JLabel lblNewLabel_1 = new JLabel("Horario");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(91, 10, 58, 16);
        panel_1.add(lblNewLabel_1);

        JButton btnNewButton_3_1 = new JButton("Selecionar Horario");
        btnNewButton_3_1.setBackground(new Color(255, 255, 255));
        btnNewButton_3_1.setBounds(54, 39, 153, 34);
        panel_1.add(btnNewButton_3_1);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(181, 143, 115));
        panel_1_1.setBounds(151, 380, 261, 89);
        frmKoalaApp.getContentPane().add(panel_1_1);
        panel_1_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Mapa das Mesas");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(63, 10, 135, 22);
        panel_1_1.add(lblNewLabel_2);

        JButton btnNewButton_3_1_1_1 = new JButton("Mesas");
        btnNewButton_3_1_1_1.setBackground(new Color(255, 255, 255));
        btnNewButton_3_1_1_1.setBounds(63, 42, 135, 34);
        panel_1_1.add(btnNewButton_3_1_1_1);

        JLabel lblNewLabel_3 = new JLabel("Marcar Reserva");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_3.setBounds(177, 67, 209, 53);
        frmKoalaApp.getContentPane().add(lblNewLabel_3);

        JButton btnNewButton = new JButton("");
        btnNewButton.setBackground(new Color(210, 222, 198));
        btnNewButton.setBounds(21, 20, 97, 97);
        frmKoalaApp.getContentPane().add(btnNewButton);

        JButton btnNewButton_2 = new JButton("");
        btnNewButton_2.setBackground(new Color(210, 222, 198));
        btnNewButton_2.setBounds(477, 20, 81, 83);
        frmKoalaApp.getContentPane().add(btnNewButton_2);
        
                JButton btnNewButton_3_1_1 = new JButton("Reservar uma Mesa");
                btnNewButton_3_1_1.setBackground(new Color(255, 255, 255));
                btnNewButton_3_1_1.setBounds(195, 508, 172, 34);
                frmKoalaApp.getContentPane().add(btnNewButton_3_1_1);
                
                btnNewButton_3_1_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	String inputMesa = JOptionPane.showInputDialog(frmKoalaApp, "Digite o número da mesa (1-9):");

                        try {
                            if (inputMesa != null && !inputMesa.trim().isEmpty()) {
                                int numeroMesa = Integer.parseInt(inputMesa);

                                if (numeroMesa >= 1 && numeroMesa <= 9) {
                                    mesanum = inputMesa;
                                    JOptionPane.showMessageDialog(frmKoalaApp, "Você escolheu a mesa: " + mesanum);
                                } else {
                                    JOptionPane.showMessageDialog(frmKoalaApp, "Número da mesa deve ser entre 1 e 9.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(frmKoalaApp, "Você não digitou nenhum número de mesa.");
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frmKoalaApp, "Por favor, digite um número válido de mesa.");
                        }
                        // Verifica se a data, o horário e a mesa foram selecionados
                        if (dataDigitada == null || horaDigitada == null || mesanum == null) {
                            JOptionPane.showMessageDialog(frmKoalaApp, "Por favor, selecione a data, o horário e a mesa antes de reservar.");
                            return; // Não continua o processo de reserva se algum campo for nulo
                        }

                        // Verifica se já existe uma reserva para essa data, mesa e hora
                        boolean reservaExistente = verificarReservaExistente(dataDigitada, mesanum, horaDigitada);

                        if (reservaExistente) {
                            JOptionPane.showMessageDialog(frmKoalaApp, "A mesa " + mesanum + " já está reservada para o horário " + horaDigitada + " na data " + dataDigitada);
                            return; // Impede que a reserva seja feita se já existir
                        }
                        int resposta = JOptionPane.showConfirmDialog(
                            frmKoalaApp, 
                            "Mesa selecionada, ir para pagamento da mesa agora?", 
                            "Confirmação", 
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                        );

                        if (resposta == JOptionPane.YES_OPTION) {
                            // Pergunta se deseja fazer o pagamento agora
                                pagamento pag = new pagamento(usuario);
                                pag.telaPagamento(usuario);
                                frmKoalaApp.dispose();
                                // Caso a opção seja "Não", realiza a inserção no banco com pagamento pendente
                                String sql = "INSERT INTO mesas (usuario, mesa_num, data, horario, pagamento) VALUES (?, ?, ?, ?, ?)";
                                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/koalinha", "root", "");
                                     PreparedStatement stmt = conn.prepareStatement(sql)) {

                                    stmt.setString(1, usuario);
                                    stmt.setString(2, mesanum);
                                    stmt.setString(3, dataDigitada);
                                    stmt.setString(4, horaDigitada); // Agora o valor de 'horaDigitada' não é nulo
                                    stmt.setString(5, "pendente");
                                    

                                    int linhasAfetadas = stmt.executeUpdate();

                                    if (linhasAfetadas > 0) {
                                        JOptionPane.showMessageDialog(frmKoalaApp, "Mesa reservada com sucesso!");
                                    } else {
                                        JOptionPane.showMessageDialog(frmKoalaApp, "Falha ao reservar a mesa.");
                                    }
                                } catch (SQLException ex) {
                                    // Caso ocorra erro no banco de dados
                                    JOptionPane.showMessageDialog(frmKoalaApp, "Erro ao conectar ao banco de dados: " + ex.getMessage());
                                }
                            }
                        }
                    });


        
        //função dos botões
                btnNewButton_3.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String input = JOptionPane.showInputDialog(frmKoalaApp, "Digite uma data (dd/MM/yyyy):");

                        if (input == null || input.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(frmKoalaApp, "Você não digitou nenhuma data.");
                            return;
                        }

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        dateFormat.setLenient(false);

                        try {
                            Date date = dateFormat.parse(input);

                            // Cria a data de 29/11/2024 para comparação
                            Date minDate = dateFormat.parse("29/11/2024");

                            // Verifica se a data digitada é anterior a 29/11/2024
                            if (date.before(minDate)) {
                                JOptionPane.showMessageDialog(frmKoalaApp, "A data digitada é inválida. A reserva não pode ser feita para antes de 29/11/2024.");
                                return;
                            }

                            JOptionPane.showMessageDialog(frmKoalaApp, "Você escolheu a data: " + dateFormat.format(date));
                            dataDigitada = input;
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(frmKoalaApp, "Por favor, digite uma data válida no formato dd/MM/yyyy.");
                        }
                    }
                });

                		//imagem do icone de retornar
                        String iconfilePath = this.getClass().getClassLoader().getResource("return_icon.png").getFile();
                		btnNewButton.setIcon(new ImageIcon(iconfilePath));
                		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
                		btnNewButton.setContentAreaFilled(false);
                		btnNewButton.setFocusable(false);
                		//imagem icone de home
                		String iconfilePath1 = this.getClass().getClassLoader().getResource("home_icon.png").getFile();
                		btnNewButton_2.setIcon(new ImageIcon(iconfilePath1));
                		btnNewButton_2.setBorder(BorderFactory.createEmptyBorder());
                		btnNewButton_2.setContentAreaFilled(false);
                		btnNewButton_2.setFocusable(false);

        //retornar pra ultima pagina
        btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmKoalaApp.dispose();
				telaPrincipal voltar = new telaPrincipal(usuario);
				voltar.telaScreen(usuario);
				
			}
			
		});
        //função do botão de retornar ao login
        btnNewButton_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmKoalaApp.dispose();
				Koala deslog = new Koala();
				deslog.main(null);
				
			}
			
		});
        //ação do botão de escolher um horario 
        btnNewButton_3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] horarios = {"15:00", "18:00", "21:00"};

                String horarioSelecionado = (String) JOptionPane.showInputDialog(
                    frmKoalaApp, 
                    "Selecione um horário:", 
                    "Escolha o Horário", 
                    JOptionPane.PLAIN_MESSAGE, 
                    null, 
                    horarios, 
                    horarios[0]
                );

                if (horarioSelecionado != null) {
                    horaDigitada = horarioSelecionado;
                    JOptionPane.showMessageDialog(frmKoalaApp, "Você escolheu o horário: " + horaDigitada);
                } else {
                    JOptionPane.showMessageDialog(frmKoalaApp, "Nenhum horário foi selecionado.");
                }
            }
        });

        btnNewButton_3_1_1_1.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mesas mesa = new mesas();
				mesa.mesasScreen();
			}
        	
        });
    }

    public boolean verificarReservaExistente(String data, String mesa, String hora) {
        String sql = "SELECT * FROM mesas WHERE data = ? AND mesa_num = ? AND horario = ?"; // Corrigido para incluir a hora na verificação

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://macfly.zapto.org:3306/koalinha", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);
            stmt.setString(2, mesa);
            stmt.setString(3, hora); // Corrigido: agora estamos usando o parâmetro correto para hora

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true; // Se existir uma reserva, retorna true
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Não existe reserva para essa data, mesa e hora
    }


    public static boolean valido(String horario) {
        String permitido = "^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$";
        return horario != null && horario.matches(permitido);
    }
}
