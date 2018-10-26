package dbWithWindowBuilder;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Windowregister extends JFrame {

	private JPanel contentPane;
	private JTextField login;
	private JPasswordField password;
	private JPasswordField passwordRegister;
	private JButton btnRegister;
	private JLabel Message;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Windowregister frame = new Windowregister();
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
	public Windowregister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		login = new JTextField();
		login.setBounds(27, 56, 96, 20);
		contentPane.add(login);
		login.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(27, 111, 117, 20);
		contentPane.add(password);
		
		Message = new JLabel("Message");
		Message.setDisplayedMnemonic('1');
		Message.setBounds(299, 114, 153, 14);
		contentPane.add(Message);
		
		passwordRegister = new JPasswordField();
		passwordRegister.setBounds(27, 178, 137, 20);
		contentPane.add(passwordRegister);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(26, 31, 118, 14);
		contentPane.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 87, 117, 14);
		contentPane.add(lblPassword);
		
		JLabel lblPasswordConfirmation = new JLabel("Password Confirmation");
		lblPasswordConfirmation.setBounds(27, 153, 191, 14);
		contentPane.add(lblPasswordConfirmation);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!password.getText().toString().isEmpty() && !passwordRegister.getText().toString().isEmpty() && !login.getText().toString().isEmpty() && password.getText().toString().equals(passwordRegister.getText().toString())) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						String verif = "SELECT * FROM user WHERE Login = '" + login.getText().toString()+"';";
						Connection co = DriverManager.getConnection("jdbc:mysql://localhost/loginpresentation?serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "");
						Statement stmt = co.createStatement();
						ResultSet resultat = stmt.executeQuery(verif);
						if(resultat.next()) {
							Message.setText("Le pseudo existe déjà");
						}
						else {
							String sql = "INSERT INTO user VALUES ('" + login.getText().toString() +"','" + password.getText().toString() +"');";
							Message.setText("compte créé");
							stmt.execute(sql);
							homeFrame R = new homeFrame();
							R.setVisible(true);
							dispose();
						}						
						
					}
					catch(Exception err){
						System.out.println(err);
					}
				}
				else {
					System.out.println("pas bon");
				}
			}
		});
		btnRegister.setBounds(150, 231, 89, 23);
		contentPane.add(btnRegister);
		

	}
}
