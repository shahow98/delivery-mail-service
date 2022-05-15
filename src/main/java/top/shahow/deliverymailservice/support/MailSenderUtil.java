package top.shahow.deliverymailservice.support;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import top.shahow.deliverymailservice.domain.email.po.ReceiverGroup;
import top.shahow.deliverymailservice.domain.email.po.SendMsg;

import javax.mail.internet.InternetAddress;

/**
 * @author shahow
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MailSenderUtil {
    private final JavaMailSender javaMailSender;

    private final MailProperties mailProperties;

    public void send(SendMsg message, ReceiverGroup receiverGroup) {
        javaMailSender.send(mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setSubject(message.getTitle());
            helper.setText(message.getContent(), true);
            helper.setFrom(new InternetAddress(mailProperties.getUsername(), message.getSender()));

            if (!receiverGroup.getTo().isEmpty()) {
                helper.setTo(receiverGroup.toArray(ReceiverGroup.ReceiverType.TO));
            }
            if (!receiverGroup.getCc().isEmpty()) {
                helper.setTo(receiverGroup.toArray(ReceiverGroup.ReceiverType.CC));
            }
            if (!receiverGroup.getBcc().isEmpty()) {
                helper.setTo(receiverGroup.toArray(ReceiverGroup.ReceiverType.BCC));
            }
        });
    }

}
