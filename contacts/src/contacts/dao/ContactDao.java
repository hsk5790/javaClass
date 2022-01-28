package contacts.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import contacts.JDBCTemplate;
import contacts.dto.ContactDto;

public class ContactDao extends JDBCTemplate {
//1. 연락처 중복여부 확인 => select, 연락처로 검색
//1.1 중복여부 boolean으로 dto통해 service로 전송	
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

//2. 연락처 추가 => insert PERSON_ID, PERSONNM, PHONENO, GUNBUNNO
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

//3. 연락처 전체보기 
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
//DTO object에 DB에서 가져온 값 저장 
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

//4. 연락처 수정
//4.1 연락처 이름으로 검색 
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
// CONTACTS table의 pk인 PERSON_ID를 기준으로 ROW를 SELECT해서 UPDATE함 
		String sql = "UPDATE CONTACTS c		    " + 
		             " SET c.PERSONNM = ?		" +
				     "    ,c.PHONENO = ?		" +
		             "    ,c.ADDRESS = ?		" +
				     "    ,c.GUBUNNO = ?		" + 
		             " WHERE c.PERSON_ID = ?    ";
		conn = getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
//			ArrayList에서 선택한 연락처의 값을 dto로 전달받음 => 컬럼값을 get해서 update함 
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
//5. 연락처 삭제 
    public boolean contactDelete(ContactDto contactDto) {
    	boolean result		    = false;
    	Connection conn 		= null;
    	PreparedStatement pstmt = null;
    	ResultSet rs 			= null;
    	
    	String sql = "DELETE FROM CONTACTS c" + 
    			     " WHERE c.PERSON_ID = ?";
//    	DTO로 전달받은 것 중 PK인 PERSON_ID 값을 가져옴
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
