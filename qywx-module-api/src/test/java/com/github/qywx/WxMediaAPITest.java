package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxMediaAPI;
import com.github.qywx.core.request.material.Article;
import com.github.qywx.core.request.material.MpNews;
import com.github.qywx.core.request.material.News;
import com.github.qywx.core.response.AccessToken;
import com.github.qywx.core.response.media.MediaRes;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjtang on 2017/12/15.
 */
public class WxMediaAPITest {

    private static Logger logger = LoggerFactory.getLogger(WxMediaAPI.class);

    private AccessToken accessToken;

    @Before
    public void beforeMethod(){
        accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
    }

    //上传永久素材
    @Test
    public void testUploadMedia() throws Exception{
        MediaRes mediaRes = WxMediaAPI.mediaUpload(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg"),"image",accessToken.getAccess_token());
        System.out.println(mediaRes);//
    }

    @Test
    public void testDownloadMedia() throws Exception{
        WxMediaAPI.mediaDownload("d:\\code","322YOCi7PwsWxKiWpdZoFnAOqxPRzhzS355W4tILytrSSpJz8NFBVab-TqkfrN_dH",accessToken.getAccess_token());
    }

    @Test
    public void testMapNewsUload() throws Exception{
        MpNews mpnews = new MpNews();
        List<Article> articles = new ArrayList<Article>();
        Article article = new Article();
        // 图文消息标题
        article.setTitle("上传永久图文素材测试-1");
        // 图文消息缩略图的media_id, 可以在上传永久素材接口中获得
        article.setThumb_media_id("322YOCi7PwsWxKiWpdZoFnAOqxPRzhzS355W4tILytrSSpJz8NFBVab-TqkfrN_dH");
        // 图文消息的作者
        article.setAuthor("tangkaijun");
        // 图文消息点击“阅读原文”之后的页面链接
        article.setContent_source_url("http://www.baidu.com");
        // 图文消息的内容，支持html标签
        article.setContent("上传永久图文素材测试-1");
        // 图文消息的描述
        article.setDigest("上传永久图文素材测试-1");
        // 是否显示封面，1为显示，0为不显示。默认为0
        article.setShow_cover_pic("1");
        articles.add(article);
        mpnews.setArticles(articles);
        News news = new News();
        news.setTouser("khtang");
        news.setMsgtype("mpnews");
        news.setAgentid(1000002);
        news.setMpnews(mpnews);
        MediaRes mediaRes=WxMediaAPI.mpNewsUpload(news,accessToken.getAccess_token());
        System.out.println(mediaRes);
    }

    /**测试删除素材*/
    @Test
    public void testDeleteMedia() throws Exception{
        WxMediaAPI.deleteMedia("322YOCi7PwsWxKiWpdZoFnAOqxPRzhzS355W4tILytrSSpJz8NFBVab-TqkfrN_dH",accessToken.getAccess_token());
    }

}
