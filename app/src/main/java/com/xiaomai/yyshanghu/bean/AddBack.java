package com.xiaomai.yyshanghu.bean;

/**
 * Created by EDZ on 2019/4/23.
 */

public class AddBack {
    String bank,creditCard,mobile,realName;
    String id;

    public AddBack(String bank, String creditCard, String id, String mobile, String realName) {
        this.bank = bank;
        this.creditCard = creditCard;
        this.id = id;
        this.mobile = mobile;
        this.realName = realName;
    }
}
