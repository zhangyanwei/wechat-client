package com.github.zhangyanwei.wechat.api.wxclient.impl;

import com.github.zhangyanwei.wechat.api.wxclient.*;
import com.github.zhangyanwei.wechat.configuration.WxConfiguration;
import com.github.zhangyanwei.wechat.module.auth.AccessTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WxClientImpl implements WxClient {

    @Autowired
    private WxConfiguration configuration;

    @Autowired
    private AccessTokenStore accessTokenStore;

    @Override
    public TokenApi token() {
        return new TokenApiImpl(configuration.getBaseUrl(), configuration.getAppId(), configuration.getAppSecret());
    }

    @Override
    public TicketApi ticket() {
        return new TicketApiImpl(accessTokenStore.get(), configuration.getBaseUrl());
    }

    @Override
    public UserInfoApi userInfo(String openid) {
        return new UserInfoApiImpl(accessTokenStore.get(), configuration.getBaseUrl(), openid);
    }

    @Override
    public UserInfoApi userInfo(String oauth2AccessToken, String openid) {
        return new UserInfoApiImpl(oauth2AccessToken, configuration.getBaseUrl(), openid, true);
    }

    @Override
    public CreateMenuApi createMenu() {
        return new CreateMenuApiImpl(accessTokenStore.get(), configuration.getBaseUrl());
    }

    @Override
    public AddKfAccountApi addKfAccount() {
        return new AddKfAccountApiImpl(accessTokenStore.get(), configuration.getBaseUrl());
    }

    @Override
    public TemplateNoticeApi templateNotice() {
        return new TemplateNoticeApiImpl(accessTokenStore.get(), configuration.getBaseUrl());
    }
}
