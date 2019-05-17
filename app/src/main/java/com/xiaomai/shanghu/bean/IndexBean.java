package com.xiaomai.shanghu.bean;

public class IndexBean {

    /**
     * code : 1
     * data : {"bank":"","credit_card":"","day_earn":"0","freeze_money":"0","is_havecard":"","liquidated":"2100","mobile":"","month_earn":"2380","noRentCount":"1","offLineCount":"0","onLineCount":"1","orderFinish":"2","orderIng":"0","orderWait":"0","real_name":"","rentCount":"4","sub_bank":"","total_earn":"2380","unliquidated":"280","yestoday_earn":"0"}
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
         * bank :
         * credit_card :
         * day_earn : 0
         * freeze_money : 0
         * is_havecard :
         * liquidated : 2100
         * mobile :
         * month_earn : 2380
         * noRentCount : 1
         * offLineCount : 0
         * onLineCount : 1
         * orderFinish : 2
         * orderIng : 0
         * orderWait : 0
         * real_name :
         * rentCount : 4
         * sub_bank :
         * total_earn : 2380
         * unliquidated : 280
         * yestoday_earn : 0
         */

        private String bank;
        private String credit_card;
        private String day_earn;
        private String freeze_money;
        private String is_havecard;
        private String liquidated;
        private String mobile;
        private String month_earn;
        private String noRentCount;
        private String offLineCount;
        private String onLineCount;
        private String orderFinish;
        private String orderIng;
        private String orderWait;
        private String real_name;
        private String rentCount;
        private String sub_bank;
        private String total_earn;
        private String unliquidated;
        private String yestoday_earn;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getCredit_card() {
            return credit_card;
        }

        public void setCredit_card(String credit_card) {
            this.credit_card = credit_card;
        }

        public String getDay_earn() {
            return day_earn;
        }

        public void setDay_earn(String day_earn) {
            this.day_earn = day_earn;
        }

        public String getFreeze_money() {
            return freeze_money;
        }

        public void setFreeze_money(String freeze_money) {
            this.freeze_money = freeze_money;
        }

        public String getIs_havecard() {
            return is_havecard;
        }

        public void setIs_havecard(String is_havecard) {
            this.is_havecard = is_havecard;
        }

        public String getLiquidated() {
            return liquidated;
        }

        public void setLiquidated(String liquidated) {
            this.liquidated = liquidated;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMonth_earn() {
            return month_earn;
        }

        public void setMonth_earn(String month_earn) {
            this.month_earn = month_earn;
        }

        public String getNoRentCount() {
            return noRentCount;
        }

        public void setNoRentCount(String noRentCount) {
            this.noRentCount = noRentCount;
        }

        public String getOffLineCount() {
            return offLineCount;
        }

        public void setOffLineCount(String offLineCount) {
            this.offLineCount = offLineCount;
        }

        public String getOnLineCount() {
            return onLineCount;
        }

        public void setOnLineCount(String onLineCount) {
            this.onLineCount = onLineCount;
        }

        public String getOrderFinish() {
            return orderFinish;
        }

        public void setOrderFinish(String orderFinish) {
            this.orderFinish = orderFinish;
        }

        public String getOrderIng() {
            return orderIng;
        }

        public void setOrderIng(String orderIng) {
            this.orderIng = orderIng;
        }

        public String getOrderWait() {
            return orderWait;
        }

        public void setOrderWait(String orderWait) {
            this.orderWait = orderWait;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getRentCount() {
            return rentCount;
        }

        public void setRentCount(String rentCount) {
            this.rentCount = rentCount;
        }

        public String getSub_bank() {
            return sub_bank;
        }

        public void setSub_bank(String sub_bank) {
            this.sub_bank = sub_bank;
        }

        public String getTotal_earn() {
            return total_earn;
        }

        public void setTotal_earn(String total_earn) {
            this.total_earn = total_earn;
        }

        public String getUnliquidated() {
            return unliquidated;
        }

        public void setUnliquidated(String unliquidated) {
            this.unliquidated = unliquidated;
        }

        public String getYestoday_earn() {
            return yestoday_earn;
        }

        public void setYestoday_earn(String yestoday_earn) {
            this.yestoday_earn = yestoday_earn;
        }
    }
}
