package model;

import service.ArticleService;

import java.util.ArrayList;

public class Article {
    public int article_id;
    public int article_category_id;
    public String date;
    public String title;
    public String content;

    public Article(int article_id, int article_category_id, String date, String title, String content) {
        this.article_id = article_id;
        this.article_category_id = article_category_id;
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getArticle_category_id() {
        return article_category_id;
    }

    public void setArticle_category_id(int article_category_id) {
        this.article_category_id = article_category_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageArticle(int index)
    {
        ArticleService manage = new ArticleService();
        ArrayList articleImages = manage.getImage(article_id);
        if (articleImages.size() > 0)
        {
            if (articleImages.size() > index)
            {
                Image_Article img = (Image_Article) articleImages.get(index);
                return img.url;
            }
        }
        return "";
    }
}
