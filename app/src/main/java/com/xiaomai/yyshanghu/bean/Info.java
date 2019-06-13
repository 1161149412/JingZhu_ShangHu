package com.xiaomai.yyshanghu.bean;

/**
 * Created by EDZ on 2019/4/22.
 */

public class Info {
    String nowPage;
    String pageSize;
    Condition condition;

    public Info(String nowPage, String pageSize, Condition condition) {
        this.nowPage = nowPage;
        this.pageSize = pageSize;
        this.condition = condition;
    }
}
