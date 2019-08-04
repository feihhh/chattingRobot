package fei.robot.test;

import fei.robot.baidu_speech.MySpeech;
import fei.robot.config.MyConfig;
import fei.robot.json.JsonUtil;
import fei.robot.record.MyRecord;
import fei.robot.record.MyRecordImpl;
import fei.robot.tuling_robot.MyRobot;
import fei.robot.util.MyUtils;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

/**
 * 测试类
 */
public class Test {

    public static MyRobot robot = new MyRobot();

    public static void testSpeak()
    {
        MyRecord record = new MyRecordImpl();
        MySpeech speech = new MySpeech();
        MyUtils utils = new MyUtils();
        Scanner s = new Scanner(System.in);
        MyRobot robot = new MyRobot();
        System.out.println("--------------- 图灵机器人 语音聊天 ---------------");
        while (true)
        {
            // 说话 -- > 语音转文字 --> 文字发给机器人 ---> 机器人返回消息 ---> 文字转语音
            //  ↑_________________________________________________________________↓
            try {
                String path = record.record(); // 录音，返回的是录音文件的路径
                String speak = speech.speech2Text(path); // 通过路径将语音文件转为文字，返回文字
                System.out.println("speak："+speak);
                if (!utils.ckeckMsg(speak.toUpperCase())) {
                    String resu = robot.
                            getResultMsg(robot.sendContent(speak));  // 把文字发给图灵机器人，返回机器人说的话
                    String retPath = speech.text2Speech(resu); // 把文字转为语音文件，返回文件路径
                    System.out.println("robot:" + resu); //打印机器人说的话
                    record.player(retPath); //播放机器人说的话
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void testText()
    {
        Scanner scanner  = new Scanner(System.in);
        while (true)
        {
            System.out.print(" wo :" );
            String worlds = scanner.nextLine();
            String resultMsg = robot.getResultMsg(robot.sendContent(worlds));
            System.out.println("robot : "+resultMsg);
        }
    }

    public static void main(String[] args) throws Exception{
        testText();
    }
}

