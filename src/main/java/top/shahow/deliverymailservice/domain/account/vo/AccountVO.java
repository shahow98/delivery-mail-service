package top.shahow.deliverymailservice.domain.account.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author shahow
 * @date 2022-05-10
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
public class AccountVO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String userName;

    private String password;

    private String email;
}
