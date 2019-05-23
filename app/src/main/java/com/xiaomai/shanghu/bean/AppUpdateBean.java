package com.xiaomai.shanghu.bean;

/**
 * Created by EDZ on 2019/5/23.
 */

public class AppUpdateBean {

    /**
     * id : 3
     * udate :
     * newVersion : 1.0
     * apkFileUrl : null
     * updateLog : 商户端第一版更新
     * targetSize : 12.0MB
     * newMd5 : null
     * cons : true
     * versionType : 1
     */

    private int id;
    private String udate;
    private String newVersion;
    private Object apkFileUrl;
    private String updateLog;
    private String targetSize;
    private Object newMd5;
    private boolean cons;
    private String versionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public Object getApkFileUrl() {
        return apkFileUrl;
    }

    public void setApkFileUrl(Object apkFileUrl) {
        this.apkFileUrl = apkFileUrl;
    }

    public String getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(String updateLog) {
        this.updateLog = updateLog;
    }

    public String getTargetSize() {
        return targetSize;
    }

    public void setTargetSize(String targetSize) {
        this.targetSize = targetSize;
    }

    public Object getNewMd5() {
        return newMd5;
    }

    public void setNewMd5(Object newMd5) {
        this.newMd5 = newMd5;
    }

    public boolean isCons() {
        return cons;
    }

    public void setCons(boolean cons) {
        this.cons = cons;
    }

    public String getVersionType() {
        return versionType;
    }

    public void setVersionType(String versionType) {
        this.versionType = versionType;
    }
}
