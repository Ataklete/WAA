package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        // create student
		Student student = new Student(101,"John doe", "johnd@acme.com", "0622341678");
		Address address = new Address("290 Washington st", "Boston", "123456");
		student.setAddress(address);
		studentRepository.save(student);
		student = new Student(109,"John Jones", "jones@acme.com", "0624321234");
		address = new Address("907 Argyle st", "Chicago", "48946");
		student.setAddress(address);
		studentRepository.save(student);
		student = new Student(66,"James Johnson", "jj123@acme.com", "068633452");
		address = new Address("1000N st", "Fairfield", "52557");
		student.setAddress(address);
		studentRepository.save(student);
		student = new Student(20,"Alliya Thomas", "alliya@acme.com", "95266263");
		address = new Address("321 Buffalo", "NY", "94946");
		student.setAddress(address);
		studentRepository.save(student);
		student = new Student(40,"Chris Barning", "chris@acme.com", "61623");
		address = new Address("654 Central-Bay", "LA", "96232");
		student.setAddress(address);
		studentRepository.save(student);
		//get Students
		System.out.println(studentRepository.findById(20).get());
		System.out.println(studentRepository.findById(40).get());
		System.out.println("-----------All Students ----------------");
		System.out.println(studentRepository.findAll());
		//update Students
		student = studentRepository.findById(101).get();
		student.setEmail("jd@gmail.com");
		studentRepository.save(student);
		System.out.println("-----------find by name ----------------");
		System.out.println(studentRepository.findStudentByName("Chris Barning"));
		System.out.println("-----------find by phone ----------------");
		System.out.println(studentRepository.findByPhone("0622341678"));
		System.out.println("-----------find by email ----------------");
		System.out.println(studentRepository.findCustomerWithEmail("jj123@acme.com"));
		System.out.println("-----------find student lived in city ----------------");
		List<Student> students = studentRepository.findStudenyByCity("Chicago");
		for (Student cust : students){
			System.out.println(cust);
		}

	}

}
