package model;

public class Article_Category {
    public int article_category_id;
    public String article_category_name;

    public Article_Category(int article_category_id, String article_category_name) {
        this.article_category_id = article_category_id;
        this.article_category_name = article_category_name;
    }

    public int getArticle_category_id() {
        return article_category_id;
    }

    public void setArticle_category_id(int article_category_id) {
        this.article_category_id = article_category_id;
    }

    public String getArticle_category_name() {
        return article_category_name;
    }

    public void setArticle_category_name(String article_category_name) {
        this.article_category_name = article_category_name;
    }

    @Override
    public String toString() {
        return "Article_Category{" +
                "article_category_id=" + article_category_id +
                ", article_category_name='" + article_category_name + '\'' +
                '}';
    }
}
