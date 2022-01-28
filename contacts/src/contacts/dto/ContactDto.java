package contacts.dto;

public class ContactDto {
	private int person_id;
    private String personnm;
    private String phoneno;
    private String address;
    private int gubunno;
    private String gubunnm;
	
	
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public String getPersonnm() {
		return personnm;
	}
	public void setPersonnm(String personnm) {
		this.personnm = personnm;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getGubunno() {
		return gubunno;
	}
	public void setGubunno(int gubunno) {
		this.gubunno = gubunno;
	}		
	public String getGubunnm() {
		return gubunnm;
	}
	public void setGubunnm(String gubunnm) {
			this.gubunnm = gubunnm;
	}	
	
	@Override
	public String toString() {
		return "번호:" + person_id + ", 이름:" + personnm + ", 전화번호:" + phoneno + ", 주소:"
				+ address + ", 구분:" + gubunnm;
	}
	
		
	}
    
    

