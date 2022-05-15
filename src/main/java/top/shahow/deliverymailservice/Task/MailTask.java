package top.shahow.deliverymailservice.Task;

import lombok.extern.slf4j.Slf4j;
import top.shahow.deliverymailservice.domain.email.po.ReceiverGroup;
import top.shahow.deliverymailservice.domain.email.po.SendMsg;
import top.shahow.deliverymailservice.support.SpringContextUtil;
import top.shahow.deliverymailservice.support.MailSenderUtil;

@Slf4j
public class MailTask implements Runnable {
    private SendMsg message;
    private ReceiverGroup receiver;
    private MailSenderUtil mailSenderUtil;

    public MailTask(SendMsg message, ReceiverGroup receiver) {
        this.message = message;
        this.receiver = receiver;
        mailSenderUtil = (MailSenderUtil) SpringContextUtil.getApplicationContext().getBean("mailSenderUtil");
        log.info("邮递任务初始化完成!");
    }

    @Override
    public void run() {
        mailSenderUtil.send(message, receiver);
        log.info("邮件发送成功");
    }
}
