package com.alg.ailigou.library.utils;

import java.util.UUID;

/**
 * Created by hai on 2017/2/16.
 */

public class UUIDUtils {
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
