package com.xiaomai.module_videoplay.video;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.BaseMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.xiaomai.module_videoplay.video.media.IPlayerInterface;
import com.xiaomai.module_videoplay.video.media.OnNotWorkedCallback;
import com.xiaomai.module_videoplay.video.media.OnPlayerStateListener;

import java.io.File;

public class ExoVideoPlayerView extends PlayerView implements Player.EventListener, IPlayerInterface {
    /** 内部封装了 PlayerControlView(视频控制)，SubtitleView(字幕)，Surface(视频) **/
    PlayerView playerView;
    /**
     * 是播放的高级视图。它在播放期间显示视频，字幕和专辑封面，以及使用播放控件 PlayerControlView
     * 播放器，可以设置，SurfaceView， TextureView；addTextOutput：字幕接收 **/
    SimpleExoPlayer mPlayer;
//    PlayerControlView : 是用于控制播放的视图。它显示标准的播放控件，包括播放/暂停按钮，快进和快退按钮以及搜索栏。
    /** 需要播放的媒体;
     *  ExoPlayer库提供 MediaSourceDASH（DashMediaSource），SmoothStreaming（SsMediaSource），HLS（HlsMediaSource）和常规媒体文件（ProgressiveMediaSource）的实现 **/
    BaseMediaSource mediaSource;
    /**带宽估算值是使用计算的，并且每次更新 转移结束的时间。初始估算基于当前运营商的网络国家代码或用户的语言环境，以及网络连接类型。可以在 **/
    DefaultBandwidthMeter banwidthMetra;

    private DataSource.Factory dataSourceFactory;

    OnPlayerStateListener mOnPlayerStateListener;
    OnNotWorkedCallback mOnNotWorkedCallback;

    //当前播放状态
    private int mPlaybackState;

    private static final String DOWNLOAD_CONTENT_DIRECTORY = "downloads";

    private HandlerThread mHandlerThread;
    private Handler mHandler;
    private String videoUrl = null;

    public ExoVideoPlayerView(@NonNull Context context) {
        this(context, null);
    }

    public ExoVideoPlayerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExoVideoPlayerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        LayoutInflater.from(context).inflate(R.layout.video_surface_layout,this);
//        playerView = findViewById(R.id.pv_view);
        playerView = this;
        initView();
    }

    private void initView(){
        banwidthMetra = DefaultBandwidthMeter.getSingletonInstance(getContext());
        initMediaPlayer();
    }

    /**
     * 构建MediaSource，设置支持的dash，HLS等
     */
    public BaseMediaSource buildMediaSource(String url, String overrideExtension){
        BaseMediaSource mediaSource = null;
        if (TextUtils.isEmpty(url)){
            return null;
        }
        try {
            Uri mUri = Uri.parse(url);
            if (mUri == null)
                return null;
            if (dataSourceFactory == null) {
                //数据传输以及文件缓存
                dataSourceFactory = buildDataSourceFactory(null);
            }

            /** 获取路径中已解码的最后一段。 返回解码后的最后一段；如果路径为空，则返回null
             * 根据结尾 判断用什么视频解析库
             **/
            String segment = mUri.getLastPathSegment();
            if (segment == null){
                segment = "";
            }
            int type = Util.inferContentType(!TextUtils.isEmpty(overrideExtension)?"."+overrideExtension : segment);
            switch (type) {
                case C.TYPE_SS: //为Smooth Streaming manifests 而返回的值
                    mediaSource = new SsMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(mUri);
                    break;
                case C.TYPE_DASH: //使用dash的解析库
                    mediaSource = new DashMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(mUri);
                    break;
                case C.TYPE_HLS: //使用hls解析库 播放m3u8视频
                    mediaSource = new HlsMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(mUri);
                    break;
//                case C.TYPE_OTHER: // 用于DASH，HLS或DASH以外的文件
//                    mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mUri);
//                    break;
                default: //默认解析  MP4等(本地数据) 渐进式媒体文件
                    mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mUri);
                    break;
            }
            //SingleSampleMediaSource : 最常用于侧面加载字幕文件
            //合成实现更复杂的播放功能:
            // ConcatenatingMediaSource: 组合MediaSoure,实现顺序播放，也可以移动播放顺序
            // ClippingMediaSource:剪辑视频， LoopingMediaSource:循环播放视频,
            // MergingMediaSource:合并视频，可以用于侧面加载字幕文件
        }catch (Exception e){
            e.printStackTrace();
        }
        return mediaSource;
    }

    /**
     * @param listener : 数据传输事件的侦听器
     * @return
     */
    private DataSource.Factory buildDataSourceFactory(TransferListener listener){
        String userAgent = Util.getUserAgent(getContext().getApplicationContext(), "ExoPlayer");
        DefaultHttpDataSourceFactory httpDataSource = new DefaultHttpDataSourceFactory(userAgent, listener);
        //这个没啥用的
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(getContext().getApplicationContext(), listener, httpDataSource);
        return buildReadOnlyCacheDataSource(defaultDataSourceFactory,getDownloadCache());
    }

    private CacheDataSourceFactory buildReadOnlyCacheDataSource(DefaultDataSourceFactory upstreamFactory, Cache cache){
        return new CacheDataSourceFactory(
                cache, upstreamFactory,
                new FileDataSource.Factory(), null,
                CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR, null);
    }
    private Cache downloadCache;
    private synchronized Cache getDownloadCache() {
        if (downloadCache == null){
            File downloadContentDirectory = new File(getDownloadDirectory(), DOWNLOAD_CONTENT_DIRECTORY);
            //      NoOpCacheEvictor : 不在缓存数据了
            downloadCache = new SimpleCache(downloadContentDirectory, new NoOpCacheEvictor());
        }
        return downloadCache;
    }

    private File downloadDirectory;
    private File getDownloadDirectory() {
        if (downloadDirectory == null) {
            downloadDirectory = getContext().getApplicationContext().getExternalFilesDir(null);
            if (downloadDirectory == null) {
                downloadDirectory = getContext().getApplicationContext().getFilesDir();
            }
        }
        return downloadDirectory;
    }

    private void initMediaPlayer(){
        if (mPlayer != null){
            releasePlayer();
        }
        if (getContext() == null){
            callbackNotWork();
            return;
        }
        mHandlerThread = new HandlerThread("ExoPlayerSurface");
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());

        mPlayer = new SimpleExoPlayer.Builder(getContext())
