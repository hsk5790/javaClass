package contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactsEx {

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
			System.exit(0);
		}
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id ="ora_user";
		String pw ="hong";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			
			String sql = "INSERT INTO CONTACT VALUES('JAMES','010-2345-0987','Jeju','OTHERS')";
			
			stmt = conn.createStatement();
			
			int cnt = stmt.executeUpdate(sql);
			
			if (cnt >0) {
				System.out.println("정보 추가 완료 ");
			}else {
				System.out.println("정보 추가 실패 ");
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		try {
			if (stmt != null) {
				stmt.close();
			if (conn != null) {
				conn.close();
			}
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}

	}

}
