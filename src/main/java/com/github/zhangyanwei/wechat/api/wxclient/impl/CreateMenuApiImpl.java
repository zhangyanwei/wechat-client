package com.github.zhangyanwei.wechat.api.wxclient.impl;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.ApiRequestBuilder;
import com.github.zhangyanwei.wechat.api.wxclient.CreateMenuApi;
import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;
import com.github.zhangyanwei.wechat.api.wxclient.model.Menu;
import com.github.zhangyanwei.wechat.api.wxclient.model.WxResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

class CreateMenuApiImpl implements CreateMenuApi {

    private static final String API_PATH = "/menu/create";

    private URI uri;

    CreateMenuApiImpl(String accessToken, String baseUrl) {
        uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path(API_PATH)
                .queryParam("access_token", accessToken)
                .build()
                .toUri();
    }

    @Override
    public WxResult request(Menu body) throws WxClientException {
        ApiRequest.ApiRequestWithBody<WxResult, Menu> apiExchange = ApiRequestBuilder.buildPostRequest(uri, WxResult.class);
        return apiExchange.request(body);
    }
}
