package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electro_eg?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertEmployee(String employeename, String phonenumber, String Employeetype,  String NIC) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into empservice(`employeeID`,`employeename`,`phonenumber`,`Employeetype`,`NIC`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, employeename);
			preparedStmt.setString(3, phonenumber);
			preparedStmt.setString(4, Employeetype);
			preparedStmt.setString(5, NIC);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readEmployee() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Employee ID</th><th>Employee Name</th><th>Phone Number</th><th>Employee Type</th><th>NIC</th></tr>";
			String query = "select * from empservice";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String employeeID = Integer.toString(rs.getInt("employeeID"));
				String employeename = rs.getString("employeename");
				String phonenumber = rs.getString("phonenumber");
				String Employeetype = rs.getString("Employeetype");
				String NIC = rs.getString("NIC");

				// Add into the html table
				output += "<tr><td>" + employeeID + "</td>";
				output += "<td>" + employeename + "</td>";
				output += "<td>" + phonenumber + "</td>";
				output += "<td>" + Employeetype + "</td>";
				output += "<td>" + NIC + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateEmployee(String employeeID, String employeename, String phonenumber, String Employeetype, String NIC) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE empservice SET employeename=?,phonenumber=?,Employeetype=?,NIC=?" + "WHERE employeeID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, employeename);
			preparedStmt.setString(2, phonenumber);
			preparedStmt.setString(3, Employeetype);
			preparedStmt.setString(4, NIC);
			preparedStmt.setInt(5, Integer.parseInt(employeeID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the employee.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteEmployee(String employeeID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from empservice where employeeID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(employeeID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the employee.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
