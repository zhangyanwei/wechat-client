package com.github.zhangyanwei.wechat.api.wxclient;

import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;
import com.github.zhangyanwei.wechat.api.wxclient.model.OauthAccessToken;

public interface Oauth2Api {
    OauthAccessToken exchange(String code) throws WxClientException;
}
