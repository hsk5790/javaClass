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
// 1. ����ó �߰�
			if (i == 1) {
				ContactService contactService = new ContactService();
	        	if(contactService.ContactAdd(scan)) {
	        		System.out.println("���������� �߰��Ǿ����ϴ�.");       		
	            }else {
	            	System.out.println("�߰����� ���߽��ϴ�.");
	            }
	        	continue;
//2. ��ϵ� ����ó ��ü ��ȸ 
	        }else if (i ==2) {
	        	ContactService contactService = new ContactService();
	        	ArrayList<ContactDto> contacts = contactService.showSearchAll();	       
	            contactView.contactsShowView(contacts);
	            continue;
//3. ����ó ����             
	        	
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
// 4. ����ó ����	
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
// 5. ���α׷� ����     	
	        }else if (i== 5) {	        	
	        	break;
	        }

		}
	
			
	}

}
