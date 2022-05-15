package top.shahow.deliverymailservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.shahow.deliverymailservice.domain.account.dto.AddDTO;
import top.shahow.deliverymailservice.domain.email.dto.EmailDTO;
import top.shahow.deliverymailservice.exception.ServiceException;
import top.shahow.deliverymailservice.service.DeliveryService;

import javax.validation.Valid;

/**
 * @author shahow
 * @date 2022-05-15
 */
@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/deliver")
    public void deliver(@Valid @RequestBody EmailDTO email) {
        deliveryService.deliver(email);
    }
}
