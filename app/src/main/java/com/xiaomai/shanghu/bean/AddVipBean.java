package com.xiaomai.shanghu.bean;

import java.util.List;

/**
 * Created by EDZ on 2019/5/28.
 */

public class AddVipBean {

    /**
     * code : 1
     * data : {"limit":100,"list":[{"id":1,"mobile":"15198264880","resultFreeTime":"00时00分00秒","userName":"账户"},{"id":3,"mobile":"18582035485","resultFreeTime":"00时00分00秒","userName":"王**"}],"pageIndex":1,"pageSize":10,"resultNumber":0,"total":2,"totalPages":0}
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
         * limit : 100
         * list : [{"id":1,"mobile":"15198264880","resultFreeTime":"00时00分00秒","userName":"账户"},{"id":3,"mobile":"18582035485","resultFreeTime":"00时00分00秒","userName":"王**"}]
         * pageIndex : 1
         * pageSize : 10
         * resultNumber : 0
         * total : 2
         * totalPages : 0
         */

        private int limit;
        private int pageIndex;
        private int pageSize;
        private int resultNumber;
        private int total;
        private int totalPages;
        private List<ListBean> list;

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

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
             * id : 1
             * mobile : 15198264880
             * resultFreeTime : 00时00分00秒
             * userName : 账户
             */

            private int id;
            private String mobile;
            private String resultFreeTime;
            private String userName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getResultFreeTime() {
                return resultFreeTime;
            }

            public void setResultFreeTime(String resultFreeTime) {
                this.resultFreeTime = resultFreeTime;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
