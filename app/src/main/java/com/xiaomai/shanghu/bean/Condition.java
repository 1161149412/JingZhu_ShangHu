package com.xiaomai.shanghu.bean;

/**
 * Created by EDZ on 2019/4/22.
 */

public class Condition {
    String sellerId;
    String state;
    String deviceId;
    String slot;

    String isFreeze;

    String moneyMax;
    String moneyMin;
    String time;

    String count;

    public Condition(String count) {
        this.count = count;
    }

    public Condition(String sellerId, String state, String deviceId, String slot) {
        this.sellerId = sellerId;
        this.state = state;
        this.deviceId = deviceId;
        this.slot = slot;
    }

    public Condition( String isFreeze,String deviceId, String slot) {
        this.deviceId = deviceId;
        this.slot = slot;
        this.isFreeze = isFreeze;
    }

    public Condition( String moneyMax, String moneyMin, String time, String state ,String sellerId) {
        this.sellerId = sellerId;
        this.state = state;
        this.moneyMax = moneyMax;
        this.moneyMin = moneyMin;
        this.time = time;
    }
}
