package com.github.qywx.core.request.message;

import java.util.List;

/**
 * Created by kjtang on 2017/12/12.
 * 图文消息
 */
public class NewsMessage {

    private News news;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    static class  News{

        //文章列表
        private List<Article> articles;

        public List<Article> getArticles() {
            return articles;
        }

        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }
    }

    static class Article{
          //标题，不超过128个字节，超过会自动截断
          private String title;
          //描述，不超过512个字节，超过会自动截断
          private String description;
          //点击后跳转的链接。
          private String url;
          //图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640320，小图8080。
          private String picurl;
          //按钮文字，仅在图文数为1条时才生效。 默认为“阅读全文”， 不超过4个文字，超过自动截断。该设置只在企业微信上生效，微信插件上不生效。
          private String btntxt;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getBtntxt() {
            return btntxt;
        }

        public void setBtntxt(String btntxt) {
            this.btntxt = btntxt;
        }
    }
}
