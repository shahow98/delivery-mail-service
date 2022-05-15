package top.shahow.deliverymailservice.domain.email.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.shahow.deliverymailservice.domain.email.po.SendMsg;
import top.shahow.deliverymailservice.domain.email.po.ReceiverGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author shahow
 * @date 2022-05-15
 */
@Data
@ToString
@EqualsAndHashCode
public class EmailDTO {
    @Valid
    @NotNull
    private SendMsgDTO sendMsg;

    @Valid
    @NotNull
    private ReceiverGroupDTO receiverGroup;
}
