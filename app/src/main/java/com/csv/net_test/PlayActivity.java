package com.csv.net_test;

import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

import com.csv.net_test.app.BaseApplication;
import com.csv.net_test.utils.JniUtil;
import com.jovision.jvplay.Jni;

public class PlayActivity extends AppCompatActivity {

    static {
        System.loadLibrary("jvplay");
        Log.i("TAG", "static initializer: so库已加载");
    }

    private static SurfaceView surfaceViewPlay;
    //    private String JvmpUrl = "jvmp://121.36.92.161:7070/live/2102412464WLL4007607?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaGFubmVsX2lkIjowLCJkZXZpY2VfaWQiOiIyMTAyNDEyNDY0V0xMNDAwNzYwNyIsImV4cGlyZV90aW1lIjoxNjEyNDgwNDY1LCJzdHJlYW1fdHlwZSI6MCwidXNlcl90eXBlIjoiRU5URVJQUklTRSJ9.GxCcCG136-IwoIxM5p9gy8t-xyuHQkNxL2qggg0hfmQ";
    private String JvmpUrl = "jvmp://121.36.92.161:7070/live/2102412464WLL4007607?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjaGFubmVsX2lkIjowLCJkZXZpY2VfaWQiOiIyMTAyNDEyNDY0V0xMNDAwNzYwNyIsImV4cGlyZV90aW1lIjoxNjEyNjMyNjUyLCJzdHJlYW1fdHlwZSI6MCwidXNlcl9pZCI6IjE3NDM2NzE3MDcyMDIxMDEyNjE0NTcwMCIsInVzZXJfdHlwZSI6IkVOVEVSUFJJU0UifQ.MJL2JISFbwhJ5SiLz7y6HURzGHZfim4XdPrFdO5OO3w";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initHoloPlayer();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releseHoloPlayer();
    }

    private void initView() {
        surfaceViewPlay = findViewById(R.id.surfaceView_play);

//        JniUtil.holosensPlayerConnectByP2p(JvmpUrl,0,0,0);
//        JniUtil.connect(,JvmpUrl);

        /**
         * 建立连接
         */
        int playId = Jni.holosensPlayerConnect(JvmpUrl, 1, 1);
        Log.i("TAG", "playId的值: " + playId);
        if (playId > 0) {
            Log.i("TAG", "initView: 连接成功");
        }

        /**
         * 显示视频
         */
        //show(playId);
    }

    public static void show(int playId) {
        Log.i("TAG", "show: 显示实时流");
        Jni.holosensPlayerShow(playId,
                surfaceViewPlay.getHolder().getSurface(),
                0,
                0,
                surfaceViewPlay.getHolder().getSurfaceFrame().width(),
                surfaceViewPlay.getHolder().getSurfaceFrame().height()
        );
        Log.i("TAG", "show: 显示实时流2");
    }

    /**
     * 初始化播放库
     */
    private void initHoloPlayer() {
        JniUtil.holosensPlayerInit(BaseApplication.getInstance());
        BaseApplication.getInstance().playerIdWindowMap.clear();
        JniUtil.windowPlayerIdMap.clear();
    }

    /**
     * 释放播放库
     */
    private void releseHoloPlayer() {
        JniUtil.holosensPlayerRelease();
    }

}