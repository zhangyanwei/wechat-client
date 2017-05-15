package com.github.zhangyanwei.wechat.api.wxpay;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.wxpay.model.TransferRequest;
import com.github.zhangyanwei.wechat.api.wxpay.model.TransferResult;

public interface TransferApi extends ApiRequest.ApiRequestWithBody<TransferResult, TransferRequest> {
}
