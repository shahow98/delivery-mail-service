package top.shahow.deliverymailservice.domain.email.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.mail.internet.InternetAddress;
import javax.validation.constraints.NotBlank;
import java.io.UnsupportedEncodingException;

/**
 * @author shahow
 * @date 2022-05-15
 */
@Slf4j
@Data
@ToString
@EqualsAndHashCode
public class UserDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String userName;

    public InternetAddress toInternetAddress() {
        try {
            return new InternetAddress(email, userName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warn("邮件地址转换错误,忽略!");
        }
        return null;
    }
}
