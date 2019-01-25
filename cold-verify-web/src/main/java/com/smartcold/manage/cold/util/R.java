package com.smartcold.manage.cold.util;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object>  {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 200);
        put("msg", "Success");
    }

    public static R newFailure() {
        return newFailure(500, "操作失败");
    }

    public static R newFailure(String msg) {
        return newFailure(500, msg);
    }

    public static R newFailure(int code, String msg) {
        return new R().put("code", code).put("msg", msg);
    }

    public static R newSuccess(String msg) {
       return new R().put("msg", msg);
    }

    public static R newData(Object data) {
        return R.newSuccess().put("data", data);
    }

    public static R newPage(Object page) {
        return R.newSuccess().put("page", page);
    }


    public static R newSuccess(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R newSuccess() {
        return new R();
    }

    public static R error401() { return newFailure(401, "你还没有登录"); }
    public static R error402() { return newFailure(402, "会话失效!"); }
    public static R error403() {
           return newFailure(403, "你没有访问权限");
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
