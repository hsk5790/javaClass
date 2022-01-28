package contacts.view;

import java.util.ArrayList;

import contacts.dao.ContactDao;
import contacts.dto.ContactDto;

public class ContactView {
//	메뉴 출력 
	public void showMenu() {
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

	}

// 전체 연락처 조회 출력	
	public void contactsShowView(ArrayList<ContactDto> contacts) {
		int ContactsCnt = contacts.size();

		if (ContactsCnt == 0) {
			System.out.println("입력된 연락처가 없습니다.");
		} else {
			System.out.println(ContactsCnt + "명의 회윈이 저장되어 있습니다.");
			for (int i = 0; i < contacts.size(); i++) {
				System.out.println(contacts.get(i));

			}

		}

	}
// 수정, 삭제시 이름으로 조회=>결과출력 
	public void contactsSearchByNameView(ArrayList<ContactDto> contact) {
		int ContactCnt = contact.size();

		if (ContactCnt == 0) {
			System.out.println("조회된 연락처가 없습니다.");
			
		} else {
			System.out.println(ContactCnt + "개의 연락처가 조회되었습니다.");
			for (int i = 0; i < contact.size(); i++) {
				System.out.println((i+1)+"."+contact.get(i));
			}
		}
	}
//	연락처 수정
	public void contactUpdateView(ContactDto contactDto) {		
		ContactDao contactDao  = new ContactDao(); 
		if(!contactDao.contactUpdate(contactDto)){			
			System.out.println("정상적으로 수정되었습니다.");
		}else {
			System.out.println("수정되지 못했습니다.");
		}
		
	}
//	연락처 삭제 
	public void contactDeleteView() {
		ContactDao contactDao = new ContactDao();
		ContactDto contactDto = new ContactDto();
		if(!contactDao.contactDelete(contactDto)) {			
			System.out.println("정상적으로 삭제되었습니다.");
		}else {
			System.out.println("삭제되지 못했습니다.");
		}
			
	}

}
