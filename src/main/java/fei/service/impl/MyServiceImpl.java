package fei.service.impl;

import fei.robot.baidu_speech.MySpeech;
import fei.robot.record.MyRecord;
import fei.robot.record.MyRecordImpl;
import fei.robot.tuling_robot.MyRobot;
import fei.service.MyService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyServiceImpl implements MyService {

    MySpeech speech = new MySpeech();

    MyRecord record = new MyRecordImpl();

    MyRobot robot = new MyRobot();

    @Override
    public String sendToRobot(String msg) {
        return robot.getResultMsg(robot.sendContent(msg));
    }

    @Override
    public List<String> getSpeakText() throws Exception {
        List<String> list = new ArrayList<>();
        String path = record.record(); // 录音
        String speak = speech.speech2Text(path);
        list.add("我 ：" + speak);
        String robotSpeak = robot.getResultMsg(robot.sendContent(speak));
        String retPath = speech.text2Speech(robotSpeak);
        record.player(retPath);
        list.add("小艾 ：" + robotSpeak);
        return list;
    }
}
