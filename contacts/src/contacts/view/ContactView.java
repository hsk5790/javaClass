package contacts.view;

import java.util.ArrayList;

import contacts.dao.ContactDao;
import contacts.dto.ContactDto;

public class ContactView {
//	�޴� ��� 
	public void showMenu() {
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

	}

// ��ü ����ó ��ȸ ���	
	public void contactsShowView(ArrayList<ContactDto> contacts) {
		int ContactsCnt = contacts.size();

		if (ContactsCnt == 0) {
			System.out.println("�Էµ� ����ó�� �����ϴ�.");
		} else {
			System.out.println(ContactsCnt + "���� ȸ���� ����Ǿ� �ֽ��ϴ�.");
			for (int i = 0; i < contacts.size(); i++) {
				System.out.println(contacts.get(i));

			}

		}

	}
// ����, ������ �̸����� ��ȸ=>������ 
	public void contactsSearchByNameView(ArrayList<ContactDto> contact) {
		int ContactCnt = contact.size();

		if (ContactCnt == 0) {
			System.out.println("��ȸ�� ����ó�� �����ϴ�.");
			
		} else {
			System.out.println(ContactCnt + "���� ����ó�� ��ȸ�Ǿ����ϴ�.");
			for (int i = 0; i < contact.size(); i++) {
				System.out.println((i+1)+"."+contact.get(i));
			}
		}
	}
//	����ó ����
	public void contactUpdateView(ContactDto contactDto) {		
		ContactDao contactDao  = new ContactDao(); 
		if(!contactDao.contactUpdate(contactDto)){			
			System.out.println("���������� �����Ǿ����ϴ�.");
		}else {
			System.out.println("�������� ���߽��ϴ�.");
		}
		
	}
//	����ó ���� 
	public void contactDeleteView() {
		ContactDao contactDao = new ContactDao();
		ContactDto contactDto = new ContactDto();
		if(!contactDao.contactDelete(contactDto)) {			
			System.out.println("���������� �����Ǿ����ϴ�.");
		}else {
			System.out.println("�������� ���߽��ϴ�.");
		}
			
	}

}
