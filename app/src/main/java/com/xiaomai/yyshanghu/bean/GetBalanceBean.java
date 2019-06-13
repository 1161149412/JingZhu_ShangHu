package com.xiaomai.yyshanghu.bean;

/**
 * Created by EDZ on 2019/4/23.
 * 获取商家提现中金额和可提现金额
 */

public class GetBalanceBean {

    /**
     * code : 1
     * data : {"withdrawFinish":0,"canWithdraw":20,"withdrawIng":0}
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
         * withdrawFinish : 0
         * canWithdraw : 20
         * withdrawIng : 0
         */

        private int withdrawFinish;
        private int canWithdraw;
        private int withdrawIng;

        public int getWithdrawFinish() {
            return withdrawFinish;
        }

        public void setWithdrawFinish(int withdrawFinish) {
            this.withdrawFinish = withdrawFinish;
        }

        public int getCanWithdraw() {
            return canWithdraw;
        }

        public void setCanWithdraw(int canWithdraw) {
            this.canWithdraw = canWithdraw;
        }

        public int getWithdrawIng() {
            return withdrawIng;
        }

        public void setWithdrawIng(int withdrawIng) {
            this.withdrawIng = withdrawIng;
        }
    }
}
