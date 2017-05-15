package com.github.zhangyanwei.wechat.module.respond.impl;

import com.github.zhangyanwei.wechat.module.respond.RespondException;
import com.github.zhangyanwei.wechat.module.respond.MessagePassiveResponder;
import com.github.zhangyanwei.wechat.module.respond.MessageResponderType;
import com.github.zhangyanwei.wechat.module.respond.model.WxEventMessage;
import com.github.zhangyanwei.wechat.module.respond.model.WxMessage;
import com.github.zhangyanwei.wechat.module.respond.model.WxTextMessage;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.github.zhangyanwei.wechat.module.respond.model.WxMessageType.EVENT;
import static com.github.zhangyanwei.wechat.module.respond.model.WxMessageType.TEXT;
import static com.google.common.base.Joiner.on;

@Component
@MessageResponderType(EVENT)
public class WxEventMessageResponder implements MessagePassiveResponder {

    @Override
    public WxMessage respond(WxMessage message) throws RespondException {

        WxEventMessage eventMessage = (WxEventMessage) message;
        WxTextMessage responseMessage = new WxTextMessage();
        responseMessage.setFromUserName(eventMessage.getToUserName());
        responseMessage.setToUserName(eventMessage.getFromUserName());
        responseMessage.setCreateTime(new Date());
        responseMessage.setMessageType(TEXT);

        switch (eventMessage.getEvent()) {
            case SUBSCRIBE:
                responseMessage.setContent("欢迎关注易居宝，您可以通过该微信公众号查询房屋信息、咨询您遇到的问题，也可以管理您的交易。");
                break;
            default:
                responseMessage.setContent("");
                break;
        }

        return responseMessage;
    }
}
