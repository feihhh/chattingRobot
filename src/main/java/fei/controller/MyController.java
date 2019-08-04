package fei.controller;

import fei.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    public MyService service;

    @Autowired
    public ArrayList<String> list;

    // 刷新页面 展示聊天信息
    @RequestMapping("/chat.action")
    public ModelAndView chat()
    {
        ModelAndView m = new ModelAndView();
        m.addObject("list", list);
        m.setViewName("chat");
        return m;
    }

    // 发送文字信息
    @RequestMapping("/getSend.action")
    public String sendMsg(String inputValue, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        list.add(" 我 ："+inputValue);
        String s = new String(inputValue.getBytes(StandardCharsets.UTF_8));
        list.add("小艾 ：" + service.sendToRobot(s));
        return "forward:chat.action";
    }

    // 说话 请求到这
    @RequestMapping("/speak.action")
    public String getSpeak() throws Exception {
        List<String> l = service.getSpeakText();
        for (String str : l)
        {
            list.add(str);
        }
        return "forward:chat.action";
    }


}
