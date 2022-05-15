package top.shahow.deliverymailservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.shahow.deliverymailservice.domain.account.dto.AddDTO;
import top.shahow.deliverymailservice.domain.account.dto.UpdPwdDTO;
import top.shahow.deliverymailservice.domain.account.dto.UpdValidDTO;
import top.shahow.deliverymailservice.domain.account.dto.ValidationDTO;
import top.shahow.deliverymailservice.exception.ServiceException;
import top.shahow.deliverymailservice.service.UserService;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * @author shahow
 * @date 2022-05-11
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/register")
    public String register(@Valid @RequestBody AddDTO add) throws ServiceException {
        return userService.register(add);
    }

    @PostMapping("/validation")
    public void validation(@Valid @RequestBody ValidationDTO valid) throws ServiceException {
        userService.validation(valid);
    }

    @GetMapping("/reset")
    public void resetValidation(@Valid UpdValidDTO updValid) throws ServiceException, UnsupportedEncodingException {
        userService.resetValidation(updValid);
    }

    @PostMapping("/reset")
    public void reset(@Valid @RequestBody UpdPwdDTO updPwd) throws ServiceException, UnsupportedEncodingException {
        userService.reset(updPwd);
    }
}
