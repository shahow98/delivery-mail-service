package top.shahow.deliverymailservice.domain.email.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.shahow.deliverymailservice.domain.email.po.SendMsg;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author shahow
 * @date 2022-05-15
 */
@Data
@ToString
@EqualsAndHashCode
public class SendMsgDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String sender;

    public SendMsg toSendMsg() {
        SendMsg sendMsg = new SendMsg();
        sendMsg.setTitle(title);
        sendMsg.setContent(content);
        sendMsg.setSender(sender);
        return sendMsg;
    }
}
