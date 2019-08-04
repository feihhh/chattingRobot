package fei.robot.json;
import fei.robot.message.MyMessage;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * 这个类的主要功能：
 *  将要发送的消息封装到一个json对象中
 *  从json格式的字符串中提取我们想要的数据
 */

public class JsonUtil
{
     /**
     * 把一个自定义的java对象转为json格式的字符串
     * @param msg 自定义对象
     * @return 返回对象的json格式的字符串
     */
    public  String tuLingMsg2Json(MyMessage msg)
    {
        JSONObject json = JSONObject.fromObject(msg);
        return json.toString();
    }


    /**
     * 通过json字符串，获取里面的具体内容
     * @param jsonStr json格式的字符串
     */
    public  String tulingJson2Msg(String jsonStr)
    {
        JSONObject json = JSONObject.fromObject(jsonStr);
        Object[] results = json.getJSONArray("results").toArray();
//        System.out.println(results.length);
//        System.out.println(results[0]);
        JSONObject jsonObject = JSONObject.fromObject(results[0]);
        String res = jsonObject.get("values").toString().split(":")[1];
        String result = res.substring(1, res.length()-2);
//        System.out.println(result);
        return result;
    }

    public  String baiduJson2Msg(String baiduRet)
    {
        // {"result":["我要听歌。"],"err_msg":"success.","sn":"595702894021562321623","corpus_no":"6710120277879695727","err_no":0}
        JSONObject json = JSONObject.fromObject(baiduRet);
        int errNO = (int) json.get("err_no");
        String result ;
        if (errNO != 0) {
            result = "";
        }
        else {
            String tmp = json.get("result").toString();
            result = tmp.substring(2, tmp.length()-2);
        }
        return result;
    }

    public  void main(String[] args) {
        String str = "{\"emotion\":{\"robotEmotion\":{\"a\":0,\"d\":0,\"emotionId\":0,\"p\":0}," +
                "\"userEmotion\":{\"a\":0,\"d\":0,\"emotionId\":21500,\"p\":0}},\"intent\":{\"actionName\":\"\",\"code\":10004,\"intentName\":\"\"},\"results\":[{\"groupType\":1,\"resultType\":\"text\",\"values\":{\"text\":\"我是绝色美男，不要对我动歪脑筋喔。\"}}]}";
        System.out.println(str);
        tulingJson2Msg(str);
    }
}