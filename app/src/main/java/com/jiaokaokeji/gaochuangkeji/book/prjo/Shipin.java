package com.jiaokaokeji.gaochuangkeji.book.prjo;

import java.util.List;

/**
 * Created by zm on 2017/4/25.
 */

public class Shipin {

    /**
     * resultCode : 200
     * message : 查询成功
     * data : [{"videos_id":"1","videos_title":"倒车入库","videos_imge":"http://www.tbqjx.com/loadimage.php?id=54","videos_url":"http://www.tbqjx.com/files/video/7.mp4","videos_tyle":"2"}]
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
         * videos_id : 1
         * videos_title : 倒车入库
         * videos_imge : http://www.tbqjx.com/loadimage.php?id=54
         * videos_url : http://www.tbqjx.com/files/video/7.mp4
         * videos_tyle : 2
         */

        private String videos_id;
        private String videos_title;
        private String videos_imge;
        private String videos_url;
        private String videos_tyle;

        public String getVideos_id() {
            return videos_id;
        }

        public void setVideos_id(String videos_id) {
            this.videos_id = videos_id;
        }

        public String getVideos_title() {
            return videos_title;
        }

        public void setVideos_title(String videos_title) {
            this.videos_title = videos_title;
        }

        public String getVideos_imge() {
            return videos_imge;
        }

        public void setVideos_imge(String videos_imge) {
            this.videos_imge = videos_imge;
        }

        public String getVideos_url() {
            return videos_url;
        }

        public void setVideos_url(String videos_url) {
            this.videos_url = videos_url;
        }

        public String getVideos_tyle() {
            return videos_tyle;
        }

        public void setVideos_tyle(String videos_tyle) {
            this.videos_tyle = videos_tyle;
        }
    }

    @Override
    public String toString() {
        return "Shipin{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
