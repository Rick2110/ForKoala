package koalinha;

// Bibliotecas que estão sendo importadas
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import koalinha.cadastro.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Window.Type;

// Declaração da classe principal 
public class Koala {

	private JFrame frmKoalaLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	
	public String usuario;
	public Connection conectar;

	//Metodo principal
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			// metodo para abrir a janela da interface
			public void run() {
				try {
					Koala window = new Koala();
					window.frmKoalaLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Inicia a aplicação
	public Koala() {
		initialize();
	}

	//Design da janela
	private void initialize() {
		//Janela
		frmKoalaLogin = new JFrame();
		frmKoalaLogin.setResizable(false);
		frmKoalaLogin.getContentPane().setBackground(new Color(78, 90, 66));
		frmKoalaLogin.setTitle("ForKoala - Login");
		frmKoalaLogin.setBounds(100, 100, 520, 500);
		frmKoalaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKoalaLogin.setLocationRelativeTo(null);
		frmKoalaLogin.getContentPane().setLayout(null);
		
		//Botão de login
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setBackground(new Color(181, 143, 115));
		btnNewButton.setBounds(189, 285, 114, 26);
		frmKoalaLogin.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBackground(new Color(210, 222, 198));
		textField.setBounds(149, 201, 190, 26);
		frmKoalaLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		//Texto de usuario
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(149, 182, 45, 19);
		frmKoalaLogin.getContentPane().add(lblNewLabel);
		
		//Texto da senha
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(149, 230, 45, 13);
		frmKoalaLogin.getContentPane().add(lblNewLabel_1);
		
		//Botão que vai para a pagina de login
		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.setBackground(new Color(181, 143, 115));
		btnNewButton_1.setBounds(189, 360, 114, 19);
		frmKoalaLogin.getContentPane().add(btnNewButton_1);
		
		//campo para digitar a senha
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(210, 222, 198));
		passwordField.setBounds(149, 249, 190, 26);
		frmKoalaLogin.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Não tem conta? Cadastre-se");
		lblNewLabel_2.setBounds(160, 336, 173, 19);
		frmKoalaLogin.getContentPane().add(lblNewLabel_2);
		
		//botão para realizar o login
		JLabel lblNewLabel_3 = new JLabel("Login");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_3.setBounds(189, 123, 100, 49);
		frmKoalaLogin.getContentPane().add(lblNewLabel_3);
		telaPrincipal tela = new telaPrincipal(usuario);
		cadastro cad = new cadastro();
		
		
		//carregar a imagem do koala
		BufferedImage wPic = null;
		try {
			wPic = ImageIO.read(this.getClass().getResource("/Koala_logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel wIcon = new JLabel(new ImageIcon(wPic));
		wIcon.setBounds(189, 22, 114, 91);
		frmKoalaLogin.getContentPane().add(wIcon);
		
		
		//ir para janela de cadastro
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cad.cadastrar(null); // abre janela cadastro
				frmKoalaLogin.dispose(); //fecha janaela atual
				
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Acessa o usuário e a senha
		        String usuario = textField.getText(); //guarda usuario em variavel
		        String password = new String(passwordField.getPassword());//guarda o valor da senha em uma variavel
		        
		        //conecta ao banco de dados
		        try (Connection conn = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10750695", "sql10750695", "FdsIjDECHi")) {
		            String sql = "SELECT usuario, senha FROM cadastro WHERE usuario = ? AND senha = ?";
		            
		            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
		                stmt.setString(1, usuario);
		                stmt.setString(2, password);
		                //verifica se o usuario e a senha existem no servidor
		                try (ResultSet rs = stmt.executeQuery()) {
		                    if (rs.next()) {
		                        telaPrincipal.telaScreen(usuario); // Passa o usuário para a tela principal
		                        frmKoalaLogin.dispose(); // Fecha a janela de login
		                    } else {
		                    	JOptionPane.showMessageDialog(frmKoalaLogin,"Usuário ou senha incorretas.");
		                    }
		                }
		            }
		        } catch (SQLException e1) {
		        	JOptionPane.showMessageDialog(frmKoalaLogin,"Erro ao conectar com o banco de dados: " + e1.getMessage());
		        }
		    }
		});
	}
}
