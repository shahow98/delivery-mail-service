package top.shahow.deliverymailservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.shahow.deliverymailservice.cache.StringCache;
import top.shahow.deliverymailservice.domain.account.dto.AddDTO;
import top.shahow.deliverymailservice.domain.account.dto.UpdPwdDTO;
import top.shahow.deliverymailservice.domain.account.dto.UpdValidDTO;
import top.shahow.deliverymailservice.domain.account.dto.ValidationDTO;
import top.shahow.deliverymailservice.domain.account.vo.AccountVO;
import top.shahow.deliverymailservice.domain.email.po.ReceiverGroup;
import top.shahow.deliverymailservice.exception.ServiceError;
import top.shahow.deliverymailservice.exception.ServiceException;
import top.shahow.deliverymailservice.repository.AccountRepository;
import top.shahow.deliverymailservice.repository.NotifyMsgRepository;
import top.shahow.deliverymailservice.support.MD5;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * @author shahow
 * @date 2022-05-10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final AccountRepository accountRepository;

    private final DeliveryService deliverService;

    private final NotifyMsgRepository notifyMsgRepository;

    private final StringCache stringCache;

    public String register(AddDTO add) throws ServiceException {
        boolean exists = accountRepository.existsByEmail(add.getEmail());
        if (!exists) {
            String uuid = UUID.randomUUID().toString();
            AccountVO addAccount = new AccountVO();
            addAccount.setUserName(add.getUserName());
            addAccount.setPassword(MD5.getMD5(uuid + add.getEmail()));
            addAccount.setEmail(add.getEmail());
            accountRepository.save(addAccount);
            return uuid;
        }

        throw ServiceError.EMAIL_IS_EXIST.asServiceException();
    }

    public void resetValidation(UpdValidDTO updValid) throws ServiceException, UnsupportedEncodingException {
        AccountVO curAccount = accountRepository.findByEmail(updValid.getEmail())
                .orElseThrow(() -> ServiceError.EMAIL_IS_NOT_REGISTERED.asServiceException());

        String uuid = UUID.randomUUID().toString();
        stringCache.put(curAccount.getEmail(), uuid);

        deliverPassword(new InternetAddress(curAccount.getEmail(), curAccount.getUserName()),
                String.format("验证码: %s", uuid));
    }

    public void reset(UpdPwdDTO updPwd) throws ServiceException, UnsupportedEncodingException {
        codeValidation(updPwd);

        AccountVO curAccount = accountRepository.findByEmail(updPwd.getEmail())
                .orElseThrow(() -> ServiceError.EMAIL_IS_NOT_REGISTERED.asServiceException());
        String uuid = UUID.randomUUID().toString();
        curAccount.setPassword(MD5.getMD5(uuid + updPwd.getEmail()));
        accountRepository.save(curAccount);

        deliverPassword(new InternetAddress(curAccount.getEmail(), curAccount.getUserName()),
                String.format("授权码: %s", uuid));
    }

    public void validation(ValidationDTO valid) throws ServiceException {
        String password = MD5.getMD5(valid.getPassword() + valid.getEmail());
        if(!accountRepository.existsByEmailAndPassword(valid.getEmail(), password)) {
            throw ServiceError.PWD_IS_WRONG.asServiceException();
        }
    }

    private void deliverPassword(InternetAddress to, String pwd) throws UnsupportedEncodingException {
        ReceiverGroup receiverGroup = new ReceiverGroup();
        receiverGroup.addReceiver(to, ReceiverGroup.ReceiverType.TO);
        deliverService.deliver(notifyMsgRepository.resetPassword(pwd), receiverGroup);
    }

    private void codeValidation(UpdPwdDTO updPwd) throws ServiceException {
        String code = stringCache.get(updPwd.getEmail())
                .orElseThrow(() -> ServiceError.VALIDATED_CORE_IS_WRONG.asServiceException());
        if (!code.equals(updPwd.getValidateCode())) {
            throw ServiceError.VALIDATED_CORE_IS_WRONG.asServiceException();
        }
        stringCache.remove(updPwd.getEmail());
    }
}
