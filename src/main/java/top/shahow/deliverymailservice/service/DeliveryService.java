package top.shahow.deliverymailservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.shahow.deliverymailservice.Task.MailTask;
import top.shahow.deliverymailservice.domain.email.dto.EmailDTO;
import top.shahow.deliverymailservice.domain.email.dto.ReceiverGroupDTO;
import top.shahow.deliverymailservice.domain.email.dto.SendMsgDTO;
import top.shahow.deliverymailservice.domain.email.po.ReceiverGroup;
import top.shahow.deliverymailservice.domain.email.po.SendMsg;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author shahow
 * @date 2022-05-11
 */
@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final ThreadPoolExecutor executor;

    public void deliver(EmailDTO email) {
        deliver(email.getSendMsg(), email.getReceiverGroup());
    }

    public void deliver(SendMsgDTO message, ReceiverGroupDTO receiver) {
        deliver(message.toSendMsg(), receiver.toReceiverGroup());
    }

    public void deliver(SendMsg message, ReceiverGroup receiver) {
        executor.execute(new MailTask(message, receiver));
    }
}
