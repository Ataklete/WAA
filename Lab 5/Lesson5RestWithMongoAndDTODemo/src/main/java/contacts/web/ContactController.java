package contacts.web;

import contacts.domain.Contact;
import contacts.service.ContactDTO;
import contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
public class ContactController {
    @Autowired
    private ContactService contactService;


    @GetMapping("/contacts/{firstName}")
    public ResponseEntity<?> getContact(@PathVariable String firstName) {
        ContactDTO contactDto = contactService.findByFirstName(firstName);
        if (contactDto.getFirstName() == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Contact with firstname= "
                    + firstName + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ContactDTO>(contactDto, HttpStatus.OK);
    }

    @PostMapping("/contacts")
    public ResponseEntity<?> addContact(@RequestBody ContactDTO contactDto) {
        contactService.add(contactDto);
        return new ResponseEntity<ContactDTO>(contactDto, HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{firstName}")
    public ResponseEntity<?> deleteContact(@PathVariable String firstName) {
        ContactDTO contact = contactService.findByFirstName(firstName);
        if (!contact.getFirstName().equals(firstName)) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Contact with firstname= " + firstName + " is not available"),HttpStatus.NOT_FOUND);
        }
        contactService.delete(firstName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/contacts/{firstName}")
    public ResponseEntity<?> updateContact(@PathVariable String firstName, @RequestBody ContactDTO contactDto) {
        contactService.update(contactDto);
        return new ResponseEntity<ContactDTO>(contactDto, HttpStatus.OK);
    }

    @GetMapping("/contacts")
    public ResponseEntity<?> getAllContacts() {
        Contacts allcontacs = new Contacts(contactService.findAll());
        return new ResponseEntity<Contacts>(allcontacs, HttpStatus.OK);
    }
}
