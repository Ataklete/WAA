package persons;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatPersonService {

    private final ChatPersonRepository chatPersonRepository;

    public ChatPersonService(ChatPersonRepository chatPersonRepository) {
        this.chatPersonRepository = chatPersonRepository;
    }

    public Flux<Person> getChatMessages(String job) {
        return chatPersonRepository.findByJob(job);
    }
}
