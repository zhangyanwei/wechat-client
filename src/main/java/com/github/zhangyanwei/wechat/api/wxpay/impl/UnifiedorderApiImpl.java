package com.github.zhangyanwei.wechat.api.wxpay.impl;

import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;
import com.github.zhangyanwei.wechat.api.wxpay.UnifiedorderApi;
import com.github.zhangyanwei.wechat.api.wxpay.model.UnifiedOrder;
import com.github.zhangyanwei.wechat.api.wxpay.model.UnifiedOrderResult;
import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.ApiRequestBuilder;
import com.github.zhangyanwei.wechat.api.wxpay.model.PureUnifiedOrder;
import com.github.zhangyanwei.wechat.api.wxpay.model.TradeType;
import com.github.zhangyanwei.wechat.configuration.WxPayConfiguration;
import com.github.zhangyanwei.wechat.utils.Json;
import com.github.zhangyanwei.wechat.utils.WxMessageSecurity;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.github.zhangyanwei.wechat.utils.Json.writeValueAsString;
import static com.github.zhangyanwei.wechat.utils.WxMessageSecurity.nextRandomString;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

class UnifiedorderApiImpl implements UnifiedorderApi {

    private WxPayConfiguration configuration;

    UnifiedorderApiImpl(WxPayConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public UnifiedOrderResult request(PureUnifiedOrder order) throws WxClientException {
        URI uri = fromUriString(configuration.getUnifiedorderUrl()).build().toUri();
        ApiRequest.ApiRequestWithBody<UnifiedOrderResult, UnifiedOrder> apiExchange = ApiRequestBuilder.buildXmlPostRequest(uri, UnifiedOrderResult.class);
        return apiExchange.request(toUnifiedOrder(order));
    }

    private UnifiedOrder toUnifiedOrder(PureUnifiedOrder pureUnifiedOrder) {
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        TradeType tradeType = pureUnifiedOrder.getTradeType();
        WxPayConfiguration.MchConfiguration mchConfiguration = tradeType == TradeType.APP ? configuration.getApp() : configuration.getWeb();
        unifiedOrder.setAppid(mchConfiguration.getAppid());
        unifiedOrder.setMchid(mchConfiguration.getMchid());
        unifiedOrder.setDeviceInfo("WEB");
        unifiedOrder.setNonce(nextRandomString());
        unifiedOrder.setBody(pureUnifiedOrder.getBody());
        unifiedOrder.setDeviceInfo(pureUnifiedOrder.getDetail());
        unifiedOrder.setAttach(pureUnifiedOrder.getAttach());
        unifiedOrder.setOutTradeNo(pureUnifiedOrder.getOutTradeNo());
        unifiedOrder.setTotalFee(pureUnifiedOrder.getTotalFee());
        unifiedOrder.setSpbillCreateIp(pureUnifiedOrder.getSpbillCreateIp());
        unifiedOrder.setTimeStart(new Date());
        unifiedOrder.setNotifyUrl(configuration.getCallbackUrl() + "/api/wechat/pay/result");
        unifiedOrder.setTradeType(tradeType);
        unifiedOrder.setProductId(pureUnifiedOrder.getProductId());
        unifiedOrder.setOpenid(pureUnifiedOrder.getOpenid());
        unifiedOrder.setSign(sign(unifiedOrder, mchConfiguration));
        return unifiedOrder;
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})
    private String sign(UnifiedOrder unifiedOrder, WxPayConfiguration.MchConfiguration mchConfiguration) {
        Map<String, Object> map = Json.readValue(writeValueAsString(unifiedOrder), HashMap.class);
        return WxMessageSecurity.sign(map, mchConfiguration.getKey());
    }

}
