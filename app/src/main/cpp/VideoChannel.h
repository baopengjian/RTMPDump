//
// Created by PF0ZYBAJ on 2020-10-11.
//

#ifndef RTMPDUMP_VIDEOCHANNEL_H
#define RTMPDUMP_VIDEOCHANNEL_H
#include <inttypes.h>
#include <x264.h>
#include <pthread.h>

class VideoChannel {
public:
    VideoChannel();

    ~VideoChannel();
    //创建x264编码器
    void setVideoEncInfo(int width, int height, int fps, int bitrate);

private:
    pthread_mutex_t mutex;
    int mWidth;
    int mHeight;
    int mFps;
    int mBitrate;
    x264_t *videoCodec = 0;
    //图片
    x264_picture_t *pic_in = 0;
    int ySize;
    int uvSize;
    int index = 0;
};


#endif //RTMPDUMP_VIDEOCHANNEL_H
