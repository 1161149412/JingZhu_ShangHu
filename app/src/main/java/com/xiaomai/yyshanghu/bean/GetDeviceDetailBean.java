package com.xiaomai.yyshanghu.bean;

/**
 * Created by EDZ on 2019/4/20.
 */

public class GetDeviceDetailBean {


    /**
     * code : 1
     * data : {"agentId":681,"avgMonthProfit":"0.00","boxAddress":"四川省凉山彝族自治州会理县会太路73号","createTime":"2019-03-29 11:04:54.0","details":"2.00","earnForMeSum":"0.00","employeeId":0,"freezeMoney":"0.00","freezeTime":"","iccid":0,"id":"JZCB061903000098","installMobile":"","installRealname":"","installTime":"","isFreeze":"3","latitude":"26.653220","longitude":"102.250550","monthProfit":"0.00","offlineTime":"","orderCount":"0","remitTime":"","resultFreezeMoney":"","sellerId":67,"sellerLinkman":"吴凯","sellerLinktel":"15202856300","sellerName":"兆信国际2号楼","sellerReward":"0.00%","slot":"[1,1,1,1,1,1]","state":"1","stock":"6","sumProfit":"0.00","unfreezeMoney":"0.00","updateTime":1555336284000}
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
         * agentId : 681
         * avgMonthProfit : 0.00
         * boxAddress : 四川省凉山彝族自治州会理县会太路73号
         * createTime : 2019-03-29 11:04:54.0
         * details : 2.00
         * earnForMeSum : 0.00
         * employeeId : 0
         * freezeMoney : 0.00
         * freezeTime :
         * iccid : 0
         * id : JZCB061903000098
         * installMobile :
         * installRealname :
         * installTime :
         * isFreeze : 3
         * latitude : 26.653220
         * longitude : 102.250550
         * monthProfit : 0.00
         * offlineTime :
         * orderCount : 0
         * remitTime :
         * resultFreezeMoney :
         * sellerId : 67
         * sellerLinkman : 吴凯
         * sellerLinktel : 15202856300
         * sellerName : 兆信国际2号楼
         * sellerReward : 0.00%
         * slot : [1,1,1,1,1,1]
         * state : 1
         * stock : 6
         * sumProfit : 0.00
         * unfreezeMoney : 0.00
         * updateTime : 1555336284000
         */

        private int agentId;
        private String avgMonthProfit;
        private String boxAddress;
        private String createTime;
        private String details;
        private String earnForMeSum;
        private int employeeId;
        private String freezeMoney;
        private String freezeTime;
        private int iccid;
        private String id;
        private String installMobile;
        private String installRealname;
        private String installTime;
        private String isFreeze;
        private String latitude;
        private String longitude;
        private String monthProfit;
        private String offlineTime;
        private String orderCount;
        private String remitTime;
        private String resultFreezeMoney;
        private int sellerId;
        private String sellerLinkman;
        private String sellerLinktel;
        private String sellerName;
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

        public String getAvgMonthProfit() {
            return avgMonthProfit;
        }

        public void setAvgMonthProfit(String avgMonthProfit) {
            this.avgMonthProfit = avgMonthProfit;
        }

        public String getBoxAddress() {
            return boxAddress;
        }

        public void setBoxAddress(String boxAddress) {
            this.boxAddress = boxAddress;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getEarnForMeSum() {
            return earnForMeSum;
        }

        public void setEarnForMeSum(String earnForMeSum) {
            this.earnForMeSum = earnForMeSum;
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

        public String getInstallMobile() {
            return installMobile;
        }

        public void setInstallMobile(String installMobile) {
            this.installMobile = installMobile;
        }

        public String getInstallRealname() {
            return installRealname;
        }

        public void setInstallRealname(String installRealname) {
            this.installRealname = installRealname;
        }

        public String getInstallTime() {
            return installTime;
        }

        public void setInstallTime(String installTime) {
            this.installTime = installTime;
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

        public String getMonthProfit() {
            return monthProfit;
        }

        public void setMonthProfit(String monthProfit) {
            this.monthProfit = monthProfit;
        }

        public String getOfflineTime() {
            return offlineTime;
        }

        public void setOfflineTime(String offlineTime) {
            this.offlineTime = offlineTime;
        }

        public String getOrderCount() {
            return orderCount;
        }

        public void setOrderCount(String orderCount) {
            this.orderCount = orderCount;
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

        public String getSellerLinkman() {
            return sellerLinkman;
        }

        public void setSellerLinkman(String sellerLinkman) {
            this.sellerLinkman = sellerLinkman;
        }

        public String getSellerLinktel() {
            return sellerLinktel;
        }

        public void setSellerLinktel(String sellerLinktel) {
            this.sellerLinktel = sellerLinktel;
        }

        public String getSellerName() {
            return sellerName;
        }

        public void setSellerName(String sellerName) {
            this.sellerName = sellerName;
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
