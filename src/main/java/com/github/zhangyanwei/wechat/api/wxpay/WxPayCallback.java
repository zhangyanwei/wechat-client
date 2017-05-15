package com.github.zhangyanwei.wechat.api.wxpay;

import com.github.zhangyanwei.wechat.api.wxpay.model.ReturnValue;

import java.util.Map;

public interface WxPayCallback {
    ReturnValue call(Map<String, String> payResultDetails);
}
