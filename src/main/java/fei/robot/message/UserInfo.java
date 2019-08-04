package fei.robot.message;

import fei.robot.config.MyConfig;

public class UserInfo {

    // apiKey
    private String apiKey ;
    // userid
    private String userId ;

    public UserInfo(MyConfig config) {
        apiKey = config.getRobotApiKey();
        userId = config.getOrbotUserId();
    }

    public UserInfo(String apiKey, String userId) {
        this.apiKey = apiKey;
        this.userId = userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "apiKey='" + apiKey + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
