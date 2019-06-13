package com.xiaomai.yyshanghu.bean;

import java.util.List;

/**
 * Created by EDZ on 2019/4/20.
 * 获取用户提现记录
 */

public class PageWithdrawRecordBean {

    /**
     * code : 0
     * data : {"list":[{"applyTime":"string","bank":"string","creditCard":"string","details":"string","handleTime":"string","id":"string","mobile":"string","money":"string","realName":"string","state":"string"}],"pageIndex":0,"pageSize":0,"resultNumber":0,"total":0,"totalPages":0}
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
         * list : [{"applyTime":"string","bank":"string","creditCard":"string","details":"string","handleTime":"string","id":"string","mobile":"string","money":"string","realName":"string","state":"string"}]
         * pageIndex : 0
         * pageSize : 0
         * resultNumber : 0
         * total : 0
         * totalPages : 0
         */

        private int pageIndex;
        private int pageSize;
        private int resultNumber;
        private int total;
        private int totalPages;
        private List<ListBean> list;

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getResultNumber() {
            return resultNumber;
        }

        public void setResultNumber(int resultNumber) {
            this.resultNumber = resultNumber;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * applyTime : string
             * bank : string
             * creditCard : string
             * details : string
             * handleTime : string
             * id : string
             * mobile : string
             * money : string
             * realName : string
             * state : string
             */

            private String applyTime;
            private String bank;
            private String creditCard;
            private String details;
            private String handleTime;
            private String id;
            private String mobile;
            private String money;
            private String realName;
            private String state;

            public String getApplyTime() {
                return applyTime;
            }

            public void setApplyTime(String applyTime) {
                this.applyTime = applyTime;
            }

            public String getBank() {
                return bank;
            }

            public void setBank(String bank) {
                this.bank = bank;
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

            public String getHandleTime() {
                return handleTime;
            }

            public void setHandleTime(String handleTime) {
                this.handleTime = handleTime;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}
