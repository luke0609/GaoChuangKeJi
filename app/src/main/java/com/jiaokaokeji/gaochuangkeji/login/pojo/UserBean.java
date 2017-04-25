package com.jiaokaokeji.gaochuangkeji.login.pojo;

import java.util.List;

/**
 * Created by Jingsheng Liang on 2017/4/24.
 */

public class UserBean {

    /**
     * resultCode : 200
     * message : 登录成功
     * data : [{"user_id":"2","user_name":"小明","user_phone":"13006383855","user_pwd":"123456","user_sex":"0","user_age":"0","user_idcard":"211","user_progress":"0","user_address":"南京","user_state":"0"}]
     */

    private int resultCode;
    private String message;
    private List<DataBean> data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 2
         * user_name : 小明
         * user_phone : 13006383855
         * user_pwd : 123456
         * user_sex : 0
         * user_age : 0
         * user_idcard : 211
         * user_progress : 0
         * user_address : 南京
         * user_state : 0
         */

        private String user_id;
        private String user_name;
        private String user_phone;
        private String user_pwd;
        private String user_sex;
        private String user_age;
        private String user_idcard;
        private String user_progress;
        private String user_address;
        private String user_state;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getUser_pwd() {
            return user_pwd;
        }

        public void setUser_pwd(String user_pwd) {
            this.user_pwd = user_pwd;
        }

        public String getUser_sex() {
            return user_sex;
        }

        public void setUser_sex(String user_sex) {
            this.user_sex = user_sex;
        }

        public String getUser_age() {
            return user_age;
        }

        public void setUser_age(String user_age) {
            this.user_age = user_age;
        }

        public String getUser_idcard() {
            return user_idcard;
        }

        public void setUser_idcard(String user_idcard) {
            this.user_idcard = user_idcard;
        }

        public String getUser_progress() {
            return user_progress;
        }

        public void setUser_progress(String user_progress) {
            this.user_progress = user_progress;
        }

        public String getUser_address() {
            return user_address;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
        }

        public String getUser_state() {
            return user_state;
        }

        public void setUser_state(String user_state) {
            this.user_state = user_state;
        }
    }
}
