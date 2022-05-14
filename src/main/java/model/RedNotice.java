package model; 
import java.sql.*; 


public class RedNotice 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/RedNotice", "root", "admin"); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readRedNotice() 
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Account ID</th>" 
		 +"<th>Customer Name</th><th>Charges</th>"
		 +"<th>Due date</th>" 
 + "<th>Update</th><th>Remove</th></tr>"; 
 String query = "select * from RedNotice"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String AccountCode = Integer.toString(rs.getInt("AccountCode")); 
 String AccountID = rs.getString("AccountID"); 
 String CustomerName = rs.getString("CustomerName"); 
 String Charges = Double.toString(rs.getDouble("Charges")); 
 String Duedate = rs.getString("Duedate"); 
 
 // Add into the html table
 output += "<tr><td>" + AccountID + "</td>"; 
 output += "<td>" + CustomerName + "</td>"; 
 output += "<td>" + Charges + "</td>"; 
 output += "<td>" + Duedate + "</td>"; 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-AccountCode='" + AccountCode + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-AccountCode='" + AccountCode + "'></td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the RedNotice."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertRedNotice(String AccountID, String CustomerName, 
		 String Charges, String Duedate) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into RedNotice (`AccountCode`,`AccountID`,`CustomerName`,`Charges`,`Duedate`)"
		 + " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, AccountID); 
		 preparedStmt.setString(3, CustomerName); 
		 preparedStmt.setDouble(4, Double.parseDouble(Charges)); 
		 preparedStmt.setString(5, Duedate); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newRedNotice = readRedNotice(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newRedNotice + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the RedNotice.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updateRedNotice(String AccountCode, String AccountID, String CustomerName, 
		 String Charges, String Duedate) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE RedNotice SET AccountID=?,CustomerName=?,Charges=?,Duedate=? WHERE AccountCode=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, AccountID); 
		 preparedStmt.setString(2, CustomerName); 
		 preparedStmt.setDouble(3, Double.parseDouble(Charges)); 
		 preparedStmt.setString(4, Duedate); 
		 preparedStmt.setInt(5, Integer.parseInt(AccountCode)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newRedNotice = readRedNotice(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newRedNotice + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the RedNotice.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deleteRedNotice(String AccountCode) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from RedNotice where AccountCode=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(AccountCode)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newRedNotice = readRedNotice(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newRedNotice + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the RedNotice.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


