package com.xiaomai.yyshanghu.bean;

import java.util.List;

/**
 * Created by EDZ on 2019/4/20.
 * 获取商家账单
 */

public class GetPageSellerBillBean {

    /**
     * code : 0
     * data : {"list":[{"billDate":"string","billMonth":"string","createTime":"string","id":0,"rewardPercent":"string","sellerEarn":"string","sellerId":"string","totalEarn":"string","totalPrice":"string"}],"pageIndex":0,"pageSize":0,"resultNumber":0,"total":0,"totalPages":0}
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
         * list : [{"billDate":"string","billMonth":"string","createTime":"string","id":0,"rewardPercent":"string","sellerEarn":"string","sellerId":"string","totalEarn":"string","totalPrice":"string"}]
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
             * billDate : string
             * billMonth : string
             * createTime : string
             * id : 0
             * rewardPercent : string
             * sellerEarn : string
             * sellerId : string
             * totalEarn : string
             * totalPrice : string
             */

            private String billDate;
            private String billMonth;
            private String createTime;
            private int id;
            private String rewardPercent;
            private String sellerEarn;
            private String sellerId;
            private String totalEarn;
            private String totalPrice;

            public String getBillDate() {
                return billDate;
            }

            public void setBillDate(String billDate) {
                this.billDate = billDate;
            }

            public String getBillMonth() {
                return billMonth;
            }

            public void setBillMonth(String billMonth) {
                this.billMonth = billMonth;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRewardPercent() {
                return rewardPercent;
            }

            public void setRewardPercent(String rewardPercent) {
                this.rewardPercent = rewardPercent;
            }

            public String getSellerEarn() {
                return sellerEarn;
            }

            public void setSellerEarn(String sellerEarn) {
                this.sellerEarn = sellerEarn;
            }

            public String getSellerId() {
                return sellerId;
            }

            public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
            }

            public String getTotalEarn() {
                return totalEarn;
            }

            public void setTotalEarn(String totalEarn) {
                this.totalEarn = totalEarn;
            }

            public String getTotalPrice() {
                return totalPrice;
            }

            public void setTotalPrice(String totalPrice) {
                this.totalPrice = totalPrice;
            }
        }
    }
}
