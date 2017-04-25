package com.jiaokaokeji.gaochuangkeji.my.prjo;

import java.util.List;

/**
 * Created by zm on 2017/4/12.
 */

public class Person {

    /**
     * resultCode : 500
     * message : 找不到这样的key
     * data : [{"user_id":"1","user_nick":"","user_name":"gao","user_phone":"13006383856","user_sex":"0","user_age":"10","user_idcard":"123456789098765432","user_address":"孝感"}]
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
         * user_id : 1
         * user_nick :
         * user_name : gao
         * user_phone : 13006383856
         * user_sex : 0
         * user_age : 10
         * user_idcard : 123456789098765432
         * user_address : 孝感
         */

        private String user_id;
        private String user_nick;
        private String user_name;
        private String user_phone;
        private String user_sex;
        private String user_age;
        private String user_idcard;
        private String user_address;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_nick() {
            return user_nick;
        }

        public void setUser_nick(String user_nick) {
            this.user_nick = user_nick;
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

        public String getUser_address() {
            return user_address;
        }

        public void setUser_address(String user_address) {
            this.user_address = user_address;
        }
    }
}
