import auth.LarkClient;
import cn.hutool.json.JSONObject;
import org.junit.Test;
import params.msg.card.CardParams;

public class LarkClientTest {

    @Test
    public void testSendMsg(){
        try {
            String appId = "cli_9e6b64b262b4900d";
            String appSecret = "le41w0sV86eo1U6WjNG3HhSVIeAjv3HJ";
            LarkClient client = new LarkClient(appId, appSecret);
            String html = "{\n" +
                    "  \"config\": {\n" +
                    "    \"wide_screen_mode\": true\n" +
                    "  },\n" +
                    "  \"header\": {\n" +
                    "    \"title\": {\n" +
                    "      \"tag\": \"plain_text\",\n" +
                    "      \"content\": \"你有一个休假申请待审批\"\n" +
                    "    }\n" +
                    "  },\n" +
                    "  \"elements\": [\n" +
                    "    {\n" +
                    "      \"tag\": \"div\",\n" +
                    "      \"fields\": [\n" +
                    "        {\n" +
                    "          \"is_short\": true,\n" +
                    "          \"text\": {\n" +
                    "            \"tag\": \"lark_md\",\n" +
                    "            \"content\": \"**申请人**\\n王晓磊\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"is_short\": true,\n" +
                    "          \"text\": {\n" +
                    "            \"tag\": \"lark_md\",\n" +
                    "            \"content\": \"**休假类型：**\\n年假\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"is_short\": false,\n" +
                    "          \"text\": {\n" +
                    "            \"tag\": \"lark_md\",\n" +
                    "            \"content\": \"\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"is_short\": false,\n" +
                    "          \"text\": {\n" +
                    "            \"tag\": \"lark_md\",\n" +
                    "            \"content\": \"**时间：**\\n2020-4-8 至 2020-4-10（共3天）\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"is_short\": false,\n" +
                    "          \"text\": {\n" +
                    "            \"tag\": \"lark_md\",\n" +
                    "            \"content\": \"\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"is_short\": true,\n" +
                    "          \"text\": {\n" +
                    "            \"tag\": \"lark_md\",\n" +
                    "            \"content\": \"**备注**\\n因家中有急事，需往返老家，故请假\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"tag\": \"hr\"\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"tag\": \"action\",\n" +
                    "      \"layout\": \"bisected\",\n" +
                    "      \"actions\": [\n" +
                    "        {\n" +
                    "          \"tag\": \"button\",\n" +
                    "          \"text\": {\n" +
                    "            \"tag\": \"plain_text\",\n" +
                    "            \"content\": \"批准\"\n" +
                    "          },\n" +
                    "          \"type\": \"primary\",\n" +
                    "          \"value\": {\n" +
                    "            \"chosen\": \"approve\"\n" +
                    "          }\n" +
                    "        },\n" +
                    "        {\n" +
                    "          \"tag\": \"button\",\n" +
                    "          \"text\": {\n" +
                    "            \"tag\": \"plain_text\",\n" +
                    "            \"content\": \"拒绝\"\n" +
                    "          },\n" +
                    "          \"type\": \"primary\",\n" +
                    "          \"value\": {\n" +
                    "            \"chosen\": \"decline\"\n" +
                    "          }\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";
            CardParams params = new CardParams();
            params.setContent(html);
            params.setMsg_type("interactive");
            // params.setReceive_type("user_id");
            params.setReceive_type("chat_id");
            // params.setReceive_id("f95facg1");
            params.setReceive_id("oc_2d9f09c5a8552d88c6960a6eeb2bcc0b");
            JSONObject a = client.sendMessage(params);
            System.out.println(a);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
