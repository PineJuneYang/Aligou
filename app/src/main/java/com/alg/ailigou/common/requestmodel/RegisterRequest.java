package com.alg.ailigou.common.requestmodel;

/**
 * Created by Chris Chen on 2017/7/26.
 *
 * 注册的设置登录密码的请求model,请求的参数 电话号码,验证码 和密码
 */
public class RegisterRequest {
    public String phoneNumber;  //电话号码
    public String vCode;  //验证码
    public String password;  //密码

}
