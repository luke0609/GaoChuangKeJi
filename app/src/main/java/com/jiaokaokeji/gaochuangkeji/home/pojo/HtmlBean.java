package com.jiaokaokeji.gaochuangkeji.home.pojo;

import java.util.List;

/**
 * Created by Jingsheng Liang on 2017/6/1.
 */

public class HtmlBean {
    /**
     * resultCode : 200
     * message : 查询成功
     * data : [{"htmls_id":"1","htmls_title":"驾校简介","htmls_content":"南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。","htmls_url":"http://192.168.0.112:8888/jianjie.html","htmls_iid":"1"},{"htmls_id":"2","htmls_title":"教学特色","htmls_content":"天保驾校成立二十年来，培训质量始终居同行业领先水平，形成了鲜明的教学特色，受到了学员的一致认可。","htmls_url":"http://192.168.0.112:8888/tese.html","htmls_iid":"2"}]
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
         * htmls_id : 1
         * htmls_title : 驾校简介
         * htmls_content : 南京天保驾校始建于1990年，有着二十多年的办学历史和经验，是全市一流的汽车、摩托车驾驶员培训专业学校，年培训规模达2万余人。
         * htmls_url : http://192.168.0.112:8888/jianjie.html
         * htmls_iid : 1
         */

        private String htmls_id;
        private String htmls_title;
        private String htmls_content;
        private String htmls_url;
        private String htmls_iid;

        public String getHtmls_id() {
            return htmls_id;
        }

        public void setHtmls_id(String htmls_id) {
            this.htmls_id = htmls_id;
        }

        public String getHtmls_title() {
            return htmls_title;
        }

        public void setHtmls_title(String htmls_title) {
            this.htmls_title = htmls_title;
        }

        public String getHtmls_content() {
            return htmls_content;
        }

        public void setHtmls_content(String htmls_content) {
            this.htmls_content = htmls_content;
        }

        public String getHtmls_url() {
            return htmls_url;
        }

        public void setHtmls_url(String htmls_url) {
            this.htmls_url = htmls_url;
        }

        public String getHtmls_iid() {
            return htmls_iid;
        }

        public void setHtmls_iid(String htmls_iid) {
            this.htmls_iid = htmls_iid;
        }
    }
}
