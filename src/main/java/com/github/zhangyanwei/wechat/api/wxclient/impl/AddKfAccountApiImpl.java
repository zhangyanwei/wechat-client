package com.github.zhangyanwei.wechat.api.wxclient.impl;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.ApiRequestBuilder;
import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;
import com.github.zhangyanwei.wechat.api.wxclient.AddKfAccountApi;
import com.github.zhangyanwei.wechat.api.wxclient.model.KfAccount;
import com.github.zhangyanwei.wechat.api.wxclient.model.WxResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

class AddKfAccountApiImpl implements AddKfAccountApi {

    private static final String API_PATH = "/customservice/kfaccount/add";

    private URI uri;

    AddKfAccountApiImpl(String accessToken, String baseUrl) {
        uri = UriComponentsBuilder.fromUriString(baseUrl)
                .replacePath(API_PATH)
                .queryParam("access_token", accessToken)
                .build()
                .toUri();
    }

    @Override
    public WxResult request(KfAccount body) throws WxClientException {
        ApiRequest.ApiRequestWithBody<WxResult, KfAccount> apiExchange = ApiRequestBuilder.buildPostRequest(uri, WxResult.class);
        return apiExchange.request(body);
    }

}
