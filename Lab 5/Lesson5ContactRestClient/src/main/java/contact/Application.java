package contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private RestOperations restTemplate;
	private String serverUrl = "http://localhost:8080/contacts";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// add a contact
		restTemplate.postForLocation(serverUrl, new ContactDTO("John","Doe", "jdoe@acme.com", "6739127563"));
		// add a contact
		restTemplate.postForLocation(serverUrl, new ContactDTO("Bob","Jones", "bobby@hotmail.com", "3214532563"));
        // get contact
		ContactDTO contact= restTemplate.getForObject(serverUrl+"/Bob", ContactDTO.class );
		System.out.println("--------------------Contact info from Bob----------------------");
		System.out.println(contact);
		// get all contacts
		Contacts contacts= restTemplate.getForObject(serverUrl, Contacts.class );
		System.out.println("--------------------All contacts----------------------");
		System.out.println(contacts);
		//delete a contact
		restTemplate.delete(serverUrl+"/Bob");
		// update contact
		contact.setEmail("bjones@gmail.com");
		restTemplate.put(serverUrl+"/Bob", contact);
	}

	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
