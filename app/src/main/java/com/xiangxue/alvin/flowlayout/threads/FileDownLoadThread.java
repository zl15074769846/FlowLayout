package com.xiangxue.alvin.flowlayout.threads;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by zhuliang on 2019/11/28.
 */

public class FileDownLoadThread extends Thread {

    private int blockSize;
    private int threadId;
    private File file;
    private URL downloadUrl;
    private int downloadLength;
    private boolean isFinish;

    public FileDownLoadThread(int blockSize, int threadId, File file, URL downloadUrl) {
        this.blockSize = blockSize;
        this.threadId = threadId;
        this.file = file;
        this.downloadUrl = downloadUrl;
    }

    @Override
    public void run() {
        BufferedInputStream is = null;
        RandomAccessFile accessFile = null;
        int startPos = blockSize * (threadId - 1);
        int endPos = blockSize * threadId - 1;

        try {
            URLConnection urlConnection = downloadUrl.openConnection();
            urlConnection.setAllowUserInteraction(true);
            urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

            is = new BufferedInputStream(urlConnection.getInputStream());
            accessFile = new RandomAccessFile(file, "rwd");
            accessFile.seek(startPos);
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes, 0, 1024)) != -1) {
                accessFile.write(bytes, 0, len);
                downloadLength += len;
            }

            isFinish = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (accessFile != null) {
                    accessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public int getLength() {
        return downloadLength;
    }

    public boolean isFinishThis() {
        return isFinish;
    }


}
