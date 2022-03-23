package customers;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {
    Student findByPhone(String phone);
    Student findStudentByName(String name);
    @Query("{email : ?0}")
    Student findCustomerWithEmail(String email);
    @Query("{'address.city' : ?0}")
    List<Student>  findStudenyByCity(String city);


}

