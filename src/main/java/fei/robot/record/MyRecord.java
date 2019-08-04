package fei.robot.record;


import org.springframework.stereotype.Component;

/**
 * 进行录音与音频播放的相关操作
 */

public interface MyRecord {

    // 开始录音
    String record () throws Exception;

    // 播放录音
    void player(String path) throws Exception ;

    // 播放wav格式的音频
    void playerWav(String path) throws Exception;

}
