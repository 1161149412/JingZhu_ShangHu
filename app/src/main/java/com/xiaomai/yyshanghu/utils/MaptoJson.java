package com.xiaomai.yyshanghu.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Map转Json字符串工具类
 *
 * */
public class MaptoJson {

    public static String toJson(String s, List<String> keylist, List<String> valulist) {
        Map<String, String> map = new HashMap<>();
        Map<String, Map<String, String>> map1 = new HashMap<>();
        for (int i = 0; i < keylist.size(); i++) {
            map.put(keylist.get(i), valulist.get(i));
        }
        map1.put(s, map);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map1);
        return jsonStr;
    }
}
