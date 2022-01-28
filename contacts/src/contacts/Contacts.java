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
			System.out.println("���� �޴� �� �ϳ��� ������ �ּ���.");
			for (int j = 0; j < 25; j++) {
				System.out.print("=");
			}
			System.out.print("\n");
			System.out.println("1.ȸ�� �߰�");
			System.out.println("2.ȸ�� ��� ����");
			System.out.println("3.ȸ�� ���� �����ϱ�");
			System.out.println("4.ȸ�� ����");
			System.out.println("5.����");
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
	
// 1. ����ó �߰� 
	public static void addContact() {
//	���� ����
		Connection conn = null;
		PreparedStatement pstmt = null;	
				
		String sql = "insert into contact values(?,?,?,?)";
		
		conn = getConnection();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�̸�: ");
		String personnm = sc.next();
		System.out.println("��ȭ��ȣ: ");
		String phone = sc.next();
		System.out.println("�ּ�");
		String address = sc.next();
		System.out.println("����");
	    String gubunno = sc.next();
	    	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, personnm);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, gubunno);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("���������� �߰��Ǿ����ϴ�.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		    System.out.println("����ó �߰� ����");
		} finally {
			close(pstmt);
			close(conn);
		}
		
		
	}
// 2. ����ó ��� ȭ�� ���
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
				System.out.print("�̸�: " + rs.getString("personnm"));
				System.out.print("|��ȭ��ȣ: " + rs.getString("phone"));
				System.out.print("|�ּ�: " + rs.getString("address"));
				System.out.println("|����: " + rs.getString("gubunnm"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		
	}
// 3. ����ó ����	

// 4. ����ó ���� 	

}
