package com.xiaomai.yyshanghu.bean;

import java.util.List;

/**
 * Created by EDZ on 2019/4/20.
 * 获取冻结设备详情
 */

public class GetDeviceFreezeDetailBean {

    /**
     * code : 0
     * data : {"agentId":0,"agentTrees":[{"agentId":0,"agentName":"string","nodeIndex":0,"parentId":0}],"boxAddress":"string","details":"string","employeeId":0,"freezeMoney":"string","freezeTime":"string","iccid":0,"id":"string","installRealname":"string","isFreeze":"string","latitude":"string","longitude":"string","offlineTime":"string","remitTime":"string","resultFreezeMoney":"string","sellerId":0,"sellerLinkman":"string","sellerName":"string","sellerReward":"string","slot":"string","state":"string","sumProfit":"string","unfreezeMoney":"string","updateTime":"2019-04-20T01:04:47.583Z"}
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
         * agentTrees : [{"agentId":0,"agentName":"string","nodeIndex":0,"parentId":0}]
         * boxAddress : string
         * details : string
         * employeeId : 0
         * freezeMoney : string
         * freezeTime : string
         * iccid : 0
         * id : string
         * installRealname : string
         * isFreeze : string
         * latitude : string
         * longitude : string
         * offlineTime : string
         * remitTime : string
         * resultFreezeMoney : string
         * sellerId : 0
         * sellerLinkman : string
         * sellerName : string
         * sellerReward : string
         * slot : string
         * state : string
         * sumProfit : string
         * unfreezeMoney : string
         * updateTime : 2019-04-20T01:04:47.583Z
         */

        private int agentId;
        private String boxAddress;
        private String details;
        private int employeeId;
        private String freezeMoney;
        private String freezeTime;
        private int iccid;
        private String id;
        private String installRealname;
        private String isFreeze;
        private String latitude;
        private String longitude;
        private String offlineTime;
        private String remitTime;
        private String resultFreezeMoney;
        private int sellerId;
        private String sellerLinkman;
        private String sellerName;
        private String sellerReward;
        private String slot;
        private String state;
        private String sumProfit;
        private String unfreezeMoney;
        private String updateTime;
        private List<AgentTreesBean> agentTrees;

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

        public String getInstallRealname() {
            return installRealname;
        }

        public void setInstallRealname(String installRealname) {
            this.installRealname = installRealname;
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

        public String getSellerLinkman() {
            return sellerLinkman;
        }

        public void setSellerLinkman(String sellerLinkman) {
            this.sellerLinkman = sellerLinkman;
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

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<AgentTreesBean> getAgentTrees() {
            return agentTrees;
        }

        public void setAgentTrees(List<AgentTreesBean> agentTrees) {
            this.agentTrees = agentTrees;
        }

        public static class AgentTreesBean {
            /**
             * agentId : 0
             * agentName : string
             * nodeIndex : 0
             * parentId : 0
             */

            private int agentId;
            private String agentName;
            private int nodeIndex;
            private int parentId;

            public int getAgentId() {
                return agentId;
            }

            public void setAgentId(int agentId) {
                this.agentId = agentId;
            }

            public String getAgentName() {
                return agentName;
            }

            public void setAgentName(String agentName) {
                this.agentName = agentName;
            }

            public int getNodeIndex() {
                return nodeIndex;
            }

            public void setNodeIndex(int nodeIndex) {
                this.nodeIndex = nodeIndex;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }
        }
    }
}
