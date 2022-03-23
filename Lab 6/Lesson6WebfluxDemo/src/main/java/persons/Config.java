package persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@EnableScheduling
@EnableReactiveMongoRepositories
public class Config {

    private int x = 0;

    @Autowired
    ChatPersonRepository chatPersonRepository;

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @PostConstruct
    public void init() {
        reactiveMongoTemplate.dropCollection("person").then(reactiveMongoTemplate.createCollection("person",
                CollectionOptions.empty().capped().size(2048).maxDocuments(10000))).block();
    }

    @Scheduled(fixedRate = 3000)
    private void savePerson() {
        System.out.println("add person Frank Brown"+x);
        chatPersonRepository.save(new Person(x + "", "Frank Brown" + x, "Developer")).block();
		x++;
    }
}
