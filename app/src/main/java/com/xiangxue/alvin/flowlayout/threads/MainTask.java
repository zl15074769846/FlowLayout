package com.xiangxue.alvin.flowlayout.threads;

import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhuliang on 2019/11/29.
 */

public class MainTask extends Thread {

    private int ThreadCount;
    private String downLoadUrl;
    private String path;
    private int blocSize;
    private int allSize;
    private ProgressBar progressBar;
    private Handler myHandler;

    public MainTask(int threadCount, String url, String path,ProgressBar progressBar,Handler myHandler) {
        ThreadCount = threadCount;
        this.downLoadUrl = url;
        this.path = path;
        this.progressBar=progressBar;
        this.myHandler=myHandler;
    }

    @Override
    public void run() {

        try {

            URL url = new URL(downLoadUrl);

            URLConnection urlConnection = url.openConnection();

            allSize = urlConnection.getContentLength();

            progressBar.setMax(allSize);

            blocSize = allSize % ThreadCount == 0 ? allSize / ThreadCount : allSize / ThreadCount + 1;

            File file=new File(path);

            FileDownLoadThread[] fileDownLoadThreads=new FileDownLoadThread[ThreadCount];

            for(int i=0;i<ThreadCount;i++)
            {
                FileDownLoadThread fileDownLoadThread=new FileDownLoadThread(blocSize,i+1,file,url);

                fileDownLoadThreads[i]=fileDownLoadThread;

                fileDownLoadThread.start();
            }

            boolean isFinished=false;
            int allSize=0;

            while (!isFinished) {

                for(int i=0;i<ThreadCount;i++)
                {
                    isFinished=true;
                    allSize+=fileDownLoadThreads[i].getLength();
                    if(fileDownLoadThreads[i].isFinishThis()==false)
                    {
                        isFinished=false;
                    }
                }

                Message message = Message.obtain();
                message.what = 1;
                message.getData().putInt("size", allSize);
                myHandler.sendMessage(message);
                Thread.sleep(1000);
                allSize=0;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getAllSize()
    {
        return allSize;
    }

}
