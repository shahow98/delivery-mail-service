package top.shahow.deliverymailservice.domain.log.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.shahow.deliverymailservice.domain.account.vo.AccountVO;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author shahow
 * @date 2022-05-10
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
public class LogVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private AccountVO from;


    @ManyToOne
    @JoinColumn(name = "to_id")
    private AccountVO to;

    @Lob
    private String text;

    private SendStatus sendStatus;

    private LocalDateTime createTime;

    public enum SendStatus {
        SUCCESS, FAIL
    }
}
