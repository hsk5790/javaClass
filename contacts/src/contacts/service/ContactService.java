package contacts.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import contacts.dao.ContactDao;
import contacts.dto.ContactDto;

public class ContactService {
//1. 연락처 추가
	public boolean ContactAdd(Scanner scan) {
		boolean result = false;

		ContactDto contactDto = new ContactDto();
		ContactDao contactDao = new ContactDao();

		String personnm = null;
		String phoneno = null;
		String address = null;
		int gubunno = 0;
		
// 전화번호 => 앞3자리 숫자(숫자로 시작), 하이픈, 중간자리 3~4자리 숫자, 하이픈, 마지막 4자리 숫자 인지 체크       
		String pattern = "^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$";

		do {
			System.out.print("전화번호 입력:");
			phoneno = scan.next();
//			패턴에 맞는지 확인 
			if (!Pattern.matches(pattern, phoneno)) {
				System.out.println("다시 입력해 주세요");
			}else {
//			전화번호 중복여부 확인 
				if (!contactDao.isNoSerach(phoneno)) {
					System.out.println("중복된 전화번호가 없습니다.");
					contactDto.setPhoneno(phoneno);
					break;
				} else {
					System.out.println("이미 등록된 전화번호 입니다.");
				}
			}
			
		} while (true);

		System.out.print("이름:");
		personnm = scan.next();	
//		공백 제거
		personnm.replace(" ", "");
		if (personnm != null && personnm != "") {
			System.out.println("정상입력");
			contactDto.setPersonnm(personnm);
		} else {
			System.out.println("처리실패");
		}

		System.out.print("주소:");
		address = scan.next();
//		공백제거 
		address.replace(" ", "");
		contactDto.setAddress(address);

		try {
			System.out.print("구분번호:1.가족, 2.친구 3.회사  4.기타");
			gubunno = scan.nextInt();
//			입력받은 값이 숫자 1,2,3,4 인지 확인 
			if (gubunno == 1 || gubunno == 2 || gubunno == 3 || gubunno == 4) {
				contactDto.setGubunno(gubunno);
			}else {
				System.out.println("잘못입력하셨습니다.");
			}
		} catch (InputMismatchException e) {
			System.out.println("잘못입력하셨습니다.");
			e.printStackTrace();
		}

		result = contactDao.contactAdd(contactDto);
		return result;
	}

//2. 연락처 전체 조회 
	public ArrayList<ContactDto> showSearchAll() {
		ArrayList<ContactDto> contacts = new ArrayList<ContactDto>();
		ContactDao contactDao = new ContactDao();
		contacts = contactDao.showContactAll();
		return contacts;
	}

//3. 연락처 수정
	public ArrayList<ContactDto> contactSearchByName(Scanner scan) {
		ArrayList<ContactDto> contact = new ArrayList<ContactDto>();
//		검색할 이름 입력 받기
		System.out.print("검색할 이름을 입력하세요");
		String name = scan.next();
//		검색결과를 받아서  DAO 에서 SELECT 한 결과를 받아옴  contact에 저장 
		ContactDao contactDao = new ContactDao();
		contact = contactDao.contactSearchByName(name);

		return contact;
	}

	public boolean contactUpdate(ArrayList<ContactDto> contact, Scanner scan) {
		boolean result = false;
		System.out.println("수정할 연락처의 번호를 선택하세요");
		int i = 0;
		i = scan.nextInt();
			    
		String newPersonnm = null;
		String newPhoneno = null;
		String newAddress = null;
		int newGubunno = 0;
		String pattern = "^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$";

		ContactDao contactDao = new ContactDao();
		ContactDto contactDto = new ContactDto();
		
		
		System.out.print("이름:");
		newPersonnm = scan.next();
		contact.get(i-1).setPersonnm(newPersonnm);

		
//		전화번호 패턴 확인 
		do {
			System.out.print("전화번호:");
			newPhoneno = scan.next();
			if(!Pattern.matches(pattern, newPhoneno)) {
				System.out.println("다시 입력해 주세요.");
							
			} else {
				contact.get(i-1).setPhoneno(newPhoneno);
			    break;
			}
		} while(true);
	
		
		System.out.print("주소:");
		newAddress = scan.next();
		contact.get(i-1).setAddress(newAddress);	
		
		System.out.print("구분(1.가족, 2.친구, 3.가족  4.기타):");
		do{
			try {
				newGubunno = scan.nextInt();		
				
				if (newGubunno == 1 || newGubunno ==2 || newGubunno == 3 || newGubunno == 4) {
					contact.get(i-1).setGubunno(newGubunno);
					break;
				}else {
					System.out.println("잘못입력하셨습니다.");
					break;
				}			
				
			} catch(InputMismatchException e){
				e.printStackTrace();
				System.out.println("잘못입력하셨습니다.");
				break;
			}			
		}while(true);
		

		result =contactDao.contactUpdate(contact.get(i-1));

		return result;
	}
//4. 연락처 삭제	
	public boolean contactDelete(ArrayList<ContactDto> contact, Scanner scan) {
		boolean result = false;
		int i = 0;
		System.out.println("삭제할 연락처의 번호를 선택하세요.");
		i = scan.nextInt();		
		
		ContactDao contactDao = new ContactDao();	
	    result = contactDao.contactDelete(contact.get(i-1));	 
		
		return result;
	}
}
