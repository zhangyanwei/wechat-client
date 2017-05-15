package com.github.zhangyanwei.wechat.api.wxclient.impl;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.wxclient.TemplateNoticeApi;
import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;
import com.github.zhangyanwei.wechat.api.wxclient.model.TemplateNotice;
import com.github.zhangyanwei.wechat.api.wxclient.model.WxResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.github.zhangyanwei.wechat.api.ApiRequestBuilder.buildPostRequest;

class TemplateNoticeApiImpl implements TemplateNoticeApi {

    private static final String API_PATH = "/message/template/send";

    private URI uri;

    TemplateNoticeApiImpl(String accessToken, String baseUrl) {
        uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path(API_PATH)
                .queryParam("access_token", accessToken)
                .build()
                .toUri();
    }

    @Override
    public WxResult request(TemplateNotice body) throws WxClientException {
        ApiRequest.ApiRequestWithBody<WxResult, TemplateNotice> apiExchange = buildPostRequest(uri, WxResult.class);
        return apiExchange.request(body);
    }
}
