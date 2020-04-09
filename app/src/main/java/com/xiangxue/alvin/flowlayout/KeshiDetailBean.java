package com.xiangxue.alvin.flowlayout;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuliang on 2019/11/25.
 */

public class KeshiDetailBean extends CommentJsonBean implements Serializable {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String deptNo;
        private String details;
        private String hisId;
        private String iconUrl;
        private int id;
        private int isHotDept;
        private String location;
        private String name;
        private String parentNo;
        private String treatDisease;
        private List<String> deptImageList;

        public String getDeptNo() {
            return deptNo;
        }

        public void setDeptNo(String deptNo) {
            this.deptNo = deptNo;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getHisId() {
            return hisId;
        }

        public void setHisId(String hisId) {
            this.hisId = hisId;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsHotDept() {
            return isHotDept;
        }

        public void setIsHotDept(int isHotDept) {
            this.isHotDept = isHotDept;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentNo() {
            return parentNo;
        }

        public void setParentNo(String parentNo) {
            this.parentNo = parentNo;
        }

        public String getTreatDisease() {
            return treatDisease;
        }

        public void setTreatDisease(String treatDisease) {
            this.treatDisease = treatDisease;
        }

        public List<String> getDeptImageList() {
            return deptImageList;
        }

        public void setDeptImageList(List<String> deptImageList) {
            this.deptImageList = deptImageList;
        }
    }
}
