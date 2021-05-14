package sqliteUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.CourseInfo;

public class DataBaseUtil {
	
	
	/* 
	 tableFormat example (all Strings): 
	  
	 "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "FirstName VARCHAR(50), "
					+ "LastName VARCHAR(50), "
					+ "UserName VARCHAR(50) NOT NULL UNIQUE,"
					+ "Password VARCHAR(50) NOT NULL,"
					+ "Number VARCHAR(10) NOT NULL,"
					+ "Email VARCHAR(50) NOT NULL"
					+ ")"
	 */
	
	/* 
	 valuesAdded Format example (all Strings):
	  
		String.format("\'%s\', \'%s\', \'%s\'", val1, val2, val3);
	 */
	
	/*
	 query Format example:
	 	
	 	String.format("%s = \'%s\' and "%s = \'%s\'", col1Name, val1, col2Name, val2);
	 
	 */
	
	private static String COURSE_FORMAT =  "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ "TERM VARCHAR(100) NOT NULL UNIQUE, "
	+ "TEXT_CONTENT VARCHAR(100) NOT NULL, "
	+ "TEXT VARCHAR(50)"
	+ ")";
	
	public static boolean tableExists(String dbName, String tableName) {
		Connection connection = null;
		try {
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
			Statement statement = connection.createStatement();	
			statement.setQueryTimeout(30);	
			
			DatabaseMetaData dbm = connection.getMetaData();
			// check if "employee" table is there
			ResultSet tables = dbm.getTables(null, null, tableName, null);
			if (tables.next() || tables.isBeforeFirst()) {
			  // Table exists
				return true;
			}
			else {
//				System.out.println("table not found");
				return false;
			}
			
		} catch(Exception e) {
			return false;
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
	}
	
	public static String createTableIfAbsent(String dbName, String tableName, String tableFormat) {
		if (tableExists(dbName, tableName)) {
			System.out.println("Table currently exists at specified location");
			return null;
		}
		Connection connection = null;
		try {
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
			Statement statement = connection.createStatement();	
			statement.setQueryTimeout(30);	
			
			String sqlString = "CREATE TABLE " + tableName + " "
					+ tableFormat;
			System.out.println(sqlString);
			statement.executeUpdate(sqlString);
			
				return tableName;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
	}
	
	public static boolean dropTable(String dbName, String tableName) {
		Connection connection = null;
		try {
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
			Statement statement = connection.createStatement();	
			statement.setQueryTimeout(30);	
			
			statement.executeUpdate("DROP TABLE IF EXISTs " + tableName);	
		
		} catch(Exception e) {
//			e.printStackTrace();
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
		return !tableExists(dbName, tableName);
	}
	
	public static boolean resetTable(String dbName, String tableName) {
		if (tableExists(dbName, tableName)) {
			dropTable(dbName, tableName);
			createTableIfAbsent(dbName,tableName, COURSE_FORMAT);
		} else {
			createTableIfAbsent(dbName,tableName, COURSE_FORMAT);
		}
		
		return tableExists(dbName, tableName);
	}

	// make changes as needed: Result Set cannot be returned after connection is closed
	public static void search(String dbName, String tableName, String query) {
		Connection connection = null;
		
		try {
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
			Statement statement = connection.createStatement();	
			statement.setQueryTimeout(30);	
			
			
			ResultSet rs = statement.executeQuery(new QueryFormatter(tableName, query).sqlQueryAllString());
				while(rs.next()) {
					System.out.println(rs.getString("CustomerOrder") + " $" + rs.getString("Price"));
				}
//				String []
				return;
		} catch(Exception e) {
			return;
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
	}
	// make changes as needed: Result Set cannot be returned after connection is closed
	public static ResultSet searchValues(String dbName, String tableName, String query, String values) {
		Connection connection = null;
		
		try {
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
			Statement statement = connection.createStatement();	
			statement.setQueryTimeout(30);	
			
			
			ResultSet rs = statement.executeQuery(new QueryFormatter(tableName, query).sqlQueryValuesString(values));
			
				return rs;
		} catch(Exception e) {
			return null;
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
	}
		
	public static boolean addToTable(String dbName, String tableName, String columnNames, String valuesAdded) {
		Connection connection = null;
		try {
			// establish connection
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
				
			//create statement object from connection to interact with db
			Statement statement = connection.createStatement();	
			
			// forces program to give up connection attempt after 30s (not necessary on local db but good practice to keep)
			statement.setQueryTimeout(30);	

			statement.executeUpdate("INSERT INTO " + tableName + " "
					+ columnNames + " "
					+ "VALUES (" + valuesAdded + ")");
			
			return true;
			} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
		
		
	}

	public static boolean updateItem(String dbName, String tableName,String querySQL,  String columnToUpdate, String updatedValue) {
		Connection connection = null;
		// create update SQL template:
		String sqlUpdate = "UPDATE " + tableName + " SET " + columnToUpdate + " = ? "
				+ "WHERE " + querySQL;
				
		try {
			// establish connection
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
				
			//create statement object from connection to interact with db
			Statement statement = connection.createStatement();	
			
			// forces program to give up connection attempt after 30s (not necessary on local db but good practice to keep)
			statement.setQueryTimeout(30);	
			
			// using the SQL Update template above, create a prepared Statement
			PreparedStatement pStatement = connection.prepareStatement(sqlUpdate);
			
			// set the vars to the correct params
			pStatement.setString(1, updatedValue);
			pStatement.executeUpdate();
			
			return true;
			} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
	
		return false;
	}
	
	public static void deleteItem(String dbName, String tableName, String query) {
		Connection connection = null;
		try {
			// establish connection
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
				
			//create statement object from connection to interact with db
			Statement statement = connection.createStatement();	
			
			// forces program to give up connection attempt after 30s (not necessary on local db but good practice to keep)
			statement.setQueryTimeout(30);	

			statement.executeUpdate("DELETE FROM " + tableName + " "
					+ "WHERE " + query);
			
			} catch (SQLException e) {
//			e.printStackTrace();
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
	}

	
	
	
	public static LinkedList<CourseInfo> getAllTables(String dbName) {
		
		LinkedList<CourseInfo> tableNames = new LinkedList<CourseInfo>();
		
		Connection connection = null;
		try {
			connection = sqliteUtil.ConnectionUtil.getConnection(dbName + ".sqlite");
			Statement statement = connection.createStatement();	
			statement.setQueryTimeout(30);	
			
			DatabaseMetaData dbm = connection.getMetaData();
			// check if "employee" table is there
			ResultSet tables = dbm.getTables(null, null, null, null);
			while(tables.next()) {
				
				String name = tables.getString("TABLE_NAME");
				ResultSet rs = statement.executeQuery("select count(*) from " + name);
				rs.next();
				int numOfTerms = rs.getInt(1);
				System.out.println(name + " " + numOfTerms);
				CourseInfo ci = new CourseInfo(name,numOfTerms);
				tableNames.add(ci);
			}
			return tableNames;
		} catch(Exception e) {
			return tableNames;
		} finally {
			sqliteUtil.ConnectionUtil.closeConnection(connection);
		}
		
	}
}
