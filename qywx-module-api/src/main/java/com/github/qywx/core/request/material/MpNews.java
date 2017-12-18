package com.github.qywx.core.request.material;

import java.util.List;

/**
 * Created by 图文素材 on 2017/12/15.
 */
public class MpNews {

    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public static Article createArticle(){
        return new Article();
    }

    public static Article createArticle(String title,String thumb_media_id,String author,String content_source_url,String content,String digest,String show_cover_pic) {
        return new Article(title, thumb_media_id, author, content_source_url, content, digest, show_cover_pic);
    }
}
