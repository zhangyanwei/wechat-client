package com.github.zhangyanwei.wechat.api.wxpay.impl;

import com.github.zhangyanwei.wechat.api.ApiRequest;
import com.github.zhangyanwei.wechat.api.ApiRequestBuilder;
import com.github.zhangyanwei.wechat.api.wxclient.exception.WxClientException;
import com.github.zhangyanwei.wechat.api.wxpay.TransferApi;
import com.github.zhangyanwei.wechat.api.wxpay.model.Transfer;
import com.github.zhangyanwei.wechat.api.wxpay.model.TransferRequest;
import com.github.zhangyanwei.wechat.api.wxpay.model.TransferResult;
import com.github.zhangyanwei.wechat.configuration.WxPayConfiguration;
import com.github.zhangyanwei.wechat.utils.Json;
import com.github.zhangyanwei.wechat.utils.WxMessageSecurity;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;

import static com.github.zhangyanwei.wechat.utils.Json.writeValueAsString;
import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

class TransferApiImpl implements TransferApi {

    private static final Logger logger = LoggerFactory.getLogger(TransferApiImpl.class);

    private WxPayConfiguration configuration;

    TransferApiImpl(WxPayConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public TransferResult request(TransferRequest transferRequest) throws WxClientException {
        URI uri = fromUriString(configuration.getTransferUrl()).build().toUri();
        ApiRequest.ApiRequestWithBody<TransferResult, Transfer> apiExchange = ApiRequestBuilder.buildXmlPostRequest(requestFactory(), uri, TransferResult.class);
        return apiExchange.request(toTransfer(transferRequest));
    }

    private ClientHttpRequestFactory requestFactory() {
        HttpClient httpClient = securityHttpClient();
        if (httpClient != null) {
            return new HttpComponentsClientHttpRequestFactory(httpClient);
        }

        return null;
    }

    private HttpClient securityHttpClient() {

        try {
            KeyStore keyStore  = KeyStore.getInstance("PKCS12");
            try (FileInputStream inputStream = new FileInputStream(new File(configuration.getWeb().getCertificationFile()))) {
                keyStore.load(inputStream, configuration.getWeb().getMchid().toCharArray());
            }

            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, configuration.getWeb().getMchid().toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    new DefaultHostnameVerifier());

            return HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .build();

        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | KeyManagementException | CertificateException | UnrecoverableKeyException e) {
            logger.error("can not configure the security http client.", e);
        }

        return null;
    }

    private Transfer toTransfer(TransferRequest transferRequest) {
        Transfer transfer = new Transfer();
        transfer.setMchAppid(configuration.getWeb().getAppid());
        transfer.setMchid(configuration.getWeb().getMchid());
        // transfer.setDeviceInfo();
        transfer.setNonceStr(WxMessageSecurity.nextRandomString());
        transfer.setPartnerTradeNo(transferRequest.getPartnerTradeNo());
        transfer.setOpenid(transferRequest.getOpenid());
        transfer.setCheckName(transferRequest.getCheckName());
        transfer.setReUserName(transferRequest.getReUserName());
        transfer.setAmount(transferRequest.getAmount());
        transfer.setDesc(transferRequest.getDesc());
        transfer.setSpbillCreateIp(transferRequest.getSpbillCreateIp());

        @SuppressWarnings({"ConstantConditions", "unchecked"})
        Map<String, Object> map = Json.readValue(writeValueAsString(transfer), HashMap.class);
        transfer.setSign(WxMessageSecurity.sign(map, configuration.getWeb().getKey()));
        return transfer;
    }
}
