package top.shahow.deliverymailservice.domain.email.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.shahow.deliverymailservice.domain.email.po.ReceiverGroup;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * @author shahow
 * @date 2022-05-15
 */
@Data
@ToString
@EqualsAndHashCode
public class ReceiverGroupDTO {
    @Valid
    @NotNull
    private List<UserDTO> to;

    @Valid
    @NotNull
    private List<UserDTO> cc;

    @Valid
    @NotNull
    private List<UserDTO> bcc;

    public ReceiverGroup toReceiverGroup(){
        ReceiverGroup group = new ReceiverGroup();
        to.stream().map(item -> item.toInternetAddress())
                .filter(Objects::nonNull)
                .forEach(item -> group.addReceiver(item, ReceiverGroup.ReceiverType.TO));
        cc.stream().map(item -> item.toInternetAddress())
                .filter(Objects::nonNull)
                .forEach(item -> group.addReceiver(item, ReceiverGroup.ReceiverType.CC));
        bcc.stream().map(item -> item.toInternetAddress())
                .filter(Objects::nonNull)
                .forEach(item -> group.addReceiver(item, ReceiverGroup.ReceiverType.BCC));
        return group;
    }
}
