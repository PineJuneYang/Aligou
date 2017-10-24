package com.alg.ailigou.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by 海航
 * on 2017/7/22.
 * 此类或接口用于 去qq界面和某人聊天
 */

public class ToQQChatUtils {
    /**
     * @param qq qq号
     */
    public static void goToQQChatActivity(Context context, String qq) {
        String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin="+qq+"&version=1";
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
    }
}
