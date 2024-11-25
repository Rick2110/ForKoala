package koalinha;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class marcarHorario {
	//declarar pra não bugar
    String dataDigitada = null;
    String horaDigitada = null;
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
                    marcarHorario window = new marcarHorario(usuario);
                    window.frmKoalaApp.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public marcarHorario(String usuario) {
    	//puxar o nome do usuario
        this.usuario = usuario;
        initialize();
    }

    private void initialize() {
    	//janela
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

        JButton btnNewButton_3_1 = new JButton("Selecionar Reserva");
        btnNewButton_3_1.setBackground(new Color(255, 255, 255));
        btnNewButton_3_1.setBounds(54, 39, 153, 34);
        panel_1.add(btnNewButton_3_1);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBackground(new Color(181, 143, 115));
        panel_1_1.setBounds(151, 380, 261, 89);
        frmKoalaApp.getContentPane().add(panel_1_1);
        panel_1_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("Mesa");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(103, 10, 42, 22);
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
        
                JButton btnNewButton_3_1_1 = new JButton("Reservar");
                btnNewButton_3_1_1.setBackground(new Color(255, 255, 255));
                btnNewButton_3_1_1.setBounds(214, 508, 135, 34);
                frmKoalaApp.getContentPane().add(btnNewButton_3_1_1);
                
                        btnNewButton_3_1_1.addActionListener(new ActionListener() {
                            @Override
                            //alguém me mata
                            public void actionPerformed(ActionEvent e) {
                                int numero = -1;
                                
                                while (numero < 0 || numero > 9) {
                                    String input = JOptionPane.showInputDialog(frmKoalaApp, "Escolha uma mesa:");
                
                                    if (input == null || input.trim().isEmpty()) {
                                        JOptionPane.showMessageDialog(frmKoalaApp, "Mesa inválida. Tente novamente.");
                                        continue;
                                    }
                
                                    try {
                                        numero = Integer.parseInt(input);
                
                                        if (numero < 0 || numero > 9) {
                                            JOptionPane.showMessageDialog(frmKoalaApp, "Número inválido. Tente novamente.");
                                        } else {
                                            mesanum = String.valueOf(numero);
                                        }
                                    } catch (NumberFormatException e1) {
                                        JOptionPane.showMessageDialog(frmKoalaApp, "Entrada inválida. Por favor, insira um número inteiro.");
                                    }
                                }
                
                                boolean reservaExistente = verificarReservaExistente(dataDigitada, mesanum);
                                
                                if (reservaExistente) {
                                    JOptionPane.showMessageDialog(frmKoalaApp, "A mesa " + mesanum +" ja foi reservada na data " + dataDigitada);
                                    if (usuario == null || mesanum == null || dataDigitada == null || horaDigitada == null) {
                            			JOptionPane.showMessageDialog(frmKoalaApp, "Preencha as configurações da reserva");
                            		} else {
                            				// Caso o usuário não confirme a continuação, não faz nada
                            				pagamento pag = new pagamento(usuario);
                            			}
                                } else {
                                	int resposta = JOptionPane.showConfirmDialog(
                                			frmKoalaApp, 
                                			"Você tem certeza que deseja continuar?", 
                                			"Confirmação", 
                                			JOptionPane.YES_NO_OPTION,
                                			JOptionPane.QUESTION_MESSAGE
                                			);
                                	if (resposta == JOptionPane.YES_OPTION) {
                                		// Pergunta se deseja fazer o pagamento agora
                                		int payment = JOptionPane.showConfirmDialog(
                                				frmKoalaApp, 
                                				"Você deseja fazer o pagamento agora?", 
                                				"Confirmação", 
                                				JOptionPane.YES_NO_OPTION,
                                				JOptionPane.QUESTION_MESSAGE
                                				);
                                		if (payment == JOptionPane.YES_OPTION) {
                                			// Caso a opção seja "Sim", chama a tela de pagamento
                                			pagamento pag = new pagamento(usuario);
                                			pag.telaPagamento(usuario);
                                		} else {
                                			// Caso a opção seja "Não", realiza a inserção no banco com pagamento pendente
                                			String sql = "INSERT INTO mesas (usuario, mesa_num, data, horario, pagamento) VALUES (?, ?, ?, ?, ?)";
                                			
                                			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/koalinha", "root", "");
                                					PreparedStatement stmt = conn.prepareStatement(sql)) {
                                				
                                				stmt.setString(1, usuario);
                                				stmt.setString(2, mesanum);
                                				stmt.setString(3, dataDigitada);
                                				stmt.setString(4, horaDigitada);
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
                                	//mudar onde essa função executa
                                    String sql = "INSERT INTO mesas (usuario, mesa_num, data, horario) VALUES (?, ?, ?, ?)";
                                	}
                                }
                            }
                        });
        
        //função dos botões
                        btnNewButton_3.addActionListener(new ActionListener() {
                            @Override
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

                                    // Define a data mínima (29/11/2024) como um objeto Date
                                    Date minimumDate = dateFormat.parse("29/11/2024");

                                    // Verifica se a data digitada
                                    if (date.before(minimumDate)) {
                                        JOptionPane.showMessageDialog(frmKoalaApp, "A data digitada é inválida..");
                                        return;
                                    }

                                    JOptionPane.showMessageDialog(frmKoalaApp, "Você escolheu a data: " + dateFormat.format(date));
                                    dataDigitada = input;
                                } catch (ParseException ex) {
                                    JOptionPane.showMessageDialog(frmKoalaApp, "Por favor, digite uma data válida no formato dd/MM/yyyy.");
                                }
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


        btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmKoalaApp.dispose();
				telaPrincipal voltar = new telaPrincipal(usuario);
				voltar.telaScreen(usuario);
				
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

        btnNewButton_3_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input1 = JOptionPane.showInputDialog(frmKoalaApp, "Digite um Horario (00:00):");

                if (input1 == null || input1.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frmKoalaApp, "Você não digitou nenhum horário.");
                    return;
                }

                if (valido(input1)) {
                    // Converte a string para objeto
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                    try {
                        Date time = timeFormat.parse(input1);

                        Date startTime = timeFormat.parse("22:00");
                        Date endTime = timeFormat.parse("07:00");

                        if ((time.after(startTime) && time.before(new Date())) || (time.before(endTime))) {
                            JOptionPane.showMessageDialog(frmKoalaApp, "Horário inválido! Não é permitido selecionar horários entre 23:00 e 07:00.");
                            return;
                        }

                        JOptionPane.showMessageDialog(frmKoalaApp, "Horário " + input1 + " selecionado");
                        horaDigitada = input1;
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(frmKoalaApp, "Erro ao comparar o horário. Verifique o formato.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frmKoalaApp, "Formato de horário inválido (00:00)");
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

    public boolean verificarReservaExistente(String data, String mesa) {
        String sql = "SELECT * FROM mesas WHERE data = ? AND mesa_num = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://macfly.zapto.org:3306/koalinha", "root", "");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, data);
            stmt.setString(2, mesa);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean valido(String horario) {
        String permitido = "^([01]?[0-9]|2[0-3]):([0-5]?[0-9])$";
        return horario != null && horario.matches(permitido);
    }
}
