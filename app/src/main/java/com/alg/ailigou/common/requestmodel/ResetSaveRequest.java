package com.alg.ailigou.common.requestmodel;

/**
 * Created by Chris Chen on 2017/7/26.
 */

/**
 * 重置新密码的model ,需要的请求参数,电话号码,验证码,密码,
 *
 *如果需要重置密码,首先需要验证手机号码,然后正确之后,将电话号码和验证码和密码一起作为参数请求
 *
 *
 */
public class ResetSaveRequest {
    public String phoneNumber;  //电话号码
    public String vCode;  //验证码

    public String password;  //密码
}
