package com.github.zhangyanwei.wechat.api.wxpay.impl;

import com.github.zhangyanwei.wechat.api.wxpay.TransferApi;
import com.github.zhangyanwei.wechat.api.wxpay.UnifiedorderApi;
import com.github.zhangyanwei.wechat.api.wxpay.WxPayClient;
import com.github.zhangyanwei.wechat.configuration.WxPayConfiguration;
import com.github.zhangyanwei.wechat.utils.WxMessageSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConditionalOnBean(WxPayConfiguration.class)
public class WxPayClientImpl implements WxPayClient {

    @Autowired
    private WxPayConfiguration configuration;

    @Override
    public UnifiedorderApi unifiedorder() {
        return new UnifiedorderApiImpl(configuration);
    }

    @Override
    public String sign(Map<String, ?> map) {
        return sign(map, false);
    }

    @Override
    public String sign(Map<String, ?> map, boolean app) {
        return WxMessageSecurity.sign(map, app ? configuration.getApp().getKey() : configuration.getWeb().getKey());
    }

    @Override
    public TransferApi transfer() {
        return new TransferApiImpl(configuration);
    }
}
