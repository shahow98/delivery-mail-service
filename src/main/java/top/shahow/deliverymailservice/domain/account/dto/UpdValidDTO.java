package top.shahow.deliverymailservice.domain.account.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author shahow
 * @date 2022-05-14
 */
@Data
@ToString
@EqualsAndHashCode
public class UpdValidDTO {
    @NotBlank
    @Length(min = 3, max = 50)
    @Pattern(regexp = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String email;
}
