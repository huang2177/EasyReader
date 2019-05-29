package com.huang.monkeybook.bean;

import android.text.TextUtils;

/**
 * Created by Administrator on 2019/5/27.
 */

public class MainResult {

    /**
     * is_wap : 0
     * wap_url : https://ux600.com/add/index.html
     * is_update : 0
     * update_url : https://futiancn.com/app/0567.apk
     * code : 200
     * msg :
     */

    private String is_wap;
    private String wap_url;
    private String is_update;
    private String update_url;
    private int code;
    private String msg;

    public boolean isWap() {
        return TextUtils.equals(is_wap, "1");
    }

    public void setIs_wap(String is_wap) {
        this.is_wap = is_wap;
    }

    public String getWap_url() {
        return wap_url;
    }

    public void setWap_url(String wap_url) {
        this.wap_url = wap_url;
    }

    public boolean isUpdate() {
        return TextUtils.equals(is_update, "1");
    }

    public void setIs_update(String is_update) {
        this.is_update = is_update;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public boolean isSuccess() {
        return code == 200;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
