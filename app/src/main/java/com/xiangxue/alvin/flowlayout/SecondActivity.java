package com.xiangxue.alvin.flowlayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiangxue.alvin.flowlayout.threads.MainTask;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zhuliang on 2019/11/28.
 */

public class SecondActivity extends AppCompatActivity {
    private TextView textView;
    private ProgressBar progressBar;
    private MyTextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        textView = findViewById(R.id.text);
        progressBar = findViewById(R.id.progressbar);
        myTextView=findViewById(R.id.anim);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  //透明状态栏

        initView();

    }

    private void initView() {

//        doDownload();

    }

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    int size=msg.getData().getInt("size");
                    int all=mainTask.getAllSize();
                    textView.setText((msg.getData().getInt("size")*100/mainTask.getAllSize())+"%");
                    progressBar.setProgress(msg.getData().getInt("size"));
                    break;
            }
        }
    };

    private MainTask mainTask;

    private void doDownload() {
        // 获取SD卡路径
        String path = Environment.getExternalStorageDirectory()
                + "/amosdownload/";
        File file = new File(path);
        // 如果SD卡目录不存在创建
        if (!file.exists()) {
            file.mkdir();
        }
        // 设置progressBar初始化
        progressBar.setProgress(0);

        // 简单起见，我先把URL和文件名称写死，其实这些都可以通过HttpHeader获取到
        String downloadUrl = "http://gdown.baidu.com/data/wisegame/91319a5a1dfae322/baidu_16785426.apk";
        String fileName = "baidu_16785426.apk";
        int threadNum = 5;
        String filepath = path + fileName;

        mainTask = new MainTask(5, downloadUrl, filepath, progressBar, myHandler);
        mainTask.start();

    }

}
