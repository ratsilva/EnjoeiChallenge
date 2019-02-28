package br.com.enjoeichallenge.models;

public class User {

    private int         id;
    private String      name;
    private Photo       avatar;
    private long        id_photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Photo getAvatar() {
        return avatar;
    }

    public void setAvatar(Photo avatar) {
        this.avatar = avatar;
    }

    public long getId_photo() {
        return id_photo;
    }

    public void setId_photo(long id_photo) {
        this.id_photo = id_photo;
    }
}
