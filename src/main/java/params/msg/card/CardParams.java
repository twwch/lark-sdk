package params.msg.card;

import lombok.Data;

@Data
public class CardParams {
    private String receive_id;
    private String content;
    private String msg_type;
    private String receive_type;
}
