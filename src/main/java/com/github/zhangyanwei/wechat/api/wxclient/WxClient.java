package com.github.zhangyanwei.wechat.api.wxclient;

public interface WxClient {
    TokenApi token();

    TicketApi ticket();

    UserInfoApi userInfo(String openid);

    UserInfoApi userInfo(String oauth2AccessToken, String openid);

    CreateMenuApi createMenu();

    AddKfAccountApi addKfAccount();

    TemplateNoticeApi templateNotice();
}
