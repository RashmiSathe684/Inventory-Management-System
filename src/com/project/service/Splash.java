package com.project.service;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class Splash extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel Percentage;
	private JProgressBar Myprogress;

	public static void main(String[] args) {
					Splash frame = new Splash();
					frame.setVisible(true);
					try {
						for(int i=0;i<=100;i++) {
							Thread.sleep(40);
							frame.Myprogress.setValue(i);
							frame.Percentage.setText(Integer.valueOf(i)+"%");
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
					new Login().setVisible(true);
					frame.dispose();
			}
		 //Start next time

	public Splash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 800,450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, new Color(255, 182, 193), new Color(245, 245, 245), new Color(255, 182, 193)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 450);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Myprogress = new JProgressBar();
		Myprogress.setBounds(0, 428, 800, 22);
		Myprogress.setBackground(new Color(220, 20, 60));
		Myprogress.setForeground(Color.WHITE);
		panel.add(Myprogress);
		
		JLabel lblNewLabel = new JLabel("Inventory Management System");
		lblNewLabel.setBounds(159, 10, 502, 60);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("InvSys");
		lblNewLabel_1.setBounds(454, 186, 120, 48);
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setFont(new Font("Century Gothic", Font.BOLD, 30));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(282, 112, 162, 167);
		lblNewLabel_2.setBackground(new Color(127, 255, 212));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Rashmi Sathe\\eclipse-workspace\\Inventory Management System\\Project Images\\db1.png"));
		panel.add(lblNewLabel_2);
		
		Percentage = new JLabel("%");
		Percentage.setForeground(new Color(220, 20, 60));
		Percentage.setFont(new Font("Century Gothic", Font.BOLD, 30));
		Percentage.setHorizontalAlignment(SwingConstants.CENTER);
		Percentage.setBounds(323, 320, 89, 48);
		panel.add(Percentage);
		setUndecorated(true);
	}
}
