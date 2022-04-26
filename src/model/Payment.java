package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electro_eg?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	public String insertPayment(String BillID, String cardID, String cardType, String Totalamount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payservice(`payment_ID`,`BillID`,`cardID`,`cardType`,`Totalamount`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, BillID);
			preparedStmt.setString(3, cardID);
			preparedStmt.setString(4, cardType);
			preparedStmt.setString(5, Totalamount);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Bill ID</th><th>Card ID</th><th>Card Type</th><th>Total Amount</th></tr>";
			String query = "select * from payservice";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String payment_ID = Integer.toString(rs.getInt("payment_ID"));
				String BillID = rs.getString("BillID");
				String cardID = rs.getString("cardID");
				String cardType = rs.getString("cardType");
				String Totalamount = Float.toString(rs.getFloat("Totalamount"));
				
				output += "<tr><td>" + payment_ID + "</td>";
				output += "<td>" + BillID + "</td>";
				output += "<td>" + cardID + "</td>";
				output += "<td>" + cardType + "</td>";
				output += "<td>" + Totalamount + "</td>";
				
			}
			con.close();
			
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String payment_ID, String BillID,String cardID, String cardType, String Totalamount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			
			String query = "UPDATE payservice SET BillID=?,cardID=?,cardType=?,Totalamount=? WHERE payment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, BillID);
			preparedStmt.setString(2, cardID);
			preparedStmt.setString(3, cardType);
			preparedStmt.setString(4, Totalamount);
			preparedStmt.setInt(5, Integer.parseInt(payment_ID));
			

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePayment(String payment_ID) {

		String output = "";
		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payservice where payment_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payment_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
