package com.xiaomai.yyshanghu.bean;

import java.util.List;

/**
 * Created by EDZ on 2019/4/19.
 * 分页获取设备列表
 */

public class PageDeviceListBean {


    /**
     * code : 1
     * data : {"list":[{"agentId":681,"boxAddress":"四川省凉山彝族自治州会理县会太路73号","details":"2.00","employeeId":0,"freezeMoney":"0","freezeTime":"","iccid":0,"id":"JZCB061903000098","isFreeze":"3","latitude":"26.653220","longitude":"102.250550","offlineTime":"","remitTime":"","resultFreezeMoney":"","sellerId":67,"sellerReward":"","slot":"[1,1,1,1,1,1]","state":"1","stock":"6","sumProfit":"0.00","unfreezeMoney":"0","updateTime":1555336284000},{"agentId":455,"boxAddress":"四川省成都市双流区利民二街31号鼎盛天一量贩式KTV","details":"1.00","employeeId":0,"freezeMoney":"0","freezeTime":"","iccid":455,"id":"JZCB061903000101","isFreeze":"0","latitude":"30.507853","longitude":"104.066150","offlineTime":"","remitTime":"","resultFreezeMoney":"","sellerId":67,"sellerReward":"40","slot":"[1,1,1,1,1,1]","state":"1","stock":"6","sumProfit":"4.00","unfreezeMoney":"0","updateTime":1555347084000}],"pageIndex":1,"pageSize":30,"resultNumber":0,"total":0,"totalPages":0}
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
         * list : [{"agentId":681,"boxAddress":"四川省凉山彝族自治州会理县会太路73号","details":"2.00","employeeId":0,"freezeMoney":"0","freezeTime":"","iccid":0,"id":"JZCB061903000098","isFreeze":"3","latitude":"26.653220","longitude":"102.250550","offlineTime":"","remitTime":"","resultFreezeMoney":"","sellerId":67,"sellerReward":"","slot":"[1,1,1,1,1,1]","state":"1","stock":"6","sumProfit":"0.00","unfreezeMoney":"0","updateTime":1555336284000},{"agentId":455,"boxAddress":"四川省成都市双流区利民二街31号鼎盛天一量贩式KTV","details":"1.00","employeeId":0,"freezeMoney":"0","freezeTime":"","iccid":455,"id":"JZCB061903000101","isFreeze":"0","latitude":"30.507853","longitude":"104.066150","offlineTime":"","remitTime":"","resultFreezeMoney":"","sellerId":67,"sellerReward":"40","slot":"[1,1,1,1,1,1]","state":"1","stock":"6","sumProfit":"4.00","unfreezeMoney":"0","updateTime":1555347084000}]
         * pageIndex : 1
         * pageSize : 30
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
             * agentId : 681
             * boxAddress : 四川省凉山彝族自治州会理县会太路73号
             * details : 2.00
             * employeeId : 0
             * freezeMoney : 0
             * freezeTime :
             * iccid : 0
             * id : JZCB061903000098
             * isFreeze : 3
             * latitude : 26.653220
             * longitude : 102.250550
             * offlineTime :
             * remitTime :
             * resultFreezeMoney :
             * sellerId : 67
             * sellerReward :
             * slot : [1,1,1,1,1,1]
             * state : 1
             * stock : 6
             * sumProfit : 0.00
             * unfreezeMoney : 0
             * updateTime : 1555336284000
             */

            private int agentId;
            private String boxAddress;
            private String details;
            private int employeeId;
            private String freezeMoney;
            private String freezeTime;
            private int iccid;
            private String id;
            private String isFreeze;
            private String latitude;
            private String longitude;
            private String offlineTime;
            private String remitTime;
            private String resultFreezeMoney;
            private int sellerId;
            private String sellerReward;
            private String slot;
            private String state;
            private String stock;
            private String sumProfit;
            private String unfreezeMoney;
            private long updateTime;

            public int getAgentId() {
                return agentId;
            }

            public void setAgentId(int agentId) {
                this.agentId = agentId;
            }

            public String getBoxAddress() {
                return boxAddress;
            }

            public void setBoxAddress(String boxAddress) {
                this.boxAddress = boxAddress;
            }

            public String getDetails() {
                return details;
            }

            public void setDetails(String details) {
                this.details = details;
            }

            public int getEmployeeId() {
                return employeeId;
            }

            public void setEmployeeId(int employeeId) {
                this.employeeId = employeeId;
            }

            public String getFreezeMoney() {
                return freezeMoney;
            }

            public void setFreezeMoney(String freezeMoney) {
                this.freezeMoney = freezeMoney;
            }

            public String getFreezeTime() {
                return freezeTime;
            }

            public void setFreezeTime(String freezeTime) {
                this.freezeTime = freezeTime;
            }

            public int getIccid() {
                return iccid;
            }

            public void setIccid(int iccid) {
                this.iccid = iccid;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsFreeze() {
                return isFreeze;
            }

            public void setIsFreeze(String isFreeze) {
                this.isFreeze = isFreeze;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getOfflineTime() {
                return offlineTime;
            }

            public void setOfflineTime(String offlineTime) {
                this.offlineTime = offlineTime;
            }

            public String getRemitTime() {
                return remitTime;
            }

            public void setRemitTime(String remitTime) {
                this.remitTime = remitTime;
            }

            public String getResultFreezeMoney() {
                return resultFreezeMoney;
            }

            public void setResultFreezeMoney(String resultFreezeMoney) {
                this.resultFreezeMoney = resultFreezeMoney;
            }

            public int getSellerId() {
                return sellerId;
            }

            public void setSellerId(int sellerId) {
                this.sellerId = sellerId;
            }

            public String getSellerReward() {
                return sellerReward;
            }

            public void setSellerReward(String sellerReward) {
                this.sellerReward = sellerReward;
            }

            public String getSlot() {
                return slot;
            }

            public void setSlot(String slot) {
                this.slot = slot;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public String getSumProfit() {
                return sumProfit;
            }

            public void setSumProfit(String sumProfit) {
                this.sumProfit = sumProfit;
            }

            public String getUnfreezeMoney() {
                return unfreezeMoney;
            }

            public void setUnfreezeMoney(String unfreezeMoney) {
                this.unfreezeMoney = unfreezeMoney;
            }

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
