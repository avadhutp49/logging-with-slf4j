package com.spring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class SelectApp 
{

	public static Logger logger = Logger.getLogger(SelectApp.class);
	
	static {
		SimpleLayout layout =  new SimpleLayout();
		ConsoleAppender appender = new ConsoleAppender(layout);
		logger.addAppender(appender);
		logger.setLevel(Level.ALL);
		
	}
	
	public static void main(String[] args) {
			
		logger.debug("Starting Main.");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		String url="jdbc:mysql://localhost:3306/test1";
		String user="root";
		String pass="asd";	
		
		try {
			con = DriverManager.getConnection(url,user,pass);
			logger.debug("Created Connection Object");
			if(con!=null) {
				st = con.createStatement();
				logger.debug("Created Statement Object");
			}if(st!=null) {
				rs=st.executeQuery("Select eid,ename,eaddr from employee");
				logger.info("Created ResultSet Object");
			}if(rs!=null) {
				System.out.println("ID\tName\tAddress");
				System.out.println("========================");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
				}
			}
		}catch(SQLException e) {
			logger.error("SQL Exception Occured.");
		}catch(Exception e1) {
			logger.fatal("Unknown Exception Occured.");
		}finally {			
			try {
				rs.close();
				st.close();
				con.close();
				logger.debug("Resources Closed.");
			} catch (Exception e) {
				logger.fatal("Unknown Exception Occured.");
			}
		}
		logger.debug("Exiting Main.");
	}
}
