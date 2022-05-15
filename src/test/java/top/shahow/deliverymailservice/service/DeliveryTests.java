package top.shahow.deliverymailservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import top.shahow.deliverymailservice.Task.MailTask;
import top.shahow.deliverymailservice.domain.email.po.ReceiverGroup;
import top.shahow.deliverymailservice.domain.email.po.SendMsg;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

/**
 * @author shahow
 * @date 2022-05-11
 */
@Slf4j
@SpringBootTest
public class DeliveryTests {
    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    MailProperties properties;

    @Test
    public void deliver() throws JsonProcessingException, UnsupportedEncodingException {
        SendMsg sendMsg = new SendMsg();
        sendMsg.setTitle("带把伞");
        sendMsg.setContent("带把伞");
        sendMsg.setSender("shahow");

        ReceiverGroup receiver = new ReceiverGroup();
        receiver.addReceiver(new InternetAddress("broadcast@shahow.top", "shahow"),
                ReceiverGroup.ReceiverType.TO);

        log.info("{}", new ObjectMapper().writeValueAsString(properties));

        MailTask task = new MailTask(sendMsg, receiver);
        task.run();
    }



}
