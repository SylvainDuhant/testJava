package dbWithWindowBuilder;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class fenetre1 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField login;
	private JPasswordField password;
	private JButton btnCo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fenetre1 frame = new fenetre1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public fenetre1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(25, 22, 73, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(25, 82, 46, 14);
		contentPane.add(lblPassword);
		
		login = new JTextField();
		login.setBounds(20, 47, 96, 20);
		contentPane.add(login);
		login.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(25, 107, 96, 20);
		contentPane.add(password);
		
		btnCo = new JButton("Login");
		btnCo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection co = DriverManager.getConnection("jdbc:mysql://localhost/loginpresentation?serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "");
					Statement stmt = co.createStatement();
					String sql = "SELECT * FROM user WHERE Login = '" + login.getText().toString()+"' AND Password = '" + password.getText().toString() +"';";
					ResultSet resultat = stmt.executeQuery(sql);
					JLabel lbco = new JLabel("");
					lbco.setBounds(298, 82, 70, 14);
					contentPane.add(lbco);
					if(resultat.next()) {
						lbco.setText("Connecté");
					}
					else {
						lbco.setText("login/mot de passe incorrect");
					}
					
				}
				catch(Exception err){
					System.out.println(err);
				}
			}
		});
		btnCo.setBounds(25, 154, 89, 23);
		contentPane.add(btnCo);				
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Windowregister R = new Windowregister();
				R.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBounds(261, 154, 89, 23);
		contentPane.add(btnRegister);
		
	}
}
