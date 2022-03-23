package contacts.service;

import contacts.domain.Contact;

public class ContactAdapter {

    public static Contact getContact(ContactDTO contactDto){
        Contact contact = new Contact();
        if (contactDto != null) {
            contact = new Contact(contactDto.getFirstName(),
                    contactDto.getLastName(),
                    contactDto.getEmail(),
                    contactDto.getPhone());
        }
        return contact;
    }

    public static ContactDTO getContactDTO(Contact contact){
        ContactDTO contactDto = new ContactDTO();
        if (contact != null) {
            contactDto = new ContactDTO(contact.getFirstName(),
                    contact.getLastName(),
                    contact.getEmail(),
                    contact.getPhone());
        }
        return contactDto;
    }
}
