package com.github.zhangyanwei.wechat.module.respond.impl;

import com.github.zhangyanwei.wechat.module.respond.MessagePassiveResponder;
import com.github.zhangyanwei.wechat.module.respond.RespondException;
import com.github.zhangyanwei.wechat.module.respond.model.WxTransferCustomerServiceMessage;
import com.github.zhangyanwei.wechat.module.respond.MessageResponderType;
import com.github.zhangyanwei.wechat.module.respond.model.WxMessage;
import com.github.zhangyanwei.wechat.module.respond.model.WxTextMessage;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.github.zhangyanwei.wechat.module.respond.model.WxMessageType.TEXT;
import static com.github.zhangyanwei.wechat.module.respond.model.WxMessageType.TRANSFER_CUSTOMER_SERVICE;

@Component
@MessageResponderType(TEXT)
public class WxTextMessageResponder implements MessagePassiveResponder {

    @Override
    public WxMessage respond(WxMessage message) throws RespondException {

        WxTextMessage textMessage = (WxTextMessage) message;

        List<String> keywords = Collections.emptyList();

        boolean matched = keywords.stream().anyMatch(keyword -> textMessage.getContent().contains(keyword));
        if (matched) {
            WxTextMessage responseMessage = new WxTextMessage();
            fill(responseMessage, textMessage);
            responseMessage.setMessageType(TEXT);
            responseMessage.setContent("如果您有任何的建议或者疑问，欢迎致电 400-876-1369。");

            return responseMessage;
        }

        // do not specify the customer service account.
        WxTransferCustomerServiceMessage transferCustomerServiceMessage = new WxTransferCustomerServiceMessage();
        fill(transferCustomerServiceMessage, textMessage);
        transferCustomerServiceMessage.setMessageType(TRANSFER_CUSTOMER_SERVICE);
        return transferCustomerServiceMessage;
    }

    private void fill(WxMessage message, WxMessage receiveMessage) {
        message.setFromUserName(receiveMessage.getToUserName());
        message.setToUserName(receiveMessage.getFromUserName());
        message.setCreateTime(new Date());
    }

}
