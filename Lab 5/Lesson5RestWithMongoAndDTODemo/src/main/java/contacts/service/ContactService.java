package contacts.service;

import contacts.data.ContactRepository;
import contacts.domain.Contact;
import contacts.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    EmailSender emailSender;

    public void add(ContactDTO contactDto){
        Contact contact = ContactAdapter.getContact(contactDto);
        contactRepository.save(contact);
        emailSender.sendEmail(contact.getEmail(), "Welcome");
    }

    public void update(ContactDTO contactDto){
        Contact contact = ContactAdapter.getContact(contactDto);
        contactRepository.save(contact);
    }

    public ContactDTO findByFirstName(String firstName){
        Contact contact = contactRepository.findByFirstName(firstName);
        return ContactAdapter.getContactDTO(contact);
    }

    public void delete(String firstName){
        Contact contact = contactRepository.findByFirstName(firstName);
        emailSender.sendEmail(contact.getEmail(), "Good By");
        contactRepository.delete(contact);
    }

    public Collection<ContactDTO> findAll(){
        Collection<Contact> contacts = contactRepository.findAll();
        Collection<ContactDTO> contactDTOs = new ArrayList<ContactDTO>();
        for (Contact contact : contacts){
            contactDTOs.add(ContactAdapter.getContactDTO(contact));
        }
        return contactDTOs;
    }
}
