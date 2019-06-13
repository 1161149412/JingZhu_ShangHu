package com.xiaomai.yyshanghu.bean;

import java.util.List;

/**
 * Created by EDZ on 2019/4/19.
 * 分页获取冻结设备列表
 */

public class DeviceFreezeListBean {

    /**
     * code : 1
     * data : {"list":[{"agentId":730,"boxAddress":"老海亭(海亭路一段)风味烤鱼","details":"200","employeeId":0,"freezeMoney":"0.00","freezeTime":"","iccid":0,"id":"JZCB061903000099","installRealname":"","isFreeze":"2","latitude":"27.862633","longitude":"102.263055","offlineTime":"","remitTime":"","resultFreezeMoney":"","sellerId":67,"sellerLinkman":"吴凯","sellerName":"兆信国际2号楼","sellerReward":"","slot":"[1,1,1,1,1,1]","state":"0","stock":"6","sumProfit":"","unfreezeMoney":"0.00","updateTime":1555359984000}],"pageIndex":1,"pageSize":30,"resultNumber":0,"total":0,"totalPages":0}
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
         * list : [{"agentId":730,"boxAddress":"老海亭(海亭路一段)风味烤鱼","details":"200","employeeId":0,"freezeMoney":"0.00","freezeTime":"","iccid":0,"id":"JZCB061903000099","installRealname":"","isFreeze":"2","latitude":"27.862633","longitude":"102.263055","offlineTime":"","remitTime":"","resultFreezeMoney":"","sellerId":67,"sellerLinkman":"吴凯","sellerName":"兆信国际2号楼","sellerReward":"","slot":"[1,1,1,1,1,1]","state":"0","stock":"6","sumProfit":"","unfreezeMoney":"0.00","updateTime":1555359984000}]
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
             * agentId : 730
             * boxAddress : 老海亭(海亭路一段)风味烤鱼
             * details : 200
             * employeeId : 0
             * freezeMoney : 0.00
             * freezeTime :
             * iccid : 0
             * id : JZCB061903000099
             * installRealname :
             * isFreeze : 2
             * latitude : 27.862633
             * longitude : 102.263055
             * offlineTime :
             * remitTime :
             * resultFreezeMoney :
             * sellerId : 67
             * sellerLinkman : 吴凯
             * sellerName : 兆信国际2号楼
             * sellerReward :
             * slot : [1,1,1,1,1,1]
             * state : 0
             * stock : 6
             * sumProfit :
             * unfreezeMoney : 0.00
             * updateTime : 1555359984000
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