//                .setTrackSelector(buildTrackSelect())
                .setBandwidthMeter(banwidthMetra)
                .build();
        playerView.setPlayer(mPlayer);
        mPlayer.addListener(this);
        /** 是否允许播放控件自动显示 **/
        playerView.setControllerAutoShow(false);
        playerView.hideController();
        playerView.showController();
        playerView.setUseController(false);
    }
    private void callbackNotWork() {
        if (mOnNotWorkedCallback != null) {
            mOnNotWorkedCallback.callbackWhenNotWorked();
        }
    }

    /**
     * 选择音轨
     */
    private DefaultTrackSelector buildTrackSelect(){
        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(getContext());
        defaultTrackSelector.setParameters(defaultTrackSelector
                        .buildUponParameters()
                        .setMaxVideoSizeSd()
                        .setPreferredAudioLanguage("deu")); //选择德语轨道
        return defaultTrackSelector;

    }

    /*******************  player监听  ****************************/
    /**
     *  @param playWhenReady 准备就绪后是否继续播放。
     *  @paramplayingState新的{@link State播放状态}。
     * */
    boolean isRetryState = false;
    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        mHandler.removeCallbacks(stateIdle);
        switch (playbackState){
            case ExoPlayer.STATE_IDLE: //没有可播放的视频和或者视频播放失败
                if (!isRetryState){
                    retryPlay();
                }else {
                    mHandler.postDelayed(stateIdle, 1000*6);
                }
                isRetryState = true;
                break;

            case ExoPlayer.STATE_BUFFERING: //正在缓存
                if (mOnPlayerStateListener != null){
                    mOnPlayerStateListener.onPlayerStatePreparing();
                }
                break;

            case ExoPlayer.STATE_READY://开始播放
                if(mOnPlayerStateListener != null) {
                    if(mPlayer.getPlayWhenReady())
                        mOnPlayerStateListener.onPlayerStatePlaying();
                    else
                        mOnPlayerStateListener.onPlayerStatePause();

                }
                isRetryState = false;
                break;

            case ExoPlayer.STATE_ENDED: //播放完
                if (mOnPlayerStateListener != null)
                    mOnPlayerStateListener.onPlayerStateEnded();
                break;
            default:
                break;
        }
        mPlaybackState = playbackState;
        Log.i("PlayerSurface=", "playbackState==" + playbackState);
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        mPlaybackState = -1;
        mPlayer.setPlayWhenReady(false);
        if (mOnPlayerStateListener != null){
            mOnPlayerStateListener.onPlayerStateError(error.getMessage());
        }
    }

    private Runnable stateIdle = new Runnable() {
        @Override
        public void run() {
            if (getContext() == null) {
                return;
            }
            if (mPlayer == null || mPlayer.isLoading() || mPlayer.isPlaying()) {
                return;
            }
            if (mPlaybackState == ExoPlayer.STATE_IDLE) {
                mPlayer.setPlayWhenReady(false);
                //播放失败和没有可播放的
                if (mOnPlayerStateListener != null) {
                    mOnPlayerStateListener.onPlayerStateIdle();
                }
            }
        }
    };

    /**
     * 检测播放何时过渡到其他项目
     * @param reason
     */
    @Override
    public void onPositionDiscontinuity(int reason) {
        //reason = Player.DISCONTINUITY_REASON_PERIOD_TRANSITION : 当播放自动从一项过渡到另一项时，会发生这种情况。
        //reason = Player.DISCONTINUITY_REASON_SEEK。当当前播放项作为搜索操作的一部分而发生更改时（例如，在调用时），会发生这种情况 Player.next。
        //reason = Player.TIMELINE_CHANGE_REASON_DYNAMIC。当播放列表发生更改（例如，添加，移动或删除项目）时，就会发生这种情况。

        //Player.getCurrentWindowIndex和的 方法来完成Player.getCurrentTag。如果您只想检测播放列表项的更改，
        // 则有必要与最新的已知窗口索引或标记进行比较，因为提到的事件可能由于其他原因而触发。
    }

    /*******************  供外部的调用  ****************************/
    @Override
    public void setPlayUrl(String url) {
        //url = "https://vdse.bdstatic.com/e0c2f7ae496b212fb511c1b8970bdc07.mp4?authorization=bce-auth-v1%2Ffb297a5cc0fb434c971b8fa103e8dd7b%2F2017-05-11T09%3A02%3A31Z%2F-1%2F%2F1b938745bce143d0d302a33e6e9995e764dd6c5b65b1a73a0c367608cf52e339";
        if (TextUtils.isEmpty(url)){
            return;
        }
        mediaSource = null;
        videoUrl = url;
    }

    boolean mReady;
    @Override
    public void setReady(boolean ready) { mReady = ready; }

    @Override
    public void pausePlay() {
        if (mPlayer != null){
            mPlayer.setPlayWhenReady(false);
            if (mOnPlayerStateListener != null){
                mOnPlayerStateListener.onPlayerStatePause();
            }
        }
    }

    @Override
    public void resumePlay() {
        if (mPlayer != null){
            mPlayer.setPlayWhenReady(true);
            if (mOnPlayerStateListener != null){
                mOnPlayerStateListener.onPlayerStateResume();
            }
        }
    }

    @Override
    public void startPlay(boolean reset) {
        if (mPlayer == null){
            initMediaPlayer();
        }
        if (mediaSource == null){
            mediaSource = buildMediaSource(videoUrl,null);
            if (mediaSource != null){
                if (reset){
                    mPlayer.prepare(mediaSource,true,true);
                }else {
                    mPlayer.prepare(mediaSource);
                }
                mPlayer.setPlayWhenReady(mReady);//自动播放
            }
        } else {
            if (reset){
                mPlayer.prepare(mediaSource,true,true);
            }else {
                mPlayer.prepare(mediaSource);
            }
            //播放完成
            if (mPlayer.getPlaybackState() == Player.STATE_ENDED){
                //代表未设定或未知时间或持续时间的特殊常数。适用于任何时基。
                mPlayer.seekTo(mPlayer.getCurrentWindowIndex(),C.TIME_UNSET);
            }
            mPlayer.setPlayWhenReady(mReady);
        }
        if (mediaSource == null || getContext() == null){
            callbackNotWork();
        }
    }

    @Override
    public void replay() { startPlay(true); }

    @Override
    public void retryPlay() {
        if (mPlayer != null){
            mPlayer.retry();
        }
    }

    @Override
    public void setOnPlayerStateListener(OnPlayerStateListener onPlayerStateListener) {
        this.mOnPlayerStateListener = onPlayerStateListener;
    }

    @Override
    public void setOnNotWorkedCallback(OnNotWorkedCallback notWorkedCallback) {
        this.mOnNotWorkedCallback = notWorkedCallback;
    }
    /** 释放资源 */
    @Override
    public void releasePlayer() {
        if (mPlayer != null){
            mPlayer.release();
            mPlayer = null;
        }
        if (mHandlerThread != null){
            mHandlerThread.quit();
        }
        if (downloadCache != null){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    downloadCache.release();
                }
            }.start();
        }
        mHandlerThread = null;
        mHandler = null;
    }


    /********************** view回调方法 *****************************/
    @Override
    protected void onDetachedFromWindow() {
        releasePlayer();
        super.onDetachedFromWindow();
    }
}