package com.sogou.bizdev.muse.adcoreweb.app.common;

import net.sf.json.JSONArray;

public class JSONUtils {
    public static String toJsonString(Object data) {
        if (data == null) {
            return "[]";
        }
        return JSONArray.fromObject(data instanceof String ? new String[]{data.toString()} : data).toString();
    }
}
