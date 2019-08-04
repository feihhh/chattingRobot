package fei.robot.config;

import org.springframework.stereotype.Component;


/**
 * 作为配置类，将配置一些主要参数
 */


public class MyConfig {

    // ====================  图灵机器人参数  ===================
    private String robotApiKey;
    private String orbotUserId;
    private int robotReqType;
    private String robotUrl;

    // ====================  百度语音参数  ==========================
    private String baiduAppId ;
    private String baiduApiKey ;
    private String baiduSecretKey ;

    public MyConfig() {
        // 图灵机器人
        robotApiKey = "e646171d19f44039b60f12141ec1ec67";
        orbotUserId = "473910";
        robotReqType = 0;
        robotUrl = "http://openapi.tuling123.com/openapi/api/v2";
        // 百度语音
        baiduAppId = "16708578";
        baiduApiKey = "2Fj7dA4CRVfW2rHWe4bykdIL";
        baiduSecretKey = "e52MrdkGxBeyEYODVWlP9iSR47A1XUBF" ;
    }

    public String getRobotApiKey() {
        return robotApiKey;
    }

    public void setRobotApiKey(String robotApiKey) {
        this.robotApiKey = robotApiKey;
    }

    public String getOrbotUserId() {
        return orbotUserId;
    }

    public void setOrbotUserId(String orbotUserId) {
        this.orbotUserId = orbotUserId;
    }

    public int getRobotReqType() {
        return robotReqType;
    }

    public void setRobotReqType(int robotReqType) {
        this.robotReqType = robotReqType;
    }

    public String getRobotUrl() {
        return robotUrl;
    }

    public void setRobotUrl(String robotUrl) {
        this.robotUrl = robotUrl;
    }

    public String getBaiduAppId() {
        return baiduAppId;
    }

    public void setBaiduAppId(String baiduAppId) {
        this.baiduAppId = baiduAppId;
    }

    public String getBaiduApiKey() {
        return baiduApiKey;
    }

    public void setBaiduApiKey(String baiduApiKey) {
        this.baiduApiKey = baiduApiKey;
    }

    public String getBaiduSecretKey() {
        return baiduSecretKey;
    }

    public void setBaiduSecretKey(String baiduSecretKey) {
        this.baiduSecretKey = baiduSecretKey;
    }

    @Override
    public String toString() {
        return "MyConfig{" +
                "robotApiKey='" + robotApiKey + '\'' +
                ", orbotUserId='" + orbotUserId + '\'' +
                ", robotReqType=" + robotReqType +
                ", robotUrl='" + robotUrl + '\'' +
                ", baiduAppId='" + baiduAppId + '\'' +
                ", baiduApiKey='" + baiduApiKey + '\'' +
                ", baiduSecretKey='" + baiduSecretKey + '\'' +
                '}';
    }

}
