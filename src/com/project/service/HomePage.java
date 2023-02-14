package com.project.service;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static HomePage frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new HomePage();
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
	public HomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1000, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1000, 105);
		panel_1.setBackground(new Color(220, 20, 60));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory Management System");
		lblNewLabel.setBounds(218, 10, 555, 50);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 35));
		panel_1.add(lblNewLabel);
		
		JLabel lblHomePage = new JLabel("Main Form");
		lblHomePage.setBounds(335, 57, 298, 38);
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setForeground(Color.WHITE);
		lblHomePage.setFont(new Font("Century Gothic", Font.BOLD, 35));
		panel_1.add(lblHomePage);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		lblNewLabel_1.setBounds(963, 12, 27, 38);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 579, 1000, 21);
		panel_2.setBackground(new Color(220, 20, 60));
		panel.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Product ");
		lblNewLabel_2.setBounds(83, 177, 136, 35);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Customer");
		lblNewLabel_2_1.setBounds(83, 390, 136, 35);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_2_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("User");
		lblNewLabel_2_2.setBounds(770, 177, 98, 35);
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_2_2.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Order");
		lblNewLabel_2_3.setBounds(765, 387, 136, 41);
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setForeground(new Color(220, 20, 60));
		lblNewLabel_2_3.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Category");
		lblNewLabel_2_3_1.setBounds(417, 277, 136, 35);
		lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_2_3_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblNewLabel_2_3_1);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Product().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_3.setBounds(93, 212, 110, 86);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Rashmi Sathe\\eclipse-workspace\\Inventory Management System\\Project Images\\product.png"));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new User().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_3_1.setBounds(780, 212, 90, 86);
		lblNewLabel_3_1.setIcon(new ImageIcon("C:\\Users\\Rashmi Sathe\\eclipse-workspace\\Inventory Management System\\Project Images\\user.png"));
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("");
		lblNewLabel_3_1_1.addMouseListener(new MouseAdapter() {
			 @Override
			public void mouseClicked(MouseEvent e) {
				new Customer().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_3_1_1.setBounds(93, 435, 98, 86);
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setIcon(new ImageIcon("C:\\Users\\Rashmi Sathe\\eclipse-workspace\\Inventory Management System\\Project Images\\customer.png"));
		panel.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_2 = new JLabel("");
		lblNewLabel_3_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Category().setVisible(true);
				dispose();
			}
			
			
		});
		lblNewLabel_3_1_2.setBounds(451, 322, 90, 75);
		lblNewLabel_3_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_2.setIcon(new ImageIcon("C:\\Users\\Rashmi Sathe\\eclipse-workspace\\Inventory Management System\\Project Images\\category.png"));
		panel.add(lblNewLabel_3_1_2);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("");
		lblNewLabel_3_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Order().setVisible(true);
				dispose();
			}
		});
		lblNewLabel_3_1_1_1.setBounds(790, 435, 83, 86);
		lblNewLabel_3_1_1_1.setIcon(new ImageIcon("C:\\Users\\Rashmi Sathe\\eclipse-workspace\\Inventory Management System\\Project Images\\order.png"));
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_3_1_1_1);
		
		JButton btnNewButton = new JButton("LogOut");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login().setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setBounds(399, 516, 162, 41);
		panel.add(btnNewButton);
		setUndecorated(true);
	}

}
