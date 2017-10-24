package com.alg.ailigou.common.consts;

/**
 * Created by 海航
 * on 2017/9/1.
 * 此类或接口用于 int 的常数
 */

public class IntConsts {

    public final static int VCODE_REGISTER = 1;//短信验证码 注册
    public final static int VCODE_RESET_PWD = 2;//忘记密码(重置密码)
    public final static int VCODE_UPDATE_LOGIN_PWD = 3;//修改登陆密码
    public final static int VCODE_UPDATE_PAY_PWD = 4;//修改登陆密码
    public final static int VCODE_UPDATE_OLD_PHONE_NUMBER = 5;//修改手机号验证老号码
    public final static int VCODE_UPDATE_NEW_PHONE_NUMBER = 6;//修改手机号设置新号码(需要验证手机是否已经有了)
}
