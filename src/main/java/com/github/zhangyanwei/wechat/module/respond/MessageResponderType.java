package com.github.zhangyanwei.wechat.module.respond;

import com.github.zhangyanwei.wechat.module.respond.model.WxMessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageResponderType {
    WxMessageType value();
}