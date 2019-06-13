package com.xiaomai.yyshanghu.indexfragment.storepage;

import java.util.List;

/**
 * Created by WANG on 17/12/5.
 */

public class DataBean {
    String title;
    boolean isExpandable;
    boolean havaChild;
    boolean havaInsertChild;
    ChildBean childBean;
    List<DataBean> dataBeanList;

    public DataBean(String title, boolean isExpandable, boolean havaChild, boolean havaInsertChild) {
        this.title = title;
        this.isExpandable = isExpandable;
        this.havaChild = havaChild;
        this.havaInsertChild = havaInsertChild;
    }

    public List<DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    public ChildBean getChildBean() {
        return childBean;
    }

    public void setChildBean(ChildBean childBean) {
        this.childBean = childBean;
    }

    public boolean isHavaInsertChild() {
        return havaInsertChild;
    }

    public void setHavaInsertChild(boolean havaInsertChild) {
        this.havaInsertChild = havaInsertChild;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public boolean isHavaChild() {
        return havaChild;
    }

    public void setHavaChild(boolean havaChild) {
        this.havaChild = havaChild;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsExpandable() {
        return isExpandable;
    }

    public void setIsExpandable(boolean isExpandable) {
        this.isExpandable = isExpandable;
    }

    public static class ChildBean {
        String title;
        String yesterday,all,type;
        int podition;

        public ChildBean(String title, String yesterday, String all, String type, int podition) {
            this.title = title;
            this.yesterday = yesterday;
            this.all = all;
            this.type = type;
            this.podition = podition;
        }

        public ChildBean(String title, int podition) {
            this.title = title;
            this.podition = podition;
        }

        public String getYesterday() {
            return yesterday;
        }

        public void setYesterday(String yesterday) {
            this.yesterday = yesterday;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPodition() {
            return podition;
        }

        public void setPodition(int podition) {
            this.podition = podition;
        }
    }

}
