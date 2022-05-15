package top.shahow.deliverymailservice.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Repository;
import top.shahow.deliverymailservice.domain.email.po.SendMsg;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

/**
 * @author shahow
 * @date 2022-05-14
 */
@Repository
@RequiredArgsConstructor
public class NotifyMsgRepository {
    private final static String POSTMAN = "POSTMAN";

    private final MailProperties mailProperties;
    public SendMsg resetPassword(String pwd) throws UnsupportedEncodingException {
        return new SendMsg("授权码", pwd, POSTMAN);
    }
}
