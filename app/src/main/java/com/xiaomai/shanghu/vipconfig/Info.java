package com.xiaomai.shanghu.vipconfig;

/**
 * Created by EDZ on 2019/5/28.
 */

public class Info {
    String nowPage;
    String pageSize;
    Condition condition;

    String mobile;

    public Info(String nowPage, String pageSize,Condition condition) {
        this.nowPage = nowPage;
        this.pageSize = pageSize;
        this.condition = condition;
    }

    public Info(String mobile) {
        this.mobile = mobile;
    }
}
