package com.xiaomai.shanghu.bean;

/**
 * Created by EDZ on 2019/4/22.
 *
 */

public class GetCreditCardBean {

    /**
     * code : 0
     * data : {"agentId":0,"bank":"string","createTime":"2019-04-22T12:39:23.391Z","creditCard":"string","details":"string","id":0,"mobile":"string","realName":"string","sellerId":0,"subBank":"string","updTime":"2019-04-22T12:39:23.391Z"}
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
         * agentId : 0
         * bank : string
         * createTime : 2019-04-22T12:39:23.391Z
         * creditCard : string
         * details : string
         * id : 0
         * mobile : string
         * realName : string
         * sellerId : 0
         * subBank : string
         * updTime : 2019-04-22T12:39:23.391Z
         */

        private int agentId;
        private String bank;
        private String createTime;
        private String creditCard;
        private String details;
        private int id;
        private String mobile;
        private String realName;
        private int sellerId;
        private String subBank;
        private String updTime;

        public int getAgentId() {
            return agentId;
        }

        public void setAgentId(int agentId) {
            this.agentId = agentId;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getCreditCard() {
            return creditCard;
        }

        public void setCreditCard(String creditCard) {
            this.creditCard = creditCard;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            this.sellerId = sellerId;
        }

        public String getSubBank() {
            return subBank;
        }

        public void setSubBank(String subBank) {
            this.subBank = subBank;
        }

        public String getUpdTime() {
            return updTime;
        }

        public void setUpdTime(String updTime) {
            this.updTime = updTime;
        }
    }
}
