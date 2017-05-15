package com.github.zhangyanwei.wechat.api.wxclient.exception;

import com.github.zhangyanwei.wechat.api.wxclient.model.WxResult;
import com.github.zhangyanwei.wechat.exception.AbstractException;

public class WxClientException extends AbstractException {

    private static final long serialVersionUID = -6504652099333383700L;

    public enum Error {
        PAY_ERROR
    }

    public WxClientException(Error code) {
        super(code);
    }

    public WxClientException(Error code, String message) {
        super(code, message);
    }

    public WxClientException(WxResult result) {
        super(Integer.toString(result.getCode()), result.getMessage());
    }

    @Override
    protected String messageResourceBaseName() {
        return "exception.client";
    }

    @Override
    protected String moduleName() {
        return "WX";
    }
}
