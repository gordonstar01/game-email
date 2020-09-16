package game;

import game.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    EmailRepository emailRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverUsed_Use(@Payload Used used){

        if(used.isMe()){
            System.out.println("##### listener Use : " + used.toJson());

            Email email = new Email();
            email.setGiftId(used.getId());
            email.setStatus(used.getStatus());

            emailRepository.save(email);
        }
    }

}
