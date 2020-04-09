package com.xiangxue.alvin.flowlayout;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuliang on 2019/11/29.
 */

public class SchoolNode {

    SchoolNode schoolNode=null;
    List<SchoolNode> childrens=new ArrayList<>();

    public  void addChild(SchoolNode child){
        if (!this.childrens.contains(child)){
            this.childrens.add(child);
            child.setParentOnly(this);
            Log.i("sssss", "我是addChild");
        }
    }

    public  void addChildOnly(SchoolNode child){
        if (!this.childrens.contains(child)){
            this.childrens.add(child);
        }
    }

    public  void setParentOnly(SchoolNode parent){
        this.schoolNode=parent;
    }

    public  void setParent(SchoolNode parent){
        this.schoolNode=parent;
        parent.addChild(this);
        Log.i("sssss", "setParent");
    }

}
