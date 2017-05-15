package com.github.zhangyanwei.wechat.api.wxclient.impl;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.ApiRequestBuilder;
import com.github.zhangyanwei.wechat.api.wxclient.TokenApi;
import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;
import com.github.zhangyanwei.wechat.api.wxclient.model.AccessToken;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

class TokenApiImpl implements TokenApi {

    private static final String API_PATH = "/token";

    private URI uri;

    TokenApiImpl(String baseUrl, String appId, String secret) {
        uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path(API_PATH)
                .queryParam("grant_type", "client_credential")
                .queryParam("appid", appId)
                .queryParam("secret", secret)
                .build()
                .toUri();
    }

    @Override
    public AccessToken request() throws WxClientException {
        ApiRequest.ApiRequestWithoutBody<AccessToken> apiExchange = ApiRequestBuilder.buildGetRequest(uri, AccessToken.class);
        return apiExchange.request();
    }
}
