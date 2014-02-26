package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Test {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		Connection con = DriverManager.getConnection("jdbc:derby:wombat");
		Statement stmt = con.createStatement();
		//stmt.execute("CREATE TABLE Table1 (b VARCHAR(10))");

		stmt.execute("INSERT INTO Table1 (b) VALUES ('somevalue')");
		stmt = con.createStatement();
		stmt.execute("INSERT INTO Table1 (b) VALUES ('somevalue2')");
		stmt = con.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT b FROM Table1");
		while (rs.next()) {
			String s = rs.getString("b");
			System.out.println(s);
		}
		
		System.out.println("-------------------------------------------------------------------");
		
		Statement stmt2 = con.createStatement();
		stmt2 = con.createStatement();
		stmt2.execute("UPDATE Table1 SET b='changed' WHERE b='somevalue2'");
		ResultSet rs2 = stmt.executeQuery("SELECT b FROM Table1");
		while (rs2.next()) {
			String s2 = rs2.getString("b");
			System.out.println(s2);
		}
		
		System.out.println("-------------------------------------------------------------------");
		
		Statement stmt3 = con.createStatement();
		stmt3 = con.createStatement();
		stmt3.execute("DELETE FROM Table1 WHERE b='changed'");
		ResultSet rs3 = stmt.executeQuery("SELECT b FROM Table1");
		while (rs3.next()) {
			String s3 = rs3.getString("b");
			System.out.println(s3);
		}
	}

}
