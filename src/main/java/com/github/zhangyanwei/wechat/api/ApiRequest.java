package com.github.zhangyanwei.wechat.api;

import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;

public interface ApiRequest {

    interface ApiRequestWithoutBody<R extends WxObject> extends ApiRequest {
        R request() throws WxClientException;
    }

    interface ApiRequestWithBody<R extends WxObject, B extends WxObject> {
        R request(B body) throws WxClientException;
    }
}
