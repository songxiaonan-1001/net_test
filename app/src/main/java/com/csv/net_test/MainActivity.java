package com.csv.net_test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.csv.net_test.app.BaseApplication;
import com.csv.net_test.utils.JniUtil;

/**
 * @author CSV
 */
public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("jvplay");
    }

    private static final String TAG = "MainActivity";
    /**
     * 用户ID,由数字组成,长度为15~25个字符
     */
    private String userId = "174367170720210126145700";
    private String ak = "178661947120210128195511";
    private String sk = "178661947120210128195511693dc92939ac43a9ac5ee834bfb701e7";
    private VideoView videoViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPlayer();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initPlayer() {
        JniUtil.holosensPlayerInit(BaseApplication.getInstance());
        BaseApplication.getInstance().playerIdWindowMap.clear();
        JniUtil.windowPlayerIdMap.clear();
    }

    //退出
    private long exitTime = 0;
    private boolean isKillProcess;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            //距离上次按返回键时间大于2s,提示再按一次退出
            isKillProcess = false;
            Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            if (isKillProcess) {
                return;
            }
            isKillProcess = true;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            MainActivity.this.finish();
            System.exit(0);
        }
    }
}