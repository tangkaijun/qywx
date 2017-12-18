package com.github.qywx.core.request.material;

/**
 * Created by kjtang on 2017/12/15.
 */
public class Article {

    private String title;
    private String thumb_media_id;
    private String author;
    private String content_source_url;
    private String content;
    private String digest;
    private String show_cover_pic;

    public Article(){

    }

    public Article(String title,String thumb_media_id,String author,String content_source_url,String content,String digest,String show_cover_pic){
        this.title = title;
        this.thumb_media_id = thumb_media_id;
        this.author = author;
        this.content_source_url = content_source_url;
        this.content = content;
        this.digest = digest;
        this.show_cover_pic = show_cover_pic;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }

    public Article setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Article setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContent_source_url() {
        return content_source_url;
    }

    public Article setContent_source_url(String content_source_url) {
        this.content_source_url = content_source_url;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDigest() {
        return digest;
    }

    public Article setDigest(String digest) {
        this.digest = digest;
        return this;
    }

    public String getShow_cover_pic() {
        return show_cover_pic;
    }

    public Article setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
        return this;
    }
}
