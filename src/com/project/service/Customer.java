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

import com.project.dao.CustomerDB;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Customer extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cstId;
	private JTextField cstNo;
	private JTable CustomerTable;
	private JTextField cstName;
	private JLabel CountLbl,AmountLbl;

	private JLabel cstIdLabelError,cstNoLabelError,cstNameLabelError;
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
					Customer frame = new Customer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Customer() {
		initcomponents();
		displayIntoCstTable();
	}
	
	public void displayIntoCstTable() 
	{
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			rs=st.executeQuery("select * from customerdb");
			CustomerTable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void initcomponents()
	{	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1000, 650);
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
		
		JLabel lblHomePage = new JLabel("Customer Product");
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
		
		JLabel lblNewLabel_01 = new JLabel("Customer Id :");
		lblNewLabel_01.setBounds(22, 138, 129, 32);
		lblNewLabel_01.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblNewLabel_01.setForeground(new Color(220, 20, 60));
		panel.add(lblNewLabel_01);
		
		JLabel lblNewLabel_1_1 = new JLabel("Name :");
		lblNewLabel_1_1.setBounds(22, 200, 151, 32);
		lblNewLabel_1_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Phone No : ");
		lblNewLabel_1_2.setBounds(22, 258, 151, 32);
		lblNewLabel_1_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_1_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_1_2);
		
		cstId = new JTextField();
		cstId.setBounds(168, 138, 176, 32);
		cstId.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cstId.setColumns(10);
		panel.add(cstId);
		
		
		cstName = new JTextField();
		cstName.setBounds(168, 200, 176, 32);
		cstName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cstName.setColumns(10);
		panel.add(cstName);
		
		cstNo = new JTextField();
		cstNo.setBounds(168, 258, 176, 32);
		cstNo.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cstNo.setColumns(10);
		panel.add(cstNo);
		
	
		
		JButton AddBtn = new JButton("Add ");
		AddBtn.setBounds(22, 328, 85, 41);
		AddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				boolean b;
				//Validation for Customer Id 
				 b = Pattern.compile("^[0-9]$").matcher(cstId.getText()).matches(); 
				 if(b==true) 
				 { 
					 cstIdLabelError.setVisible(false);
					
				 }
				 else 
				 {
					 cstIdLabelError.setVisible(true);

				 }
				 
				//Validation for Customer Name
				 b =Pattern.compile("^[A-Za-z0_\\s]*$").matcher(cstName.getText()).matches(); 
				 if(b==true) 
				 { 
					 cstNameLabelError.setVisible(false);
					}
				 else 
				 {
					 cstNameLabelError.setVisible(true);

				 }
				 
				//Validation for Customer No
				 b =Pattern.compile("^[6-9]{1}[0-9]{9}$").matcher(cstNo.getText()).matches(); 
				 if(b==true) { 
					 cstNoLabelError.setVisible(false);
								}
				 else {
					 cstNoLabelError.setVisible(true);

				 }
				 if(cstId.getText().equals(null) && cstName.getText().equals(null) &&  cstNo.getText().equals(null))
	        	 { 
        		     if(cstIdLabelError.isVisible() && cstNameLabelError.isVisible() && cstNoLabelError.isVisible()) 
	        		   {

		        	 JOptionPane.showMessageDialog(null,"Not Submitted");
	        		   }		      
        		 }				
        		 else
        		 {
        			 ArrayList<String> al=new ArrayList<String>();
					 		 al.add(cstId.getText());
					         al.add(cstName.getText());
					         al.add(cstNo.getText());
					        
				        	 if(!cstId.getText().equals(null) && !cstName.getText().equals(null) &&  !cstNo.getText().equals(null))
				        	 { 
				        		 if(!cstIdLabelError.isVisible() && !cstNameLabelError.isVisible() && !cstNoLabelError.isVisible()) 
				        		   {

						    	 CustomerDB pb=new CustomerDB();
						    	 pb.insertIntoReg(al);
							     JOptionPane.showMessageDialog(null,"Customer Added Successfully ");
							     displayIntoCstTable();
				        		   }
				        	 else 
			        		 {
			        			 JOptionPane.showMessageDialog(null,"Customer is not added.!!");			        		 
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
		btnEdit.setBounds(139, 328, 85, 41);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cstId.getText().isEmpty() || cstName.getText().isEmpty() || cstNo.getText().isEmpty()) {
					 JOptionPane.showMessageDialog(null,"Missing Information");
				}
				else {
					try {
						con=DriverManager.getConnection(url,user,pwd);
						String UpdateQuery="update customerdb set CustomerName='"+cstName.getText()+"'"+",CustomerNo='"+cstNo.getText()+"'"+" where CustomerId="+cstId.getText();
						Statement Add=con.createStatement();
						Add.executeUpdate(UpdateQuery);
						JOptionPane.showMessageDialog(null,"Customer Updated Successfully");
						displayIntoCstTable();
					}catch(Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnEdit.setBorderPainted(false);
		btnEdit.setBackground(new Color(220, 20, 60));
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(259, 328, 85, 41);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
					 String prodid;
						prodid= JOptionPane.showInputDialog ("Enter Customer ID to be Deleted : ");
						int id= Integer.parseInt(prodid);
					 con=DriverManager.getConnection(url,user,pwd); 
					 String Query="Delete from customerdb where CustomerId="+id;
					 Statement Add=con.createStatement();
					 Add.executeUpdate(Query);
					 displayIntoCstTable();
					 JOptionPane.showMessageDialog(null,"Customer Deleted Successfully");
				 }catch(SQLException e3) {
					 e3.printStackTrace();
				 }
			}
		});
		btnDelete.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(220, 20, 60));
		panel.add(btnDelete);
		
		JButton btnHome = new JButton("Home");
		btnHome.setBounds(139, 405, 85, 41);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			new HomePage().setVisible(true);
			dispose();
			}
		});
		btnHome.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnHome.setForeground(Color.WHITE);
		btnHome.setBorderPainted(false);
		btnHome.setBackground(new Color(220, 20, 60));
		panel.add(btnHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 174, 564, 248);
		scrollPane.setFont(new Font("Century Gothic", Font.BOLD, 15));
		panel.add(scrollPane);
		
		CustomerTable = new JTable();
		CustomerTable.setRowHeight(21);
		CustomerTable.setRowMargin(3);
		CustomerTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model=(DefaultTableModel)CustomerTable.getModel();
				int Myindex=CustomerTable.getSelectedRow();
				cstId.setText(model.getValueAt(Myindex,0).toString());
				cstName.setText(model.getValueAt(Myindex,1).toString());
				cstNo.setText(model.getValueAt(Myindex,2).toString());
				
				try {
					con=DriverManager.getConnection(url, user, pwd);
					st=con.createStatement();
					Statement st1=null;
					st1=con.createStatement();
					ResultSet rs1=null;
					
					rs=st.executeQuery("select count(*) from orderdb where OrderName='"+model.getValueAt(Myindex,1).toString()+"'");
					rs1=st1.executeQuery("select SUM(Amount) from orderdb where OrderName='"+model.getValueAt(Myindex,1).toString()+"'");
					while(rs.next()) {
						CountLbl.setText(""+rs.getInt(1));
					}
					while(rs1.next()){
						AmountLbl.setText("Rs"+rs1.getInt(1));
				}
				}catch(SQLException e1){
				e1.printStackTrace();
				}
				
		
			}
		});
		CustomerTable.setColumnSelectionAllowed(true);
		CustomerTable.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		CustomerTable.setCellSelectionEnabled(true);
		CustomerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Customer Id", "Name", "Mobile No "
			}
		));
		scrollPane.setViewportView(CustomerTable);
		
		JLabel lblProductList = new JLabel("Customer List");
		lblProductList.setBounds(497, 128, 323, 38);
		lblProductList.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductList.setForeground(new Color(220, 20, 60));
		lblProductList.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblProductList);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 626, 1000, 24);
		panel_2.setBackground(new Color(220, 20, 60));
		panel.add(panel_2);
		
		
		
		cstIdLabelError = new JLabel("*Customer Id is required");
		cstIdLabelError.setBounds(196, 174, 194, 13);
		cstIdLabelError.setVisible(false);
		cstIdLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cstIdLabelError.setForeground(Color.RED);
		panel.add(cstIdLabelError);
		
		
		cstNameLabelError = new JLabel("*Customer Name is required");
		cstNameLabelError.setBounds(178, 235, 212, 13);
		cstNameLabelError.setVisible(false);
		cstNameLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cstNameLabelError.setForeground(Color.RED);
		panel.add(cstNameLabelError);
		
		
		cstNoLabelError = new JLabel("*Valid phone no is required");
		cstNoLabelError.setBounds(178, 300, 212, 19);
		cstNoLabelError.setVisible(false);
		cstNoLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		cstNoLabelError.setForeground(Color.RED);
		panel.add(cstNoLabelError);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(220, 220, 220));
		panel_3.setBounds(123, 475, 218, 129);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		CountLbl = new JLabel("X");
		CountLbl.setBounds(90, 62, 41, 26);
		CountLbl.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel_3.add(CountLbl);
		
		JPanel panel_5 = new JPanel();
		panel_5.setForeground(new Color(255, 255, 255));
		panel_5.setBackground(new Color(47, 79, 79));
		panel_5.setBounds(0, 0, 218, 38);
		panel_3.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Order Number ");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel_3.setBounds(10, 0, 198, 38);
		panel_5.add(lblNewLabel_3);
		
		JPanel panel_4= new JPanel();
		panel_4.setBackground(new Color(211, 211, 211));
		panel_4.setBounds(463, 475, 218, 129);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		AmountLbl = new JLabel("X");
		AmountLbl.setHorizontalAlignment(SwingConstants.CENTER);
		AmountLbl.setFont(new Font("Century Gothic", Font.BOLD, 25));
		AmountLbl.setBounds(25, 64, 183, 26);
		panel_4.add(AmountLbl);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBackground(new Color(47, 79, 79));
		panel_5_1.setFont(new Font("Segoe UI Variable", Font.PLAIN, 10));
		panel_5_1.setBounds(0, 0, 218, 38);
		panel_4.add(panel_5_1);
		panel_5_1.setLayout(null);
		
		JLabel lblNewLabel_3_1 = new JLabel("Order Amount");
		lblNewLabel_3_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblNewLabel_3_1.setBounds(10, 0, 198, 38);
		panel_5_1.add(lblNewLabel_3_1);
		
		
		setUndecorated(true);
	}
}
