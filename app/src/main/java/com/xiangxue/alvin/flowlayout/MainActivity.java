package com.xiangxue.alvin.flowlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable {

    private LinearLayout linearLayout;
    private FrameLayout frameLayout;
    private ViewPager viewPager;
    private ScrollView scrollView;
    private Fragment fragment;
    private Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = findViewById(R.id.sousuo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window = getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                //设置状态栏为透明，否则在部分手机上会呈现系统默认的浅灰色
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以考虑设置为透明色
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }

        text.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        MyRunnable myRunnable = new MyRunnable();
        myRunnable.main();
//        Thread th1 = new Thread(this);
//        Thread th2 = new Thread(this);
//        th1.start();
//        th2.start();

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            synchronized (this) {
                method();
            }
        }
    };
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            synchronized (this) {
                method();
            }
        }
    };

    private void method() {
        synchronized (this) {
            Log.i("sssss", "我是类锁的第二种形式，synchronize（*.class），我叫" + Thread.currentThread().getName() + ":");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("sssss", Thread.currentThread().getName() + "结束");
        }
    }

    @Override
    public void run() {

        synchronized (this) {

            Log.i("sssss", "我是类锁的第二种形式，synchronize（*.class），我叫" + Thread.currentThread().getName() + ":");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("sssss", Thread.currentThread().getName() + "结束");
        }
    }
}
