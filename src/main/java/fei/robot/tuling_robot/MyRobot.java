package fei.robot.tuling_robot;

import fei.robot.config.MyConfig;
import fei.robot.json.JsonUtil;
import fei.robot.message.InputText;
import fei.robot.message.MyMessage;
import fei.robot.message.Perception;
import fei.robot.message.UserInfo;
import java.io.*;
import java.net.*;

/**
 * 机器人类，主要功能有：
 *  1、接收要发送的数据，并发送给图灵机器人
 *  2、返回机器人回复的内容
 */
public class MyRobot {


    private JsonUtil jsonUtil = new JsonUtil();

    private static MyConfig config = new MyConfig();
    /**
     * 把 msg 发送给图灵机器人平台
     * @return 返回机器人会的信息
     */
    public  String sendContent(String msg){
        msg = makeMessage(msg);
        PrintWriter out = null;
        BufferedReader br = null;
        StringBuffer result = new StringBuffer();
        URL myUrl ;
        try {
            // 定义一个url
            myUrl  = new URL(config.getRobotUrl());
            // 打开和url之间的连接
            URLConnection connection = myUrl.openConnection();
            HttpURLConnection httpConn = (HttpURLConnection)connection;
            // 设置请求属性
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("x-adviewrtb-version", "2.1");
            // 发送post请求必须做如下设置
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);

            System.out.println("msg = " + msg);
            // 获取对应的输出流
            out = new PrintWriter(httpConn.getOutputStream());
            // 发送请求的内容
            out.write(msg);
            out.flush();
            httpConn.connect();
            // 利用BufferedReader类读取响应信息
//            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), "UTF-8");
            br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
//            char[] array = new char[1024];
//            int len = isr..read(array);
//            while((len=isr.read(array))!=-1){
//
//            }
            String line ;
            while ((line = br.readLine()) != null)
            {
                result.append(line);
            }
            System.out.println("========================="+result);

            // 获取响应状态码
            String str = new String(httpConn.getResponseCode()+"");
        } catch (Exception e) {
            System.out.println("数据发送异常...");
            e.printStackTrace();
        }finally {
            if (out != null)
            {
                out.close();
            }
            if (br != null)
            {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return (result == null || result.length()==0)?null:result.toString();
    }

    // 把输入的信息封装为一个请求信息
    private  String makeMessage(String msg)
    {
        MyMessage message = new MyMessage();
        Perception perception = new Perception();
        perception.setInputText(new InputText(msg));
        message.setPerception(perception);
        message.setUserInfo(new UserInfo(config));
        message.setReqType(config.getRobotReqType());
        return jsonUtil.tuLingMsg2Json(message);
    }

    /**
     * 或者返回的json字符串里面的具体内容
     * @param result
     */
    public  String getResultMsg(String result)
    {
        if (result != null)
        {
            // System.out.println(tulingJson2Msg(result));
            result =  jsonUtil.tulingJson2Msg(result);
            if (result.startsWith("请求次数超限制"))
            {
                return "今天太累了，不想和你说了，想我了我们明天再聊吧~";
            }
            return result;
        }
        else
        {
            return "虽然我不知道你在说什么，但是你傻乎乎的样子真可爱";
        }
    }

}
