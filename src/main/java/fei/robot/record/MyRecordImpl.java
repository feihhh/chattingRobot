package fei.robot.record;

import javazoom.jl.player.Player;
import org.springframework.stereotype.Component;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyRecordImpl implements MyRecord {

    // 存放当前系统时间 用作录音文件名
    private String time ;

    // 存放录音文件存放的路径
    private String path;

    public String getTime() {
        return time;
    }

    public String getPath()
    {
        return this.path;
    }

    public MyRecordImpl ()
    {
        time = this.getNowTime();
        // 获取当前工程的resources下的录音文件的路径
//        path = System.getProperty("user.dir") + File.separator+"src"+File.separator+"main"+File
//                 .separator+"resources"+File.separator+"record_msg"+File.separator+this.getTime()+".wav";
        path = "D:\\WorkPlace\\event\\chatting_robot\\src\\main\\resources\\record_msg" +
                "\\" + this.getNowTime()+".wav";
    }

    /**
     * 开始录音
     * 返回录音文件的路径
     */
    @Override
    public String record() throws Exception {
        File file = new File(getPath());

        /**
         * 关于下面的几个参数：
         * encoding           音频编码
         * sampleRate         采样频率
         * sampleSizeInBits   每个样本中的比特数
         * channels           声道数量
         * frameSize          每个帧中的字节数   =  （sampleSizeInBits/8) * channels
         * frameRate          每秒帧数
         * bigEndian          指示单个示例的数据是否按大端字节顺序存储
         */

        AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000.0F, 16, 1,
                2, 16000F, true);

//        AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 16000.0F, 16, 2,
//                4, 16000F, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
        targetDataLine.open(audioFormat);
        targetDataLine.start();
        new Thread(() -> {
            AudioInputStream inputStream = new AudioInputStream(targetDataLine);
            try {
                AudioSystem.write(inputStream, AudioFileFormat.Type.WAVE, file);
                System.out.println("over~~~");
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("请开始说话，按回车结束...");
        Thread.sleep(5000);
//        System.in.read();
        targetDataLine.close();
        return path;
    }


    @Override
    public void player(String path) throws Exception
    {
        /**
         * 下面这种播放录音 这个只能播放wav、 pcm 不能播放MP3 不使用这种，使用第三方jar包
         */
        Player player= new Player(new FileInputStream(path));
        player.play();
    }

    public void playerWav(String path) throws Exception
    {
        AudioInputStream in = AudioSystem.getAudioInputStream(new File(path));
        AudioFormat format = in.getFormat();
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);
        line.open(format);
        line.start();
        int readBytes = 0;
        byte[] bytes = new byte[1024];
        while ((readBytes = in.read(bytes)) > 0)
        {
            line.write(bytes, 0, readBytes);
        }
        line.drain();
        line.close();
    }

    /**
     * 获取当前系统时间
     * @return 返回由当前系统时间拼接成的字符串，作为露营 的文件名
     */
    public static String getNowTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
        Date date = new Date();
//        System.out.println(format.format(date));
        return format.format(date);
    }


    //==========================================方法测试=============================================================

    public static void main(String[] args) {
        // 测试获取当前时间的方法
//        MyRecordImpl m1 = new MyRecordImpl();
//        m1.getNowTime();
        // 测试获取当前路径
//        MyRecordImpl m2 = new MyRecordImpl();
//        System.out.println(System.getProperty("user.dir")+File.separator+m2.getPath());
        // 测试录音
           MyRecordImpl m3 = new MyRecordImpl();
           String path = null;
            try {
                path = m3.record();
            } catch (Exception e) {
                System.out.println("录音异常...");
                e.printStackTrace();
            }
//        //测试播放
//        MyRecordImpl m4 = new MyRecordImpl();
//        try {
//            m4.player("D:\\WorkPlace\\event\\chatting_robot\\src\\main\\resources\\response_msg\\2019_07_04-13_59_46.wav");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
