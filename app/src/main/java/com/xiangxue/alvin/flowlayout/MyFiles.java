package com.xiangxue.alvin.flowlayout;

import android.os.Environment;

import java.io.File;

/**
 * Created by zhuliang on 2019/11/28.
 */

public class MyFiles {

    private void init()
    {
        File file= Environment.getExternalStorageDirectory();
        File files=new File(file,"mmm.png");
    }

}
