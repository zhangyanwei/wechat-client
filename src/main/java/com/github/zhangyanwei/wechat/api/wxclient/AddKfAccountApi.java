package com.github.zhangyanwei.wechat.api.wxclient;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.wxclient.model.KfAccount;
import com.github.zhangyanwei.wechat.api.wxclient.model.WxResult;

public interface AddKfAccountApi extends ApiRequest.ApiRequestWithBody<WxResult, KfAccount> {
}
