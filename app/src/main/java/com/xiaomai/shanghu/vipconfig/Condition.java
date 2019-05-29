package com.xiaomai.shanghu.vipconfig;

/**
 * Created by EDZ on 2019/5/28.
 */

public class Condition {

   String mobile,messageCode,userName;

    public Condition(String mobile, String messageCode, String userName) {
        this.mobile = mobile;
        this.messageCode = messageCode;
        this.userName = userName;
    }

    public Condition(String mobile) {
        this.mobile = mobile;
    }

    public Condition() {
    }
}
