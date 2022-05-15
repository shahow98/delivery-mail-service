package top.shahow.deliverymailservice.domain.email.po;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author shahow
 * @date 2022-05-10
 */
@Getter
@ToString
@EqualsAndHashCode
public class ReceiverGroup {
    /** 收件人 **/
    private final List<InternetAddress> to;

    /** 抄送 **/
    private final List<InternetAddress> cc;

    /** 密送 **/
    private final List<InternetAddress> bcc;

    public ReceiverGroup() {
        this.to = new ArrayList<>();
        this.cc = new ArrayList<>();
        this.bcc = new ArrayList<>();
    }

    public void addReceiver(InternetAddress receiver, ReceiverType receiverType) {
        switch (receiverType) {
            case TO: to.add(receiver); break;
            case CC: cc.add(receiver); break;
            case BCC: bcc.add(receiver); break;
        }
    }

    public InternetAddress[] toArray(ReceiverType receiverType) {
        List<InternetAddress> cur = Collections.emptyList();
        switch (receiverType) {
            case TO: cur = to; break;
            case CC: cur = cc; break;
            case BCC: cur = bcc; break;
        }
        InternetAddress[] curArr = new InternetAddress[cur.size()];
        return cur.toArray(curArr);
    }

    public enum ReceiverType {
        TO, CC, BCC
    }
}
