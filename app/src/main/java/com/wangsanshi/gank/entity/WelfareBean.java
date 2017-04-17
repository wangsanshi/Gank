package com.wangsanshi.gank.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WelfareBean {

    /**
     * error : false
     * results : [{"_id":"58eef016421aa9544b773fd0","createdAt":"2017-04-13T11:27:18.557Z","desc":"4-13","publishedAt":"2017-04-13T11:36:04.435Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-13-17882785_926451654163513_7725522121023029248_n.jpg","used":true,"who":"dmj"},{"_id":"58ed8242421aa9544b773fc1","createdAt":"2017-04-12T09:26:26.434Z","desc":"4-12","publishedAt":"2017-04-12T12:12:18.213Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-12-17662441_1675934806042139_7236493360834281472_n.jpg","used":true,"who":"daimajia"},{"_id":"58ec30e4421aa9544ed88919","createdAt":"2017-04-11T09:27:00.840Z","desc":"4-11","publishedAt":"2017-04-11T14:43:34.738Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-11-17881546_248332202297978_2420944671002853376_n.jpg","used":true,"who":"dmj"},{"_id":"58ea5f51421aa954511ebe51","createdAt":"2017-04-10T00:20:33.996Z","desc":"4-10","publishedAt":"2017-04-10T12:11:14.794Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-09-17586558_426275167734768_6312107349515436032_n.jpg","used":true,"who":"dmj"},{"_id":"58e6dec3421aa90d6f211e4a","createdAt":"2017-04-07T08:35:15.664Z","desc":"4-7","publishedAt":"2017-04-07T12:02:31.316Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-07-17817932_274034076387428_5240190736292380672_n.jpg","used":true,"who":"带马甲"},{"_id":"58e5bd9c421aa90d6f211e3f","createdAt":"2017-04-06T12:01:32.576Z","desc":"4-6","publishedAt":"2017-04-06T12:06:10.17Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-06-17493825_1061197430652762_1457834104966873088_n.jpg","used":true,"who":"代码家"},{"_id":"58e3198e421aa90d6bc75ab4","createdAt":"2017-04-04T11:57:02.111Z","desc":"4-4","publishedAt":"2017-04-05T11:50:18.748Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-04-17438270_1418311001574160_8728796670000627712_n.jpg","used":true,"who":"dmj"},{"_id":"58ddcb2b421aa969f51a911e","createdAt":"2017-03-31T11:21:15.303Z","desc":"3-31","publishedAt":"2017-03-31T11:26:54.330Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-31-17662615_1819763634937161_2829588299293655040_n.jpg","used":true,"who":"dmj"},{"_id":"58dc5645421aa969fd8a3dea","createdAt":"2017-03-30T08:50:13.178Z","desc":"3-30","publishedAt":"2017-03-30T11:46:55.192Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-30-17265582_1877445642507654_3057988544061505536_n.jpg","used":true,"who":"dmj"},{"_id":"58db2305421aa969fb0fbed1","createdAt":"2017-03-29T10:59:17.522Z","desc":"3-29","publishedAt":"2017-03-29T11:48:49.343Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-29-17267498_392021674501739_8632065627013513216_n.jpg","used":true,"who":"dmj"}]
     */

    private boolean error;
    /**
     * _id : 58eef016421aa9544b773fd0
     * createdAt : 2017-04-13T11:27:18.557Z
     * desc : 4-13
     * publishedAt : 2017-04-13T11:36:04.435Z
     * source : chrome
     * type : 福利
     * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-13-17882785_926451654163513_7725522121023029248_n.jpg
     * used : true
     * who : dmj
     */

    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        @SerializedName("_id")
        private String id;
        @Expose(deserialize = false, serialize = false)
        private String createdAt;
        private String desc;
        @Expose(deserialize = false, serialize = false)
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}
