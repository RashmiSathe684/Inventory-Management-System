package com.project.service;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.project.dao.CategoryDB;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
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
import java.awt.event.ActionEvent;

public class Category extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField CatId;
	private JTable CategoryTable;
	
	private JTextField CatName;
	private JLabel catIdLabelError,catNameLabelError;
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
					Category frame = new Category();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Category() {
		initcomponents();
		displayIntoCatTable();
	}
	/**
	 * Create the frame.
	 */
	public void displayIntoCatTable() {
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			rs=st.executeQuery("select * from categorydb");
			CategoryTable.setModel(DbUtils.resultSetToTableModel(rs));
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
		
		JLabel lblHomePage = new JLabel("Manage Categories");
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
		
		JLabel lblNewLabel_01 = new JLabel("Category Id :");
		lblNewLabel_01.setBounds(29, 138, 129, 32);
		lblNewLabel_01.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblNewLabel_01.setForeground(new Color(220, 20, 60));
		panel.add(lblNewLabel_01);
		
		JLabel lblNewLabel_1_1 = new JLabel("Category Name :");
		lblNewLabel_1_1.setBounds(29, 205, 176, 32);
		lblNewLabel_1_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_1_1);
		
		CatId = new JTextField();
		CatId.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		CatId.setBounds(209, 143, 176, 32);
		panel.add(CatId);
		CatId.setColumns(10);
		
		JButton addBtn = new JButton("Add ");
		addBtn.setBounds(44, 285, 85, 41);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean b;
				//Validation for Category Id 
				 b = Pattern.compile("^[0-9]*$").matcher(CatId.getText()).matches(); 
				 if(b==true) 
				 { 
					 catIdLabelError.setVisible(false);
					
				 }
				 else 
				 {
					 catIdLabelError.setVisible(true);

				 }
				 
				//Validation for Category Name
				 b =Pattern.compile("^[A-Za-z0-9_-]*$").matcher(CatName.getText()).matches(); 
				 if(b==true) 
				 { 
					catNameLabelError.setVisible(false);
				 }
				 else {
					catNameLabelError.setVisible(true);

				 }
				 
				 
				 if(CatId.getText().equals(null) && CatName.getText().equals(null))
	        	 { 
        		     if(catIdLabelError.isVisible() && catNameLabelError.isVisible()) 
	        		   {

		        	 JOptionPane.showMessageDialog(null,"Not Submitted");
	        		   }		      
        		 }				
        		 else
        		 {
        			 ArrayList<String> al=new ArrayList<String>();
					 		 al.add(CatId.getText());
					         al.add(CatName.getText());
					        
				        	 if(!CatId.getText().equals(null) && !CatName.getText().equals(null))
				        	 { 
				        		 if(!catIdLabelError.isVisible() && !catNameLabelError.isVisible()) 
				        		   {

						    	 CategoryDB pb=new CategoryDB();
						    	 pb.insertIntoReg(al);
							     JOptionPane.showMessageDialog(null,"Category Added Successfully ");
							     displayIntoCatTable();
				        		   }
				        	 else 
			        		 {
			        			 JOptionPane.showMessageDialog(null,"Category is not added.!!");			        		 
		        		   }
		        		 }		        		
        		 }
			}
		});
		addBtn.setFont(new Font("Century Gothic", Font.BOLD, 16));
		addBtn.setForeground(Color.WHITE);
		addBtn.setBorderPainted(false);
		addBtn.setBackground(new Color(220, 20, 60));
		panel.add(addBtn);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CatId.getText().isEmpty() || CatName.getText().isEmpty() ) {
					 JOptionPane.showMessageDialog(null,"Missing Information");
				}
				else {
					try {
						con=DriverManager.getConnection(url,user,pwd);
						String UpdateQuery="update categorydb set CategoryName='"+CatName.getText()+"'"+" where CategoryId="+CatId.getText();
						Statement Add=con.createStatement();
						Add.executeUpdate(UpdateQuery);
						JOptionPane.showMessageDialog(null,"Product Updated Successfully");
						displayIntoCatTable();
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnEdit.setBounds(167, 285, 85, 41);
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(220, 20, 60));
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
					 String prodid;
						prodid= JOptionPane.showInputDialog ("Enter Category ID to be Deleted : ");
						int id= Integer.parseInt(prodid);
					 con=DriverManager.getConnection(url,user,pwd); 
					 String Query="Delete from categorydb where CategoryId="+id;
					 Statement Add=con.createStatement();
					 Add.executeUpdate(Query);
					 displayIntoCatTable();
					 JOptionPane.showMessageDialog(null,"Category Deleted Successfully");
				 }catch(SQLException e3) {
					 e3.printStackTrace();
				 }
			}
		});
		btnDelete.setBounds(289, 285, 85, 41);
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
		btnHome.setBounds(167, 355, 85, 41);
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBorderPainted(false);
		btnHome.setBackground(new Color(220, 20, 60));
		panel.add(btnHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(449, 163, 524, 366);
		scrollPane.setFont(new Font("Century Gothic", Font.BOLD, 15));
		panel.add(scrollPane);
		
		CategoryTable = new JTable();
		CategoryTable.setRowHeight(21);
		CategoryTable.setRowMargin(3);
		CategoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)CategoryTable.getModel();
				int Myindex=CategoryTable.getSelectedRow();
				CatId.setText(model.getValueAt(Myindex,0).toString());
				CatName.setText(model.getValueAt(Myindex,1).toString());
			}
		});
		CategoryTable.setColumnSelectionAllowed(true);
		CategoryTable.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		CategoryTable.setCellSelectionEnabled(true);
		CategoryTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Category  Id", "Category Name"
			}
		));
		CategoryTable.getColumnModel().getColumn(0).setPreferredWidth(86);
		CategoryTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		scrollPane.setViewportView(CategoryTable);
		
		JLabel lblProductList = new JLabel("Categories List");
		lblProductList.setBounds(553, 115, 323, 38);
		lblProductList.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductList.setForeground(new Color(220, 20, 60));
		lblProductList.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblProductList);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 576, 1000, 24);
		panel_2.setBackground(new Color(220, 20, 60));
		panel.add(panel_2);
		
		CatName = new JTextField();
		CatName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		CatName.setBounds(209, 210, 176, 32);
		CatName.setColumns(10);
		panel.add(CatName);
		
		
		catIdLabelError =  new JLabel("*Category Id is required");
		catIdLabelError.setBounds(219, 174, 220, 24);
		catIdLabelError.setVisible(false);
		catIdLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		catIdLabelError.setForeground(Color.RED);
		panel.add(catIdLabelError);
		
		
		catNameLabelError = new JLabel("*Name is required");	
		catNameLabelError.setBounds(219, 251, 220, 24);
		catNameLabelError.setVisible(false);
		catNameLabelError.setForeground(Color.RED);
		catNameLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		panel.add(catNameLabelError);
		setUndecorated(true);
	}
}
