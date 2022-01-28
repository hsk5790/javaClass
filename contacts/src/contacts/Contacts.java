package contacts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Contacts extends JDBCTemplate{

	public static void main(String[] args) {
		
		while (true) {
			for (int j = 0; j < 25; j++) {
				System.out.print("=");
			}
			System.out.print("\n");
			System.out.println("다음 메뉴 중 하나를 선택해 주세요.");
			for (int j = 0; j < 25; j++) {
				System.out.print("=");
			}
			System.out.print("\n");
			System.out.println("1.회원 추가");
			System.out.println("2.회원 목록 보기");
			System.out.println("3.회원 정보 수정하기");
			System.out.println("4.회원 삭제");
			System.out.println("5.종료");
			System.out.print("\n");	
			
			Scanner scan = new Scanner(System.in);
			int i = scan.nextInt();
	        if (i == 1) {	        
	        	addContact();	        	
	        	continue;
	        }else if (i ==2) {
	        	showContact();
	        	continue;
	        }else if (i ==3) {
	        	
	        	continue;
	        }else if (i ==4) {
	        	
	        	continue;
	        }else if (i== 5) {
	        	
	        	continue;
	        }
	        scan.close();  
		}
	
			
		}	
	
// 1. 연락처 추가 
	public static void addContact() {
//	변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;	
				
		String sql = "insert into contact values(?,?,?,?)";
		
		conn = getConnection();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("이름: ");
		String personnm = sc.next();
		System.out.println("전화번호: ");
		String phone = sc.next();
		System.out.println("주소");
		String address = sc.next();
		System.out.println("구분");
	    String gubunno = sc.next();
	    	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, personnm);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, gubunno);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("정상적으로 추가되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		    System.out.println("연락처 추가 실패");
		} finally {
			close(pstmt);
			close(conn);
		}
		
		
	}
// 2. 연락처 목록 화면 출력
	public static void showContact() {
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs 			= null;
		
		String sql = "SELECT c.PERSONNM, c.PHONE, c.ADDRESS, g.GUBUNNM" + 
					 "  FROM CONTACT c 								  " + 
					 "  	,gubun g								  " + 
					 " WHERE c.GUBUNNO = g.GUBUNNO					  ";
		
		conn = getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.print("이름: " + rs.getString("personnm"));
				System.out.print("|전화번호: " + rs.getString("phone"));
				System.out.print("|주소: " + rs.getString("address"));
				System.out.println("|구분: " + rs.getString("gubunnm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
	}
// 3. 연락처 수정	

// 4. 연락처 삭제 	

}
