package fei.service;

import org.springframework.stereotype.Component;

import java.util.List;


public interface MyService {

    String sendToRobot(String msg);

    List<String> getSpeakText() throws Exception;

}
