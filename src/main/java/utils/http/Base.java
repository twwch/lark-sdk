package utils.http;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

public class Base {
    private static final String JSON_CONTENT_TYPE = "application/json";

    public Object doGet(String url, Object params) {
        String json = HttpRequest.get(String.format("%s?%s", url, params.toString()))
                .header(Header.CONTENT_TYPE, JSON_CONTENT_TYPE)
                .timeout(20000)
                .execute().body();
        return JSONUtil.toBean(json, Object.class);
    }

    public static boolean isJSON2(String str) {
        boolean result = false;
        try {
            JSONUtil.parseObj(str);
            result = true;
        } catch (Exception ignored) {
        }
        return result;
    }

    public JSONObject doPost(String url, Object data, Map<String, String> headers) {
        if (headers == null) {
            headers = new HashMap<>();
            headers.put(Header.CONTENT_TYPE.toString(), JSON_CONTENT_TYPE);
        }
        JSONObject json = JSONUtil.parseObj(data, false);
        // System.out.println(json.toStringPretty());
        String result = HttpRequest.post(url)
                .header(Header.CONTENT_TYPE, JSON_CONTENT_TYPE)
                .headerMap(headers, false)
                .body(json.toString())
                .timeout(20000)
                .execute().body();
        if (isJSON2(result)) {
            return JSONUtil.parseObj(result);
        }
        return null;
    }
}
