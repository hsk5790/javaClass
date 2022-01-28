package contacts.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import contacts.dao.ContactDao;
import contacts.dto.ContactDto;

public class ContactService {
//1. ����ó �߰�
	public boolean ContactAdd(Scanner scan) {
		boolean result = false;

		ContactDto contactDto = new ContactDto();
		ContactDao contactDao = new ContactDao();

		String personnm = null;
		String phoneno = null;
		String address = null;
		int gubunno = 0;
		
// ��ȭ��ȣ => ��3�ڸ� ����(���ڷ� ����), ������, �߰��ڸ� 3~4�ڸ� ����, ������, ������ 4�ڸ� ���� ���� üũ       
		String pattern = "^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$";

		do {
			System.out.print("��ȭ��ȣ �Է�:");
			phoneno = scan.next();
//			���Ͽ� �´��� Ȯ�� 
			if (!Pattern.matches(pattern, phoneno)) {
				System.out.println("�ٽ� �Է��� �ּ���");
			}else {
//			��ȭ��ȣ �ߺ����� Ȯ�� 
				if (!contactDao.isNoSerach(phoneno)) {
					System.out.println("�ߺ��� ��ȭ��ȣ�� �����ϴ�.");
					contactDto.setPhoneno(phoneno);
					break;
				} else {
					System.out.println("�̹� ��ϵ� ��ȭ��ȣ �Դϴ�.");
				}
			}
			
		} while (true);

		System.out.print("�̸�:");
		personnm = scan.next();	
//		���� ����
		personnm.replace(" ", "");
		if (personnm != null && personnm != "") {
			System.out.println("�����Է�");
			contactDto.setPersonnm(personnm);
		} else {
			System.out.println("ó������");
		}

		System.out.print("�ּ�:");
		address = scan.next();
//		�������� 
		address.replace(" ", "");
		contactDto.setAddress(address);

		try {
			System.out.print("���й�ȣ:1.����, 2.ģ�� 3.ȸ��  4.��Ÿ");
			gubunno = scan.nextInt();
//			�Է¹��� ���� ���� 1,2,3,4 ���� Ȯ�� 
			if (gubunno == 1 || gubunno == 2 || gubunno == 3 || gubunno == 4) {
				contactDto.setGubunno(gubunno);
			}else {
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
			}
		} catch (InputMismatchException e) {
			System.out.println("�߸��Է��ϼ̽��ϴ�.");
			e.printStackTrace();
		}

		result = contactDao.contactAdd(contactDto);
		return result;
	}

//2. ����ó ��ü ��ȸ 
	public ArrayList<ContactDto> showSearchAll() {
		ArrayList<ContactDto> contacts = new ArrayList<ContactDto>();
		ContactDao contactDao = new ContactDao();
		contacts = contactDao.showContactAll();
		return contacts;
	}

//3. ����ó ����
	public ArrayList<ContactDto> contactSearchByName(Scanner scan) {
		ArrayList<ContactDto> contact = new ArrayList<ContactDto>();
//		�˻��� �̸� �Է� �ޱ�
		System.out.print("�˻��� �̸��� �Է��ϼ���");
		String name = scan.next();
//		�˻������ �޾Ƽ�  DAO ���� SELECT �� ����� �޾ƿ�  contact�� ���� 
		ContactDao contactDao = new ContactDao();
		contact = contactDao.contactSearchByName(name);

		return contact;
	}

	public boolean contactUpdate(ArrayList<ContactDto> contact, Scanner scan) {
		boolean result = false;
		System.out.println("������ ����ó�� ��ȣ�� �����ϼ���");
		int i = 0;
		i = scan.nextInt();
			    
		String newPersonnm = null;
		String newPhoneno = null;
		String newAddress = null;
		int newGubunno = 0;
		String pattern = "^[0-9]{3}-[0-9]{3,4}-[0-9]{4}$";

		ContactDao contactDao = new ContactDao();
		ContactDto contactDto = new ContactDto();
		
		
		System.out.print("�̸�:");
		newPersonnm = scan.next();
		contact.get(i-1).setPersonnm(newPersonnm);

		
//		��ȭ��ȣ ���� Ȯ�� 
		do {
			System.out.print("��ȭ��ȣ:");
			newPhoneno = scan.next();
			if(!Pattern.matches(pattern, newPhoneno)) {
				System.out.println("�ٽ� �Է��� �ּ���.");
							
			} else {
				contact.get(i-1).setPhoneno(newPhoneno);
			    break;
			}
		} while(true);
	
		
		System.out.print("�ּ�:");
		newAddress = scan.next();
		contact.get(i-1).setAddress(newAddress);	
		
		System.out.print("����(1.����, 2.ģ��, 3.����  4.��Ÿ):");
		do{
			try {
				newGubunno = scan.nextInt();		
				
				if (newGubunno == 1 || newGubunno ==2 || newGubunno == 3 || newGubunno == 4) {
					contact.get(i-1).setGubunno(newGubunno);
					break;
				}else {
					System.out.println("�߸��Է��ϼ̽��ϴ�.");
					break;
				}			
				
			} catch(InputMismatchException e){
				e.printStackTrace();
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
				break;
			}			
		}while(true);
		

		result =contactDao.contactUpdate(contact.get(i-1));

		return result;
	}
//4. ����ó ����	
	public boolean contactDelete(ArrayList<ContactDto> contact, Scanner scan) {
		boolean result = false;
		int i = 0;
		System.out.println("������ ����ó�� ��ȣ�� �����ϼ���.");
		i = scan.nextInt();		
		
		ContactDao contactDao = new ContactDao();	
	    result = contactDao.contactDelete(contact.get(i-1));	 
		
		return result;
	}
}
