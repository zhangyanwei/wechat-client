package com.github.zhangyanwei.wechat.api.wxclient.model;

import com.github.zhangyanwei.wechat.api.WxObject;

public class AccessToken implements WxObject {

    private String accessToken;
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
