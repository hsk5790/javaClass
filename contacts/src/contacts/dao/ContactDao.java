package contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import contacts.JDBCTemplate;
import contacts.dto.ContactDto;

public class ContactDao extends JDBCTemplate {
//1. ����ó �ߺ����� Ȯ�� => select, ����ó�� �˻�
//1.1 �ߺ����� boolean���� dto���� service�� ����	
	public boolean isNoSerach(String phoneno) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT c.PERSON_ID, c.PERSONNM , c.PHONENO " +
					 "  FROM contacts c            			     " + 
				     " WHERE c.PHONENO = ?                       ";

		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phoneno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return result;
	}

//2. ����ó �߰� => insert PERSON_ID, PERSONNM, PHONENO, GUNBUNNO
	public boolean contactAdd(ContactDto contactDto) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "INSERT INTO CONTACTS VALUES (CONTACTS_SEQ.NEXTVAL,?,?,?,?)";

		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, contactDto.getPersonnm());
			pstmt.setString(2, contactDto.getPhoneno());
			pstmt.setString(3, contactDto.getAddress());
			pstmt.setInt(4, contactDto.getGubunno());

			int rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return result;
	}

//3. ����ó ��ü���� 
	public ArrayList<ContactDto> showContactAll() {
		ArrayList<ContactDto> contact = new ArrayList<ContactDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT c.PERSON_ID		  " +
					 "     , c.PERSONNM           " + 
				     "     , c.PHONENO			  " + 
					 "     , c.ADDRESS			  " + 
				     "     , g.GUBUNNM			  " + 
					 "  FROM CONTACTS c			  " + 
				     "     , GUBUN g			  " + 
					 " WHERE c.GUBUNNO = g.GUBUNNO" + 
				     " ORDER BY c.PERSON_ID       ";

		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ContactDto contactDto = new ContactDto();
//DTO object�� DB���� ������ �� ���� 
				contactDto.setPerson_id(rs.getInt("PERSON_ID"));
				contactDto.setPersonnm(rs.getString("PERSONNM"));
				contactDto.setPhoneno(rs.getString("PHONENO"));
				contactDto.setAddress(rs.getString("ADDRESS"));
				contactDto.setGubunnm(rs.getString("GUBUNNM"));
				contact.add(contactDto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}

		return contact;
	}

//4. ����ó ����
//4.1 ����ó �̸����� �˻� 
	public ArrayList<ContactDto> contactSearchByName(String name) {
		ArrayList<ContactDto> contact = new ArrayList<ContactDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT  C.PERSON_ID					" + 
					 "      , c.PERSONNM					" + 
				     "      , c.PHONENO						" + 
					 "      , c.ADDRESS						" + 
				     "      , g.GUBUNNM						" + 
					 "   FROM CONTACTS c					" +
				     "       ,GUBUN g						" + 
					 "  WHERE c.GUBUNNO = g.GUBUNNO			" +
				     "    AND c.PERSONNM LIKE '%' ||?|| '%' ";
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ContactDto contactDto = new ContactDto();
				contactDto.setPerson_id(rs.getInt("PERSON_ID"));
				contactDto.setPersonnm(rs.getString("PERSONNM"));
				contactDto.setPhoneno(rs.getString("PHONENO"));
				contactDto.setAddress(rs.getString("ADDRESS"));
				contactDto.setGubunnm(rs.getString("GUBUNNM"));
				contact.add(contactDto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return contact;
	}

	public boolean contactUpdate(ContactDto contactDto) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
// CONTACTS table�� pk�� PERSON_ID�� �������� ROW�� SELECT�ؼ� UPDATE�� 
		String sql = "UPDATE CONTACTS c		    " + 
		             " SET c.PERSONNM = ?		" +
				     "    ,c.PHONENO = ?		" +
		             "    ,c.ADDRESS = ?		" +
				     "    ,c.GUBUNNO = ?		" + 
		             " WHERE c.PERSON_ID = ?    ";
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
//			ArrayList���� ������ ����ó�� ���� dto�� ���޹��� => �÷����� get�ؼ� update�� 
			pstmt.setString(1, contactDto.getPersonnm());
			pstmt.setString(2, contactDto.getPhoneno());
			pstmt.setString(3, contactDto.getAddress());
			pstmt.setInt(4, contactDto.getGubunno());
			pstmt.setInt(5, contactDto.getPerson_id());

			int rowCnt = pstmt.executeUpdate();

			if (rowCnt > 0) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(conn);
		}
		return result;

	}
//5. ����ó ���� 
    public boolean contactDelete(ContactDto contactDto) {
    	boolean result		    = false;
    	Connection conn 		= null;
    	PreparedStatement pstmt = null;
    	ResultSet rs 			= null;
    	
    	String sql = "DELETE FROM CONTACTS c" + 
    			     " WHERE c.PERSON_ID = ?";
//    	DTO�� ���޹��� �� �� PK�� PERSON_ID ���� ������
    	conn = getConnection();
    	try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, contactDto.getPerson_id());
			
			int rowCnt = pstmt.executeUpdate();
		
			if (rowCnt > 0) {
				result = true;
			}	
						
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
    	
		return result;   	
    }
}
