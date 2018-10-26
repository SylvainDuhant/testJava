package dbWithWindowBuilder;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class homeFrame extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public homeFrame() {
		String user = null;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel welcome = new JLabel("Welcome " + user);
		welcome.setBounds(119, 21, 202, 14);
		contentPane.add(welcome);
		
		
		
		JButton btnSupprimerLeCompte = new JButton("Supprimer le compte");
		btnSupprimerLeCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String verif = "DELETE FROM user WHERE Login = '" + user + "';";
					Connection co = DriverManager.getConnection("jdbc:mysql://localhost/loginpresentation?serverTimezone=UTC&autoReconnect=true&useSSL=false", "root", "");
					Statement stmt = co.createStatement();
					stmt.execute(verif);
					fenetre1 R = new fenetre1();
					R.setVisible(true);
					dispose();
			}
			catch(Exception err){
					System.out.println(err);
				}
		}});
		btnSupprimerLeCompte.setBounds(119, 182, 196, 23);
		contentPane.add(btnSupprimerLeCompte);
	}

}
