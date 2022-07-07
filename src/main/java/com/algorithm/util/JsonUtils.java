package com.algorithm.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.MapSerializer;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", null);
        JSONObject object = new JSONObject();
        object.putAll(map);
        System.out.println(object.toString(SerializerFeature.WriteMapNullValue));
    }
}
