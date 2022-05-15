package top.shahow.deliverymailservice.domain.email.po;

import lombok.*;

import javax.mail.internet.InternetAddress;

/**
 * @author shahow
 * @date 2022-05-10
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SendMsg {
    private String title;

    private String content;

    private String sender;
}
