package top.shahow.deliverymailservice.domain.DeliveryConfig.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import top.shahow.deliverymailservice.domain.account.vo.AccountVO;

import javax.persistence.*;

/**
 * @author shahow
 * @date 2022-05-11
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
public class DeliveryConfigVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private AccountVO from;

    private String sendEmail;
}
