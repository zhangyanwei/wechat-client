package com.github.zhangyanwei.wechat.api.wxpay;

import com.github.zhangyanwei.wechat.api.wxpay.model.UnifiedOrderResult;
import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.wxpay.model.PureUnifiedOrder;

public interface UnifiedorderApi extends ApiRequest.ApiRequestWithBody<UnifiedOrderResult, PureUnifiedOrder> {
}
