package com.wangsanshi.gank.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeneralBean {
    /**
     * error : false
     * results : [{"_id":"58eae3b8421aa9544825f83f","createdAt":"2017-04-10T09:45:28.617Z","desc":"Unsplash 的三方壁纸客户端","images":["http://img.gank.io/da234724-80ab-4059-ae3b-52831508e45c"],"publishedAt":"2017-04-10T12:11:14.794Z","source":"chrome","type":"App","url":"https://github.com/WangDaYeeeeee/Mysplash","used":true,"who":"咕咚"},{"_id":"58d1ee44421aa90f033451bc","createdAt":"2017-03-22T11:23:48.960Z","desc":"上传到图片到微博并生成外链的 Chrome 浏览器扩展（最低版本支持：Chrome 56）","publishedAt":"2017-03-22T11:47:09.555Z","source":"chrome","type":"App","url":"https://github.com/Aqours/Weibo-Picture-Store","used":true,"who":"代码家"},{"_id":"58cf3b28421aa90f13178699","createdAt":"2017-03-20T10:15:04.103Z","desc":"实时歌词，Ubuntu 下的一款歌词 App","images":["http://img.gank.io/ca9116e1-6ce1-4667-bf11-e1bfd03d9d78"],"publishedAt":"2017-03-20T11:44:56.224Z","source":"chrome","type":"App","url":"https://github.com/bhrigu123/Instant-Lyrics","used":true,"who":"lii"},{"_id":"58c56618421aa95810795c49","createdAt":"2017-03-12T23:15:36.502Z","desc":"📱一款追求全新用户体验的干货集中营 iOS客户端","images":["http://img.gank.io/f772bc0b-c973-42c9-bfdb-3b16be2886f4","http://img.gank.io/66ab873a-6050-45c5-ac00-1a31e7cf5621"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"web","type":"App","url":"https://github.com/iphone5solo/Gank","used":true,"who":"CoderKo1o"},{"_id":"58b1970e421aa957ef93533a","createdAt":"2017-02-25T22:39:10.739Z","desc":"Tale 一款简洁美观的Java博客管理发布系统","images":["http://img.gank.io/ebc695dd-6006-4a7b-8239-2069f07b1735","http://img.gank.io/0bb2346a-4b75-4936-8f08-1c7dbcfb6d33"],"publishedAt":"2017-02-28T11:45:44.815Z","source":"chrome","type":"App","url":"https://github.com/otale/tale","used":true,"who":"咕咚"},{"_id":"58b3863a421aa90efc4fb615","createdAt":"2017-02-27T09:51:54.184Z","desc":"简影讯，一款简洁优雅、完全MD风格的影讯app。基于Colorful换肤框架，具有自定义多彩主题和夜间模式，使用RxJava+Retrofit+MVP","images":["http://img.gank.io/58d430a8-2c39-442d-b497-5484c21ff79b","http://img.gank.io/d7948807-f37d-4927-80f6-b9c387d62125"],"publishedAt":"2017-02-27T11:31:40.141Z","source":"web","type":"App","url":"https://github.com/woxingxiao/GracefulMovies","used":true,"who":"Xiao"},{"_id":"58afa1ab421aa957ef93532f","createdAt":"2017-02-24T10:59:55.244Z","desc":"简单漂亮的应用锁。","images":["http://img.gank.io/16cd3576-bb1b-4455-8a38-c794c6c71792","http://img.gank.io/2ccf614a-7da3-423e-af52-ad05ffa2002f"],"publishedAt":"2017-02-24T11:47:11.416Z","source":"chrome","type":"App","url":"https://github.com/lizixian18/AppLock","used":true,"who":"代码家"},{"_id":"58a6739b421aa966366d05ec","createdAt":"2017-02-17T11:52:59.346Z","desc":"gank.io首个win10客户端，支持磁贴、通知、小娜\u2026\u2026已发布到应用商店，欢迎下载。","images":["http://img.gank.io/3a873787-6bba-4549-b3c0-44be014f49e7"],"publishedAt":"2017-02-20T11:56:22.616Z","source":"api","type":"App","url":"https://github.com/lhysrc/gank.uwp","used":true,"who":"Hy.Lim"},{"_id":"58aa63ad421aa93d33938852","createdAt":"2017-02-20T11:34:05.832Z","desc":"老司机娱乐必备","images":["http://img.gank.io/d0270173-caf5-4301-b329-f5c3fdf22876"],"publishedAt":"2017-02-20T11:56:22.616Z","source":"web","type":"App","url":"https://github.com/guodongAndroid/guodong","used":true,"who":"孫小逗"},{"_id":"589414c6421aa97bb3b1a371","createdAt":"2017-02-03T13:27:34.760Z","desc":"可能是 iOS 上最好用的 Gank.io 客户端，已开源","publishedAt":"2017-02-04T11:47:42.336Z","source":"web","type":"App","url":"https://github.com/Wildog/Gankee","used":true,"who":"Wildog"}]
     */

    private boolean error;
    /**
     * _id : 58eae3b8421aa9544825f83f
     * createdAt : 2017-04-10T09:45:28.617Z
     * desc : Unsplash 的三方壁纸客户端
     * images : ["http://img.gank.io/da234724-80ab-4059-ae3b-52831508e45c"]
     * publishedAt : 2017-04-10T12:11:14.794Z
     * source : chrome
     * type : App
     * url : https://github.com/WangDaYeeeeee/Mysplash
     * used : true
     * who : 咕咚
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

    public static class ResultsBean implements Parcelable {
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
        @Nullable
        private List<String> images;//该字段在type为福利的时候可以为空

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

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
            dest.writeStringList(this.images);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this.id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
            this.images = in.createStringArrayList();
        }

        public static final Parcelable.Creator<ResultsBean> CREATOR = new Parcelable.Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }
}
