package contact;

import java.util.Collection;
import java.util.List;

public class Contacts {

    private Collection<ContactDTO> contacts;

    public Contacts() {
    }

    public Contacts(Collection<ContactDTO> contacts) {
        this.contacts = contacts;
    }
    public Collection<ContactDTO> getContacts() {
        return contacts;
    }
    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "contacts=" + contacts +
                '}';
    }
}
