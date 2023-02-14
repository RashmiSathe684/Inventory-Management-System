package com.project.service;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Order extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField BillId;
	private JTable ProductTable;
	private JTable CustomerTable;
	private JTable BillTable;
	private JLabel CstNameLbl,DateLbl,totalAmountLbl;
	private JTextField QtyBtn;
	private static String driver="com.mysql.jdbc.Driver"; 
	  private static String url="jdbc:mysql:///inventory_management_system";
	  private static String user="root";
	  private static String pwd=null;
	
		String query;
		Connection con=null;
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
	
		int i=1,Uprice,tot=0,total,flag=0,productId,oldQty;
		String prodName;
		private JTextField price;
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() 
			{
				try 
				{
					Order frame = new Order();
					frame.setVisible(true); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	public Order() {
		initcomponents();
		displayIntoTable();
		displayIntoCstTable();
		GetToday();
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
	private void GetToday() {
		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now=LocalDateTime.now();
		DateLbl.setText(dtf.format(now));
		
	}
	private void update() {
		int newQty=oldQty-Integer.valueOf(QtyBtn.getText());
		try {
			con=DriverManager.getConnection(url,user,pwd);
			String UpdateQuery="update productdb set Quantity='"+newQty+"'"+" where ProductId="+productId;
			Statement Add=con.createStatement();
			Add.executeUpdate(UpdateQuery);
			//JOptionPane.showMessageDialog(null,"Product Updated Successfully");
			displayIntoTable();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void initcomponents()
	{	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(150, 30, 1300, 750);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JPanel panel = new JPanel();
	panel.setBackground(Color.WHITE);
	panel.setBounds(0, 0, 1300, 750);
	contentPane.add(panel);
	panel.setLayout(null);
	
	JPanel panel_1 = new JPanel();
	panel_1.setBounds(0, 0, 1300, 118);
	panel_1.setBackground(new Color(220, 20, 60));
	panel.add(panel_1);
	panel_1.setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Inventory Management System");
	lblNewLabel.setBounds(446, 10, 555, 50);
	lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	lblNewLabel.setForeground(Color.WHITE);
	lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 35));
	panel_1.add(lblNewLabel);
	
	JLabel lblHomePage = new JLabel("Manage Orders");
	lblHomePage.setBounds(557, 70, 298, 38);
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
	lblNewLabel_1.setBounds(1269, 10, 21, 25);
	panel_1.add(lblNewLabel_1);
	
	JLabel lblNewLabel_01 = new JLabel("Order Id :");
	lblNewLabel_01.setBounds(37, 436, 151, 32);
	lblNewLabel_01.setFont(new Font("Century Gothic", Font.PLAIN, 20));
	lblNewLabel_01.setForeground(new Color(220, 20, 60));
	panel.add(lblNewLabel_01);
	
	BillId = new JTextField();
	BillId.setBounds(266, 438, 176, 32);
	BillId.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	panel.add(BillId);
	BillId.setColumns(10);
	
	
	
	JButton btnHome = new JButton("Home");
	btnHome.setBounds(183, 658, 98, 41);
	btnHome.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		new HomePage().setVisible(true);
		dispose();
		}
	});
	btnHome.setFont(new Font("Century Gothic", Font.BOLD, 20));
	btnHome.setForeground(Color.WHITE);
	btnHome.setBorderPainted(false);
	btnHome.setBackground(new Color(220, 20, 60));
	panel.add(btnHome);
	

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(722, 179, 564, 248);
	scrollPane.setFont(new Font("Century Gothic", Font.BOLD, 15));
	panel.add(scrollPane);
	
	ProductTable = new JTable();
	ProductTable.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			DefaultTableModel model=(DefaultTableModel)ProductTable.getModel();
			int Myindex=ProductTable.getSelectedRow();
			productId=Integer.valueOf(model.getValueAt(Myindex,0).toString());
			prodName=model.getValueAt(Myindex,1).toString();
			oldQty=Integer.valueOf(model.getValueAt(Myindex,2).toString());
			flag=1;
		}
	});
	ProductTable.setRowHeight(21);
	ProductTable.setRowMargin(3);
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
	lblProductList.setBounds(838, 135, 323, 32);
	lblProductList.setHorizontalAlignment(SwingConstants.CENTER);
	lblProductList.setForeground(new Color(220, 20, 60));
	lblProductList.setFont(new Font("Century Gothic", Font.BOLD, 25));
	panel.add(lblProductList);
	
	JPanel panel_2 = new JPanel();
	panel_2.setBounds(0, 725, 1300, 25);
	panel_2.setBackground(new Color(220, 20, 60));
	panel.add(panel_2);
	
	JScrollPane scrollPane1 = new JScrollPane();
	scrollPane1.setBounds(37, 177, 463, 237);
	scrollPane1.setFont(new Font("Century Gothic", Font.BOLD, 15));
	panel.add(scrollPane1);
	
	CustomerTable = new JTable();
	CustomerTable.setRowHeight(21);
	CustomerTable.setRowMargin(3);
	CustomerTable.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			DefaultTableModel model=(DefaultTableModel)CustomerTable.getModel();
			int Myindex=CustomerTable.getSelectedRow();
			CstNameLbl.setText(model.getValueAt(Myindex,1).toString());
		}
	});
	CustomerTable.setColumnSelectionAllowed(true);
	CustomerTable.setFont(new Font("Century Gothic", Font.PLAIN, 18));
	CustomerTable.setCellSelectionEnabled(true);
	CustomerTable.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Customer Id", "Name", "Mobile No "
		}
	));
	scrollPane1.setViewportView(CustomerTable);
	
	JScrollPane scrollPane2 = new JScrollPane();
	scrollPane2.setBounds(714, 503, 572, 167);
	scrollPane2.setFont(new Font("Century Gothic", Font.BOLD, 15));
	panel.add(scrollPane2);
	
	BillTable = new JTable();
	BillTable.setRowHeight(21);
	BillTable.setRowMargin(3);
	BillTable.setColumnSelectionAllowed(true);
	BillTable.setFont(new Font("Century Gothic", Font.PLAIN, 15));
	BillTable.setCellSelectionEnabled(true);
	BillTable.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"No", "Product", "Quantity", "Price", "Total"
		}
	));
	scrollPane2.setViewportView(BillTable);
	
		
	JButton Addbtn = new JButton("Add Orders");
	Addbtn.setBounds(37, 607, 166, 41);
	Addbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
			if(BillId.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,"Enter the Bill Id !");
			}
				else {
			
			
			try {
				con=DriverManager.getConnection(url,user,pwd);
				PreparedStatement add=con.prepareStatement("insert into orderdb values(?,?,?,?)");
				add.setInt(1,Integer.valueOf(BillId.getText()));
				add.setString(2,CstNameLbl.getText());
				add.setString(3,DateLbl.getText());
				add.setInt(4,Integer.valueOf(totalAmountLbl.getText()));
				//add.setInt(4,Integer.valueOf(totalAmountLbl.getText()));
			
				int row=add.executeUpdate();
				if(row>0) {
				JOptionPane.showMessageDialog(null,"Order Successfully Added");
				}
				con.close();
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		}
		
	});
		Addbtn.setFont(new Font("Century Gothic", Font.BOLD, 20));
		Addbtn.setForeground(Color.WHITE);
		Addbtn.setBorderPainted(false);
		Addbtn.setBackground(new Color(220, 20, 60));
		panel.add(Addbtn);
		
		JLabel lblProductList_1 = new JLabel("Customer List");
		lblProductList_1.setBounds(78, 135, 323, 32);
		lblProductList_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductList_1.setForeground(new Color(220, 20, 60));
		lblProductList_1.setFont(new Font("Century Gothic", Font.BOLD, 25));
		panel.add(lblProductList_1);
		
		JLabel lblNewLabel_01_1 = new JLabel("Customer Name :");
		lblNewLabel_01_1.setBounds(37, 484, 176, 32);
		lblNewLabel_01_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_01_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_01_1);
		
		JLabel lblNewLabel_01_1_1 = new JLabel("Date :");
		lblNewLabel_01_1_1.setBounds(37, 531, 176, 32);
		lblNewLabel_01_1_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_01_1_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_01_1_1);
		
		CstNameLbl = new JLabel("CstName");
		CstNameLbl.setBounds(266, 486, 176, 32);
		CstNameLbl.setForeground(new Color(220, 20, 60));
		CstNameLbl.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(CstNameLbl);
		
		DateLbl = new JLabel("Date");
		DateLbl.setBounds(266, 531, 204, 32);
		DateLbl.setForeground(new Color(220, 20, 60));
		DateLbl.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(DateLbl);
		
		JButton btnViewOrders = new JButton("View Orders");
		btnViewOrders.setBounds(266, 607, 166, 41);
		btnViewOrders.setForeground(Color.WHITE);
		btnViewOrders.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnViewOrders.setBorderPainted(false);
		btnViewOrders.setBackground(new Color(220, 20, 60));
		panel.add(btnViewOrders);
		
		JLabel lblNewLabel_01_2 = new JLabel("Quantity : ");
		lblNewLabel_01_2.setBounds(778, 436, 105, 32);
		lblNewLabel_01_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_01_2.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_01_2);
		
		QtyBtn = new JTextField();
		QtyBtn.setBounds(893, 437, 194, 34);
		QtyBtn.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		QtyBtn.setColumns(10);
		panel.add(QtyBtn);
		
		
		JButton btnAddToOrders = new JButton("Add To Orders");
		btnAddToOrders.setBounds(1097, 432, 189, 41);
		btnAddToOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag==0 || QtyBtn.getText().isEmpty() || price.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null ,"Select Product and enter Qty!! ");
				}
				else {
					
				
				Uprice=Integer.valueOf(price.getText());
				tot=Uprice*Integer.valueOf(QtyBtn.getText());
				Vector v=new Vector();
				v.add(i);
				v.add(prodName);
				v.add(QtyBtn.getText());
				v.add(Uprice);
				v.add(tot);
				DefaultTableModel dt=(DefaultTableModel)BillTable.getModel();
				dt.addRow(v);
				total=total+tot;
				totalAmountLbl.setText(Integer.toString(total));
				update();
				i++;
		/*
		 * TABLE CREATION FOR ORDER
		 */
				}
			}
		});
		btnAddToOrders.setForeground(Color.WHITE);
		btnAddToOrders.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnAddToOrders.setBorderPainted(false);
		btnAddToOrders.setBackground(new Color(220, 20, 60));
		panel.add(btnAddToOrders);
		
		JLabel lblNewLabel_01_2_1 = new JLabel("Price : ");
		lblNewLabel_01_2_1.setBounds(555, 436, 71, 32);
		lblNewLabel_01_2_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_01_2_1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(lblNewLabel_01_2_1);
		
		price = new JTextField();
		price.setBounds(636, 437, 132, 34);
		price.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		price.setColumns(10);
		panel.add(price);
		
		totalAmountLbl = new JLabel("Amount");
		totalAmountLbl.setBounds(1046, 680, 98, 32);
		totalAmountLbl.setForeground(new Color(220, 20, 60));
		totalAmountLbl.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		panel.add(totalAmountLbl);
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.setBounds(1154, 681, 98, 32);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BillTable.print();
				}catch(Exception exp) {
					exp.printStackTrace();
				}
			}
		});
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setFont(new Font("Century Gothic", Font.BOLD, 20));
		btnPrint.setBorderPainted(false);
		btnPrint.setBackground(new Color(220, 20, 60));
		panel.add(btnPrint);
		
		JLabel lblRs = new JLabel("Rs");
		lblRs.setForeground(new Color(220, 20, 60));
		lblRs.setFont(new Font("Century Gothic", Font.PLAIN, 20));
		lblRs.setBounds(1016, 680, 20, 32);
		panel.add(lblRs);
		setUndecorated(true);
	} 
}