package com.github.zhangyanwei.wechat.api.wxclient;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.wxclient.model.Menu;
import com.github.zhangyanwei.wechat.api.wxclient.model.WxResult;

public interface CreateMenuApi extends ApiRequest.ApiRequestWithBody<WxResult, Menu> {
}
