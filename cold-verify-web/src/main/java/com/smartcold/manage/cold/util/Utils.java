package com.smartcold.manage.cold.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C) SmartCold 版权所有
 *
 * @author MaQiang34
 * @Description: 基类
 * @createDate:2018-12-07 19:43
 * @version:V1.0
 */
public class Utils {
    public static final Gson GSON = new GsonBuilder().create();
    public static final DecimalFormat DF = new DecimalFormat("#.00");
    public static final Type MAPTYPE = new TypeToken<Map<String, Object>>() {}.getType();
    public static final Type LISTMAPTYPE = new TypeToken<List<Map<String, Object>>>() {}.getType();

    public static boolean isNull(final String str) {
        return str == null || str.isEmpty();
    }
    public static boolean isnotNull(final String str) {
        return str != null && !str.isEmpty();
    }
    public static boolean isnotNullList(Collection list) {
        return list != null && !list.isEmpty();
    }
    public static boolean isNullList(Collection list) { return list == null || list.isEmpty(); }
    public static boolean isnotNullMap(Map map) { return map != null && !map.isEmpty(); }
    public static boolean isNullMap(Map map) { return map == null || map.isEmpty(); }

}
