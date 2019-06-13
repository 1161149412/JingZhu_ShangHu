package com.xiaomai.yyshanghu.bean;

import java.util.List;

/**
 * Created by EDZ on 2019/4/22.
 * 商户个人信息
 */

public class GetMessageBean {

    /**
     * code : 1
     * data : {"address":"四川省成都市武侯区","agentId":0,"area":"武侯区","canRent":true,"canReturn":false,"city":"成都","createTime":1548478427000,"createTimestr":"","details":"","headPic":"","id":67,"lastLoginip":"125.71.177.222","lastLogintime":1555913021835,"latitude":30.62192,"linkman":"吴","linktel":"15202856300","longitude":103.98008,"name":"兆信国际2号楼","openTime":"0:00-24:00","openid":"","personCost":"10","pics":"[\"https://www.jzcdsc.com/pics/big1.jpg\",\"https://www.jzcdsc.com/pics/big2.jpg\",\"https://www.jzcdsc.com/pics/big3.jpg\"]","picsList":[],"province":"四川","reward":0,"smallpic":"https://www.jzcdsc.com/pics/big1.jpg","updateTime":1554980842000,"updateTimestr":"","weixinNickname":"","weixinToken":"16b8da1f-6939-418a-b505-47a1aa36e887"}
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
         * address : 四川省成都市武侯区
         * agentId : 0
         * area : 武侯区
         * canRent : true
         * canReturn : false
         * city : 成都
         * createTime : 1548478427000
         * createTimestr :
         * details :
         * headPic :
         * id : 67
         * lastLoginip : 125.71.177.222
         * lastLogintime : 1555913021835
         * latitude : 30.62192
         * linkman : 吴
         * linktel : 15202856300
         * longitude : 103.98008
         * name : 兆信国际2号楼
         * openTime : 0:00-24:00
         * openid :
         * personCost : 10
         * pics : ["https://www.jzcdsc.com/pics/big1.jpg","https://www.jzcdsc.com/pics/big2.jpg","https://www.jzcdsc.com/pics/big3.jpg"]
         * picsList : []
         * province : 四川
         * reward : 0
         * smallpic : https://www.jzcdsc.com/pics/big1.jpg
         * updateTime : 1554980842000
         * updateTimestr :
         * weixinNickname :
         * weixinToken : 16b8da1f-6939-418a-b505-47a1aa36e887
         */

        private String address;
        private int agentId;
        private String area;
        private boolean canRent;
        private boolean canReturn;
        private String city;
        private long createTime;
        private String createTimestr;
        private String details;
        private String headPic;
        private int id;
        private String lastLoginip;
        private long lastLogintime;
        private double latitude;
        private String linkman;
        private String linktel;
        private double longitude;
        private String name;
        private String openTime;
        private String openid;
        private String personCost;
        private String pics;
        private String province;
        private int reward;
        private String smallpic;
        private long updateTime;
        private String updateTimestr;
        private String weixinNickname;
        private String weixinToken;
        private List<?> picsList;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getAgentId() {
            return agentId;
        }

        public void setAgentId(int agentId) {
            this.agentId = agentId;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public boolean isCanRent() {
            return canRent;
        }

        public void setCanRent(boolean canRent) {
            this.canRent = canRent;
        }

        public boolean isCanReturn() {
            return canReturn;
        }

        public void setCanReturn(boolean canReturn) {
            this.canReturn = canReturn;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCreateTimestr() {
            return createTimestr;
        }

        public void setCreateTimestr(String createTimestr) {
            this.createTimestr = createTimestr;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLastLoginip() {
            return lastLoginip;
        }

        public void setLastLoginip(String lastLoginip) {
            this.lastLoginip = lastLoginip;
        }

        public long getLastLogintime() {
            return lastLogintime;
        }

        public void setLastLogintime(long lastLogintime) {
            this.lastLogintime = lastLogintime;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getLinktel() {
            return linktel;
        }

        public void setLinktel(String linktel) {
            this.linktel = linktel;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOpenTime() {
            return openTime;
        }

        public void setOpenTime(String openTime) {
            this.openTime = openTime;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getPersonCost() {
            return personCost;
        }

        public void setPersonCost(String personCost) {
            this.personCost = personCost;
        }

        public String getPics() {
            return pics;
        }

        public void setPics(String pics) {
            this.pics = pics;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getReward() {
            return reward;
        }

        public void setReward(int reward) {
            this.reward = reward;
        }

        public String getSmallpic() {
            return smallpic;
        }

        public void setSmallpic(String smallpic) {
            this.smallpic = smallpic;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public String getUpdateTimestr() {
            return updateTimestr;
        }

        public void setUpdateTimestr(String updateTimestr) {
            this.updateTimestr = updateTimestr;
        }

        public String getWeixinNickname() {
            return weixinNickname;
        }

        public void setWeixinNickname(String weixinNickname) {
            this.weixinNickname = weixinNickname;
        }

        public String getWeixinToken() {
            return weixinToken;
        }

        public void setWeixinToken(String weixinToken) {
            this.weixinToken = weixinToken;
        }

        public List<?> getPicsList() {
            return picsList;
        }

        public void setPicsList(List<?> picsList) {
            this.picsList = picsList;
        }
    }
}
