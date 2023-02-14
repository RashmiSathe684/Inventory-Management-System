package com.project.service;
import com.project.dao.*;

import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class Product extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField prodId;
	private JTextField prodName;
	private JTextField prodQty;
	private JTextField prodDesc;
	//private JTextField prodCat;
	private JComboBox prodCat;
	private JTable ProductTable;
	private JLabel prodIdLabelError,prodCatLabelError,prodNameLabelError,prodQtyLabelError,prodDescLabelError;
	private static String driver="com.mysql.jdbc.Driver"; 
	  private static String url="jdbc:mysql:///inventory_management_system";
	  private static String user="root";
	  private static String pwd=null;
	
		String query;
		Connection con=null;
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Product frame = new Product();
					frame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	public Product() {
		initcomponents();
		displayIntoTable();
		getCat();
	}
	public void displayIntoTable() {
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			rs=st.executeQuery("select * from productdb");
			ProductTable.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	private void getCat() {
		try {
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			String query="select * from categorydb";
			rs=st.executeQuery(query);
			while(rs.next()) {
				String MyCat=rs.getString("CategoryName");
				prodCat.addItem(MyCat);
			}
			
		}catch(Exception exp1) {
			exp1.printStackTrace();
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
	
	JLabel lblHomePage = new JLabel("Manage Product");
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
	lblNewLabel_1.setBounds(969, 12, 21, 25);
	panel_1.add(lblNewLabel_1);
	
	JLabel lblNewLabel_01 = new JLabel("Product Id :");
	lblNewLabel_01.setBounds(29, 133, 151, 32);
	lblNewLabel_01.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	lblNewLabel_01.setForeground(new Color(220, 20, 60));
	panel.add(lblNewLabel_01);
	
	JLabel lblNewLabel_1_1 = new JLabel("Name :");
	lblNewLabel_1_1.setBounds(29, 186, 151, 32);
	lblNewLabel_1_1.setForeground(new Color(220, 20, 60));
	lblNewLabel_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	panel.add(lblNewLabel_1_1);
	
	JLabel lblNewLabel_1_2 = new JLabel("Quantity : ");
	lblNewLabel_1_2.setBounds(29, 242, 151, 32);
	lblNewLabel_1_2.setForeground(new Color(220, 20, 60));
	lblNewLabel_1_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	panel.add(lblNewLabel_1_2);
	
	JLabel lblNewLabel_1_3 = new JLabel("Description : ");
	lblNewLabel_1_3.setBounds(29, 295, 151, 32);
	lblNewLabel_1_3.setForeground(new Color(220, 20, 60));
	lblNewLabel_1_3.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	panel.add(lblNewLabel_1_3);
	
	JLabel lblNewLabel_1_4 = new JLabel("Category : ");
	lblNewLabel_1_4.setBounds(29, 350, 151, 32);
	lblNewLabel_1_4.setForeground(new Color(220, 20, 60));
	lblNewLabel_1_4.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	panel.add(lblNewLabel_1_4);
	
	prodId = new JTextField();
	prodId.setBounds(158, 133, 176, 32);
	prodId.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	panel.add(prodId);
	prodId.setColumns(10);
	
	prodName = new JTextField();
	prodName.setBounds(158, 186, 176, 32);
	prodName.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	prodName.setColumns(10);
	panel.add(prodName);
	
	prodQty = new JTextField();
	prodQty.setBounds(158, 242, 176, 24);
	prodQty.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	prodQty.setColumns(10);
	panel.add(prodQty);
	
	prodDesc = new JTextField();
	prodDesc.setBounds(158, 295, 176, 32);
	prodDesc.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	prodDesc.setColumns(10);
	panel.add(prodDesc);
	
//	prodCat = new JTextField();
//	prodCat.setBounds(328, 473, 176, 32);
//	prodCat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
//	prodCat.setColumns(10);
//	panel.add(prodCat);
	
	JButton btnEdit = new JButton("Edit");
	btnEdit.setBounds(139, 410, 85, 41);
	btnEdit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(prodId.getText().isEmpty() || prodName.getText().isEmpty() || prodQty.getText().isEmpty() || prodDesc.getText().isEmpty() || prodCat.getSelectedItem().equals(null)) {
				 JOptionPane.showMessageDialog(null,"Missing Information");
			}
			else {
				try {
					con=DriverManager.getConnection(url,user,pwd);
					String UpdateQuery="update productdb set ProductName='"+prodName.getText()+"'"+",Quantity="+prodQty.getText()+""+",Description='"+prodDesc.getText()+"'"+",Category='"+prodCat.getSelectedItem()+"'"+" where ProductId="+prodId.getText();
					Statement Add=con.createStatement();
					Add.executeUpdate(UpdateQuery);
					JOptionPane.showMessageDialog(null,"Product Updated Successfully");
					displayIntoTable();
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
	btnDelete.setBounds(249, 410, 85, 41);
	btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			 try {
				 String prodid;
					prodid= JOptionPane.showInputDialog ("Enter Product ID to be Deleted : ");
					int id= Integer.parseInt(prodid);
				 con=DriverManager.getConnection(url,user,pwd); 
				 String Query="Delete from productdb where ProductId="+id;
				 Statement Add=con.createStatement();
				 Add.executeUpdate(Query);
				 displayIntoTable();
				 JOptionPane.showMessageDialog(null,"Product Deleted Successfully");
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
	btnHome.setBounds(139, 468, 85, 41);
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
	scrollPane.setBounds(400, 176, 564, 248);
	scrollPane.setFont(new Font("Century Gothic", Font.BOLD, 15));
	panel.add(scrollPane);
	
	ProductTable = new JTable();
	ProductTable.setRowHeight(21);
	ProductTable.setRowMargin(3);
	ProductTable.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			DefaultTableModel model=(DefaultTableModel)ProductTable.getModel();
			int Myindex=ProductTable.getSelectedRow();
			prodId.setText(model.getValueAt(Myindex,0).toString());
			prodName.setText(model.getValueAt(Myindex,1).toString());
			prodQty.setText(model.getValueAt(Myindex,2).toString());
			prodDesc.setText(model.getValueAt(Myindex,3).toString());
			prodCat.setSelectedItem(model.getValueAt(Myindex,4).toString());
		}
	});
	ProductTable.setColumnSelectionAllowed(true);
	ProductTable.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	ProductTable.setCellSelectionEnabled(true);
	ProductTable.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Product Id", "Name", "Quantity", "Description", "Category"
		}
	));
	scrollPane.setViewportView(ProductTable);
	
	JLabel lblProductList = new JLabel("Product List");
	lblProductList.setBounds(497, 128, 323, 38);
	lblProductList.setHorizontalAlignment(SwingConstants.CENTER);
	lblProductList.setForeground(new Color(220, 20, 60));
	lblProductList.setFont(new Font("Century Gothic", Font.BOLD, 25));
	panel.add(lblProductList);
	
	JPanel panel_2 = new JPanel();
	panel_2.setBounds(0, 576, 1000, 24);
	panel_2.setBackground(new Color(220, 20, 60));
	panel.add(panel_2);
	
	
	prodIdLabelError = new JLabel("*Product Id is required");
	prodIdLabelError.setBounds(202, 168, 193, 13);
	prodIdLabelError.setVisible(false);
	prodIdLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	prodIdLabelError.setForeground(Color.RED);
	panel.add(prodIdLabelError);
	
	prodNameLabelError = new JLabel("*Name is required");
	prodNameLabelError.setBounds(202, 219, 193, 13);
	prodNameLabelError.setVisible(false);
	prodNameLabelError.setForeground(Color.RED);
	prodNameLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	panel.add(prodNameLabelError);
	
	prodQtyLabelError = new JLabel("*Quantity is required");
	prodQtyLabelError.setBounds(202, 272, 193, 13);
	prodQtyLabelError.setVisible(false);
	prodQtyLabelError.setForeground(Color.RED);
	prodQtyLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	panel.add(prodQtyLabelError);
	
	prodDescLabelError = new JLabel("*Description is required");
	prodDescLabelError.setBounds(197, 331, 193, 13);
	prodDescLabelError.setVisible(false);
	prodDescLabelError.setForeground(Color.RED);
	prodDescLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	panel.add(prodDescLabelError);
	
	prodCatLabelError = new JLabel("*Choose Category");
	prodCatLabelError.setBounds(197, 387, 193, 13);
	prodCatLabelError.setVisible(false);
	prodCatLabelError.setForeground(Color.RED);
	prodCatLabelError.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	panel.add(prodCatLabelError);
		
	JButton Addbtn = new JButton("Add ");
	Addbtn.setBounds(29, 410, 85, 41);
	Addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				boolean b;
				//Validation for Product Id 
				 b = Pattern.compile("^[0-9]$").matcher(prodId.getText()).matches(); 
				 if(b==true) 
				 { 
					 prodIdLabelError.setVisible(false);
					
				 }
				 else 
				 {
					 prodIdLabelError.setVisible(true);

				 }
				 
				//Validation for Product Name
				 b =Pattern.compile("^[A-Za-z0-9_-]*$").matcher(prodName.getText()).matches(); 
				 if(b==true) 
				 { 
					 prodNameLabelError.setVisible(false);
				 }
				 else {
					 prodNameLabelError.setVisible(true);

				 }
				 
				//Validation for Product Quantity
				 b =Pattern.compile("^[0-9]{1,50}$").matcher(prodQty.getText()).matches(); 
				 if(b==true) { 
					 prodQtyLabelError.setVisible(false);
						}
				 else {
					 prodQtyLabelError.setVisible(true);
				 	}
				 
				//Validation for Product Description
				 b =Pattern.compile("^[A-Za-z0-9_\\s]*$").matcher(prodDesc.getText()).matches(); 
				 if(b==true)
				 { 
					 
					 prodDescLabelError.setVisible(false);
				}
				 else {
					 prodDescLabelError.setVisible(true);

				 }
				 
				//Validation for Product Category
				 
				if(prodCat.getSelectedItem()== null)
				 { 
					 prodCatLabelError.setVisible(true);
					}
				 else
				 {
					 prodCatLabelError.setVisible(false);

				 }
				 
				 if(prodId.getText().equals(null) && prodName.getText().equals(null) &&  prodQty.getText().equals(null) && prodDesc.getText().equals(null) && prodCat.getSelectedItem().equals(null)  )
	        	 { 
        		     if(prodIdLabelError.isVisible() && prodNameLabelError.isVisible() && prodQtyLabelError.isVisible() &&  prodDescLabelError.isVisible() && prodCatLabelError.isVisible()) 
	        		   {

		        	 JOptionPane.showMessageDialog(null,"Not Submitted");
	        		   }		      
        		 }				
        		 else
        		 {
        			 ArrayList<String> al=new ArrayList<String>();
					 		 al.add(prodId.getText());
					         al.add(prodName.getText());
					         al.add(prodQty.getText());
					         al.add(prodDesc.getText());
					         al.add((String) prodCat.getSelectedItem());
					        
				        	 if(!prodId.getText().equals(null) && !prodName.getText().equals(null) &&  !prodQty.getText().equals(null) && !prodDesc.getText().equals(null) && !prodCat.getSelectedItem().equals(null))
				        	 { 
				        		 if(!prodIdLabelError.isVisible() && !prodNameLabelError.isVisible() && !prodQtyLabelError.isVisible() &&  !prodDescLabelError.isVisible() && !prodCatLabelError.isVisible()) 
				        		   {

						    	 ProductDB pb=new ProductDB();
						    	 pb.insertIntoReg(al);
							     JOptionPane.showMessageDialog(null,"Product Added Successfully ");
							     displayIntoTable();
				        		   }
				        	 else 
			        		 {
			        			 JOptionPane.showMessageDialog(null,"Product is not added.!!");			        		 
		        		   }
		        		 }		        		
        		 }
			}
		});
		Addbtn.setFont(new Font("Century Gothic", Font.BOLD, 16));
		Addbtn.setForeground(Color.WHITE);
		Addbtn.setBorderPainted(false);
		Addbtn.setBackground(new Color(220, 20, 60));
		panel.add(Addbtn);
		
		prodCat = new JComboBox();
		prodCat.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		prodCat.setBounds(151, 350, 183, 34);
		panel.add(prodCat);
		setUndecorated(true);
	} 
}