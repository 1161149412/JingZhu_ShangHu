package com.xiaomai.shanghu.bean;

/**
 * Created by EDZ on 2019/4/20.
 * 通关code登录
 */

public class LoginByCodeBean {

    /**
     * code : 0
     * data : {"alipayToken":"string","avoid":0,"cash":0,"orderId":"string","orderState":"string","refreshToken":"string","userId":0,"weixinToken":"string"}
     * msg : string
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
         * alipayToken : string
         * avoid : 0
         * cash : 0
         * orderId : string
         * orderState : string
         * refreshToken : string
         * userId : 0
         * weixinToken : string
         */

        private String alipayToken;
        private int avoid;
        private int cash;
        private String orderId;
        private String orderState;
        private String refreshToken;
        private int userId;
        private String weixinToken;

        public String getAlipayToken() {
            return alipayToken;
        }

        public void setAlipayToken(String alipayToken) {
            this.alipayToken = alipayToken;
        }

        public int getAvoid() {
            return avoid;
        }

        public void setAvoid(int avoid) {
            this.avoid = avoid;
        }

        public int getCash() {
            return cash;
        }

        public void setCash(int cash) {
            this.cash = cash;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getOrderState() {
            return orderState;
        }

        public void setOrderState(String orderState) {
            this.orderState = orderState;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getWeixinToken() {
            return weixinToken;
        }

        public void setWeixinToken(String weixinToken) {
            this.weixinToken = weixinToken;
        }
    }
}
