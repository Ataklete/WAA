package contacts.data;

import contacts.domain.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ContactRepository extends MongoRepository<Contact, String> {

    Contact findByFirstName(String firstName);
}
