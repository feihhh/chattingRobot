package fei.robot.message;

/**
 *
 * 整个message包下的是一个信息的json格式所需要的类，json格式如下：
 * {
 * 	 "reqType":0,
 *   "perception": {
 *         "inputText": {
 *             "text": "附近的酒店"
 *         }
 *     },
 *     "userInfo": {
 *         "apiKey": "",
 *         "userId": ""
 *     }
 * }
 */
public class InputText {
    // 我们聊天输入的内容
    private String text = "";

    public InputText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "InputText{" +
                "text='" + text + '\'' +
                '}';
    }
}
