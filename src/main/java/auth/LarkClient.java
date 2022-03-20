package auth;

import cn.hutool.json.JSONObject;
import consts.url.LarkConst;
import params.msg.card.CardParams;
import utils.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class LarkClient {

    private String tenant_token;
    private String app_token;
    private Map<String, String> authedHeader = new HashMap<>();

    public HttpUtils httpUtils = new HttpUtils();

    private void initTenantToken(String appId, String appSecret) {
        Map<String, String> data = new HashMap<>();
        data.put("app_id", appId);
        data.put("app_secret", appSecret);
        JSONObject resp = httpUtils.doPost(LarkConst.TENANT_TOKEN, data, null);
        System.out.println(resp.toStringPretty());
        if (resp.getInt("code") == 0) {
            this.tenant_token = resp.getStr("tenant_access_token");
            return;
        }
        throw new RuntimeException("初始化TenantToken失败！" + resp.toStringPretty());
    }

    private void initAppToken(String appId, String appSecret) {
        Map<String, String> data = new HashMap<>();
        data.put("app_id", appId);
        data.put("app_secret", appSecret);
        JSONObject resp = httpUtils.doPost(LarkConst.APP_TOKEN, data, null);
        // System.out.println(resp.toStringPretty());
        if (resp.getInt("code") == 0) {
            String tenant_access_token = resp.getStr("tenant_access_token");
            this.app_token = resp.getStr("app_access_token");
            this.tenant_token = resp.getStr(tenant_access_token);
            this.authedHeader.put("Authorization", String.format("Bearer %s", tenant_access_token));
            return;
        }
        throw new RuntimeException("初始化AppToken失败！" + resp.toStringPretty());
    }

    public LarkClient(String appId, String appSecret) throws Exception {
        // this.initTenantToken(appId, appSecret);
        this.initAppToken(appId, appSecret);
    }

    public JSONObject sendMessage(CardParams params) {
        String url = String.format("%s?receive_id_type=%s", LarkConst.SEND_MSG, params.getReceive_type());
        return httpUtils.doPost(url, params, this.authedHeader);
    }

}
