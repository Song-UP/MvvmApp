package com.xiaomai.module_videoplay.video.media;

public class OnPlayerStateListener {
    /**
     * 播放出错
     */
    public void onPlayerStateError(String error){}

    /**
     * 播放暂停
     */
    public void onPlayerStatePause(){}

    /**
     * 播放暂停
     */
    public void onPlayerStateResume(){}

    /**
     * 开始播放
     */
    public void onPlayerStatePlaying(){}

    /**
     * 正在缓冲数据
     */
    public void onPlayerStatePreparing(){}

    /**
     * 播放完成
     */
    public void onPlayerStateEnded(){}

    /**
     * 没有可播放的文件
     */
    public void onPlayerStateIdle(){}
}

