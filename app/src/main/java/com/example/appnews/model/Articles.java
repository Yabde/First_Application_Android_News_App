package com.example.appnews.model;

public class Articles {

    private String id;
    private String name;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
    private Source source;


    /** Contructeurs **/

    public Articles() {
    }

    public Articles(String source_name, String author, String title, String description, String urlToImage, String publishedAt, String url) {
        this.name = source_name;  //Utilisé pour afficher la source dans le détail d'une activité seulement
        this.author = author;
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.url = url;
    }

    public Articles(String id, String name, String author, String title, String description, String url, String urlToImage, String publishedAt, String content) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    // Methode pour prendre en comtpe les différentes sources et les rajouter dans notre SPINNER
    public void setSource(Source source) {
        this.source = source;
    }

    /** Partie Getters **/

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        if(publishedAt != null){
            return publishedAt.replace("T", " ").replace("Z", "");
        }
        return "";
    }

    public String getContent() {
        return content;
    }

    /** Partie SETTERS **/

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
