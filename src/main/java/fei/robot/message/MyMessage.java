package fei.robot.message;

public class MyMessage {

    // reqType 默认是0
    private Integer reqType ;

    private Perception perception ;

    private UserInfo userInfo ;

    public MyMessage() {
    }

    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

    public Perception getPerception() {
        return perception;
    }

    public void setPerception(Perception perception) {
        this.perception = perception;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "reqType=" + reqType +
                ", perception=" + perception +
                ", userInfo=" + userInfo +
                '}';
    }
}
