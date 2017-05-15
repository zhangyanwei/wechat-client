package com.github.zhangyanwei.wechat.module.respond;

import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;

public class RespondException extends Exception {

    public RespondException(WxClientException cause) {
        super(cause);
    }
}
