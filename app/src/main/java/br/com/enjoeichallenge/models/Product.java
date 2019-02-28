package br.com.enjoeichallenge.models;

import java.util.List;

public class Product {

    private int id;
    private double discount_percentage;
    private List<Photo> photos;
    private String title;
    private double price;
    private double original_price;
    private int size;
    private int likes_count;
    private int maximum_installment;
    private int published_comments_count;
    private String content;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(double original_price) {
        this.original_price = original_price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getMaximum_installment() {
        return maximum_installment;
    }

    public void setMaximum_installment(int maximum_installment) {
        this.maximum_installment = maximum_installment;
    }

    public int getPublished_comments_count() {
        return published_comments_count;
    }

    public void setPublished_comments_count(int published_comments_count) {
        this.published_comments_count = published_comments_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

