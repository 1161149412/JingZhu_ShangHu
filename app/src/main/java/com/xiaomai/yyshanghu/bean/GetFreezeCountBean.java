package com.xiaomai.yyshanghu.bean;

/**
 * Created by EDZ on 2019/4/19.
 * 查询冻结设备和金额
 */

public class GetFreezeCountBean {

    /**
     * code : 1
     * data : {"freezeCount":0,"freezeMoney":0}
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
         * freezeCount : 0
         * freezeMoney : 0
         */

        private int freezeCount;
        private int freezeMoney;

        public int getFreezeCount() {
            return freezeCount;
        }

        public void setFreezeCount(int freezeCount) {
            this.freezeCount = freezeCount;
        }

        public int getFreezeMoney() {
            return freezeMoney;
        }

        public void setFreezeMoney(int freezeMoney) {
            this.freezeMoney = freezeMoney;
        }
    }
}
