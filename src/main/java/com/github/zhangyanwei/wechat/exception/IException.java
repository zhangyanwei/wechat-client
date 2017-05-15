package com.github.zhangyanwei.wechat.exception;

public interface IException {
    String getCodeValue();
    String getMessage();
    String getLocalizedMessage();
    <C extends Enum<C>> C getCode();
}
