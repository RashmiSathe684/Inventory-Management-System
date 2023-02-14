package com.project.service;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField uid;
	private JPasswordField pwd;
	private JLabel passLabelError,unameLabelError;
	private static String driver="com.mysql.jdbc.Driver"; 
	  private static String url="jdbc:mysql:///inventory_management_system";
	  private static String user="root";
	  private static String pwd1=null;
	Connection con=null;
	PreparedStatement ps=null;
	Statement st=null;
	ResultSet rs=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 450, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 450, 127);
		panel_1.setBackground(new Color(220, 20, 60));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("InvSys");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 35));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(120, 26, 165, 58);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("X");
		lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(413, 10, 27, 38);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Username : ");
		lblNewLabel_1.setBounds(22, 244, 138, 41);
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password : ");
		lblNewLabel_2.setBounds(22, 315, 138, 41);
		lblNewLabel_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 20));
		panel.add(lblNewLabel_2);
		
		uid = new JTextField();
		uid.setBounds(170, 251, 256, 41);
		panel.add(uid);
		uid.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(170, 316, 256, 41);
		pwd.setColumns(10);
		panel.add(pwd);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(60, 414, 121, 41);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(uid.getText().isEmpty() || pwd.getPassword().equals(null)) 
				{
					JOptionPane.showMessageDialog(null,"Missing Information" );
				}
				else {
				String Query="select * from userdb where Username='"+uid.getText()+"' and Password='"+String.valueOf(pwd.getPassword())+"'";
				try {
					Class.forName(driver);
					con=DriverManager.getConnection(url,user,pwd1);
					st=con.createStatement();
					rs=st.executeQuery(Query);
					if(rs.next()) {
						new HomePage().setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null,"Wrong Username and Password" );
					}
				}catch(Exception exp) {
					exp.printStackTrace();
				}
				}
			}
		});
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(127, 255, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
		panel.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(242, 414, 121, 41);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uid.setText("");
				pwd.setText("");
			}
		});
		btnClear.setBorderPainted(false);
		btnClear.setBackground(new Color(255, 69, 0));
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Century Gothic", Font.BOLD, 20));
		panel.add(btnClear);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(164, 137, 97, 97);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Rashmi Sathe\\eclipse-workspace\\Inventory Management System\\Project Images\\trolly1.png"));
		panel.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 559, 450, 41);
		panel_2.setBackground(new Color(220, 20, 60));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		unameLabelError = new JLabel("*Valid Username is required");
		unameLabelError.setBounds(216, 293, 210, 13);
		unameLabelError.setVisible(false);
		unameLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		unameLabelError.setForeground(Color.RED);
		panel.add(unameLabelError);
		
		passLabelError = new JLabel("*Valid Password is required");
		passLabelError.setBounds(216, 367, 210, 13);
		passLabelError.setVisible(false);
		passLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		passLabelError.setForeground(Color.RED);
		panel.add(passLabelError);
		setUndecorated(true);
	}
}
