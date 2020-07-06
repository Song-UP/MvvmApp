package com.xiaomai.module_videoplay.video.media;

public interface IPlayerInterface {

    /**
     * 播放地址
     *
     * @param url
     */
    void setPlayUrl(String url);

    /**
     * 是否自己播放
     *
     * @param ready
     */
    void setReady(boolean ready);

    /**
     * 停止播放
     */
    void pausePlay();

    /**
     * 恢复播放
     */
    void resumePlay();

    /**
     * 开始播放
     *
     * @param reset 是否重置,从头开始播放
     */
    void startPlay(boolean reset);

    /**
     * 重新播放
     */
    void replay();

    /**
     * 重试播放
     */
    void retryPlay();

    /**
     * 播放器 状态变化 回调
     *
     * @param onPlayerStateListener
     */
    void setOnPlayerStateListener(OnPlayerStateListener onPlayerStateListener);

    /**
     * 不管什么情况 不能正常播放时回调
     *
     * @param notWorkedCallback
     */
    void setOnNotWorkedCallback(OnNotWorkedCallback notWorkedCallback);

    void releasePlayer();
}
