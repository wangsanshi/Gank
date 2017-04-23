package com.wangsanshi.gank.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoBean {

    /**
     * error : false
     * results : [{"_id":"58b8cd61421aa90efc4fb64b","createdAt":"2017-03-03T09:56:49.925Z","desc":"2017奥斯卡开场段子","publishedAt":"2017-04-21T11:30:29.323Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/ExXIfwDU5?from=vhot","used":true,"who":"lxxself"},{"_id":"58f7988f421aa9544ed88990","createdAt":"2017-04-20T01:04:15.616Z","desc":"大连某小学的\u201c篮球操\u201d，男生女生下腰，玩转篮球，简直逆天了！ . \u200b","publishedAt":"2017-04-20T14:03:06.490Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/EF7jMi1As?fid=1034:88ffec38a3a3e0713325bd97cb170000","used":true,"who":"lxxself"},{"_id":"58f63f7c421aa9544b77401a","createdAt":"2017-04-19T00:31:56.490Z","desc":"1986年版《西游记》总导演杨洁去世，这是她在2013年接受《论道》节目采访时的视频。","publishedAt":"2017-04-19T11:44:51.925Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/EEUf7o2KR?fid=1034:4aab144509158979644e43eb58af1c19","used":true,"who":"lxxself"},{"_id":"58bcbdee421aa90f03345127","createdAt":"2017-03-06T09:39:58.976Z","desc":"有生之年，你想和谁去？[心]","publishedAt":"2017-04-18T11:34:29.203Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/Eymux2YK9?fid=1034:89d72c02fc882ee21129020adfc661c3","used":true,"who":"lxxself"},{"_id":"58f41d38421aa9544ed88959","createdAt":"2017-04-17T09:41:12.315Z","desc":"李达康早期作品，我不信我最后一个知道","publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"休息视频","url":"http://www.kanbilibili.com/video/av9818563","used":true,"who":"lxxself"},{"_id":"58f0456a421aa9544825f875","createdAt":"2017-04-14T11:43:38.824Z","desc":"【实测】耳机之间的差别有多大?","publishedAt":"2017-04-14T11:46:48.47Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av8823143/","used":true,"who":"daimajia"},{"_id":"58eed474421aa9544b773fc9","createdAt":"2017-04-13T09:29:24.331Z","desc":"【36氪纪录片】如果描述快手","publishedAt":"2017-04-13T11:36:04.435Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/EE2ZzfA7F?fid=1034:9a8c998da5a6e47f69bdaa2fd4df22c4","used":true,"who":"lxxself"},{"_id":"58b9113e421aa90f03345117","createdAt":"2017-03-03T14:46:22.358Z","desc":"万一能在绝望的时候抓住些什么，所以用力地活着","publishedAt":"2017-04-12T12:12:18.213Z","source":"web","type":"休息视频","url":"https://m.douban.com/page/okbs9nz?FeatureFeedThemeKey=18&dt_ref=02B380E3F459AA448E530105625086E9AD369D659148A6493608ED479712DE414DF04AB726CF3548&dt_dapp=1&dt_platform=com.douban.activity.qq_session","used":true,"who":null},{"_id":"58e67429421aa90d66eef5c5","createdAt":"2017-04-07T01:00:25.748Z","desc":"俄罗斯流行的jumpstyle舞蹈，厉害了！跳这舞比跑长跑都累！[哈哈]","publishedAt":"2017-04-11T14:43:34.738Z","source":"chrome","type":"休息视频","url":"http://weibo.com/tv/v/04612a2cfb1b29a3771a27638d2db8ac?fid=1034:04612a2cfb1b29a3771a27638d2db8ac","used":true,"who":"lxxself"},{"_id":"58e9b556421aa9544b773fa1","createdAt":"2017-04-09T12:15:18.204Z","desc":"好尸六分钟带你看完【玩尽杀绝】香艳的杀鸡","publishedAt":"2017-04-10T12:11:14.794Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av9708577/","used":true,"who":"LHF"}]
     */
    private boolean error;
    /**
     * _id : 58b8cd61421aa90efc4fb64b
     * createdAt : 2017-03-03T09:56:49.925Z
     * desc : 2017奥斯卡开场段子
     * publishedAt : 2017-04-21T11:30:29.323Z
     * source : chrome
     * type : 休息视频
     * url : http://weibo.com/tv/v/ExXIfwDU5?from=vhot
     * used : true
     * who : lxxself
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
        private String createdAt;
        private String desc;
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
