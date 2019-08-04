package fei.robot.util;

import fei.robot.baidu_speech.MySpeech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Component
public class MyUtils {

//    @Autowired
//    public MySpeech speech;

    MySpeech speech = new MySpeech();

    public boolean ckeckMsg(String msg) throws Exception {
        if (msg.startsWith("退出")) {
            myExit();
        }
        else if(msg.startsWith("打开") && msg.length()>2)
        {
            return openAppilcation(msg);
        }
        return false;
    }

    /**
     * 在map集合中找要打开的应用程序
     * @param msg 消息内容
     * @return 如果成功打开了应用程序，返回真；否则返回假
     */
    private boolean openAppilcation(String msg) throws Exception {
        Set<Map.Entry<String, String>> set = initOpenMap().entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        boolean flag = false;
        while (iterator.hasNext())
        {
            Map.Entry<String, String> entry = iterator.next();
            if (msg.contains(entry.getKey()))
            {
                flag = true;
                Runtime rt = Runtime.getRuntime();
                rt.exec(entry.getValue());
                break;
            }
        }
        return flag;
    }

    private  Map<String, String> initOpenMap() throws Exception {
        Map<String, String> appMap = new HashMap();
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\config\\open_app.txt";
        File file = new File(path);
        if (!file.exists())
        {
            return null;
        }
        Reader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null)
        {
            appMap.put(line.split("=")[0].toUpperCase(), line.split("=")[1]);
        }
        Set<Map.Entry<String, String>> entries = appMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> tmp = iterator.next();
            System.out.println(tmp.getKey()+":"+tmp.getValue());
        }
        return appMap;
    }

    private void myExit()
    {
        try {
            speech.text2Speech("好的,下次想我了再聊。");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
