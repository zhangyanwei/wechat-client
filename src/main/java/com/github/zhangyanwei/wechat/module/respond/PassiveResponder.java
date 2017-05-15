package com.github.zhangyanwei.wechat.module.respond;

import com.github.zhangyanwei.wechat.module.respond.model.WxMessage;

public interface PassiveResponder {
    WxMessage respond(WxMessage message) throws RespondException;
}
