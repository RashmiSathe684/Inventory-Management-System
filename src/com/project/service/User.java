package com.project.service;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.project.dao.UserDB;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class User extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField uname;
	private JTextField uno;
	private JPasswordField upass;
	private JTable UserTable;
	private JLabel unameLabelError,upassLabelError,unoLabelError;
	private static String driver="com.mysql.jdbc.Driver"; 
	  private static String url="jdbc:mysql:///inventory_management_system";
	  private static String user="root";
	  private static String pwd=null;
	
		String query;
		Connection con=null;
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User frame = new User();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public User() {
		initcomponents();
		displayIntoUserTable();
	}
	//Display the data of user table into User JTable
	public void displayIntoUserTable() 
	{
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			rs=st.executeQuery("select * from userdb");
			UserTable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void initcomponents()
	{	
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
		
		JLabel lblHomePage = new JLabel("Manage Users");
		lblHomePage.setBounds(335, 57, 298, 38);
		lblHomePage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomePage.setForeground(Color.WHITE);
		lblHomePage.setFont(new Font("Century Gothic", Font.BOLD, 30));
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
		
		JLabel lblNewLabel_01 = new JLabel("Username :");
		lblNewLabel_01.setBounds(29, 138, 129, 32);
		lblNewLabel_01.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblNewLabel_01.setForeground(new Color(220, 20, 60));
		panel.add(lblNewLabel_01);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setBounds(29, 193, 151, 32);
		lblNewLabel_1_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Phone No : ");
		lblNewLabel_1_2.setBounds(29, 252, 151, 32);
		lblNewLabel_1_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_1_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_1_2);
		
		uname = new JTextField();
		uname.setBounds(168, 138, 176, 32);
		uname.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		uname.setColumns(10);
		panel.add(uname);
		
		uno = new JTextField();
		uno.setBounds(168, 254, 176, 32);
		uno.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		uno.setColumns(10);
		panel.add(uno);
		
		upass = new JPasswordField();
		upass.setBounds(168, 192, 176, 33);
		upass.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		upass.setColumns(10);
		panel.add(upass);
		
		
		unameLabelError = new JLabel("*Username is required");
		unameLabelError.setBounds(196, 174, 194, 13);
		unameLabelError.setVisible(false);
		unameLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		unameLabelError.setForeground(Color.RED);
		panel.add(unameLabelError);
		
		
		upassLabelError = new JLabel("*Password is required");
		upassLabelError.setBounds(196, 231, 176, 13);
		upassLabelError.setVisible(false);
		upassLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		upassLabelError.setForeground(Color.RED);
		panel.add(upassLabelError);
		
		
		unoLabelError = new JLabel("*Valid phone no is required");
		unoLabelError.setBounds(196, 288, 212, 19);
		unoLabelError.setVisible(false);
		unoLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		unoLabelError.setForeground(Color.RED);
		panel.add(unoLabelError);
		
		JButton AddBtn = new JButton("Add ");
		AddBtn.setBounds(22, 328, 85, 41);
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				boolean b;
				//Validation for Username
				 b =Pattern.compile("^[A-Za-z]{2,29}$").matcher(uname.getText()).matches(); 
				 if(b==true) 
				 { 
					 unameLabelError.setVisible(false);
					
				 }
				 else 
				 {
					 unameLabelError.setVisible(true);

				 }
				//Validation for password
				 b =Pattern.compile("^[A-Za-z0-9@#!$]{6,10}$").matcher(String.valueOf(upass.getPassword())).matches(); 
				 if(b==true) 
				 { 
					 upassLabelError.setVisible(false);
					}
				 else 
				 {
					 upassLabelError.setVisible(true);

				 }
				 
				//Validation for User No
				 b =Pattern.compile("^[6-9]{1}[0-9]{9}$").matcher(uno.getText()).matches(); 
				 if(b==true) { 
					 unoLabelError.setVisible(false);
								}
				 else {
					 unoLabelError.setVisible(true);

				 }
				 
				 if(uname.getText().equals(null) && upass.getPassword().equals(null) &&  uno.getText().equals(null))
	        	 { 
        		     if(unameLabelError.isVisible() && upassLabelError.isVisible() && unoLabelError.isVisible()) 
	        		   {

		        	 JOptionPane.showMessageDialog(null,"Not Submitted");
	        		   }		      
        		 }				
        		 else
        		 {
        			 ArrayList<String> al=new ArrayList<String>();
					 		 al.add(uname.getText());
					         al.add(String.valueOf(upass.getPassword()));
					         al.add(uno.getText());
					        
				        	 if(!uname.getText().equals(null) && !upass.getPassword().equals(null) &&  !uno.getText().equals(null))
				        	 { 
				        		 if(!unameLabelError.isVisible() && !upassLabelError.isVisible() && !unoLabelError.isVisible()) 
				        		   {

						    	 UserDB pb=new UserDB();
						    	 pb.insertIntoReg(al);
							     JOptionPane.showMessageDialog(null,"User Added Successfully ");
							     displayIntoUserTable();
				        		   }
				        	 else 
			        		 {
			        			 JOptionPane.showMessageDialog(null,"User is not added.!!");			        		 
		        		   }
		        		 }		        		
        		 }
			}
		});
		AddBtn.setFont(new Font("Century Gothic", Font.BOLD, 16));
		AddBtn.setForeground(Color.WHITE);
		AddBtn.setBorderPainted(false);
		AddBtn.setBackground(new Color(220, 20, 60));
		panel.add(AddBtn);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(uname.getText().isEmpty() || upass.getPassword().equals("") || uno.getText().isEmpty()) {
					 JOptionPane.showMessageDialog(null,"Missing Information");
				}
				else {
					try {
						con=DriverManager.getConnection(url,user,pwd);
						String UpdateQuery="update userdb set Username='"+uname.getText()+"'"+",Password='"+String.valueOf(upass.getPassword())+"'"+" where PhoneNo="+uno.getText();
						Statement Add=con.createStatement();
						Add.executeUpdate(UpdateQuery);
						JOptionPane.showMessageDialog(null,"User Updated Successfully");
						displayIntoUserTable();
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnEdit.setBounds(139, 328, 85, 41);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(220, 20, 60));
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				 try {
//					 String msg;
//					msg= JOptionPane.showInputDialog ("Enter Phoneno of user to be Deleted : ");
//					int Id= Integer.parseInt(msg);
//					 con=DriverManager.getConnection(url,user,pwd); 
//					 String Query="Delete from userdb where PhoneNo="+Id;
//					 Statement Add=con.createStatement();
//					 Add.executeUpdate(Query);
//					 displayIntoUserTable();
//					 JOptionPane.showMessageDialog(null,"User Deleted Successfully");			 
//				 }

				 try {
					 String phone;
					 phone= JOptionPane.showInputDialog ("Enter Phoneno of user to be Deleted : ");
						int id= Integer.parseInt(phone);
					 con=DriverManager.getConnection(url,user,pwd); 
					 String Query="Delete from userdb where PhoneNo="+id;
					 Statement Add=con.createStatement();
					 Add.executeUpdate(Query);
					 displayIntoUserTable();
					 JOptionPane.showMessageDialog(null,"User Deleted Successfully");
				 }
				 catch(SQLException e3) {
					 e3.printStackTrace();
				 }
			}
		});		btnDelete.setBounds(259, 328, 85, 41);
		btnDelete.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(220, 20, 60));
		panel.add(btnDelete);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new HomePage().setVisible(true);
			dispose();
			}
		});
		btnHome.setBounds(139, 405, 85, 41);
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBorderPainted(false);
		btnHome.setBackground(new Color(220, 20, 60));
		panel.add(btnHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 174, 564, 248);
		scrollPane.setFont(new Font("Century Gothic", Font.BOLD, 15));
		panel.add(scrollPane);
		
		UserTable = new JTable();
		UserTable.setRowHeight(21);
		UserTable.setRowMargin(3);
		UserTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)UserTable.getModel();
				int Myindex=UserTable.getSelectedRow();
				uname.setText(model.getValueAt(Myindex,0).toString());
				upass.setText(model.getValueAt(Myindex,1).toString());
				uno.setText(model.getValueAt(Myindex,2).toString());
			}
		});
		UserTable.setColumnSelectionAllowed(true);
		UserTable.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		UserTable.setCellSelectionEnabled(true);
		UserTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Username", "Password", "PhoneNo "
			}
		));
		scrollPane.setViewportView(UserTable);
		
		JLabel lblProductList = new JLabel("User List");
		lblProductList.setBounds(497, 128, 323, 38);
		lblProductList.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductList.setForeground(new Color(220, 20, 60));
		lblProductList.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblProductList);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 576, 1000, 24);
		panel_2.setBackground(new Color(220, 20, 60));
		panel.add(panel_2);
		
		
		
		setUndecorated(true);
	}
}
