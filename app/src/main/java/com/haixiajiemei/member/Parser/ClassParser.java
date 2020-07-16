package com.haixiajiemei.member.Parser;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassParser {

    private static Gson gson() {
        return new GsonBuilder().create();
    }

    public static <T> T toData(String json, Class<T> type) throws JsonParseException {
        return toData(json, type, gson());
    }

    private static <T> T toData(String json, Class<T> type, Gson gson) throws JsonParseException {
        return gson.fromJson(json, type);
    }

    public static <T> List<T> toList(String json, Class<T> type) throws JsonParseException {
        Gson gson = gson();
        List<T> list = new ArrayList<>();

        if (!TextUtils.isEmpty(json)) {
            Object[] array = (Object[]) Array.newInstance(type, 1);
            array = gson.fromJson(json, array.getClass());

            for (Object object : array) {
                @SuppressWarnings("unchecked")
                T t = (T) object;
                list.add(t);
            }
        }

        return list;
    }

    public static Map<String, String> toMap(String json) {
        return gson().fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
    }

    public static <T> String toJson(T t) throws JsonParseException {
        return gson().toJson(t);
    }
}
