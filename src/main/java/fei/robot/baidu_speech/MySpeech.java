package fei.robot.baidu_speech;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import fei.robot.config.MyConfig;
import fei.robot.json.JsonUtil;
import fei.robot.record.MyRecordImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
/**
 * 这个类的主要功能：
 *  通过调用百度的语音识别和语音合成，实现将语音转文字，将文字转语音
 */

public class MySpeech {


    private MyConfig config = new MyConfig();
 ;
    private JsonUtil jsonUtil = new JsonUtil();

    private String baiduAppId ;
    private String baiduApiKey ;
    private String baiduSecretKey ;

    public MySpeech ()
    {
        baiduApiKey = config.getBaiduApiKey();
        baiduAppId = config.getBaiduAppId();
        baiduSecretKey = config.getBaiduSecretKey();
    }

    /**
     * 调用百度的语音识别 实现将语音转文字
     * @param path 存放语音文件的路径
     * @return 返回由语音转成的文字
     */
    public String speech2Text(String path) {
        AipSpeech client = new AipSpeech(baiduAppId, baiduApiKey, baiduSecretKey);
        String result = client.asr(path, "wav", 16000, null).toString();
        String ret = jsonUtil.baiduJson2Msg(result);
        return ret;
    }

    //将文字转为语音
    public String text2Speech(String text) throws IOException {
        String path = "D:\\WorkPlace\\event\\chatting_robot\\src\\main\\resources\\response_msg" +
                "\\" + MyRecordImpl.getNowTime()+".mp3";
        AipSpeech aipSpeech = new AipSpeech(baiduAppId, baiduApiKey, baiduSecretKey);
        TtsResponse res = aipSpeech.synthesis(text, "zh", 1, null);
        byte[] data =res.getData();
        if (data != null)
        {
            Util.writeBytesToFileSystem(data, path );
            return path;
        }
        else {
            System.out.println("转换有问题...");
            return null;
        }
    }
}
