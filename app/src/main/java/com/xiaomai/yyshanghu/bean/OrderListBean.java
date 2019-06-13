package com.xiaomai.yyshanghu.bean;

import java.util.List;

/**
 * Created by EDZ on 2019/4/20.
 * 获取订单
 */

public class OrderListBean {

    /**
     * code : 0
     * data : {"list":[{"continueStr":"string","continueTime":"string","deviceId":"string","discountPrice":"string","fourReward":"string","fourRewardPercent":"string","id":"string","oneReward":"string","oneRewardPercent":"string","orderState":"string","realPayment":"string","rentAddress":"string","rentPrice":"string","rentTime":"string","returnAddress":"string","returnBoxId":"string","returnTime":"string","sellerId":"string","sellerPercent":"string","sellerReward":"string","threeReward":"string","threeRewardPercent":"string","twoReward":"string","twoRewardPercent":"string","updTime":"string"}],"pageIndex":0,"pageSize":0,"resultNumber":0,"total":0,"totalPages":0}
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
         * list : [{"continueStr":"string","continueTime":"string","deviceId":"string","discountPrice":"string","fourReward":"string","fourRewardPercent":"string","id":"string","oneReward":"string","oneRewardPercent":"string","orderState":"string","realPayment":"string","rentAddress":"string","rentPrice":"string","rentTime":"string","returnAddress":"string","returnBoxId":"string","returnTime":"string","sellerId":"string","sellerPercent":"string","sellerReward":"string","threeReward":"string","threeRewardPercent":"string","twoReward":"string","twoRewardPercent":"string","updTime":"string"}]
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
             * continueStr : string
             * continueTime : string
             * deviceId : string
             * discountPrice : string
             * fourReward : string
             * fourRewardPercent : string
             * id : string
             * oneReward : string
             * oneRewardPercent : string
             * orderState : string
             * realPayment : string
             * rentAddress : string
             * rentPrice : string
             * rentTime : string
             * returnAddress : string
             * returnBoxId : string
             * returnTime : string
             * sellerId : string
             * sellerPercent : string
             * sellerReward : string
             * threeReward : string
             * threeRewardPercent : string
             * twoReward : string
             * twoRewardPercent : string
             * updTime : string
             */

            private String continueStr;
            private String continueTime;
            private String deviceId;
            private String discountPrice;
            private String fourReward;
            private String fourRewardPercent;
            private String id;
            private String oneReward;
            private String oneRewardPercent;
            private String orderState;
            private String realPayment;
            private String rentAddress;
            private String rentPrice;
            private String rentTime;
            private String returnAddress;
            private String returnBoxId;
            private String returnTime;
            private String sellerId;
            private String sellerPercent;
            private String sellerReward;
            private String threeReward;
            private String threeRewardPercent;
            private String twoReward;
            private String twoRewardPercent;
            private String updTime;

            public String getContinueStr() {
                return continueStr;
            }

            public void setContinueStr(String continueStr) {
                this.continueStr = continueStr;
            }

            public String getContinueTime() {
                return continueTime;
            }

            public void setContinueTime(String continueTime) {
                this.continueTime = continueTime;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getDiscountPrice() {
                return discountPrice;
            }

            public void setDiscountPrice(String discountPrice) {
                this.discountPrice = discountPrice;
            }

            public String getFourReward() {
                return fourReward;
            }

            public void setFourReward(String fourReward) {
                this.fourReward = fourReward;
            }

            public String getFourRewardPercent() {
                return fourRewardPercent;
            }

            public void setFourRewardPercent(String fourRewardPercent) {
                this.fourRewardPercent = fourRewardPercent;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOneReward() {
                return oneReward;
            }

            public void setOneReward(String oneReward) {
                this.oneReward = oneReward;
            }

            public String getOneRewardPercent() {
                return oneRewardPercent;
            }

            public void setOneRewardPercent(String oneRewardPercent) {
                this.oneRewardPercent = oneRewardPercent;
            }

            public String getOrderState() {
                return orderState;
            }

            public void setOrderState(String orderState) {
                this.orderState = orderState;
            }

            public String getRealPayment() {
                return realPayment;
            }

            public void setRealPayment(String realPayment) {
                this.realPayment = realPayment;
            }

            public String getRentAddress() {
                return rentAddress;
            }

            public void setRentAddress(String rentAddress) {
                this.rentAddress = rentAddress;
            }

            public String getRentPrice() {
                return rentPrice;
            }

            public void setRentPrice(String rentPrice) {
                this.rentPrice = rentPrice;
            }

            public String getRentTime() {
                return rentTime;
            }

            public void setRentTime(String rentTime) {
                this.rentTime = rentTime;
            }

            public String getReturnAddress() {
                return returnAddress;
            }

            public void setReturnAddress(String returnAddress) {
                this.returnAddress = returnAddress;
            }

            public String getReturnBoxId() {
                return returnBoxId;
            }

            public void setReturnBoxId(String returnBoxId) {
                this.returnBoxId = returnBoxId;
            }

            public String getReturnTime() {
                return returnTime;
            }

            public void setReturnTime(String returnTime) {
                this.returnTime = returnTime;
            }

            public String getSellerId() {
                return sellerId;
            }

            public void setSellerId(String sellerId) {
                this.sellerId = sellerId;
            }

            public String getSellerPercent() {
                return sellerPercent;
            }

            public void setSellerPercent(String sellerPercent) {
                this.sellerPercent = sellerPercent;
            }

            public String getSellerReward() {
                return sellerReward;
            }

            public void setSellerReward(String sellerReward) {
                this.sellerReward = sellerReward;
            }

            public String getThreeReward() {
                return threeReward;
            }

            public void setThreeReward(String threeReward) {
                this.threeReward = threeReward;
            }

            public String getThreeRewardPercent() {
                return threeRewardPercent;
            }

            public void setThreeRewardPercent(String threeRewardPercent) {
                this.threeRewardPercent = threeRewardPercent;
            }

            public String getTwoReward() {
                return twoReward;
            }

            public void setTwoReward(String twoReward) {
                this.twoReward = twoReward;
            }

            public String getTwoRewardPercent() {
                return twoRewardPercent;
            }

            public void setTwoRewardPercent(String twoRewardPercent) {
                this.twoRewardPercent = twoRewardPercent;
            }

            public String getUpdTime() {
                return updTime;
            }

            public void setUpdTime(String updTime) {
                this.updTime = updTime;
            }
        }
    }
}
