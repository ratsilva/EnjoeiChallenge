package br.com.enjoeichallenge.objects;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private long id;
    private double discount_percentage;
    private ArrayList<Photo> photos;
    private String title;
    private double price;
    private double original_price;
    private String size;
    private int likes_count;
    private int maximum_installment;
    private int published_comments_count;
    private String content;
    private User user;
    private long id_user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
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

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public class ProductJson{

        List<Product> products;

        public List<Product> getProduts() {
            return products;
        }

        public void setProduts(List<Product> products) {
            this.products = products;
        }
    }
}

