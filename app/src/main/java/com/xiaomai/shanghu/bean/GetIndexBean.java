package com.xiaomai.shanghu.bean;

/**
 * Created by EDZ on 2019/4/19.
 * 商户端账单Index
 */

public class GetIndexBean {

    /**
     * code : 1
     * data : {"sellerRewardSum":20,"orderRewardSum":1800}
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
         * sellerRewardSum : 20
         * orderRewardSum : 1800
         */

        private int sellerRewardSum;
        private int orderRewardSum;

        public int getSellerRewardSum() {
            return sellerRewardSum;
        }

        public void setSellerRewardSum(int sellerRewardSum) {
            this.sellerRewardSum = sellerRewardSum;
        }

        public int getOrderRewardSum() {
            return orderRewardSum;
        }

        public void setOrderRewardSum(int orderRewardSum) {
            this.orderRewardSum = orderRewardSum;
        }
    }
}
