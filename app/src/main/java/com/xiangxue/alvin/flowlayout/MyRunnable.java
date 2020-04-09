package com.xiangxue.alvin.flowlayout;

import android.os.Environment;
import android.util.Log;
import android.view.ViewGroup;

import java.io.File;

/**
 * Created by zhuliang on 2019/11/27.
 */

public class MyRunnable implements Runnable {
    static MyRunnable instanec = new MyRunnable();
    static MyRunnable instanec2 = new MyRunnable();

    public void main() {
        Thread th1 = new Thread(runnable);
        Thread th2 = new Thread(runnable1);
        th1.start();
        th2.start();
        while (th1.isAlive() || th2.isAlive()) {
        }
//        Log.i("sssss", "finished");
    }


    @Override
    public void run() {
        method();
//        synchronized (MyRunnable.class)
//        {
//            Log.i("sssss", "我是类锁的第二种形式，synchronize（*.class），我叫" + Thread.currentThread().getName() + ":");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Log.i("sssss", Thread.currentThread().getName() + "结束");
//        }
//            meth();
    }


    SchoolNode parent=new SchoolNode();
    SchoolNode child=new SchoolNode();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                parent.addChildOnly(child);
//                child.setParentOnly(parent);
        }
    };
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                parent.addChildOnly(child);
                child.setParentOnly(parent);
        }
    };

    private void meth() {
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

    private String ssss;

    private void method() {

        synchronized (instanec) {
            instanec2.ssss = "";
            Log.i("sssss", "我是类锁的第二种形式，synchronize（*.class），我叫" + Thread.currentThread().getName() + ":");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("sssss", Thread.currentThread().getName() + "结束");
        }
    }

    private void method1() {

        synchronized (instanec2) {
            instanec.ssss = "";
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
