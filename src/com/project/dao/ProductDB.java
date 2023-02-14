package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductDB{
	private static String driver="com.mysql.jdbc.Driver"; 
	  private static String url="jdbc:mysql:///inventory_management_system";
	  private static String user="root";
	  private static String pwd=null;
	
		String query;
		Connection con=null;
		PreparedStatement ps=null;
		Statement st=null;
	public void insertIntoReg(ArrayList<String> al)
	{
		
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pwd);
			st=con.createStatement();
			query = "INSERT INTO `productdb`(`ProductId`, `ProductName`, `Quantity`, `Description`, `Category`)VALUES (?,?,?,?,?)";
			
			ps=con.prepareStatement(query);
			ps.setString(1,al.get(0));      
			ps.setString(2,al.get(1));
			ps.setString(3,al.get(2));
			ps.setString(4,al.get(3));
			ps.setString(5,al.get(4));
			ps.executeUpdate();
			}
		
			catch(Exception ex) 
			{
				JOptionPane.showMessageDialog(null,ex);
			}				   
	}
}