package com.cn.ray.rtmpdump.live.channel;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.cn.ray.rtmpdump.LivePusher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AudioChannel {
    private  AudioRecord audioRecord;
    private ExecutorService executors;
    private LivePusher mLivePusher;
    private int channels = 2;
    private boolean isLiving;

    public AudioChannel(LivePusher livePusher) {
        mLivePusher = livePusher;
        executors = Executors.newSingleThreadExecutor();
        int channelConfig;
        if (channels == 2) {
            channelConfig = AudioFormat.CHANNEL_IN_STEREO;
        } else {
            channelConfig = AudioFormat.CHANNEL_IN_MONO;
        }
        int minBufferSize = AudioRecord.getMinBufferSize(44100, channelConfig, AudioFormat.ENCODING_PCM_16BIT) * 2;
        audioRecord =new AudioRecord(MediaRecorder.AudioSource.MIC, 44100, channelConfig, AudioFormat.ENCODING_PCM_16BIT, minBufferSize);
    }

    public void startLive() {
        isLiving = true;
        executors.submit(new AudioTask());
    }

    public void stopLive() {
        isLiving = false;
    }

    public void release(){
        audioRecord.release();
    }

    class AudioTask implements Runnable{

        @Override
        public void run() {
            audioRecord.startRecording();
            while (isLiving){
             //   audioRecord.read()

            }

            audioRecord.stop();
        }
    }

}
