package com.jsp.jdbc_crud_with_preparedstatement.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class ProductController {
public static void main(String[] args) {
	Scanner sc =new Scanner(System.in);
	Connection connection=null;
	try {
		//step-1 register driver
		Driver driver =new Driver();
		DriverManager.registerDriver(driver);
		
		//step-2 create connection
		String url="jdbc:mysql://localhost:3306/firstassignment";
		String user = "root";
		String pass = "Ha@210903";
		
		connection = DriverManager.getConnection(url, user, pass);
		
		//step-3
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	char c;
	do{System.out.println("Enter your option:-\n1.Insert\n2.Delete\n3.Display\n4.DisplayBYId\n5.Update\n6.Exit");
	int option=sc.nextInt();
	switch(option) {
	case 1:{
		System.out.print("name:- ");
		String name=sc.next();
		sc.nextLine();
		System.out.print("color:- ");
		String color=sc.next();
		System.out.print("price:- ");
		double price=sc.nextDouble();
		System.out.println("Enter expiry date:- ");
		System.out.print("year:- ");
		int year=sc.nextInt();
		System.out.print("month:- ");
		int month=sc.nextInt();
		System.out.print("day:- ");
		int day=sc.nextInt();
		
		
		String inser_product_query="insert into product(name,color,price,exp_date) values(?,?,?,?)";
		 try {
			 PreparedStatement preparedStatement= connection.prepareStatement(inser_product_query);
			 preparedStatement.setString(1,name);
			 preparedStatement.setString(2, color);
			 preparedStatement.setDouble(3,price);
			 preparedStatement.setObject(4, LocalDate.of(year, month, day));
			 preparedStatement.execute();
			 System.out.println("Data stored successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	}
	case 2:{
		System.out.print("Enter id of data that will be delete:- ");
		int id=sc.nextInt();
		String delete_query="delete from product where id=?";
		try {
			PreparedStatement ps= connection.prepareStatement(delete_query);
			ps.setInt(1, id);
			int a=ps.executeUpdate();
			if(a!=0)	
				System.out.println("data deleted successfully");
			else
				System.out.println("Data has not deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	}
	case 3:{
		String display="select *from product";
		try {
			PreparedStatement ps= connection.prepareStatement(display);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+rs.getObject(5));
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	}
	case 4:{
		System.out.print("Enter Product id:- ");
		int id=sc.nextInt();
		String displayId="select * from product where id=?";
		try {
			PreparedStatement ps= connection.prepareStatement(displayId);
			ps.setInt(1, id);
			ResultSet rs= ps.executeQuery();
			if(rs.next())
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+rs.getObject(5));
			else
				System.out.println("youe entered wrong id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		break;
	}
	case 5:{
		System.out.print("Enter your id:- ");
		int id=sc.nextInt();
		System.out.println("what you want to update:-\n1.name\n2color\n3.price\n4.expiry date");
		int n=sc.nextInt();
		switch(n) {
		case 1:{
			System.out.print("Enter new name:- ");
			String name=sc.next();
			String update="update product set name=? where id =?";
			try {
				PreparedStatement ps=connection.prepareStatement(update);
				ps.setString(1, name);
				ps.setInt(2, id);
				int r=ps.executeUpdate();
				System.out.println(r==1?"Data Updated":"Data is not updated or wrong id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 2:{
			System.out.print("Enter new color:- ");
			String color=sc.next();
			String update="update product set color=? where id =?";
			try {
				PreparedStatement ps=connection.prepareStatement(update);
				ps.setString(1, color);
				ps.setInt(2, id);
				int r=ps.executeUpdate();
				System.out.println(r==1?"Data Updated":"Data is not updated or wrong id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 3:{
			System.out.print("Enter new price:- ");
			double price=sc.nextDouble();
			String update="update product set price=? where id =?";
			try {
				PreparedStatement ps=connection.prepareStatement(update);
				ps.setDouble(1, price);
				ps.setInt(2, id);
				int r=ps.executeUpdate();
				System.out.println(r==1?"Data Updated":"Data is not updated or wrong id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case 4:{
			System.out.print("Enter new expiry year:- ");
			int year=sc.nextInt();
			System.out.print("Enter new expiry month:- ");
			int month=sc.nextInt();
			System.out.print("Enter new expiry day:- ");
			int day=sc.nextInt();
			String update="update product set exp_date=? where id =?";
			try {
				PreparedStatement ps=connection.prepareStatement(update);
				ps.setObject(1, LocalDate.of(year, month, day));
				ps.setInt(2, id);
				int r=ps.executeUpdate();
				System.out.println(r==1?"Data Updated":"Data is not updated or wrong id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		default: {
			System.out.println("Enter correct option>>>");
		}
		}
	break;}
	case 6:{
		System.exit(0);
	}
	default:{
		System.out.println("Enter correct option>>>");
	}
	}
	System.out.println("Press Y/y for more DBMS operation:- ");
	c=sc.next().charAt(0);}while(c=='Y' ||c=='y');
	System.out.println("<< Program ends >>");
}
}
