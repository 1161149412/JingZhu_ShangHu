package com.xiaomai.shanghu.bean;

/**
 * Created by EDZ on 2019/4/19.
 * 获取在线离线设备数量
 */

public class GetDviceCountBean {


    /**
     * code : 1
     * data : {"all":0,"offline":0,"online":0}
     * msg : 操作成功
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * all : 0
         * offline : 0
         * online : 0
         */

        private int all;
        private int offline;
        private int online;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        public int getOffline() {
            return offline;
        }

        public void setOffline(int offline) {
            this.offline = offline;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }
    }
}
