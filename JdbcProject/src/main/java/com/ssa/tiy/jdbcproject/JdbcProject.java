package com.ssa.tiy.jdbcproject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcProject {
	
		//connection variables
		
		static Connection myConn=null;
		static Statement stmt=null;
		static ResultSet rs=null;
		static String theDburl;
		static String theUser;
		static String thePassword;
		
		public static void main(String[] args) throws SQLException, FileNotFoundException, IOException{
			
			
			
			//update();
			//insert
			//insertData();
			//delete a record
			//deleteRecord();
			//fetch
			select();
			//fetchData();
			
				
		}
		public static void loadProperties() throws FileNotFoundException, IOException{
			Properties props = new Properties();
			props.load(new FileInputStream("project.properties"));
			
			theUser = props.getProperty("user");
			thePassword = props.getProperty("password");
		   theDburl = props.getProperty("dburl");
			
		}
		public static void fetchData() throws SQLException{
			try
			{
				
				
				myConn= (Connection)DriverManager.getConnection(theDburl, theUser, thePassword);/*1. get connection to db*/
									
				stmt=myConn.createStatement();/*2. Create a statement*/
		
				rs= stmt.executeQuery("select * from student");/*3. Execute SQL query (ResultSet)*/
					
				//4. Process the result set
			System.out.println("First Name " + "Last Name");
			System.out.println("++++++++++++++++++++++++++++");
			while(rs.next())
				System.out.println( rs.getString("first_name") + " " + rs.getString("last_name") +"\t\t" + rs.getDouble("gpa") );
			System.out.println("Data Fetched");
			
		}catch(Exception exc){
			exc.printStackTrace();
			
		}finally{
			MyClose(myConn,stmt,rs);
		}
	}
		public static void MyClose(Connection con, Statement stmt, ResultSet rs) throws SQLException{
			if(con!=null)
				con.close();
			if(stmt != null)
				stmt.close();
			if(rs != null)
				rs.close();
		}
		public static void update() throws SQLException{
			
			try{
				loadProperties();
				
			myConn= (Connection)DriverManager.getConnection(theDburl, theUser, thePassword);
			
			stmt= myConn.createStatement();
			
			String sql= "update student set sat=1600 where id=111";
			int rowAffected=stmt.executeUpdate(sql);
			System.out.println("Row Affected" + rowAffected);
			
			//4. Process the result set
			
		}catch(Exception ex){ex.printStackTrace();}
		finally{
			if(myConn!= null);
				myConn.close();
			if(stmt != null)
				stmt.close();
		}

	}
		
		public static void insertData() throws SQLException{
			try{
				loadProperties();
				
				myConn= (Connection)DriverManager.getConnection(theDburl, theUser, thePassword);
				
				stmt=myConn.createStatement();
				
				String query= "insert student values(111,4.0,1600, 'George', 'Washington')";
				int rowAffected = stmt.executeUpdate(query);
				System.out.println("Row Affected" + rowAffected);
				
			}catch(Exception ex){
				ex.printStackTrace();
				
			}finally{
				if(myConn!= null);
				myConn.close();
				if(stmt != null)
				stmt.close();
			}
		}
		public static void deleteRecord() throws SQLException{
			
			try{
				loadProperties();
				
				myConn= (Connection)DriverManager.getConnection(theDburl, theUser, thePassword);
				
				stmt=myConn.createStatement();
				 
				String query= "delete from student where id = 999";
				int rowAffected = stmt.executeUpdate(query);
				System.out.println("Row Affected" + rowAffected);
				
			}catch(Exception ex){
				ex.printStackTrace();
				
			}finally{
				if(myConn!= null);
				myConn.close();
				if(stmt != null)
				stmt.close();
			}
			public static void select() throws SQLException{
				
				try{
					loadProperties();
					
					myConn= (Connection)DriverManager.getConnection(theDburl, theUser, thePassword);
					
					stmt=myConn.createStatement();
					 
					String query= "select * from student where id = 111";
														
					System.out.println("First Name " + "Last Name");
					System.out.println("++++++++++++++++++++++++++++");
					while(rs.next())
						System.out.println( rs.getString("id") + " " rs.getString("first_name") + " " + rs.getString("last_name") +"\t\t" + rs.getDouble("gpa")+"\t\t" + rs.getDouble("sat")  );
					
				}catch(Exception ex){
					ex.printStackTrace();
					
				}finally{
					if(myConn!= null);
					myConn.close();
					if(stmt != null)
					stmt.close();
				}
		}
	}