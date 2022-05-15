package top.shahow.deliverymailservice.exception;

import lombok.RequiredArgsConstructor;

/**
 * @author shahow
 * @date 2022-05-14
 */
@RequiredArgsConstructor
public enum ServiceError {
    EMAIL_IS_EXIST("The email is registered!"),
    EMAIL_IS_NOT_REGISTERED("The email is not registered!"),

    PWD_IS_WRONG("The password is wrong!"),

    VALIDATED_CORE_IS_WRONG("The validated code is wrong!");

    private final String remark;

    public ServiceException asServiceException() {
        return new ServiceException(remark);
    }
}
