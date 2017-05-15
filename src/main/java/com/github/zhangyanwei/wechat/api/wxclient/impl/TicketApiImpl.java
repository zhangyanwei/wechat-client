package com.github.zhangyanwei.wechat.api.wxclient.impl;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.ApiRequestBuilder;
import com.github.zhangyanwei.wechat.api.wxclient.TicketApi;
import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;
import com.github.zhangyanwei.wechat.api.wxclient.model.Ticket;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

class TicketApiImpl implements TicketApi {

    private static final String API_PATH = "/ticket/getticket";

    private URI uri;

    TicketApiImpl(String accessToken, String baseUrl) {
        uri = UriComponentsBuilder.fromUriString(baseUrl)
                .path(API_PATH)
                .queryParam("access_token", accessToken)
                .queryParam("type", "jsapi")
                .build()
                .toUri();
    }

    @Override
    public Ticket request() throws WxClientException {
        ApiRequest.ApiRequestWithoutBody<Ticket> apiExchange = ApiRequestBuilder.buildGetRequest(uri, Ticket.class);
        return apiExchange.request();
    }
}
