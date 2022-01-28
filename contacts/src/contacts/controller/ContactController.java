package contacts.controller;

import java.util.ArrayList;
import java.util.Scanner;

import contacts.dao.ContactDao;
import contacts.dto.ContactDto;
import contacts.service.ContactService;
import contacts.view.ContactView;

public class ContactController {

	public static void main(String[] args) {
	   Scanner scan = new Scanner(System.in);
		while (true) {
			ContactView contactView = new ContactView();
			contactView.showMenu();
	
			int i = scan.nextInt();	
// 1. 연락처 추가
			if (i == 1) {
				ContactService contactService = new ContactService();
	        	if(contactService.ContactAdd(scan)) {
	        		System.out.println("정상적으로 추가되었습니다.");       		
	            }else {
	            	System.out.println("추가되지 못했습니다.");
	            }
	        	continue;
//2. 등록된 연락처 전체 조회 
	        }else if (i ==2) {
	        	ContactService contactService = new ContactService();
	        	ArrayList<ContactDto> contacts = contactService.showSearchAll();	       
	            contactView.contactsShowView(contacts);
	            continue;
//3. 연락처 수정             
	        	
	        }else if (i ==3) {
	        	ContactService contactService = new ContactService();
	        	ArrayList<ContactDto> contact = contactService.contactSearchByName(scan);
	        	contactView.contactsSearchByNameView(contact);
	        	
	        	if (contact.size() == 0) {
	        		continue;
	        	} else {
	        		contactService.contactUpdate(contact, scan);
		        	ContactDto contactDto = new ContactDto();
		            contactView.contactUpdateView(contactDto);
		            continue;
		        }   	        	
// 4. 연락처 삭제	
	        }else if (i ==4) {
	        	ContactService contactService = new ContactService();
	        	ArrayList<ContactDto> contact = contactService.contactSearchByName(scan);
	        	contactView.contactsSearchByNameView(contact);
	        	
	        	if(contact.size() == 0) {
	        		continue;
	        	}else {
	        		contactService.contactDelete(contact, scan);	        		
	        		contactView.contactDeleteView();
	        	}	 
	        	
	        	continue;
// 5. 프로그램 종료     	
	        }else if (i== 5) {	        	
	        	break;
	        }

		}
	
			
	}

}
