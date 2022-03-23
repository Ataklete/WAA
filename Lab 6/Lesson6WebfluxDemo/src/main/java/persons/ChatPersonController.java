package persons;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
public class ChatPersonController {

	@Autowired
    private ChatPersonService chatPersonService;

    @GetMapping(value="/persons", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Person> getChatMessages() {
        return chatPersonService.getChatMessages("Developer");
    }


}
