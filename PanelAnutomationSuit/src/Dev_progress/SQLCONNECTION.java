package Dev_progress;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLCONNECTION {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("test");
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
		System.out.println("test1");
		Connection conn = DriverManager.getConnection("jdbc:sqlserver://he05ik8udk.database.windows.net;user=Rezstaging;password=Staging@123;database=redcoredblive28may16_test");
		System.out.println("test3");
		Statement sta = conn.createStatement();
		String Sql = "select * from RIOccupancyBasedRuleSetup";
		ResultSet rs = sta.executeQuery(Sql);
		while (rs.next()) {
			System.out.println(rs.getRow());
		}

	}

}
